package clases;

public class Estadio {
	
	private int id;
	private String nombre;
	private int aforo;
	private int anyoCreacion;
	private int ciudad;
	
	
	public Estadio(int id, String nombre, int aforo, int anyoCreacion, int ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aforo = aforo;
		this.anyoCreacion = anyoCreacion;
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
	public int getAforo() {
		return aforo;
	}
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	public int getAnyoCreacion() {
		return anyoCreacion;
	}
	public void setAnyoCreacion(int anyoCreacion) {
		this.anyoCreacion = anyoCreacion;
	}
	public int getCiudad() {
		return ciudad;
	}
	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Estadio [id=" + id + ", nombre=" + nombre + ", aforo=" + aforo + ", anyoCreacion=" + anyoCreacion
				+ ", ciudad=" + ciudad + "]";
	}

	
	
}
