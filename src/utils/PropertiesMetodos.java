package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class PropertiesMetodos {

	
	/**
	 * Es el setter del account.properties
	 * 
	 * @param mail Email del usuario
	 * @param password Contrasena del usuario
	 */
	public static void setProp(String mail, String password) {
		File archivo = new File("account.properties");
		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			Properties propConfig = new Properties();

			propConfig.setProperty("correo", mail);
			propConfig.setProperty("contrasena", password);
			propConfig.store(fos, "program Settings");
			fos.close();
		} catch (IOException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Es el getter del correo del usuario del archivo account.properties
	 * 
	 * @return Devuelve el correo del usuario
	 */
	public static String getProp1() {
		File archivo = new File("account.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			String nombre = propConfig.getProperty("correo");
			return nombre;
		} catch (IOException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Es el getter de la contrasena del usuario del archivo account.properties
	 * 
	 * @return Devuelve la contrasena del usuario
	 */
	public static String getProp2() {
		File archivo = new File("account.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			String contr = propConfig.getProperty("contrasena");
			return contr;
		} catch (IOException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Properties prop = mainPackage.PropertiesMetodos.loadPropertiesFile(); String
	 * CONTROLADOR = prop.getProperty("DB.CONTROLADOR"); String URL =
	 * prop.getProperty("DB.URL"); String USUARIO = prop.getProperty("DB.USUARIO");
	 * String CONTRASENA = prop.getProperty("DB.CONTRASENA");
	 */
	
	/**
	 * Crea el archivo jdbc.properties donde se recoge información sobre la BD
	 * 
	 * @return El archivo properties
	 * @throws Exception En caso de error
	 */
	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("jdbc.properties");
		prop.load(in);
		in.close();
		return prop;
	}

}
