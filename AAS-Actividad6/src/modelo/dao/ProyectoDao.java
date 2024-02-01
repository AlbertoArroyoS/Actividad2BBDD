package modelo.dao;

import java.util.List;


import modelo.javabean.Proyecto;
/**
 * Interface del Proyecto
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public interface ProyectoDao {
	
		//Metodos que implementan el interface

		int altaProyecto(Proyecto proyecto);
		int eliminarProyecto(String idProyecto);
		int modificarProyecto (Proyecto proyecto);
		Proyecto buscarProyecto(String idProyecto);
		List<Proyecto> buscarTodos();	
		List<Proyecto> proyectosByEstado(String estado); 
		List<Proyecto> proyectosByCliente(String cif); 
		List<Proyecto> proyectosByJefeProyectoAndByEstado(int jefeProyecto, String estado); 
		double importesVentaProyectosTerminados(); 
		double margenBrutoProyectosTerminados(); 
		int diasATerminoProyectoActivo(String codigoProyecto);
		int diasATerminoProyectoActivoMy8(String codigoProyecto);
		
		List<Proyecto> buscarProyectoPorContenidoId(String contenidoId);
}
