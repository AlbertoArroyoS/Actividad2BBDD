package testing;

import modelo.javabean.Cliente;
/**
 * Clase en la que realizamos los test del javabean Cliente.
 * 
 * Probamos:
 * 
 * 1. Crear objetos de la clase Cliente a traves del constructor.
 * 2. Ver la informacion del objeto a traves de toString.
 * 3. Obtener informacion a traves del get del objeto, individualmente.
 * 4. Introducir informacion a traves del set al objeto, individualmente.
 * 5. Obtener informacion a traves del get de otro objeto, individualmente.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */

public class TestCliente {
	
	private static final String SEPARACION = "------------------------------------------------------";

	public static void main(String[] args) {
			
		//1. Crear objetos de la clase Cliente a traves del constructor
		
		Cliente cliente1 = new Cliente("D99999999", "Juan", "Salgado", "Ciudad Real", 99000, 1000);
				
		//2. Ver la informacion del objeto a traves de toString
		
		System.out.println(SEPARACION);
		System.out.println(cliente1);
		
		//3. Obtener informacion a traves del get del objeto, individualmente
		
		System.out.println(SEPARACION);
		System.out.println("DATOS DEL CLIENTE 1");
		System.out.println(SEPARACION);
		System.out.println("CIF: " + cliente1.getCif());
		System.out.println("Nombre: " + cliente1.getNombre());
		System.out.println("Apellidos: " +cliente1.getApellidos());
		System.out.println("Facturacion anual: " +cliente1.getFacturacionAnual());
		System.out.println("Numero empleados: " +cliente1.getNumeroEmpleados());
		
		//4. Introducir informacion a traves del set al objeto, 

		Cliente cliente2 = new Cliente();
		cliente2.setCif("E77777777");
		cliente2.setNombre("Julia");
		cliente2.setApellidos("Martinez");
		cliente2.setFacturacionAnual(80000);
		cliente2.setNumeroEmpleados(15);
		
		//5. Obtener informacion a traves del get de otro objeto, individualmente
		
		System.out.println(SEPARACION);
		System.out.println(cliente2);
		System.out.println(SEPARACION);
		System.out.println("DATOS DEL CLIENTE 2");
		System.out.println(SEPARACION);
		System.out.println("CIF: " + cliente2.getCif());
		System.out.println("Nombre: " + cliente2.getNombre());
		System.out.println("Apellidos: " +cliente2.getApellidos());
		System.out.println("Facturacion anual: " +cliente2.getFacturacionAnual());
		System.out.println("Numero empleados: " +cliente2.getNumeroEmpleados());
	}

}
