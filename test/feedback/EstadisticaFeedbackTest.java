package feedback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test EstadisticaFeedback
 *
 * @author sergi
 *
 */
public class EstadisticaFeedbackTest {

	static EstadisticaFeedback ef = new EstadisticaFeedback();

	/**
	 * Cre un nuevo EstadisticaFeedback
	 *
	 * @throws Exception En caso de error
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		ef = new EstadisticaFeedback();
	}

	/**
	 * Comprueba el metodo que sirve para realizar una puntuacion media de los
	 * feedbacks que realizan los usuarios
	 *
	 */
	@Test
	public void testMediaPuntuacion() {
		double a = 4;
		double b = 2;

		assertTrue(b != 0);

		double expected = EstadisticaFeedback.mediaPuntuacion(4, 2);
		double actual = a / b;
		double delta = 0;
		assertEquals(expected, actual, delta);
	}

	/**
	 * Comprueba el metodo de la recomendación "si" o "no" que realizan los usuarios
	 *
	 */
	@Test
	public void testSiNO() {
		double a = 6;
		double b = 4;
		double c = 10;

		assertTrue(c != 0);
		assertTrue((a + b) == c);

		double ans1 = (a / c) * 100;
		double ans2 = (b / c) * 100;

		Map<Integer, Double> map = new HashMap<Integer, Double>();
		map.put(0, ans1);
		map.put(1, ans2);

		assertEquals(map, EstadisticaFeedback.siNo(a, b, c));
	}

	/**
	 * Comprueba el metodo que sirve para recibir y guardar el Feedback de los
	 * usuarios en un .log Recibe y guarda la valoracion del usuario (del 1 al 5) y
	 * la recomendacion (si o no) Ademas, con esos datos realiza la media de
	 * valoracion y el porcentaje de si o no
	 *
	 */
	@Test
	public void testReadAndLoad() {
		try {
			assertEquals(EstadisticaFeedback.ReadAndLoad().size(), 4);

			assertTrue(
					(EstadisticaFeedback.ReadAndLoad().get(1) > 0) && (EstadisticaFeedback.ReadAndLoad().get(1) < 5));

			double porcentajeSi = EstadisticaFeedback.ReadAndLoad().get(2);
			double porcentajeNo = EstadisticaFeedback.ReadAndLoad().get(3);
			assertEquals(porcentajeSi + porcentajeNo, 100, 0.01);

			assertTrue((EstadisticaFeedback.ReadAndLoad().get(4) > 0)
					&& ((EstadisticaFeedback.ReadAndLoad().get(4) % 1) == 0));

		} catch (RWException e) {
			e.printStackTrace();
		}
	}

}
