package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Jugador.PieFav;
import clases.Jugador.Posicion;

public class JugadorTest {

	private Jugador j;

	@Before
	public void setUp() {
		j = new Jugador(1, "Koikili", "1980", "Athletic Club", "Bilbao", Posicion.Centrocampista, 14, 5, 180, 78,
				PieFav.Ambidiestro, 1, "Buen jugador", 1);
	}

	@Test
	public void testId() {
		j.setId(1);
		assertEquals(1, j.getId());
	}

	@Test
	public void testNombre() {

		j.setNombre("Koikili");
		assertEquals("Koikili", j.getNombre());
	}

	@Test
	public void testAnyoNacimiento() {

		j.setFechaNac("1980");
		assertEquals("1980", j.getFechaNac());
	}

	@Test
	public void testClub() {

		j.setClub("Athletic Club");
		assertEquals("Athletic Club", j.getClub());
	}

	@Test
	public void testCiudad() {

		j.setCiudad("Bilbao");
		assertEquals("Bilbao", j.getCiudad());
	}

	@Test
	public void testPosicion() {

		j.setPosicion(Posicion.Centrocampista);
		assertEquals(Posicion.Centrocampista, j.getPosicion());
	}

	@Test
	public void testDorsal() {

		j.setDorsal(14);
		assertEquals(14, j.getDorsal());
	}

	@Test
	public void testGoles() {

		j.setGoles(5);
		assertEquals(5, j.getGoles());
	}

	@Test
	public void testAltura() {

		j.setAltura(180);
		assertEquals(180, j.getAltura());
	}

	@Test
	public void testPeso() {

		j.setPeso(78);
		assertEquals(78, j.getPeso());
	}

	@Test
	public void testPieFav() {

		j.setPiefav(PieFav.Ambidiestro);
		assertEquals(PieFav.Ambidiestro, j.getPiefav());
	}

	@Test
	public void testValoracion() {

		j.setValoracion(1);
		assertEquals(1, j.getValoracion());
	}

	@Test
	public void testDescripcion() {

		j.setDescripcion("Buen jugador");
		assertEquals("Buen jugador", j.getDescripcion());
	}

	@Test
	public void testVoto() {

		j.setVoto(1);
		assertEquals(1, j.getVoto());
	}

	@Test
	public void metodoToString() {

		j.setId(1);
		j.setNombre("Gaizka Garitano");
		j.setFechaNac("1980");
		j.setClub("Athletic Club");
		j.setCiudad("Bilbao");
		j.setPosicion(Posicion.Centrocampista);
		j.setDorsal(14);
		j.setGoles(5);
		j.setAltura(180);
		j.setPeso(78);
		j.setPiefav(PieFav.Ambidiestro);
		j.setValoracion(1);
		j.setDescripcion("Buen jugador");
		j.setVoto(1);

		System.out.println(j.toString());

		assertEquals("Jugador [id=1, nombre=Gaizka Garitano, fechaNac=1980, club=Athletic Club, ciudad=Bilbao, "
				+ "posicion=Centrocampista, dorsal=14, goles=5, altura=180, peso=78, piefav=Ambidiestro, valoracion=1, "
				+ "descripcion=Buen jugador, voto=1]", j.toString());
	}
}
