package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoPasajeroEnCoche {
	
	int addPasajeroCoche(int idCoche, int idPasajero);
	int eliminarPasajeroCoche(int idCoche, int idPasajero);
	List<Coche> pasajerosEnCoche(int idCoche);
	
	List<Coche> mostrarCochesDisponibles();
	List<Coche> mostrarCochesConPasajeros();
	
}
