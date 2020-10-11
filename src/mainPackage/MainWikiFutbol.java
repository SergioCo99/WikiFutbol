package mainPackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ventanas.VentanaLogin;

public class MainWikiFutbol {

	static PrintStream log;

	public static void crearFicheroLog() { // gitignore los ficheros cuando le cambiemos el nombre
		try {
			log = new PrintStream(new FileOutputStream("PruebaLog.log", true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// ->en esta misma clase
		// log.println("Message");

		// -> en otra clase
		// mainPackage.MainWikiFutbol.log.println("Message");
	}

	static Logger logger = Logger.getLogger(MainWikiFutbol.class.getName()); // asi??

	public static void crearFicheroLogger() { // gitignore los ficheros cuando le cambiemos el nombre
		// FINEST / FINER / FINE / CONFIG / INFO / WARNING / SEVERE
		logger.setLevel(Level.ALL);
		try {
			logger.addHandler(new FileHandler("PruebaLogger.log", true));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		// ->en esta misma clase
		// logger.log(Level.X, " Message ");

		// -> en otra clase
		// mainPackage.MainWikiFutbol.logger.log([Por ahora Level.INFO],"Message");
	}

	public static void main(String[] args) {
		crearFicheroLog();
		crearFicheroLogger();

		VentanaLogin VL = new VentanaLogin();
		VL.setVisible(true);
	}

}
