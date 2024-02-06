package modelo.persistencia.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Usuario;
import modelo.persistencia.interfaces.DaoUsuario;

public class DaoUsuarioMySql implements DaoUsuario{
	
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String nombreBBDD = "GestionUsuariosLab2";
	private static String usuario = "root";
	private static String password = "";
	
	private Connection conexion;
	
	/**
	 * Método para dar de alta un usuario en la base de datos que recibe por parametro
	 * @param usuario que queremos introducir en el ficheor
	 * @return <b>true</b> en caso de que hayamos introducido el usuario en el fichoro
	 * , <b>false</b> en caso contrario.
	 */
	@Override
	public boolean altaUsuario(Usuario u) {
		if(!abrirConexion()){
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
	 * @return el objeto usuario si existe en la base de datos, <b>>null</b> en caso de
	 * que no exista o hayamos tenido un problema en la conexion 
	 */
	@Override
	public Usuario obtenerUsuario(String nombre) {
		if(!abrirConexion()){
			return null;
		}		
		Usuario usuario = null;
		
		String query = "select * from usuarios where NOMBRE = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, nombre);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				usuario = new Usuario();
				usuario.setNombre(rs.getString("nombre"));
				usuario.setPassword(rs.getString("password"));
				usuario.setEdad(rs.getInt("edad"));
				
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
		if(!abrirConexion()){
			return null;
		}			
		List<Usuario> listaPersonas = new ArrayList<>();
		
		String query = "select NOMBRE,PASSWORD,EDAD from usuarios";
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
	 * Crea la conexion para el almacenamiento de usuarios y llama al medoto para
	 * crear la base de datos en caso que que no exista y al metodo para crear
	 * la tabla con sus propiedades
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - 1 si la conexión se creó exitosamente.
	 *         - 2 si hubo un error al intentar hacer la conexion
	 */
	@Override
	public int crearAccesoADatos() {
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
			// Crear la base de datos
            crearBaseDeDatos(conexion);
            // Establecer conexión con la base de datos recién creada
            try (Connection conexionBD = DriverManager.getConnection(url + nombreBBDD, usuario, password)) {
                // Crear la tabla de usuarios
                crearTablaUsuarios(conexionBD);
                crearTablaPasajeros(conexionBD);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
		return 1;
	}
	
	/**
	 * Abre una conexión a la base de datos utilizando la URL, el nombre de la base de datos,
	 * el usuario y la contraseña proporcionados.
	 *
	 * @return true si la conexión se abre con éxito, false en caso de error.
	 * @throws SQLException Si ocurre un error al intentar establecer la conexión.
	 */
	public boolean abrirConexion(){
		try {
			conexion = DriverManager.getConnection(url+nombreBBDD,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
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
	
	// Método para crear la base de datos
    private static void crearBaseDeDatos(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la base de datos si no existe
        	statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + nombreBBDD);
        }
    }
    

	// Método para crear la tabla de usuarios
    private static void crearTablaUsuarios(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la tabla de usuarios si no existe
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Usuarios ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "password VARCHAR(50) NOT NULL,"
                    + "edad INT NOT NULL)"
            );
        }
    }
    
 // Método para crear la tabla de pasajeros
    private static void crearTablaPasajeros(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la tabla de usuarios si no existe
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Pasajeros (" +
                    "id_pasajero INT PRIMARY KEY AUTO_INCREMENT," +
                    "nombre VARCHAR(255) NOT NULL," +
                    "edad INT NOT NULL," +
                    "peso DECIMAL(5,2) NOT NULL" +
                    ")"
            );
        }
    }

}
