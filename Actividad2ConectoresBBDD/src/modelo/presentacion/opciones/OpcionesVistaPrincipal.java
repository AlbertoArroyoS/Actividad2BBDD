package modelo.presentacion.opciones;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;



public class OpcionesVistaPrincipal {
	
	
	private GestorCoche gestor= new GestorCoche();
	
	//opcion 1 del menu principal, añadir coche
	/**
     * Realiza la opción de crear un nuevo usuario , comprueba si ya existe ese nombre
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
		        	System.out.println("** Coche NO añadido. Error al estableces la conexión **");
		            break;
		        case 3:
		        	System.out.println("** Error de Excepción **");
		            break;
			}		
		}		
	
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
