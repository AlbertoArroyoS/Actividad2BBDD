package testing;

import java.sql.Date;

import java.text.SimpleDateFormat;

import modelo.javabean.Departamento;
import modelo.javabean.Empleado;
import modelo.javabean.Perfil;
/**
 * Clase en la que realizamos los test del javabean Empleado.
 * 
 * Probamos:
 * 
 * 1. Crear las fechas a traves de un String con forma de fecha.
 * 2. Crear objetos de la clase Empleado a traves del constructor.
 * 3. Ver la informacion del objeto a traves de toString.
 * 4. Obtener informacion a traves del get del objeto, individualmente.
 * 5. Mostramos informacion que obtenemos de los métodos responsabilidad de la clase Empleado.
 * 		-salarioBruto()
 * 		-salarioMensual()
 * 		-literalSexo()
 * 		-obtenerEmail()
 * 		-nombreCompleto()
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */

public class TestEmpleado {

	private static final String SEPARACION = "------------------------------------------------------";

	public static void main(String[] args) {
		
		//1. Crear las fechas a traves de un String con forma de fecha.
		
		/**
		 * Creacion de fechas como hemos visto en clase.
		 * Creamos un Date del javautil, nombrandolo directamente para no importar el java.utils
		 * Creamos un String con forma de fecha.
		 * Asignamos el formato en que queremos la fecha, 
		 * que sea yyyy para los años, MM *MAYUSCULA*, para los meses, dd para los días,
		 * hacemos un parse de la fecha y lo asignamos como Date para que salga en el formato decidido. 
		 */
		java.util.Date fecha1 = new java.util.Date();
		java.util.Date fecha2 = new java.util.Date();
		String fechaIngreso = "2023-01-29";
		String fechaNacimiento = "1984-03-05";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //MM MAYUSCULAS
		
		try {
			fecha1 = sdf.parse(fechaIngreso);
			fecha2 = sdf.parse(fechaNacimiento);
		} catch (Exception e) {
			System.out.println("**FECHAS NO CORRECTAS**");
		}
		System.out.println(SEPARACION);
		System.out.println("FORMATO INICIAL DE LA FECHA DESPUES DEL PARSE");
		System.out.println("FECHA DE INGRESO: "+ fecha1);
		System.out.println("FECHA DE NACIMIENTO: " +fecha2);
		Date fechaIngreso1 = new Date (fecha1.getTime());
		Date fechaNacimiento2 = new Date (fecha2.getTime());
		System.out.println(SEPARACION);
		System.out.println("FECHAS A INGRESAR FINAL EN EL EMPLEADO NUEVO");
		System.out.println("FECHA DE INGRESO: "+fechaIngreso1);
		System.out.println("FECHA DE NACIMIENTO: " +fechaNacimiento2);
		
		//2. Crear objetos de la clase Empleado, Perfil y Departamento a traves del constructor
		
		Perfil perfil1 = new Perfil();
		perfil1.setIdPerfil(1);
		Departamento departarmento1 = new Departamento();
		departarmento1.setIdDepar(40);
		Empleado empleado1 = new Empleado(199, "Erika", "Fernandez", 'M', "erika@e.es", "pwd", 120000, 
				fechaIngreso1, fechaNacimiento2,perfil1, departarmento1);
		
		//3. Ver la informacion del objeto a traves de toString
		
		System.out.println(SEPARACION);
		System.out.println(empleado1);
		System.out.println(SEPARACION);
		
		//4. Obtener informacion a traves del get del objeto, individualmente
		
		System.out.println("DATOS DEL EMPLEADO");
		System.out.println(SEPARACION);
		System.out.println("ID EMPLEADO: " + empleado1.getIdEmpl());
		System.out.println("NOMBRE: " + empleado1.getNombre());
		System.out.println("APELLIDOS: " + empleado1.getApellidos());
		System.out.println("EMAIL: " + empleado1.getEmail());
		System.out.println("PASSWORD: " + empleado1.getPassword());
		System.out.println("SALARIO: " + empleado1.getSalario());
		System.out.println("FECHA INGRESO: " + empleado1.getFechaIngreso());
		System.out.println("FECHA NACIMIENTO: " + empleado1.getFechaNacimiento());
		System.out.println("ID PERFIL: " + empleado1.getPerfil().getIdPerfil());
		System.out.println("ID DEPARTAMENTO: " + empleado1.getDepartamento().getIdDepar());
			
		 //5. Mostramos informacion de que obtenemos de los métodos responsabilidad de la clase Empleado
		 
		System.out.println(SEPARACION);
		System.out.println("Otros datos del empleado");
		System.out.println(SEPARACION);
		System.out.println("Salario Bruto : " + empleado1.salarioBruto());
		System.out.println("Salario mensual : " + empleado1.salarioMensual(12));
		System.out.println("El literal de sexo es : " + empleado1.literalSexo());
		System.out.println("El email corporativo es : " + empleado1.obtenerEmail());
		System.out.println("El nombre completo es : " + empleado1.nombreCompleto());
		System.out.println(SEPARACION);
	}

}
