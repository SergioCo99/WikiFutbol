package clases;

/**
 * Clase para la creacion de feedback
 *
 * @author sergiolopez
 *
 */
public class Feedback {

	/**
	 * Recomendacion si o no
	 *
	 * @author sergi
	 *
	 */
	public enum Recomendacion {
		si, no
	}

	private int id;
	private String usuario; // Nos referimos al correo, que es unico e identificara mejor al usuario
	private int valoracion;
	private Recomendacion recomendacion;
	private String opinion;

	/**
	 * Crea un feedback con su respectivos atributos
	 *
	 * @param id            del feedback
	 * @param usuario       del feedback
	 * @param valoracion    del feedback
	 * @param recomendacion del feedback
	 * @param opinion       del feedback
	 */
	public Feedback(int id, String usuario, int valoracion, Recomendacion recomendacion, String opinion) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.valoracion = valoracion;
		this.recomendacion = recomendacion;
		this.opinion = opinion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Recomendacion getRecomendacion() {
		return recomendacion;
	}

	public void setRecomendacion(Recomendacion recomendacion) {
		this.recomendacion = recomendacion;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", usuario=" + usuario + ", valoracion=" + valoracion + ", recomendacion="
				+ recomendacion + ", opinion=" + opinion + "]";
	}

}
