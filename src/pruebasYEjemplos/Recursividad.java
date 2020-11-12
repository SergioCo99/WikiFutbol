package pruebasYEjemplos;

public class Recursividad {

	public static int p(int n) {
		if (n == 0) {
			return n;
		}

		return n = n + p(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(p(5));
	}

}
