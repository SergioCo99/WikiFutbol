package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TeamOfTheYearTest {

	private TeamOfTheYear t;

	@Before
	public void setUp() {
		t = new TeamOfTheYear(1, "Koikili");
	}

	@Test
	public void testId() {
		t.setId(1);
		assertEquals(1, t.getId());
	}

	@Test
	public void testJugador() {

		t.setJugador("Koikili");
		assertEquals("Koikili", t.getJugador());
	}

	@Test
	public void metodoToString() {

		t.setId(1);
		t.setJugador("Koikili");

		System.out.println(t.toString());

		assertEquals("TeamOfTheYear [id=1, jugador=Koikili]", t.toString());
	}
}
