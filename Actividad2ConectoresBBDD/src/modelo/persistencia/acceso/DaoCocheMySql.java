package modelo.persistencia.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 * 1. altaCliente(Cliente cliente) : int
 * 2. buscarCliente(String cif) : Cliente
 * 3. buscarTodos() : List<Cliente>
 * 4. eliminarCliente(String cif) : int
 * 5. modificarCliente(Cliente cliente) : int
 * 6. buscarPorDomicilio(String domicilio) : List<Cliente>
 * 7. crearObjetoCliente(Cliente cl) : void 
 * 
 * @see DaoCoche
 *  
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class DaoCocheMySql implements DaoCoche{
	
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String nombreBBDD = "GestionUsuariosLab2";
	private static String coche = "root";
	private static String password = "";
	
	private int filas = 0;
	private String sql;
	private Connection conexion;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * Método para dar de alta un coche en la Base de datos que recibimos por parametro
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	@Override
	public int altaCoche(Coche coche) {
		if(!abrirConexion()){
			filas = 2;
			return filas;
		}
		sql = "insert into coches values(?,?,?,?,?,?)";
		filas = 0;
		try {
			//preparamos la query con valores parametrizables(?)
			ps = conexion.prepareStatement(sql);
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setInt(3, coche.getFabYear());
			ps.setInt(4, coche.getKilometros());
			filas = ps.executeUpdate();
			//filas = 1;
			if(filas == 0) {
				return filas;
			}
			//filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}
		finally{
			cerrarConexion();
		}
		
		return filas;
	}
	/**
	 * Método para eliminar un coche por su id
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	@Override
	public int eliminarCoche(int id) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}		
		sql = "delete from coches where ID = ?";
		try {
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}
		finally{
			cerrarConexion();
		}
		
		return filas;
	}
	/**
	 * Método que devuelve un objeto coche a partir de su id
	 * @param id representa el id del coche que vamos a
	 * hacer la busqueda.
	 * @return - <b>el objeto coche</b> si existe en la base de datos
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
	 */
	@Override
	public Coche buscarCoche(int id) {
		if(!abrirConexion()){
			return null;
		}		
		sql = "select * from clientes where ID = ?";
		Coche coche = null;
		
		try {
			ps = conexion.prepareStatement(sql);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coche;
	}

	@Override
	public int modificarCoche(Coche coche) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Coche> buscarTodosCoches() {
		sql = "select * from clientes";
		List<Cliente> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cl = new Cliente();;
				crearObjetoCliente(cl);
				lista.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int accesoADatos() {
		// TODO Auto-generated method stub
		return 0;
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
			conexion = DriverManager.getConnection(url,coche,password);
			// Crear la base de datos
            crearBaseDeDatos(conexion);
            // Establecer conexión con la base de datos recién creada
            try (Connection conexionBD = DriverManager.getConnection(url + nombreBBDD, coche, password)) {
                // Crear la tabla de usuarios
                crearTablaUsuarios(conexionBD);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
		return 1;
	}
	
	public boolean abrirConexion(){
		try {
			conexion = DriverManager.getConnection(url+nombreBBDD,coche,password);
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
                   // + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "password VARCHAR(50) NOT NULL,"
                    + "edad INT NOT NULL)"
            );
        }
    }


}
