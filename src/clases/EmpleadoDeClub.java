package clases;

/**
 * Clase para la creacion de empleados del club
 *
 * @author sergiolopez
 *
 */
public class EmpleadoDeClub {

	public int id;
	public String nombre;
	/**
	 * YYYY-MM-dd , por ejemplo "1990-06-25"
	 */
	public String fechaNac;
	public String club;
	public String ciudad;

	/**
	 * Crea un empleado del club con sus respectivos atributos
	 *
	 * @param id       del empleado del club
	 * @param nombre   del empleado del club
	 * @param fechaNac del empleado del club
	 * @param club     del empleado del club
	 * @param ciudad   del empleado del club
	 */
	public EmpleadoDeClub(int id, String nombre, String fechaNac, String club, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.club = club;
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

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "EmpleadoDeClub [id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", club=" + club
				+ ", ciudad=" + ciudad + "]";
	}

}
