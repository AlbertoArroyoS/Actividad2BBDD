package testing;

import modelo.javabean.Departamento;
/**
 * Clase en la que realizamos los test del javabean Departamento.
 * 
 * Probamos:
 * 
 * 1. Crear objetos de la clase Departamento a traves del constructor.
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

public class TestDepartamento {
	
	private static final String SEPARACION = "------------------------------------------------------";

	public static void main(String[] args) {

		//1. Crear objetos de la clase Departamento a traves del constructor
		
		Departamento departamento1 = new Departamento(50, "Informatica", "Ciudad Real");

		//2. Ver la informacion del objeto a traves de toString
		
		System.out.println(SEPARACION);
		System.out.println(departamento1);
		System.out.println(SEPARACION);
		
		//3. Obtener informacion a traves del get del objeto, individualmente
		
		System.out.println("DATOS DEL DEPARTAMENTO 1");
		System.out.println(SEPARACION);
		System.out.println("ID Departamento: " + departamento1.getIdDepar());
		System.out.println("Nombre departamento: " + departamento1.getNombre());
		System.out.println("Direccion del departamento: " + departamento1.getDireccion());
		
		//4. Introducir informacion a traves del set al objeto, individualmente
		
		Departamento departamento2 = new Departamento();
		departamento2.setIdDepar(60);
		departamento2.setNombre("Juridico");
		departamento2.setDireccion("Barcelona");
		
		//5. Obtener informacion a traves del get de otro objeto, individualmente
		
		System.out.println(SEPARACION);
		System.out.println(departamento2);
		System.out.println(SEPARACION);
		System.out.println("DATOS DEL DEPARTAMENTO 2");
		System.out.println(SEPARACION);
		System.out.println("ID Departamento: " + departamento2.getIdDepar());
		System.out.println("Nombre departamento: " + departamento2.getNombre());
		System.out.println("Direccion del departamento: " + departamento2.getDireccion());
	}

}
