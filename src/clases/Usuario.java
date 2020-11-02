package clases;

/**
 * Clase que hace referencia a los usuarios
 * 
 * @author sergiolopez
 *
 */
public class Usuario {

	int id;
	String nombre;
	String contrasena;
	String correo;
	int admin;
	String fechaNac; /* YYYY-MM-dd , por ejemplo "1990-06-25" */

	/**
	 * Crea un usuario con sus respectivos atributos
	 * 
	 * @param id         - Id del usuario
	 * @param nombre     - Nombre del usuario
	 * @param contrasena - Contraseña del usuario
	 * @param correo     - Correo del usuario
	 * @param admin      - Dice si el usuario es admin o no
	 * @param fechaNac   - Fecha de nacimiento del usuario
	 */
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
