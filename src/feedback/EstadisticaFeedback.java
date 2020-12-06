package feedback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Estadistica feedback
 *
 * @author sergi
 *
 */
public class EstadisticaFeedback {

	private static double ans;

	/**
	 * Este metodo sirve para realizar una puntuacion media de los feedbacks que
	 * realizan los usuarios
	 *
	 * @param a Puntuacion a
	 * @param b Puntuacion b
	 * @return El valor de la puntuacion media
	 */
	public static double mediaPuntuacion(double a, double b) {
		if (b == 0) {
			throw new ArithmeticException("No puedes dividir por cero");
		}
		ans = a / b;
		// System.out.println("Media de puntuacion: " + String.format("%.2f", ans) +
		// "/5,00");
		return ans;
	}

	/**
	 * Resultado de la recomendacion "si" o "no" que realizan los usuarios
	 *
	 * @param a Recomendacion Si
	 * @param b Recomendacion No
	 * @param c c
	 * @return Devuelve el array con los resultado de las recomendaciones
	 */
	public static Map<Integer, Double> siNo(double a, double b, double c) {
		if (c == 0) {
			throw new ArithmeticException("No puedes dividir por cero");
		}
		double ans1 = a / c;
		ans1 = ans1 * 100;
		// System.out.println("Si: " + String.format("%.2f", ans1) + " %.");

		double ans2 = b / c;
		ans2 = ans2 * 100;
		// System.out.println("No: " + String.format("%.2f", ans2) + " %.");

		Map<Integer, Double> map = new HashMap<Integer, Double>();
		map.put(0, ans1);
		map.put(1, ans2);
		return map;
	}

	/**
	 * Este metodo sirve para recibir y guardar el Feedback de los usuarios en un
	 * .log Recibe y guarda la valoracion del usuario (del 1 al 5) y la
	 * recomendacion (si o no) Ademas, con esos datos realiza la media de valoracion
	 * y el porcentaje de si o no
	 *
	 * @return Array con los resultados de la media de valoracion y el porcentaje si
	 *         o no
	 * @throws RWException En caso de error
	 */
	public static Map<Integer, Double> ReadAndLoad() throws RWException {
		File f1 = new File("FeedBackLog.log"); // Creation of File Descriptor for input file
		String[] words = null; // Intialize the word Array

		String s;
		String si = "si"; // Input word to be searched
		double countSi = 0;
		String no = "no";
		double countNo = 0;
		double countSN = 0;

		String uno = "1";
		String dos = "2";
		String tres = "3";
		String cuatro = "4";
		String cinco = "5";
		double countNum = 0;
		double mediaNum = 0; // Intialize the word to zero

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
			// Creation of File Reader object

			while ((s = br.readLine()) != null) { // Reading Content from the file
				words = s.split(" "); // Split the word using space
				for (String word : words) {
					// numero
					if (word.equals(uno) || word.equals(dos) || word.equals(tres) || word.equals(cuatro)
							|| word.equals(cinco)) { // Search for the given word
						mediaNum++; // If Present increase the count by one
					}
					if (word.equals(uno)) {
						countNum = countNum + 1;
					} else if (word.equals(dos)) {
						countNum = countNum + 2;
					} else if (word.equals(tres)) {
						countNum = countNum + 3;
					} else if (word.equals(cuatro)) {
						countNum = countNum + 4;
					} else if (word.equals(cinco)) {
						countNum = countNum + 5;
					}
					// si o no
					if (word.equals(si) || word.equals(no)) {
						countSN++;
					}
					if (word.equals(si)) {
						countSi++;
					} else if (word.equals(no)) {
						countNo++;
					}
				}
			}
			mediaPuntuacion(countNum, mediaNum);
			siNo(countSi, countNo, countSN);
			br.close();
		} catch (FileNotFoundException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			throw new RWException("El archivo no fue encontrado", e);
		} catch (IOException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			throw new RWException("Error de input/output", e);
		}

		// Aqui empieza la segunda parte
		Map<Integer, Double> map = new HashMap<Integer, Double>();

		for (int i = 0; i < siNo(countSi, countNo, countSN).size(); i++) {
			// filas 2 y 3 (i+2 y i+3 -> 0+2 y 1+3)
			map.put(i + 2, siNo(countSi, countNo, countSN).get(i));
		}
		map.put(1, mediaPuntuacion(countNum, mediaNum)); // fila 1
		map.put(4, mediaNum); // fila 4

		return map;
	}

}
