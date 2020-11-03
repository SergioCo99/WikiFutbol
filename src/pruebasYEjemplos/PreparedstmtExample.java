package pruebasYEjemplos;

import database.DBManager;
import database.DBManagerException;

public class PreparedstmtExample {

	public static void main(String[] args) throws DBManagerException {
		DBManager.registrarFeedback("a@gmail.com", "4", "si", "op");

		System.out.println(
				DBManager.getIdUsuario("eneko.perez23@gmail.com")
		);
	}

}
