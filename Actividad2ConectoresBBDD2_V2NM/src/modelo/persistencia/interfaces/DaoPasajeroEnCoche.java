package modelo.persistencia.interfaces;

import java.util.List;
import java.util.Map;

import modelo.entidad.Coche;
/**
 * Esta interfaz proporciona métodos para gestionar la relación entre Coches y Pasajeros en una base de datos.
 * Implementar esta interfaz permite la interacción con la capa de persistencia para la relación entre Coches y Pasajeros.
 */
public interface DaoPasajeroEnCoche {
	
	int addPasajeroCoche(int idCoche, int idPasajero);
	int eliminarPasajeroCoche(int idCoche, int idPasajero);
	List<Coche> pasajerosEnCoche(int idCoche);
	Map<Coche, Integer> mostrarCochesConNumeroPasajeros();
	List<Coche> mostrarCochesDisponibles();
	List<Coche> mostrarCochesConPasajeros();
	
}
