package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la clase Club
 *
 * @author sergi
 *
 */
public class ClubTest {

	private Club a;

	/**
	 * Crea un nuevo Club
	 */
	@Before
	public void setUp() {
		a = new Club(1, "Athletic Club", "Bilbao", "San Mam�s", 1898, "2 Ligas", "Gaizka Garitano");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del club
	 */
	@Test
	public void testId() {
		a.setId(1);
		assertEquals(1, a.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre del
	 * club
	 */
	@Test
	public void testNombre() {
		a.setNombre("Athletic Club");
		assertEquals("Athletic Club", a.getNombre());
	}

	/**
	 * Comprueba que getCiudad() funciona correctamente devolviendo la ciudad del
	 * club
	 */
	@Test
	public void testCiudad() {
		a.setCiudad("Bilbao");
		assertEquals("Bilbao", a.getCiudad());
	}

	/**
	 * Comprueba que getEstadio() funciona correctamente devolviendo el estadio del
	 * club
	 */
	@Test
	public void testEstadio() {
		a.setEstadio("San Mam�s");
		assertEquals("San Mam�s", a.getEstadio());
	}

	/**
	 * Comprueba que getAnyoCreacion() funciona correctamente devolviendo el
	 * anyoCreacion del club
	 */
	@Test
	public void testAnyoCreacion() {
		a.setAnyoCreacion(1898);
		assertEquals(1898, a.getAnyoCreacion());
	}

	/**
	 * Comprueba que getPalmares() funciona correctamente devolviendo el palmares
	 * del club
	 */
	@Test
	public void testPalmares() {
		a.setPalmares("2 Ligas");
		assertEquals("2 Ligas", a.getPalmares());
	}

	/**
	 * Comprueba que getEntrenador() funciona correctamente devolviendo el
	 * entrenador del club
	 */
	@Test
	public void testEntrenador() {
		a.setEntrenador("Gaizka Garitano");
		assertEquals("Gaizka Garitano", a.getEntrenador());
	}

	/**
	 * Comprueba el método toString() de la clase Club
	 */
	@Test
	public void metodoToString() {
		a.setId(1);
		a.setNombre("Athletic Club");
		a.setCiudad("Bilbao");
		a.setEstadio("San Mam�s");
		a.setAnyoCreacion(1898);
		a.setPalmares("2 Ligas");
		a.setEntrenador("Gaizka Garitano");

		System.out.println(a.toString());
		assertEquals(
				"Club [id=1, nombre=Athletic Club, ciudad=Bilbao, estadio=San Mam�s, anyoCreacion=1898, palmares=2 Ligas, entrenador=Gaizka Garitano]",
				a.toString());
	}

}
