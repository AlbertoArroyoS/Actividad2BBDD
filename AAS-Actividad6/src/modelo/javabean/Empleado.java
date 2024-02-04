package modelo.javabean;

import java.sql.Date;
import java.util.Objects;

/**
 * Entidad Empleado
 * 
 * Con los atributos privados:
 * 	private int idEmpl : int
 * 	nombre : String
 *	apellidos : String
 * 	genero : char
 * 	email : String
 * 	password : String
 * 	salario : double
 * 	comision : double
 * 	fechaIngreso : Date
 * 	fechaNacimiento : Date
 * 	perfil : Perfil
 * 	departamento : Departamento
 * 
 * Metodos propios:
 * 	salarioBruto() : double
 * 	salarioMensual(int meses) : double
 * 	literalSexo() : String
 * 	obtenerEmail() : String
 * 	nombreCompleto(): String
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */

public class Empleado {
	
	//ATRIBUTOS PRIVADOS
	
	private int idEmpl;
	private String nombre;
	private String apellidos;
	private char genero;
	private String email;
	private String password;
	private double salario;
	private double comision;
	private Date fechaIngreso;
	private Date fechaNacimiento;
	private Perfil perfil;
	private Departamento departamento;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Empleado() {
		super();
	}
	
	//CONSTRUCTOR CON PARAMETROS
	public Empleado(int idEmpl, String nombre, String apellidos, char genero, String email, String password,
			double salario, Date fechaIngreso, Date fechaNacimiento, Perfil perfil, Departamento departamento) {
		super();
		this.idEmpl = idEmpl;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.email = email;
		this.password = password;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.fechaNacimiento = fechaNacimiento;
		this.perfil = perfil;
		this.departamento = departamento;
	}


	//GETTER Y SETTER
	public int getIdEmpl() {
		return idEmpl;
	}


	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
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


	public char getGenero() {
		return genero;
	}


	public void setGenero(char genero) {
		this.genero = genero;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public double getSalario() {
		return salario;
	}


	public void setSalario(double salario) {
		this.salario = salario;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	//METODOS REESCRITOS
	@Override
	public int hashCode() {
		return Objects.hash(idEmpl);
	}

	//IGUAL SI TIENE EL MISMO idEmpl
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return idEmpl == other.idEmpl;
	}


	@Override
	public String toString() {
		return "Empleado [idEmpl=" + idEmpl + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero
				+ ", email=" + email + ", password=" + password + ", salario=" + salario + ", fechaIngreso="
				+ fechaIngreso + ", fechaNacimiento=" + fechaNacimiento + ", perfil=" + perfil + ", departamento="
				+ departamento + "]";
	}

	//METODOS PROPIOS
	public double salarioBruto() {
		double salarioBruto = salario + comision;
		return salarioBruto;
	}
	
	public double salarioMensual(int meses) {
		double salarioMensual = salario / meses;
		return salarioMensual;
	}
	
	public String literalSexo() {
		if (genero == 'H'|| genero == 'h' )
			return "Hombre";
		if (genero == 'M'|| genero == 'm' )
			return "Mujer";
		else
			return "Sexo erroneo";
	}

	public String obtenerEmail() {
		
		char primeraLetraNom = nombre.charAt(0);
		String apellidoEmail ="";
		int contadorEspacio = 0;
		/*
		 * Con un bucle for para ver donde está el primer espacio y cojo el primer apellido
		 * desde la posición 0 hasta la posicion del espacio
		 */
		
		for(int i=0; i<apellidos.length(); i++) {
			char obtenerPrimeraLetraAp = apellidos.charAt(i);
			if (obtenerPrimeraLetraAp == ' ')
				contadorEspacio = i;
			//en caso de que sólo sea un apellido y no contenga espacios
			if(contadorEspacio == 0)
				contadorEspacio = apellidos.length();
		}
		
		apellidoEmail = apellidos.substring(0, contadorEspacio);
		
		/*
		 * Para apellidos que contienen una ñ 
		 * y no es admitida por las direcciones de correo electrónico
		 */
		
		for (int i = 0; i < apellidos.length(); i++) {
			if (apellidos.charAt(i) == 'ñ') {
				apellidos = (apellidos.replace(apellidos.charAt(i), 'n'));
			}
		}
		
		String dominioEmail = "@empleados.edix.com";
		String obtenerEmail = primeraLetraNom + apellidoEmail + dominioEmail;
		
		return obtenerEmail.toLowerCase();
	}

	public String nombreCompleto() {
		String nombreCompleto = nombre + " " + apellidos;
		return nombreCompleto;
	}	
}
