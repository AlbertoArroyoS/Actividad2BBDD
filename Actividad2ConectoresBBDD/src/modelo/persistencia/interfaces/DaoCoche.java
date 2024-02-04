package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DaoCoche {
	
	int altaCoche(Coche coche);
	int eliminarCoche(int id);
	Coche buscarCoche(int id);
	int modificarCoche (Coche coche);	
	List<Coche> buscarTodosCoches();

}
