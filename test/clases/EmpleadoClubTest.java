package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EmpleadoClubTest {

	private EmpleadoDeClub ect;

	@Before
	public void setUp() {
		ect = new EmpleadoDeClub(1, "Koikili", 1980, 1, 1);
	}

	@Test
	public void testId() {

		ect.setId(1);
		assertEquals(1, ect.getId());
	}

	@Test
	public void testNombre() {

		ect.setNombre("Koikili");
		assertEquals("Koikili", ect.getNombre());
	}

	@Test
	public void testAnyoNacimiento() {

		ect.setAnyoNacimiento(1980);
		assertEquals(1980, ect.getAnyoNacimiento());
	}

	@Test
	public void testClub() {

		ect.setClub(1);
		assertEquals(1, ect.getClub());
	}

	@Test
	public void testCiudad() {

		ect.setCiudad(1);
		assertEquals(1, ect.getCiudad());
	}

	@Test
	public void metodoToString() {
		
		ect.setId(1);
		ect.setNombre("Koikili");
		ect.setAnyoNacimiento(1980);
		ect.setClub(1);
		ect.setCiudad(1);

		System.out.println(ect.toString());
<<<<<<< HEAD
		assertEquals("EmpleadoDeClub [id=1, nombre=Koikili, anyoNacimiento=1980, club=1, ciudad=1]", ect.toString());
=======
		
		assertEquals(
				"EmpleadoDeClub [id=1, nombre=Koikili, anyoNacimiento=1980, club=1, ciudad=1]",
				ect.toString());
>>>>>>> branch 'master' of https://github.com/SergioCo99/WikiFutbol.git
	}
}
