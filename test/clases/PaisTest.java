package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la clase Pais
 *
 * @author sergi
 *
 */
public class PaisTest {

	private Pais p;

	/**
	 * Crea un nuevo pais
	 */
	@Before
	public void setUp() {
		p = new Pais(1, "España", "Madrid", "español", "castellano");
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

		p.setNombre("España");
		assertEquals("España", p.getNombre());
	}

	@Test
	public void testCapital() {

		p.setCapital("Madrid");
		assertEquals("Madrid", p.getCapital());
	}

	@Test
	public void testGentilicio() {

		p.setGentilicio("español");
		assertEquals("español", p.getGentilicio());
	}

	@Test
	public void testIdioma() {

		p.setCapital("castellano");
		assertEquals("castellano", p.getIdioma());
	}

	/**
	 * Comprueba el método toString() de la clase Pais
	 */
	@Test
	public void metodoToString() {

		p.setId(1);
		p.setNombre("España");
		p.setCapital("Madrid");
		p.setGentilicio("español");
		p.setIdioma("castellano");

		System.out.println(p.toString());

		assertEquals("Pais [id=1, nombre=España, capital=Madrid, gentilicio=español, idioma=castellano]", p.toString());
	}
}
