package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la clase EmpleadoClub
 * 
 * @author sergi
 *
 */
public class EmpleadoClubTest {

	private EmpleadoDeClub ect;

	/**
	 * Crea un nuevo empleado del club
	 */
	@Before
	public void setUp() {
		ect = new EmpleadoDeClub(1, "Koikili", "1990-06-25", "Athletic Club", "Bilbao");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del empleado
	 * del club
	 */
	@Test
	public void testId() {

		ect.setId(1);
		assertEquals(1, ect.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre del
	 * empleado del club
	 */
	@Test
	public void testNombre() {

		ect.setNombre("Koikili");
		assertEquals("Koikili", ect.getNombre());
	}

	/**
	 * Comprueba que getFechaNac() funciona correctamente devolviendo la fecha de
	 * nacimiento del empleado del club
	 */
	@Test
	public void testAnyoNacimiento() {

		ect.setFechaNac("1990-06-25");
		assertEquals("1990-06-25", ect.getFechaNac());
	}

	/**
	 * Comprueba que getClub() funciona correctamente devolviendo el club del
	 * empleado
	 */
	@Test
	public void testClub() {

		ect.setClub("Athletic Club");
		assertEquals("Athletic Club", ect.getClub());
	}

	/**
	 * Comprueba que getCiudad() funciona correctamente devolviendo la ciudad del
	 * empleado
	 */
	@Test
	public void testCiudad() {
		ect.setCiudad("Bilbao");
		assertEquals("Bilbao", ect.getCiudad());
	}

	/**
	 * Comprueba el método toString() de la clase EmpleadoClub
	 */
	@Test
	public void metodoToString() {

		ect.setId(1);
		ect.setNombre("Koikili");
		ect.setFechaNac("1990-06-25");
		ect.setClub("Athletic Club");
		ect.setCiudad("Bilbao");

		System.out.println(ect.toString());

		assertEquals("EmpleadoDeClub [id=1, nombre=Koikili, fechaNac=1990-06-25, club=Athletic Club, ciudad=Bilbao]",
				ect.toString());
	}
}
