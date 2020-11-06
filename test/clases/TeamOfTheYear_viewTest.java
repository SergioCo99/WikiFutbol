package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.TeamOfTheYear_view.Posicion;

public class TeamOfTheYear_viewTest {

	private TeamOfTheYear_view t;

	/**
	 * Crea una nueva Team of the year view
	 */
	@Before
	public void setUp() {
		t = new TeamOfTheYear_view(2, 2, "Kenan Kodro", Posicion.Delantero, 0, 0);
	}

	/**
	 * Comprueba que getId_teamoftheyear() funciona correctamente devolviendo el id del team of the year
	 */
	@Test
	public void testIdTeam() {
		assertEquals(2, t.getId_teamoftheyear());
	}

	/**
	 * Comprueba que getId_jugador() funciona correctamente devolviendo el id del jugador
	 */
	@Test
	public void testIdJugador() {
		assertEquals(2, t.getId_jugador());
	}

	/**
	 * Comprueba que getNombre_jugador() funciona correctamente devolviendo el nombre del jugador
	 */
	@Test
	public void testNombreJugador() {
		assertEquals("Kenan Kodro", t.getNombre_jugador());
	}

	/**
	 * Comprueba que getPosicion_jugador() funciona correctamente devolviendo la posicion del jugador
	 */
	@Test
	public void testPosicionJugador() {
		assertEquals(Posicion.Delantero, t.getPosicion_jugador());
	}

	/**
	 * Comprueba que getVoto_jugador() funciona correctamente devolviendo los votos del jugador
	 */
	@Test
	public void testVotoJugador() {
		assertEquals(0, t.getVoto_jugador());
	}

	/**
	 * Comprueba que getGoles_jugador() funciona correctamente devolviendo los goles del jugador
	 */
	@Test
	public void testGolesJugador() {
		assertEquals(0, t.getGoles_jugador());
	}

	/**
	 * Comprueba el método toString() de la cles TeamOfTheYear_view
	 */
	@Test
	public void metodoToString() {

		System.out.println(t.toString());

		assertEquals(
				"TeamOfTheYear_view [id_teamoftheyear=2, id_jugador=2, nombre_jugador=Kenan Kodro, posicion_jugador=Delantero, voto_jugador=0, goles_jugador=0]",
				t.toString());
	}
}
