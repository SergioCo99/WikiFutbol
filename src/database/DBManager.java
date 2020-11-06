package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	private static Connection conn;
	private static Statement stmt = null;
	private static PreparedStatement preparedstmt = null;

	/**
	 * Método para conectar con la BD
	 * @return Devuelve la conexión con la BD
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static Connection connect() throws DBManagerException {
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
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error connect DBManager", e);
		}
		return conn;
	}

	/**
	 * Método para desconectar con la BD
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void disconnect() throws DBManagerException {
		try {
			conn.close();
			System.out.println("DESCONEXION");
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error disconnect DBManager", e);
		}
	}

	/**
	 * Método para comprobar si un correo ya esta en la BD o no
	 * 
	 * @param correo_usuario Correo a comprobar su existencia
	 * @return Devuelve true en caso de existir el correo y false en caso de no existir
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static boolean existeCorreo(String correo_usuario) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select correo_usuario from usuario where correo_usuario= ? ";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, correo_usuario);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				String mail = rs.getString("correo_usuario");
				if (mail.equals(correo_usuario)) {
					return true;
				} else if (!mail.equals(correo_usuario)) {
					return false;
				}
			}
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error existeCorreo DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
		return false;
	}

	/**
	 * Método para registrar un usuario en la base de datos
	 * 
	 * @param nombre_usuario Nombre con el que el usuario se quiere registrar
	 * @param correo_usuario Correo con el que el usuario se quiere registrar
	 * @param contrasena_usuario Contrasena que el usuario se quiere registrar
	 * @param fechaNac_usuario Fecha de nacimiento del usuario
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void registrarUsuario(String nombre_usuario, String correo_usuario, String contrasena_usuario,
			String fechaNac_usuario) throws DBManagerException {
		connect();
		try {
			String sql = "insert into usuario(nombre_usuario, correo_usuario, contrasena_usuario, fechaNac_usuario) values(?,?,?,?)";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, nombre_usuario);
			preparedstmt.setString(2, correo_usuario);
			preparedstmt.setString(3, contrasena_usuario);
			preparedstmt.setString(4, fechaNac_usuario);
			preparedstmt.executeUpdate();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error registrarUsuario DBManager", e);
		} finally {
			try {
				preparedstmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método para realizar un login correcto para entrar a la aplicación
	 * 
	 * @param correo_usuario Correo del usuario
	 * @param contrasena_usuario Contrasena del usuario
	 * @return Verifica que la contrasena es correcta
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static boolean login(String correo_usuario, String contrasena_usuario) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select * from usuario where correo_usuario = ? and contrasena_usuario = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, correo_usuario);
			preparedstmt.setString(2, contrasena_usuario);
			rs = preparedstmt.executeQuery();
			rs.next();
			String dato = rs.getString("contrasena_usuario");
			if (dato.equals(contrasena_usuario)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error login DBManager, o no coincide contrasena", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método que te devuelve si un usuario es admin o no
	 * 
	 * @param correo_usuario Correo del usuario
	 * @return True en caso de ser admin. False en caso de no ser admin
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static boolean esAdmin(String correo_usuario) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select admin_usuario from usuario where correo_usuario = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, correo_usuario);
			rs = preparedstmt.executeQuery();
			rs.next();
			if (rs.getInt("admin_usuario") == 1) {
				return true;
			} else if (rs.getInt("admin_usuario") == 0) {
				return false;
			} else {
				return false;
			}
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error esAdmin DBManager, o no es admin", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Cambia la propidedad de un usuario para que sea admin
	 * 
	 * @param correo_usuario Correo del usuario
	 * @param admin_usuario 1 o 0. Dependiendo del usuario
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void cambiarAdmin(String correo_usuario, int admin_usuario) throws DBManagerException {
		if ((admin_usuario == 1) || (admin_usuario == 0)) {
			connect();
			try {
				String sql = "update usuario set admin_usuario = ? where correo_usuario = ?";
				preparedstmt = conn.prepareStatement(sql);
				preparedstmt.setInt(1, admin_usuario);
				preparedstmt.setString(2, correo_usuario);
				preparedstmt.executeUpdate();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
				throw new DBManagerException("Error cambiarAdmin DBManager", e);
			} finally {
				try {
					preparedstmt.close();
				} catch (SQLException e) {
					mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
				}
				disconnect();
			}
		}
	}

	/**
	 * Método para eliminar a los usuarios de la BD
	 * 
	 * @param correo_usuario Correo del usuario a eliminar
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void eliminarUsuario(String correo_usuario) throws DBManagerException {
		connect();
		try {
			String sql = "delete from usuario where correo_usuario = ?;";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, correo_usuario);
			preparedstmt.executeUpdate();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error eliminarUsuario DBManager", e);
		} finally {
			try {
				preparedstmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método para cambiar la contrasena de los usuarios
	 * 
	 * @param correo_usuario Correo del usuario
	 * @param contrasena_usuario Contrasena del usuario
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void cambiarContrasena(String correo_usuario, String contrasena_usuario) throws DBManagerException {
		connect();
		try {
			String sql = "update usuario set contrasena_usuario = ? where correo_usuario = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, contrasena_usuario);
			preparedstmt.setString(2, correo_usuario);
			preparedstmt.executeUpdate();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error cambiarContrasena DBManager", e);
		} finally {
			try {
				preparedstmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método que permite ver las tablas de la BD
	 * 
	 * @return Devuelve un array con el contenido
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static ArrayList<String> verTablas() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql1 = "use wikifutbolschema;";
			stmt.executeQuery(sql1);
			String sql2 = "show tables;";
			rs = stmt.executeQuery(sql2);
			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("Tables_in_wikifutbolschema"));
			}
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error verTablas DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método que permite ver todos los correos registrados
	 * 
	 * @return Devuelve un array con los correos
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static ArrayList<String> todosLosCorreos() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select correo_usuario from usuario";
			rs = stmt.executeQuery(sql);
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
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error todosLosCorreos DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Permite a los usuarios añadir Feedback en la aplicacion
	 * 
	 * @param correo_usuario Correo del usuario del feedback
	 * @param valoracion_feedback Valoracion añadida por el usuario
	 * @param recomendacion_feedback Recomendacion añadida por el usuario
	 * @param opinion_feedback Opinion escrita por el usuario
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void registrarFeedback(String correo_usuario, String valoracion_feedback,
			String recomendacion_feedback, String opinion_feedback) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql1 = "select id_usuario from usuario where correo_usuario = ?";
			preparedstmt = conn.prepareStatement(sql1);
			preparedstmt.setString(1, correo_usuario);
			rs = preparedstmt.executeQuery();
			rs.next();
			int id = rs.getInt("id_usuario");
			String sql2 = "insert into feedback(usuario_feedback, valoracion_feedback, recomendacion_feedback, opinion_feedback) values(?,?,?,?)";
			preparedstmt = conn.prepareStatement(sql2);
			preparedstmt.setInt(1, id);
			preparedstmt.setString(2, valoracion_feedback);
			preparedstmt.setString(3, recomendacion_feedback);
			preparedstmt.setString(4, opinion_feedback);
			preparedstmt.executeUpdate();
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error registrarFeedback DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Devuelve una lista de los jugadores dependiendo de su posicion
	 * 
	 * @param posicion_jugador Posicion en la que juega el futbolista
	 * @return Array con los futbolistas de dicha posicion
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static ArrayList<String> getJugadoresPorPosicion(String posicion_jugador) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select nombre_jugador from jugador where posicion_jugador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, posicion_jugador);
			rs = preparedstmt.executeQuery();
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
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getJugadoresPorPosicion DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método que devuelve el Id del usuario
	 * 
	 * @param correo_usuario Correo del usuario
	 * @return Id del usuario
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static int getIdUsuario(String correo_usuario) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select id_usuario from usuario where correo_usuario = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, correo_usuario);
			rs = preparedstmt.executeQuery();
			rs.next();
			int id = rs.getInt("id_usuario");
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getIdUsuario DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Método que devuelve el id del jugador
	 * 
	 * @param nombre_jugador Nombre del jugador
	 * @return Id del jugador
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static int getIdJugador(String nombre_jugador) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select id_jugador from jugador where nombre_jugador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, nombre_jugador);
			rs = preparedstmt.executeQuery();
			rs.next();
			int id = rs.getInt("id_jugador");
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getIdJugador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Sirve para que los usuarios puedan realizar la votacion a los jugadores
	 * 
	 * @param usuario_usuarioVotacion Usuario que realiza la votacion
	 * @param delanteroVotado_usuarioVotacion Delantero votado por el usuario
	 * @param centrocampistaVotado_usuarioVotacion Centrocampista votado por el usuario
	 * @param defensaVotado_usuarioVotacion Defensa votado por el usuario
	 * @param porteroVotado_usuarioVotacion Portero votado por el usuario
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void votar(int usuario_usuarioVotacion, int delanteroVotado_usuarioVotacion,
			int centrocampistaVotado_usuarioVotacion, int defensaVotado_usuarioVotacion,
			int porteroVotado_usuarioVotacion) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql1 = "select count(usuario_usuarioVotacion) from usuariovotacion where usuario_usuarioVotacion = ?";
			preparedstmt = conn.prepareStatement(sql1);
			preparedstmt.setInt(1, usuario_usuarioVotacion);
			rs = preparedstmt.executeQuery();
			rs.next();
			if (rs.getInt("count(usuario_usuarioVotacion)") == 0) {
				String sql2 = "insert into usuariovotacion(usuario_usuarioVotacion, delanteroVotado_usuarioVotacion, "
						+ "centrocampistaVotado_usuarioVotacion, defensaVotado_usuarioVotacion, porteroVotado_usuarioVotacion)"
						+ " values(?,?,?,?,?)";
				preparedstmt = conn.prepareStatement(sql2);
				preparedstmt.setInt(1, usuario_usuarioVotacion);
				preparedstmt.setInt(2, delanteroVotado_usuarioVotacion);
				preparedstmt.setInt(3, centrocampistaVotado_usuarioVotacion);
				preparedstmt.setInt(4, defensaVotado_usuarioVotacion);
				preparedstmt.setInt(5, porteroVotado_usuarioVotacion);
				preparedstmt.executeUpdate();
			} else if (rs.getInt("count(usuario_usuarioVotacion)") != 0) {
				String sql2 = "update usuariovotacion set delanteroVotado_usuarioVotacion = ?, "
						+ "centrocampistaVotado_usuarioVotacion = ?, " + "defensaVotado_usuarioVotacion = ?, "
						+ "porteroVotado_usuarioVotacion = ? " + "where usuario_usuarioVotacion = ?";
				preparedstmt = conn.prepareStatement(sql2);
				preparedstmt.setInt(1, delanteroVotado_usuarioVotacion);
				preparedstmt.setInt(2, centrocampistaVotado_usuarioVotacion);
				preparedstmt.setInt(3, defensaVotado_usuarioVotacion);
				preparedstmt.setInt(4, porteroVotado_usuarioVotacion);
				preparedstmt.setInt(5, usuario_usuarioVotacion);
				preparedstmt.executeUpdate();
			}
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error votar DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Cuenta los jugadores existentes en la tabla "jugador" de la BD
	 * 
	 * @return Devuelve el numero con la cantidad de jugadores
	 * @throws DBManagerException En caso de existir algun problema
	 */
	private static int contarJugadores() throws DBManagerException {
		ResultSet rs = null;
		try {
			String sql = "select count(id_jugador) from jugador";
			rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("count(id_jugador)");
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error contarJugadores DBManager", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
		}
	}

	
	/** Metodo que cuenta los votos que recive cada jugador 
	 * @param i - Número de votos de un jugador 
	 * @param jugadorVotado_usuarioVotacion - Jugador el cual es votado por un usuario
	 * @return Devuelve todo slos votos recividos
	 * @throws DBManagerException
	 */
	private static int contarVotosPorJugador(int i, String jugadorVotado_usuarioVotacion) throws DBManagerException {
		ResultSet rs = null;
		try {
			String sql = "select count(" + jugadorVotado_usuarioVotacion + ") from usuariovotacion where "
					+ jugadorVotado_usuarioVotacion + " = " + i;
			rs = stmt.executeQuery(sql);
			rs.next();
			int v = rs.getInt("count(" + jugadorVotado_usuarioVotacion + ")");
			return v;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error contarVotosPorJugador DBManager", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
		}
	}

	/**
	 * Método para actualizar los votos que reciben los jugadores, dependiendo de su posicion
	 * 
	 * @throws DBManagerException En caso de existir algun problema
	 */
	public static void actualizarVotos() throws DBManagerException {
		System.out.println("Inicio actualizarVotos, puede tardar un rato...");
		connect();
		try {
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
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error actualizarVotos DBManager", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
			System.out.println("Fin de actualizarVotos.");
		}
	}
	// HASTA AQUI CONTAR VOTOS

	// CREAR TEAM OF THE YEAR
	/**
	 * Cuenta los jugadores que estan el el Team Of The Year
	 * 
	 * @param n Id
	 * @return Numero de jugadores en la tabla teamoftheyear
	 * @throws DBManagerException En caso de existir algun problema
	 */
	private static int countTOFT(int n) throws DBManagerException {
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select count(id_teamoftheyear) from teamoftheyear where id_TeamOfTheYear = '" + n + "';";
			rs = stmt.executeQuery(sql);
			rs.next();
			n = rs.getInt("count(id_teamoftheyear)");
			return n;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error contarTOFT DBManager", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
		}
	}


	
	/**  Metodo para recivir los jugadores mas votados.
	 * @param posicion - Posicion del jugador.
	 * @param limit - 
	 * @param i - Indica si el jugador es votado o no.
	 * @return Devuelve el id del jugador mas votado
	 * @throws DBManagerException
	 */
	private static int getMasVotados(String posicion, int limit, int i) throws DBManagerException {
		ResultSet rs = null;
		try {
			int id = 0;
			String sql1 = "select * from jugador where posicion_jugador = '" + posicion
					+ "' order by voto_jugador desc, goles_jugador desc limit " + limit;
			rs = stmt.executeQuery(sql1);
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
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getMasVotados DBManager", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
		}
	}

	/** Metodo que recive los juagadores mas votados para hacer un equipo
	 * @return arr - Devuelve un arraylist del id de los tres delantero, 
	 * los tres centrocampistas, los tres defensas y el portero mas votado.
	 * @throws DBManagerException
	 */
	public static ArrayList<Integer> toft() throws DBManagerException {
		connect();
		try {
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
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error setTOFT DBManager", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos da el nombre de los jugadores TOFT
	 *
	 * @return Nos devuelve el nombre del jugador
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<String> toftNombres() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select jugador_TeamOfTheYear, nombre_jugador from jugador, "
					+ "teamoftheyear where id_jugador = jugador_TeamOfTheYear " + "order by id_TeamOfTheYear";
			rs = stmt.executeQuery(sql);
			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("nombre_jugador"));
			}
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error todosLosCorreos DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}
	// HASTA AQUI CREAR TEAM OF THE YEAR

	// getClasesBasicas
	/**
	 * Este metodo recopila todas las Ciudades guardadas
	 *
	 * @return Nos devulve las ciudades existentes en la base de datos
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<Ciudad> getCiudades() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Ciudad> array = new ArrayList<Ciudad>();
			rs = stmt.executeQuery(
					"select id_ciudad, nombre_ciudad, nombre_pais from ciudad, pais where pais_ciudad = id_pais");
			while (rs.next()) {
				Ciudad ciudad = new Ciudad(rs.getInt(1), rs.getString(2), rs.getString(3));
				array.add(ciudad);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getCiudades DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila todos los Clubes guardados en la base de datos
	 *
	 * @return Devuelve el conjunto de libros en la BD en formato ArrayList
	 * @throws DBManagerException En caso de existir algun problema de acceso a la
	 *                            BD
	 */
	public static ArrayList<Club> getClubes() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Club> array = new ArrayList<Club>();
			rs = stmt.executeQuery(
					"select id_club, nombre_club, nombre_ciudad, nombre_estadio, anoCreacion_club, palmares_club, nombre_entrenador from club, ciudad, estadio, entrenador where ciudad_club = id_ciudad and estadio_club = id_estadio and entrenador_club = id_entrenador ");
			while (rs.next()) {
				Club club = new Club(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getString(7));
				array.add(club);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getClubes DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	// Metodos entrenador
	/**
	 * Este metodo recopila todos los entrenadores guardados en la base de datos
	 *
	 * @return Devuelve el conjunto de Entrenadores en la BD en formato arrayList
	 * @throws DBManagerException En caso de existir algun problema de acceso a la
	 *                            BD
	 */
	public static ArrayList<Entrenador> getEntrenadores() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Entrenador> array = new ArrayList<Entrenador>();
			rs = stmt.executeQuery(
					"select id_entrenador, nombre_entrenador, fechaNac_entrenador, nombre_club, nombre_ciudad, formacion_entrenador, mentalidad_entrenador from entrenador, club, ciudad where club_entrenador = id_club and ciudad_entrenador = id_ciudad");
			while (rs.next()) {
				Entrenador entrenador = new Entrenador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), Mentalidad.valueOf(rs.getString(7)));
				array.add(entrenador);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getEntrenadores DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	// Metodos que se usa en VentanaEntrenador
	/**
	 * Este metodo nos ofrece el nombre de un entrenador
	 *
	 * @param Entrenador El entrenador del que queremos saber el nombre
	 * @param BD         La BD a utilizar
	 * @return Nos devuelve el nombre del entrenador
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static String nombreEntrenador(String Entrenador, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String nombre = "";
			String sql = "SELECT nombre_entrenador FROM entrenador WHERE nombre_entrenador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Entrenador);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				nombre = rs.getString("nombre_entrenador");
			}
			return nombre;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error nombreEntrenador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila la fecha de nacimiento del entrenador
	 *
	 * @param Entrenador Nombre del entrenador del que se desa el anyo de nacimiento
	 * @param BD         Nombre de la base de datos a utilizar
	 * @return Devuelve la fecha de nacimiento del entrenador
	 * @throws DBManagerException En caso de existir algun problema de acceso a la
	 *                            BD
	 */
	public static String fechaNacimiento(String Entrenador, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String fechaNacimiento = "";
			String sql = "SELECT fechaNac_entrenador FROM entrenador WHERE nombre_entrenador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Entrenador);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				fechaNacimiento = rs.getString("fechaNac_entrenador");
			}
			return fechaNacimiento;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error fechaNacimiento DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos ofrece el club el cual pertenece un entrenador
	 *
	 * @param Entrenador Nombre del entrenador del que queremos saber el club
	 * @param BD         Nombre de la BD a usar
	 * @return Devuelve el nombre del equipo del entrenador
	 * @throws DBManagerException En caso de existir algun problema de acceso a la
	 *                            BD
	 */
	public static String clubEntrenador(String Entrenador, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String clubEntrenador = "";
			String sql = "select nombre_club from club, entrenador where entrenador_club = id_entrenador and nombre_entrenador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Entrenador);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				clubEntrenador = rs.getString("nombre_club");
			}
			return clubEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error clubEntrenador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos da la ciudad del Entrenador
	 *
	 * @param Entrenador Nombre del entrenador del que queremos saber la ciudad
	 * @param BD         Nombre de la BD a usar
	 * @return Devuelve el nombre de la ciudad del entrenador
	 * @throws DBManagerException En caso de existir algun problema de acceso a la
	 *                            BD
	 */
	public static String ciudadEntrenador(String Entrenador, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String ciudadEntrenador = "";
			String sql = "select nombre_ciudad from ciudad, entrenador where id_ciudad = ciudad_entrenador and nombre_entrenador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Entrenador);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				ciudadEntrenador = rs.getString("nombre_ciudad");
			}
			return ciudadEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error ciudadEntrenador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos da la formacion mas frecuente que usa el entrenador
	 *
	 * @param Entrenador El entrenador del que queremos saber la formacion
	 * @param BD         El nombre de la BD a usar
	 * @return Devuelve la formacion
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static String formacionEntrenador(String Entrenador, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String formacionEntrenador = "";
			String sql = "SELECT formacion_entrenador FROM entrenador WHERE nombre_entrenador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Entrenador);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				formacionEntrenador = rs.getString("formacion_entrenador");
			}
			return formacionEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error formacionEntrenador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos ofrece la mentalidad con la que juega el entrenador, pueden
	 * ser de distintos tipos.
	 *
	 * @param Entrenador El entrenador del que queremos saber la mentalidad
	 * @param BD         Nombre de la BD
	 * @return Devuelve la mentalidad del entrenador
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static String mentalidadEntrenador(String Entrenador, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String mentalidadEntrenador = "";
			String sql = "SELECT mentalidad_entrenador FROM entrenador WHERE nombre_entrenador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Entrenador);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				mentalidadEntrenador = rs.getString("mentalidad_entrenador");
			}
			return mentalidadEntrenador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error mentalidadEntrenador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila todos los estadios existentes
	 *
	 * @return Devuelve el conjunto de estadios que tenemos en la BD
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<Estadio> getEstadios() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			connect();
			stmt = conn.createStatement();
			ArrayList<Estadio> array = new ArrayList<Estadio>();
			rs = stmt.executeQuery(
					"select id_estadio, nombre_estadio, aforo_estadio, anoCreacion_estadio, nombre_ciudad from estadio, ciudad where ciudad_estadio = id_ciudad");
			while (rs.next()) {
				Estadio estadio = new Estadio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5));
				array.add(estadio);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getEstadios DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	// metodos para VentanaEstadio
	/**
	 * Este metodo nos devuelve el nombre de un estadio en concreto
	 *
	 * @param Estadio El estadio del que queremos saber el nombre
	 * @param BD      El nombre de la BD a utilizar
	 * @return Devuelve el nombre del estadio
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static String nombreEstadio(String Estadio, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String nombre = "";
			String sql = "SELECT nombre_estadio FROM estadio WHERE nombre_estadio = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Estadio);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				nombre = rs.getString("nombre_estadio");
			}
			return nombre;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error nombreEntrenador DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo sirve para recibir el aforo que tiene un estadio en concreto
	 *
	 * @param Estadio El estadio del que queremos saber su aforo
	 * @param BD      Nombre de la BD a usar
	 * @return Nos devuelve el aforo del estadio
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static int aforoEstadio(String Estadio, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			int aforo = 0;
			String sql = "SELECT aforo_estadio FROM estadio WHERE nombre_estadio = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Estadio);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				aforo = rs.getInt("aforo_estadio");
			}
			return aforo;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error aforoEstadio DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo sirve para recibir el anyo de construccion de un estadio
	 *
	 * @param Estadio El estadio del que queremos saber el anyo de construccion
	 * @param BD      La BD a usar
	 * @return Nos devuelve el aforo del estadio
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static int anyoEstadio(String Estadio, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			int anyo = 0;
			String sql = "SELECT anoCreacion_estadio FROM estadio WHERE nombre_estadio = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Estadio);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				anyo = rs.getInt("anoCreacion_estadio");
			}
			return anyo;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error anyoEstadio DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo sirve para saber el nombre de la ciudad en la que se encuentra un
	 * estadio
	 *
	 * @param Estadio Nombre del estadio del que queremos saber la ciudad
	 * @param BD      Nombre de la BD a utilizar
	 * @return Devuelve el nombre de la ciudad en la que se encuentra el estadio
	 * @throws DBManagerException
	 */
	public static String ciudadEstadio(String Estadio, String BD) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String ciudadEstadio = "";
			String sql = "select nombre_ciudad from ciudad, estadio where id_ciudad = ciudad_estadio and nombre_estadio = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, Estadio);
			rs = preparedstmt.executeQuery();
			while (rs.next()) {
				ciudadEstadio = rs.getString("nombre_ciudad");
			}
			return ciudadEstadio;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error ciudadEstadio DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila todos los "Feedbacks" existentes
	 *
	 * @return Devuelve el conjunto de "Feedbacks" de nuestra base de datos
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<Feedback> getFeedbacks() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Feedback> array = new ArrayList<Feedback>();
			rs = stmt.executeQuery(
					"select id_feedback, correo_usuario, valoracion_feedback, recomendacion_feedback, opinion_feedback from feedback, usuario where usuario_feedback = id_usuario");
			while (rs.next()) {
				Feedback feedback = new Feedback(rs.getInt(1), rs.getString(2), rs.getInt(3),
						Recomendacion.valueOf(rs.getString(4)), rs.getString(5));
				array.add(feedback);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getFeedbacks DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila todos los jugadores guardados
	 *
	 * @return Devuelve el conjunto de jugadores existentes en nuestra base de datos
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<Jugador> getJugadores() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Jugador> array = new ArrayList<Jugador>();
			rs = stmt.executeQuery(
					"select id_jugador, nombre_jugador, fechaNac_jugador, nombre_club, nombre_ciudad, posicion_jugador, dorsal_jugador, goles_jugador, altura_jugador, peso_jugador, pieFav_jugador, valoracion_jugador, descripcion_jugador, voto_jugador from jugador, club, ciudad where club_jugador = id_club and ciudad_jugador = id_ciudad");
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), Posicion.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9),
						rs.getInt(10), PieFav.valueOf(rs.getString(11)), rs.getInt(12), rs.getString(13),
						rs.getInt(14));
				array.add(jugador);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getJugadores DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Nos permite a los jugadores partiendo del nombre del jugador como "String"
	 * 
	 * @param nombre_jugador Nombre del jugador en String
	 * @return Nos devuelve al Jugador
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static Jugador getJugadorBd(String nombre_jugador) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select id_jugador, nombre_jugador, fechaNac_jugador, nombre_club, nombre_ciudad, posicion_jugador,"
					+ " dorsal_jugador, goles_jugador, altura_jugador, peso_jugador, pieFav_jugador, valoracion_jugador,"
					+ " descripcion_jugador, voto_jugador from jugador, club, ciudad "
					+ "where club_jugador = id_club and ciudad_jugador = id_ciudad and nombre_jugador = ?";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, nombre_jugador);
			rs = preparedstmt.executeQuery();
			rs.next();
			Jugador jugador = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), Posicion.valueOf(rs.getString(6)), rs.getInt(7), rs.getInt(8), rs.getInt(9),
					rs.getInt(10), PieFav.valueOf(rs.getString(11)), rs.getInt(12), rs.getString(13), rs.getInt(14));

			return jugador;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getJugadorBd DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila todos los paises existentes
	 *
	 * @return Devuelve el conjunto de paises que se encuentran en la base de datos
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<Pais> getPaises() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Pais> array = new ArrayList<Pais>();
			rs = stmt.executeQuery("select id_pais, nombre_pais from pais");
			while (rs.next()) {
				Pais pais = new Pais(rs.getInt(1), rs.getString(2));
				array.add(pais);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getPaises DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este método devuelve un Array con el Team of The Year
	 * 
	 * @return Array con el Team of The Year
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<TeamOfTheYear_view> getTeamOfTheYear_view() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<TeamOfTheYear_view> array = new ArrayList<TeamOfTheYear_view>();
			rs = stmt.executeQuery("select * from teamoftheyear_view");
			while (rs.next()) {
				TeamOfTheYear_view toft_v = new TeamOfTheYear_view(rs.getInt(1), rs.getInt(2), rs.getString(3),
						TeamOfTheYear_view.Posicion.valueOf(rs.getString(4)), rs.getInt(5), rs.getInt(6));
				array.add(toft_v);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getTeamOfTheYear_view DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos da los jugadores que pertenecen al Team Of The Year
	 *
	 * @return Nos devuelve los jugadores pertenecientes al Team Of The Year
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<TeamOfTheYear> getTeamOfTheYear() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<TeamOfTheYear> array = new ArrayList<TeamOfTheYear>();
			rs = stmt.executeQuery(
					"select id_TeamOfTheYear, nombre_jugador from teamoftheyear, jugador where jugador_TeamOfTheYear = id_jugador order by id_TeamOfTheYear asc");
			while (rs.next()) {
				TeamOfTheYear toft = new TeamOfTheYear(rs.getInt(1), rs.getString(2));
				array.add(toft);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getTeamOfTheYear DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo recopila los usuarios existentes
	 *
	 * @return Devuelve el conjunto de usuarios de la base de datos
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static ArrayList<Usuario> getUsuarios() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<Usuario> array = new ArrayList<Usuario>();
			rs = stmt.executeQuery(
					"select id_usuario, nombre_usuario, correo_usuario, contrasena_usuario, admin_usuario, fechaNac_usuario from usuario;");
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6));
				array.add(usuario);
			}
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getUsuarios DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/** Metodo que recoge el id y el correo del usuario que ha hecho la votacion 
	 * @return Devuelve un array de los usuarios que han votado
	 * @throws DBManagerException
	 */
	public static ArrayList<UsuarioVotacion> getUsuarioVotaciones() throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			ArrayList<UsuarioVotacion> array = new ArrayList<UsuarioVotacion>();
			rs = stmt.executeQuery("select id_usuarioVotacion, correo_usuario, "
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
			return array;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getUsuarioVotaciones DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}
	// HASTA AQUI getClasesBasicas

	// CAMBIAR DATOS
	/** Metodo para ver las columnas de las tablas 
	 * @param tabla - El nombre de la tabla
	 * @return arr - Array de los nombres de las columnas
	 * @throws DBManagerException
	 */
	public static ArrayList<String> verColumnas(String tabla) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql1 = "select column_name from INFORMATION_SCHEMA.columns where table_name = '" + tabla
					+ "' order by ordinal_position";
			rs = stmt.executeQuery(sql1);

			ArrayList<String> arr = new ArrayList<String>();
			while (rs.next()) {
				arr.add(rs.getString("column_name"));
			}
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error verColumnas DBManager", e);
		} finally {
			try {
				// stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			// disconnect();
		}
	}

	public static Object[][] data(String tabla) throws DBManagerException {
		connect();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			stmt = conn.createStatement();
			// sacar datos
			String sql1 = "select * from " + tabla + ";";
			rs1 = stmt.executeQuery(sql1);
			// numero de columnas
			int ncolumns = verColumnas(tabla).size();
			// numero de filas
			String sql2 = "select count(*) from " + tabla + ";";
			rs2 = stmt.executeQuery(sql2);
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
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncolumns; j++) {
					// System.out.println(ss[i][j]);
				}
			}
			return ss;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error data DBManager", e);
		} finally {
			try {
				stmt.close();
				rs1.close();
				rs2.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/**
	 * Este metodo nos permite cambiar los datos de la BD
	 *
	 * @param consulta La consulta SQL con la que modificaremos la BD
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	// ???
	public static void cambiarDatos(String consulta) throws DBManagerException {
		connect();
		try {
			stmt = conn.createStatement();
			String sql = consulta;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error cambiarDatos DBManager", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	} // ???

	/**
	 * Este metodo nos permite cambiar los datos de la BD
	 *
	 * @param tabla   La tabla de la BD en la que queremos realizar la modificacion
	 * @param columna La columna de la tabla anteriormente definida
	 * @param valor   El nuevo valor que queremos que obtenga ese campo en concreto
	 * @param id
	 * @throws DBManagerException Si hay algun problema de acceso a la base de datos
	 */
	public static void cambiarDatosDesdeJTable(String tabla, String columna, Object valor, int id)
			throws DBManagerException {
		connect();
		try {
			stmt = conn.createStatement();
			String sql = "update " + tabla + " set " + columna + "='" + valor + "' where id_" + tabla + " = " + id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error CambiarDatosDesdeJTable DBManager", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	// HASTA AQUI CAMBIAR DATOS

	/**
	 * Este metodo nos ofrece los jugadores que estan en un equipo en concreto
	 *
	 * @param nombre_club Nombre del equipo del que queremos saber los jugadores
	 * @return Nos devuelve los jugadores que estan en ese equipo
	 * @throws DBManagerException
	 */
	public static ArrayList<String> getJugadoresPorEquipo(String nombre_club) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			String sql = "select nombre_jugador from jugador, club where club_jugador = id_club and nombre_club = ? order by id_jugador";
			preparedstmt = conn.prepareStatement(sql);
			preparedstmt.setString(1, nombre_club);
			rs = preparedstmt.executeQuery();
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
			return arr;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error getJugadoresPorPosicion DBManager", e);
		} finally {
			try {
				preparedstmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	/** Recoge el numero de filas de una tabla
	 * @param tabla - Tabla de la cual queremos recoger la informacion
	 * @return Devuelve el numero de filas de una tabla 
	 * @throws DBManagerException
	 */
	public static int numeroDeFilasEnUnaTabla(String tabla) throws DBManagerException {
		connect();
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			int id = 0;
			if (tabla.equals("teamoftheyear_view")) {
				String sql = "select count(id_teamoftheyear) from " + tabla;
				rs = stmt.executeQuery(sql);
				rs.next();
				id = rs.getInt("count(id_teamoftheyear)");
			} else {
				String sql = "select count(id_" + tabla + ") from " + tabla;
				rs = stmt.executeQuery(sql);
				rs.next();
				id = rs.getInt("count(id_" + tabla + ")");
			}
			return id;
		} catch (SQLException e) {
			mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
			throw new DBManagerException("Error numeroDeFilasEnUnaTabla DBManager", e);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				mainPackage.MainWikiFutbol.loggerBD.log(Level.INFO, e.toString());
			}
			disconnect();
		}
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		votar(1, 1, 5, 9, 15);
		actualizarVotos();
	}
}
