package modelo.javabean;

import java.util.Objects;

/**
 * Entidad Perfil.
 * 
 * Con los atributos privados:
 * 	idPerfil : int
 * 	nombre : String
 * 	precioHora : double
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */

public class Perfil {
	
	//ATRIBUTOS PRIVADOS
	private int idPerfil;
	private String nombre;
	private double precioHora;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Perfil() {
		super();
	}

	//CONSTRUCTOR CON PARAMETROS
	public Perfil(int idPerfil, String nombre, double precioHora) {
		super();
		this.idPerfil = idPerfil;
		this.nombre = nombre;
		this.precioHora = precioHora;
	}
	
	//GETTER Y SETTER
	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//METODOS REESCRITOS
	@Override
	public int hashCode() {
		return Objects.hash(idPerfil);
	}

	//IGUAL SI TIENE EL MISMO idPerfil
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return idPerfil == other.idPerfil;
	}

	

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", nombre=" + nombre + ", precioHora=" + precioHora + "]";
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}
}
