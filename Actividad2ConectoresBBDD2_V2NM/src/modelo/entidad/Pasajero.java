package modelo.entidad;
/**
 * Clase que representa a un pasajero asociado a un coche.
 * 
 * <p>Un coche puede tener varios pasajeros asociados, pero cada pasajero
 * está asociado a un solo coche.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 * @since 2024-02-08
 */
public class Pasajero {
	
	/* Tipo de relacion
	Un coche puede tener varios pasajeros asociados, 
	pero cada pasajero está asociado a un solo coche
*/
	private int id_pasajero;
	private String nombre;
	private int edad;
	private double peso;
	
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

	@Override
	public String toString() {
		return "Pasajero [id_pasajero=" + id_pasajero + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso
				+ "]";
	}

	

	
	
	
	
	
}
