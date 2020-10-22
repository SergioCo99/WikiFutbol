package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EstadioTest {

	private Estadio e;
	
	@Before
	public void setUp() {
		e = new Estadio(1, "San Mamés", 20000, 1900, 1);
	}

	@Test
	public void testId() {
		e.setId(1);
		assertEquals(1, e.getId());
	}
	
	@Test
	public void testNombre() {
		e.setNombre("San Mamés");
		assertEquals("San Mamés", e.getNombre());
	}
	
	@Test
	public void testAforo() {
		e.setAforo(2000);
		assertEquals(2000 , e.getAforo());
	}
	
	@Test
	public void testAnyoCreacion() {
		e.setAnyoCreacion(1900);
		assertEquals(1900 , e.getAnyoCreacion());
	}
	
	@Test
	public void testCiudad() {
		e.setCiudad(1);
		assertEquals(1, e.getCiudad());
	}
	
	@Test
	public void metodoToString() {
		e.setId(1);
		e.setNombre("San Mamés");
		e.setAforo(20000);
		e.setAnyoCreacion(1900);
		e.setCiudad(1);

		System.out.println(e.toString());
		
		assertEquals(
				"Estadio [id=1, nombre=San Mamés, aforo=20000, anyoCreacion=1900, ciudad=1]",
				e.toString());
	}

}
