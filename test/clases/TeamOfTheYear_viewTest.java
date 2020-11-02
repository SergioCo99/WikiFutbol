package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.TeamOfTheYear_view.Posicion;

public class TeamOfTheYear_viewTest {

	private TeamOfTheYear_view t;

	@Before
	public void setUp() {
		t = new TeamOfTheYear_view(2, 2, "Kenan Kodro", Posicion.Delantero, 0, 0);
	}

	@Test
	public void testIdTeam() {
		assertEquals(2, t.getId_teamoftheyear());
	}

	@Test
	public void testIdJugador() {
		assertEquals(2, t.getId_jugador());
	}

	@Test
	public void testNombreJugador() {
		assertEquals("Kenan Kodro", t.getNombre_jugador());
	}

	@Test
	public void testPosicionJugador() {
		assertEquals(Posicion.Delantero, t.getPosicion_jugador());
	}

	@Test
	public void testVotoJugador() {
		assertEquals(0, t.getVoto_jugador());
	}

	@Test
	public void testGolesJugador() {
		assertEquals(0, t.getGoles_jugador());
	}

	@Test
	public void metodoToString() {

		System.out.println(t.toString());

		assertEquals(
				"TeamOfTheYear_view [id_teamoftheyear=2, id_jugador=2, nombre_jugador=Kenan Kodro, posicion_jugador=Delantero, voto_jugador=0, goles_jugador=0]",
				t.toString());
	}
}
