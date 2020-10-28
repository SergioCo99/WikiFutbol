package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Entrenador;
import clases.Entrenador.Mentalidad;
import clases.Estadio;
import clases.Feedback;
import clases.Feedback.Recomendacion;
import clases.Jugador;
import clases.Jugador.PieFav;
import clases.Jugador.Posicion;
import clases.Usuario;

public class DBManagerTest {

	static DBManager db = new DBManager();
	static Usuario u = new Usuario(1, "nombre usuario", "contrasena", "correo", 0, "1970-01-01");
	static Entrenador e = new Entrenador(1, "Gaizka Garitano", "1975-07-09", "Athletic Club", "Bilbao", "4-3-3",
			Mentalidad.Equilibrada);
	static Estadio es = new Estadio(1, "San Mames", 53289, 2013, "Bilbao");
	static Feedback f = new Feedback(1, u.getCorreo(), 5, Recomendacion.si, "opinion");
	static Jugador j = new Jugador(1, "Alex Berenguer", "1993-10-01", "Athletic", "Bilbao", Posicion.Delantero, 8, 0,
			182, 81, PieFav.Diestro, 84, "Jugador con desvorde.", 0);

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

	@BeforeClass
	public static void testConnect() throws DBManagerException {
		DBManager.connect();
	}

	@BeforeClass
	public static void testDisconnect() throws DBManagerException {
		DBManager.disconnect();
	}

