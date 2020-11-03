package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FileChooserTest {

	static FileChooser fc = new FileChooser();

	@Before
	public void setUp() throws Exception {
		fc = new FileChooser();
	}

	@Test
	public void test() {
		// Hay que seleccionar el archivo del "String actual" en concreto
		String finalPath = utils.FileChooser.Choose();
		String actual = System.getProperty("user.home") + "\\git\\WikiFutbol\\resources\\wf.png";

		assertEquals(finalPath, actual);

		// Esta vez no se selecciona nada, es decir, boton cancelar o X
		String finalPath2 = utils.FileChooser.Choose();
		String actual2 = "";

		assertEquals(finalPath2, actual2);
	}

}
