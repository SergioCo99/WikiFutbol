package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import clases.Equipo;

public class DBManager {

	static Connection conn;
	static Statement stmt = null;

	public static void connect() throws DBManagerException {
		try {

			Properties prop = mainPackage.PropertiesMetodos.loadPropertiesFile();
			String CONTROLADOR = prop.getProperty("DB.CONTROLADOR");
			String URL = prop.getProperty("DB.URL");
			String USUARIO = prop.getProperty("DB.USUARIO");
			String CONTRASENA = prop.getProperty("DB.CONTRASENA");

			Class.forName(CONTROLADOR); // esto para que sirve???
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		} catch (Exception e) {
			throw new DBManagerException("Error conexion DBManager", e);
		}
	}

	public static void disconnect() throws DBManagerException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DBManagerException("Error desconexion DBManager", e);
		}
	}

	public static void registrarUsuario(String nombre_usuario, String contrasena, String/* ¿es String? */ fechaNac)
			throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "insert into usuario(nombre_usuario, contrasena, fechaNac) values('" + nombre_usuario + "','"
					+ contrasena + "','" + fechaNac + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error registrarUsuario DBManager", e);
		}
	}

	public static boolean login(String nombre_usuario, String contrasena) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select * from usuario where nombre_usuario = '" + nombre_usuario + "' and contrasena = '"
					+ contrasena + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			String dato = rs.getString("contrasena");
			if (dato.equals(contrasena)) {
				rs.close();
				stmt.close();
				disconnect();
				return true;
			} else {
				rs.close();
				stmt.close();
				disconnect();
				return false;
			}
		} catch (SQLException e) {
			throw new DBManagerException("Error registrarUsuario DBManager, o no existe usuario", e);
			// MUCHO TEXTO?, igual hay que quitar la "e" :v
		}
	}

	public static boolean esAdmin(String nombre_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select admin from usuario where nombre_usuario = '" + nombre_usuario + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			if (rs.getInt("admin") == 1) {
				rs.close();
				stmt.close();
				disconnect();
				return true;
			} else if (rs.getInt("admin") == 0) {
				rs.close();
				stmt.close();
				disconnect();
				return false;
			}
		} catch (SQLException e) {
			// hay k plantearse quitar este "error"
			// throw new DBManagerException("Error esAdmin DBManager, o no es admin", e);
		}
		return false;
	}

	public static void cambiarAdmin(String nombre_usuario, int admin) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "update usuario set admin = '" + admin + "' where nombre_usuario = '" + nombre_usuario + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error cambiarAdmin DBManager", e);
		}
	}

	public static void eliminarUsuario(String nombre_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "delete from usuario where nombre_usuario = '" + nombre_usuario + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error eliminarUsuario DBManager", e);
		}
	}

	public static void cambiarContrasena(String nombre_usuario, String contrasena) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "update usuario set contrasena = '" + contrasena + "' where nombre_usuario = '"
					+ nombre_usuario + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error cambiarContrasena DBManager", e);
		}
	}

	public static ArrayList<String> verTablas() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "use wikifutbolschema;";
			stmt.executeQuery(sql1);
			String sql2 = "show tables;";
			ResultSet rs = stmt.executeQuery(sql2);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				int i = 1;
				arr.add(rs.getString(i));
				i++;
			}
			return arr;
		} catch (SQLException e) {
			throw new DBManagerException("Error verTablas DBManager", e);
		}
	}

	// ???
	public ArrayList<Equipo> getEquipos() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Equipo> array = new ArrayList<Equipo>();
			ResultSet rs = stmt.executeQuery("select * from equipo");
			while (rs.next()) {
				Equipo e = new Equipo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getString(7));
				array.add(e);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (Exception e) {
			throw new DBManagerException("Error getEquipos DBManager", e);
		}
	}// ???

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		connect();
	}
}
