package clases;

/**
 * Clase para la creacion de entrenadores
 *
 * @author sergiolopez
 *
 */
public class Entrenador extends EmpleadoDeClub {

	/**
	 * Distintas opciones de mentalidad del Entrenador
	 * 
	 * @author sergi
	 *
	 */
	public enum Mentalidad {
		Defensiva, Equilibrada, Atacante
	}

	String formacion;
	Mentalidad mentalidad;

	/**
	 * Crea un entrenador con sus respectivos atributos
	 *
	 * @param id         del entrenador
	 * @param nombre     del entrenador
	 * @param fechaNac   del entrenador
	 * @param club       del entrenador
	 * @param ciudad     del entrenador
	 * @param formacion  del entrenador
	 * @param mentalidad del entrenador
	 */
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
