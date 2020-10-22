package clases;

public class Jugador extends EmpleadoDeClub {

	public enum PieFav {
		Diestro, Zurdo, Ambidiestro
	}

	public enum Posicion {
		Delantero, Centrocampista, Defensa, Portero
	}

	Posicion posicion;
	int dorsal;
	int goles;
	int altura;
	int peso;
	PieFav piefav;
	int valoracion;
	String descripcion;
	int voto;

	public Jugador(int id, String nombre, String fechaNac, int club, int ciudad, Posicion posicion, int dorsal,
			int goles, int altura, int peso, PieFav piefav, int valoracion, String descripcion, int voto) {
		super(id, nombre, fechaNac, club, ciudad);
		this.posicion = posicion;
		this.dorsal = dorsal;
		this.goles = goles;
		this.altura = altura;
		this.peso = peso;
		this.piefav = piefav;
		this.valoracion = valoracion;
		this.descripcion = descripcion;
		this.voto = voto;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public PieFav getPiefav() {
		return piefav;
	}

	public void setPiefav(PieFav piefav) {
		this.piefav = piefav;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "Jugador [posicion=" + posicion + ", dorsal=" + dorsal + ", goles=" + goles + ", altura=" + altura
				+ ", peso=" + peso + ", piefav=" + piefav + ", valoracion=" + valoracion + ", descripcion="
				+ descripcion + ", voto=" + voto + ", id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac
				+ ", club=" + club + ", ciudad=" + ciudad + "]";
	}

}
