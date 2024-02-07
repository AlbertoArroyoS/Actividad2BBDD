package modelo.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.acceso.ConfiguracionPropiedades;
import modelo.persistencia.acceso.DaoCocheMySql;
import modelo.persistencia.acceso.TablasBaseDeDatos;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {
	
	private DaoCoche daoCoche = new DaoCocheMySql();
	
	
	
	
	//validar datos introducidos del coche

	/**
	 * Método que valida si los datos introducidos del coche son correctos
	 * 
	 * @param usuario El objeto Coche a ser validado.
	 * @return Entero que indica el resultado de la validación:
	 *         - 0 si la validacion no ha dado ningun problema
	 *         - 1 si el string pasado por parametro esta vacio o solo tiene espacios
	 */
	public boolean validarCampoVacio(String opcion) {
		if(opcion.isBlank()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Método que agrega un coche a la persistencia
	 * @param coche a agregar al motor de persistencia
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	public int altaCoche(Coche coche) {
		int altaCoche = daoCoche.altaCoche(coche);
		return altaCoche;
	}
	
	/**
	 * Método para eliminar un coche por su id
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha borrado ningun coche
	 *         - <b>1</b> se ha eliminado correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	public int eliminarCoche(int id) {
		int borrarCoche = daoCoche.eliminarCoche(id);
		return borrarCoche;
	}
	/**
	 * Método que devuelve un objeto coche a partir de su id
	 * @param id representa el id del coche que vamos a
	 * hacer la busqueda.
	 * @return - <b>el objeto coche</b> si existe en la base de datos
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
	 */
	public Coche buscarCoche(int id) {
		Coche cocheBuscado = daoCoche.buscarCoche(id);
		return cocheBuscado;
	}
	/**
	 * Método para modificar un coche de la base de datos introduciendo su id
	 * por parámetro
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha modificado ningun coche
	 *         - <b>1</b> si se ha modificado correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	public int modificarCoche(Coche coche) {
		int modificarCoche = daoCoche.modificarCoche(coche);
		return modificarCoche;
	}
	
	/**
	 * Método que devuelve una lista de coches
	 * @return - <b>List<Usuario>
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
	 */
	public List<Coche> buscarTodosCoches() {
		List<Coche> listaAuxiliar = daoCoche.buscarTodosCoches();
		return listaAuxiliar;
	}
	
	public String obtenerPropiedades(String propiedad) {
    	ConfiguracionPropiedades configuracion = new ConfiguracionPropiedades();

		return configuracion.getProperty(propiedad);
		
	}

	/**
	 * Crea la conexion para el almacenamiento de usuarios y llama al medoto para
	 * crear la base de datos en caso que que no exista y al metodo para crear
	 * la tabla con sus propiedades
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>1</b> si la conexión se creó exitosamente.
	 *         - <b>2</b> si hubo un error al intentar hacer la conexion
	 */
	public int iniciarAccesoADatos() {
		TablasBaseDeDatos tablas = new TablasBaseDeDatos();
		int accesoADatos= tablas.accesoADatos();
		return accesoADatos;
	}
	/**
	 * Metodo para acceder a un fichero properties que esta dentro de nuestro "classpath"
	 * @return true si la conexion ha sido correcta y false en caso de que no
	 */
	public boolean iniciarPropiedades() {
		ConfiguracionPropiedades conf = new ConfiguracionPropiedades();
		boolean configuracion=conf.inicializarPropiedades();
		return configuracion;
	}
	
	
}
