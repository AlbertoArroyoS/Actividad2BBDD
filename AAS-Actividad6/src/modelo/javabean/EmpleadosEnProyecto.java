package modelo.javabean;

import java.sql.Date;
import java.util.Objects;
/**
 * Entidad ProyectoConEmpleado.
 * 
 * Con los atributos privados:
 *	numeroOrden : int
 * 	horasAsignadas : int
 * 	fechaIncorporacion : Date
 * 	empleado : Empleado
 * 	proyecto : Proyecto
 * 
 * Metodos propios:
 * 	costeHorasAsignadas(double precioHora): double
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */

public class EmpleadosEnProyecto {
	
	//ATRIBUTOS PRIVADOS	
	private int numeroOrden;
	private int horasAsignadas;
	private Date fechaIncorporacion;
	private Empleado empleado;
	private Proyecto proyecto;
	
	//CONSTRUCTOR SIN PARAMETROS
	public EmpleadosEnProyecto() {
		super();
	}
	
	//CONSTRUCTOR CON PARAMETROS	
	public EmpleadosEnProyecto(int numeroOrden, int horasAsignadas, Date fechaIncorporacion, Empleado empleado,
			Proyecto proyecto) {
		super();
		this.numeroOrden = numeroOrden;
		this.horasAsignadas = horasAsignadas;
		this.fechaIncorporacion = fechaIncorporacion;
		this.empleado = empleado;
		this.proyecto = proyecto;
	}
	
	//CONSTRUCTOR sobrecargado sin el numero_orden

	public EmpleadosEnProyecto(int horasAsignadas, Date fechaIncorporacion, Empleado empleado, Proyecto proyecto) {
		super();
		this.horasAsignadas = horasAsignadas;
		this.fechaIncorporacion = fechaIncorporacion;
		this.empleado = empleado;
		this.proyecto = proyecto;
	}

	//GETTER Y SETTER
	public int getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public int getHorasAsignadas() {
		return horasAsignadas;
	}

	public void setHorasAsignadas(int horasAsignadas) {
		this.horasAsignadas = horasAsignadas;
	}

	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		
		this.empleado = empleado;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	//METODOS REESCRITOS
	@Override
	public int hashCode() {
		return Objects.hash(numeroOrden);
	}

	//IGUAL SI TIENE EL MISMO numeroOrden
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadosEnProyecto other = (EmpleadosEnProyecto) obj;
		return numeroOrden == other.numeroOrden;
	}

	//toString modificado para que nos de solo el id del proyecto, no el proyecto entero por pantalla
	
	@Override
	public String toString() {
		return "EmpleadosEnProyecto [numeroOrden=" + numeroOrden + ", proyecto=" + proyecto.getIdProyecto() + ", empleado=" + empleado
				+ ", horasAsignadas=" + horasAsignadas + ", fechaIncorporacion=" + fechaIncorporacion + "]";
	}

	//METODOS PROPIOS
	
	//paso el precioHora por parametro 
	public double costeHorasAsignadas(double precioHora) {
		
		System.out.println(precioHora +"€/h");
		double coste = horasAsignadas * precioHora;
		
		return coste;	
	}	
}
