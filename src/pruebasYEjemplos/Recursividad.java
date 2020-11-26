package pruebasYEjemplos;

public class Recursividad {
	public static void main(String[] args) {
		String[] ClassOne = { "Kring", "Panda", "Soliel", "Darryl", "Chan", "Matang", "Jollibee.", "Inasal" };
		String[] ClassTwo = { "Minnie", "Kitty", "Madonna", "Miley", "Zoom-zoom", "Cristine", "Bubbles", "Ara", "Rose",
				"Maria" };
		String[] names = new String[ClassOne.length + ClassTwo.length];

		mergeSort(ClassOne);
		mergeSort(ClassTwo);

		merge(names, ClassOne, ClassTwo);

		mergeSort(names);
		// Arrays.sort(names);

		for (String ClassThree : names) {
			System.out.println(ClassThree);
		}
	}

	public static void mergeSort(String[] names) {
		if (names.length >= 2) {
			String[] left = new String[names.length / 2];
			String[] right = new String[names.length - (names.length / 2)];

			for (int i = 0; i < left.length; i++) {
				left[i] = names[i];
			}

			for (int i = 0; i < right.length; i++) {
				right[i] = names[i + (names.length / 2)];
			}

			mergeSort(left);
			mergeSort(right);
			merge(names, left, right);
		}
	}

	public static void merge(String[] names, String[] left, String[] right) {
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

}