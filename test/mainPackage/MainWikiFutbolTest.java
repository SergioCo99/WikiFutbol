package mainPackage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class MainWikiFutbolTest {

	static MainWikiFutbol mwf = new MainWikiFutbol();

	/**
	 * Crea un nuevo MainWikiFutbol
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mwf = new MainWikiFutbol();
	}

	/**
	 * Verifica la creación del fichero log FeedbackLog.log
	 * 
	 * En caso de crearlo con exito devuelve true
	 * En caso de no crearlo, nos avisaria del fallo
	 */
	@Test
	public void testCrearFicheroLog() {
		MainWikiFutbol.crearFicheroLog();

		File f = new File("FeedbackLog.log");
		if (f.exists()) {
			assertTrue(true);
		} else if (!f.exists()) {
			fail();
		}
	}

	/**
	 * Verifica la creación de los ficheros logger BDLogger.log y GeneralLogger.log
	 * 
	 * En caso de que exista, nos devuelve true
	 * En caso de que no exista, el método crearFicherLogger() crearía dicho logger
	 * Si el método ha realizado con exito la operación nos devuelve true
	 * En caso contrario, nos avisa de que el fichero no existe
	 */
	@Test
	public void testCrearFicheroLogger() {
		File f1 = new File("BDLogger.log");
		File f2 = new File("GeneralLogger.log");

		if (f1.exists()) {
			assertTrue(true);
		} else if (!f1.exists()) {
			MainWikiFutbol.crearFicheroLogger();
			if (f1.exists()) {
				assertTrue(true);
			} else if (!f1.exists()) {
				fail("Fichero BDLogger.log no existe");
			}
		}

		if (f2.exists()) {
			assertTrue(true);
		} else if (!f2.exists()) {
			MainWikiFutbol.crearFicheroLogger();
			if (f2.exists()) {
				assertTrue(true);
			} else if (!f2.exists()) {
				fail("Fichero GeneralLogger.log no existe");
			}
		}
	}

	/**
	 * Verifica la creación de los ficheros properties account.properties y jdbc.properties
	 * 
	 * Si el método crearFicheroLogger() ha funcionado correctamente, recibimos el true
	 * En caso contrario, nos avisa que el fichero no existe
	 */
	@Test
	public void testCrearFicherosProperties() {
		MainWikiFutbol.crearFicheroLogger();

		File f1 = new File("account.properties");
		File f2 = new File("jdbc.properties");

		if (f1.exists()) {
			assertTrue(true);
		} else if (!f1.exists()) {
			fail("Fichero account.properties no existe");
		}

		if (f2.exists()) {
			assertTrue(true);
		} else if (!f2.exists()) {
			fail("Fichero jdbc.properties no existe");
		}
	}

}
