package clases;

public class Feedback {

	public enum Recomendacion {
		si, no
	}

	int id;
	String usuario;
	// en realidad es el correo, que es unico e identificara mejor al usuario
	int valoracion;
	Recomendacion recomendacion;
	String opinion;

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
