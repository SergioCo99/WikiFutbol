package clases;

/**
 * Esta clase hace referencia al equipo del anyo \\\ es una clase basica de una
 * VIEW, NO DE UNA TABLA!!! SOLO SE VE, NO SE ACTUALIZA!!! SOLO GETTERS!!!
 *
 * @author sergiolopez
 *
 */
public class TeamOfTheYear_view {

	/**
	 * Posicion del jugador
	 *
	 * @author sergi
	 *
	 */
	public enum Posicion {
		Delantero, Centrocampista, Defensa, Portero
	}

	private int id_teamoftheyear;
	private int id_jugador;
	private String nombre_jugador;
	private Posicion posicion_jugador;
	private int voto_jugador;
	private int goles_jugador;

	/**
	 * Crea un equipo del año con los distintos atributos que lo forman
	 *
	 * @param id_teamoftheyear - id del equipo del año
	 * @param id_jugador       - id de uno de los jugadores que forma el equipo del
	 *                         año
	 * @param nombre_jugador   - nombre de uno de los jugadores que forma el equipo
	 *                         del año
	 * @param posicion         - posición de uno de los jugadores que forma el
	 *                         equipo del año
	 * @param voto_jugador     - votos que ha recivido uno de los jugadores que
	 *                         forma el equipo del año
	 * @param goles_jugador    - goles que ha marcado uno de los jugadores que forma
	 *                         el equipo del año
	 */
	public TeamOfTheYear_view(int id_teamoftheyear, int id_jugador, String nombre_jugador, Posicion posicion,
			int voto_jugador, int goles_jugador) {
		super();
		this.id_teamoftheyear = id_teamoftheyear;
		this.id_jugador = id_jugador;
		this.nombre_jugador = nombre_jugador;
		this.posicion_jugador = posicion;
		this.voto_jugador = voto_jugador;
		this.goles_jugador = goles_jugador;

	}

	public int getId_teamoftheyear() {
		return id_teamoftheyear;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public String getNombre_jugador() {
		return nombre_jugador;
	}

	public Posicion getPosicion_jugador() {
		return posicion_jugador;
	}

	public int getVoto_jugador() {
		return voto_jugador;
	}

	public int getGoles_jugador() {
		return goles_jugador;
	}

	@Override
	public String toString() {
		return "TeamOfTheYear_view [id_teamoftheyear=" + id_teamoftheyear + ", id_jugador=" + id_jugador
				+ ", nombre_jugador=" + nombre_jugador + ", posicion_jugador=" + posicion_jugador + ", voto_jugador="
				+ voto_jugador + ", goles_jugador=" + goles_jugador + "]";
	}

}
