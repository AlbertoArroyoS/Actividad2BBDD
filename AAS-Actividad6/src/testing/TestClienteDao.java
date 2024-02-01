package testing;

import modelo.dao.ClienteDao;
import modelo.dao.ClienteDaoImplMy8;
import modelo.javabean.Cliente;
/**
 * Clase en la que realizamos los test del ClienteDao.
 * 
 * Probamos:
 * 
 * 1. Buscar cliente por cif.
 * 2. Buscar todos los clientes.
 * 3. Dar de alta cliente.
 * 4. Borrar cliente.
 * 6. Borrar el ultimo cliente creado.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestClienteDao {
	
	private static final String SEPARACION = "-------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
		
		ClienteDao cdao = new ClienteDaoImplMy8();
			
		//1. Buscar cliente por cif
		
		System.out.println("\n"+SEPARACION);
		System.out.println("1.1. BUSCAR CLIENTE A22222222\n");
		Cliente cli = cdao.buscarCliente("A22222222");
		if (cli != null)
			System.out.println(cli);
		else
			System.out.println("Cliente no encontrado");
		System.out.println("\n"+SEPARACION);
		System.out.println("1.2. BUSCAR CLIENTE Z88888888\n");
		cli = cdao.buscarCliente("Z88888888");
		if (cli != null)
			System.out.println(cli);
		else
			System.out.println("Cliente no encontrado");
		
		System.out.println(SEPARACION);
		
		//2. Buscar todos los clientes
		
		System.out.println("2. LISTAR TODOS\n");
		for(Cliente ele: cdao.buscarTodos())
			System.out.println(ele);
		
		System.out.println(SEPARACION);
			
		
		//3. Dar de alta cliente
		System.out.println("3.1. DAR DE ALTA CLIENTE 1\n");
		Cliente cli1 = new Cliente("C44444444", "Pedro", "Fernandez Gonzalez", "Ciudad Real", 1750000, 500);
		if (cdao.altaCliente(cli1) == 1) {
		System.out.println("AÑADIDO CORRECTAMENTE\n");
		for(Cliente ele: cdao.buscarTodos())
			System.out.println(ele);	
		}
		else
			System.out.println("NO SE HA AÑADIDO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		System.out.println("3.2. DAR DE ALTA CLIENTE 2\n");
		Cliente cli2 = new Cliente("D55555555", "Erika", "Ruiz Perez", "Albacete", 1250000, 700);
		if (cdao.altaCliente(cli2) == 1) {
		System.out.println("AÑADIDO CORRECTAMENTE\n");
		for(Cliente ele: cdao.buscarTodos())
			System.out.println(ele);	
		}
		else
			System.out.println("NO SE HA AÑADIDO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		//4. Borrar cliente
		
		System.out.println("4. ELIMINAR CLIENTE CON CIF C44444444\n");
		if (cdao.eliminarCliente("C44444444") == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
			for(Cliente ele: cdao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
			
		//5. Modificar
		System.out.println("5. MODIFICAR CLIENTE, DOMICILIO NUEVO EN PUERTOLLANO\n");	
		Cliente cli3 = cdao.buscarCliente("D55555555");
		cli3.setDomicilio("Puertollano");
		if (cdao.modificarCliente(cli3) == 1) {
			System.out.println("MODIFICADO CORRECTAMENTE\n");
			for(Cliente ele: cdao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA MODIFICADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		//6. Borrar el ultimo cliente creado
		
		System.out.println("6. ELIMINAR CLIENTE CON CIF D55555555\n");
		if (cdao.eliminarCliente("D55555555") == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
			for(Cliente ele: cdao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
	}
}
