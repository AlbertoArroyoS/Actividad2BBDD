package modelo.dao;

import java.util.List;

import modelo.javabean.Departamento;
/**
 * Interface del Departamento
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public interface DepartamentoDao {
	
	//Metodos que implementan el interface
	
	int altaDepartamento(Departamento departamento);
	int eliminarDepartamento(int idDepar);
	int modificarDepartamento(Departamento departamento);
	Departamento buscarDepartamento(int idDepar);
	List<Departamento> buscarTodos();
	List<Departamento> buscarPorNombre(String nombre);
}
