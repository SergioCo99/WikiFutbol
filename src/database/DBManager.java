package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

import clases.Equipo;

public class DBManager {

	static Connection conn;
	static Statement stmt = null;

	public static void connect() throws DBManagerException {
		try {
			Properties prop = utils.PropertiesMetodos.loadPropertiesFile();
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

	public static void registrarUsuario(String nombre_usuario, String correo_usuario, String contrasena_usuario,
			String/* ¿es String? */ fechaNac_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "insert into usuario(nombre_usuario, correo_usuario, contrasena_usuario, fechaNac_usuario) values('"
					+ nombre_usuario + "','" + correo_usuario + "','" + contrasena_usuario + "','" + fechaNac_usuario
					+ "')";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error registrarUsuario DBManager", e);
		}
	}

	public static boolean login(String correo_usuario, String contrasena_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select * from usuario where correo_usuario = '" + correo_usuario
					+ "' and contrasena_usuario = '" + contrasena_usuario + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			String dato = rs.getString("contrasena_usuario");
			if (dato.equals(contrasena_usuario)) {
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
			// MUCHO TEXTO?, igual hay que quitar la "e" :v
			throw new DBManagerException("Error login DBManager, o no coincide contraseña", e);
		}
	}

	public static boolean esAdmin(String correo_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select admin_usuario from usuario where correo_usuario = '" + correo_usuario + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			if (rs.getInt("admin_usuario") == 1) {
				rs.close();
				stmt.close();
				disconnect();
				return true;
			} else if (rs.getInt("admin_usuario") == 0) {
				rs.close();
				stmt.close();
				disconnect();
				return false;
			}
		} catch (SQLException e) {
			// hay k plantearse quitar este "error"
			throw new DBManagerException("Error esAdmin DBManager, o no es admin", e);
		}
		return false;
	}

	public static void cambiarAdmin(String correo_usuario, int admin_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "update usuario set admin_usuario = '" + admin_usuario + "' where correo_usuario = '"
					+ correo_usuario + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error cambiarAdmin DBManager", e);
		}
	}

	public static void eliminarUsuario(String correo_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "delete from usuario where correo_usuario = '" + correo_usuario + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error eliminarUsuario DBManager", e);
		}
	}

	public static void cambiarContrasena(String correo_usuario, String contrasena_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "update usuario set contrasena_usuario = '" + contrasena_usuario + "' where correo_usuario = '"
					+ correo_usuario + "';";
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
			rs.close();
			stmt.close();
			disconnect();
			return arr;
		} catch (SQLException e) {
			throw new DBManagerException("Error verTablas DBManager", e);
		}
	}

	public static ArrayList<String> todosLosCorreos() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select correo_usuario from usuario";
			ResultSet rs = stmt.executeQuery(sql);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("correo_usuario"));
			}
			Collections.sort(arr, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s1.compareToIgnoreCase(s2);
				}
			});
			System.out.println(arr);
			rs.close();
			stmt.close();
			disconnect();
			return arr;
		} catch (SQLException e) {
			throw new DBManagerException("Error todosLosCorreos DBManager", e);
		}
	}

	public static void registrarFeedback(String correo_usuario, String valoracion_feedback,
			String recomendacion_feedback, String opinion_feedback) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "select id_usuario from usuario where correo_usuario = '" + correo_usuario + "'";
			ResultSet rs = stmt.executeQuery(sql1);
			rs.next();

			String id = rs.getString("id_usuario");

			String sql2 = "insert into feedback(usuario_feedback, valoracion_feedback, recomendacion_feedback, opinion_feedback) values('"
					+ id + "','" + valoracion_feedback + "','" + recomendacion_feedback + "','" + opinion_feedback
					+ "')";
			stmt.executeUpdate(sql2);
			System.out.println(sql2);
			stmt.close();
			rs.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error registrarFeedback DBManager", e);
		}
	}

	public static ArrayList<String> getJugadoresPorPosicion(String posicion_jugador) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select nombre_jugador from jugador where posicion_jugador = '" + posicion_jugador + "';";
			ResultSet rs = stmt.executeQuery(sql);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("nombre_jugador"));
			}
			Collections.sort(arr, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s1.compareToIgnoreCase(s2);
				}
			});
			rs.close();
			stmt.close();
			disconnect();
			return arr;
		} catch (SQLException e) {
			throw new DBManagerException("Error getJugadoresPorPosicion DBManager", e);
		}
	}

	public static Integer getIdUsuario(String correo_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "select id_usuario from usuario where correo_usuario = '" + correo_usuario + "'";
			ResultSet rs = stmt.executeQuery(sql1);
			rs.next();

			int id = rs.getInt("id_usuario");

			rs.close();
			stmt.close();
			disconnect();

			System.out.println("getIdUsuario: " + id);
			return id;
		} catch (SQLException e) {
			throw new DBManagerException("Error getIdUsuario DBManager", e);
		}
	}

	public static Integer getIdJugador(String nombre_jugador) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "select id_jugador from jugador where nombre_jugador = '" + nombre_jugador + "'";
			ResultSet rs = stmt.executeQuery(sql1);
			rs.next();

			int id = rs.getInt("id_jugador");

			rs.close();
			stmt.close();
			disconnect();

			System.out.println("getIdJugador: " + id);

			return id;
		} catch (SQLException e) {
			throw new DBManagerException("Error getIdJugador DBManager", e);
		}
	}

	public static void votar(int usuario_usuarioVotacion, int delanteroVotado_usuarioVotacion,
			int centrocampistaVotado_usuarioVotacion, int defensaVotado_usuarioVotacion,
			int porteroVotado_usuarioVotacion) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "select count(usuario_usuarioVotacion) from usuarioVotacion where usuario_usuarioVotacion = '"
					+ usuario_usuarioVotacion + "';";
			ResultSet rs = stmt.executeQuery(sql1);
			System.out.println("VOTAR: " + sql1);
			rs.next();

			System.out.println("votar COUNT: " + rs.getInt("count(usuario_usuarioVotacion)"));

			if (rs.getInt("count(usuario_usuarioVotacion)") == 0) {

				String sql2 = "insert into usuariovotacion(usuario_usuarioVotacion, delanteroVotado_usuarioVotacion, "
						+ "centrocampistaVotado_usuarioVotacion, defensaVotado_usuarioVotacion, porteroVotado_usuarioVotacion)"
						+ " values(" + usuario_usuarioVotacion + "," + delanteroVotado_usuarioVotacion + ","
						+ centrocampistaVotado_usuarioVotacion + "," + defensaVotado_usuarioVotacion + ","
						+ porteroVotado_usuarioVotacion + ")";

				System.out.println("VOTAR 0 : " + sql2);
				stmt.executeUpdate(sql2);
			} else if (rs.getInt("count(usuario_usuarioVotacion)") != 0) {
				String sql2 = "update usuariovotacion set delanteroVotado_usuarioVotacion = '"
						+ delanteroVotado_usuarioVotacion + "', centrocampistaVotado_usuarioVotacion = '"
						+ centrocampistaVotado_usuarioVotacion + "', defensaVotado_usuarioVotacion = '"
						+ defensaVotado_usuarioVotacion + "', porteroVotado_usuarioVotacion = '"
						+ porteroVotado_usuarioVotacion + "' " + "where usuario_usuarioVotacion = '"
						+ usuario_usuarioVotacion + "'";

				System.out.println("VOTAR 1 : " + sql2);
				stmt.executeUpdate(sql2);
			}

			rs.close();
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error votar DBManager", e);
		}
	}

	// ???
	public static void cambiarDatos(String consulta) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = consulta;
			stmt.executeUpdate(sql);
			System.out.println(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error cambiarDatos DBManager", e);
		}
	} // ???

	// ???
	public ArrayList<Equipo> getEquipos() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Equipo> array = new ArrayList<Equipo>();
			ResultSet rs = stmt.executeQuery("select * from club");
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
		votar(2, 1, 3, 5, 8);
	}
}
