package modelo.negocio;

import java.util.List;
import modelo.entidad.Coche;
import modelo.persistencia.acceso.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;
/**
 * Clase que gestiona las operaciones relacionadas con los coches en la persistencia.
 * 
 * <p>Proporciona métodos para agregar, eliminar, buscar y modificar coches en la base de datos.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 1.0
 * @since 2024-02-08
 */
public class GestorCoche {
	
	private DaoCoche daoCoche = new DaoCocheMySql();
	
	
	/**
	 * Método para agregar un coche a la persistencia.
	 * 
	 * @param coche El coche a agregar.
	 * @return Entero que indica el resultado de la operación:
	 *         - 0 no se ha dado de alta ningún coche
	 *         - 1 se ha añadido correctamente
	 *         - 2 hay un error al establecer la conexión
	 *         - 3 error de excepción
	 */
	public int altaCoche(Coche coche) {
		int altaCoche = daoCoche.altaCoche(coche);
		return altaCoche;
	}
	
	/**
	 * Método para eliminar un coche por su id.
	 * 
	 * @param id El id del coche a eliminar.
	 * @return Entero que indica el resultado de la operación:
	 *         - 0 no se ha borrado ningún coche
	 *         - 1 se ha eliminado correctamente
	 *         - 2 hay un error al establecer la conexión
	 *         - 3 error de excepción
	 */
	public int eliminarCoche(int id) {
		int borrarCoche = daoCoche.eliminarCoche(id);
		return borrarCoche;
	}
	/**
	 * Método que devuelve un objeto coche a partir de su id.
	 * 
	 * @param id El id del coche a buscar.
	 * @return El objeto coche si existe en la base de datos, null en caso de que no exista o haya un problema en la conexión.
	 */
	public Coche buscarCoche(int id) {
		Coche cocheBuscado = daoCoche.buscarCoche(id);
		return cocheBuscado;
	}
	/**
	 * Método para modificar un coche en la base de datos.
	 * 
	 * @param coche El coche modificado.
	 * @return Entero que indica el resultado de la operación:
	 *         - 0 no se ha modificado ningún coche
	 *         - 1 se ha modificado correctamente
	 *         - 2 hay un error al establecer la conexión
	 *         - 3 error de excepción
	 */
	public int modificarCoche(Coche coche) {
		int modificarCoche = daoCoche.modificarCoche(coche);
		return modificarCoche;
	}
	
	/**
	 * Método que devuelve una lista de coches.
	 * 
	 * @return La lista de coches si existe en la base de datos, null en caso de que no exista o haya un problema en la conexión.
	 */
	public List<Coche> buscarTodosCoches() {
		List<Coche> listaAuxiliar = daoCoche.buscarTodosCoches();
		return listaAuxiliar;
	}
	
	
}
