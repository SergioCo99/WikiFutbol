package clases;

/**
 * Clase para la creacion de ciudades
 *
 * @author sergiolopez
 *
 */
public class Ciudad {

	private int id;
	private String nombre;
	private String pais;
	private int poblacion;
	private String gentilicio;
	private String provincia;
	private String comAutonoma;

	/**
	 * @param id          ID de la ciudad
	 * @param nombre      Nombre de la ciudad
	 * @param pais        Pais de la ciudad
	 * @param poblacion   Poblacion de la ciudad
	 * @param gentilicio  Gentilicio de los ciudadanos
	 * @param provincia   Provincia de la ciudad
	 * @param comAutonoma Comunidad Autonoma de la ciudad
	 */
	public Ciudad(int id, String nombre, String pais, int poblacion, String gentilicio, String provincia,
			String comAutonoma) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.poblacion = poblacion;
		this.gentilicio = gentilicio;
		this.provincia = provincia;
		this.comAutonoma = comAutonoma;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public String getGentilicio() {
		return gentilicio;
	}

	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComAutonoma() {
		return comAutonoma;
	}

	public void setComAutonoma(String comAutonoma) {
		this.comAutonoma = comAutonoma;
	}

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", poblacion=" + poblacion
				+ ", gentilicio=" + gentilicio + ", provincia=" + provincia + ", comAutonoma=" + comAutonoma + "]";
	}

}
