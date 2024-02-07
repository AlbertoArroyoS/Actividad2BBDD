package modelo.negocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.acceso.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {
	
	private DaoPasajero daoPasajero= new DaoPasajeroMySql();
	/**
	 * Método que agrega un pasajero a la persistencia
	 * @param pasajero a agregar al motor de persistencia
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	public int altaPasajero(Pasajero pasajero) {
		int altaPasajero = daoPasajero.altaPasajero(pasajero);
		return altaPasajero;
	}
	
	/**
	 * Método para eliminar un pasajero por su id
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha borrado ningun coche
	 *         - <b>1</b> se ha eliminado correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	public int eliminarPasajero(int id) {
		int borrarPasajero = daoPasajero.eliminarPasajero(id);
		return borrarPasajero;
	}
	
	/**
	 * Método que devuelve un objeto pasajero a partir de su id
	 * @param id representa el id del pasajero que vamos a
	 * hacer la busqueda.
	 * @return - <b>el objeto coche</b> si existe en la base de datos
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
	 */
	public Pasajero buscarPasajero(int id) {
		Pasajero pasajeroBuscado = daoPasajero.buscarPasajero(id);
		return pasajeroBuscado;
	}
	
	/**
	 * Método que devuelve una lista de pasajeros
	 * @return - <b>List<Usuario>
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
	 */
	public List<Pasajero> buscarTodosCoches() {
		List<Pasajero> listaAuxiliar = daoPasajero.buscarTodosPasajeros();
		return listaAuxiliar;
	}
	
	/**
	 * Metodo para asocia un pasajero a un coche en la base de datos.
	 *
	 * @param idCoche    El ID del coche al cual se asociará el pasajero.
	 * @param idPasajero El ID del pasajero que se asociará al coche.
	 * @return Un valor entero que representa el resultado de la operación:
	 *         - 1: Éxito, el pasajero se asoció al coche correctamente.
	 *         - 2: No se pudo abrir la conexión a la base de datos.
	 *         - 3: Ocurrió una excepción SQLException durante la ejecución.
	 */
	public int addPasajeroCoche(int idCoche, int idPasajero) {
		int addPC=daoPasajero.addPasajeroCoche(idCoche, idPasajero);
		return addPC;
	}
	
	/**
	 * Metodo para eliminar un pasajero de un coche en la base de datos.
	 *
	 * @param idCoche    El ID del coche del cual se eliminará el pasajero.
	 * @param idPasajero El ID del pasajero que se eliminará del coche.
	 * @return Un valor entero que representa el resultado de la operación:
	 *         - 1: Éxito, el pasajero fue eliminado del coche.
	 *         - 0: No se encontró el coche o el pasajero asociado.
	 *         - 2: No se pudo abrir la conexión a la base de datos.
	 *         - 3: Ocurrió una excepción SQLException durante la ejecución.
	 */
	public int eliminarPasajeroCoche(int idCoche, int idPasajero) {
		int borrarPC=daoPasajero.eliminarPasajeroCoche(idCoche, idPasajero);
		return borrarPC;
	}
	
	/**
     * Obtiene una lista de coches con información sobre los pasajeros asociados a un coche específico.
     *
     * @param idCoche El ID del coche del cual se desea obtener la información de pasajeros.
     * @return Una lista de objetos Coche con información sobre los pasajeros asociados al coche.
     *         Si no se puede abrir la conexión, devuelve null.
     */
	public List<Coche> pasajerosEnCoche(int idCoche) {
		List<Coche> listaAuxiliar = daoPasajero.pasajerosEnCoche(idCoche);
		return listaAuxiliar;
    }
}
