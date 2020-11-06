package feedback;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Test RWException
 * @author sergi
 *
 */
public class RWExceptionTest {

	RWException rwe = new RWException("Mensaje", null);
	Connection conn;

	/**
	 * Crea un nuevo RWException
	 *
	 * @throws Exception En caso de fallos
	 */
	@Before
	public void setUp() throws Exception {
		rwe = new RWException(rwe.getMessage(), rwe.getCause());
	}

	/**
	 * Prueba que nos lanza un mensaje en caso de execepcion y la causa
	 *
	 * @throws RWException En caso de fallo
	 */
	@Test(expected = RWException.class)
	public void testDBManagerException() throws RWException {
		try {
			File myObj = new File("ficheroQueNoExiste.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		} catch (Exception e) {
			throw new RWException("Mensaje y causa", e);
		}
	}
}
