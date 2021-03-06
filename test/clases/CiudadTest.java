package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la clase Ciudad
 *
 * @author sergi
 *
 */
public class CiudadTest {

	private Ciudad c;

	/**
	 * Crea una nueva ciudad
	 */
	@Before
	public void setUp() {
		c = new Ciudad(1, "Bilbao", "Espa�a", 5, "bilbaino", "Vizcaya", "País Vasco");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id de la ciudad
	 */
	@Test
	public void testId() {
		c.setId(1);
		assertEquals(1, c.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre de la
	 * ciudad
	 */
	@Test
	public void testNombre() {

		c.setNombre("Bilbao");
		assertEquals("Bilbao", c.getNombre());
	}

	/**
	 * Comprueba que getPais() funciona correctamente devolviendo el pais de la
	 * ciudad
	 */
	@Test
	public void tesetPoblacion() {

		c.setPoblacion(5);
		assertEquals(5, c.getPoblacion());
	}

	@Test
	public void testGentilicio() {

		c.setGentilicio("bilbaino");
		assertEquals("bilbaino", c.getGentilicio());
	}

	@Test
	public void testProvincia() {

		c.setProvincia("Vizcaya");
		assertEquals("Vizcaya", c.getProvincia());
	}

	@Test
	public void testPais() {

		c.setPais("Espa�a");
		assertEquals("Espa�a", c.getPais());
	}

	/**
	 * Comprueba el método toString() de la clase Ciudad
	 */
	@Test
	public void metodoToString() {

		c.setId(1);
		c.setNombre("Bilbao");
		c.setPais("Espa�a");
		c.setGentilicio("bilbaino");
		c.setPoblacion(5);
		c.setProvincia("Vizcaya");
		c.setComAutonoma("País Vasco");
		System.out.println(c.toString());

		assertEquals(
				"Ciudad [id=1, nombre=Bilbao, pais=Espa�a, poblacion=5, gentilicio=bilbaino, provincia=Vizcaya, comAutonoma=País Vasco]",
				c.toString());
	}
}
