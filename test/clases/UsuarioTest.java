package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	private Usuario u;

	@Before
	public void setUp() {
		u = new Usuario(1, "a", "a", "a@a.com", 1, "1990-06-25");
	}

	@Test
	public void testId() {
		u.setId(1);
		assertEquals(1, u.getId());
	}

	@Test
	public void testNombre() {
		u.setNombre("a");
		assertEquals("a", u.getNombre());
	}

	@Test
	public void testContrasena() {
		u.setContrasena("a");
		assertEquals("a", u.getContrasena());
	}

	@Test
	public void testCorreo() {
		u.setCorreo("a@a.com");
		assertEquals("a@a.com", u.getCorreo());
	}

	@Test
	public void testAdmin() {
		u.setAdmin(1);
		;
		assertEquals(1, u.getAdmin());
	}

	@Test
	public void testFechaNac() {
		u.setFechaNac("1990-06-25");
		assertEquals("1990-06-25", u.getFechaNac());
	}

	@Test
	public void metodoToString() {

		u.setId(1);
		u.setNombre("a");
		u.setContrasena("a");
		u.setCorreo("a@a.com");
		u.setAdmin(1);
		u.setFechaNac("1990-06-25");

		System.out.println(u.toString());

		assertEquals("Usuario [id=1, nombre=a, contrasena=a, correo=a@a.com, admin=1, fechaNac=1990-06-25]",
				u.toString());
	}
}
