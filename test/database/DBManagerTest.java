package database;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Entrenador;
import clases.Entrenador.Mentalidad;
import clases.Estadio;
import clases.Usuario;

public class DBManagerTest {

	static DBManager db = new DBManager();
	static Usuario u = new Usuario(1, "Nombre Test", "test", "test@gmail.com", 0, "1970-01-01");
	static Entrenador e = new Entrenador(1, "Gaizka Garitano", "1975-07-09", "Athletic Club", "Bilbao", "4-3-3",
			Mentalidad.Equilibrada);
	static Estadio es = new Estadio(1, "San Mames", 53289, 2013, "Bilbao");

	@BeforeClass
	public static void setUp() throws Exception {
		db = new DBManager();
		u = new Usuario(u.getId(), u.getNombre(), u.getContrasena(), u.getCorreo(), u.getAdmin(), u.getFechaNac());
		e = new Entrenador(e.getId(), e.getNombre(), e.getFechaNac(), e.getClub(), e.getCiudad(), e.getFormacion(),
				e.getMentalidad());
		es = new Estadio(es.getId(), es.getNombre(), es.getAforo(), es.getAnyoCreacion(), es.getCiudad());
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
		assertEquals(true, DBManager.existeCorreo(correo_usuario));
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

		DBManager.login(correo_usuario, contrasena_usuario);
	}

	@After
	public void testEliminarUsuario() throws DBManagerException {
		String correo_usuario = u.getCorreo();

		DBManager.eliminarUsuario(correo_usuario);
	}
	
	

	// M�todos Entrenador
	@Test
	public void nombreEntrenador() throws DBManagerException {
		assertEquals(e.getNombre(), DBManager.nombreEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void fechaNacimiento() throws DBManagerException {
		assertEquals(e.getFechaNac(), DBManager.fechaNacimiento("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void clubEntrenador() throws DBManagerException {
		assertEquals(e.getClub(), DBManager.clubEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void ciudadEntrenador() throws DBManagerException {
		assertEquals(e.getCiudad(), DBManager.ciudadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void formacionEntrenador() throws DBManagerException {
		assertEquals(e.getFormacion(), DBManager.formacionEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	@Test
	public void mentalidadEntrenador() throws DBManagerException {
		assertEquals("Equilibrada", DBManager.mentalidadEntrenador("Gaizka Garitano", "wikifutbolschema"));
	}

	// Fin M�todos Entrenador

	// M�todos Estadio
	@Test
	public void nombreEstadio() throws DBManagerException {
		assertEquals(es.getNombre(), DBManager.nombreEstadio("San Mames", "wikifutbolschema"));
	}

	@Test
	public void aforoEstadio() throws DBManagerException {
		assertEquals(es.getAforo(), DBManager.aforoEstadio("San Mames", "wikifutbolschema"));
	}

	@Test
	public void anyoEstadio() throws DBManagerException {
		assertEquals(es.getAnyoCreacion(), DBManager.anyoEstadio("San Mames", "wikifutbolschema"));
	}

	@Test
	public void ciudadEstadio() throws DBManagerException {
		assertEquals(es.getCiudad(), DBManager.ciudadEstadio("San Mames", "wikifutbolschema"));
	}

	// Fin M�todos Estadio

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
