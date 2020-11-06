package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la clase Estadio
 * @author sergi
 *
 */
public class EstadioTest {

	private Estadio e;

	/**
	 * Crea un nuevo estadio
	 */
	@Before
	public void setUp() {
		e = new Estadio(1, "San Mam�s", 20000, 1900, "Bilbao");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del estadio
	 */
	@Test
	public void testId() {
		e.setId(1);
		assertEquals(1, e.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre del
	 * estadio
	 */
	@Test
	public void testNombre() {
		e.setNombre("San Mam�s");
		assertEquals("San Mam�s", e.getNombre());
	}

	/**
	 * Comprueba que getAforo() funciona correctamente devolviendo el aforo del
	 * estadio
	 */
	@Test
	public void testAforo() {
		e.setAforo(2000);
		assertEquals(2000, e.getAforo());
	}

	/**
	 * Comprueba que getAnyoCreacion() funciona correctamente devolviendo el
	 * anyoCreacion del estadio
	 */
	@Test
	public void testAnyoCreacion() {
		e.setAnyoCreacion(1900);
		assertEquals(1900, e.getAnyoCreacion());
	}

	/**
	 * Comprueba que getCiudad() funciona correctamente devolviendo la ciudad del
	 * estadio
	 */
	@Test
	public void testCiudad() {
		e.setCiudad("Bilbao");
		assertEquals("Bilbao", e.getCiudad());
	}

	/**
	 * Comprueba el método toString() de la clase Estadio
	 */
	@Test
	public void metodoToString() {
		e.setId(1);
		e.setNombre("San Mam�s");
		e.setAforo(20000);
		e.setAnyoCreacion(1900);
		e.setCiudad("Bilbao");

		System.out.println(e.toString());

		assertEquals("Estadio [id=1, nombre=San Mam�s, aforo=20000, anyoCreacion=1900, ciudad=Bilbao]", e.toString());
	}

}
