package clases;

/**
 * Clase para la creacion de ciudades
 * 
 * @author sergiolopez
 *
 */
public class Ciudad {

	int id;
	String nombre;
	String pais;

	/**
	 * Crea una ciudad con sus diferentes parametros
	 * 
	 * @param id     de la ciudad
	 * @param nombre de la ciudad
	 * @param pais   de la ciudad
	 */
	public Ciudad(int id, String nombre, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
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

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
	}

}
