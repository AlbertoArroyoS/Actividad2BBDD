package modelo.presentacion.opciones;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorDatos;


/**
 * Clase que define las opciones del menú principal para gestionar coches.
 * Permite realizar operaciones como añadir, eliminar, consultar y modificar coches,
 * listar todos los coches registrados, mostrar datos de conexión a la base de datos,
 * e iniciar el programa gestionando la conexión y acceso a la base de datos.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 * @since 2024-02-08
 */
public class OpcionesVistaPrincipal {
	
	
	private GestorCoche gestor= new GestorCoche();
	private GestorDatos gestorDatos = new GestorDatos();
	
	/**
     * Realiza la opción de crear un nuevo coche, comprueba si ya existe ese nombre
     * y en caso de que no existe lo añade.
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion1(Scanner leer) {
		
		Coche cocheAuxiliar = introducirDatos();
			int altaCoche = gestor.altaCoche(cocheAuxiliar);
			switch (altaCoche) {
		        case 0:
		        	System.out.println("** Coche NO añadido **");
		        	break;
		        case 1:
		        	System.out.println("** Coche añadido correctamente **");
		            break;
		        case 2:
		        	System.out.println("** Coche NO añadido. Error al establecer la conexión **");
		            break;
		        case 3:
		        	System.out.println("** Error de Excepción **");
		            break;
		}		
	}		
	
	
	/**
     * Realiza la opción de eliminar un coche por su ID.
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion2(Scanner leer) {
		System.out.println("Id del coche a eliminar:");
		int idBorrar = leer.nextInt();
		int bajaCoche= gestor.eliminarCoche(idBorrar);
		switch (bajaCoche) {
	        case 0:
	        	System.out.println("** No existe ningun coche con ese ID **");
	        	break;
	        case 1:
	        	System.out.println("** Coche borrado correctamente **");
	            break;
	        case 2:
	        	System.out.println("** Coche NO borrado. Error al establecer la conexión **");
	            break;
	        case 3:
	        	System.out.println("** Error de Excepción **");
	            break;
		}		
	}
	/**
     * Realiza la opción de consultar un coche por su ID e imprime sus detalles.
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion3(Scanner leer) {
		System.out.println("Id del coche a consultar:");
		int idConsultar = leer.nextInt();
		Coche cocheAuxiliar = gestor.buscarCoche(idConsultar);
		if(cocheAuxiliar!=null) {
			System.out.println("-----COCHE ENCONTRADO--------------------");
			System.out.println("Id: " + cocheAuxiliar.getId());
			System.out.println("Marca: " + cocheAuxiliar.getMarca());
			System.out.println("Modelo: " + cocheAuxiliar.getModelo());
			System.out.println("Año de fabricacion: " + cocheAuxiliar.getFabYear());
			System.out.println("Kilometros: "+ cocheAuxiliar.getKilometros());
		}else {
			System.out.println("No se ha encontrado ningún coche");
		}
	
	}
	/**
     * Realiza la opción de modificar un coche por su ID con nuevos datos.
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion4(Scanner leer) {
		System.out.println("Indroduzca el ID del coche a modificar:");
		int idBuscar = leer.nextInt();
		Coche cocheAuxiliar = introducirDatos();
		cocheAuxiliar.setId(idBuscar);

		int modificarCoche = gestor.modificarCoche(cocheAuxiliar);
		switch (modificarCoche) {
	        case 0:
	        	System.out.println("** Coche NO modificado **");
	        	break;
	        case 1:
	        	System.out.println("** Coche modificado correctamente **");
	        	System.out.println(cocheAuxiliar);
	            break;
	        case 2:
	        	System.out.println("** Coche NO modificado. Error al establecer la conexión **");
	            break;
	        case 3:
	        	System.out.println("** Error de Excepción **");
	            break;
		}		
	}	
	 /**
     * Realiza la opción de listar todos los coches registrados.
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion5(Scanner leer) {
		List<Coche> listaAuxiliar = gestor.buscarTodosCoches();
		if (listaAuxiliar == null || listaAuxiliar.isEmpty()) {
			System.out.println("No existe ningun coche registrado");
		}
		else {
			System.out.println("------LISTADO DE COCHES REGISTRADOS --------");
			for(Coche coche : listaAuxiliar) {				
				System.out.println("Id: " + coche.getId());
				System.out.println("Marca: " + coche.getMarca());
				System.out.println("Modelo: " + coche.getModelo());
				System.out.println("Año de fabricacion: " + coche.getFabYear());
				System.out.println("Kilometros: "+ coche.getKilometros()+"\n");
			}
			
		}	
	}
	/**
     * Realiza la opción 6 (pendiente de futura implementación).
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion6(Scanner leer) {
		
	}
	/**
     * Muestra los datos de conexión a la base de datos.
     */
	public void opcion7() {
		System.out.println("----------------------------------------------------");
		System.out.println("|                DATOS  ACCESO                     |");
		System.out.println("----------------------------------------------------");
		System.out.println("url:"+ gestorDatos.obtenerPropiedades("url"));
		System.out.println("nombreBBDD:"+gestorDatos.obtenerPropiedades("nombreBBDD"));
		System.out.println("usuario:"+gestorDatos.obtenerPropiedades("usuario"));
		System.out.println("password:"+gestorDatos.obtenerPropiedades("password"));
		System.out.println("----------------------------------------------------");
	}
	
	
	
	/**
     * Inicia el programa, llamando al método para conectarse a la BBDD y crear
     * la base de datos y la tabla en caso de que no exista. Y accede al fichero
     * properties que contiene los datos para la conexión.
     * Muestra mensajes según el resultado.
     */
	public void iniciarPrograma() {
		
		boolean accesoAPropiedades=gestorDatos.iniciarPropiedades();
		if (accesoAPropiedades) {
			System.out.println("Acceso a propiedades correcto");
		}else {
			System.out.println("Error al acceder a propiedades");
		}
		int accesoADatos= gestorDatos.iniciarAccesoADatos();
		switch (accesoADatos) {
	        case 1://login del usuario, lectura del archivo
	        	//System.out.println("Creado el archivo");
	        	System.out.println("Conexion correcta a datos");
	            break;
	        case 2://nuevo usuario, escritura del archivo si no existe ya
	        	System.out.println("Error al iniciar la BBDD");
	            break;

		}
		
	}

	//metodos propios
	
	/**
     * Método para introducir datos de un coche desde la consola.
     *
     * @return Objeto Coche creado a partir de los datos introducidos.
     */
	public Coche introducirDatos() {

		Scanner leer = new Scanner(System.in);
		Coche cocheAuxiliar = new Coche();	
		System.out.println("Marca:");
		String marcaCoche = gestorDatos.validarCampoVacioString();
		cocheAuxiliar.setMarca(marcaCoche);
		System.out.println("Modelo:");
		String modeloCoche = gestorDatos.validarCampoVacioString();
		cocheAuxiliar.setModelo(modeloCoche);
		System.out.println("Año de fabricación:");
		int fabYear = leer.nextInt();
		System.out.println("Kilometros:");
		int kilometros = leer.nextInt();
		cocheAuxiliar.setFabYear(fabYear);
		cocheAuxiliar.setKilometros(kilometros);	
		return cocheAuxiliar;
	}

}
