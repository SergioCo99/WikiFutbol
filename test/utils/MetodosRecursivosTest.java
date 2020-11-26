package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MetodosRecursivosTest {

	private List<String> l = new ArrayList<String>();
	String s[] = { "z", "A", "C", "Z", "B", "X", "N", "E" };

	/**
	 * Añade valores a List l
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		new MetodosRecursivos();
		l.add("A");
		l.add("C");
		l.add("B");
		l.add("X");
		l.add("N");
		l.add("E");
	}

	/**
	 * Compara un array/list que antes no estaba ordenado pero ahora deberia
	 * estarlo, con un array/list con los mismos elementos que si esta ordenado
	 */
	@Test
	public void testOrdenarXStringRecursivo() {
		String s2[] = { "A", "B", "C", "E", "N", "X", "z", "Z" };
		List<String> l2 = new ArrayList<String>();
		l2.add("A");
		l2.add("B");
		l2.add("C");
		l2.add("E");
		l2.add("N");
		l2.add("X");

		MetodosRecursivos.ordenarArrayStringRecursivo(s, s.length);
		assertTrue(Arrays.equals(s2, s));

		MetodosRecursivos.ordenarListStringRecursivo(l, l.size());
		assertTrue(l.equals(l2));
	}

	/**
	 * Busca si existe el String "A" en el array/list, como existe devuelve el valor
	 * de la posicion en la que esta
	 *
	 * Busca si existe el String "no" en el array/list, como no existe devuelve el
	 * valor -1
	 */
	@Test
	public void testBinarySearchXStringRecursivo() {
		int existe = utils.MetodosRecursivos.binarySearchArrayStringRecursivo(s, 0, s.length, "A");
		assertNotEquals(-1, existe);

		int noexiste = utils.MetodosRecursivos.binarySearchArrayStringRecursivo(s, 0, s.length, "no");
		assertEquals(-1, noexiste);

		int existe2 = utils.MetodosRecursivos.binarySearchListStringRecursivo(l, 0, l.size(), "A");
		assertNotEquals(-1, existe2);

		int noexiste2 = utils.MetodosRecursivos.binarySearchListStringRecursivo(l, 0, l.size(), "no");
		assertEquals(-1, noexiste2);
	}

	/**
	 * Compara un array/list que antes no estaba ordenado pero ahora deberia
	 * estarlo, con un array/list con los mismos elementos que si esta ordenado
	 */
	@Test
	public void testMergeSortX() {
		String s2[] = { "A", "B", "C", "E", "N", "X", "Z", "z" };
		List<String> l2 = new ArrayList<String>();
		l2.add("A");
		l2.add("B");
		l2.add("C");
		l2.add("E");
		l2.add("N");
		l2.add("X");

		MetodosRecursivos.mergeSortArray(s);
		assertTrue(Arrays.equals(s2, s));

		MetodosRecursivos.mergeSortList(l);
		assertTrue(l.equals(l2));
	}

}
