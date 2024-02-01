package testing;

import java.sql.Connection;

import conexion.ConexionMy8;
/**
 * Clase en la que realizamos el test de la conexion a MySQL8
 * 
 * 1. Probamos que realiza una conexion singleton, es decir, que no realiza multiples conexiones para cada operacion.
 * 2. Comprobamos si las tres conexiones son iguales.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestConexion {

	public static void main(String[] args) {
			
		
		//Test singleton
		
		Connection conn1 = ConexionMy8.getConexion();
		Connection conn2 = ConexionMy8.getConexion();
		Connection conn3 = ConexionMy8.getConexion();
		
		System.out.println(conn1);
		System.out.println(conn2);
		System.out.println(conn3);
		
		//Comprobamos si las tres conexiones son iguales
		
		if(conn1 == conn2 && conn2 == conn3)
			System.out.println("LA CONEXION ES LA MISMA");

	}

}
