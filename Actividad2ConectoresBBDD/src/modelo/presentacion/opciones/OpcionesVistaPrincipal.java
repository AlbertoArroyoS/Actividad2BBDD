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
		
		Coche cocheAuxiliar = introducirDatos(leer);
		
		int resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		String marcaCoche;
		String modeloCoche;
		
		while (resultadoValidar==1 || resultadoValidar==2 || resultadoValidar==3) {
			switch (resultadoValidar) {
		        case 0:
		        	System.out.println("** Coche NO añadido **");
		        	System.out.println("La marca del coche no puede estar vacia:\n");
		        	System.out.println("Marca:");
		    		marcaCoche = leer.nextLine();
		    		cocheAuxiliar.setMarca(marcaCoche);
		    		resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		        	break;
		        case 1:
		        	System.out.println("** Coche NO añadido **");
		        	System.out.println("El modelo del coche no puede esta vacio:\n");
		        	System.out.println("Modelo:");
		    		modeloCoche = leer.nextLine();
		    		cocheAuxiliar.setModelo(modeloCoche);
		    		resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		            break;
		        case 2:
		        	System.out.println("** Coche NO añadido **");
		        	System.out.println("Eo modelo y la marca del coche no pueden estar vacios:\n");
		        	System.out.println("Marca:");
		    		marcaCoche = leer.nextLine();
		    		System.out.println("Modelo:");
		    		modeloCoche = leer.nextLine();
		    		cocheAuxiliar.setMarca(marcaCoche);
		    		cocheAuxiliar.setModelo(modeloCoche);
		    		resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		            break;
			}		
		}
		if(resultadoValidar==0) {
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
	
	}
	
	public void opcion2(Scanner leer) {
		System.out.println("Id del coche a eliminar:");
		int idBorrar = leer.nextInt();
		int bajaCoche= gestor.eliminarCoche(idBorrar);
		switch (bajaCoche) {
	        case 0:
	        	System.out.println("** Coche NO borrado **");
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
		Coche cocheAuxiliar = introducirDatos(leer);
		
		int resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		String marcaCoche;
		String modeloCoche;
		
		while (resultadoValidar==1 || resultadoValidar==2 || resultadoValidar==3) {
			switch (resultadoValidar) {
		        case 0:
		        	System.out.println("** Coche NO modificado **");
		        	System.out.println("La marca del coche no puede estar vacia:\n");
		        	System.out.println("Marca:");
		    		marcaCoche = leer.nextLine();
		    		cocheAuxiliar.setMarca(marcaCoche);
		    		resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		        	break;
		        case 1:
		        	System.out.println("** Coche NO modificado **");
		        	System.out.println("El modelo del coche no puede esta vacio:\n");
		        	System.out.println("Modelo:");
		    		modeloCoche = leer.nextLine();
		    		cocheAuxiliar.setModelo(modeloCoche);
		    		resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		            break;
		        case 2:
		        	System.out.println("** Coche NO modificado **");
		        	System.out.println("Eo modelo y la marca del coche no pueden estar vacios:\n");
		        	System.out.println("Marca:");
		    		marcaCoche = leer.nextLine();
		    		System.out.println("Modelo:");
		    		modeloCoche = leer.nextLine();
		    		cocheAuxiliar.setMarca(marcaCoche);
		    		cocheAuxiliar.setModelo(modeloCoche);
		    		resultadoValidar = gestor.validarCoche(cocheAuxiliar);
		            break;
			}		
		}
		if(resultadoValidar==0) {
			int modificarCoche = gestor.modificarCoche(cocheAuxiliar);
			switch (modificarCoche) {
		        case 0:
		        	System.out.println("** Coche NO modificado **");
		        	break;
		        case 1:
		        	System.out.println("** Coche modificado correctamente **");
		            break;
		        case 2:
		        	System.out.println("** Coche NO modificado. Error al establecer la conexión **");
		            break;
		        case 3:
		        	System.out.println("** Error de Excepción **");
		            break;
			}		
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
	
	public void opcion6Consultar(Scanner leer) {
		
		System.out.println("url:"+ gestor.obtenerPropiedades("url"));
		System.out.println("nombreBBDD:"+gestor.obtenerPropiedades("nombreBBDD"));
		System.out.println("usuario:"+gestor.obtenerPropiedades("usuario"));
		System.out.println("password:"+gestor.obtenerPropiedades("password"));
	
	}
	
	public void opcion6Editar(Scanner leer) {
		gestor.cambiarPropiedad(null, null);
	}
	
	public void opcion7(Scanner leer) {
		
	}
	
	
	
	/**
     * Inicia el programa, llamando al método para crear el archivo y muestra mensajes según el resultado.
     */
	public void iniciarPrograma() {
		int crearFichero= gestor.iniciarFichero();
		switch (crearFichero) {
	        case 1://login del usuario, lectura del archivo
	        	//System.out.println("Creado el archivo");
	        	System.out.println("Conexion correcta");
	            break;
	        case 2://nuevo usuario, escritura del archivo si no existe ya
	        	System.out.println("Error al crear el archivo");
	            break;
	        case 3:
	        	System.out.println("El archivo ya existe");
	            break;

		}
	
	
	
	
	
	//metodos propios
	public Coche introducirDatos(Scanner leer) {
		
		Coche cocheAuxiliar = new Coche();
		System.out.println("Marca:");
		String marcaCoche = leer.nextLine();
		System.out.println("Modelo:");
		String modeloCoche = leer.nextLine();
		System.out.println("Año de fabricación:");
		int fabYear = leer.nextInt();
		System.out.println("Año de fabricación:");
		int kilometros = leer.nextInt();
		cocheAuxiliar.setMarca(marcaCoche);
		cocheAuxiliar.setModelo(modeloCoche);
		cocheAuxiliar.setFabYear(fabYear);
		cocheAuxiliar.setKilometros(kilometros);
		
		return cocheAuxiliar;
	}

}
