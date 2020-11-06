package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsuarioVotacionTest {

	private UsuarioVotacion uv;

	/**
	 * Crea un nuevo usuario votacion
	 */
	@Before
	public void setUp() {
		uv = new UsuarioVotacion(1, "a", "d", "c", "d", "p");
	}

	/**
	 * Comprueba que getUsuario() funciona correctamente devolviendo el usuario
	 */
	@Test
	public void testUsuario() {
		uv.setUsuario("a");
		assertEquals("a", uv.getUsuario());
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id
	 */
	@Test
	public void testId() {
		uv.setId(1);
		assertEquals(1, uv.getId());
	}

	/**
	 * Comprueba que getDelanero() funciona correctamente devolviendo los delanteros
	 */
	@Test
	public void testDelantero() {
		uv.setDelantero("d");
		assertEquals("d", uv.getDelantero());
	}

	/**
	 * Comprueba que getCentrocampista() funciona correctamente devolviendo los centrocampistas
	 */
	@Test
	public void testCentrocampista() {
		uv.setCentrocampista("c");
		assertEquals("c", uv.getCentrocampista());
	}

	/**
	 * Comprueba que getDefensa() funciona correctamente devolviendo los defensas
	 */
	@Test
	public void testDefensa() {
		uv.setDefensa("d");
		assertEquals("d", uv.getDefensa());
	}

	/**
	 * Comprueba que getPortero() funciona correctamente devolviendo los porteros
	 */
	@Test
	public void testPortero() {
		uv.setPortero("p");
		assertEquals("p", uv.getPortero());
	}

	/**
	 * Comprueba el método toString() de la clase UsuarioVotacion
	 */
	@Test
	public void metodoToString() {

		uv.setId(1);
		uv.setUsuario("a");
		uv.setDelantero("d");
		uv.setCentrocampista("c");
		uv.setDefensa("d");
		uv.setPortero("p");

		System.out.println(uv.toString());

		assertEquals("UsuarioVotacion [id=1, usuario=a, delantero=d, centrocampista=c, defensa=d, portero=p]",
				uv.toString());
	}

}
