package pruebasYEjemplos;

import database.DBManager;
import database.DBManagerException;

public class magia {

	static String[] tablas;

	public static String[] prepararVentanaDescargarDatos() {
		try {
			for (int i = 0; i < database.DBManager.verTablas().size(); i++) {
				System.out.println(DBManager.verTablas().get(i));
				tablas[i] = DBManager.verTablas().get(i).toString();
			}
		} catch (DBManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tablas;
	}

	public static void main(String[] args) {
		System.out.println(prepararVentanaDescargarDatos());
	}
}
