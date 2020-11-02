package utils;

import org.junit.Before;
import org.junit.Test;

public class MailConFicheroTest {

	static MailConFichero mcf = new MailConFichero();

	@Before
	public void setUp() throws Exception {
		mcf = new MailConFichero();
	}

	@Test
	public void test() {
		String dest = "wikifutbol@gmail.com"; // tiene que ser una direccion de correo
		String asunto = "asunto -test-";
		String texto = "texto -test-";
		String filename = utils.FileChooser.Choose(); // tiene que tener un archivo SI O SI

		utils.MailConFichero.SendMailConFichero(dest, asunto, texto, filename);
	}

}
