package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Clase con los datos para realizar la conexion singleton
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class ConexionMy8 {
	
	private static String url,user,pwd;
	private static Connection conn;
	static {
		url = "jdbc:mysql://localhost:3306/clientes_proyectos_empleados_2023?serverTimezone=UTC";
		user = "root";
		pwd = null;
	}
	
	private ConexionMy8() {
				
		try {
			conn=DriverManager.getConnection(url, user, pwd);
			System.out.println("CONEXION CORRECTA");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("****NO CONECTADO*****");
		}
	}
		
	public static Connection getConexion() {
		if (conn==null) {
			new ConexionMy8();
		}
		return conn;

	}
}