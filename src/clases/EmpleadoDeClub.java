package clases;

public class EmpleadoDeClub {

	int id;
	String nombre;
	String fechaNac;
	int club;
	int ciudad;

	public EmpleadoDeClub(int id, String nombre, String fechaNac, int club, int ciudad) {
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

	public int getClub() {
		return club;
	}

	public void setClub(int club) {
		this.club = club;
	}

	public int getCiudad() {
		return ciudad;
	}

	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "EmpleadoDeClub [id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", club=" + club
				+ ", ciudad=" + ciudad + "]";
	}

}
