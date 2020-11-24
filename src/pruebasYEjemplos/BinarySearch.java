package pruebasYEjemplos;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	/**
	 * Busqueda binaria de un list de String YA ORDENADO (aA-zZ) alfabeticamente
	 *
	 * @param list
	 * @param left
	 * @param right
	 * @param palabra
	 * @return
	 */
	public static int binarySearch(List<String> list, int left, int right, String palabra) {
		if ((right >= left) && (left < (list.size() - 1))) {
			int mid = left + ((right - left) / 2);
			int res = palabra.compareTo(list.get(mid));
			if (res == 0) {
				return mid;
			}
			if (res > 0) {
				return binarySearch(list, mid + 1, right, palabra);
			}
			return binarySearch(list, left, mid - 1, palabra);
		}
		return -1;
	}

	public static void main(String args[]) {
		List<String> l = new ArrayList<String>();
		l.add("A");
		l.add("C");
		l.add("B");
		l.add("X");
		l.add("N");
		l.add("E");
		System.out.println(l);
		utils.MetodosRecursivos.ordenarListStringRecursivo(l, l.size());
		System.out.println(l);
		int result = binarySearch(l, 0, l.size() - 1, "E");
		if (result == -1) {
			System.out.println("Element not present");
		} else {
			System.out.println("Element found at index " + result);
		}
	}
}
