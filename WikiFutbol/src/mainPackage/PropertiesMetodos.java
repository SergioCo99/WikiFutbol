package mainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesMetodos {

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
			e.printStackTrace();
		}
	}

	public static String getProp1() {
		File archivo = new File("account.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			String nombre = propConfig.getProperty("correo");
			return nombre;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getProp2() {
		File archivo = new File("account.properties");
		try {
			FileInputStream fis = new FileInputStream(archivo);
			Properties propConfig = new Properties();
			propConfig.load(fis);
			String contr = propConfig.getProperty("contrasena");
			return contr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
