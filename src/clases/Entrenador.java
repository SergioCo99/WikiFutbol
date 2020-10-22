package clases;

public class Entrenador extends EmpleadoDeClub{

	String formacion;
	Mentalidad mentalidad;
	
	public Entrenador(int id, String nombre, int anyoNacimiento, int club, int ciudad,
			String formacion, Mentalidad mentalidad) {
		super(id, nombre, anyoNacimiento, club, ciudad);
		// TODO Auto-generated constructor stub
		
		this.formacion = formacion;
		this.mentalidad = mentalidad;
		
	}

	public String getFormacion() {
		return formacion;
	}

	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}

	public Mentalidad getMentalidad() {
		return mentalidad;
	}

	public void setMentalidad(Mentalidad mentalidad) {
		this.mentalidad = mentalidad;
	}

	@Override
	public String toString() {
		return "Entrenador [formacion=" + formacion + ", mentalidad=" + mentalidad + ", id=" + id + ", nombre=" + nombre
				+ ", anyoNacimiento=" + anyoNacimiento + ", club=" + club + ", ciudad=" + ciudad + ", getId()="
				+ getId() + ", getNombre()=" + getNombre() + ", getAnyoNacimiento()=" + getAnyoNacimiento()
				+ ", getClub()=" + getClub() + ", getCiudad()=" + getCiudad() + ", toString()=" + super.toString()
				+ "]";
	}	
	
	
}
