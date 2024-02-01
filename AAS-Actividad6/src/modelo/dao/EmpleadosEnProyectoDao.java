package modelo.dao;

import java.util.List;

import modelo.javabean.EmpleadosEnProyecto;
/**
 * Interface del EmpleadosEnProyecto
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public interface EmpleadosEnProyectoDao {
	
	//Metodos que implementan el interface
	
	int altaEmpleadoEnProyecto(EmpleadosEnProyecto empleadosEnProyecto);
	int eliminarEmpleadoEnProyecto(int numeroOrden);
	int modificarEmpleadoEnProyecto (EmpleadosEnProyecto empleadosEnProyecto, int numeroOrden );
	EmpleadosEnProyecto buscarEmpleadoOrden(int numeroOrden);
	List<EmpleadosEnProyecto> buscarTodos();
	List<EmpleadosEnProyecto> empleadosByProyecto(String codigoProyecto);
	int asignarEmpleadosAProyecto (List<EmpleadosEnProyecto> empleadosEnProyecto);
	int horasAsignadasAProyecto(String codigoProyecto);
	double costeActualDeProyecto(String codigoProyecto);
	double margenActualProyecto(String codigoProyecto);
}
