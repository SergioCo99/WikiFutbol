package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaisTest {

	private Pais p;
	
	@Before
	public void setUp() {
		p = new Pais(1, "España");
	}
	
	@Test
	public void testId() {
		p.setId(1);
		assertEquals(1, p.getId());
	}

	@Test
	public void testNombre() {

		p.setNombre("España");
		assertEquals("España", p.getNombre());
	}
	
	public void metodoToString() {

		p.setId(1);
		p.setNombre("España");
		
		System.out.println(p.toString());

		assertEquals(
				"Pais [id=1, pais=España]",
				p.toString());
	}
}
