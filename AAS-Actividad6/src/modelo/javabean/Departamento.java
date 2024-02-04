package modelo.javabean;

import java.util.Objects;

/**
 * Entidad Departamento. 
 * 
 * Con los atributos privados:
 * 	idDepar : int
 * 	nombre : String
 * 	direccion : String
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */

public class Departamento {
	
	//ATRIBUTOS PRIVADOS
	private int idDepar;
	private String nombre;
	private String direccion;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Departamento() {
		super();
	}

	//CONSTRUCTOR CON PARAMETROS
	public Departamento(int idDepar, String nombre, String direccion) {
		super();
		this.idDepar = idDepar;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	//GETTER Y SETTER
	public int getIdDepar() {
		return idDepar;
	}


	public void setIdDepar(int idDepar) {
		this.idDepar = idDepar;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	//METODOS REESCRITOS
	@Override
	public int hashCode() {
		return Objects.hash(idDepar);
	}

	//IGUAL SI TIENE EL MISMO idDepar
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return idDepar == other.idDepar;
	}

	@Override
	public String toString() {
		return "Departamento [idDepar=" + idDepar + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
}
