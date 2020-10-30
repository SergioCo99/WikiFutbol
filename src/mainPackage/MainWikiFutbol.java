package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ventanas.VentanaLogin;

public class MainWikiFutbol {

	public static PrintStream logFeedback;

	public static void crearFicheroLog() {
		try {
			logFeedback = new PrintStream(new FileOutputStream("FeedbackLog.log", true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// ->en esta misma clase
		// log.println("Message");

		// -> en otra clase
		// mainPackage.MainWikiFutbol.log.println("Message");
	}

	public static Logger loggerBD = Logger.getLogger(MainWikiFutbol.class.getName()); // asi??
	public static Logger loggerGeneral = Logger.getLogger(MainWikiFutbol.class.getName()); // asi??

	public static void crearFicheroLogger() { // gitignore los ficheros cuando le cambiemos el nombre
		// FINEST / FINER / FINE / CONFIG / INFO / WARNING / SEVERE
		loggerBD.setLevel(Level.ALL);
		loggerGeneral.setLevel(Level.ALL);
		try {
			loggerBD.addHandler(new FileHandler("BDLogger.log", true));
			loggerGeneral.addHandler(new FileHandler("GeneralLogger.log", true));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		// ->en esta misma clase
		// loggerBD.log(Level.X, " Message ");
		// loggerGeneral.log(Level.X, " Message ");

		// -> en otra clase
		// mainPackage.MainWikiFutbol.loggerBD.log(Level.WARNING, e.toString());
		// mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
	}

	// por si no existe el fichero
	public static void crearFicherosAccountProperties() {
		try {
			File accountprop = new File("account.properties");
			accountprop.createNewFile();
			File jdbcprop = new File("jdbc.properties");
			jdbcprop.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		crearFicheroLog();
		crearFicheroLogger();
		crearFicherosAccountProperties();

		VentanaLogin VL = new VentanaLogin();
		VL.setVisible(true);
	}

}
