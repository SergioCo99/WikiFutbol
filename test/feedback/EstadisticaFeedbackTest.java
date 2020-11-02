package feedback;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class EstadisticaFeedbackTest {

	static EstadisticaFeedback ef = new EstadisticaFeedback();

	@BeforeClass
	public static void setUp() throws Exception {
		ef = new EstadisticaFeedback();
	}

	@Test
	public void testMediaPuntuacion() {
		double a = 4;
		double b = 2;

		if (b == 0) {
			fail("No puedes dividir por cero");
		}

		double expected = EstadisticaFeedback.mediaPuntuacion(4, 2);
		double actual = a / b;
		double delta = 0;
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testSiNO() {
		double a = 6;
		double b = 4;
		double c = 10;

		if (c == 0) {
			fail("No puedes dividir por cero");
		} else if (a + b != c) {
			fail("Deberia que dar la suma");
		}

		double ans1 = (a / c) * 100;
		double ans2 = (b / c) * 100;

		ArrayList<Double> arr = new ArrayList<>();
		arr.add(0, ans1);
		arr.add(1, ans2);

		assertEquals(arr, EstadisticaFeedback.siNo(a, b, c));
	}

	@Test
	public void testReadAndLoad() {
		try {
			assertEquals(EstadisticaFeedback.ReadAndLoad().size(), 4);

			if (EstadisticaFeedback.ReadAndLoad().get(0) < 0 || EstadisticaFeedback.ReadAndLoad().get(0) > 5) {
				fail("Tiene que ser una puntuacion entre 0 y 5");
			}

			double porcentajeSi = EstadisticaFeedback.ReadAndLoad().get(1);
			double porcentajeNo = EstadisticaFeedback.ReadAndLoad().get(2);
			assertEquals(porcentajeSi + porcentajeNo, 100, 0);

			if (EstadisticaFeedback.ReadAndLoad().get(3) < 0 || EstadisticaFeedback.ReadAndLoad().get(3) % 1 != 0) {
				fail("No puede ser menor que 0, y tiene que ser un numero entero");
			}
		} catch (RWException e) {
			e.printStackTrace();
		}
	}

}
