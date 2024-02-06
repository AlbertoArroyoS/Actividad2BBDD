package modelo.entidad;

public class Pasajero {

	private int id_pasajero;
	private String nombre;
	private int edad;
	private double peso;
	private Coche coche;
	
	public Pasajero() {
		super();
	}

	public int getId_pasajero() {
		return id_pasajero;
	}

	public void setId_pasajero(int id_pasajero) {
		this.id_pasajero = id_pasajero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	
	@Override
	public String toString() {
		return "Pasajero [id_pasajero=" + id_pasajero + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso
				+ ", coche=" + coche + "]";
	}
	
	
	
	
}
