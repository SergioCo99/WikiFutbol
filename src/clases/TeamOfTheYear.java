package clases;

public class TeamOfTheYear {

	int id;
	String jugador;

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
