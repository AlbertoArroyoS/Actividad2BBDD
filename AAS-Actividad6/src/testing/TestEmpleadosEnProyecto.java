package testing;

import java.sql.Date;
import java.text.SimpleDateFormat;

import modelo.javabean.Empleado;
import modelo.javabean.EmpleadosEnProyecto;
import modelo.javabean.Proyecto;
/**
 * Clase en la que realizamos los test del javabean EmpleadosEnProyecto.
 * 
 * Probamos:
 * 
 * 1. Crear las fechas a traves de un String con forma de fecha.
 * 2. Crear objetos de la clase EmpleadosEnProyecto a traves del constructor.
 * 3. Ver la informacion del objeto a traves de toString.
 * 4. Obtener informacion a traves del get del objeto, individualmente.
 * 5. Mostramos informacion que obtenemos de los métodos responsabilidad de la clase EmpleadosEnProyecto:
 *		- costeHorasAsignadas()
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestEmpleadosEnProyecto {
	
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
		String fechaInicio = "2019-01-29";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
		try {
			fecha1 = sdf.parse(fechaInicio);
					
		} catch (Exception e) {
			System.out.println("**FECHAS NO CORRECTAS**");
		}
		
		Date fechaInicio1 = new Date (fecha1.getTime());
		System.out.println(SEPARACION);
		System.out.println("FECHAS A INGRESAR EN PROYECTO EN EMPLEADOS EN PROYECTO NUEVO");
		System.out.println("FECHA DE INICIO: " + fechaInicio1);
		
		//2. Crear objetos de la clase EmpleadosEnProyecto, Empleado y Proyecto a traves del constructor
		
		Empleado empleado1 = new Empleado();
		empleado1.setIdEmpl(199);
		Proyecto proyecto1 = new Proyecto();
		proyecto1.setIdProyecto("FOR2021002");
		EmpleadosEnProyecto emplPro = new EmpleadosEnProyecto(5 , 70, fechaInicio1, empleado1, proyecto1);
		
		//3. Ver la informacion del objeto a traves de toString
		
		System.out.println(SEPARACION);
		System.out.println(emplPro);
		System.out.println(SEPARACION);
		
		//4. Obtener informacion a traves del get del objeto, individualmente.
		
		System.out.println("DATOS DEL EMPLEADOS EN PROYECTO");
		System.out.println(SEPARACION);
		System.out.println("NUMERO ORDEN: " + emplPro.getNumeroOrden());
		System.out.println("ID PROYECTO: " + emplPro.getProyecto().getIdProyecto());
		System.out.println("ID EMPLEADO: " + emplPro.getEmpleado().getIdEmpl());
		System.out.println("HORAS ASIGNADAS: " + emplPro.getHorasAsignadas());
		System.out.println("FECHA INCORPORACION: " + emplPro.getFechaIncorporacion());
		
		//5. Mostramos informacion de que obtenemos de los métodos responsabilidad de la clase EmpleadosEnProyecto.
		
		System.out.println(SEPARACION);
		System.out.println("Otros datos de empleados en proyecto");
		System.out.println(SEPARACION);
		System.out.println("Coste total horas asignadas : " + emplPro.costeHorasAsignadas(35) +"€");
	}

}
