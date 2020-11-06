package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la clase TeamOfTheYear
 * @author sergi
 *
 */
public class TeamOfTheYearTest {

	private TeamOfTheYear t;

	/**
	 * Crea un team of the year
	 */
	@Before
	public void setUp() {
		t = new TeamOfTheYear(1, "Koikili");
	}

	/**
	 * Comprueba que getID() funciona correctamente devolviendo el id del team of
	 * the year
	 */
	@Test
	public void testId() {
		t.setId(1);
		assertEquals(1, t.getId());
	}

	/**
	 * Comprueba que getJugador() funciona correctamente devolviendo el jugador
	 */
	@Test
	public void testJugador() {

		t.setJugador("Koikili");
		assertEquals("Koikili", t.getJugador());
	}

	/**
	 * Comprueba el método toString() de la clase TeamOfTheYear
	 */
	@Test
	public void metodoToString() {

		t.setId(1);
		t.setJugador("Koikili");

		System.out.println(t.toString());

		assertEquals("TeamOfTheYear [id=1, jugador=Koikili]", t.toString());
	}
}
