package ventanas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VentanaPrincipalTest {

	@Test
	public void testPrivilegiosAdmin() {

		String correoQueSiEsdmin = "a@gmail.com";

		assertTrue(VentanaPrincipal.privilegiosAdmin(correoQueSiEsdmin));

		String correoQueNoEsdmin = "b@gmail.com";

		assertFalse(VentanaPrincipal.privilegiosAdmin(correoQueNoEsdmin));
	}

}