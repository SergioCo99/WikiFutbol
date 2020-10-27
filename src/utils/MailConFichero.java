package utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailConFichero {

	public static void m2(String dest, String asunto, String texto, String filename) {
		// Recipient's email ID needs to be mentioned.
		// dest = "eneko.perez@opendeusto.es";

		// Sender's email ID needs to be mentioned
		String from = "wikifutbolteam@gmail.com";

		final String username = "WikiFutbol Alert";// change accordingly
		final String password = "kflipao99";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // linea misteriosa de StackOverflow

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(/* username */from, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from, username));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));

			// Set Subject: header field
			message.setSubject(asunto);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText(texto);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			// String filename = utils.FileChooser.Choose(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));

			String s = filename.substring(filename.lastIndexOf("."), filename.length());
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH-mm");
			String dateTimeInfo = dateFormat.format(new Date());

			messageBodyPart.setFileName(/* nombreArchivoAdjunto( */"WikiFutbol" + dateTimeInfo + s/* ) */);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException | UnsupportedEncodingException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			throw new RuntimeException(e);
		}
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		m2("eneko.perez23@gmail.com", "Asunto? m2.java", "Texto?  m2.java", utils.FileChooser.Choose());
	}
}