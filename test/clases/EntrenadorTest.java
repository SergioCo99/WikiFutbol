package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Entrenador.Mentalidad;

public class EntrenadorTest {

	private Entrenador e;

	@Before
	public void setUp() {
		e = new Entrenador(1, "Gaizka Garitano", "1970", "Athletic Club", "Bilbao", "Escuela Albacete",
				Mentalidad.Defensiva);
	}

	@Test
	public void testId() {
		e.setId(1);
		assertEquals(1, e.getId());
	}

	@Test
	public void testNombre() {

		e.setNombre("Gaizka Garitano");
		assertEquals("Gaizka Garitano", e.getNombre());
	}

	@Test
	public void testAnyoNacimiento() {

		e.setFechaNac("1970");
		assertEquals("1970", e.getFechaNac());
	}

	@Test
	public void testClub() {

		e.setClub("Athletic Club");
		assertEquals("Athletic Club", e.getClub());
	}

	@Test
	public void testCiudad() {

		e.setCiudad("Bilbao");
		assertEquals("Bilbao", e.getCiudad());
	}

	@Test
	public void testFormacion() {

		e.setFormacion("Escuela Albacete");
		assertEquals("Escuela Albacete", e.getFormacion());
	}

	@Test
	public void testMentalidad() {

		e.setMentalidad(Mentalidad.Defensiva);
		assertEquals(Mentalidad.Defensiva, e.getMentalidad());
	}

	@Test
	public void metodoToString() {

		e.setId(1);
		e.setNombre("Gaizka Garitano");
		e.setFechaNac("1980");
		e.setClub("Athletic Club");
		e.setCiudad("Bilbao");
		e.setFormacion("Escuela Albacete");
		e.setMentalidad(Mentalidad.Defensiva);

		System.out.println(e.toString());

		assertEquals("Entrenador [id=1, nombre=Gaizka Garitano, fechaNac=1980, club=Athletic Club, ciudad=Bilbao,"
				+ " formacion=Escuela Albacete, mentalidad=Defensiva]", e.toString());
	}
}
