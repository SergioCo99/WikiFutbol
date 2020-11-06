package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	private Usuario u;

	/**
	 * Crea un usuario
	 */
	@Before
	public void setUp() {
		u = new Usuario(1, "a", "a", "a@a.com", 1, "1990-06-25");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del usuario
	 */
	@Test
	public void testId() {
		u.setId(1);
		assertEquals(1, u.getId());
	}

	/**
	 * Comprueba que getNombre() funciona correctamente devolviendo el nombre del
	 * usuario
	 */
	@Test
	public void testNombre() {
		u.setNombre("a");
		assertEquals("a", u.getNombre());
	}

	/**
	 * Comprueba que getContrasena() funciona correctamente devolviendo la
	 * contrasena del usuario
	 */
	@Test
	public void testContrasena() {
		u.setContrasena("a");
		assertEquals("a", u.getContrasena());
	}

	/**
	 * Comprueba que getCorreo() funciona correctamente devolviendo el correo del
	 * usuario
	 */
	@Test
	public void testCorreo() {
		u.setCorreo("a@a.com");
		assertEquals("a@a.com", u.getCorreo());
	}

	/**
	 * Comprueba que getAdmin() funciona correctamente devolviendo si el usuario es
	 * admin o no
	 */
	@Test
	public void testAdmin() {
		u.setAdmin(1);
		;
		assertEquals(1, u.getAdmin());
	}

	/**
	 * Comprueba que getFechaNac() funciona correctamente devolviendo la fecha de
	 * nacimiento del usuario
	 */
	@Test
	public void testFechaNac() {
		u.setFechaNac("1990-06-25");
		assertEquals("1990-06-25", u.getFechaNac());
	}

	/**
	 * Comprueba el método toString() de la clase Usuario
	 */
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
