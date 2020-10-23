package clases;

public class Entrenador extends EmpleadoDeClub {

	public enum Mentalidad {
		Defensiva, Equilibrada, Atacante
	}

	String formacion;
	Mentalidad mentalidad;

	public Entrenador(int id, String nombre, String fechaNac, String club, String ciudad, String formacion,
			Mentalidad mentalidad) {
		super(id, nombre, fechaNac, club, ciudad);
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
		return "Entrenador [id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", club=" + club + ", ciudad="
				+ ciudad + ", formacion=" + formacion + ", mentalidad=" + mentalidad + "]";
	}

}
