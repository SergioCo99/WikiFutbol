package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetodosRecursivos {

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un array de String
	 * 
	 * @param array
	 * @param arrayLength
	 */
	public static void ordenarArrayStringRecursivo(String array[], int arrayLength) {
		// Base case
		if (arrayLength == 1)
			return;

		for (int i = 0; i < (array.length - 1); i++) {
			if (array[i].compareToIgnoreCase(array[i + 1]) > 0) {
				// Intercambiamos valores
				String variableauxiliar = array[i];
				array[i] = array[i + 1];
				array[i + 1] = variableauxiliar;
			}
		}
		ordenarArrayStringRecursivo(array, arrayLength - 1);
	}

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un List de String
	 * 
	 * @param list
	 * @param listSize
	 */
	public static void ordenarListStringRecursivo(List<String> list, int listSize) {
		// Base case
		if (listSize == 1)
			return;

		for (int i = 0; i < (list.size() - 1); i++) {
			if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
				// Intercambiamos valores
				String variableauxiliar = list.get(i);
				list.set(i, list.get(i + 1));
				list.set(i + 1, variableauxiliar);
			}
		}
		ordenarListStringRecursivo(list, listSize - 1);
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("A");
		l.add("C");
		l.add("B");
		l.add("X");
		l.add("N");
		l.add("E");
		System.out.println(l);
		ordenarListStringRecursivo(l, l.size());
		System.out.println(l);

		String s[] = { "z", "A", "C", "Z", "B", "X", "N", "E" };
		System.out.println(Arrays.toString(s));
		ordenarArrayStringRecursivo(s, s.length);
		System.out.println(Arrays.toString(s));

	}
}
