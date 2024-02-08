package modelo.negocio;

import java.util.Scanner;

import modelo.persistencia.datos.ConfiguracionPropiedades;
import modelo.persistencia.datos.TablasBaseDeDatos;
/**
 * Clase que gestiona la entrada de datos y configuraciones del sistema.
 * 
 * <p>Proporciona métodos para validar cadenas, obtener propiedades de configuración,
 * iniciar la conexión a la base de datos y cargar propiedades.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 * @since 2024-02-08
 */
public class GestorDatos {
	

	/**
	 * Método que valida si el campo introducido es vacío o solo tiene espacios.
	 * 
	 * @param opcion La cadena a validar.
	 * @return true si la cadena está vacía o solo tiene espacios, false en caso contrario.
	 */
	public String validarCampoVacioString() {
		
		Scanner leer = new Scanner(System.in);
		String opcionFrase = leer.nextLine();

		while (opcionFrase.isBlank()) {
			
			System.out.println("Este campo NO puede estar vacio");
			System.out.println("Introduzca valor de nuevo: ");
			opcionFrase = leer.nextLine();
		}
		
		return opcionFrase;
			
		
	}
	/**
	 * Método que obtiene una propiedad del archivo de configuración.
	 * 
	 * @param propiedad La propiedad a obtener.
	 * @return La propiedad solicitada.
	 */
	public String obtenerPropiedades(String propiedad) {
    	ConfiguracionPropiedades configuracion = new ConfiguracionPropiedades();

		return configuracion.getProperty(propiedad);
		
	}
	
	/**
	 * Método para iniciar la conexión a la base de datos y configurar las tablas.
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - 1 si la conexión se creó exitosamente.
	 *         - 2 si hubo un error al intentar hacer la conexión.
	 */
	public int iniciarAccesoADatos() {
		TablasBaseDeDatos tablas = new TablasBaseDeDatos();
		int accesoADatos= tablas.accesoADatos();
		return accesoADatos;
	}
	
	/**
	 * Método para iniciar la carga de propiedades.
	 * 
	 * @return true si la conexión ha sido correcta y false en caso contrario.
	 */
	public boolean iniciarPropiedades() {
		ConfiguracionPropiedades conf = new ConfiguracionPropiedades();
		boolean configuracion=conf.inicializarPropiedades();
		return configuracion;
	}
}
