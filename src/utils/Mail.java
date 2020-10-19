package utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static void Email(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("wikifutbolteam@gmail.com", "WikiFutbol Alert"));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			System.out.println("Message is ready");
			Transport.send(msg);

			System.out.println("Correo enviado correctamente!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void SendMail(String toEmail, String asunto, String texto) {

		final String fromEmail = "wikifutbolteam@gmail.com"; // requires valid gmail id
		final String password = "kflipao99"; // correct password for gmail id
		// final String toEmail = "eneko.perez@opendeusto.es"; // can be any email id

		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // linea misteriosa de StackOverflow

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		Email(session, toEmail, // A quien se lo mandas
				asunto, // Asunto del correo
				texto // Texto del correo
		);

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		Mail.SendMail("eneko.perez@opendeusto.es", "Asunto?", "Texto?");
	}
}
