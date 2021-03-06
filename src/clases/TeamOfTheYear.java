package clases;

/**
 * Clase que hace referencia al equipo del anyo
 *
 * @author sergiolopez
 *
 */
public class TeamOfTheYear {

	private int id;
	private String jugador;

	/**
	 * Constructor del equipo del año
	 *
	 * @param id      - id del equipo del año
	 * @param jugador - jugador que entra en el equipo del año
	 */
	public TeamOfTheYear(int id, String jugador) {
		super();
		this.id = id;
		this.jugador = jugador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	@Override
	public String toString() {
		return "TeamOfTheYear [id=" + id + ", jugador=" + jugador + "]";
	}

}
