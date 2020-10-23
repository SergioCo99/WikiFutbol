package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioVotacionTest {

	private UsuarioVotacion uv;

	@Before
	public void setUp() {
		uv = new UsuarioVotacion(1, "a", "d", "c", "d", "p");
	}
	
	@Test
	public void testUsuario() {
		uv.setUsuario("a");
		assertEquals("a", uv.getUsuario());
	}

	@Test
	public void testId() {
		uv.setId(1);
		assertEquals(1, uv.getId());
	}

	@Test
	public void testDelantero() {
		uv.setDelantero("d");
		assertEquals("d", uv.getDelantero());
	}

	@Test
	public void testCentrocampista() {
		uv.setCentrocampista("c");
		assertEquals("c", uv.getCentrocampista());
	}

	@Test
	public void testDefensa() {
		uv.setDefensa("d");
		assertEquals("d", uv.getDefensa());
	}

	@Test
	public void testPortero() {
		uv.setPortero("p");
		assertEquals("p", uv.getPortero());
	}

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
