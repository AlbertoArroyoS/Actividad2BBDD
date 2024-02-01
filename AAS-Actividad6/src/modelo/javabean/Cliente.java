package modelo.javabean;

import java.util.Objects;

/**
 * Entidad Cliente. 
 * 
 * Con los atributos privados:
 * 	cif : String
 * 	String nombre : String
 * 	String domicilio : String
 * 	facturacionAnual : double
 * 	numeroEmpleados : int
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */

public class Cliente {
	
	//ATRIBUTOS PRIVADOS

	private String cif;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private double facturacionAnual;
	private int numeroEmpleados;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Cliente() {
		super();
	}
	
	//CONSTRUCTOR CON PARAMETROS
	public Cliente(String cif, String nombre, String apellidos, String domicilio, double facturacionAnual,
			int numeroEmpleados) {
		super();
		this.cif = cif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.facturacionAnual = facturacionAnual;
		this.numeroEmpleados = numeroEmpleados;
	}

	//GETTER Y SETTER
	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public double getFacturacionAnual() {
		return facturacionAnual;
	}


	public void setFacturacionAnual(double facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}


	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}


	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	//METODOS REESCRITOS
	@Override
	public String toString() {
		return "Cliente [cif=" + cif + ", nombre=" + nombre + ", apellidos=" + apellidos + ", domicilio=" + domicilio
				+ ", facturacionAnual=" + facturacionAnual + ", numeroEmpleados=" + numeroEmpleados + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cif);
	}

	//IGUAL SI TIENE EL MISMO cif
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cif, other.cif);
	}		
}
