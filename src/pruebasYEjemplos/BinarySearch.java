package pruebasYEjemplos;

import java.util.Arrays;

public class BinarySearch {

	/**
	 * Busqueda binaria de un array de String YA ORDENADO (aA-zZ) alfabeticamente
	 * 
	 * @param arr     array
	 * @param left    primera casilla, normalmente 0
	 * @param right   ultima casilla, normalmente arr.length -1
	 * @param palabra palabra que se quiere buscar
	 * @return
	 */
	public static int binarySearch(String arr[], int left, int right, String palabra) {
		if (right >= left && left < arr.length - 1) {
			int mid = left + (right - left) / 2;
			int res = palabra.compareTo(arr[mid]);
			if (res == 0) {
				return mid;
			}
			if (res > 0) {
				return binarySearch(arr, left, mid - 1, palabra);
			}
			return binarySearch(arr, mid + 1, right, palabra);
		}
		return -1;
	}

	public static void main(String args[]) {
		String arr[] = { "z", "A", "C", "Z", "B", "X", "N", "E" };
		String s2[] = { "", "A", "B", "C", "D" };
		int result = binarySearch(s2, 0, s2.length - 1, "A");
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at index " + result);
	}
}
