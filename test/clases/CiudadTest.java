package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CiudadTest {

	private Ciudad c;

	@Before
	public void setUp() {
		c = new Ciudad(1, "Bilbao", "España");
	}

	@Test
	public void testId() {
		c.setId(1);
		assertEquals(1, c.getId());
	}

	@Test
	public void testNombre() {

		c.setNombre("Bilbao");
		assertEquals("Bilbao", c.getNombre());
	}

	@Test
	public void testPais() {

		c.setPais("España");
		assertEquals("España", c.getPais());
	}

	@Test
	public void metodoToString() {

		c.setId(1);
		c.setNombre("Bilbao");
		c.setPais("España");

		System.out.println(c.toString());

		assertEquals("Ciudad [id=1, nombre=Bilbao, pais=España]", c.toString());
	}
}
