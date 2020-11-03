package mainPackage;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class MainWikiFutbolTest {

	static MainWikiFutbol mwf = new MainWikiFutbol();

	@Before
	public void setUp() throws Exception {
		mwf = new MainWikiFutbol();
	}

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
