package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PaisTest {

	private Pais p;

	/**
	 * Crea un nuevo pais
	 */
	@Before
	public void setUp() {
		p = new Pais(1, "Espa�a");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del pais
	 */
	@Test
	public void testId() {
		p.setId(1);
		assertEquals(1, p.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre del
	 * pais
	 */
	@Test
	public void testNombre() {

		p.setNombre("Espa�a");
		assertEquals("Espa�a", p.getNombre());
	}

	/**
	 * Comprueba el método toString() de la clase Pais
	 */
	@Test
	public void metodoToString() {

		p.setId(1);
		p.setNombre("Espa�a");

		System.out.println(p.toString());

		assertEquals("Pais [id=1, nombre=Espa�a]", p.toString());
	}
}
