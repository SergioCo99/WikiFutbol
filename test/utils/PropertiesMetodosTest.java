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

/**
 * Test PropertiesMetodos
 * @author sergi
 *
 */
public class PropertiesMetodosTest {

	static PropertiesMetodos pm = new PropertiesMetodos();

	String mail = "abc@gmail.com";
	String password = "passw";

	/**
	 * Crea un nuevo PropertiesMetodos
	 *
	 * @throws Exception En caso de error
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		pm = new PropertiesMetodos();
	}

	/**
	 * Incorpora esos valores
	 *
	 * @throws Exception En caso de fallo
	 */
	@After
	public void rollBack() throws Exception {
		utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
	}

	/**
	 * Realiza un setter para añadir los valores a mail y password, y añadirlos en
	 * account.properties
	 *
	 */
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

	/**
	 * Recibe el mail y lo compara con el esperado
	 *
	 */
	@Test
	public void testGetProp1() {
		String actual = utils.PropertiesMetodos.getProp1();
		String expected = mail;

		assertEquals(expected, actual);
	}

	/**
	 * Recibe la contrasena y la compara con la esperada
	 */
	@Test
	public void testGetProp2() {
		String actual = utils.PropertiesMetodos.getProp2();
		String expected = password;

		assertEquals(expected, actual);
	}

	/**
	 * Pueba el archivo jdbc.properties donde se recoge información sobre la BD
	 */
	@Test
	public void testLoadPropertiesFile() {
		BufferedReader reader;

		try {
			Properties prop = utils.PropertiesMetodos.loadPropertiesFile();

			String CONTROLADOR = prop.getProperty("DB.CONTROLADOR");
			String URL = prop.getProperty("DB.URL");
			String USUARIO = prop.getProperty("DB.USUARIO");
			String CONTRASENA = prop.getProperty("DB.CONTRASENA");
			//String EXCLUIDAS = prop.getProperty("DB.TABLASEXCLUIDAS");

			reader = new BufferedReader(new FileReader("jdbc.properties"));

			String line = reader.readLine();
			assertEquals("com.mysql.cj.jdbc.Driver", CONTROLADOR);

			line = reader.readLine();
			assertEquals("jdbc:mysql://wikifutboldb.cdzgkizlagy4.eu-west-3.rds.amazonaws.com:3306/wikifutbolschema",
					URL);

			line = reader.readLine();
			System.out.println(line);
			assertEquals("root", USUARIO);

			line = reader.readLine();
			assertEquals("adminadmin", CONTRASENA);

			line = reader.readLine();
			assertEquals(
					"# en DB.TABLASEXCLUIDA las tablas se diferencian entre comas y todo JUNTO (p.e.:usuario,jugador,club), �SI LO CAMBIAS REVISA QUE FUNCIONA en VentanaDescargar!",
					line);

			/*
			 * line = reader.readLine(); assertEquals("usuario", EXCLUIDAS);
			 */

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
