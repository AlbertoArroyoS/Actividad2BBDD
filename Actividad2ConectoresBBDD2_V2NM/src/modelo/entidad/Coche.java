package modelo.entidad;

public class Coche {
	
	//Atributos privados
	private int id;
	private String marca;
	private String modelo;
	private int fabYear;
	private int kilometros;
	
	//requerimiento 2 - relacion 1:N 
	private Pasajero pasajero;
	
	
	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	//Constructor por defecto
	public Coche() {
		super();
	}

	//Getters y Setters publicos
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getFabYear() {
		return fabYear;
	}


	public void setFabYear(int fabYear) {
		this.fabYear = fabYear;
	}


	public int getKilometros() {
		return kilometros;
	}


	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	//toString
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", fabYear=" + fabYear + ", kilometros="
				+ kilometros + "]";
	}

	
}
