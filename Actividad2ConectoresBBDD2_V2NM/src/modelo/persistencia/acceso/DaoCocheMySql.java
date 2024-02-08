package modelo.persistencia.acceso;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Coche;
import modelo.persistencia.datos.ConfiguracionPropiedades;
import modelo.persistencia.interfaces.DaoCoche;

/**
 * Clase que implementa el interfaz DaoCoche y proporciona métodos para interactuar con la persistencia
 * utilizando una base de datos MySQL.
 * 
 * <p>Proporciona métodos para realizar operaciones CRUD en la entidad Coche, así como métodos adicionales
 * para abrir y cerrar conexiones y crear la base de datos y tablas si es necesario.
 * 
 * @see DaoCoche
 * @author Alberto Arroyo Santofimia
 * @version v2.0
 * @since 2024-02-08
 */
public class DaoCocheMySql implements DaoCoche{
	
	
	
	//variables
	private int filas = 0;
	private String sql;
	private Connection conexion;
	private ResultSet rs;
	
	
	/**
	 * Método para dar de alta un coche en la base de datos que se recibe por parámetro.
	 * 
	 * @param coche El coche que se agregará a la base de datos.
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningún coche.
	 *         - <b>1</b> se ha añadido correctamente.
	 *         - <b>2</b> hay un error al establecer la conexión.
	 *         - <b>3</b> error de excepción.
	 */
	@Override
	public int altaCoche(Coche coche) {
		if(!abrirConexion()){
			filas = 2;
			return filas;
		}

		sql = "insert into coches (MARCA,MODELO,AÑO_FABRICACION,KILOMETROS) "
				+ " values(?,?,?,?)";
		filas = 0;
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			//preparamos la query con valores parametrizables(?)
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getFabYear());
			ps.setInt(4, coche.getKilometros());
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
	 * Método para eliminar un coche por su id.
	 * 
	 * @param id El id del coche a eliminar.
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha borrado ningún coche.
	 *         - <b>1</b> se ha eliminado correctamente.
	 *         - <b>2</b> hay un error al establecer la conexión.
	 *         - <b>3</b> error de excepción.
	 */
	@Override
	public int eliminarCoche(int id) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}		
		sql = "delete from COCHES where ID = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			filas = ps.executeUpdate();
			//filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}		
		return filas;
	}
	/**
	 * Método que devuelve un objeto coche a partir de su id.
	 * 
	 * @param id El id del coche a buscar.
	 * @return El objeto coche si existe en la base de datos, null en caso de que no exista o haya un problema en la conexión.
	 */
	@Override
	public Coche buscarCoche(int id) {
		if(!abrirConexion()){
			return null;
		}		
		sql = "select * from COCHES where ID = ?";
		Coche coche = null;
		
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			//Como solo hay 1 ?
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt("ID"));
				coche.setMarca(rs.getString("MARCA"));
				coche.setModelo(rs.getString("MODELO"));
				coche.setFabYear(rs.getInt("AÑO_FABRICACION"));
				coche.setKilometros(rs.getInt("KILOMETROS"));
			}
		} catch (SQLException e) {
			return null;
		}
		return coche;
	}
	/**
	 * Método para modificar un coche en la base de datos.
	 * 
	 * @param coche El coche modificado.
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha modificado ningún coche.
	 *         - <b>1</b> se ha modificado correctamente.
	 *         - <b>2</b> hay un error al establecer la conexión.
	 *         - <b>3</b> error de excepción.
	 */
	@Override
	public int modificarCoche(Coche coche) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}
		sql = "update COCHES set MARCA = ?, MODELO = ?, AÑO_FABRICACION = ?, KILOMETROS = ?"
				+ " where ID = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {	
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getFabYear());
			ps.setInt(4, coche.getKilometros());;
			ps.setInt(5, coche.getId());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}
		
		return filas;
	}
	/**
	 * Método que devuelve una lista de coches.
	 * 
	 * @return La lista de coches si existe en la base de datos, null en caso de que no exista o haya un problema en la conexión.
	 */
	@Override
	public List<Coche> buscarTodosCoches() {
		if(!abrirConexion()){
			return null;
		}	
		sql = "select * from COCHES";
		List<Coche> lista = new ArrayList<>();
		Coche coche = null;
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			rs = ps.executeQuery();
			while (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt("ID"));
				coche.setMarca(rs.getString("MARCA"));
				coche.setModelo(rs.getString("MODELO"));
				coche.setFabYear(rs.getInt("AÑO_FABRICACION"));
				coche.setKilometros(rs.getInt("KILOMETROS"));
				lista.add(coche);
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
	 * @return <b>true</b> si la conexión se abre con éxito, <b>false</b> en caso de error.
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


