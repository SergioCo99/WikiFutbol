package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Club;
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
			182, 81, PieFav.Diestro, 84, "Jugador con desborde", 0);

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
		int nuevoValor_admin_usuario = 1;

		if (nuevoValor_admin_usuario != 1 && nuevoValor_admin_usuario != 0) {
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
		/*
		 * Poner todos los correos es ilogico, ademas si hay nuevos usuarios cambia el
		 * orden y la cantidad. Lo dejo en *fail* para acordarnos de preguntarle que
		 * hacer
		 */

		fail();
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
		/*
		 * Poner todos los jugadores es ilogico, ademas si hay nuevos jugadores cambia
		 * el orden y la cantidad. Lo dejo en *fail* para acordarnos de preguntarle que
		 * hacer
		 */

		fail();
	}

	@Test
	public void testIdUsuario() throws DBManagerException {
		/*
		 * id del usuario de prueba cambia, preguntarle si usar el de prueba o uno ya
		 * existente. Lo dejo en *fail* para acordarnos de preguntarle que hacer
		 */

		// String correo_usuario = u.getCorreo();
		// assertEquals(DBManager.getIdUsuario(correo_usuario), u.getId());

		String correo_usuario = "eneko.perez23@gmail.com";

		// u.setid(5);
		assertEquals(5, DBManager.getIdUsuario(correo_usuario));

		fail();
	}

	@Test
	public void testIdJugador() throws DBManagerException {
		/*
		 * no convence del todo, Lo dejo en *fail* para acordarnos de preguntarle que
		 * hacer
		 */

		String nombre_jugador = j.getNombre();

		assertEquals(j.getId(), DBManager.getIdJugador(nombre_jugador));

		// String nombre_jugador = "Alex Berenguer";
		// assertEquals(1, DBManager.getIdJugador(nombre_jugador));

		fail();
	}

	@Test
	public void testVotar() throws DBManagerException {
		/*
		 * preguntarle como comprobar (test) que en una tabla han cambiado valores sin
		 * tener que hacer metodos nuevos de BD
		 */

		fail();
	}

	@Test
	public void testContarJugadores() throws DBManagerException {
		// Contar todos los jugadores es ilogico, ademas si hay nuevos jugadores cambia
		// el numero. Lo dejo en *fail* para acordarnos de preguntarle que hacer

		// no tiene/necesita connect()

		fail();
	}

	@Test
	public void testContarVotosPorJugador() throws DBManagerException {
		// no tiene/necesita connect()

		fail();
	}

	@Test
	public void testActualizarVotos() throws DBManagerException {
		/*
		 * preguntarle como comprobar (test) que en una tabla han cambiado valores sin
		 * tener que hacer metodos nuevos de BD
		 */

		fail();
	}

	@Test
	public void testCountToft() throws DBManagerException {
		// no tiene/necesita connect()

		fail();
	}

	@Test
	public void testGetMasVotados() throws DBManagerException {
		// no tiene/necesita connect()

		fail();
	}

	@Test
	public void testToft() throws DBManagerException {
		/*
		 * es un array de SIEMPRE 11 valores [0 - 10] pero que puede cambiar el valor
		 * interno. Preguntar.
		 */

		fail();
	}

	@Test
	public void testToftNombres() throws DBManagerException {
		/* depende del anterior, y por lo tanto mismo problema */

		fail();
	}

	@Test
	public void testGetCiudades() throws DBManagerException {
		fail();
	}

	@Test
	public void testGetClubes() throws DBManagerException {

		fail();

		assertEquals(
				"Club [id=1, nombre=Athletic Club, ciudad=Bilbao, estadio=San Mames, anyoCreacion=1898, palmares=25, entrenador=Sergio Co], Club [id=2, nombre=Real Sociedad, ciudad=San Sebastian, estadio=Reale Arena, anyoCreacion=1908, palmares=5, entrenador=Imanol Alguacil], Club [id=3, nombre=Villarreal, ciudad=Villarreal, estadio=Estadio de la Ceramica, anyoCreacion=1923, palmares=2, entrenador=Unai Emery], Club [id=4, nombre=Real Madrid, ciudad=Madrid, estadio=Santiago Bernabeu, anyoCreacion=1902, palmares=34, entrenador=Zinedine Zidane], Club [id=5, nombre=Huesca, ciudad=Huesca, estadio=El Alcoraz, anyoCreacion=1960, palmares=1, entrenador=Michel], Club [id=6, nombre=Elche, ciudad=Elche, estadio=Estadio Martinez Valero, anyoCreacion=1922, palmares=2, entrenador=Jorge Almiron], Club [id=7, nombre=Getafe, ciudad=Getafe, estadio=Coliseum Alfonso Perez, anyoCreacion=1983, palmares=4, entrenador=Jose Bordalas], Club [id=8, nombre=Cadiz, ciudad=Cadiz, estadio=Estadio Ramon de Carranza, anyoCreacion=1909, palmares=6, entrenador=Alvaro Cervera], Club [id=9, nombre=Granada, ciudad=Granada, estadio=Estadio Nuevo Los Carmenes, anyoCreacion=1931, palmares=5, entrenador=Diego Martinez], Club [id=10, nombre=Betis, ciudad=Sevilla, estadio=Benito Villamarin, anyoCreacion=1907, palmares=1, entrenador=Manuel Pellegrini], Club [id=11, nombre=Atl. Madrid, ciudad=Madrid, estadio=Wanda Metropolitano, anyoCreacion=1903, palmares=24, entrenador=Diego Simeone], Club [id=12, nombre=Barcelona, ciudad=Barcelona, estadio=Camp Nou, anyoCreacion=1899, palmares=31, entrenador=Ronald Koeman], Club [id=13, nombre=Sevilla, ciudad=Sevilla, estadio=Ramon Sanchez Pizjuan, anyoCreacion=1890, palmares=19, entrenador=Julen Lopetegui], Club [id=14, nombre=Celta, ciudad=Vigo, estadio=Municipal de Balaidos, anyoCreacion=1927, palmares=5, entrenador=Oscar Garcia], Club [id=15, nombre=Alaves, ciudad=Vitoria-Gasteiz, estadio=Estadio de Mendizorroza, anyoCreacion=1921, palmares=1, entrenador=Pablo Machin], Club [id=16, nombre=Levante, ciudad=Valencia, estadio=Cuidad de Valencia, anyoCreacion=1908, palmares=5, entrenador=Paco Lopez], Club [id=17, nombre=Valladolid, ciudad=Valladolid, estadio=Jose Zorrilla, anyoCreacion=1928, palmares=2, entrenador=Sergio], Club [id=18, nombre=Eibar, ciudad=Eibar, estadio=Estadio Municipal de Ipurua, anyoCreacion=1940, palmares=3, entrenador=Jose Luis Mendilibar], Club [id=19, nombre=Valencia, ciudad=Valencia, estadio=Mestalla, anyoCreacion=1919, palmares=15, entrenador=Javi Gracia], Club [id=20, nombre=Osasuna, ciudad=Pamplona, estadio=Estadio El Sadar, anyoCreacion=1920, palmares=0, entrenador=Jagoba Arrasate]",
				DBManager.getClubes());
		// fail();

	}

	@Test
	public void testGetEntrenadores() throws DBManagerException {

		fail();
	}

	// M�todos Entrenador
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
	// Fin M�todos Entrenador

	@Test
	public void testGetEstadios() throws DBManagerException {

		fail();
	}

	// M�todos Estadio
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
	// Fin M�todos Estadio

	@Test
	public void testGetFeedbacks() throws DBManagerException {

		fail();
	}

	@Test
	public void testGetJugadores() throws DBManagerException {

		fail();
	}

	@Test
	public void testGetPaises() throws DBManagerException {
		// DA LO MISMO PERO DA ERROR

		fail();

		assertEquals(
				"[Pais [id=1, nombre=Espa�a], Pais [id=2, nombre=Francia], Pais [id=3, nombre=Guinea Ecuatorial], Pais [id=4, nombre=Chile], Pais [id=5, nombre=Argentina], Pais [id=6, nombre=Paises Bajos]]",
				DBManager.getPaises());
		// fail();
	}

	@Test
	public void testGetTeamOfTheYear_view() throws DBManagerException {

		fail();
	}

	@Test
	public void testGetTeamOfTheYear() throws DBManagerException {

		fail();
	}

	@Test
	public void testGetUsuarios() throws DBManagerException {

		fail();

		assertEquals(
				"[Usuario [id=1, nombre=sergio, contrasena=sergio@gmail.com, correo=a, admin=1, fechaNac=1999-06-23], Usuario [id=2, nombre=lopez, contrasena=lopez@gmail.com, correo=b, admin=0, fechaNac=1999-06-24], Usuario [id=4, nombre=cogollos, contrasena=cogollos@gmail.com, correo=c, admin=0, fechaNac=1999-06-26], Usuario [id=5, nombre=Eneko, contrasena=eneko.perez23@gmail.com, correo=12345, admin=1, fechaNac=2020-10-01], Usuario [id=231, nombre=hola, contrasena=hola@gmail.com, correo=hola, admin=0, fechaNac=2020-11-01]",
				DBManager.getUsuarios());
		// fail();

	}

	@Test
	public void testGetUsuarioVotaciones() throws DBManagerException {

		fail();
	}

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

		String tabla2 = "estadio";
		assertEquals(DBManager.verColumnas(tabla2), actualEstadio);

		// hacer de todas las tablas?? o con dos de prueba ya valdria?
		fail();
	}

	@Test
	public void testData() throws DBManagerException {
		/*
		 * es un metodo que devuelve en un array 2D de object todos los datos de una
		 * tabla. Preguntarle �como hacer un test de eso si los datos de la tabla puede
		 * que sean modificados (incluso insertando valores nuevos o siendo borrados
		 * otros)?
		 */

		fail();
	}

	@Test
	public void testCambiarDatos() throws DBManagerException {

		/*
		 * preguntarle como comprobar (test) que en una tabla han cambiado valores sin
		 * tener que hacer metodos nuevos de BD
		 */

		fail();

		Club e1 = new Club(1, "Athletic Club", "Bilbao", "San Mames", 1898, "25", "Gaizka Garitano");
		DBManager.cambiarDatos(
				"UPDATE `wikifutbolschema`.`pruebaclub` SET `nombre_club` = 'Athletic Clu' WHERE (`id_club` = '1');");
		assertNotEquals(e1.getNombre(), "Athletic Clu");
		// fail();
	}

	@Test
	public void testCambiarDatosDesdeJTable() throws DBManagerException {

		/*
		 * preguntarle como comprobar (test) que en una tabla han cambiado valores sin
		 * tener que hacer metodos nuevos de BD
		 */

		Club e1 = new Club(1, "Athletic Club", "Bilbao", "San Mames", 1898, "25", "Gaizka Garitano");

		DBManager.cambiarDatosDesdeJTable("pruebaclub", "anoCreacion_club", 2000, 1);
		assertEquals(2000, e1.getAnyoCreacion());

		fail();
	}

	@Test
	public void testGetJugadoresPorEquipo() throws DBManagerException {
		// no se si hacerlo asi (?)
		ArrayList<String> arr = DBManager.getJugadoresPorEquipo("Athletic Club");

		System.out.println(DBManager.getJugadoresPorEquipo("Athletic Club"));
		assertEquals(15, arr.size()); // tama�o
		assertEquals("Alex Berenguer", arr.get(0)); // primero, ojo al orden que es abc
		assertEquals("Yuri Berchiche", arr.get(14)); // ultimo, ojo al orden que es abc

		fail();
	}

	@Test
	public void testNumeroDeFilasEnUnaTabla() throws DBManagerException {
		for (String table : database.DBManager.verTablas()) {
			DBManager.numeroDeFilasEnUnaTabla(table);

			if (table.equals("pais")) {
				assertEquals(6 /* contandolo a dedo en la BD */, DBManager.numeroDeFilasEnUnaTabla(table));
			} // CON TODOS? Y SI SE A�ADEN FILAS???
		}

		// fail();
	}

	/*
	 * @Test public void getClubes() throws DBManagerException { ArrayList<String>
	 * equipos = new ArrayList<>(); equipos.
	 * add("id=1, nombre=Athletic Club, ciudad=Bilbao, estadio=San Mames, anyoCreacion=1898, palmares=0, entrenador=Gaizka Garitano, id=2, nombre=Real Sociedad, ciudad=San Sebastian, estadio=Reale Arena, anyoCreacion=1908, palmares=0, entrenador=Imanol Alguacil, "
	 * +
	 * "id=3, nombre=Villarreal, ciudad=Villarreal, estadio=Estadio de la Ceramica, anyoCreacion=1923, palmares=0, entrenador=Unai Emery, "
	 * +
	 * "id=4, nombre=Real Madrid, ciudad=Madrid, estadio=Santiago Bernabeu, anyoCreacion=1902, palmares=0, entrenador=Zinedine Zidane, "
	 * +
	 * "id=5, nombre=Huesca, ciudad=Huesca, estadio=El Alcoraz, anyoCreacion=1960, palmares=0, entrenador=Michel,"
	 * +
	 * "id=6, nombre=Elche, ciudad=Elche, estadio=Estadio Martinez Valero, anyoCreacion=1922, palmares=0, entrenador=Jorge Almiron, "
	 * +
	 * "id=7, nombre=Getafe, ciudad=Getafe, estadio=Coliseum Alfonso Perez, anyoCreacion=1983, palmares=0, entrenador=Jose Bordalas, "
	 * +
	 * "id=8, nombre=Cadiz, ciudad=Cadiz, estadio=Estadio Ramon de Carranza, anyoCreacion=1909, palmares=0, entrenador=Alvaro Cervera, "
	 * +
	 * "id=9, nombre=Granada, ciudad=Granada, estadio=Estadio Nuevo Los Carmenes, anyoCreacion=1931, palmares=0, entrenador=Diego Martinez, "
	 * +
	 * "id=10, nombre=Betis, ciudad=Sevilla, estadio=Benito Villamarin, anyoCreacion=1907, palmares=0, entrenador=Manuel Pellegrini, "
	 * +
	 * "id=11, nombre=Atl. Madrid, ciudad=Madrid, estadio=Wanda Metropolitano, anyoCreacion=1903, palmares=0, entrenador=Diego Simeone, "
	 * +
	 * "id=12, nombre=Barcelona, ciudad=Barcelona, estadio=Camp Nou, anyoCreacion=1899, palmares=0, entrenador=Ronald Koeman, "
	 * +
	 * "id=13, nombre=Sevilla, ciudad=Sevilla, estadio=Ramon Sanchez Pizjuan, anyoCreacion=1890, palmares=0, entrenador=Julen Lopetegui, "
	 * +
	 * "id=14, nombre=Celta, ciudad=Vigo, estadio=Municipal de Balaidos, anyoCreacion=1927, palmares=0, entrenador=Oscar Garcia, "
	 * +
	 * "id=15, nombre=Alaves, ciudad=Vitoria-Gasteiz, estadio=Estadio de Mendizorroza, anyoCreacion=1921, palmares=0, entrenador=Pablo Machin,"
	 * +
	 * "id=16, nombre=Levante, ciudad=Valencia, estadio=Cuidad de Valencia, anyoCreacion=1908, palmares=0, entrenador=Paco Lopez, "
	 * +
	 * "id=17, nombre=Valladolid, ciudad=Valladolid, estadio=Jose Zorrilla, anyoCreacion=1928, palmares=0, entrenador=Sergio, "
	 * +
	 * "id=18, nombre=Eibar, ciudad=Eibar, estadio=Estadio Municipal de Ipurua, anyoCreacion=1940, palmares=0, entrenador=Jose Luis Mendilibar, "
	 * +
	 * "id=19, nombre=Valencia, ciudad=Valencia, estadio=Mestalla, anyoCreacion=1919, palmares=0, entrenador=Javi Gracia, "
	 * +
	 * "id=20, nombre=Osasuna, ciudad=Pamplona, estadio=Estadio El Sadar, anyoCreacion=1920, palmares=0, entrenador=Jagoba Arrasate"
	 * );
	 * 
	 * assertEquals("id=1, nombre=Athletic Club, ciudad=Bilbao, estadio=San Mames, anyoCreacion=1898, palmares=0, entrenador=Gaizka Garitano, id=2, nombre=Real Sociedad, ciudad=San Sebastian, estadio=Reale Arena, anyoCreacion=1908, palmares=0, entrenador=Imanol Alguacil, id=3, nombre=Villarreal, ciudad=Villarreal, estadio=Estadio de la Ceramica, anyoCreacion=1923, palmares=0, entrenador=Unai Emery, id=4, nombre=Real Madrid, ciudad=Madrid, estadio=Santiago Bernabeu, anyoCreacion=1902, palmares=0, entrenador=Zinedine Zidane, id=5, nombre=Huesca, ciudad=Huesca, estadio=El Alcoraz, anyoCreacion=1960, palmares=0, entrenador=Michel,id=6, nombre=Elche, ciudad=Elche, estadio=Estadio Martinez Valero, anyoCreacion=1922, palmares=0, entrenador=Jorge Almiron, id=7, nombre=Getafe, ciudad=Getafe, estadio=Coliseum Alfonso Perez, anyoCreacion=1983, palmares=0, entrenador=Jose Bordalas, id=8, nombre=Cadiz, ciudad=Cadiz, estadio=Estadio Ramon de Carranza, anyoCreacion=1909, palmares=0, entrenador=Alvaro Cervera, id=9, nombre=Granada, ciudad=Granada, estadio=Estadio Nuevo Los Carmenes, anyoCreacion=1931, palmares=0, entrenador=Diego Martinez, id=10, nombre=Betis, ciudad=Sevilla, estadio=Benito Villamarin, anyoCreacion=1907, palmares=0, entrenador=Manuel Pellegrini, id=11, nombre=Atl. Madrid, ciudad=Madrid, estadio=Wanda Metropolitano, anyoCreacion=1903, palmares=0, entrenador=Diego Simeone, id=12, nombre=Barcelona, ciudad=Barcelona, estadio=Camp Nou, anyoCreacion=1899, palmares=0, entrenador=Ronald Koeman, id=13, nombre=Sevilla, ciudad=Sevilla, estadio=Ramon Sanchez Pizjuan, anyoCreacion=1890, palmares=0, entrenador=Julen Lopetegui, id=14, nombre=Celta, ciudad=Vigo, estadio=Municipal de Balaidos, anyoCreacion=1927, palmares=0, entrenador=Oscar Garcia, id=15, nombre=Alaves, ciudad=Vitoria-Gasteiz, estadio=Estadio de Mendizorroza, anyoCreacion=1921, palmares=0, entrenador=Pablo Machin,id=16, nombre=Levante, ciudad=Valencia, estadio=Cuidad de Valencia, anyoCreacion=1908, palmares=0, entrenador=Paco Lopez, id=17, nombre=Valladolid, ciudad=Valladolid, estadio=Jose Zorrilla, anyoCreacion=1928, palmares=0, entrenador=Sergio, id=18, nombre=Eibar, ciudad=Eibar, estadio=Estadio Municipal de Ipurua, anyoCreacion=1940, palmares=0, entrenador=Jose Luis Mendilibar, id=19, nombre=Valencia, ciudad=Valencia, estadio=Mestalla, anyoCreacion=1919, palmares=0, entrenador=Javi Gracia, id=20, nombre=Osasuna, ciudad=Pamplona, estadio=Estadio El Sadar, anyoCreacion=1920, palmares=0, entrenador=Jagoba Arrasate"
	 * , DBManager.getClubes());
	 * 
	 * }
	 */
}
