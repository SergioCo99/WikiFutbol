package database;

/**
 * Clase DBManagerException
 *
 * @author sergi
 *
 */
public class DBManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * En caso de error nos avisa con un mensaje
	 *
	 * @param message Mensaje del error
	 */
	public DBManagerException(String message) {
		super(message);
	}

	/**
	 * En caso de error nos avisa con un mensaje y la causa
	 *
	 * @param message Mensaje del error
	 * @param cause   Causa del error
	 */
	public DBManagerException(String message, Throwable cause) {
		super(message, cause);
	}

}
