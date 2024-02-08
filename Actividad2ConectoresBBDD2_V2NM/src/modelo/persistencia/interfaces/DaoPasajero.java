package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Pasajero;
/**
 * Esta interfaz proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en objetos de tipo Pasajero en una base de datos.
 * Implementar esta interfaz permite la interacción con la capa de persistencia para la entidad Pasajero.
 */
public interface DaoPasajero {
	
	int altaPasajero(Pasajero pasajero);
	int eliminarPasajero(int idPasajero);
	Pasajero buscarPasajero(int idPasajero);
	List<Pasajero> buscarTodosPasajeros();
		
}
