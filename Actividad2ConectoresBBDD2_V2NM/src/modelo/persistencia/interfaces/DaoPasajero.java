package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public interface DaoPasajero {
	
	int altaPasajero(Pasajero pasajero);
	int eliminarPasajero(int idPasajero);
	Pasajero buscarPasajero(int idPasajero);
	List<Pasajero> buscarTodosPasajeros();
	
	
}
