package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EmpleadoClubTest {

	private EmpleadoDeClub ect;

	@Before
	public void setUp() {
		ect = new EmpleadoDeClub(1, "Koikili", "1980", "Athletic Club", "Bilbao");
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

		ect.setFechaNac("1980");
		assertEquals("1980", ect.getFechaNac());
	}

	@Test
	public void testClub() {

		ect.setClub("Athletic Club");
		assertEquals("Athletic Club", ect.getClub());
	}

	@Test
	public void testCiudad() {
		ect.setCiudad("Bilbao");
		assertEquals("Bilbao", ect.getCiudad());
	}

	@Test
	public void metodoToString() {

		ect.setId(1);
		ect.setNombre("Koikili");
		ect.setFechaNac("1980");
		ect.setClub("Athletic Club");
		ect.setCiudad("Bilbao");

		System.out.println(ect.toString());

		assertEquals("EmpleadoDeClub [id=1, nombre=Koikili, fechaNac=1980, club=Athletic Club, ciudad=Bilbao]", ect.toString());
	}
}
