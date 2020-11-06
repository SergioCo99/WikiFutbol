package utils;

import org.junit.Before;
import org.junit.Test;

/**
 * Test MailSinFichero
 * @author sergi
 *
 */
public class MailSinFicheroTest {

	static MailSinFichero msf = new MailSinFichero();

	/**
	 * Crea un nuevo MailSinFichero
	 *
	 * @throws Exception En caso de fallo
	 */
	@Before
	public void setUp() throws Exception {
		msf = new MailSinFichero();
	}

	/**
	 * Crea un correo con su destinatario, asunto y contenido a enviar Comprueba que
	 * el metodo SendMail() funciona correctamente
	 */
	@Test
	public void test() {
		String toEmail = "wikifutbol@gmail.com";
		String asunto = "asunto -test-";
		String texto = "texto -test-";

		utils.MailSinFichero.SendMail(toEmail, asunto, texto);
	}
}
