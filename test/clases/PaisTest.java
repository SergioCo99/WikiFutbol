package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaisTest {

	private Pais p;
	
	@Before
	public void setUp() {
		p = new Pais(1, "Espa�a");
	}
	
	@Test
	public void testId() {
		p.setId(1);
		assertEquals(1, p.getId());
	}

	@Test
	public void testNombre() {

		p.setNombre("Espa�a");
		assertEquals("Espa�a", p.getNombre());
	}
	
	public void metodoToString() {

		p.setId(1);
		p.setNombre("Espa�a");
		
		System.out.println(p.toString());

		assertEquals(
				"Pais [id=1, pais=Espa�a]",
				p.toString());
	}
}
