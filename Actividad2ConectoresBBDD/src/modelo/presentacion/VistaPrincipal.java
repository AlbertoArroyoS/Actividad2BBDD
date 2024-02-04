package modelo.presentacion;

import java.util.Scanner;

import modelo.presentacion.opciones.OpcionesVistaPrincipal;



public class VistaPrincipal {

	private static Scanner leer;
	//Para poder leer las opciones del menu	que estan en un metodo estatico
	static {
			leer = new Scanner(System.in);
	}

	public static void main(String[] args) {
		
		OpcionesVistaPrincipal opciones = new OpcionesVistaPrincipal();
		opciones.iniciarPrograma();

		boolean continuar = true;
		
		do {
        	//Cargamos el menu inicial y recuperamos la opción elegida
			int opcion = menu();
			//Si la opcion está fuera del rango de opciones se repetira el menu
			while (opcion<1 || opcion>7){
				opcion = menu();
			}

	            switch (opcion) {
	                case 1://login del usuario, lectura del archivo
	                	opciones.opcion1(leer);
	                    break;
	                case 2://nuevo usuario, escritura del archivo si no existe ya
	                	opciones.opcion2(leer);
	                    break;
	                case 3:
	                	opciones.opcion3();
	                    break;
	                case 4:
	                	System.out.println("Programa terminado");
	                    continuar=false;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			
        } while (continuar);
		

        // Cierro el scanner
        if (leer != null) {
            leer.close();
        }
				
}


public static int menu() {
	
	int opcion = 0;
	System.out.println("----------------------------------------------------");
	System.out.println("|                MENU  PRINCIPAL                   |");
	System.out.println("----------------------------------------------------");
	System.out.println("1. Añadir nuevo coche");
	System.out.println("2. Borrar coche ");
	System.out.println("3. Consulta coche ");
	System.out.println("4. Listado de coches");
	System.out.println("5. Gestión de pasajeros");
	System.out.println("6. Configurar usuario BBDD");
	System.out.println("7. Terminar el programa");
	System.out.println("----------------------------------------------------");
	System.out.println("Introduzca una opción del 1 al 7, si quiere salir 7");
	System.out.println("----------------------------------------------------");
	
	try {
		opcion = leer.nextInt();
		
	} catch (java.util.InputMismatchException e) {
        // Atrapar la excepción si se ingresa algo que no es un entero
        System.out.println("Entrada no válida. Ingrese un número entero.");
        leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
    }
	
	if (opcion<1 || opcion > 7) {
		System.out.println("OPCION INCORRECTA");
	}
	
	return opcion;	
	}
public static int menuGestionPasajeros() {
	
	int opcion = 0;
	System.out.println("----------------------------------------------------");
	System.out.println("|            MENU  GESTIÓN PASAJEROS               |");
	System.out.println("----------------------------------------------------");
	System.out.println("1. Crear nuevo pasajero");
	System.out.println("2. Borrar pasajero por id ");
	System.out.println("3. Consulta pasajero por id ");
	System.out.println("4. Listar todos los pasajeros");
	System.out.println("5. Añadir pasajero a coche");
	System.out.println("6. Eliminar pasajero de un coche");
	System.out.println("7. Listar todos los pasajeros de un coche");
	System.out.println("8. Volver al menú principal");
	System.out.println("9. Terminar el programa");
	System.out.println("----------------------------------------------------");
	System.out.println("Introduzca una opción del 1 al 9, si quiere salir 9");
	System.out.println("----------------------------------------------------");
	
	try {
		opcion = leer.nextInt();
		
	} catch (java.util.InputMismatchException e) {
        // Atrapar la excepción si se ingresa algo que no es un entero
        System.out.println("Entrada no válida. Ingrese un número entero.");
        leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
    }
	
	if (opcion<1 || opcion > 9) {
		System.out.println("OPCION INCORRECTA");
	}
	
	return opcion;	
	}

}
