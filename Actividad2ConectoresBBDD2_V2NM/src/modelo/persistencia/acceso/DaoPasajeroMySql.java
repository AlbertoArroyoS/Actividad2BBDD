package modelo.persistencia.acceso;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Pasajero;
import modelo.persistencia.datos.ConfiguracionPropiedades;
import modelo.persistencia.interfaces.DaoPasajero;
/**
 * Clase que implementa el interfaz DaoPasajero y proporciona métodos para interactuar con la persistencia
 * utilizando una base de datos MySQL.
 * 
 * <p>Proporciona métodos para realizar operaciones CRUD en la entidad Pasajero, así como métodos adicionales
 * para abrir y cerrar conexiones y buscar información específica sobre pasajeros.
 * 
 * @see DaoPasajero
 * @author Alberto Arroyo Santofimia
 * @version v2.0
 * @since 2024-02-08
 */
public class DaoPasajeroMySql implements DaoPasajero{
	
	//variables
		private int filas = 0;
		private String sql;
		private Connection conexion;
		private ResultSet rs;


	/**
	 * Método para dar de alta un pasajero en la Base de datos que recibimos por parámetro.
	 * 
	 * @param pasajero Objeto Pasajero que se dará de alta en la base de datos.
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningún pasajero.
	 *         - <b>1</b> si se ha añadido correctamente.
	 *         - <b>2</b> si hay un error al establecer la conexión.
	 *         - <b>3</b> error de Excepción.
	 */
	@Override
	public int altaPasajero(Pasajero pasajero) {
		if(!abrirConexion()){
			filas = 2;
			return filas;
		}

		sql = "insert into pasajeros (NOMBRE,EDAD,PESO) "
				+ " values(?,?,?)";
		filas = 0;
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			//preparamos la query con valores parametrizables(?)
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			filas = ps.executeUpdate();
			filas = 1;
			if(filas == 0) {
				return filas;
			}
			//filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}
		return filas;
	}
	
	/**
	 * Método para eliminar un pasajero por su id.
	 * 
	 * @param idPasajero Representa el id del pasajero que vamos a borrar.
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha borrado ningún pasajero.
	 *         - <b>1</b> se ha eliminado correctamente.
	 *         - <b>2</b> si hay un error al establecer la conexión.
	 *         - <b>3</b> error de Excepción.
	 */
	@Override
	public int eliminarPasajero(int idPasajero) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}		
		sql = "delete from PASAJEROS where ID = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, idPasajero);
			filas = ps.executeUpdate();
			//filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}		
		return filas;
	}
	/**
	 * Método que devuelve un objeto Pasajero a partir de su id.
	 * 
	 * @param idPasajero Representa el id del pasajero que vamos a buscar.
	 * @return - <b>El objeto Pasajero</b> si existe en la base de datos.
	 *         - <b>null</b> en caso de que no exista o haya habido un problema en la conexión.
	 */
	@Override
	public Pasajero buscarPasajero(int idPasajero) {
		if(!abrirConexion()){
			return null;
		}		
		sql = "select * from PASAJEROS where ID = ?";
		Pasajero pasajero = null;
		
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			//Como solo hay 1 ?
			ps.setInt(1, idPasajero);
			rs = ps.executeQuery();
			if (rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId_pasajero(rs.getInt("ID"));
				pasajero.setNombre(rs.getString("NOMBRE"));
				pasajero.setEdad(rs.getInt("EDAD"));
				pasajero.setPeso(rs.getDouble("Peso"));
			}
		} catch (SQLException e) {
			return null;
		}
		return pasajero;
	}
	/**
	 * Método que devuelve una lista de todos los pasajeros.
	 * 
	 * @return - <b>List<Pasajero></b> si existen pasajeros en la base de datos.
	 *         - <b>null</b> en caso de que no exista o haya habido un problema en la conexión.
	 */
	@Override
	public List<Pasajero> buscarTodosPasajeros() {
		if(!abrirConexion()){
			return null;
		}	
		sql = "select * from PASAJEROS";
		List<Pasajero> lista = new ArrayList<>();
		Pasajero pasajero = null;
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			rs = ps.executeQuery();
			while (rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId_pasajero(rs.getInt("ID"));
				pasajero.setNombre(rs.getString("NOMBRE"));
				pasajero.setEdad(rs.getInt("EDAD"));
				pasajero.setPeso(rs.getDouble("Peso"));
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			return null;			
		}
		return lista;
	}
	
	
	
	/**
	 * Abre una conexión a la base de datos utilizando la URL, el nombre de la base de datos,
	 * el usuario y la contraseña proporcionados.
	 *
	 * @return - <b>true</b> si la conexión se abre con éxito.
	 *         - <b>false</b> en caso de error.
	 */
	public boolean abrirConexion(){
		ConfiguracionPropiedades conf = new ConfiguracionPropiedades();
		String url=conf.getProperty("url").toString();
		String usuario = conf.getProperty("usuario").toString();
		String password = conf.getProperty("password").toString();
		String nombreBBDD =conf.getProperty("nombreBBDD").toString();
		try {
			conexion = DriverManager.getConnection(url+nombreBBDD,usuario,password);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	

}
