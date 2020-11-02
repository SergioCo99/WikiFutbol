package clases;

public class UsuarioVotacion {

	int id;
	String usuario; // Nos referimos al correo, que es unico e identificara mejor al usuario
	String delantero;
	String centrocampista;
	String defensa;
	String portero;

	public UsuarioVotacion(int id, String usuario, String delantero, String centrocampista, String defensa,
			String portero) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.delantero = delantero;
		this.centrocampista = centrocampista;
		this.defensa = defensa;
		this.portero = portero;
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

	public String getDelantero() {
		return delantero;
	}

	public void setDelantero(String delantero) {
		this.delantero = delantero;
	}

	public String getCentrocampista() {
		return centrocampista;
	}

	public void setCentrocampista(String centrocampista) {
		this.centrocampista = centrocampista;
	}

	public String getDefensa() {
		return defensa;
	}

	public void setDefensa(String defensa) {
		this.defensa = defensa;
	}

	public String getPortero() {
		return portero;
	}

	public void setPortero(String portero) {
		this.portero = portero;
	}

	@Override
	public String toString() {
		return "UsuarioVotacion [id=" + id + ", usuario=" + usuario + ", delantero=" + delantero + ", centrocampista="
				+ centrocampista + ", defensa=" + defensa + ", portero=" + portero + "]";
	}

}
