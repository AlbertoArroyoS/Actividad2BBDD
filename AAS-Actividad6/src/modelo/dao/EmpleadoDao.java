package modelo.dao;

import java.util.List;

import modelo.javabean.Empleado;

/**
 * Interface del Empleado
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public interface EmpleadoDao {
	
	//Metodos que implementan el interface
	
	int altaEmpleado(Empleado empleado);
	int eliminarEmpleado(int idEmpl);
	int modificarEmpleado (Empleado empleado);
	Empleado buscarEmpleado(int idEmpl);
	List<Empleado> buscarTodos();
	List<Empleado> empleadosByDepartamento(int idDepar);
	List<Empleado> empleadosBySexo(char sexo);
	List<Empleado> empleadosByApellido(String subcadena);
	double salarioTotal();
	double salarioTotal(int idDepar);
}
