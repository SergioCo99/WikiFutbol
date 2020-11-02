package clases;

/**
 * Clase para la creación de paises
 * 
 * @author sergiolopez
 *
 */
public class Pais {

	int id;
	String nombre;

	/**
	 * Crea paises con sus respectivos atributos
	 * 
	 * @param id     del pais
	 * @param nombre del pais
	 */
	public Pais(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + "]";
	}

}
