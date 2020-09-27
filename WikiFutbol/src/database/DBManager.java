package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	static Connection conn;
	static Statement stmt = null;

	public static void connect() throws DBManagerException {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database.db");
			System.out.println("connect VA");
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBManagerException("Error conexion DBManager", e);
		}
	}

	public static void disconnect() throws DBManagerException {
		try {
			conn.close();
			System.out.println("disconnect VA");
		} catch (SQLException e) {
			throw new DBManagerException("Error desconexion DBManager", e);
		}
	}

	public static void registrarUsuario(String nombre_usuario, String contrasena) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "insert into usuario(nombre_usuario, contrasena) values('" + nombre_usuario + "','"
					+ contrasena + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
			System.out.println(sql);
			System.out.println("registrarUsuario VA");
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
				System.out.println(sql);
				System.out.println("login true VA");
				rs.close();
				stmt.close();
				disconnect();
				return true;
			} else {
				System.out.println(sql);
				System.out.println("login false VA");
				rs.close();
				stmt.close();
				disconnect();
				return false;
			}
		} catch (SQLException e) {
			throw new DBManagerException("Error registrarUsuario DBManager, o no existe usuario", e);
			// MUCHO TEXTO, igual hay que quitar la "e" :v
		}
	}

	public static boolean esAdmin(String nombre_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select admin from usuario where nombre_usuario = '" + nombre_usuario + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.getInt("admin") == 1) {
				System.out.println(sql);
				System.out.println("esAdmin true VA");
				rs.close();
				stmt.close();
				disconnect();
				return true;
			} else if (rs.getInt("admin") == 0) {
				System.out.println(sql);
				System.out.println("esAdmin false VA");
				rs.close();
				stmt.close();
				disconnect();
				return false;
			}
		} catch (Exception e) {
			throw new DBManagerException("Error esAdmin DBManager", e);
		}
		return false;
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		esAdmin("a");
	}
}
