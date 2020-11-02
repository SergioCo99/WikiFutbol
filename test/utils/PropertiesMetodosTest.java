package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropertiesMetodosTest {

	static PropertiesMetodos pm = new PropertiesMetodos();

	String mail = "abc@gmail.com";
	String password = "passw";

	@BeforeClass
	public static void setUp() throws Exception {
		pm = new PropertiesMetodos();
	}

	@After
	public void rollBack() throws Exception {
		utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
	}

	@Before
	public void testSetProp() {
		utils.PropertiesMetodos.setProp(mail, password);

		try {
			File myObj = new File("account.properties");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				for (int i = 0; i <= 3; i++) {
					String data = myReader.nextLine();
					if (i == 2) {
						if (!data.equals("correo=" + mail)) {
							fail("No concuerda el correo");
						}
					} else if (i == 3) {
						if (!data.equals("contrasena=" + password)) {
							fail("No concuerda la contrasena");
						}
					}
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("FileNotFoundException");
		}
	}

	@Test
	public void testGetProp1() {
		String actual = utils.PropertiesMetodos.getProp1();
		String expected = mail;

		assertEquals(expected, actual);
	}

	@Test
	public void testGetProp2() {
		String actual = utils.PropertiesMetodos.getProp2();
		String expected = password;

		assertEquals(expected, actual);
	}

	@Test
	public void testLoadPropertiesFile() {
		BufferedReader reader;

		try {
			Properties prop = utils.PropertiesMetodos.loadPropertiesFile();

			String CONTROLADOR = prop.getProperty("DB.CONTROLADOR");
			String URL = prop.getProperty("DB.URL");
			String USUARIO = prop.getProperty("DB.USUARIO");
			String CONTRASENA = prop.getProperty("DB.CONTRASENA");
			String EXCLUIDAS = prop.getProperty("DB.TABLASEXCLUIDAS");

			reader = new BufferedReader(new FileReader("jdbc.properties"));

			String line = reader.readLine();
			assertEquals("com.mysql.cj.jdbc.Driver", CONTROLADOR);

			line = reader.readLine();
			assertEquals(
					"jdbc:mysql://localhost:3306/wikifutbolschema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					URL);

			line = reader.readLine();
			System.out.println(line);
			assertEquals("root", USUARIO);

			line = reader.readLine();
			assertEquals("admin", CONTRASENA);

			line = reader.readLine();
			assertEquals(
					"# en DB.TABLASEXCLUIDA las tablas se diferencian entre comas y todo JUNTO (p.e.:usuario,jugador,club), ¡SI LO CAMBIAS REVISA QUE FUNCIONA en VentanaDescargar!",
					line);

			line = reader.readLine();
			assertEquals("usuario", EXCLUIDAS);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
