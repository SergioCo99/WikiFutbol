package utils;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileChooser {
	
	/**
	 * Este método sirve para seleccionar distintos tipos de archivos
	 * 
	 * @return El archivo seleccionado
	 */
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
		String path = "";

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

			path = chooser.getSelectedFile().getPath();
			System.out.println("Path: " + path);
			JOptionPane.showMessageDialog(chooser, "Archivo seleccionado: " + chooser.getSelectedFile().getName());

			String tipoDeArchivo = path.substring(path.lastIndexOf("."), path.length());
			String NombreDeArchivo = path.substring(path.lastIndexOf("\\"), path.length());
			System.out.println("Tipo de archivo: " + tipoDeArchivo + ", nombre: " + NombreDeArchivo);
		} else {
			System.out.println("nada seleccionado");
			path = "";
		}

		return path;
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		Choose();
	}
}