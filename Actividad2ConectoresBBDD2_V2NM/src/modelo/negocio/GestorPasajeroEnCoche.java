package modelo.negocio;
import java.util.List;
import java.util.Map;

import modelo.entidad.Coche;
import modelo.persistencia.acceso.DaoPasajeroEnCocheMySql;
import modelo.persistencia.interfaces.DaoPasajeroEnCoche;
/**
 * Clase que gestiona la interacción entre pasajeros y coches en el sistema.
 * 
 * <p>Proporciona métodos para mostrar coches disponibles, mostrar coches con pasajeros asociados,
 * asociar y eliminar pasajeros de coches, obtener información sobre pasajeros en un coche específico,
 * y mostrar coches con el número de pasajeros asociados.
 * 
 * <p>Requiere un objeto DaoPasajeroEnCoche para interactuar con la persistencia.
 * 
 * @author Alberto Arroyo Santofimia
 * @version 2.0
 * @since 2024-02-08
 */
public class GestorPasajeroEnCoche {
	
////REQUERIMIENTO 2
	private DaoPasajeroEnCoche daoPC = new DaoPasajeroEnCocheMySql();
	
	/**
     * Obtiene una lista de coches disponibles que no tienen un pasajero asociado en la base de datos.
     *
     * @return Una lista de objetos Coche que representan los coches disponibles.
     *         Si no se puede abrir la conexión, devuelve null.
     */
    public List<Coche> mostrarCochesDisponibles() {
    	List<Coche> listaAuxiliar = daoPC.mostrarCochesDisponibles();
		return listaAuxiliar;
    }
  		
    /**
  	 * Muestra todos los coches y sus pasajeros asociados en la base de datos.  	 *
  	 * @return Una lista de objetos Coche con información sobre los coches y sus pasajeros asociados.
  	 *         Si no se puede abrir la conexión, devuelve null.
  	 */
    public List<Coche> mostrarCochesConPasajeros() {
    	List<Coche> listaAuxiliar = daoPC.mostrarCochesConPasajeros();
		return listaAuxiliar;
    }
    /**
	 * Metodo para asociar un pasajero a un coche en la base de datos.
	 *
	 * @param idCoche    El ID del coche al cual se asociará el pasajero.
	 * @param idPasajero El ID del pasajero que se asociará al coche.
	 * @return Un valor entero que representa el resultado de la operación:
	 *         - 1: Éxito, el pasajero se asoció al coche correctamente.
	 *         - 2: No se pudo abrir la conexión a la base de datos.
	 *         - 3: Ocurrió una excepción SQLException durante la ejecución.
	 */
	public int addPasajeroCoche(int idCoche, int idPasajero) {
		int addPC=daoPC.addPasajeroCoche(idCoche, idPasajero);
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
		int borrarPC=daoPC.eliminarPasajeroCoche(idCoche, idPasajero);
		return borrarPC;
	}
	
	/**
     * Metodo para obtiener una lista de coches con información sobre los pasajeros asociados a un coche específico.
     *
     * @param idCoche El ID del coche del cual se desea obtener la información de pasajeros.
     * @return Una lista de objetos Coche con información sobre los pasajeros asociados al coche.
     *         Si no se puede abrir la conexión, devuelve null.
     */
	public List<Coche> pasajerosEnCoche(int idCoche) {
		List<Coche> listaAuxiliar = daoPC.pasajerosEnCoche(idCoche);
		return listaAuxiliar;
    }
	
	/**
	 * Muestra todos los coches disponibles y el número de pasajeros asociados en la base de datos.
	 *
	 * @return Un Map donde las claves son objetos Coche representando coches disponibles,
	 *         y los valores son enteros representando el número de pasajeros asociados a cada coche.
	 *         Si no se puede abrir la conexión, devuelve null.
	 */
		public Map<Coche, Integer> mostrarCochesConNumeroPasajeros() {
			Map<Coche, Integer> mapaAuxiliar = daoPC.mostrarCochesConNumeroPasajeros();
			return mapaAuxiliar;
		}

}
