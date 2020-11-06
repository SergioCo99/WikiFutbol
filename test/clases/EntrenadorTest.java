package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Entrenador.Mentalidad;

public class EntrenadorTest {

	private Entrenador e;

	/**
	 * Crea un nuevo entrenador
	 */
	@Before
	public void setUp() {
		e = new Entrenador(1, "Gaizka Garitano", "1970", "Athletic Club", "Bilbao", "Escuela Albacete",
				Mentalidad.Defensiva);
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del entrenador
	 */
	@Test
	public void testId() {
		e.setId(1);
		assertEquals(1, e.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre del
	 * entrenador
	 */
	@Test
	public void testNombre() {

		e.setNombre("Gaizka Garitano");
		assertEquals("Gaizka Garitano", e.getNombre());
	}

	/**
	 * Comprueba que getFechaNac() funciona correctamente devolviendo la fecha de
	 * nacimiento del entrenador
	 */
	@Test
	public void testAnyoNacimiento() {

		e.setFechaNac("1970");
		assertEquals("1970", e.getFechaNac());
	}

	/**
	 * Comprueba que getClub() funciona correctamente devolviendo el club del
	 * entrenador
	 */
	@Test
	public void testClub() {

		e.setClub("Athletic Club");
		assertEquals("Athletic Club", e.getClub());
	}

	/**
	 * Comprueba que getCiudad() funciona correctamente devolviendo la ciudad del
	 * entrenador
	 */
	@Test
	public void testCiudad() {

		e.setCiudad("Bilbao");
		assertEquals("Bilbao", e.getCiudad());
	}

	/**
	 * Comprueba que getFormacion() funciona correctamente devolviendo la formacion
	 * del entrenador
	 */
	@Test
	public void testFormacion() {

		e.setFormacion("Escuela Albacete");
		assertEquals("Escuela Albacete", e.getFormacion());
	}

	/**
	 * Comprueba que getMentalidad() funciona correctamente devolviendo la
	 * mentalidad del entrenador
	 */
	@Test
	public void testMentalidad() {

		e.setMentalidad(Mentalidad.Defensiva);
		assertEquals(Mentalidad.Defensiva, e.getMentalidad());
	}

	/**
	 * Comprueba el método toString() de la clase entrenador
	 */
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
