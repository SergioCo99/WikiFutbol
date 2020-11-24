package pruebasYEjemplos;

import java.io.File;

public class RecursiveFileSearch {
	public static void main(String[] args) {
		boolean found = searchFile(new File("/tmp"), "10174");
		System.out.println(found);
	}

	private static boolean searchFile(File file, String search) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				boolean found = searchFile(f, search);
				if (found) {
					return true;
				}
			}
		} else {
			if (search.equals(file.getName())) {
				return true;
			}
		}
		return false;
	}
}
