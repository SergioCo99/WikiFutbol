package ventanas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import database.DBManager;
import database.DBManagerException;

/**
 * Test de la clase VentanaPrincipal
 *
 * @author sergi
 *
 */
public class VentanaPrincipalTest {

	/**
	 * Conectar al servidor
	 *
	 * @throws DBManagerException En caso de fallo
	 */
	@BeforeClass
	public static void testConnect() throws DBManagerException {
		DBManager.connect();
	}

	/**
	 * Comprueba si el usuario es admin o no lo es
	 *
	 */
	@Test
	public void testPrivilegiosAdmin() {

		String correoQueSiEsdmin = "a@gmail.com";

		assertTrue(VentanaPrincipal.privilegiosAdmin(correoQueSiEsdmin));

		String correoQueNoEsdmin = "b@gmail.com";

		assertFalse(VentanaPrincipal.privilegiosAdmin(correoQueNoEsdmin));
	}

}
