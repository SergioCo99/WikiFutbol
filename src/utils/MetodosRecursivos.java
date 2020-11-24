package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Metodos:
 *
 * ordenarArrayStringRecursivo(String array[], int arrayLength) /////
 * ordenarListStringRecursivo(List<String> list, int listSize) /////
 * binarySearchArrayStringRecursivo(String arr[], int left, int right, String
 * palabra) ///// binarySearchListStringRecursivo(List<String> list, int left,
 * int right, String palabra)
 */
public class MetodosRecursivos {

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un array de String
	 *
	 * @param array
	 * @param arrayLength
	 */
	public static void ordenarArrayStringRecursivo(String array[], int arrayLength) {
		// Base case
		if (arrayLength == 1) {
			return;
		}

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
		if (listSize == 1) {
			return;
		}

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

	/**
	 * Busqueda binaria de un array de String YA ORDENADO (aA-zZ) alfabeticamente
	 *
	 * @param arr     array
	 * @param left    primera casilla, normalmente 0
	 * @param right   ultima casilla, normalmente arr.length -1
	 * @param palabra palabra que se quiere buscar
	 * @return
	 */
	public static int binarySearchArrayStringRecursivo(String arr[], int left, int right, String palabra) {
		// EXISTE
		if ((right >= left) && (left < (arr.length - 1))) {
			int mid = left + ((right - left) / 2);
			int res = palabra.compareTo(arr[mid]);
			if (res == 0) {
				return mid;
			}
			if (res > 0) {
				return binarySearchArrayStringRecursivo(arr, mid + 1, right, palabra);
			} else {
				return binarySearchArrayStringRecursivo(arr, left, mid - 1, palabra);
			}
		}
		// NO EXISTE
		return -1;
	}

	/**
	 * Busqueda binaria de un list de String YA ORDENADO (aA-zZ) alfabeticamente
	 *
	 * @param list    List<String> x
	 * @param left    primera casilla, normalmente 0
	 * @param right   ultima casilla, normalmente list.size -1
	 * @param palabra palabra que se quiere buscar
	 * @return
	 */
	public static int binarySearchListStringRecursivo(List<String> list, int left, int right, String palabra) {
		// EXISTE
		if ((right >= left) && (left < (list.size() - 1))) {
			int mid = left + ((right - left) / 2);
			int res = palabra.compareTo(list.get(mid));
			if (res == 0) {
				return mid;
			}
			if (res > 0) {
				return binarySearchListStringRecursivo(list, mid + 1, right, palabra);
			} else {
				return binarySearchListStringRecursivo(list, left, mid - 1, palabra);
			}
		}
		// NO EXISTE
		return -1;
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		String s[] = { "z", "A", "C", "Z", "B", "X", "N", "E" };
		System.out.println(Arrays.toString(s));
		ordenarArrayStringRecursivo(s, s.length);
		System.out.println(Arrays.toString(s));
		int n = binarySearchArrayStringRecursivo(s, 0, s.length, "C");
		System.out.println(n);

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
		int n2 = binarySearchListStringRecursivo(l, 0, l.size(), "E");
		System.out.println(n2);
	}
}
