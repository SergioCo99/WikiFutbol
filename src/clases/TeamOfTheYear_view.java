package clases;

//no se si deberiamos hacerlo o es tonteria, pero:
// es una clase basica de una VIEW, NO DE UNA TABLA!!! SOLO SE VE, NO SE ACTUALIZA!!! SOLO GETTERS!!!
public class TeamOfTheYear_view {

	public enum Posicion {
		Delantero, Centrocampista, Defensa, Portero
	}

	int id_teamoftheyear;
	int id_jugador;
	String nombre_jugador;
	Posicion posicion_jugador;
	int voto_jugador;
	int goles_jugador;

	public TeamOfTheYear_view(int id_teamoftheyear, int id_jugador, String nombre_jugador, Posicion posicion_jugador,
			int voto_jugador, int goles_jugador) {
		super();
		this.id_teamoftheyear = id_teamoftheyear;
		this.id_jugador = id_jugador;
		this.nombre_jugador = nombre_jugador;
		this.posicion_jugador = posicion_jugador;
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
