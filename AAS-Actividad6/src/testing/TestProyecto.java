package testing;

import java.sql.Date;
import java.text.SimpleDateFormat;

import modelo.javabean.Cliente;
import modelo.javabean.Empleado;
import modelo.javabean.Proyecto;
/**
 * Clase en la que realizamos los test del javabean Proyecto.
 * 
 * Probamos:
 * 
 * 1. Crear las fechas a traves de un String con forma de fecha.
 * 2. Crear objetos de la clase Proyecto a traves del constructor.
 * 3. Ver la informacion del objeto a traves de toString.
 * 4. Obtener informacion a traves del get del objeto, individualmente.
 * 5. Mostramos informacion que obtenemos de los métodos responsabilidad de la clase Proyecto:
 *		- margenPrevisto()
 *		- margenReal()
 *		- diferenciaGastos()
 *		- diferenciaFinPrevistoReal()
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestProyecto {
	
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
		java.util.Date fecha3 = new java.util.Date();
		String fechaInicio = "2019-01-29";
		String fechaFinPrevisto = "2022-03-05";
		String fechaFinReal = "2023-03-05";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			fecha1 = sdf.parse(fechaInicio);
			fecha2 = sdf.parse(fechaFinPrevisto);
			fecha3 = sdf.parse(fechaFinReal);
		} catch (Exception e) {
			System.out.println("**FECHAS NO CORRECTAS**");
		}

		Date fechaInicio1 = new Date (fecha1.getTime());
		Date fechaFinPrevisto1 = new Date (fecha2.getTime());
		Date fechaFinReal1 = new Date (fecha3.getTime());
		System.out.println(SEPARACION);
		System.out.println("FECHAS A INGRESAR EN EL PROYECTO NUEVO");
		System.out.println("FECHA DE INICIO: " + fechaInicio1);
		System.out.println("FECHA FIN PREVISTO: " +fechaFinPrevisto1);
		System.out.println("FECHA FIN REAL: " +fechaFinReal1);
		
		//2. Crear objetos de la clase Proyecto, Cliente y Empleado a traves del constructor.
		
		Cliente cliente1 = new Cliente("D99999999", "Juan", "Salgado", "Ciudad Real", 99000, 1000);
		Empleado jefeProyecto1 = new Empleado();
		jefeProyecto1.setIdEmpl(999);
		Proyecto proyecto1 = new Proyecto("FOR2021999", "Formacion", fechaInicio1, fechaFinPrevisto1, fechaFinReal1, 75000, 25000, 40000, "TERMINADO", jefeProyecto1, cliente1);
		
		//3. Ver la informacion del objeto a traves de toString.
		
		System.out.println(SEPARACION);
		System.out.println(proyecto1);
		System.out.println(SEPARACION);
		
		//4. Obtener informacion a traves del get del objeto, individualmente.
		
		System.out.println("DATOS DEL PROYECTO");
		System.out.println(SEPARACION);
		System.out.println("ID PROYECTO: " + proyecto1.getIdProyecto());
		System.out.println("DESCRIPCION: " + proyecto1.getDescripcion());
		System.out.println("FECHA INICIO: " + proyecto1.getFechaInicio());
		System.out.println("FECHA FIN PREVISTO: " + proyecto1.getFechaFinPrevisto());
		System.out.println("FECHA FIN REAL: " + proyecto1.getFechaFinReal());
		System.out.println("VENTA PREVISTO:" + proyecto1.getVentaPrevisto());
		System.out.println("COSTES PREVISTO: " + proyecto1.getCostesPrevisto());
		System.out.println("COSTES REAL: " + proyecto1.getCosteReal());
		System.out.println("ESTADO: " + proyecto1.getEstado());
		System.out.println("ID JEFE PROYECTO: " + proyecto1.getJefeProyecto().getIdEmpl());
		System.out.println("CIF CLIENTE: " + proyecto1.getCliente().getCif());
		System.out.println(SEPARACION);
			
		//5. Mostramos informacion que obtenemos de los métodos responsabilidad de la clase Proyecto
		
		System.out.println("MARGEN PREVISTO: " + proyecto1.margenPrevisto());
		System.out.println("MARGEN REAL: " + proyecto1.margenReal());
		System.out.println("DIFERENCIA GASTOS: " + proyecto1.diferenciaGastos());
		System.out.println("DIFERENCIA DIAS FECHA PREVISTA: " + proyecto1.diferenciaFinPrevistoReal());
	}

}
