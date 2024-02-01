package testing;

import java.sql.Date;

import java.text.SimpleDateFormat;

import modelo.dao.DepartamentoDao;
import modelo.dao.DepartamentoDaoImplMy8;
import modelo.dao.EmpleadoDao;
import modelo.dao.EmpleadoDaoImplMy8;
import modelo.dao.PerfilDao;
import modelo.dao.PerfilDaoImplMy8;

import modelo.javabean.Empleado;
/**
 * Clase en la que realizamos los test del EmpleadoDao.
 * 
 * Probamos:
 * 
 * 1. Buscar empleado por id de empleado.
 * 2. Buscar todos los empleados.
 * 3. Buscar empleados por departamento.
 * 4. Buscar empleados por genero.
 * 5. Buscar empleados por apellido.
 * 6. Test salario total empleados.
 * 7. Test salario total por departamentos.
 * 8. Alta empleado
 * 9. Mofificar empleado.
 * 10. Eliminar empleado.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestEmpleadoDao {
	
	private static final String SEPARACION = "-------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
			
		EmpleadoDao edao = new EmpleadoDaoImplMy8();
		PerfilDao pdao = new PerfilDaoImplMy8();
		DepartamentoDao ddao = new DepartamentoDaoImplMy8();

		//1. Buscar empleado por id de empleado
		
		System.out.println("\n" + SEPARACION);
		System.out.println("1.1. BUSCAR EMPLEADO CON ID EMPL 120\n");
		Empleado emp120 = edao.buscarEmpleado(120);
		System.out.println(emp120);
		System.out.println("\nOBTENER EL ID PERFIL DEL EMPLEADO 120: " + emp120.getPerfil().getIdPerfil());
		System.out.println("\nOBTENER EL ID DEPARTAMENTO DEL EMPLEADO 120: " + emp120.getDepartamento().getIdDepar());
		System.out.println(SEPARACION);
		System.out.println("1.2. BUSCAR EMPLEADO CON ID EMPL 999\n");
		emp120 = edao.buscarEmpleado(999);
		System.out.println(emp120);
		
		//2. Buscar todos los empleados
		
		System.out.println(SEPARACION);
		System.out.println("2. LISTAR TODOS LOS EMPLEADOS\n");
		for(Empleado ele: edao.buscarTodos())
			System.out.println(ele);				
		System.out.println(SEPARACION);
				
		//3. Buscar empleados por departamento	
				
		System.out.println("3.1. MOSTRAR EMPLEADOS EN EL DEPARTAMENTO 10\n");
		for(Empleado ele: edao.empleadosByDepartamento(10))
				System.out.println(ele);
				
		System.out.println(SEPARACION);
		System.out.println("3.2. MOSTRAR EMPLEADOS EN EL DEPARTAMENTO 20\n");
		for(Empleado ele: edao.empleadosByDepartamento(20))
				System.out.println(ele);
		System.out.println(SEPARACION);
		System.out.println("3.3 MOSTRAR EMPLEADOS EN EL DEPARTAMENTO 100\n");
		for(Empleado ele: edao.empleadosByDepartamento(100))
				System.out.println(ele);
		System.out.println(SEPARACION);
		
		//4. Buscar empleados por genero	
		
		System.out.println("4.1. MOSTRAR EMPLEADOS HOMBRE\n");
		for(Empleado ele: edao.empleadosBySexo('H'))
			System.out.println(ele);
		System.out.println(SEPARACION);
		System.out.println("4.2 MOSTRAR EMPLEADOS MUJER\n");
		for(Empleado ele: edao.empleadosBySexo('M'))
			System.out.println(ele);
		System.out.println(SEPARACION);
		
		//5. Buscar empleados por apellido	
		
		System.out.println("5.1. MOSTRAR EMPLEADOS CON APELLIDO DIAZ\n");
		for(Empleado ele: edao.empleadosByApellido("Diaz"))
			System.out.println(ele);
		System.out.println(SEPARACION);
		System.out.println("5.2. MOSTRAR EMPLEADOS CON APELLIDO KOO\n");
		for(Empleado ele: edao.empleadosByApellido("Koo"))
			System.out.println(ele);
		System.out.println(SEPARACION);
		
		//6. Test salario total empleados
		
		System.out.println("6. MOSTRAR SUMA DE LOS SALARIOS DE LOS EMPLEADOS\n");
		System.out.println("EL SALARIO TOTAL ES: " + edao.salarioTotal() + " €");
		System.out.println(SEPARACION);
						
		//7. Test salario total por departamentos
		
		System.out.println("7.1. MOSTRAR SUMA DE LOS SALARIOS DEL DEPARTAMENTO 40\n");
		System.out.println("EL SALARIO TOTAL ES: " + edao.salarioTotal(40) + " €");
		System.out.println(SEPARACION);
		System.out.println("7.2. MOSTRAR SUMA DE LOS SALARIOS DEL DEPARTAMENTO 20\n");
		System.out.println("EL SALARIO TOTAL ES: " + edao.salarioTotal(20) + " €");
		System.out.println(SEPARACION);
					
		//8. Alta empleado
		
		//fechas del empleado
		/**
		 * Creo un string con las fechas, les doy formato yyyy-MM-dd 
		 *  les hago un parse y por ultimo se las asigno a las variables Date
		 */	
		System.out.println("8. ALTA EMPLEADO\n");
		java.util.Date fecha1 = new java.util.Date();
		java.util.Date fecha2 = new java.util.Date();
		String fechaIngreso = "2023-01-29";
		String fechaNacimiento = "2019-03-05";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			fecha1 = sdf.parse(fechaIngreso);
			fecha2 = sdf.parse(fechaNacimiento);
		} catch (Exception e) {
			System.out.println("**FECHAS NO CORRECTAS**");
		}
	
		Date fechaIngreso1 = new Date (fecha1.getTime());
		Date fechaNacimiento2 = new Date (fecha2.getTime());
		System.out.println("FECHAS A INGRESAR EN EL EMPLEADO NUEVO");
		System.out.println("FECHA DE INGRESO: "+fechaIngreso1);
		System.out.println("FECHA DE NACIMIENTO: " +fechaNacimiento2);
				
		Empleado empleado = new Empleado(199, "Erika", "Fernandez", 'M', "erika@e.es", "pwd", 120000, 
				fechaIngreso1, fechaNacimiento2,pdao.buscarPerfil(1), ddao.buscarDepartamento(40));
		System.out.println("EMPLEADO A DAR DE ALTA: " +empleado);
		if(edao.altaEmpleado(empleado) == 1)
			System.out.println("DADO DE ALTA CORRECTAMENTE");
		else
			System.out.println("NO DADO DE ALTA");
			
		System.out.println("\nLISTAR TODOS LOS EMPLEADOS\n");
		for(Empleado ele: edao.buscarTodos())
			System.out.println(ele);				
		System.out.println(SEPARACION);
				
		//9. Mofificar empleado
		
		System.out.println("9. MODIFICAR EMPLEADO 199, cambiar contraseña a 12345\n");
		empleado.setPassword("12345");
		if (edao.modificarEmpleado(empleado) == 1) {
			System.out.println("MODIFICADO CORRECTAMENTE\n");
			Empleado emp199 = edao.buscarEmpleado(199);
			System.out.println(emp199);	
			}
		else
			System.out.println("NO SE HA MODIFICADO CORRECTAMENTE");
		System.out.println(SEPARACION);
		
		//10. Eliminar empleado
		
		System.out.println("10. ELIMINAR EMPLEADO 199");
		if (edao.eliminarEmpleado(199) == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
		System.out.println("LISTAR TODOS\n");
			for(Empleado ele: edao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE");
		System.out.println(SEPARACION);	
	}
}
