package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Equipo;

public class DBManager {

	static Connection conn;
	static Statement stmt = null;

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	// URL PORK ASI DE LARGO???
	// SE PODRIA HACER ONLINE???
	private static final String URL = "jdbc:mysql://localhost:3306/wikifutbolschema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USUARIO = "admin";
	private static final String CONTRASENA = "1234";

	public static void connect() throws DBManagerException {
		try {
			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		} catch (ClassNotFoundException | SQLException e) {
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
		} catch (Exception e) {
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

			if (rs.getInt("admin") == 1) {
				return true;
			} else if (rs.getInt("admin") == 0) {
				return false;
			}
			rs.close();
			stmt.close();
			disconnect();
		} catch (Exception e) {
			// hay k plantearse quitar este "error"
			throw new DBManagerException("Error esAdmin DBManager, o no es admin", e);
		}
		return false;
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
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {

	}
}
