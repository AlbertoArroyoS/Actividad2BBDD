package modelo.persistencia.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{
	
	//variables
		private int filas = 0;
		private String sql;
		private Connection conexion;
		private PreparedStatement ps;
		private ResultSet rs;


	//1
	/**
	 * Método para dar de alta un pasajero en la Base de datos que recibimos por parametro
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	@Override
	public int altaPasajero(Pasajero pasajero) {
		if(!abrirConexion()){
			filas = 2;
			return filas;
		}

		sql = "insert into pasajeros (NOMBRE,EDAD,PESO,ID_COCHE) "
				+ " values(?,?,?,?)";
		filas = 0;
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			//preparamos la query con valores parametrizables(?)
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			ps.setInt(4, pasajero.getCoche().getId());
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
	
	//2
	/**
	 * Método para eliminar un pasajero por su id
	 * 
	 * @param id representa el id del pasajero que vamos a
	 * hacer la busqueda.
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha borrado ningun coche
	 *         - <b>1</b> se ha eliminado correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
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
	//3
	/**
	 * Método que devuelve un objeto pasajero a partir de su id
	 * @param id representa el id del pasajero que vamos a
	 * hacer la busqueda.
	 * @return - <b>el objeto pasajero</b> si existe en la base de datos
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
	 */
	@Override
	public Pasajero buscarPasajero(int idPasajero) {
		if(!abrirConexion()){
			return null;
		}		
		sql = "select * from COCHES where ID = ?";
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
				pasajero.getCoche().setId(rs.getInt("ID_COCHE"));
			}
		} catch (SQLException e) {
			return null;
		}
		return pasajero;
	}
	/**
	 * Método que devuelve una lista de pasajeros
	 * @return - <b>List<Pasajero>
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
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
				pasajero.getCoche().setId(rs.getInt("ID_COCHE"));
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			return null;			
		}
		return lista;
	}

	@Override
	public int addPasajeroCoche(Pasajero pasajero) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}
		sql = "update PASAJEROS set ID_COCHE = ?"
				+ " where ID = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {	
			ps.setInt(1, pasajero.getCoche().getId());
			ps.setInt(2, pasajero.getId_pasajero());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}
		
		return filas;
	}

	@Override
	public int eliminarPasajeroCoche(Pasajero pasajero) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Pasajero> pasajerosEnCoche(int idCoche) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Abre una conexión a la base de datos utilizando la URL, el nombre de la base de datos,
	 * el usuario y la contraseña proporcionados.
	 *
	 * @return - <b>true</b> si la conexión se abre con éxito
	 *         - <b>false</b> false en caso de error.
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
