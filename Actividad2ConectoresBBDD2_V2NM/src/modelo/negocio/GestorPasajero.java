package modelo.negocio;

import java.util.List;
import modelo.entidad.Pasajero;
import modelo.persistencia.acceso.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;
/**
 * Clase que gestiona la interacción con los pasajeros en el sistema.
 * 
 * <p>Proporciona métodos para dar de alta, eliminar y buscar pasajeros en la persistencia.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 * @since 2024-02-08
 */
public class GestorPasajero {
	
	private DaoPasajero daoPasajero= new DaoPasajeroMySql();
	/**
	 * Método que agrega un pasajero a la persistencia.
	 * 
	 * @param pasajero El pasajero a agregar.
	 * @return Entero que indica el resultado de la operación:
	 *         - 0 no se ha dado de alta ningún pasajero.
	 *         - 1 se ha añadido correctamente.
	 *         - 2 hay un error al establecer la conexión.
	 *         - 3 error de excepción.
	 */
	public int altaPasajero(Pasajero pasajero) {
		int altaPasajero = daoPasajero.altaPasajero(pasajero);
		return altaPasajero;
	}
	
	/**
	 * Método para eliminar un pasajero por su id.
	 * 
	 * @param id El id del pasajero a eliminar.
	 * @return Entero que indica el resultado de la operación:
	 *         - 0 no se ha borrado ningún pasajero.
	 *         - 1 se ha eliminado correctamente.
	 *         - 2 hay un error al establecer la conexión.
	 *         - 3 error de excepción.
	 */
	public int eliminarPasajero(int id) {
		int borrarPasajero = daoPasajero.eliminarPasajero(id);
		return borrarPasajero;
	}
	
	/**
	 * Método que devuelve un objeto pasajero a partir de su id.
	 * 
	 * @param id El id del pasajero a buscar.
	 * @return El objeto pasajero si existe en la base de datos, null en caso de que no exista o haya un problema en la conexión.
	 */
	public Pasajero buscarPasajero(int id) {
		Pasajero pasajeroBuscado = daoPasajero.buscarPasajero(id);
		return pasajeroBuscado;
	}
	
	/**
	 * Método que devuelve una lista de pasajeros.
	 * 
	 * @return La lista de pasajeros si existe en la base de datos, null en caso de que no exista o haya un problema en la conexión.
	 */
	public List<Pasajero> buscarTodosCoches() {
		List<Pasajero> listaAuxiliar = daoPasajero.buscarTodosPasajeros();
		return listaAuxiliar;
	}
	
	
}
