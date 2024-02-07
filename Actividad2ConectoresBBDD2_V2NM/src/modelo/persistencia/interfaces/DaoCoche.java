package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
/**
 * Esta interfaz define métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con la entidad Coche en una base de datos.
 */
public interface DaoCoche {
	
	int altaCoche(Coche coche);
	int eliminarCoche(int id);
	Coche buscarCoche(int id);
	int modificarCoche (Coche coche);	
	List<Coche> buscarTodosCoches();

}
