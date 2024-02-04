package modelo.presentacion.opciones;


import java.util.List;
import java.util.Scanner;

import modelo.entidad.Usuario;
import modelo.negocio.GestorUsuario;

public class OpcionesVista {
	
	//login del usuario
	/**
     * Realiza la opción de inicio de sesión del usuario.Comprueba si el nombre y la contraseña están en el
     * archivo txt
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion1(Scanner leer) {
		GestorUsuario gestor= new GestorUsuario();
		Usuario usuarioAuxiliar =introducirDatosLogin(leer);		
		if (gestor.validarUsuario(usuarioAuxiliar) == 2) {
			System.out.println("¡Bienvenido, " + usuarioAuxiliar.getNombre() + "!");
		}else {
        	
            System.out.println("Usuario o contraseña incorrectos.");
        }
			
	}
	//nuevo usuario, escritura del archivo si no existe ya
	/**
     * Realiza la opción de crear un nuevo usuario , comprueba si ya existe ese nombre
     * y en caso de que no existe lo añade .
     *
     * @param leer Scanner utilizado para la entrada de datos.
     */
	public void opcion2(Scanner leer) {
		GestorUsuario gestor= new GestorUsuario();
		Usuario usuarioAuxiliar = introducirDatos(leer);
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
	//listar las usuarios
	/**
     * Lista todos los usuarios registrados.
     */
	public void opcion3() {
		GestorUsuario gestor= new GestorUsuario();
		List<Usuario> listaAuxiliar = gestor.usuariosRegistrados();
		if (listaAuxiliar.isEmpty()) {
			System.out.println("No existe ningun usuario registrado");
		}
		else {
			System.out.println("------LISTADO DE USUARIOS REGISTRADOS --------");
			for(Usuario usuario : listaAuxiliar) {				
				System.out.println("Nombre: " + usuario.getNombre());
				System.out.println("Contraseña: " + usuario.getPassword());
				System.out.println("Contraseña: " + usuario.getEdad()+"\n");
			}
			
		}	
		
	}
	/**
     * Introduce los datos del usuario mediante la entrada estándar.
     * Nos pide el nombre, la contraseña y la edad
     *
     * @param leer Scanner utilizado para la entrada de datos.
     * @return Usuario creado con los datos introducidos.
     */
	public Usuario introducirDatos(Scanner leer) {
		Usuario usuarioAuxiliar = new Usuario();
		System.out.println("Nombre de usuario");
		String nombreUsuario = leer.next();
		System.out.println("Contraseña");
		String passUsuario = leer.next();
		System.out.println("Edad");
		int edadUsuario = leer.nextInt();
		usuarioAuxiliar.setNombre(nombreUsuario);
		usuarioAuxiliar.setPassword(passUsuario);
		usuarioAuxiliar.setEdad(edadUsuario);
		return usuarioAuxiliar;
		
	}
	/**
     * Introduce los datos del usuario mediante la entrada estándar.
     * Nos pide el nombre, la contraseña y la edad
     *
     * @param leer Scanner utilizado para la entrada de datos.
     * @return Usuario creado con los datos introducidos.
     */
	public Usuario introducirDatosLogin(Scanner leer) {
		Usuario usuarioAuxiliar = new Usuario();
		System.out.println("Nombre de usuario");
		String nombreUsuario = leer.next();
		System.out.println("Contraseña");
		String passUsuario = leer.next();
		usuarioAuxiliar.setNombre(nombreUsuario);
		usuarioAuxiliar.setPassword(passUsuario);
		return usuarioAuxiliar;
		
	}
	//metodos al iniciar el programa, de momento llamamos al metodos para crear el archivo
	/**
     * Inicia el programa, llamando al método para crear el archivo y muestra mensajes según el resultado.
     */
	public void iniciarPrograma() {
		GestorUsuario gestor= new GestorUsuario();
		int crearFichero= gestor.iniciarFichero();
		switch (crearFichero) {
	        case 1://login del usuario, lectura del archivo
	        	System.out.println("Creado el archivo");
	            break;
	        case 2://nuevo usuario, escritura del archivo si no existe ya
	        	System.out.println("Error al crear el archivo");
	            break;
	        case 3:
	        	System.out.println("El archivo ya existe");
	            break;

		}
		
		
	}
	
	

}
