package clases;

public class Usuario {

	int id;
	String nombre;
	String contrasena;
	String correo;
	int admin;
	String fechaNac;

	public Usuario(int id, String nombre, String contrasena, String correo, int admin, String fechaNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.correo = correo;
		this.admin = admin;
		this.fechaNac = fechaNac;
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasena=" + contrasena + ", correo=" + correo
				+ ", admin=" + admin + ", fechaNac=" + fechaNac + "]";
	}

}
