package cd;

public class DBManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	public DBManagerException(String message) {
		super(message);
	}

	public DBManagerException(String message, Throwable cause) {
		super(message, cause);
	}

}
