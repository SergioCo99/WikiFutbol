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
import java.util.logging.Level;

import clases.Ciudad;
import clases.Club;
import clases.Entrenador;
import clases.Entrenador.Mentalidad;
import clases.Estadio;
import clases.Feedback;
import clases.Feedback.Recomendacion;
import clases.Jugador;
import clases.Jugador.PieFav;
import clases.Jugador.Posicion;
import clases.Pais;
import clases.TeamOfTheYear;
import clases.TeamOfTheYear_view;
import clases.Usuario;
import clases.UsuarioVotacion;

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

			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			System.out.println("CONEXION");
		} catch (Exception e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error connect DBManager", e);
		}
	}

	public static void disconnect() throws DBManagerException {
		try {
			conn.close();
			System.out.println("DESCONEXION");
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error disconnect DBManager", e);
		}
	}

	public static boolean existeCorreo(String correo_usuario) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select correo_usuario from usuario where correo_usuario = '" + correo_usuario + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			String mail = rs.getString("correo_usuario");
			if (mail.equals(correo_usuario)) {
				return true;
			} else if (!mail.equals(correo_usuario)) {
				return false;
			}
			rs.close();
			stmt.close();
			disconnect();
			return false;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error existeCorreo DBManager", e);
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.INFO, e.toString());
			// MUCHO TEXTO?, igual hay que quitar la "e" :v (ABAJO, en el throw new
			// DBManagerException)
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			// hay k plantearse quitar este "error" (ABAJO, en el throw new
			// DBManagerException)
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
				arr.add(rs.getString("Tables_in_wikifutbolschema"));
			}
			rs.close();
			stmt.close();
			disconnect();
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getIdUsuario DBManager", e);
		}
	}

	public static int getIdJugador(String nombre_jugador) throws DBManagerException {
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
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
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error votar DBManager", e);
		}
	}

	// CONTAR VOTOS
	public static int contarJugadores() throws DBManagerException {
		try {
			String sql = "select count(id_jugador) from jugador";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			int id = rs.getInt("count(id_jugador)");

			rs.close();
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error contarJugadores DBManager", e);
		}
	}

	public static int contarVotosPorJugador(int i, String jugadorVotado_usuarioVotacion) throws DBManagerException {
		try {
			String sql = "select count(" + jugadorVotado_usuarioVotacion + ") from usuarioVotacion where "
					+ jugadorVotado_usuarioVotacion + " = " + i;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			int v = rs.getInt("count(" + jugadorVotado_usuarioVotacion + ")");

			return v;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error contarVotosPorJugador DBManager", e);
		}
	}

	public static void actualizarVotos() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();

			// Actualiza los votos de los delanteros
			for (int i = 1; i < (contarJugadores() + 1); i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "delanteroVotado_usuarioVotacion") + "' where id_jugador = '" + i
						+ "' and posicion_jugador = 'Delantero';";
				stmt.executeUpdate(sql);
			}

			// Actualiza los votos de los centrocampistas
			for (int i = 1; i < (contarJugadores() + 1); i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "centrocampistaVotado_usuarioVotacion") + "' where id_jugador = '"
						+ i + "' and posicion_jugador = 'Centrocampista';";
				stmt.executeUpdate(sql);
			}

			// Actualiza los votos de los defensas
			for (int i = 1; i < (contarJugadores() + 1); i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "defensaVotado_usuarioVotacion") + "' where id_jugador = '" + i
						+ "' and posicion_jugador = 'Defensa';";
				stmt.executeUpdate(sql);
			}

			// Actualiza los votos de los porteros
			for (int i = 1; i < (contarJugadores() + 1); i++) {
				String sql = "update jugador set voto_jugador = '"
						+ contarVotosPorJugador(i, "PorteroVotado_usuarioVotacion") + "' where id_jugador = '" + i
						+ "' and posicion_jugador = 'Portero';";
				stmt.executeUpdate(sql);
			}

			stmt.close();
			disconnect();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error actualizarVotos DBManager", e);
		}
	}
	// HASTA AQUI CONTAR VOTOS

	// CREAR TEAM OF THE YEAR
	public static int countTOFT(int n) throws DBManagerException {
		try {
			stmt = conn.createStatement();
			String sql = "select count(id_TeamOfTheYear) from TeamOfTheYear where id_TeamOfTheYear = '" + n + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			n = rs.getInt("count(id_TeamOfTheYear)");
			rs.close();
			return n;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error contarTOFT DBManager", e);
		}
	}

	public static int getMasVotados(String posicion, int limit, int i) throws DBManagerException {
		try {
			int id = 0;

			String sql1 = "select * from jugador where posicion_jugador = '" + posicion
					+ "' order by voto_jugador desc, goles_jugador desc limit " + limit;
			ResultSet rs = stmt.executeQuery(sql1);

			while (rs.next()) {
				id = rs.getInt("id_jugador");

				if (countTOFT(i) == 0) {
					String sql2 = "insert into teamoftheyear values(" + i + ", " + id + ")";
					stmt.executeUpdate(sql2);
				} else if (countTOFT(i) == 1) {
					String sql2 = "update teamoftheyear set jugador_teamoftheyear = " + id
							+ " where id_teamoftheyear = " + i + "";
					stmt.executeUpdate(sql2);
				}
			}
			rs.close();
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getMasVotados DBManager", e);
		}
	}

	public static ArrayList<Integer> toft() throws DBManagerException {
		// por ahora no hace nada, sirve como idea de poner lo de abajo mas elegante
		ArrayList<String> posicion = new ArrayList<>();
		posicion.add("Delantero");
		posicion.add("Centrocampista");
		posicion.add("Defensa");
		posicion.add("Portero");

		try {
			connect();
			stmt = conn.createStatement();

			// ademas de meter los datos en el array, ejecuta el metodo getMasVotados()
			ArrayList<Integer> arr = new ArrayList<Integer>();
			arr.add(getMasVotados("Delantero", 1, 1));
			arr.add(getMasVotados("Delantero", 2, 2));
			arr.add(getMasVotados("Delantero", 3, 3));
			arr.add(getMasVotados("Centrocampista", 1, 4));
			arr.add(getMasVotados("Centrocampista", 2, 5));
			arr.add(getMasVotados("Centrocampista", 3, 6));
			arr.add(getMasVotados("Defensa", 1, 7));
			arr.add(getMasVotados("Defensa", 2, 8));
			arr.add(getMasVotados("Defensa", 3, 9));
			arr.add(getMasVotados("Defensa", 4, 10));
			arr.add(getMasVotados("Portero", 1, 11));

			stmt.close();
			disconnect();
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error setTOFT DBManager", e);
		}
	}

	public static ArrayList<String> toftNombres() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select jugador_TeamOfTheYear, nombre_jugador from jugador, teamoftheyear where id_jugador = jugador_TeamOfTheYear order by id_TeamOfTheYear";
			ResultSet rs = stmt.executeQuery(sql);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("nombre_jugador"));
			}

			rs.close();
			stmt.close();
			disconnect();
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error todosLosCorreos DBManager", e);
		}
	}
	// HASTA AQUI CREAR TEAM OF THE YEAR

	// getClasesBasicas
	public static ArrayList<Ciudad> getCiudades() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Ciudad> array = new ArrayList<Ciudad>();
			ResultSet rs = stmt.executeQuery(
					"select id_ciudad, nombre_ciudad, nombre_pais from ciudad, pais where pais_ciudad = id_pais");
			while (rs.next()) {
				Ciudad ciudad = new Ciudad(rs.getInt(1), rs.getString(2), rs.getString(3));
				array.add(ciudad);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getCiudades DBManager", e);
		}
	}

	public static ArrayList<Club> getClubes() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Club> array = new ArrayList<Club>();
			ResultSet rs = stmt.executeQuery(
					"select id_club, nombre_club, nombre_ciudad, nombre_estadio, anoCreacion_club, palmares_club, nombre_entrenador from club, ciudad, estadio, entrenador where ciudad_club = id_ciudad and estadio_club = id_estadio and entrenador_club = id_entrenador ");
			while (rs.next()) {
				Club club = new Club(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getString(7));
				array.add(club);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getClubes DBManager", e);
		}
	}

	// Métodos entrenador
	public static ArrayList<Entrenador> getEntrenadores() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Entrenador> array = new ArrayList<Entrenador>();
			ResultSet rs = stmt.executeQuery(
					"select id_entrenador, nombre_entrenador, fechaNac_entrenador, nombre_club, nombre_ciudad, formacion_entrenador, mentalidad_entrenador from entrenador, club, ciudad where club_entrenador = id_club and ciudad_entrenador = id_ciudad");
			while (rs.next()) {
				Entrenador entrenador = new Entrenador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), Mentalidad.valueOf(rs.getString(7)));
				array.add(entrenador);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getEntrenadores DBManager", e);
		}
	}

	// Metodos que se usa en VentanaEntrenador
	public static String nombreEntrenador(String Entrenador, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String nombre = "";
			String query = "SELECT nombre_entrenador FROM entrenador WHERE nombre_entrenador = '" + Entrenador + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				nombre = RS.getString("nombre_entrenador");
			}
			return nombre;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error nombreEntrenador DBManager", e);
		}
	}

	public static String fechaNacimiento(String Entrenador, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String fechaNacimiento = "";
			String query = "SELECT fechaNac_entrenador FROM entrenador WHERE nombre_entrenador = '" + Entrenador + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				fechaNacimiento = RS.getString("fechaNac_entrenador");
			}
			return fechaNacimiento;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error fechaNacimiento DBManager", e);
		}
	}

	public static String clubEntrenador(String Entrenador, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String clubEntrenador = "";
			// String query = "SELECT club_entrenador FROM entrenador WHERE
			// nombre_entrenador = '" + Entrenador + "'";
			String query = "select nombre_club from club, entrenador where entrenador_club = id_entrenador and nombre_entrenador = '"
					+ Entrenador + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				clubEntrenador = RS.getString("nombre_club");
			}
			return clubEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error clubEntrenador DBManager", e);
		}
	}

	public static String ciudadEntrenador(String Entrenador, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String ciudadEntrenador = "";
			// String query = "SELECT ciudad_entrenador FROM entrenador WHERE
			// nombre_entrenador = '" + Entrenador + "'";
			String query = "select nombre_ciudad from ciudad, entrenador where id_ciudad = ciudad_entrenador and nombre_entrenador = '"
					+ Entrenador + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				ciudadEntrenador = RS.getString("nombre_ciudad");
			}
			return ciudadEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error ciudadEntrenador DBManager", e);
		}
	}

	public static String formacionEntrenador(String Entrenador, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String formacionEntrenador = "";
			String query = "SELECT formacion_entrenador FROM entrenador WHERE nombre_entrenador = '" + Entrenador + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				formacionEntrenador = RS.getString("formacion_entrenador");
			}
			return formacionEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error formacionEntrenador DBManager", e);
		}
	}

	public static String mentalidadEntrenador(String Entrenador, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String mentalidadEntrenador = "";
			String query = "SELECT mentalidad_entrenador FROM entrenador WHERE nombre_entrenador = '" + Entrenador
					+ "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				mentalidadEntrenador = RS.getString("mentalidad_entrenador");
			}
			return mentalidadEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error mentalidadEntrenador DBManager", e);
		}
	}

	public static ArrayList<Estadio> getEstadios() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Estadio> array = new ArrayList<Estadio>();
			ResultSet rs = stmt.executeQuery(
					"select id_estadio, nombre_estadio, aforo_estadio, anoCreacion_estadio, nombre_ciudad from estadio, ciudad where ciudad_estadio = id_ciudad");
			while (rs.next()) {
				Estadio estadio = new Estadio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5));
				array.add(estadio);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getEstadios DBManager", e);
		}
	}

	// Métodos para VentanaEstadio
	public static String nombreEstadio(String Estadio, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String nombre = "";
			String query = "SELECT nombre_estadio FROM estadio WHERE nombre_estadio = '" + Estadio + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				nombre = RS.getString("nombre_estadio");
			}
			return nombre;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error nombreEntrenador DBManager", e);
		}
	}

	public static int aforoEstadio(String Estadio, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			int aforo = 0;
			String query = "SELECT aforo_estadio FROM estadio WHERE nombre_estadio = '" + Estadio + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				aforo = RS.getInt("aforo_estadio");
			}
			return aforo;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error aforoEstadio DBManager", e);
		}
	}

	public static int anyoEstadio(String Estadio, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			int anyo = 0;
			String query = "SELECT anoCreacion_estadio FROM estadio WHERE nombre_estadio = '" + Estadio + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				anyo = RS.getInt("anoCreacion_estadio");
			}
			return anyo;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error anyoEstadio DBManager", e);
		}
	}

	public static String ciudadEstadio(String Estadio, String BD) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String ciudadEstadio = "";
			String query = "select nombre_ciudad from ciudad, estadio where id_ciudad = ciudad_estadio and nombre_estadio = '"
					+ Estadio + "'";
			ResultSet RS = stmt.executeQuery(query);
			while (RS.next()) {
				ciudadEstadio = RS.getString("nombre_ciudad");
			}
			return ciudadEstadio;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error ciudadEstadio DBManager", e);
		}
	}

	public static ArrayList<Feedback> getFeedbacks() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Feedback> array = new ArrayList<Feedback>();
			ResultSet rs = stmt.executeQuery(
					"select id_feedback, correo_usuario, valoracion_feedback, recomendacion_feedback, opinion_feedback from feedback, usuario where usuario_feedback = id_usuario");
			while (rs.next()) {
				Feedback feedback = new Feedback(rs.getInt(1), rs.getString(2),
						/* Valoracion.valueOf(rs.getString(3)) */ rs.getInt(3), Recomendacion.valueOf(rs.getString(4)),
						rs.getString(5));
				array.add(feedback);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getFeedbacks DBManager", e);
		}
	}

	public static ArrayList<Jugador> getJugadores() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Jugador> array = new ArrayList<Jugador>();
			ResultSet rs = stmt.executeQuery(
					"select id_jugador, nombre_jugador, fechaNac_jugador, nombre_club, nombre_ciudad, posicion_jugador, dorsal_jugador, goles_jugador, altura_jugador, peso_jugador, pieFav_jugador, valoracion_jugador, descripcion_jugador, voto_jugador from jugador, club, ciudad where club_jugador = id_club and ciudad_jugador = id_ciudad");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), Posicion.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						rs.getInt(10), PieFav.valueOf(rs.getString(11)), rs.getInt(12), rs.getString(13),
						rs.getInt(14));
				array.add(jugador);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getJugadores DBManager", e);
		}
	}

	public static ArrayList<Pais> getPaises() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Pais> array = new ArrayList<Pais>();
			ResultSet rs = stmt.executeQuery("select id_pais, nombre_pais from pais");
			while (rs.next()) {
				Pais pais = new Pais(rs.getInt(1), rs.getString(2));
				array.add(pais);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getPaises DBManager", e);
		}
	}

	public static ArrayList<TeamOfTheYear_view> getTeamOfTheYear_view() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<TeamOfTheYear_view> array = new ArrayList<TeamOfTheYear_view>();
			ResultSet rs = stmt.executeQuery("select * from teamoftheyear_view");
			while (rs.next()) {
				TeamOfTheYear_view toft_v = new TeamOfTheYear_view(rs.getInt(1), rs.getInt(2), rs.getString(3),
						TeamOfTheYear_view.Posicion.valueOf(rs.getString(4)), rs.getInt(5), rs.getInt(6));
				array.add(toft_v);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getTeamOfTheYear_view DBManager", e);
		}
	}

	public static ArrayList<TeamOfTheYear> getTeamOfTheYear() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<TeamOfTheYear> array = new ArrayList<TeamOfTheYear>();
			ResultSet rs = stmt.executeQuery(
					"select id_TeamOfTheYear, nombre_jugador from teamoftheyear, jugador where jugador_TeamOfTheYear = id_jugador order by id_TeamOfTheYear asc");
			while (rs.next()) {
				TeamOfTheYear toft = new TeamOfTheYear(rs.getInt(1), rs.getString(2));
				array.add(toft);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getTeamOfTheYear DBManager", e);
		}
	}

	public static ArrayList<Usuario> getUsuarios() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Usuario> array = new ArrayList<Usuario>();
			ResultSet rs = stmt.executeQuery(
					"select id_usuario, nombre_usuario, correo_usuario, contrasena_usuario, admin_usuario, fechaNac_usuario from usuario;");
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6));
				array.add(usuario);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getUsuarios DBManager", e);
		}
	}

	public static ArrayList<UsuarioVotacion> getUsuarioVotaciones() throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<UsuarioVotacion> array = new ArrayList<UsuarioVotacion>();
			ResultSet rs = stmt.executeQuery("select id_usuarioVotacion, correo_usuario, "
					+ "(select nombre_jugador from jugador where delanteroVotado_usuarioVotacion = id_jugador),"
					+ "(select nombre_jugador from jugador where centrocampistaVotado_usuarioVotacion = id_jugador),"
					+ "(select nombre_jugador from jugador where defensaVotado_usuarioVotacion = id_jugador),"
					+ "(select nombre_jugador from jugador where porteroVotado_usuarioVotacion = id_jugador)"
					+ "from usuariovotacion, usuario where usuario_usuarioVotacion = id_usuario");
			while (rs.next()) {
				UsuarioVotacion usuariovotacion = new UsuarioVotacion(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				array.add(usuariovotacion);
			}
			rs.close();
			stmt.close();
			disconnect();
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getUsuarioVotaciones DBManager", e);
		}
	}
	// HASTA AQUI getClasesBasicas

	// CAMBIAR DATOS
	public static ArrayList<String> verColumnas(String tabla) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql1 = "select column_name from INFORMATION_SCHEMA.columns where table_name = '" + tabla
					+ "' order by ordinal_position";
			ResultSet rs = stmt.executeQuery(sql1);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("column_name"));
			}
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error verColumnas DBManager", e);
		}
	}

	public static Object[][] data(String tabla) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();

			// sacar datos
			String sql1 = "select * from " + tabla + ";";
			ResultSet rs1 = stmt.executeQuery(sql1);

			// numero de columnas
			int ncolumns = verColumnas(tabla).size();

			// numero de filas
			String sql2 = "select count(*) from " + tabla + ";";
			ResultSet rs2 = stmt.executeQuery(sql2);
			rs2.next();
			int nrows = rs2.getInt("count(*)");

			// meter datos en array 2D
			ArrayList<String> as = new ArrayList<String>();
			while (rs1.next()) {
				for (int i = 1; i <= ncolumns; i++) {
					as.add(rs1.getObject(i).toString());
				}
			}

			int z = 0;
			String[][] ss = new String[nrows][ncolumns];
			for (int j = 0; j < nrows; j++) {
				for (int i = 0; i < ncolumns; i++) {
					// System.out.println("fila: " + j + ", columna: " + i + " -> " + as.get(z));
					ss[j][i] = as.get(z).toString();
					z++;
				}
			}

			rs1.close();
			rs2.close();
			stmt.close();
			disconnect();

			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncolumns; j++) {
					// System.out.println(ss[i][j]);
				}
			}
			return ss;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error data DBManager", e);
		}
	}

	// ???
	public static void cambiarDatos(String consulta) throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = consulta;
			stmt.executeUpdate(sql);
			System.out.println("database.DBManager.cambiarDatos: " + sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error cambiarDatos DBManager", e);
		}
	} // ???

	public static void CambiarDatosDesdeJTable(String tabla, String columna, Object valor, int id)
			throws DBManagerException {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "update " + tabla + " set " + columna + "='" + valor + "' where id_" + tabla + " = " + id;
			stmt.executeUpdate(sql);
			System.out.println(sql);
			stmt.close();
			disconnect();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.logger.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error CambiarDatosDesdeJTable DBManager", e);
		}
	}
	// HASTA AQUI CAMBIAR DATOS

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {

	}
}
