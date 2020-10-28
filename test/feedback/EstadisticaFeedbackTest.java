package feedback;

import static org.junit.Assert.*;

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
		
		double ans1 = (a / c) *100;
		double ans2 = (b / c) *100;
		
		ArrayList<Double> arr = new ArrayList<>();
		arr.add(0, ans1);
		arr.add(1, ans2);
		
		assertEquals(arr, EstadisticaFeedback.siNo(a, b, c));	
	}

	@Test
	public void TestReadAndLoad() {

	}

}
