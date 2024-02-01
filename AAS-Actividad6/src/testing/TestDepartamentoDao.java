package testing;

import modelo.dao.DepartamentoDao;
import modelo.dao.DepartamentoDaoImplMy8;
import modelo.javabean.Departamento;
/**
 * Clase en la que realizamos los test del DepartamentoDao.
 * 
 * Probamos:
 * 
 * 1. Buscar todos los departamentos.
 * 2. Buscar departamento por nombre.
 * 3. Buscar departamento por id departamento.
 * 4. Dar de alta departamento.
 * 5. Modificar departamento.
 * 6. Eliminar departamento.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */

public class TestDepartamentoDao {
	
	private static final String SEPARACION = "-------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
		
		DepartamentoDao ddao = new DepartamentoDaoImplMy8();
				
		//1. Buscar todos los departamentos
		
		System.out.println("\n"+SEPARACION);
		System.out.println("1. LISTAR TODOS LOS DEPARTAMENTOS\n");
		for(Departamento ele: ddao.buscarTodos())
			System.out.println(ele);
				
		System.out.println(SEPARACION);
		
		//2. Buscar departamento por nombre
		
		System.out.println("2. MOSTRAR EL DEPARTAMENTO DE SOTFWARE\n");
		for(Departamento ele: ddao.buscarPorNombre("Software"))
			System.out.println(ele);
		
		System.out.println(SEPARACION);
		
		//3. Buscar departamento por id departamento

		System.out.println("3.1. BUSCAR DEPARTAMENTO 10\n");
		Departamento dp = ddao.buscarDepartamento(10);
		if (dp != null)
			System.out.println(dp);
		else
			System.out.println("DEPARTAMENTO NO ENCONTRADO\n");
		System.out.println(SEPARACION);		
		System.out.println("3.2. BUSCAR DEPARTAMENTO 99\n");
		dp = ddao.buscarDepartamento(99);
		if (dp != null)
				System.out.println(dp);
		else
			System.out.println("DEPARTAMENTO NO ENCONTRADO\n");
				
		System.out.println(SEPARACION);	
		
		//4. Dar de alta departamento
		
		System.out.println("4. AÑADIR DEPARTAMENTO 50 CON NOMBRE INFORMATICA EN CIUDAD REAL\n");
		Departamento pf = new Departamento(50, "Informatica","Ciudad Real");
		if (ddao.altaDepartamento(pf) == 1) {
			System.out.println("AÑADIDO CORRECTAMENTE\n");
			for(Departamento ele: ddao.buscarTodos())
			System.out.println(ele);	
		}
		else
			System.out.println("NO SE HA AÑADIDO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		//5. Modificar departamento
		
		System.out.println("5. MODIFICAR DEPARTAMENTO 50 A NOMBRE I+D,DIRECCION PUERTOLLANO\n");
		pf.setNombre("I+D");
		pf.setDireccion("Puertollano");
		if (ddao.modificarDepartamento(pf) == 1) {
			System.out.println("MODIFICADO CORRECTAMENTE\n");
			for(Departamento ele: ddao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA MODIFICADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		//6. Eliminar departamento
		
		System.out.println("6. ELIMINAR DEPARTAMENTO 50\n");
		if (ddao.eliminarDepartamento(50) == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
		System.out.println("LISTAR TODOS");
			for(Departamento ele: ddao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
	}
}
