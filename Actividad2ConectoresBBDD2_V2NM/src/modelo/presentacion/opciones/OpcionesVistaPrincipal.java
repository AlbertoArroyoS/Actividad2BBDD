package modelo.presentacion.opciones;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;



public class OpcionesVistaPrincipal {
	
	
	private GestorCoche gestor= new GestorCoche();
	
	//opcion 1 del menu principal, añadir coche
	/**
     * Realiza la opción de crear un nuevo coche , comprueba si ya existe ese nombre
     * y en caso de que no existe lo añade .
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
	
	public void opcion6(Scanner leer) {
		
	}
	
	public void opcion7() {
		System.out.println("----------------------------------------------------");
		System.out.println("|                DATOS  ACCESO                     |");
		System.out.println("----------------------------------------------------");
		System.out.println("url:"+ gestor.obtenerPropiedades("url"));
		System.out.println("nombreBBDD:"+gestor.obtenerPropiedades("nombreBBDD"));
		System.out.println("usuario:"+gestor.obtenerPropiedades("usuario"));
		System.out.println("password:"+gestor.obtenerPropiedades("password"));
		System.out.println("----------------------------------------------------");
	}
	
	
	
	/**
     * Inicia el programa, llamando al método para conectarse a la BBDD y crear
     * la base de datos y la tabla en caso de que no exista. Y accede al fichero
     * properties que contiene los datos para la conexion.
     * Muestra mensajes según el resultado.
     */
	public void iniciarPrograma() {
		
		boolean accesoAPropiedades=gestor.iniciarPropiedades();
		if (accesoAPropiedades) {
			System.out.println("Acceso a propiedades correcto");
		}else {
			System.out.println("Error al acceder a propiedades");
		}
		int accesoADatos= gestor.iniciarAccesoADatos();
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
	public Coche introducirDatos() {
		
		boolean validarVacio=true;
		Scanner leer = new Scanner(System.in);
		Coche cocheAuxiliar = new Coche();	
		
		while (validarVacio) {
			System.out.println("Marca:");
			String marcaCoche = leer.nextLine();
			cocheAuxiliar.setMarca(marcaCoche);
			if(validarVacio=gestor.validarCampoVacio(marcaCoche)) {
				System.out.println("**La marca del coche no puede estar vacia\n");
				
			}
		}
		validarVacio=true;
		while (validarVacio) {
			System.out.println("Modelo:");
			String modeloCoche = leer.nextLine();
			cocheAuxiliar.setModelo(modeloCoche);
			if(validarVacio=gestor.validarCampoVacio(modeloCoche)) {
				System.out.println("**El modelo del coche no puede estar vacio:\n");
			}
		}
		System.out.println("Año de fabricación:");
		int fabYear = leer.nextInt();
		System.out.println("Kilometros:");
		int kilometros = leer.nextInt();

		cocheAuxiliar.setFabYear(fabYear);
		cocheAuxiliar.setKilometros(kilometros);
		
		return cocheAuxiliar;
	}

}
