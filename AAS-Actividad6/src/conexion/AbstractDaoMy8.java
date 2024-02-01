package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Clase abstracta con la atributos protected, que son comunes en los Dao,
 *  para heredarlos y no tener que declararlos en cada una y metodo para hacer la conexion
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public abstract class AbstractDaoMy8 {
	
	//Atributos 
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected String sql;
	protected int filas;
	
	//Metodo para hacer la conexion
	public AbstractDaoMy8() {
		conn = ConexionMy8.getConexion();
	}

}
