package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
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

/**
 * Test de DBManager
 *
 * @author sergi
 *
 */
public class DBManagerTest {
	private static Usuario u = new Usuario(1, "nombre usuario", "contrasena", "correo", 0, "1970-01-01");
	private static Entrenador e = new Entrenador(1, "Gaizka Garitano", "1975-07-09", "Athletic Club", "Bilbao", "4-3-3",
			Mentalidad.Equilibrada);
	private static Estadio es = new Estadio(1, "San Mames", 53289, 2013, "Bilbao");
	private static Feedback f = new Feedback(1, u.getCorreo(), 5, Recomendacion.si, "opinion");
	private static Jugador j = new Jugador(1, "Alex Berenguer", "1993-10-01", "Athletic Club", "Bilbao",
			Posicion.Delantero, 8, 0, 182, 81, PieFav.Diestro, 84, "Jugador con desborde", 1);

	private static Connection conn;

	@BeforeClass
	public static void setUp() throws Exception {
		new DBManager();
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
	 * @throws DBManagerException En caso de fallo
	 */
	@BeforeClass
	public static void testConnect() throws DBManagerException {
		conn = DBManager.connect();
	}

	/**
	 * Comprueba si se puede desconectar del servidor
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@AfterClass
	public static void testDisconnect() throws DBManagerException {
		DBManager.disconnect();
	}

	/**
	 * Comprueba si el correo insertado existe o no
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testExisteCorreo() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		assertTrue(DBManager.existeCorreo(correo_usuario));

		String correoInventado = "xxxxx@gmail.com";
		assertFalse(DBManager.existeCorreo(correoInventado));
	}

	/**
	 * Comprueba si se puede registrar un usuario nuevo
	 *
	 * @throws DBManagerException En caso de fallo
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
	 * Test que comprueba el correcto funcionamiento del metodo login
	 *
	 * @throws DBManagerException En caso de fallo
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
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testEsAdmin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		assertFalse(DBManager.esAdmin(correo_usuario));

		String correoDeUnAdmin = "eneko.perez23@gmail.com";
		assertTrue(DBManager.esAdmin(correoDeUnAdmin));
	}

	/**
	 * Comprueba si funciona el metodo que nos permite cambiar a un usuario como
	 * admin o como usuario normal
	 *
	 * @throws DBManagerException En caso de fallo
	 * @throws SQLException       En caso de fallo En caso de fallo
	 */
	@Test
	public void testCambiarAdmin() throws DBManagerException, SQLException {
		String correo_usuario = u.getCorreo();
		int nuevoValor_admin_usuario = 1;

		ResultSet rs = null;
		PreparedStatement preparedstmt = null;

		String sql = "select admin_usuario from usuario where correo_usuario = ?";
		preparedstmt = conn.prepareStatement(sql);
		preparedstmt.setString(1, u.getCorreo());
		rs = preparedstmt.executeQuery();
		rs.next();
		assertEquals(rs.getInt("admin_usuario"), u.getAdmin());
		assertEquals(rs.getInt("admin_usuario"), 0);

		assertTrue((nuevoValor_admin_usuario == 1) || (nuevoValor_admin_usuario == 0));

		DBManager.cambiarAdmin(correo_usuario, nuevoValor_admin_usuario);

		String sql2 = "select admin_usuario from usuario where correo_usuario = ?";
		preparedstmt = conn.prepareStatement(sql2);
		preparedstmt.setString(1, u.getCorreo());
		rs = preparedstmt.executeQuery();
		rs.next();
		assertEquals(rs.getInt("admin_usuario"), 1);
	}

	/**
	 * Comprueba si se pueden eliminar los usuarios
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@After
	public void testEliminarUsuario() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		DBManager.eliminarUsuario(correo_usuario);
	}

	/**
	 * Comprueba si se puede cambiar la contrasena de los usuarios
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testCambiarContrasena() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		String nuevoValor_contrasena_usuario = "contrasena2";

		DBManager.login(u.getCorreo(), u.getContrasena());
		DBManager.cambiarContrasena(correo_usuario, nuevoValor_contrasena_usuario);
		DBManager.login(u.getCorreo(), nuevoValor_contrasena_usuario);
	}

	/**
	 * Comprueba si se devuelve un arraylist de strings con el nombre de todas las
	 * tablas de la base de datos
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testVerTablas() throws DBManagerException {
		List<String> actualArr = DBManager.verTablas();
		List<String> expectedArr = new ArrayList<String>();

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
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * correos de la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testTodosLosCorreos() throws DBManagerException {
		List<String> correos = new ArrayList<String>();
		correos = DBManager.todosLosCorreos();
		for (String correo : correos) {
			Assert.assertNotNull(correo);
		}
	}

	/**
	 * Comprueba si funciona la opción de añadir Feedback en la aplicación
	 *
	 * @throws DBManagerException En caso de fallo
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
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * jugadores de la BD, teniendo en cuenta su posición
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetJugadoresPorPosicion() throws DBManagerException {
		ArrayList<String> nombre_posicion = new ArrayList<String>();
		nombre_posicion.add("Delantero");
		nombre_posicion.add("Centrocampista");
		nombre_posicion.add("Defensa");
		nombre_posicion.add("Portero");

		for (String posicion : nombre_posicion) {
			List<String> jugadoresPorPosicion = DBManager.getJugadoresPorPosicion(posicion);
			for (String jugador : jugadoresPorPosicion) {
				Assert.assertNotNull(jugador);
			}
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve el ID de un
	 * usuario especifico que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetIdUsuario() throws DBManagerException {
		String correo_usuario = "a@gmail.com";
		int idUsuario;
		idUsuario = DBManager.getIdUsuario(correo_usuario);
		Assert.assertNotNull(idUsuario);
		assertEquals(1, idUsuario);
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve el ID de un
	 * jugador especifico que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
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
	 * @throws DBManagerException En caso de fallo
	 * @throws SQLException       En caso de fallo En caso de fallo
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

		ResultSet rs = null;
		Statement stmt = null;
		PreparedStatement preparedstmt = null;

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

		String sql01 = "select * from usuariovotacion where usuario_usuarioVotacion = " + usuario_usuarioVotacion;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql01);
		rs.next();
		assertEquals(rs.getInt("usuario_usuarioVotacion"), usuario_usuarioVotacion);
		assertEquals(rs.getInt("delanteroVotado_usuarioVotacion"), delanteroVotado_usuarioVotacion);
		assertEquals(rs.getInt("centrocampistaVotado_usuarioVotacion"), centrocampistaVotado_usuarioVotacion);
		assertEquals(rs.getInt("defensaVotado_usuarioVotacion"), defensaVotado_usuarioVotacion);
		assertEquals(rs.getInt("porteroVotado_usuarioVotacion"), porteroVotado_usuarioVotacion1);

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

		String sql02 = "select * from usuariovotacion where usuario_usuarioVotacion = " + usuario_usuarioVotacion;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql02);
		rs.next();
		assertEquals(rs.getInt("usuario_usuarioVotacion"), usuario_usuarioVotacion);
		assertEquals(rs.getInt("delanteroVotado_usuarioVotacion"), delanteroVotado_usuarioVotacion);
		assertEquals(rs.getInt("centrocampistaVotado_usuarioVotacion"), centrocampistaVotado_usuarioVotacion);
		assertEquals(rs.getInt("defensaVotado_usuarioVotacion"), defensaVotado_usuarioVotacion);
		assertEquals(rs.getInt("porteroVotado_usuarioVotacion"), porteroVotado_usuarioVotacion2);

		String sql3 = "select count(usuario_usuarioVotacion) from usuariovotacion where usuario_usuarioVotacion = ?";
		preparedstmt = conn.prepareStatement(sql3);
		preparedstmt.setInt(1, usuario_usuarioVotacion);
		rs = preparedstmt.executeQuery();
		rs.next();
		assertEquals(1, rs.getInt("count(usuario_usuarioVotacion)"));

		DBManager.actualizarVotos();
	}

	/**
	 * Devuelve un arraylist con 11 ids de los jugadores mas votados
	 *
	 * Devuelve un arraylist con los nombres de los 11 jugadores del toft
	 *
	 * @throws DBManagerException En caso de fallo
	 * @throws SQLException       En caso de fallo En caso de fallo
	 */
	@Test
	public void testToft_y_testToftNombres() throws DBManagerException, SQLException {
		Map<Integer, Integer> toft = DBManager.toft();

		for (Iterator<Entry<Integer, Integer>> iterator = toft.entrySet().iterator(); iterator.hasNext();) {
			Entry<Integer, Integer> jugador = iterator.next();
			assertNotNull(jugador);
			assertNotEquals(null, jugador);
			assertNotEquals("", jugador);
		}

		assertTrue(toft.size() == 11);

		Collection<Integer> list = toft.values();
		for (Iterator<Integer> itr = list.iterator(); itr.hasNext();) {
			assertTrue(Collections.frequency(list, itr.next()) == 1);
		}

		List<String> toftNombre = DBManager.toftNombres();

		ResultSet rs = null;
		PreparedStatement preparedstmt = null;

		int contador = 1;
		for (String jugador : toftNombre) {
			String sql1 = "select * from jugador where nombre_jugador = ?";
			preparedstmt = conn.prepareStatement(sql1);
			preparedstmt.setString(1, jugador);
			rs = preparedstmt.executeQuery();
			rs.next();

			int expected = toft.get(contador);
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
			}
			assertTrue((contador == 1) || (contador == 2) || (contador == 3) || (contador == 4) || (contador == 5)
					|| (contador == 6) || (contador == 7) || (contador == 8) || (contador == 9) || (contador == 10)
					|| (contador == 11));
			contador++;
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todas las
	 * ciudades que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetCiudades() throws DBManagerException {
		List<Ciudad> ciudades = DBManager.getCiudades();
		for (Ciudad ciudad : ciudades) {
			Assert.assertNotNull(ciudad);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * clubes que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetClubes() throws DBManagerException {
		List<Club> clubes = DBManager.getClubes();
		for (Club club : clubes) {
			Assert.assertNotNull(club);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * entrenadores que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetEntrenadores() throws DBManagerException {
		List<Entrenador> entrenadores = DBManager.getEntrenadores();
		for (Entrenador entrenador : entrenadores) {
			Assert.assertNotNull(entrenador);
		}
	}

	// Metodos Entrenador

	/**
	 * Devuelve el nombre del entrenador
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testNombreEntrenador() throws DBManagerException {
		assertEquals(e.getNombre(), DBManager.nombreEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la fecha de nacimiento del entrenador
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testFechaNacimiento() throws DBManagerException {
		assertEquals(e.getFechaNac(), DBManager.fechaNacimiento("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve el club al cual pertenece el entrenador
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testClubEntrenador() throws DBManagerException {
		assertEquals(e.getClub(), DBManager.clubEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la ciudad del entrenador
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testCiudadEntrenador() throws DBManagerException {
		assertEquals(e.getCiudad(), DBManager.ciudadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la formacion del entrenador
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testFormacionEntrenador() throws DBManagerException {
		assertEquals(e.getFormacion(), DBManager.formacionEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	/**
	 * Devuelve la mentalidad del entrenador
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testMentalidadEntrenador() throws DBManagerException {
		assertEquals("Defensiva", DBManager.mentalidadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}
	// Fin Metodos Entrenador

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * estadios que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetEstadios() throws DBManagerException {
		List<Estadio> estadios = DBManager.getEstadios();
		for (Estadio estadio : estadios) {
			Assert.assertNotNull(estadio);
		}
	}

	// Metodos Estadio
	/**
	 * Devuelve el nombre del estadio
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testNombreEstadio() throws DBManagerException {
		assertEquals(es.getNombre(), DBManager.nombreEstadio("San Mames", "wikifutbolschema"));
	}

	/**
	 * Devuelve el aforo del estadio
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testAforoEstadio() throws DBManagerException {
		assertEquals(es.getAforo(), DBManager.aforoEstadio("San Mames", "wikifutbolschema"));
	}

	/**
	 * Devuelve el anyo que el estadio fue construido
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testAnyoEstadio() throws DBManagerException {
		assertEquals(es.getAnyoCreacion(), DBManager.anyoEstadio("San Mames", "wikifutbolschema"));
	}

	/**
	 * Devuelve la ciudad donde se encuentra el estadio
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testCiudadEstadio() throws DBManagerException {
		assertEquals(es.getCiudad(), DBManager.ciudadEstadio("San Mames", "wikifutbolschema"));
	}
	// Fin Metodos Estadio

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * Feedbacks que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetFeedbacks() throws DBManagerException {
		List<Feedback> feedbacks = DBManager.getFeedbacks();
		for (Feedback feedback : feedbacks) {
			Assert.assertNotNull(feedback);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * jugadores que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetJugadores() throws DBManagerException {
		List<Jugador> jugadores = DBManager.getJugadores();
		for (Jugador jugador : jugadores) {
			Assert.assertNotNull(jugador);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * jugadores que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetJugadorBd() throws DBManagerException {
		Jugador jugador = DBManager.getJugadorBd(j.getNombre());

		assertEquals(jugador.getId(), j.getId());
		assertEquals(jugador.getNombre(), j.getNombre());
		assertEquals(jugador.getFechaNac(), j.getFechaNac());
		assertEquals(jugador.getClub(), j.getClub());
		assertEquals(jugador.getCiudad(), j.getCiudad());
		assertEquals(jugador.getPosicion(), j.getPosicion());
		assertEquals(jugador.getDorsal(), j.getDorsal());
		assertEquals(jugador.getGoles(), j.getGoles());
		assertEquals(jugador.getAltura(), j.getAltura());
		assertEquals(jugador.getPeso(), j.getPeso());
		assertEquals(jugador.getPiefav(), j.getPiefav());
		assertEquals(jugador.getValoracion(), j.getValoracion());
		assertEquals(jugador.getDescripcion(), j.getDescripcion());
		// assertEquals(jugador.getVoto(), j.getVoto());
		/* si se le vota o no puede cambiar el valor y dar error */
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * paises que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetPaises() throws DBManagerException {
		List<Pais> paises = new ArrayList<Pais>();
		paises = DBManager.getPaises();
		for (Pais pais : paises) {
			Assert.assertNotNull(pais);
		}
	}

	/**
	 * Comprueba que el Team of The Year se ve correctamente
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetTeamOfTheYear_view() throws DBManagerException {

		List<TeamOfTheYear_view> totyv = new ArrayList<TeamOfTheYear_view>();
		totyv = DBManager.getTeamOfTheYear_view();
		for (TeamOfTheYear_view teamOfTheYear_view : totyv) {
			Assert.assertNotNull(teamOfTheYear_view);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * jugadores que se encuentran en el Team of The Year
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetTeamOfTheYear() throws DBManagerException {
		List<TeamOfTheYear> toty = new ArrayList<TeamOfTheYear>();
		toty = DBManager.getTeamOfTheYear();
		for (TeamOfTheYear teamOfTheYear : toty) {
			Assert.assertNotNull(teamOfTheYear);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo que nos devuelve todos los
	 * usuarios que se encuentran en la BD
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetUsuarios() throws DBManagerException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = DBManager.getUsuarios();
		for (Usuario usuario : usuarios) {
			Assert.assertNotNull(usuario);
		}
	}

	/**
	 * Comprueba el correcto funcionamiento del metodo de la BD getUsuarioVotaciones
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetUsuarioVotaciones() throws DBManagerException {
		List<UsuarioVotacion> usuarioVotaciones = new ArrayList<UsuarioVotacion>();
		usuarioVotaciones = DBManager.getUsuarioVotaciones();
		for (UsuarioVotacion usuarioVotacion : usuarioVotaciones) {
			Assert.assertNotNull(usuarioVotacion);
		}
	}

	/**
	 * Comprueba si se devuelven las columnas de la BD correctamente
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testVerColumnas() throws DBManagerException {
		ArrayList<String> actualPais = new ArrayList<String>();
		actualPais.add(0, "id_pais");
		actualPais.add(1, "nombre_pais");
		actualPais.add(2, "capital_pais");
		actualPais.add(3, "gentilicio_pais");
		actualPais.add(4, "idioma_pais");

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
		actualCiudad.add(3, "poblacion_ciudad");
		actualCiudad.add(4, "gentilicio_ciudad");
		actualCiudad.add(5, "provincia_ciudad");
		actualCiudad.add(6, "comAutonoma_ciudad");

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

	/**
	 * Prueba el metodo que vuelca los datos de cualquier tabla de la BD a un array
	 * 2D El objetivo es rellenar una JTable
	 *
	 * @throws DBManagerException En caso de fallos
	 */
	@Test
	public void testData() throws DBManagerException {
		for (String tabla : DBManager.verTablas()) {
			assertNotNull(DBManager.data(tabla));
		}
	}

	/**
	 * Comprueba la opcion de cambiar datos que tiene el administrador desde una
	 * especie de cosnola
	 *
	 * @throws DBManagerException En caso de fallo
	 * @throws SQLException       En caso de fallo En caso de fallo
	 */
	@Test
	public void testCambiarDatos() throws DBManagerException, SQLException {
		ResultSet rs = null;
		PreparedStatement preparedstmt = null;

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
	 * @throws DBManagerException En caso de fallo
	 * @throws SQLException       En caso de fallo En caso de fallo
	 */
	@Test
	public void testCambiarDatosDesdeJTable() throws DBManagerException, SQLException {
		ResultSet rs = null;
		PreparedStatement preparedstmt = null;

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
	 * Comprueba el metodo que nos permite ver a los jugadores dependiendo del
	 * equipo al que pertenezcan
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@Test
	public void testGetJugadoresPorEquipo() throws DBManagerException {

		String nombre_club = "Athletic Club";
		List<String> jugadoresPorEquipo = new ArrayList<String>();
		jugadoresPorEquipo = DBManager.getJugadoresPorEquipo(nombre_club);
		for (String jugadorPorEquipo : jugadoresPorEquipo) {
			Assert.assertNotNull(jugadorPorEquipo);
		}
	}

	/**
	 * Comprueba que devuelve el numero de filas que tiene cada una de las tablas de
	 * la BD
	 *
	 * @throws DBManagerException En caso de fallo En caso de fallo
	 */
	@Test
	public void testNumeroDeFilasEnUnaTabla() throws DBManagerException {
		for (String table : database.DBManager.verTablas()) {
			Assert.assertNotNull(DBManager.numeroDeFilasEnUnaTabla(table));
		}
	}

	/**
	 * Comprueba que inserta nuevos valores (filas) en la BD y luego es capaz de
	 * borrar verificando el metodo borrar de DBManager.class
	 *
	 * @throws DBManagerException
	 */
	@Test
	public void testNuevasFilas_y_borrar() throws DBManagerException {
		for (String tabla : DBManager.verTablas()) {
			int idMasBajo = DBManager.idMasBajoSinUsar(tabla);
			int filas = DBManager.numeroDeFilasEnUnaTabla(tabla);
			if (tabla.equals("ciudad")) {
				DBManager.nuevaCiudad(idMasBajo, "ciudad", 1, 10000, "ciudadano", "provincia", "C A");
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("club")) {
				DBManager.nuevoClub(idMasBajo, "club", 1, 1, 1970, 10, 1);
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("entrenador")) {
				DBManager.nuevoEntrenador(idMasBajo, "entrenador", "1970-01-01", 1, 1, "4-3-3", "Defensiva");
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("estadio")) {
				DBManager.nuevoEstadio(idMasBajo, "estadio", 1000, 1970, 1);
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("feedback")) {
				DBManager.nuevoFeedback(idMasBajo, DBManager.getIdUsuario(u.getCorreo()), 5, "si", "opinion");
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("jugador")) {
				DBManager.nuevoJugador(idMasBajo, "jugador", "1970-01-01", 1, 1, "Delantero", 9, 5, 175, 75, "Diestro",
						84, "Descripcion", 0);
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("pais")) {
				DBManager.nuevoPais(idMasBajo, "pais", "capital", "ciudadano", "idioma");
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("usuario")) {
				DBManager.nuevoUsuario(idMasBajo, "nombre usuario", "correo@gmail.com", "passw", 1, "1970-01-01");
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			} else if (tabla.equals("usuariovotacion")) {
				DBManager.nuevoUsuarioVotacion(idMasBajo, DBManager.getIdUsuario(u.getCorreo()), 1, 5, 9, 14);
				assertEquals(filas + 1, DBManager.numeroDeFilasEnUnaTabla(tabla));
				DBManager.borrar(tabla, idMasBajo);
				assertEquals(filas, DBManager.numeroDeFilasEnUnaTabla(tabla));
			}
		}
	}

	/**
	 * Comprueba que devuelve el id mas bajo sin usar de cada tabla
	 *
	 * @throws DBManagerException
	 * @throws SQLException
	 */
	@Test
	public void TestIdMasBajoSinUsar() throws DBManagerException, SQLException {
		ResultSet rs = null;
		PreparedStatement preparedstmt = null;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String sql1;
		int id;

		for (String tabla : DBManager.verTablas()) {
			if (!tabla.equals("usuariovotacion")) { // !!!
				ids.clear();
				if (tabla.equals("teamoftheyear_view")) {
					sql1 = "SELECT id_teamoftheyear FROM teamoftheyear_view order by id_teamoftheyear";
				} else {
					sql1 = "select id_" + tabla + " from " + tabla + " order by id_" + tabla;
				}
				preparedstmt = conn.prepareStatement(sql1);
				rs = preparedstmt.executeQuery();
				while (rs.next()) {
					if (tabla.equals("teamoftheyear_view")) {
						id = rs.getInt("id_teamoftheyear");
					} else {
						id = rs.getInt("id_" + tabla);
					}
					ids.add(id);
				}
				int noId = 0;
				int filas = DBManager.numeroDeFilasEnUnaTabla(tabla) + 1;
				for (int i = 1; i < filas; i++) {
					if (i != ids.get(i - 1)) {
						noId = i;
						break;
					}
				}
				if (noId != 0) {
					// si noId es 0 es que no hay nignun id sin usar entre filas
					assertEquals(noId, DBManager.idMasBajoSinUsar(tabla));
				} else {
					assertEquals(filas, DBManager.idMasBajoSinUsar(tabla));
				}
			}
		}
	}

}