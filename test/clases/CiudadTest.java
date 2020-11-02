package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CiudadTest {

	private Ciudad c;

	@Before
	public void setUp() {
		c = new Ciudad(1, "Bilbao", "Espa�a");
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

		c.setPais("Espa�a");
		assertEquals("Espa�a", c.getPais());
	}

	@Test
	public void metodoToString() {

		c.setId(1);
		c.setNombre("Bilbao");
		c.setPais("Espa�a");

		System.out.println(c.toString());

		assertEquals("Ciudad [id=1, nombre=Bilbao, pais=Espa�a]", c.toString());
	}
}
