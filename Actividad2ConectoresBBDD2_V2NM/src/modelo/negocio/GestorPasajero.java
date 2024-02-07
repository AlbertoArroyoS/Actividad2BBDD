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
	
	
}
