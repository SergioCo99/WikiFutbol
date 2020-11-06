package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test FileChooser
 *
 * @author sergi
 *
 */
public class FileChooserTest {

	static FileChooser fc = new FileChooser();

	/**
	 * Crea un nuevo FileChooser
	 *
	 * @throws Exception En caso de fallo
	 */
	@Before
	public void setUp() throws Exception {
		fc = new FileChooser();
	}

	/**
	 * Primero tiene que seleccionar el archivo del "String actual" en concreto
	 * Despues comprueba si el archivo que se recibe del metodo
	 * "FileChooser.Choose()" y el que se encuentra en "String actual" son iguales
	 *
	 * En la segunda prueba, no selecciona nada, es decir, se pulsa en el boton
	 * cancelar o en la X Verifica que lo que ha recibido FileChooser.Choose() y el
	 * "String actual2" son iguales
	 */
	@Test
	public void test() {
		String finalPath = utils.FileChooser.Choose();
		String actual = System.getProperty("user.home") + "\\git\\WikiFutbol\\resources\\wf.png";

		assertEquals(finalPath, actual);

		String finalPath2 = utils.FileChooser.Choose();
		String actual2 = "";

		assertEquals(finalPath2, actual2);
	}

}
