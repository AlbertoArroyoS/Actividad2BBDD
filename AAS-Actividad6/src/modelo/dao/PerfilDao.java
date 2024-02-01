package modelo.dao;

import java.util.List;

import modelo.javabean.Perfil;
/**
 * Interface del Perfil
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public interface PerfilDao {

	//Metodos que implementan el interface
	
	int altaPerfil(Perfil perfil);
	int eliminarPerfil(int idPerfil);
	int modificarPerfil(Perfil perfil);
	Perfil buscarPerfil(int idPerfil);
	List<Perfil> buscarTodos();
	List<Perfil> buscarPorNombre(String nombre);
}
