package pruebasYEjemplos;

import database.DBManager;
import database.DBManagerException;

public class magia {

	public static void magic() {
		try {
			System.out.println(DBManager.verTablas());
		} catch (DBManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		magic();
	}
	
}
