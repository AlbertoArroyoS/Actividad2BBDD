package principales;

import java.util.Scanner;

import modelo.dao.ClienteDao;
import modelo.dao.ClienteDaoImplMy8;
import modelo.javabean.Cliente;
/**
 * Clase GestionClientes con un  menú con las siguientes opciones:
 * 1. Alta del Cliente
 * 2. Buscar un Cliente
 * 3. Mostrar Todos.
 * 4. Eliminar un cliente
 * 5. Salir
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class GestionCliente {
	
	// Declaramos los atributos estaticos
	
	private static Scanner leer;
	private static final String SEPARACION = "------------------------------------------";
	static ClienteDao cdao = new ClienteDaoImplMy8();
	static String cif, nombre, apellidos, domicilio; 
	static double facturacionAnual; 
	static int numeroEmpleados;
	
	//Para introducir datos a traves del teclado
	
	static {
		leer = new Scanner(System.in);
	}
	
	//main
	public static void main(String[] args) {
		int opcion = 0;		
		opcion = pintarMenu();
		while(opcion !=5) {	
			switch(opcion) {
			//1. Alta del Cliente
			case 1:
				altaCliente();
				break;
			//2. Buscar un Cliente
			case 2:
				buscarCliente();
				break;
			//3. Mostrar Todos.
			case 3:
				mostrarTodos();
				break;
			//4. Eliminar un cliente
			case 4:
				eliminarCliente();
				break;
			default:
				System.out.println("opcion erronea ...");
			}
			opcion = pintarMenu();
		}
		System.out.println("FIN DE PROGRAMA");
	}
		
	// Metodos del main
	
	//Metodo para dar de alta un cliente
	public static void altaCliente() {
		System.out.println(SEPARACION);
		System.out.println("DAR DE ALTA A UN CLIENTE");
		System.out.println(SEPARACION);
		System.out.println("Introduzca cif: ");
		cif = leer.next();
		System.out.println("Introduzca nombre: ");
		nombre = leer.next();
		leer.nextLine();
		System.out.println("Introduzca apellidos: ");
		apellidos = leer.nextLine();
		System.out.println("Introduzca domicilio: ");
		domicilio = leer.nextLine();
		System.out.println("Introduzca facturacion anual: ");
		facturacionAnual = leer.nextDouble();
		System.out.println("Introduzca numero de empleados: ");
		numeroEmpleados = leer.nextInt();
		
		Cliente cliente = new Cliente(cif, nombre, apellidos, domicilio, facturacionAnual, numeroEmpleados);

		if (cdao.altaCliente(cliente) == 1) {
			System.out.println("\nAÑADIDO CORRECTAMENTE\n");
		for(Cliente ele: cdao.buscarTodos())
			System.out.println(ele);	
		}
		else
			System.out.println("NO SE HA AÑADIDO CORRECTAMENTE");
		
		System.out.println(SEPARACION);
	}
	
	//Metodo para buscar clientes
	public static  void buscarCliente() {
		
		System.out.println(SEPARACION);
		System.out.println("BUSCAR UN CLIENTE");
		System.out.println(SEPARACION);
		System.out.println("Introduzca cif a buscar : ");
		cif = leer.next();
		Cliente cli = cdao.buscarCliente(cif);
		if (cli != null)
			System.out.println(cli);
		else
			System.out.println("Cliente no encontrado");
		
		System.out.println(SEPARACION);
	}
	
	//Metodo para mostrar todos los clientes
	public static void mostrarTodos() {
		System.out.println(SEPARACION);
		System.out.println("TODOS LOS CLIENTES");
		System.out.println(SEPARACION);
		for(Cliente ele: cdao.buscarTodos())
			System.out.println(ele);
		
		System.out.println(SEPARACION);
	}
	
	//Metodo para eliminar un cliente
	public static void eliminarCliente() {
		System.out.println(SEPARACION);
		System.out.println("ELIMINAR CLIENTE");
		System.out.println(SEPARACION);
		System.out.println("Introduzca cif a ELIMINAR : ");
		cif = leer.next();
		if (cdao.eliminarCliente(cif) == 1) 
			System.out.println("ELIMINADO CORRECTAMENTE");
			
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE");
		System.out.println(SEPARACION);

	}

	//Metodo para mostrar el menu
	public static int pintarMenu() {
		
		int opcion = 0;
		System.out.println("\n"+SEPARACION);
		System.out.println("             MENU DE OPCIONES");
		System.out.println(SEPARACION);
		System.out.println("1. Alta del Cliente");
		System.out.println("2. Buscar un Cliente");
		System.out.println("3. Mostrar Todos");
		System.out.println("4. Eliminar un cliente");
		System.out.println("5. Salir");
		System.out.println(SEPARACION);
		System.out.println("Teclee una opcion del 1 al 4, 5 para salir");
		System.out.println(SEPARACION);
		opcion = leer.nextInt();
		
		while(opcion<1 || opcion > 5) {
			System.out.println("**POR FAVOR, INTRODUZCA OPCION DEL 1 AL 5**");
			opcion = leer.nextInt();
		}			
		return opcion;
	}
}
