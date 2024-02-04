package testing;

import modelo.javabean.Perfil;
/**
 * Clase en la que realizamos los test del javabean Perfil.
 * 
 * Probamos:
 * 
 * 1. Crear objetos de la clase Perfil a traves del constructor.
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
public class TestPerfil {
	
	private static final String SEPARACION = "------------------------------------------------------";


	public static void main(String[] args) {
		
		//1. Crear objetos de la clase Perfil a traves del constructor.
		
		Perfil perfil1 = new Perfil(5, "Administrativo", 50);
		
		//2. Ver la informacion del objeto a traves de toString.
		
		System.out.println(SEPARACION);
		System.out.println(perfil1);
		
		//3. Obtener informacion a traves del get del objeto, individualmente. 
		
		System.out.println(SEPARACION);
		System.out.println("DATOS DEL PERFIL");
		System.out.println(SEPARACION);
		System.out.println("ID Perfil: " + perfil1.getIdPerfil());
		System.out.println("Nombre: " + perfil1.getNombre());
		System.out.println("Precio/hora: " + perfil1.getPrecioHora());
		//4. Introducir informacion a traves del set al objeto, individualmente.
		
		Perfil perfil2 = new Perfil();
		perfil2.setIdPerfil(6);
		perfil2.setNombre("Juridico");
		perfil2.setPrecioHora(59);
		//5. Obtener informacion a traves del get de otro objeto, individualmente.
		
		System.out.println(SEPARACION);
		System.out.println(perfil2);
		System.out.println(SEPARACION);
		System.out.println("DATOS DEL PERFIL");
		System.out.println(SEPARACION);
		System.out.println("ID Perfil: " + perfil2.getIdPerfil());
		System.out.println("Nombre: " + perfil2.getNombre());
		System.out.println("Precio/hora: " + perfil2.getPrecioHora());
	}

}
