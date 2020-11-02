package clases;

/**
 * Clase para la creación de estadios
 * 
 * @author sergiolopez
 *
 */
public class Estadio {

	int id;
	String nombre;
	int aforo;
	int anyoCreacion;
	String ciudad;

	/**
	 * Crea un estadio con sus respectivos atributos
	 * 
	 * @param id           del estadio
	 * @param nombre       del estadio
	 * @param aforo        del estadio
	 * @param anyoCreacion del estadio
	 * @param ciudad       del estadio
	 */
	public Estadio(int id, String nombre, int aforo, int anyoCreacion, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aforo = aforo;
		this.anyoCreacion = anyoCreacion;
		this.ciudad = ciudad;
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

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public int getAnyoCreacion() {
		return anyoCreacion;
	}

	public void setAnyoCreacion(int anyoCreacion) {
		this.anyoCreacion = anyoCreacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Estadio [id=" + id + ", nombre=" + nombre + ", aforo=" + aforo + ", anyoCreacion=" + anyoCreacion
				+ ", ciudad=" + ciudad + "]";
	}

}
