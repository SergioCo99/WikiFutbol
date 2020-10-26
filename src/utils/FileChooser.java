package utils;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
	public static String Choose() {
		String userDir = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(userDir + "/Pictures");
		// FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "txt");
		/*
		 * La opcion "JPG & GIF Images" seLecciona jpg y gif, IGUAL hay que poner
		 * tambien png y mas archivos... TAMBIEN hay una opcion que es "Todos los
		 * archivos
		 */
		// chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		String path = null;

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

			path = chooser.getSelectedFile().getPath();
			System.out.println("Path: " + path);
			JOptionPane.showMessageDialog(chooser, "Archivo seleccionado: " + chooser.getSelectedFile().getName());

		}
		
		return path;
	}
	
	// este main es para pruebas, habria que quitarlo
		public static void main(String[] args) {
			Choose();
		}
}