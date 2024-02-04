package modelo.persistencia.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Usuario;
import modelo.persistencia.interfaces.DaoUsuario;

public class DaoUsuarioMySql implements DaoUsuario{
	
	private Connection conexion;
	
	/**
	 * Método que introduce un objeto usuario en un fichero en formato USUARIO/PASSWORD
	 * @param usuario que queremos introducir en el ficheor
	 * @return <b>true</b> en caso de que hayamos introducido el usuario en el fichoro
	 * , <b>false</b> en caso contrario.
	 */
	@Override
	public boolean altaUsuario(Usuario u) {
		if(crearConexion()==2){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into usuarios (NOMBRE,PASSWORD,EDAD) "
				+ " values(?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getEdad());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			alta = false;
			
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}
	/**
	 * Método que devuelve un objeto usuario a partir de un nombre
	 * @param nombre representa el nombre del usuario del que vamos a
	 * hacer la busqueda.
	 * @return el objeto usuario si existe en el fichero, <b>>null</b> en caso de
	 * que no exista o hayamos tenido un problema en la conexion 
	 */
	@Override
	public Usuario obtenerUsuario(String nombre) {
		if(crearConexion()==2){
			return null;
		}		
		Usuario usuario = null;
		
		String query = "select PASSWORD,EDAD from usuarios "
				+ "where NOMBRE = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, nombre);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				usuario = new Usuario();
				usuario.setNombre(rs.getString(1));
				usuario.setPassword(rs.getString(2));
				usuario.setEdad(rs.getInt(3));
				
			}
		} catch (SQLException e) {
			return null;
		} finally {
			cerrarConexion();
		}
		
		
		return usuario;
	}
	/**
	 * Método que devuelve una lista con los usuarios que ya están registrados
	 * @return <b>List<Usuario> </b> la lista de los usuarios
	 */
	@Override
	public List<Usuario> listarTodosUsuarios() {
		if(crearConexion()==2){
			return null;
		}			
		List<Usuario> listaPersonas = new ArrayList<>();
		
		String query = "select ID,NOMBRE,EDAD,PESO from personas";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				usuario.setNombre(rs.getString(1));
				usuario.setPassword(rs.getString(2));
				usuario.setEdad(rs.getInt(3));
				
				listaPersonas.add(usuario);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			cerrarConexion();
		}
		
		
		return listaPersonas;
	}

	/**
	 * Crea la conexion para el almacenamiento de usuarios.
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - 1 si la conexión se creó exitosamente.
	 *         - 2 si hubo un error al intentar hacer la conexion
	 */
	@Override
	public int crearConexion() {
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
		return 1;
	}
	
	/**
	 * Cierra la conexión con la base de datos.
	 * 
	 * @return true si la conexión se cerró correctamente, false si ocurrió un error.
	 */
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
