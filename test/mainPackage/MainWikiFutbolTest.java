package mainPackage;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * Test MainWikiFutbol
 *
 * @author sergi
 *
 */
public class MainWikiFutbolTest {

	static MainWikiFutbol mwf = new MainWikiFutbol();

	/**
	 * Crea un nuevo MainWikiFutbol
	 *
	 * @throws Exception En caso de fallo
	 */
	@Before
	public void setUp() throws Exception {
		mwf = new MainWikiFutbol();
	}

	/**
	 * Verifica la creacion del fichero log FeedbackLog.log
	 *
	 * En caso de crearlo con exito devuelve true En caso de no crearlo, nos
	 * avisaria del fallo
	 */
	@Test
	public void testCrearFicheroLog() {
		MainWikiFutbol.crearFicheroLog();

		File f = new File("FeedbackLog.log");
		assertTrue(f.exists());
	}

	/**
	 * Verifica la creacion de los ficheros logger BDLogger.log y GeneralLogger.log
	 *
	 * En caso de que exista, nos devuelve true En caso de que no exista, el metodo
	 * crearFicherLogger() crearía dicho logger Si el metodo ha realizado con exito
	 * la operación nos devuelve true En caso contrario, nos avisa de que el fichero
	 * no existe
	 */
	@Test
	public void testCrearFicheroLogger() {
		File f1 = new File("BDLogger.log");
		File f2 = new File("GeneralLogger.log");

		if (f1.exists()) {
			assertTrue(true);
		} else if (!f1.exists()) {
			MainWikiFutbol.crearFicheroLogger();
			assertTrue(f1.exists());
		}

		if (f2.exists()) {
			assertTrue(true);
		} else if (!f2.exists()) {
			MainWikiFutbol.crearFicheroLogger();
			assertTrue(f2.exists());
		}
	}

	/**
	 * Verifica la creacion de los ficheros properties account.properties y
	 * jdbc.properties
	 *
	 * Si el metodo crearFicheroLogger() ha funcionado correctamente, recibimos el
	 * true En caso contrario, nos avisa que el fichero no existe
	 */
	@Test
	public void testCrearFicherosProperties() {
		MainWikiFutbol.crearFicheroLogger();

		File f1 = new File("account.properties");
		File f2 = new File("jdbc.properties");

		assertTrue(f1.exists());

		assertTrue(f2.exists());
	}

}
