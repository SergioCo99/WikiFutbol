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
			System.out.println("CONEXION");
		} catch (Exception e) {
			throw new DBManagerException("Error conexion DBManager", e);
		}
	}

	public static void disconnect() throws DBManagerException {
		try {
			conn.close();
			System.out.println("DESCONEXION");
		} catch (SQLException e) {
			throw new DBManagerException("Error desconexion DBManager", e);
		}
	}

	public static void registrarUsuario(String nombre_usuario, String correo_usuario, String contrasena_usuario,
			String/* �es String? */ fechaNac_usuario) throws DBManagerException {
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
			throw new DBManagerException("Error login DBManager, o no coincide contrase�a", e);
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
			// tambien se podria ordenar en la consulta sql, usando "order by correo_usuario
			// desc"
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

	public static int getIdUsuario(String correo_usuario) throws DBManagerException {
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
			rs.next();

			if (rs.getInt("count(usuario_usuarioVotacion)") == 0) {

				String sql2 = "insert into usuariovotacion(usuario_usuarioVotacion, delanteroVotado_usuarioVotacion, "
						+ "centrocampistaVotado_usuarioVotacion, defensaVotado_usuarioVotacion, porteroVotado_usuarioVotacion)"
						+ " values(" + usuario_usuarioVotacion + "," + delanteroVotado_usuarioVotacion + ","
						+ centrocampistaVotado_usuarioVotacion + "," + defensaVotado_usuarioVotacion + ","
						+ porteroVotado_usuarioVotacion + ")";

				stmt.executeUpdate(sql2);
			} else if (rs.getInt("count(usuario_usuarioVotacion)") != 0) {
				String sql2 = "update usuariovotacion set delanteroVotado_usuarioVotacion = '"
						+ delanteroVotado_usuarioVotacion + "', centrocampistaVotado_usuarioVotacion = '"
						+ centrocampistaVotado_usuarioVotacion + "', defensaVotado_usuarioVotacion = '"
						+ defensaVotado_usuarioVotacion + "', porteroVotado_usuarioVotacion = '"
						+ porteroVotado_usuarioVotacion + "' " + "where usuario_usuarioVotacion = '"
						+ usuario_usuarioVotacion + "'";

				stmt.executeUpdate(sql2);
			}

			rs.close();
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error votar DBManager", e);
		}
	}

	// CONTAR VOTOS
	public static int contarJugadores() throws DBManagerException {
		try {
			// connect();
			// stmt = conn.createStatement();
			String sql = "select count(id_jugador) from jugador";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			int id = rs.getInt("count(id_jugador)");

			rs.close();
			// stmt.close();
			// disconnect();
			return id;
		} catch (SQLException e) {
			throw new DBManagerException("Error contarJugadores DBManager", e);
		}
	}

	public static int contarVotosPorJugador(int i, String jugadorVotado_usuarioVotacion) throws DBManagerException {
		try {
			// connect();
			// stmt = conn.createStatement();
			String sql = "select count(" + jugadorVotado_usuarioVotacion + ") from usuarioVotacion where "
					+ jugadorVotado_usuarioVotacion + " = " + i;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			int v = rs.getInt("count(" + jugadorVotado_usuarioVotacion + ")");

			System.out.println(sql + " -> " + v);

			// rs.close();
			// stmt.close();
			// disconnect();
			return v;
		} catch (SQLException e) {
			throw new DBManagerException("Error contarVotosPorJugador DBManager", e);
		}
	}

	public static void actualizarVotos() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();

			// Actualiza los votos de los delanteros
			for (int i = 1; i < contarJugadores() + 1; i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "delanteroVotado_usuarioVotacion") + "' where id_jugador = '" + i
						+ "' and posicion_jugador = 'Delantero';";
				stmt.executeUpdate(sql);
			}

			// Actualiza los votos de los centrocampistas
			for (int i = 1; i < contarJugadores() + 1; i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "centrocampistaVotado_usuarioVotacion") + "' where id_jugador = '"
						+ i + "' and posicion_jugador = 'Centrocampista';";
				stmt.executeUpdate(sql);
			}

			// Actualiza los votos de los defensas
			for (int i = 1; i < contarJugadores() + 1; i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "defensaVotado_usuarioVotacion") + "' where id_jugador = '" + i
						+ "' and posicion_jugador = 'Defensa';";
				stmt.executeUpdate(sql);
			}

			// Actualiza los votos de los porteros
			for (int i = 1; i < contarJugadores() + 1; i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "PorteroVotado_usuarioVotacion") + "' where id_jugador = '" + i
						+ "' and posicion_jugador = 'Portero';";
				stmt.executeUpdate(sql);
			}

			stmt.close();
			disconnect();
		} catch (SQLException e) {
			throw new DBManagerException("Error actualizarVotos DBManager", e);
		}
	}
	// HASTA AQUI CONTAR VOTOS

	// CREAR TEAM OF THE YEAR
	public static int contarTOFT(int n) throws DBManagerException {
		try {
			stmt = conn.createStatement();

			String sql = "select count(id_TeamOfTheYear) from TeamOfTheYear where id_TeamOfTheYear = '" + n + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			n = rs.getInt("count(id_TeamOfTheYear)");
			System.out.println(sql + "->" + n);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public static int getMasVotados(String posicion, int limit, int i) throws DBManagerException {
		try {
			int id = 0;

			String sql1 = "select * from jugador where posicion_jugador = '" + posicion
					+ "' order by voto_jugador desc, goles_jugador desc limit " + limit;
			ResultSet rs = stmt.executeQuery(sql1);
			System.out.println(sql1);

			while (rs.next()) {
				id = rs.getInt("id_jugador");
				System.out.println("id del jugador: " + id);

				if (contarTOFT(i) == 0) {
					String sql2 = "insert into teamoftheyear values(" + i + ", " + id + ")";
					stmt.executeUpdate(sql2);
					System.out.println(sql2);
				} else if (contarTOFT(i) == 1) {
					String sql2 = "update teamoftheyear set jugador_teamoftheyear = " + id
							+ " where id_teamoftheyear = " + i + "";
					stmt.executeUpdate(sql2);
					System.out.println(sql2);
				}
			}
			System.out.println("return: " + id);
			System.out.println("==============================================================================");
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; // ??? */
	}

	public static void getTOFT() throws DBManagerException {
		ArrayList<String> posicion = new ArrayList<>();
		posicion.add("Delantero");
		posicion.add("Centrocampista");
		posicion.add("Defensa");
		posicion.add("Portero");

		try {
			connect();
			stmt = conn.createStatement();

			getMasVotados("Delantero", 1, 1);
			getMasVotados("Delantero", 2, 2);
			getMasVotados("Delantero", 3, 3);
			getMasVotados("Centrocampista", 1, 4);
			getMasVotados("Centrocampista", 2, 5);
			getMasVotados("Centrocampista", 3, 6);
			getMasVotados("Defensa", 1, 7);
			getMasVotados("Defensa", 2, 8);
			getMasVotados("Defensa", 3, 9);
			getMasVotados("Defensa", 4, 10);
			getMasVotados("Portero", 1, 11);

			/*
			 * for (int j = 1; j <= 11; j++) { for (int i = 0; i < posicion.size(); i++) {
			 * if (i == 0 && 1 <= j && j <= 3) { getMasVotados(posicion.get(i), j, j); }
			 * else if (i == 1 && 4 <= j && j <= 6) { getMasVotados(posicion.get(i), j, j);
			 * } else if (i == 2 && 7 <= j && j <= 10) { getMasVotados(posicion.get(i), j,
			 * j); } else if (i == 3 && 11 == j) { getMasVotados(posicion.get(i), j, j); } }
			 * }
			 */

			stmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// HASTA AQUI CREAR TEAM OF THE YEAR

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
		// getMasVotados("Delantero", 1, 1);
		getTOFT();
	}
}
