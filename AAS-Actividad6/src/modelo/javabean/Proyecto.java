package modelo.javabean;

import java.sql.Date;
import java.util.Objects;

/**
 * Entidad Proyecto:
 * 
 * Con los atributos privados:
 * idProyecto : String
 * descripcion : String
 * fechaInicio : Date 
 * fechaFinPrevisto : Date
 * fechaFinReal : Date
 * ventaPrevisto : double
 * costesPrevisto : double
 * costeReal : double
 * estado : String
 * jefeProyecto : Empleado
 * cliente : Cliente
 * 
 * Metodos propios:
 * 
 * margenPrevisto() : double
 * margenReal() : double
 * diferenciaGastos() : double
 * diferenciaFinPrevistoReal() : int
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */


public class Proyecto {
	
	//ATRIBUTOS PRIVADOS
	private String idProyecto;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFinPrevisto;
	private Date fechaFinReal;
	private double ventaPrevisto;
	private double costesPrevisto;
	private double costeReal;
	private String estado;
	private Empleado jefeProyecto;
	private Cliente cliente;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Proyecto() {
		super();
	}
	
	//CONSTRUCTOR CON PARAMETROS
	public Proyecto(String idProyecto, String descripcion, Date fechaInicio, Date fechaFinPrevisto, Date fechaFinReal,
			double ventaPrevisto, double costesPrevisto, double costeReal, String estado, Empleado jefeProyecto,
			Cliente cliente) {
		super();
		this.idProyecto = idProyecto;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFinPrevisto = fechaFinPrevisto;
		this.fechaFinReal = fechaFinReal;
		this.ventaPrevisto = ventaPrevisto;
		this.costesPrevisto = costesPrevisto;
		this.costeReal = costeReal;
		this.estado = estado;
		this.jefeProyecto = jefeProyecto;
		this.cliente = cliente;
	}

	//GETTER Y SETTER
	public String getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinPrevisto() {
		return fechaFinPrevisto;
	}

	public void setFechaFinPrevisto(Date fechaFinPrevisto) {
		this.fechaFinPrevisto = fechaFinPrevisto;
	}

	public Date getFechaFinReal() {
		return fechaFinReal;
	}

	public void setFechaFinReal(Date fechaFinReal) {
		this.fechaFinReal = fechaFinReal;
	}

	public double getVentaPrevisto() {
		return ventaPrevisto;
	}

	public void setVentaPrevisto(double ventaPrevisto) {
		this.ventaPrevisto = ventaPrevisto;
	}

	public double getCostesPrevisto() {
		return costesPrevisto;
	}

	public void setCostesPrevisto(double costesPrevisto) {
		this.costesPrevisto = costesPrevisto;
	}

	public double getCosteReal() {
		return costeReal;
	}

	public void setCosteReal(double costeReal) {
		this.costeReal = costeReal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empleado getJefeProyecto() {
		return jefeProyecto;
	}

	public void setJefeProyecto(Empleado jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	//METODOS REESCRITOS
	@Override
	public int hashCode() {
		return Objects.hash(idProyecto);
	}

	//IGUAL SI TIENE EL MISMO idProyecto
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto other = (Proyecto) obj;
		return Objects.equals(idProyecto, other.idProyecto);
	}

	@Override
	public String toString() {
		return "Proyecto [idProyecto=" + idProyecto + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio
				+ ", fechaFinPrevisto=" + fechaFinPrevisto + ", fechaFinReal=" + fechaFinReal + ", ventaPrevisto="
				+ ventaPrevisto + ", costesPrevisto=" + costesPrevisto + ", costeReal=" + costeReal + ", estado="
				+ estado + ", jefeProyecto=" + jefeProyecto + ", cliente=" + cliente + "]";
	}
	
	//METODOS PROPIOS
	
	public double margenPrevisto() {
		
		double margenPrevisto = ventaPrevisto - costesPrevisto ;
		return margenPrevisto;
	
	}
	public double margenReal() {
		double margenReal = ventaPrevisto - costeReal ;
		return margenReal;
		
	}
	public double diferenciaGastos() {
		
		double diferenciaGastos = costeReal - costesPrevisto;
		return diferenciaGastos;
	}
	public int diferenciaFinPrevistoReal() {
		
		//los milisegundos que tiene un dia
		int milisegundosDia = 86400000;
		
		//calcular los milisegundos entre las fechas y dividirlos por los milisegundos de un dia
		int diferenciaFinPrevistoReal = (int) ((fechaFinPrevisto.getTime()- fechaFinReal.getTime())/milisegundosDia);
		
		return diferenciaFinPrevistoReal;
	}
}
