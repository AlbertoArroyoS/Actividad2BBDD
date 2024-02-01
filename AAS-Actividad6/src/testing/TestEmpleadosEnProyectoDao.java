package testing;

import java.sql.Date;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import modelo.dao.EmpleadoDao;
import modelo.dao.EmpleadoDaoImplMy8;
import modelo.dao.EmpleadosEnProyectoDao;
import modelo.dao.EmpleadosEnProyectoDaoImplMy8;

import modelo.dao.ProyectoDao;
import modelo.dao.ProyectoDaoImplMy8;

import modelo.javabean.EmpleadosEnProyecto;
import modelo.javabean.Proyecto;

/**
 * Clase en la que realizamos los test del EmpledosEnProyectoDao.
 * 
 * Probamos:
 * 
 * 1. Buscar empleados en proyecto por numero de orden. 
 * 2. Buscar todos los empleados en proyecto.
 * 3. empleadosByProyecto.
 * 4. asignarEmpleadosAProyecto.
 * 5. horasAsignadasAProyecto.
 * 6. costeActualDeProyecto.
 * 7. margenActualProyecto.
 * 8. Alta empleados en proyecto.
 * 9. Modificar empleados en proyecto.
 * 10. Eliminar empleados en proyecto por numero de orden.
 * 11. asignarEmpleadosAProyecto BIEN HECHO
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestEmpleadosEnProyectoDao {
	
	private static final String SEPARACION = "-------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
		
		EmpleadosEnProyectoDao eepdao = new EmpleadosEnProyectoDaoImplMy8();
		ProyectoDao prodao = new ProyectoDaoImplMy8();
		EmpleadoDao edao = new EmpleadoDaoImplMy8();
				
		//1. Buscar empleados en proyecto por numero de orden 
		
		System.out.println("\n"+SEPARACION);	
		System.out.println("1. EMPLEADO EN NUMERO DE ORDEN 1\n");
		EmpleadosEnProyecto epr = eepdao.buscarEmpleadoOrden(1);
		if (epr != null)
			System.out.println(epr);
		else
		System.out.println("PROYECTO NO ENCONTRADO");
		
		//2. Buscar todos los empleados en proyecto
		
		System.out.println(SEPARACION);
		System.out.println("2. LISTAR TODOS LOS EMPLEADOS EN PROYECTO\n");
		for(EmpleadosEnProyecto ele: eepdao.buscarTodos())
			System.out.println(ele);
		
		//3. empleadosByProyecto
		
		System.out.println(SEPARACION);
		System.out.println("3.1. EMPLEADOS DEL PROYECTO FOR2020001\n");
		for(EmpleadosEnProyecto ele: eepdao.empleadosByProyecto("FOR2020001")) 
			System.out.println(ele);
		System.out.println(SEPARACION);
		System.out.println("3.2. EMPLEADOS DEL PROYECTO FOR2021001\n");
		for(EmpleadosEnProyecto ele: eepdao.empleadosByProyecto("FOR2021001")) 
			System.out.println(ele);
		
		//4. asignarEmpleadosAProyecto
		
	//	System.out.println(SEPARACION);
	//	System.out.println("4. CANTIDAD DE EMPLEADOS EN TABLA clientes_proyectos_empleados\n");
	//	List<EmpleadosEnProyecto> empleados = eepdao.buscarTodos();
	//	System.out.println(eepdao.asignarEmpleadosAProyecto(empleados));
		
		//5. horasAsignadasAProyecto(String codigoProyecto) FOR2020001
		
		System.out.println(SEPARACION);
		System.out.println("5. NUMERO DE HORAS ASIGNADAS AL PROYECTO FOR2020001\n");
		System.out.println(eepdao.horasAsignadasAProyecto("FOR2020001"));
		
		//6. costeActualDeProyecto(String codigoProyecto): double . horas*coste-hora de cada empleadoasignado al proyecto
		System.out.println(SEPARACION);
		System.out.println("6.1. PROYECTO FOR2020001");
		System.out.println("EL COSTE ACTUAL DEL PROYECTO ES " +eepdao.costeActualDeProyecto("FOR2020001"));
		System.out.println(SEPARACION);
		System.out.println("6.2. PROYECTO FOR2021001");
		System.out.println("EL COSTE ACTUAL DEL PROYECTO ES : " +eepdao.costeActualDeProyecto("FOR2021001") +"€");
		
		//7. margenActualProyecto, del proyecto FOR2020001
		
		System.out.println(SEPARACION);
		System.out.println("7. MARGEN ACTUAL DEL PROYECTO FOR2020001\n");
		System.out.println("EL MARGEN ES DE : " +eepdao.margenActualProyecto("FOR2020001")+ "€");
		
	
		//8. Alta empleados en proyecto
		System.out.println(SEPARACION);
		System.out.println("8. ALTA PROYECTO EN EMPLEADO\n");
		//creacion de fechas 
		java.util.Date fecha1 = new java.util.Date();
		String fechaInicio = "2019-01-29";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
		try {
			fecha1 = sdf.parse(fechaInicio);
					
		} catch (Exception e) {
			System.out.println("**FECHAS NO CORRECTAS**");
		}

		Date fechaInicio1 = new Date (fecha1.getTime());
		System.out.println("FECHAS A INGRESAR EN EL PROYECTO NUEVO");
		System.out.println("FECHA DE INICIO: " + fechaInicio1);
		
		EmpleadosEnProyecto emplPro = new EmpleadosEnProyecto();

		emplPro.setEmpleado(edao.buscarEmpleado(119));
		emplPro.setHorasAsignadas(99);
		emplPro.setFechaIncorporacion(fechaInicio1);
		emplPro.setProyecto(prodao.buscarProyecto("FOR2021001"));
		
		System.out.println("EMPLEADO EN PROYECTO A DAR DE ALTA: " + emplPro);
		if (eepdao.altaEmpleadoEnProyecto(emplPro) ==1)
			System.out.println("DADO DE ALTA CORRECTAMENTE");
		else
			System.out.println("NO DADO DE ALTA");
		System.out.println("\nLISTAR TODOS PROYECTOS\n");
		for(EmpleadosEnProyecto ele: eepdao.buscarTodos())
			System.out.println(ele);
		System.out.println(SEPARACION);	
		
		//9. Modificar empleados en proyecto
		
		System.out.println("9. MODIFICAR NUMERO DE ORDEN 5, HORAS ASIGNADAS A 75\n");
		emplPro.setHorasAsignadas(75);
		if (eepdao.modificarEmpleadoEnProyecto(emplPro,5) == 1) {
			System.out.println("MODIFICADO CORRECTAMENTE\n");
			epr = eepdao.buscarEmpleadoOrden(5);
			if (epr != null)
				System.out.println(epr);
			else
			System.out.println("PROYECTO NO ENCONTRADO");	
			}
		else
			System.out.println("NO SE HA MODIFICADO CORRECTAMENTE");
		System.out.println(SEPARACION);
		
		//10. Eliminar empleados en proyecto por numero de orden
		
		System.out.println("10. ELIMINAR NUMERO DE ORDEN 5\n");
		if (eepdao.eliminarEmpleadoEnProyecto(5) == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
		System.out.println("LISTAR TODOS");
		for(EmpleadosEnProyecto ele: eepdao.buscarTodos())
			System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE");
		
		//11. asignarEmpleadosAProyecto
		
		System.out.println(SEPARACION);
		System.out.println("11. clientes_proyectos_empleados\n");
		List<EmpleadosEnProyecto> listaEmpleados4 = new ArrayList<>();
		EmpleadosEnProyecto emplPro1 = new EmpleadosEnProyecto();
		EmpleadosEnProyecto emplPro2 = new EmpleadosEnProyecto();
		emplPro1.setEmpleado(edao.buscarEmpleado(119));
		emplPro1.setHorasAsignadas(99);
		emplPro1.setFechaIncorporacion(fechaInicio1);
		emplPro1.setProyecto(prodao.buscarProyecto("FOR2021001"));
		emplPro2.setEmpleado(edao.buscarEmpleado(120));
		emplPro2.setHorasAsignadas(44);
		emplPro2.setFechaIncorporacion(fechaInicio1);
		emplPro2.setProyecto(prodao.buscarProyecto("FOR2021001"));
		listaEmpleados4.add(emplPro1);
		listaEmpleados4.add(emplPro2);
		System.out.println("Numero de empleados añadidos: " +eepdao.asignarEmpleadosAProyecto(listaEmpleados4));
		System.out.println(SEPARACION);
		System.out.println("LISTAR TODOS LOS EMPLEADOS EN PROYECTO\n");
		for(EmpleadosEnProyecto ele: eepdao.buscarTodos())
			System.out.println(ele);


		
	}
}
