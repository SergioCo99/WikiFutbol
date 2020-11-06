package database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;

public class DBManagerExceptionTest {

	DBManagerException dbme = new DBManagerException("Mensaje");
	Connection conn;

	/**
	 * Crea un nuevo DBManagerException
	 * 
	 * @throws Exception En caso de fallo
	 */
	@Before
	public void setUp() throws Exception {
		dbme = new DBManagerException(dbme.getMessage());
	}

	/**
	 * Prueba los metodos que nos avisan en caso de error
	 * Primero en el que en caso de error nos avisa con un mensaje y la causa
	 * Y despues en el que en caso de error nos avisa solo con un mensaje
	 * 
	 * @throws DBManagerException En caso de fallo
	 */
	@Test(expected = DBManagerException.class)
	public void testDBManagerException() throws DBManagerException {
		try {
			String CONTROLADOR = "MAL";
			String URL = "MAL";
			String USUARIO = "MAL";
			String CONTRASENA = "MAL";

			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		} catch (Exception e) {
			throw new DBManagerException("Mensaje y causa", e);
		}

		try {
			String CONTROLADOR = "MAL";
			String URL = "MAL";
			String USUARIO = "MAL";
			String CONTRASENA = "MAL";

			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		} catch (Exception e) {
			throw new DBManagerException("Solo mensaje");
		}
	}
}
