package modelo.presentacion.opciones;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;
import modelo.persistencia.acceso.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class OpcionesMenuPasajeros {
	
	private GestorPasajero gestorP = new GestorPasajero();
	private GestorCoche gestor= new GestorCoche();
	//opcion 1 del menu principal, añadir coche
	
		/**
	     * Realiza la opción de crear un nuevo coche , comprueba si ya existe ese nombre
	     * y en caso de que no existe lo añade .
	     *
	     * @param leer Scanner utilizado para la entrada de datos.
	     */
		public void opcion1(Scanner leer) {
			
			Pasajero pasajeroAuxiliar = introducirDatos();
				int altaCoche = gestorP.altaPasajero(pasajeroAuxiliar);
				switch (altaCoche) {
			        case 0:
			        	System.out.println("** Pasajero NO añadido **");
			        	break;
			        case 1:
			        	System.out.println("** Pasajero añadido correctamente **");
			            break;
			        case 2:
			        	System.out.println("** Pasajero NO añadido. Error al establecer la conexión **");
			            break;
			        case 3:
			        	System.out.println("** Error de Excepción **");
			            break;
			}		
		}		
		
		
		
		public void opcion2(Scanner leer) {
			System.out.println("Id del Pasajero a eliminar:");
			int idBorrar = leer.nextInt();
			int bajaPasajero= gestorP.eliminarPasajero(idBorrar);
			switch (bajaPasajero) {
		        case 0:
		        	System.out.println("** No existe ningun pasajero con ese ID **");
		        	break;
		        case 1:
		        	System.out.println("** Pasajero borrado correctamente **");
		            break;
		        case 2:
		        	System.out.println("** Pasajero NO borrado. Error al establecer la conexión **");
		            break;
		        case 3:
		        	System.out.println("** Error de Excepción **");
		            break;
			}		
		}
		
		public void opcion3(Scanner leer) {
			System.out.println("Id del pasajero a consultar:");
			int idConsultar = leer.nextInt();
			Pasajero pasajeroAuxiliar = gestorP.buscarPasajero(idConsultar);
			if(pasajeroAuxiliar!=null) {
				System.out.println("-----PASAJERO ENCONTRADO--------------------");
				System.out.println("Id: " + pasajeroAuxiliar.getId_pasajero());
				System.out.println("Nombre: " + pasajeroAuxiliar.getNombre());
				System.out.println("Edad: " + pasajeroAuxiliar.getEdad());
				System.out.println("Peso: " + pasajeroAuxiliar.getPeso());
				System.out.println("-----------------------------------------------");
			}else {
				System.out.println("No se ha encontrado ningún pasajero");
			}
		
		}
		
		public void opcion4(Scanner leer) {
			List<Pasajero> listaAuxiliar = gestorP.buscarTodosCoches();
			if (listaAuxiliar == null || listaAuxiliar.isEmpty()) {
				System.out.println("No existe ningun pasajero registrado");
			}
			else {
				System.out.println("------LISTADO DE PASAJEROS REGISTRADOS --------");
				for(Pasajero pasajero : listaAuxiliar) {				
					System.out.println("Id: " + pasajero.getId_pasajero());
					System.out.println("Nombre: " + pasajero.getNombre());
					System.out.println("Edad: " + pasajero.getEdad());
					System.out.println("Peso: " + pasajero.getPeso());
					System.out.println("-----------------------------------------------");
				}
				
			}	
		}
		//
		public void opcion5(Scanner leer) {
			System.out.println("¿Desea que le muestre primero los coches que no tienen pasajeros? ");
			System.out.println("Respuesta: Si o No");
			String respuesta =leer.next();
			if("Si".equalsIgnoreCase(respuesta)) {
				List<Coche> listaAuxiliar = gestor.mostrarCochesDisponibles();
				System.out.println("-----------------------------------------------");
				for(Coche coche: listaAuxiliar) {
					System.out.println("Id: " + coche.getId());
					System.out.println("Marca: " + coche.getMarca());
					System.out.println("Modelo: " + coche.getModelo());
					System.out.println("Año de fabricacion: " + coche.getFabYear());
					System.out.println("Kilometros: "+ coche.getKilometros()+"\n");
				}
			}
			
			System.out.println("--------Añadir pasajero a coche-------");
			System.out.println("Id del coche a añádir:");
			int idCoche = leer.nextInt();
			System.out.println("Id del pasajero a añadir:");
			int idPasajero = leer.nextInt();			
			int altaCoche=gestorP.addPasajeroCoche(idCoche, idPasajero);
			switch (altaCoche) {
	        case 1:
	        	System.out.println("** Pasajero añadido correctamente **");
	            break;
	        case 2:
	        	System.out.println("** Pasajero NO añadido. Error al establecer la conexión **");
	            break;
	        case 3:
	        	System.out.println("** Error de Excepción **");
	            break;
			}		
		}
		
		public void opcion6(Scanner leer) {
			System.out.println("¿Desea que le muestre primero todos los coches que no tienen pasajero asociado? ");
			System.out.println("Respuesta: Si o No");
			String respuesta =leer.next();
			if("Si".equalsIgnoreCase(respuesta)) {
				System.out.println("-----------------------------------------------");
				List<Coche> listaAuxiliar = gestor.mostrarCochesConPasajeros();
				if(listaAuxiliar!= null) {
					System.out.println("----Coches y sus pasajeros asociados---");
					for(Coche coche: listaAuxiliar) {
						System.out.println("****COCHE******");
						System.out.println("Id: " + coche.getId());
						System.out.println("Marca: " + coche.getMarca());
						System.out.println("Modelo: " + coche.getModelo());
						System.out.println("Año de fabricacion: " + coche.getFabYear());
						System.out.println("Kilometros: "+ coche.getKilometros()+"\n");
						System.out.println("****ASOCIADO A PASAJERO**");
						System.out.println("Id: " + coche.getPasajero().getId_pasajero());
						System.out.println("Nombre: " + coche.getPasajero().getNombre());
						System.out.println("Edad: " + coche.getPasajero().getEdad());
						System.out.println("Peso: " + coche.getPasajero().getPeso());
						System.out.println("******************************");
					}
				
					
				}else {
					System.out.println("NO hay ningun pasajero asociado a un coche");
				}
			}
			
			System.out.println("--------Eliminar pasajero de coche-------");
			System.out.println("Id del coche a eliminar:");
			int idCoche = leer.nextInt();
			System.out.println("Id del pasajero a eliminar:");
			int idPasajero = leer.nextInt();			
			int bajaCoche=gestorP.eliminarPasajeroCoche(idCoche, idPasajero);
			switch (bajaCoche) {
	        case 1:
	        	System.out.println("** Pasajero borrado correctamente **");
	            break;
	        case 2:
	        	System.out.println("** Pasajero NO borrado. Error al establecer la conexión **");
	            break;
	        case 3:
	        	System.out.println("** Error de Excepción **");
	            break;
			}		
		}
		
		public void opcion7(Scanner leer) {
			System.out.println("Id del coche a consultar:");
			int idConsultar = leer.nextInt();
			List<Coche> listaAuxiliar = gestorP.pasajerosEnCoche(idConsultar);
			if(!listaAuxiliar.isEmpty()) {
				for(Coche coche : listaAuxiliar) {
					System.out.println("******************************");
					System.out.println("El coche Tiene el siguientes pasajeros asociado");
					System.out.println("Id: " + coche.getPasajero().getId_pasajero());
					System.out.println("Nombre: " + coche.getPasajero().getNombre());
					System.out.println("Edad: " + coche.getPasajero().getEdad());
					System.out.println("Peso: " + coche.getPasajero().getPeso());
					System.out.println("******************************");
				}
			}else {
				System.out.println("No tiene ningun pasajero asociado");
			}
	
		}
		
		//metodos propios
		public Pasajero introducirDatos() {
			

			Scanner leer = new Scanner(System.in);
			Pasajero pasajeroAuxiliar = new Pasajero();	
			System.out.println("Nombre:");
			String nombrePasajero = leer.nextLine();
			System.out.println("Edad:");
			int edadPasajero = leer.nextInt();
			System.out.println("Peso:");
			double pesoPasajero= leer.nextDouble();
			pasajeroAuxiliar.setNombre(nombrePasajero);
			pasajeroAuxiliar.setEdad(edadPasajero);
			pasajeroAuxiliar.setPeso(pesoPasajero);
			
			return pasajeroAuxiliar;
		}

}
