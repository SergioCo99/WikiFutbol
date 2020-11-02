package clases;

/** Clase para la creacion de clubes
 * @author sergiolopez
 *
 */
public class Club {

	int id;
	String nombre;
	String ciudad;
	String estadio;
	int anyoCreacion;
	String palmares;
	String entrenador;

	/** Crea un club con sus diferentes paramentros
	 * @param id del club
	 * @param nombre del club
	 * @param ciudad del club
	 * @param estadio del club
	 * @param anyoCreacion del club
	 * @param palmares del club
	 * @param entrenador del club
	 */
	public Club(int id, String nombre, String ciudad, String estadio, int anyoCreacion, String palmares,
			String entrenador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.estadio = estadio;
		this.anyoCreacion = anyoCreacion;
		this.palmares = palmares;
		this.entrenador = entrenador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public int getAnyoCreacion() {
		return anyoCreacion;
	}

	public void setAnyoCreacion(int anyoCreacion) {
		this.anyoCreacion = anyoCreacion;
	}

	public String getPalmares() {
		return palmares;
	}

	public void setPalmares(String palmares) {
		this.palmares = palmares;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", estadio=" + estadio
				+ ", anyoCreacion=" + anyoCreacion + ", palmares=" + palmares + ", entrenador=" + entrenador + "]";
	}

}
