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

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un array de String
	 *
	 * @param names
	 * @param left
	 * @param right
	 */
	public static void mergeArray(String[] names, String[] left, String[] right) {
		int a = 0;
		int b = 0;
		for (int i = 0; i < names.length; i++) {
			if ((b >= right.length) || ((a < left.length) && (left[a].compareToIgnoreCase(right[b]) < 0))) {
				names[i] = left[a];
				a++;
			} else {
				names[i] = right[b];
				b++;
			}
		}
	}

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un array de String
	 *
	 * @param names
	 */
	public static void mergeSortArray(String[] names) {
		if (names.length >= 2) {
			String[] left = new String[names.length / 2];
			String[] right = new String[names.length - (names.length / 2)];

			for (int i = 0; i < left.length; i++) {
				left[i] = names[i];
			}

			for (int i = 0; i < right.length; i++) {
				right[i] = names[i + (names.length / 2)];
			}

			mergeSortArray(left);
			mergeSortArray(right);
			mergeArray(names, left, right);
		}
	}

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un array de String
	 *
	 * @param names
	 * @param left
	 * @param right
	 */
	public static void mergeList(List<String> names, List<String> left, List<String> right) {
		int a = 0;
		int b = 0;
		for (int i = 0; i < names.size(); i++) {
			if ((b >= right.size()) || ((a < left.size()) && (left.get(a).compareToIgnoreCase(right.get(b)) < 0))) {
				names.set(i, left.get(a));
				// names[i] = left[a];
				a++;
			} else {
				names.set(i, right.get(b));
				// names[i] = right[b];
				b++;
			}
		}
	}

	/**
	 * Metodo recursivo que ordena alfabeticamente (aA-zZ) un array de String
	 *
	 * @param names
	 */
	public static void mergeSortList(List<String> names) {
		if (names.size() >= 2) {
			List<String> left = new ArrayList<String>();
			for (int i = 0; i < (names.size() / 2); i++) {
				left.add(i, null);
			}
			List<String> right = new ArrayList<String>(names.size() - (names.size() / 2));
			for (int i = 0; i < (names.size() - (names.size() / 2)); i++) {
				right.add(i, null);
			}
			// String[] left = new String[names.size() / 2];
			// String[] right = new String[names.size() - names.size() / 2];

			for (int i = 0; i < left.size(); i++) {
				left.set(i, names.get(i));
				// left[i] = names[i];
			}

			for (int i = 0; i < right.size(); i++) {
				right.set(i, names.get(i + (names.size() / 2)));
				// right[i] = names[i + names.length / 2];
			}

			mergeSortList(left);
			mergeSortList(right);
			mergeList(names, left, right);
		}
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		String s[] = { "z", "A", "C", "Z", "B", "X", "N", "E" };
		List<String> l = new ArrayList<String>();
		l.add("A");
		l.add("C");
		l.add("B");
		l.add("X");
		l.add("N");
		l.add("E");

		System.out.println("s:" + Arrays.toString(s));
		mergeSortArray(s);
		System.out.println("s:" + Arrays.toString(s));

		System.out.println("l:" + l);
		mergeSortList(l);
		System.out.println("l:" + l);
	}
}
