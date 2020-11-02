package utils;

import org.junit.Before;
import org.junit.Test;

public class MailSinFicheroTest {

	static MailSinFichero msf = new MailSinFichero();

	@Before
	public void setUp() throws Exception {
		msf = new MailSinFichero();
	}

	@Test
	public void test() {
		String toEmail = "wikifutbol@gmail.com"; // tiene que ser una direccion de correo
		String asunto = "asunto -test-";
		String texto = "texto -test-";

		utils.MailSinFichero.SendMail(toEmail, asunto, texto);
	}
}
