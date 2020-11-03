package feedback;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class RWExceptionTest {

	RWException rwe = new RWException("Mensaje", null);
	Connection conn;

	@Before
	public void setUp() throws Exception {
		rwe = new RWException(rwe.getMessage(), rwe.getCause());
	}

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
