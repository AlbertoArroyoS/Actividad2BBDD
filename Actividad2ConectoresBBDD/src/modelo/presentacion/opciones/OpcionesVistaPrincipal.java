package modelo.presentacion.opciones;

import java.util.Scanner;

import modelo.entidad.Usuario;

public class OpcionesVistaPrincipal {
	
	
	//opcion 1 del menu principal, añadir coche
	/**
     * Realiza la opción de crear un nuevo usuario , comprueba si ya existe ese nombre
     * y en caso de que no existe lo añade .
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion1(Scanner leer) {
		Coche cocheAuxiliar = introducirDatos(leer);
		int resultadoValidar = gestor.validarUsuario(usuarioAuxiliar);
		switch (resultadoValidar) {
	        case 1:
	        	System.out.println("** Usuario NO añadido **");
	        	System.out.println("Ya existe un usuario con ese nombre");
	        	break;
	        case 2:
	        	System.out.println("** Usuario NO añadido **");
	        	System.out.println("Ya existe un usuario con ese nombre y esa contraseña");
	            break;
	        case 3:
	        	System.out.println("** Usuario NO añadido **");
	        	System.out.println("No se pueden añadir usuarios menores de 18 años.");
	            break;
	        default:
	        	if(gestor.introducir(usuarioAuxiliar)) {
					System.out.println("Usuario añadido correctamente.");
				}
				else {
					System.out.println("Error al introducir el usuario.");
				}
	            break;
		}		
	
	}

}
