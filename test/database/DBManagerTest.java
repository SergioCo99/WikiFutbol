package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class DBManagerTest {

	static DBManager db = new DBManager();
	static Usuario u = new Usuario(1, "nombre usuario", "contrasena", "correo", 0, "1970-01-01");
	static Entrenador e = new Entrenador(1, "Gaizka Garitano", "1975-07-09", "Athletic Club", "Bilbao", "4-3-3",
			Mentalidad.Equilibrada);
	static Estadio es = new Estadio(1, "San Mames", 53289, 2013, "Bilbao");
	static Feedback f = new Feedback(1, u.getCorreo(), 5, Recomendacion.si, "opinion");
	static Jugador j = new Jugador(1, "Alex Berenguer", "1993-10-01", "Athletic Club", "Bilbao", Posicion.Delantero, 8,
			0, 182, 81, PieFav.Diestro, 84, "Jugador con desborde", 1);

	// private static Connection conn;
	private static Statement stmt = null;
	private static PreparedStatement preparedstmt = null;

	@BeforeClass
	public static void setUp() throws Exception {
		db = new DBManager();
		u = new Usuario(u.getId(), u.getNombre(), u.getContrasena(), u.getCorreo(), u.getAdmin(), u.getFechaNac());
		e = new Entrenador(e.getId(), e.getNombre(), e.getFechaNac(), e.getClub(), e.getCiudad(), e.getFormacion(),
				e.getMentalidad());
		es = new Estadio(es.getId(), es.getNombre(), es.getAforo(), es.getAnyoCreacion(), es.getCiudad());
		f = new Feedback(f.getId(), f.getUsuario(), f.getValoracion(), f.getRecomendacion(), f.getOpinion());
		j = new Jugador(j.getId(), j.getNombre(), j.getFechaNac(), j.getClub(), j.getCiudad(), j.getPosicion(),
				j.getDorsal(), j.getGoles(), j.getAltura(), j.getPeso(), j.getPiefav(), j.getValoracion(),
				j.getDescripcion(), j.getVoto());
	}

	/**
	 * Comprueba si se puede conectar al servidor
	 *
	 * @throws DBManagerException
	 */
	@BeforeClass
	public static void testConnect() throws DBManagerException {
		DBManager.connect();
	}

	/**
	 * Comprueba si se puede desconectar del servidor
	 *
	 * @throws DBManagerException
	 */
	@BeforeClass
	public static void testDisconnect() throws DBManagerException {
		DBManager.disconnect();
	}

	/**
	 * Comprueba si el correo insertado existe o no
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testExisteCorreo() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		assertTrue(DBManager.existeCorreo(correo_usuario));
	}

	/**
	 * Comprueba si se puede registrar un usuario nuevo
	 *
	 * @throws DBManagerException
	 */
	@Before
	public void testRegistrarUsuario() throws DBManagerException {
		String nombre_usuario = u.getNombre();
		String correo_usuario = u.getCorreo();
		String contrasena_usuario = u.getContrasena();
		String fechaNac_usuario = u.getFechaNac();

		DBManager.registrarUsuario(nombre_usuario, correo_usuario, contrasena_usuario, fechaNac_usuario);
	}

	/**
	 * Test que comprueba el correcto funcionamiento del método login
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testLogin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		String contrasena_usuario = u.getContrasena();

		assertTrue(DBManager.login(correo_usuario, contrasena_usuario));
	}

	/**
	 * Comprueba si el usuario tiene los permisos de Admin o no
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testEsAdmin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		assertFalse(DBManager.esAdmin(correo_usuario));

		String correoDeUnAdmin = "eneko.perez23@gmail.com";
		assertTrue(DBManager.esAdmin(correoDeUnAdmin));
	}

	/**
	 * Comprueba si funciona el método que nos permite cambiar a un usuario como
	 * admin o como usuario normal
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testCambiarAdmin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		int nuevoValor_admin_usuario = 1;

		if ((nuevoValor_admin_usuario != 1) && (nuevoValor_admin_usuario != 0)) {
			fail("Tiene que ser 0 o 1");
		}

		DBManager.cambiarAdmin(correo_usuario, nuevoValor_admin_usuario);
	}

	/**
	 * Comprueba si se pueden eliminar los usuarios
	 *
	 * @throws DBManagerException
	 */
	@After
	public void testEliminarUsuario() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		DBManager.eliminarUsuario(correo_usuario);
	}

	/**
	 * Comprueba si se puede cambiar la contrasena de los usuarios
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testCambiarContrasena() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		String nuevoValor_contrasena_usuario = "contrasena2";

		DBManager.cambiarContrasena(correo_usuario, nuevoValor_contrasena_usuario);
	}

	/**
	 * Comprueba si se devuelve un arraylist de strings con el nombre de todas las
	 * tablas de la base de datos
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testVerTablas() throws DBManagerException {
		ArrayList<String> actualArr = DBManager.verTablas();
		ArrayList<String> expectedArr = new ArrayList<String>();

		expectedArr.add("ciudad");
		expectedArr.add("club");
		expectedArr.add("entrenador");
		expectedArr.add("estadio");
		expectedArr.add("feedback");
		expectedArr.add("jugador");
		expectedArr.add("pais");
		expectedArr.add("teamoftheyear");
		expectedArr.add("teamoftheyear_view");
		expectedArr.add("usuario");
		expectedArr.add("usuariovotacion");

		assertEquals(expectedArr, actualArr);
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * correos de la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testTodosLosCorreos() throws DBManagerException {
		ArrayList<String> correos = new ArrayList<String>();
		correos = DBManager.todosLosCorreos();
		for (String correo : correos) {
			Assert.assertNotNull(correo);
		}
	}

	/**
	 * Comprueba si funciona la opción de añadir Feedback en la aplicación
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testRegistrarFeedback() throws DBManagerException {
		String correo_usuario = f.getUsuario();
		int valoracion_feedback = f.getValoracion();
		String valoracion_feedback2 = Integer.toString(valoracion_feedback);
		String recomendacion_feedback = f.getRecomendacion().toString();
		String opinion_feedback = f.getOpinion();

		DBManager.registrarFeedback(correo_usuario, valoracion_feedback2, recomendacion_feedback, opinion_feedback);
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * jugadores de la BD, teniendo en cuenta su posición
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetJugadoresPorPosicion() throws DBManagerException {
		ArrayList<String> nombre_posicion = new ArrayList<String>();
		nombre_posicion.add("Delantero");
		nombre_posicion.add("Centrocampista");
		nombre_posicion.add("Defensa");
		nombre_posicion.add("Portero");

		for (String posicion : nombre_posicion) {
			ArrayList<String> jugadoresPorPosicion = new ArrayList<String>();
			jugadoresPorPosicion = DBManager.getJugadoresPorPosicion(posicion);
			for (String jugadorPorPosicion : jugadoresPorPosicion) {
				Assert.assertNotNull(jugadorPorPosicion);
			}
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve el ID de un
	 * usuario especifico que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetIdUsuario() throws DBManagerException {
		String correo_usuario = "lopez@gmail.com";
		int idUsuario;
		idUsuario = DBManager.getIdUsuario(correo_usuario);
		Assert.assertNotNull(idUsuario);
		assertEquals(3, idUsuario);
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve el ID de un
	 * jugador especifico que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetIdJugador() throws DBManagerException {
		String nombre_jugador = "Iñaki Williams";
		int idJugador;
		idJugador = DBManager.getIdJugador(nombre_jugador);
		Assert.assertNotNull(idJugador);
		assertEquals(4, idJugador);
	}

	/**
	 * Comprueba opción de Votar a los jugadores, que la tienen disponible los
	 * usuarios
	 *
	 * Comprueba que se actualizan los votos en la BD
	 *
	 * 0. Se obtienen los id de un usuario y de los jugadores a votar, se crea un
	 * arraylist con el id de los jugadores para poder hacer un loop y acortar el
	 * codigo, y se llama al metodo de actulizarVotos para que los votos a cada
	 * jugador esten acorde a lo votado por los usuarios
	 *
	 * 1. verifica que el usuario u no ha votado (no ha podido votar, es un usuario
	 * de prueba que hemos creado en los test)
	 *
	 * 1.2 se almacenan en memoria los votos que tienen cada uno de los 5 jugadores
	 * que tenemos para usar como ejemplo
	 *
	 * 2. vota
	 *
	 * 2.1 actualiza los votos que tiene cada jugador (esto se guarda en la columna
	 * votos de la tabla jugador)
	 *
	 * 2.2 se verifica que la cantidad de votos que tenia cada jugador, n votos, ha
	 * aumentado y ahora es n+1, esto sucede en todos menos en el portero2 que es
	 * n+0 por no haber sido votado
	 *
	 * 3. se verifica que en la BD se han guardado el id de usuario y de los
	 * jugadores correctos
	 *
	 * 4. verifica que el usuario u ha votado 1 vez (se cuentan las filas con el id
	 * del usuario u)
	 *
	 * 5. vota, pero cambia el portero votado
	 *
	 * 5.1 actualiza los votos que tiene cada jugador (esto se guarda en la columna
	 * votos de la tabla jugador)
	 *
	 * 5.2 se verifica que la cantidad de votos que tenia cada jugador al principio
	 * ha aumentado en +1, pero que al haber cambiado de portero votado, el portero1
	 * vuelve a tener n votos mientras que el portero2 tiene ahora n+1 votos
	 *
	 * 6. se verifica que en la BD se han guardado el id de usuario y de los
	 * jugadores correctos
	 *
	 * 7. verifica que aun habiendo votado 2 veces, solo se guarda la ultima
	 * votacion del usuario u
	 *
	 * @throws DBManagerException
	 * @throws SQLException
	 */
	@Test
	public void testVotar_y_testActualizarVotos() throws DBManagerException, SQLException {
		int usuario_usuarioVotacion = DBManager.getIdUsuario(u.getCorreo());
		int delanteroVotado_usuarioVotacion = DBManager.getIdJugador("Iñaki Williams");
		int centrocampistaVotado_usuarioVotacion = DBManager.getIdJugador("Raul Garcia");
		int defensaVotado_usuarioVotacion = DBManager.getIdJugador("Iñigo Martinez");
		int porteroVotado_usuarioVotacion1 = DBManager.getIdJugador("Unai Simon");
		int porteroVotado_usuarioVotacion2 = DBManager.getIdJugador("Iago Herrerin");

		ArrayList<Integer> jugadores = new ArrayList<Integer>();
		jugadores.add(delanteroVotado_usuarioVotacion);
		jugadores.add(centrocampistaVotado_usuarioVotacion);
		jugadores.add(defensaVotado_usuarioVotacion);
		jugadores.add(porteroVotado_usuarioVotacion1);
		jugadores.add(porteroVotado_usuarioVotacion2);

		int voto_delantero = 0;
		int voto_centrocampista = 0;
		int voto_defensa = 0;
		int voto_portero = 0;
		int voto_portero2 = 0;

		Connection conn = DBManager.connect();
		ResultSet rs = null;

		DBManager.actualizarVotos();

		String sql1 = "select count(usuario_usuarioVotacion) from usuariovotacion where usuario_usuarioVotacion = ?";
		preparedstmt = conn.prepareStatement(sql1);
		preparedstmt.setInt(1, usuario_usuarioVotacion);
		rs = preparedstmt.executeQuery();
		rs.next();
		assertEquals(0, rs.getInt("count(usuario_usuarioVotacion)"));

		for (Integer jugador : jugadores) {
			String sql12 = "select voto_jugador from jugador where id_jugador = ?";
			preparedstmt = conn.prepareStatement(sql12);
			preparedstmt.setInt(1, jugador);
			rs = preparedstmt.executeQuery();
			rs.next();
			if (jugador == delanteroVotado_usuarioVotacion) {
				voto_delantero = rs.getInt("voto_jugador");
			} else if (jugador == centrocampistaVotado_usuarioVotacion) {
				voto_centrocampista = rs.getInt("voto_jugador");
			} else if (jugador == defensaVotado_usuarioVotacion) {
				voto_defensa = rs.getInt("voto_jugador");
			} else if (jugador == porteroVotado_usuarioVotacion1) {
				voto_portero = rs.getInt("voto_jugador");
			} else if (jugador == porteroVotado_usuarioVotacion2) {
				voto_portero2 = rs.getInt("voto_jugador");
			}
		}

		DBManager.votar(usuario_usuarioVotacion, delanteroVotado_usuarioVotacion, centrocampistaVotado_usuarioVotacion,
				defensaVotado_usuarioVotacion, porteroVotado_usuarioVotacion1);

		DBManager.actualizarVotos();

		for (Integer jugador : jugadores) {
			String sql12 = "select voto_jugador from jugador where id_jugador = ?";
			preparedstmt = conn.prepareStatement(sql12);
			preparedstmt.setInt(1, jugador);
			rs = preparedstmt.executeQuery();
			rs.next();
			if (jugador == delanteroVotado_usuarioVotacion) {
				assertEquals(voto_delantero + 1, rs.getInt("voto_jugador"));
			} else if (jugador == centrocampistaVotado_usuarioVotacion) {
				assertEquals(voto_centrocampista + 1, rs.getInt("voto_jugador"));
			} else if (jugador == defensaVotado_usuarioVotacion) {
				assertEquals(voto_defensa + 1, rs.getInt("voto_jugador"));
			} else if (jugador == porteroVotado_usuarioVotacion1) {
				assertEquals(voto_portero + 1, rs.getInt("voto_jugador"));
			} else if (jugador == porteroVotado_usuarioVotacion2) {
				assertEquals(voto_portero2 + 0, rs.getInt("voto_jugador"));
			}
		}

		DBManager.connect();
		String sql01 = "select * from usuariovotacion where usuario_usuarioVotacion = " + usuario_usuarioVotacion;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql01);
		rs.next();
		assertEquals(rs.getInt("usuario_usuarioVotacion"), usuario_usuarioVotacion);
		assertEquals(rs.getInt("delanteroVotado_usuarioVotacion"), delanteroVotado_usuarioVotacion);
		assertEquals(rs.getInt("centrocampistaVotado_usuarioVotacion"), centrocampistaVotado_usuarioVotacion);
		assertEquals(rs.getInt("defensaVotado_usuarioVotacion"), defensaVotado_usuarioVotacion);
		assertEquals(rs.getInt("porteroVotado_usuarioVotacion"), porteroVotado_usuarioVotacion1);

		DBManager.connect();
		String sql2 = "select count(usuario_usuarioVotacion) from usuariovotacion where usuario_usuarioVotacion = ?";
		preparedstmt = conn.prepareStatement(sql2);
		preparedstmt.setInt(1, usuario_usuarioVotacion);
		rs = preparedstmt.executeQuery();
		rs.next();
		assertEquals(1, rs.getInt("count(usuario_usuarioVotacion)"));

		DBManager.votar(usuario_usuarioVotacion, delanteroVotado_usuarioVotacion, centrocampistaVotado_usuarioVotacion,
				defensaVotado_usuarioVotacion, porteroVotado_usuarioVotacion2);

		DBManager.actualizarVotos();

		for (Integer jugador : jugadores) {
			String sql12 = "select voto_jugador from jugador where id_jugador = ?";
			preparedstmt = conn.prepareStatement(sql12);
			preparedstmt.setInt(1, jugador);
			rs = preparedstmt.executeQuery();
			rs.next();
			if (jugador == delanteroVotado_usuarioVotacion) {
				assertEquals(voto_delantero + 1, rs.getInt("voto_jugador"));
			} else if (jugador == centrocampistaVotado_usuarioVotacion) {
				assertEquals(voto_centrocampista + 1, rs.getInt("voto_jugador"));
			} else if (jugador == defensaVotado_usuarioVotacion) {
				assertEquals(voto_defensa + 1, rs.getInt("voto_jugador"));
			} else if (jugador == porteroVotado_usuarioVotacion1) {
				assertEquals(voto_portero + 0, rs.getInt("voto_jugador"));
			} else if (jugador == porteroVotado_usuarioVotacion2) {
				assertEquals(voto_portero2 + 1, rs.getInt("voto_jugador"));
			}
		}

		DBManager.connect();
		String sql02 = "select * from usuariovotacion where usuario_usuarioVotacion = " + usuario_usuarioVotacion;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql02);
		rs.next();
		assertEquals(rs.getInt("usuario_usuarioVotacion"), usuario_usuarioVotacion);
		assertEquals(rs.getInt("delanteroVotado_usuarioVotacion"), delanteroVotado_usuarioVotacion);
		assertEquals(rs.getInt("centrocampistaVotado_usuarioVotacion"), centrocampistaVotado_usuarioVotacion);
		assertEquals(rs.getInt("defensaVotado_usuarioVotacion"), defensaVotado_usuarioVotacion);
		assertEquals(rs.getInt("porteroVotado_usuarioVotacion"), porteroVotado_usuarioVotacion2);

		DBManager.connect();
		String sql3 = "select count(usuario_usuarioVotacion) from usuariovotacion where usuario_usuarioVotacion = ?";
		preparedstmt = conn.prepareStatement(sql3);
		preparedstmt.setInt(1, usuario_usuarioVotacion);
		rs = preparedstmt.executeQuery();
		rs.next();
		assertEquals(1, rs.getInt("count(usuario_usuarioVotacion)"));
		DBManager.disconnect();
	}

	/**
	 * Restaura los votos despues de la eliminacion (que ocurre en @after) del
	 * usuario de prueba para los test
	 * 
	 * Cierra conexion por si se ha dejado abierto en algun test
	 *
	 * @throws DBManagerException
	 */
	@After
	public void testRestaurarVotos() throws DBManagerException {
		int x;
		// quitar este comentario, y quitar las dos barras "//" al de abajo pero DEJAR
		// el metodo!!!
		// DBManager.actualizarVotos();
		// DBManager.disconnect();
	}

	/*
	 * @Test public void testContarJugadores() throws DBManagerException {
	 *
	 * // private
	 *
	 * }
	 */

	/*
	 * @Test public void testContarVotosPorJugador() throws DBManagerException {
	 *
	 * // private
	 *
	 * }
	 */

	/*
	 * @Test public void testActualizarVotos() throws DBManagerException {
	 *
	 * // Arriba, con testVotar
	 *
	 * }
	 */

	/*
	 * @Test public void testCountToft() throws DBManagerException {
	 *
	 * // private
	 *
	 * }
	 */

	/*
	 * @Test public void testGetMasVotados() throws DBManagerException {
	 *
	 * // private
	 *
	 * }
	 */

	/**
	 * Devuelve un arraylist con 11 ids de los jugadores mas votados
	 *
	 * Devuelve un arraylist con los nombres de los 11 jugadores del toft
	 *
	 * @throws DBManagerException
	 * @throws SQLException
	 */
	@Test
	public void testToft_y_testToftNombres() throws DBManagerException, SQLException {
		ArrayList<Integer> toft = new ArrayList<Integer>();
		toft = DBManager.toft();

		for (Integer jugador : toft) {
			assertNotNull(jugador);
			assertNotEquals(null, jugador);
			assertNotEquals("", jugador);
		}
		if (toft.size() != 11) {
			fail("Tienen que ser 11, por 11 jugadores");
		}
		for (var i = 0; i < toft.size(); i++) {
			if (toft.indexOf(toft.get(i)) != toft.lastIndexOf(toft.get(i))) {
				fail("No puede haber dos ids iguales, tienen que ser 11 diferentes");
			}
		}

		ArrayList<String> toftNombre = new ArrayList<String>();
		toftNombre = DBManager.toftNombres();

		Connection conn = DBManager.connect();
		ResultSet rs = null;

		int contador = 1;
		for (String jugador : toftNombre) {
			String sql1 = "select * from jugador where nombre_jugador = ?";
			preparedstmt = conn.prepareStatement(sql1);
			preparedstmt.setString(1, jugador);
			rs = preparedstmt.executeQuery();
			rs.next();

			int expected = toft.get(contador - 1);
			int actual = rs.getInt("id_jugador");
			assertEquals(expected, actual);

			if ((contador == 1) || (contador == 2) || (contador == 3)) {
				assertEquals("Delantero", rs.getString("posicion_jugador"));
			} else if ((contador == 4) || (contador == 5) || (contador == 6)) {
				assertEquals("Centrocampista", rs.getString("posicion_jugador"));
			} else if ((contador == 7) || (contador == 8) || (contador == 9) || (contador == 10)) {
				assertEquals("Defensa", rs.getString("posicion_jugador"));
			} else if (contador == 11) {
				assertEquals("Portero", rs.getString("posicion_jugador"));
			} else {
				fail("Tienen que ser 3 delanteros, 3 centrocampistas, 4 defensas, 1 portero, y ademas en ese orden");
			}
			contador++;
		}
	}

	/*
	 * @Test public void testToftNombres() throws DBManagerException, SQLException {
	 *
	 * // Arriba, con testToft
	 *
	 * }
	 */

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todas las
	 * ciudades que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetCiudades() throws DBManagerException {
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		ciudades = DBManager.getCiudades();
		for (Ciudad ciudad : ciudades) {
			Assert.assertNotNull(ciudad);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * clubes que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetClubes() throws DBManagerException {

		ArrayList<Club> clubes = new ArrayList<Club>();
		clubes = DBManager.getClubes();
		for (Club club : clubes) {
			Assert.assertNotNull(club);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * entrenadores que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetEntrenadores() throws DBManagerException {

		ArrayList<Entrenador> entrenadores = new ArrayList<Entrenador>();
		entrenadores = DBManager.getEntrenadores();
		for (Entrenador entrenador : entrenadores) {
			Assert.assertNotNull(entrenador);
		}
	}

	// M�todos Entrenador

	/**
	 * Devuelve el nombre del entrenador
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testNombreEntrenador() throws DBManagerException {
		assertEquals(e.getNombre(), DBManager.nombreEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la fecha de nacimiento del entrenador
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testFechaNacimiento() throws DBManagerException {
		assertEquals(e.getFechaNac(), DBManager.fechaNacimiento("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve el club al cual pertenece el entrenador
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testClubEntrenador() throws DBManagerException {
		assertEquals(e.getClub(), DBManager.clubEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la ciudad del entrenador
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testCiudadEntrenador() throws DBManagerException {
		assertEquals(e.getCiudad(), DBManager.ciudadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la formacion del entrenador
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testFormacionEntrenador() throws DBManagerException {
		assertEquals(e.getFormacion(), DBManager.formacionEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la mentalidad del entrenador
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testMentalidadEntrenador() throws DBManagerException {
		assertEquals("Defensiva", DBManager.mentalidadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}
	// Fin Metodos Entrenador

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * estadios que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetEstadios() throws DBManagerException {

		ArrayList<Estadio> estadios = new ArrayList<Estadio>();
		estadios = DBManager.getEstadios();
		for (Estadio estadio : estadios) {
			Assert.assertNotNull(estadio);
		}
	}

	// Metodos Estadio
	/**
	 * Devuelve el nombre del estadio
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testNombreEstadio() throws DBManagerException {
		assertEquals(es.getNombre(), DBManager.nombreEstadio("San Mames", "wikifutbolschema"));
	}

	/**
	 * Devuelve el aforo del estadio
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testAforoEstadio() throws DBManagerException {
		assertEquals(es.getAforo(), DBManager.aforoEstadio("San Mames", "wikifutbolschema"));
	}

	/**
	 * Devuelve el anyo que el estadio fue construido
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testAnyoEstadio() throws DBManagerException {
		assertEquals(es.getAnyoCreacion(), DBManager.anyoEstadio("San Mames", "wikifutbolschema"));
	}

	/**
	 * Devuelve la ciudad donde se encuentra el estadio
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testCiudadEstadio() throws DBManagerException {
		assertEquals(es.getCiudad(), DBManager.ciudadEstadio("San Mames", "wikifutbolschema"));
	}
	// Fin Metodos Estadio

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * Feedbacks que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetFeedbacks() throws DBManagerException {

		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		feedbacks = DBManager.getFeedbacks();
		for (Feedback feedback : feedbacks) {
			Assert.assertNotNull(feedback);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * jugadores que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetJugadores() throws DBManagerException {

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores = DBManager.getJugadores();
		for (Jugador jugador : jugadores) {
			Assert.assertNotNull(jugador);
		}

	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * jugadores que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetJugadorBd() throws DBManagerException {
		Jugador jugador = DBManager.getJugadorBd(j.getNombre());
		assertEquals(jugador, j);
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * paises que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetPaises() throws DBManagerException {
		ArrayList<Pais> paises = new ArrayList<Pais>();
		paises = DBManager.getPaises();
		for (Pais pais : paises) {
			Assert.assertNotNull(pais);
		}
	}

	/**
	 * Comprueba que el Team of The Year se ve correctamente
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetTeamOfTheYear_view() throws DBManagerException {

		ArrayList<TeamOfTheYear_view> totyv = new ArrayList<TeamOfTheYear_view>();
		totyv = DBManager.getTeamOfTheYear_view();
		for (TeamOfTheYear_view teamOfTheYear_view : totyv) {
			Assert.assertNotNull(teamOfTheYear_view);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * jugadores que se encuentran en el Team of The Year
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetTeamOfTheYear() throws DBManagerException {

		ArrayList<TeamOfTheYear> toty = new ArrayList<TeamOfTheYear>();
		toty = DBManager.getTeamOfTheYear();
		for (TeamOfTheYear teamOfTheYear : toty) {
			Assert.assertNotNull(teamOfTheYear);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método que nos devuelve todos los
	 * usuarios que se encuentran en la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetUsuarios() throws DBManagerException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = DBManager.getUsuarios();
		for (Usuario usuario : usuarios) {
			Assert.assertNotNull(usuario);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del método de la BD getUsuarioVotaciones
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetUsuarioVotaciones() throws DBManagerException {

		ArrayList<UsuarioVotacion> usuarioVotaciones = new ArrayList<UsuarioVotacion>();
		usuarioVotaciones = DBManager.getUsuarioVotaciones();
		for (UsuarioVotacion usuarioVotacion : usuarioVotaciones) {
			Assert.assertNotNull(usuarioVotacion);
		}
	}

	/**
	 * Comprueba si se devuelven las columnas de la BD correctamente
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testVerColumnas() throws DBManagerException {
		ArrayList<String> actualPais = new ArrayList<String>();
		actualPais.add(0, "id_pais");
		actualPais.add(1, "nombre_pais");

		String tabla = "pais";
		assertEquals(DBManager.verColumnas(tabla), actualPais);

		ArrayList<String> actualEstadio = new ArrayList<String>();
		actualEstadio.add(0, "id_estadio");
		actualEstadio.add(1, "nombre_estadio");
		actualEstadio.add(2, "aforo_estadio");
		actualEstadio.add(3, "anoCreacion_estadio");
		actualEstadio.add(4, "ciudad_estadio");

		tabla = "estadio";
		assertEquals(DBManager.verColumnas(tabla), actualEstadio);

		ArrayList<String> actualCiudad = new ArrayList<String>();
		actualCiudad.add(0, "id_ciudad");
		actualCiudad.add(1, "nombre_ciudad");
		actualCiudad.add(2, "pais_ciudad");

		tabla = "ciudad";
		assertEquals(DBManager.verColumnas(tabla), actualCiudad);

		ArrayList<String> actualClub = new ArrayList<String>();
		actualClub.add(0, "id_club");
		actualClub.add(1, "nombre_club");
		actualClub.add(2, "ciudad_club");
		actualClub.add(3, "estadio_club");
		actualClub.add(4, "anoCreacion_club");
		actualClub.add(5, "palmares_club");
		actualClub.add(6, "entrenador_club");

		tabla = "club";
		assertEquals(DBManager.verColumnas(tabla), actualClub);

		ArrayList<String> actualEntrenador = new ArrayList<String>();
		actualEntrenador.add(0, "id_entrenador");
		actualEntrenador.add(1, "nombre_entrenador");
		actualEntrenador.add(2, "fechaNac_entrenador");
		actualEntrenador.add(3, "club_entrenador");
		actualEntrenador.add(4, "ciudad_entrenador");
		actualEntrenador.add(5, "formacion_entrenador");
		actualEntrenador.add(6, "mentalidad_entrenador");

		tabla = "entrenador";
		assertEquals(DBManager.verColumnas(tabla), actualEntrenador);

		ArrayList<String> actualFeedback = new ArrayList<String>();
		actualFeedback.add(0, "id_feedback");
		actualFeedback.add(1, "usuario_feedback");
		actualFeedback.add(2, "valoracion_feedback");
		actualFeedback.add(3, "recomendacion_feedback");
		actualFeedback.add(4, "opinion_feedback");

		tabla = "feedback";
		assertEquals(DBManager.verColumnas(tabla), actualFeedback);

		ArrayList<String> actualJugador = new ArrayList<String>();
		actualJugador.add(0, "id_jugador");
		actualJugador.add(1, "nombre_jugador");
		actualJugador.add(2, "fechaNac_jugador");
		actualJugador.add(3, "club_jugador");
		actualJugador.add(4, "ciudad_jugador");
		actualJugador.add(5, "posicion_jugador");
		actualJugador.add(6, "dorsal_jugador");
		actualJugador.add(7, "goles_jugador");
		actualJugador.add(8, "altura_jugador");
		actualJugador.add(9, "peso_jugador");
		actualJugador.add(10, "pieFav_jugador");
		actualJugador.add(11, "valoracion_jugador");
		actualJugador.add(12, "descripcion_jugador");
		actualJugador.add(13, "voto_jugador");

		tabla = "jugador";
		assertEquals(DBManager.verColumnas(tabla), actualJugador);

		ArrayList<String> actualtoft = new ArrayList<String>();
		actualtoft.add(0, "id_TeamOfTheYear");
		actualtoft.add(1, "jugador_TeamOfTheYear");

		tabla = "teamoftheyear";
		assertEquals(DBManager.verColumnas(tabla), actualtoft);

		ArrayList<String> actualusuario = new ArrayList<String>();
		actualusuario.add(0, "id_usuario");
		actualusuario.add(1, "nombre_usuario");
		actualusuario.add(2, "correo_usuario");
		actualusuario.add(3, "contrasena_usuario");
		actualusuario.add(4, "admin_usuario");
		actualusuario.add(5, "fechaNac_usuario");

		tabla = "usuario";
		assertEquals(DBManager.verColumnas(tabla), actualusuario);

		ArrayList<String> actualusuariovotacion = new ArrayList<String>();
		actualusuariovotacion.add(0, "id_usuarioVotacion");
		actualusuariovotacion.add(1, "usuario_usuarioVotacion");
		actualusuariovotacion.add(2, "delanteroVotado_usuarioVotacion");
		actualusuariovotacion.add(3, "centrocampistaVotado_usuarioVotacion");
		actualusuariovotacion.add(4, "defensaVotado_usuarioVotacion");
		actualusuariovotacion.add(5, "porteroVotado_usuarioVotacion");

		tabla = "usuariovotacion";
		assertEquals(DBManager.verColumnas(tabla), actualusuariovotacion);

		ArrayList<String> actualtoftview = new ArrayList<String>();
		actualtoftview.add(0, "id_teamoftheyear");
		actualtoftview.add(1, "id_jugador");
		actualtoftview.add(2, "nombre_jugador");
		actualtoftview.add(3, "posicion_jugador");
		actualtoftview.add(4, "voto_jugador");
		actualtoftview.add(5, "goles_jugador");

		tabla = "teamoftheyear_view";
		assertEquals(DBManager.verColumnas(tabla), actualtoftview);
	}

	@Test
	public void testData() throws DBManagerException {
		for (String tabla : DBManager.verTablas()) {
			Object[][] o = DBManager.data(tabla);
			assertNotNull(o);
		}
	}

	/**
	 * Comprueba la opcion de cambiar datos que tiene el administrador desde una
	 * especie de cosnola
	 *
	 * @throws DBManagerException
	 * @throws SQLException
	 */
	@Test
	public void testCambiarDatos() throws DBManagerException, SQLException {
		Connection conn = DBManager.connect();
		ResultSet rs = null;

		String sql1 = "select nombre_usuario from usuario where correo_usuario = ?";
		preparedstmt = conn.prepareStatement(sql1);
		preparedstmt.setString(1, u.getCorreo());
		rs = preparedstmt.executeQuery();
		rs.next();
		String primerDato = rs.getString("nombre_usuario");

		String sql2 = "update usuario set nombre_usuario = 'nombre nuevo' where correo_usuario = '" + u.getCorreo()
				+ "'";
		DBManager.cambiarDatos(sql2);

		String sql3 = "select nombre_usuario from usuario where correo_usuario = ?";
		preparedstmt = conn.prepareStatement(sql3);
		preparedstmt.setString(1, u.getCorreo());
		rs = preparedstmt.executeQuery();
		rs.next();

		assertNotEquals(primerDato, rs.getString("nombre_usuario"));
	}

	/**
	 * Comprueba la opcion de cambiar datos desde la JTable que tiene el
	 * administrador, luego restaura el valor
	 * 
	 * @throws DBManagerException
	 * @throws SQLException
	 */
	@Test
	public void testCambiarDatosDesdeJTable() throws DBManagerException, SQLException {

		Connection conn = DBManager.connect();
		ResultSet rs = null;

		String sql1 = "select nombre_usuario from usuario where correo_usuario = 'a@gmail.com'";
		preparedstmt = conn.prepareStatement(sql1);
		rs = preparedstmt.executeQuery();
		rs.next();
		String primerDato = rs.getString("nombre_usuario");

		String tabla = "usuario";
		String columna = "nombre_usuario";
		Object valor = "nuevoNombre";
		int id = 1;
		DBManager.cambiarDatosDesdeJTable(tabla, columna, valor, id);

		String sql3 = "select nombre_usuario from usuario where correo_usuario = 'a@gmail.com'";
		preparedstmt = conn.prepareStatement(sql3);
		rs = preparedstmt.executeQuery();
		rs.next();

		assertNotEquals(primerDato, rs.getString("nombre_usuario"));

		DBManager.cambiarDatosDesdeJTable(tabla, columna, primerDato, id);
	}

	/**
	 * Comprueba el método que nos permite ver a los jugadores dependiendo del
	 * equipo al que pertenezcan
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testGetJugadoresPorEquipo() throws DBManagerException {

		String nombre_club = "Athletic Club";
		ArrayList<String> jugadoresPorEquipo = new ArrayList<String>();
		jugadoresPorEquipo = DBManager.getJugadoresPorEquipo(nombre_club);
		for (String jugadorPorEquipo : jugadoresPorEquipo) {
			Assert.assertNotNull(jugadorPorEquipo);
		}
	}

	/**
	 * Comprueba que devuelve el numero de filas que tiene cada una de las tablas de
	 * la BD
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testNumeroDeFilasEnUnaTabla() throws DBManagerException {
		for (String table : database.DBManager.verTablas()) {
			System.out.println(DBManager.numeroDeFilasEnUnaTabla(table));
			Assert.assertNotNull(DBManager.numeroDeFilasEnUnaTabla(table));
		}
	}

}