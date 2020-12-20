package clases;

/**
 * Clase para la creacion de Jugadores
 *
 * @author sergiolopez
 *
 */
public class Jugador extends EmpleadoDeClub {

	/**
	 * Pie favorito del jugador
	 *
	 * @author sergi
	 *
	 */
	public enum PieFav {
		Diestro, Zurdo, Ambidiestro
	}

	/**
	 * Posicion del jugador
	 *
	 * @author sergi
	 *
	 */
	public enum Posicion {
		Delantero, Centrocampista, Defensa, Portero
	}

	private Posicion posicion;
	private int dorsal;
	private int goles;
	private int altura;
	private int peso;
	private PieFav piefav;
	/**
	 * es distinta valoracion a la de Feedback, NO es enum
	 */
	private int valoracion;
	private String descripcion;
	private int voto;

	/**
	 * Crea un jugador con sus respectivos atributos
	 *
	 * @param id          del jugador
	 * @param nombre      del jugador
	 * @param fechaNac    del jugador
	 * @param club        del jugador
	 * @param ciudad      del jugador
	 * @param posicion    del jugador
	 * @param dorsal      del jugador
	 * @param goles       del jugador
	 * @param altura      del jugador
	 * @param peso        del jugador
	 * @param piefav      del jugador
	 * @param valoracion  del jugador
	 * @param descripcion del jugador
	 * @param voto        del jugador
	 */
	public Jugador(int id, String nombre, String fechaNac, String club, String ciudad, Posicion posicion, int dorsal,
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
		return "Jugador [id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", club=" + club + ", ciudad="
				+ ciudad + ", posicion=" + posicion + ", dorsal=" + dorsal + ", goles=" + goles + ", altura=" + altura
				+ ", peso=" + peso + ", piefav=" + piefav + ", valoracion=" + valoracion + ", descripcion="
				+ descripcion + ", voto=" + voto + "]";
	}

}
