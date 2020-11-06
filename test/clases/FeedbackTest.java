package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Feedback.Recomendacion;

/**
 * Test de la clase Feedback
 * 
 * @author sergi
 *
 */
public class FeedbackTest {

	private Feedback f;

	/**
	 * Crea un nuevo feedback
	 */
	@Before
	public void setUp() {
		f = new Feedback(1, "a", /* Valoracion.CINCO */ 5, Recomendacion.si, "Correcto");
	}

	/**
	 * Comprueba que getId() funciona correctamente devolviendo el id del feedback
	 */
	@Test
	public void testId() {
		f.setId(1);
		assertEquals(1, f.getId());
	}

	/**
	 * Comprueba que getUsuario() funciona correctamente devolviendo el usuario del
	 * feedback
	 */
	@Test
	public void testUsuario() {
		f.setUsuario("a");
		assertEquals("a", f.getUsuario());
	}

	/**
	 * Comprueba que getValoracion() funciona correctamente devolviendo la
	 * valoracion del feedback
	 */
	@Test
	public void testValoracion() {
		f.setValoracion(/* Valoracion.CINCO */ 5);
		assertEquals(/* Valoracion.CINCO */ 5, f.getValoracion());
	}

	/**
	 * Comprueba que getRecomendacion() funciona correctamente devolviendo el si o
	 * el no de la recomendacion
	 */
	@Test
	public void testRecomendacion() {
		f.setRecomendacion(Recomendacion.si);
		assertEquals(Recomendacion.si, f.getRecomendacion());
	}

	/**
	 * Comprueba que getOpinion() funciona correctamente devolviendo la opinion del
	 * feedback
	 */
	@Test
	public void testOpinion() {
		f.setOpinion("Correcto");
		assertEquals("Correcto", f.getOpinion());
	}

	/**
	 * Comprueba el método toString() de la clase Feedback
	 */
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
