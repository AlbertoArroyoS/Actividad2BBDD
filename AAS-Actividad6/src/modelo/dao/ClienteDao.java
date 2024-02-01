package modelo.dao;

import java.util.List;

import modelo.javabean.Cliente;
/**
 * Interface del Cliente
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public interface ClienteDao {
	
	//Metodos que implementan el interface
	
	int altaCliente(Cliente cliente);
	int eliminarCliente(String cif);
	int modificarCliente (Cliente cliente);
	Cliente buscarCliente(String cif);
	List<Cliente> buscarTodos();
	List<Cliente> buscarPorDomicilio(String domicilio);	
}
