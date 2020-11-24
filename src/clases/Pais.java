package clases;

/**
 * Clase para la creacion de paises
 *
 * @author sergiolopez
 *
 */
public class Pais {

	int id;
	String nombre;
	String capital;
	String gentilicio;
	String idioma;

	/**
	 * @param id         ID del pais
	 * @param nombre     Nombre del pais
	 * @param capital    Capital del pais
	 * @param gentilicio Gentilicio del pais
	 * @param idioma     Idioma del pais
	 */
	public Pais(int id, String nombre, String capital, String gentilicio, String idioma) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.capital = capital;
		this.gentilicio = gentilicio;
		this.idioma = idioma;
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

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getGentilicio() {
		return gentilicio;
	}

	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", capital=" + capital + ", gentilicio=" + gentilicio
				+ ", idioma=" + idioma + "]";
	}

}