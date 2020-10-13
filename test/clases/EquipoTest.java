package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EquipoTest {

	private Equipo a;

	@Before
	public void setUp() {
		a = new Equipo(1, "Athletic Club", "Bilbao", "San Mamés", 1898, "2 Ligas", "Gaizka Garitano");
	}

	@Test
	public void testId() {
		a.setId(1);
		assertEquals(1, a.getId());
	}

	@Test
	public void testNombre() {
		a.setNombre("Athletic Club");
		assertEquals("Athletic Club", a.getNombre());
	}

	@Test
	public void testCiudad() {
		a.setCiudad("Bilbao");
		assertEquals("Bilbao", a.getCiudad());
	}

	@Test
	public void testEstadio() {
		a.setEstadio("San Mamés");
		assertEquals("San Mamés", a.getEstadio());
	}

	@Test
	public void testAnyoCreacion() {
		a.setAnyoCreacion(1898);
		assertEquals(1898, a.getAnyoCreacion());
	}

	@Test
	public void testPalmares() {
		a.setPalmares("2 Ligas");
		assertEquals("2 Ligas", a.getPalmares());
	}

	@Test
	public void testEntrenador() {
		a.setEntrenador("Gaizka Garitano");
		assertEquals("Gaizka Garitano", a.getEntrenador());
	}

	@Test
	public void metodoToString() {
		a.setId(1);
		a.setNombre("Athletic Club");
		a.setCiudad("Bilbao");
		a.setEstadio("San Mamés");
		a.setAnyoCreacion(1898);
		a.setPalmares("2 Ligas");
		a.setEntrenador("Gaizka Garitano");

		System.out.println(a.toString());
		assertEquals(
				"Equipo [id=1, nombre=Athletic Club, ciudad=Bilbao, estadio=San Mamés, anyoCreacion=1898, palmares=2 Ligas, entrenador=Gaizka Garitano]",
				a.toString());
	}

}
