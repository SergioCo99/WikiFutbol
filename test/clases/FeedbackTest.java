package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Feedback.Recomendacion;

public class FeedbackTest {

	private Feedback f;

	@Before
	public void setUp() {
		f = new Feedback(1, "a", /* Valoracion.CINCO */ 5, Recomendacion.si, "Correcto");
	}

	@Test
	public void testId() {
		f.setId(1);
		assertEquals(1, f.getId());
	}

	@Test
	public void testUsuario() {
		f.setUsuario("a");
		assertEquals("a", f.getUsuario());
	}

	@Test
	public void testValoracion() {
		f.setValoracion(/* Valoracion.CINCO */ 5);
		assertEquals(/* Valoracion.CINCO */ 5, f.getValoracion());
	}

	@Test
	public void testRecomendacion() {
		f.setRecomendacion(Recomendacion.si);
		assertEquals(Recomendacion.si, f.getRecomendacion());
	}

	@Test
	public void testOpinion() {
		f.setOpinion("Correcto");
		assertEquals("Correcto", f.getOpinion());
	}

	@Test
	public void metodoToString() {

		f.setId(1);
		f.setUsuario("a");
		f.setValoracion(/* Valoracion.CINCO */ 5);
		f.setRecomendacion(Recomendacion.si);
		f.setOpinion("Correcto");

		System.out.println(f.toString());

		assertEquals("Feedback [id=1, usuario=a, valoracion=5, recomendacion=si, opinion=Correcto]", f.toString());
	}
}
