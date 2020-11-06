package feedback;

/**
 * Clase RWException
 * @author sergi
 *
 */
public class RWException extends Exception {

	/**
	 * clase para una excepcion propia
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nos lanza un mensaje en caso de excepcion
	 *
	 * @param mensaje Mensaje en caso de excepcion
	 * @param e       La clase Throwable representa a todas las excepciones que
	 *                pueden ocurrir en el programa
	 */
	public RWException(String mensaje, Throwable e) {
		super(mensaje, e);
	}
}
