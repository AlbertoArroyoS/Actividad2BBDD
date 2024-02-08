package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
/**
 * Esta interfaz proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en objetos de tipo Coche en una base de datos.
 * Implementar esta interfaz permite la interacción con la capa de persistencia para la entidad Coche.
 */
public interface DaoCoche {
	
	int altaCoche(Coche coche);
	int eliminarCoche(int id);
	Coche buscarCoche(int id);
	int modificarCoche (Coche coche);	
	List<Coche> buscarTodosCoches();

}
