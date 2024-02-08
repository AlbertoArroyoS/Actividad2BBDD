package modelo.persistencia.interfaces;

import java.util.List;
import java.util.Map;

import modelo.entidad.Coche;

public interface DaoPasajeroEnCoche {
	
	int addPasajeroCoche(int idCoche, int idPasajero);
	int eliminarPasajeroCoche(int idCoche, int idPasajero);
	List<Coche> pasajerosEnCoche(int idCoche);
	Map<Coche, Integer> mostrarCochesConNumeroPasajeros();
	List<Coche> mostrarCochesDisponibles();
	List<Coche> mostrarCochesConPasajeros();
	
}
