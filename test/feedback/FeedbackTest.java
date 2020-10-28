package feedback;

import static org.junit.Assert.*;

import org.junit.Test;

public class FeedbackTest {

	@Test
	public void MediaPuntuacion() {
		System.out.println("testMediaPuntuacion");
		double expected = EstadisticaFeedback.mediaPuntuacion(4, 2);
		double actual = 4 / 2;
		double delta = 0;
		assertEquals(expected, actual, delta);
	}
	
	@Test
	public void SiNO() {
		System.out.println("testSiNo");
		double a = 3;
		double b = 5;
		double c = 8;
		double delta = 0; 
		EstadisticaFeedback.siNo(a, b, c);
		assertEquals(a + b, c, delta);
	}

}
