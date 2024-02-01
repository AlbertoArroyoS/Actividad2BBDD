package testing;

import java.sql.Date;

import java.text.SimpleDateFormat;

import modelo.dao.ClienteDao;
import modelo.dao.ClienteDaoImplMy8;
import modelo.dao.EmpleadoDao;
import modelo.dao.EmpleadoDaoImplMy8;
import modelo.dao.ProyectoDao;
import modelo.dao.ProyectoDaoImplMy8;
import modelo.javabean.Proyecto;
/**
 * Clase en la que realizamos los test del ProyectoDao.
 * 
 * Probamos:
 * 
 * 1. Buscar proyectos por id proyecto.
 * 2. Buscar todos los proyectos.
 * 3. Buscar proyectosByEstado.
 * 4. Buscar proyectosByCliente.
 * 5. Buscar proyectosByJefeProyectoAndByEstado.
 * 6. importesVentaProyectosTerminados.
 * 7. margenBrutoProyectosTerminados.
 * 8.1 diasATerminoProyectoActivo JAVA.
 * 8.2 diasATerminoProyectoActivo MySQL.
 * 9. Alta proyecto.
 * 10. Mofificar proyecto.
 * 11. Eliminar proyecto.
 * 12. buscarProyectoPorContenidoId.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestProyectoDao {
	
	private static final String SEPARACION = "-------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
		
		ProyectoDao pdao = new ProyectoDaoImplMy8();
		EmpleadoDao edao = new EmpleadoDaoImplMy8();
		ClienteDao cdao = new ClienteDaoImplMy8();
		
		//1. Buscar proyectos por id proyecto
		
		System.out.println("\n"+SEPARACION);	
		System.out.println("1.1. BUSCAR PROYECTO FOR2020001\n");
		Proyecto pr = pdao.buscarProyecto("FOR2020001");
			if (pr != null)
				System.out.println(pr);
			else
			System.out.println("PROYECTO NO ENCONTRADO\n");	
		System.out.println(SEPARACION);
		System.out.println("1.2. BUSCAR PROYECTO FOR2020999\n");
		pr = pdao.buscarProyecto("FOR2020999");
			if (pr != null)
				System.out.println(pr);
			else
				System.out.println("PROYECTO NO ENCONTRADO\n");		
		
		//2. Buscar todos los proyectos
		
		System.out.println(SEPARACION);
		System.out.println("2. LISTAR TODOS LOS PROYECTOS\n");
		for(Proyecto ele: pdao.buscarTodos())
			System.out.println(ele);
		
		//3. Buscar proyectosByEstado
		
		System.out.println(SEPARACION);
		System.out.println("3.1. LISTAR LOS PROYECTOS TERMINADOS\n");
		for(Proyecto ele: pdao.proyectosByEstado("terminado"))
			System.out.println(ele);
		System.out.println(SEPARACION);
		System.out.println("3.2. LISTAR LOS PROYECTOS ACTIVOS\n");
		for(Proyecto ele: pdao.proyectosByEstado("activo"))
			System.out.println(ele);
		
		//4. Buscar proyectosByCliente
		
		System.out.println(SEPARACION);
		System.out.println("4. LISTAR LOS PROYECTOS DEL CLIENTE A22222222\n");
		for(Proyecto ele: pdao.proyectosByCliente("A22222222"))
			System.out.println(ele);
		
		//5. Buscar proyectosByJefeProyectoAndByEstado int jefeProyecto, String estado
		
		System.out.println(SEPARACION);
		System.out.println("5. LISTAR LOS PROYECTOS DEL JEFE PROYECTO 114 Y QUE ESTE TERMINADO\n");
		for(Proyecto ele: pdao.proyectosByJefeProyectoAndByEstado(114, "terminado"))
			System.out.println(ele);
		
		//6. importesVentaProyectosTerminados()
		
		System.out.println(SEPARACION);
		System.out.println("6. IMPORTE DE VENTA DE PROYECTOS TERMINADOS\n");
		System.out.println("EL IMPORTE TOTAL ES: " + pdao.importesVentaProyectosTerminados());
		
		//7. margenBrutoProyectosTerminados() Diferencia suma Importes venta y gastos reales
		System.out.println(SEPARACION);
		System.out.println("7. MARGEN BRUTO DE PROYECTOS TERMINADOS\n");
		System.out.println("EL MARGEN BRUTO ES: " + pdao.margenBrutoProyectosTerminados());
		
		//8.1 diasATerminoProyectoActivo(String codigoProyecto) JAVA
		//Cuantos días quedan para terminar elproyecto (diferencia entre fecha_fin_previsto y la fecha de hoy

		System.out.println(SEPARACION);
		System.out.println("8.1. DIAS A TERMINO PROYECTO ACTIVO POR JAVA\n");
		System.out.println("LOS DIFERENCIA DE DIAS ES DE : " + pdao.diasATerminoProyectoActivo("FOR2020001"));
		
		//8.2 diasATerminoProyectoActivo(String codigoProyecto) MySQL
		System.out.println(SEPARACION);
		System.out.println("8.2. DIAS A TERMINO PROYECTO ACTIVO POR MySQL\n");
		System.out.println("LOS DIFERENCIA DE DIAS ES DE : " + pdao.diasATerminoProyectoActivoMy8("FOR2020001"));
		
				
		//9. Alta proyecto FOR2021999
		System.out.println(SEPARACION);
		System.out.println("9. ALTA PROYECTO FOR2021999\n");
		//creacion de fechas para el proyecto
		java.util.Date fecha1 = new java.util.Date();
		java.util.Date fecha2 = new java.util.Date();
		java.util.Date fecha3 = new java.util.Date();
		String fechaInicio = "2019-01-29";
		String fechaFinPrevisto = "2022-03-05";
		String fechaFinReal = "2023-02-01";
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
		System.out.println("FECHAS A INGRESAR EN EL PROYECTO NUEVO");
		System.out.println("FECHA DE INICIO: " + fechaInicio1);
		System.out.println("FECHA FIN PREVISTO: " +fechaFinPrevisto1);
		System.out.println("FECHA FIN REAL: " +fechaFinReal1);
		
		Proyecto proyecto = new Proyecto("FOR2021999", "Formacion", fechaInicio1, fechaFinPrevisto1, fechaFinReal1, 75000, 25000, 40000, "TERMINADO", edao.buscarEmpleado(114), cdao.buscarCliente("B33333333"));
		System.out.println("PROYECTO A DAR DE ALTA: " + proyecto);
		if(pdao.altaProyecto(proyecto)==1)
			System.out.println("DADO DE ALTA CORRECTAMENTE");
		else
			System.out.println("NO DADO DE ALTA");
		System.out.println("\nLISTAR TODOS PROYECTOS\n");
		for(Proyecto ele: pdao.buscarTodos())
			System.out.println(ele);				
		System.out.println(SEPARACION);
		
		//10. Mofificar proyecto FOR2021999
		
		System.out.println("10. MODIFICAR PROYECTO FOR2021999, cambiar DESCRIPCION a Formacion de programacion\n");
		proyecto.setDescripcion("Formacion de programacion");
		if (pdao.modificarProyecto(proyecto) == 1) {
			System.out.println("MODIFICADO CORRECTAMENTE\n");
			Proyecto proyecto999 = pdao.buscarProyecto("FOR2021999");
			System.out.println(proyecto999);	
			}
		else
			System.out.println("NO SE HA MODIFICADO CORRECTAMENTE");
		System.out.println(SEPARACION);
		
		//11. Eliminar proyecto FOR2021999
		
		System.out.println("11. ELIMINAR PROYECTO FOR2021999");
		if (pdao.eliminarProyecto("FOR2021999") == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
		System.out.println("LISTAR TODOS");
		for(Proyecto ele: pdao.buscarTodos())
			System.out.println(ele);		
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE");
		
		//12. Buscar ProyectoPorContenidoId
		
		System.out.println(SEPARACION);
		System.out.println("12 BUSCAR PROYECTOS EN LOS QUE EL ID CONTENGA 2021\n");
		for(Proyecto ele: pdao.buscarProyectoPorContenidoId("2021"))
			System.out.println(ele);
	}
}