	@Test
	public void testExisteCorreo() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		assertTrue(DBManager.existeCorreo(correo_usuario));
	}

	@Before
	public void testRegistrarUsuario() throws DBManagerException {
		String nombre_usuario = u.getNombre();
		String correo_usuario = u.getCorreo();
		String contrasena_usuario = u.getContrasena();
		String fechaNac_usuario = u.getFechaNac();

		DBManager.registrarUsuario(nombre_usuario, correo_usuario, contrasena_usuario, fechaNac_usuario);
	}

	@Test
	public void testLogin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		String contrasena_usuario = u.getContrasena();

		assertTrue(DBManager.login(correo_usuario, contrasena_usuario));
	}

	@Test
	public void testEsAdmin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		assertFalse(DBManager.esAdmin(correo_usuario));

		String correoDeUnAdmin = "eneko.perez23@gmail.com";
		assertTrue(DBManager.esAdmin(correoDeUnAdmin));
	}

	@Test
	public void testCambiarAdmin() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		int nuevoValor_admin_usuario = 4;
		
		if (nuevoValor_admin_usuario != 1 || nuevoValor_admin_usuario != 0) {
			fail("Tiene que ser 0 o 1");
		}

		DBManager.cambiarAdmin(correo_usuario, nuevoValor_admin_usuario);
	}

	@After
	public void testEliminarUsuario() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		DBManager.eliminarUsuario(correo_usuario);
	}

	@Test
	public void testCambiarContrasena() throws DBManagerException {
		String correo_usuario = u.getCorreo();
		String nuevoValor_contrasena_usuario = "contrasena2";

		DBManager.cambiarContrasena(correo_usuario, nuevoValor_contrasena_usuario);
	}

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

	@Test
	public void testTodosLosCorreos() throws DBManagerException {
		ArrayList<String> actualArr = DBManager.todosLosCorreos();
		// ArrayList<String> expectedArr = new ArrayList<String>();
		// assertEquals(expectedArr, actualArr);

		// Poner todos los correos es ilogico, ademas si hay nuevos usuarios cambia el
		// orden y la cantidad. Lo dejo en *fail* para acordarnos de preguntarle que
		// hacer

		// fail();
	}

	@Test
	public void testRegistrarFeedback() throws DBManagerException {
		String correo_usuario = f.getUsuario();
		int valoracion_feedback = f.getValoracion();
		String valoracion_feedback2 = Integer.toString(valoracion_feedback);
		String recomendacion_feedback = f.getRecomendacion().toString();
		String opinion_feedback = f.getOpinion();

		DBManager.registrarFeedback(correo_usuario, valoracion_feedback2, recomendacion_feedback, opinion_feedback);
	}

	@Test
	public void testGetJugadoresPorPosicion() throws DBManagerException {
		String posicion_jugador = "Delantero"; // Por ejemplo
		ArrayList<String> actualArr = DBManager.getJugadoresPorPosicion(posicion_jugador);
		// ArrayList<String> expectedArr = new ArrayList<String>();
		// assertEquals(expectedArr, actualArr);

		// Poner todos los jugadores es ilogico, ademas si hay nuevos jugadores cambia
		// el
		// orden y la cantidad. Lo dejo en *fail* para acordarnos de preguntarle que
		// hacer

		// fail();
	}

	@Test
	public void testIdUsuario() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		// assertEquals(DBManager.getIdUsuario(correo_usuario), u.getId());

		// fail();
	}

	@Test
	public void testIdJugador() throws DBManagerException {
		// String nombre_jugador = j.

		// assertEquals(DBManager.getIdUsuario(correo_usuario), u.getId());

		// fail();
	}

	@Test
	public void testVotar() throws DBManagerException {
		/*
		 * int usuario_usuarioVotacion = int delanteroVotado_usuarioVotacion = int
		 * centrocampistaVotado_usuarioVotacion = int defensaVotado_usuarioVotacion =
		 * int porteroVotado_usuarioVotacion =
		 */

		// fail();
	}

	@Test
	public void testContarJugadores() throws DBManagerException {
		// Contar todos los jugadores es ilogico, ademas si hay nuevos jugadores cambia
		// el numero. Lo dejo en *fail* para acordarnos de preguntarle que hacer

		// fail();
	}

	@Test
	public void testContarVotosPorJugador() throws DBManagerException {
		// necesitamos jugadores

		// fail();
	}

	@Test
	public void testActualizarVotos() throws DBManagerException {
		// necesitamos jugadores

		// fail();
	}

	@Test
	public void testCountToft() throws DBManagerException {
		// necesitamos jugadores

		// fail();
	}

	@Test
	public void testGetMasVotados() throws DBManagerException {
		// necesitamos jugadores

		// fail();
	}

	@Test
	public void testToft() throws DBManagerException {
		// necesitamos jugadores

		// fail();
	}

	@Test
	public void testToftNombres() throws DBManagerException {
		// necesitamos jugadores

		// fail();
	}

	@Test
	public void testGetCiudades() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetClubes() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetEntrenadores() throws DBManagerException {

		// fail();
	}

	// Métodos Entrenador
	@Test
	public void testNombreEntrenador() throws DBManagerException {
		assertEquals(e.getNombre(), DBManager.nombreEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void testFechaNacimiento() throws DBManagerException {
		assertEquals(e.getFechaNac(), DBManager.fechaNacimiento("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void testClubEntrenador() throws DBManagerException {
		assertEquals(e.getClub(), DBManager.clubEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void testCiudadEntrenador() throws DBManagerException {
		assertEquals(e.getCiudad(), DBManager.ciudadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void testFormacionEntrenador() throws DBManagerException {
		assertEquals(e.getFormacion(), DBManager.formacionEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void testMentalidadEntrenador() throws DBManagerException {
		assertEquals("Defensiva", DBManager.mentalidadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}
	// Fin Métodos Entrenador

	@Test
	public void testGetEstadios() throws DBManagerException {

		// fail();
	}

	// Métodos Estadio
	@Test
	public void testNombreEstadio() throws DBManagerException {
		assertEquals(es.getNombre(), DBManager.nombreEstadio("San Mames", "wikifutbolschema"));
	}

	@Test
	public void testAforoEstadio() throws DBManagerException {
		assertEquals(es.getAforo(), DBManager.aforoEstadio("San Mames", "wikifutbolschema"));
	}

	@Test
	public void testAnyoEstadio() throws DBManagerException {
		assertEquals(es.getAnyoCreacion(), DBManager.anyoEstadio("San Mames", "wikifutbolschema"));
	}

	@Test
	public void testCiudadEstadio() throws DBManagerException {
		assertEquals(es.getCiudad(), DBManager.ciudadEstadio("San Mames", "wikifutbolschema"));
	}
	// Fin Métodos Estadio

	@Test
	public void testGetFeedbacks() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetJugadores() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetPaises() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetTeamOfTheYear_view() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetTeamOfTheYear() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetUsuarios() throws DBManagerException {

		// fail();
	}

	@Test
	public void testGetUsuarioVotaciones() throws DBManagerException {

		// fail();
	}

	@Test
	public void testVerColumnas() throws DBManagerException {

		// fail();
	}

	@Test
	public void testData() throws DBManagerException {

		// fail();
	}

	@Test
	public void testCambiarDatos() throws DBManagerException {

		// fail();
	}

	@Test
	public void testCambiarDatosDesdeJTable() throws DBManagerException {

		// fail();
	}

	/*
	 * @Test public void getClubes() throws DBManagerException { ArrayList<String>
	 * equipos = new ArrayList<>(); equipos.
	 * add("id=1, nombre=Athletic Club, ciudad=Bilbao, estadio=San Mames, anyoCreacion=1898, palmares=0, entrenador=Gaizka Garitano"
	 * ); equipos.
	 * add("id=2, nombre=Real Sociedad, ciudad=San Sebastian, estadio=Reale Arena, anyoCreacion=1908, palmares=0, entrenador=Imanol Alguacil"
	 * ); equipos.
	 * add("id=3, nombre=Villarreal, ciudad=Villarreal, estadio=Estadio de la Ceramica, anyoCreacion=1923, palmares=0, entrenador=Unai Emery"
	 * ); equipos.
	 * add("id=4, nombre=Real Madrid, ciudad=Madrid, estadio=Santiago Bernabeu, anyoCreacion=1902, palmares=0, entrenador=Zinedine Zidane"
	 * ); equipos.
	 * add("id=5, nombre=Huesca, ciudad=Huesca, estadio=El Alcoraz, anyoCreacion=1960, palmares=0, entrenador=Michel"
	 * ); equipos.
	 * add("id=6, nombre=Elche, ciudad=Elche, estadio=Estadio Martinez Valero, anyoCreacion=1922, palmares=0, entrenador=Jorge Almiron"
	 * ); equipos.
	 * add("id=7, nombre=Getafe, ciudad=Getafe, estadio=Coliseum Alfonso Perez, anyoCreacion=1983, palmares=0, entrenador=Jose Bordalas"
	 * ); equipos.
	 * add("id=8, nombre=Cadiz, ciudad=Cadiz, estadio=Estadio Ramon de Carranza, anyoCreacion=1909, palmares=0, entrenador=Alvaro Cervera"
	 * ); equipos.
	 * add("id=9, nombre=Granada, ciudad=Granada, estadio=Estadio Nuevo Los Carmenes, anyoCreacion=1931, palmares=0, entrenador=Diego Martinez"
	 * ); equipos.
	 * add("id=10, nombre=Betis, ciudad=Sevilla, estadio=Benito Villamarin, anyoCreacion=1907, palmares=0, entrenador=Manuel Pellegrini"
	 * ); equipos.
	 * add("id=11, nombre=Atl. Madrid, ciudad=Madrid, estadio=Wanda Metropolitano, anyoCreacion=1903, palmares=0, entrenador=Diego Simeone"
	 * ); equipos.
	 * add("id=12, nombre=Barcelona, ciudad=Barcelona, estadio=Camp Nou, anyoCreacion=1899, palmares=0, entrenador=Ronald Koeman"
	 * ); equipos.
	 * add("id=13, nombre=Sevilla, ciudad=Sevilla, estadio=Ramon Sanchez Pizjuan, anyoCreacion=1890, palmares=0, entrenador=Julen Lopetegui"
	 * ); equipos.
	 * add("id=14, nombre=Celta, ciudad=Vigo, estadio=Municipal de Balaidos, anyoCreacion=1927, palmares=0, entrenador=Oscar Garcia"
	 * ); equipos.
	 * add("id=15, nombre=Alaves, ciudad=Vitoria-Gasteiz, estadio=Estadio de Mendizorroza, anyoCreacion=1921, palmares=0, entrenador=Pablo Machin"
	 * ); equipos.
	 * add("id=16, nombre=Levante, ciudad=Valencia, estadio=Cuidad de Valencia, anyoCreacion=1908, palmares=0, entrenador=Paco Lopez"
	 * ); equipos.
	 * add("id=17, nombre=Valladolid, ciudad=Valladolid, estadio=Jose Zorrilla, anyoCreacion=1928, palmares=0, entrenador=Sergio"
	 * ); equipos.
	 * add("id=18, nombre=Eibar, ciudad=Eibar, estadio=Estadio Municipal de Ipurua, anyoCreacion=1940, palmares=0, entrenador=Jose Luis Mendilibar"
	 * ); equipos.
	 * add("id=19, nombre=Valencia, ciudad=Valencia, estadio=Mestalla, anyoCreacion=1919, palmares=0, entrenador=Javi Gracia"
	 * ); equipos.
	 * add("id=20, nombre=Osasuna, ciudad=Pamplona, estadio=Estadio El Sadar, anyoCreacion=1920, palmares=0, entrenador=Jagoba Arrasate"
	 * );
	 *
	 *
	 * assertEquals(equipos, DBManager.getClubes()); }
	 */
}
