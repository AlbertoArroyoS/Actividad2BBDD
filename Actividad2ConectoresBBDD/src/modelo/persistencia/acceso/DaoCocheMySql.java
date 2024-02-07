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
 * Clase en la que estan los metodos que implementan el interface y los métodos propios:
 * 
 *  1. altaCoche(Coche coche) : int
 *  2. eliminarCoche(int id) : int
 *  3. buscarCoche(int id) : Coche
 *  4. modificarCoche(Coche coche) : int
 *  5. buscarTodosCoches() : List<Coche>
 *  6. accesoADatos() : int 
 * Métodos própios
 *  7. abrirConexion() : boolean 
 *  8. cerrarConexion() : boolean 
 *  9. crearBaseDeDatos(Connection conexion) : void 
 * 10. crearTablaUsuarios(Connection conexion) :void  
 * 
 * @see DaoCoche
 *  
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class DaoCocheMySql implements DaoCoche{
	
	
	
	//variables
	private int filas = 0;
	private String sql;
	private Connection conexion;
	private ResultSet rs;
	
	
	//1
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
	//2
	/**
	 * Método para eliminar un coche por su id
	 * 
	 * @param id representa el id del pasajero que vamos a eliminar
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha borrado ningun coche
	 *         - <b>1</b> se ha eliminado correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
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
	//3
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
	//4
	/**
	 * Método para modificar un coche de la base de datos introduciendo su id
	 * por parámetro
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
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
	//5
	/**
	 * Método que devuelve una lista de coches
	 * @return - <b>List<Usuario>
	 *         - <b>null</b> en caso de que no exista o hayamos tenido un problema en la conexion
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
	//6
	/**
	 * Crea la conexion para el almacenamiento de usuarios y llama al medoto para
	 * crear la base de datos en caso que que no exista y al metodo para crear
	 * la tabla con sus propiedades
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>1</b> si la conexión se creó exitosamente.
	 *         - <b>2</b> si hubo un error al intentar hacer la conexion
	 */
	@Override
	public int accesoADatos() {
		ConfiguracionPropiedades conf = new ConfiguracionPropiedades();
		String url=conf.getProperty("url").toString();
		String usuario = conf.getProperty("usuario").toString();
		String password = conf.getProperty("password").toString();
		String nombreBBDD =conf.getProperty("nombreBBDD").toString();
		
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
			// Crear la base de datos
            crearBaseDeDatos(conexion);
            // Establecer conexión con la base de datos recién creada
            try (Connection conexionBD = DriverManager.getConnection(url + nombreBBDD, usuario, password)) {
                // Crear la tabla de usuarios
            	
                crearTablaUsuarios(conexionBD);
                crearTablaPasajeros(conexionBD);
                //llamo al metodo para crear la relacion entre las tablas
                
                crearRelacionForeignKey(conexionBD);
                
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return 2;
		}
		return 1;
	}
	//7	
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
	//8
	/**
	 * Cierra la conexión con la base de datos.
	 * 
	 * @return - <b>true</b> si la conexión se cerró correctamente
	 *         - <b>false</b> false en caso de error.
	 */
	/*public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}*/
	//9
	 /**
     * Crea una base de datos utilizando la conexión proporcionada si no existe.
     *
     * @param conexion representa la conexión a la base de datos donde se creará la nueva base de datos.
     * @throws SQLException Si ocurre un error al intentar crear la base de datos.
     * 
     */
    private void crearBaseDeDatos(Connection conexion) throws SQLException {
    	ConfiguracionPropiedades conf = new ConfiguracionPropiedades();
		String nombreBBDD =conf.getProperty("nombreBBDD").toString();
        try (Statement statement = conexion.createStatement()) {
            // Crear la base de datos si no existe
        	statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + nombreBBDD);
        }
    }
    //10
	// Método para crear la tabla de usuarios
    /**
     * Crea una tabla de coches en la base de datos proporcionada si no existe.
     *
     * @param conexion La conexión a la base de datos donde se creará la nueva tabla de coches.
     * @throws SQLException Si ocurre un error al intentar crear la tabla de coches.
     */
    private static void crearTablaUsuarios(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la tabla de coches si no existe
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS coches ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "marca VARCHAR(50) NOT NULL,"
                    + "modelo VARCHAR(50) NOT NULL,"
                    + "año_fabricacion INT NOT NULL,"
                    + "kilometros INT NOT NULL,"
                    + "id_pasajero INT"
                    + ")");
        }
    }
    
    // Método para crear la tabla de pasajeros
    private static void crearTablaPasajeros(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la tabla de pasajeros si no existe
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Pasajeros (" 
                   + "id INT PRIMARY KEY AUTO_INCREMENT," 
                   + "nombre VARCHAR(255) NOT NULL," 
                   + "edad INT NOT NULL," 
                   + "peso DECIMAL(5,2) NOT NULL"
                   + ")");
        }
    }
    
    private static void crearRelacionForeignKey(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la relación de clave externa si no existe
            statement.executeUpdate("ALTER TABLE coches "
                    + "ADD CONSTRAINT fk_pasajero "
                    + "FOREIGN KEY (id_pasajero) "
                    + "REFERENCES Pasajeros(id)");
        }
    }
  //metodo sacar todos los coches disponibles, pedido en requerimiento 2 añadir pasajero a coche
    
    //REQUERIMIENTO 2
    
    /**
     * Obtiene una lista de coches disponibles que no tienen un pasajero asociado en la base de datos.
     *
     * @return Una lista de objetos Coche que representan los coches disponibles.
     *         Si no se puede abrir la conexión, devuelve null.
     */
    @Override
    public List<Coche> mostrarCochesDisponibles() {
  		if(!abrirConexion()){
			filas=2;
			return null;
		}
  				
  		List<Coche> listaAuxiliar = new ArrayList<>();
          String sql = "SELECT * FROM coches WHERE id_pasajero IS NULL";
          Coche coche = null;
          try (PreparedStatement pstmt = conexion.prepareStatement(sql);
               ResultSet rs = pstmt.executeQuery()) {

              while (rs.next()) {
              	coche = new Coche();
              	coche.setId(rs.getInt("ID"));
  				coche.setMarca(rs.getString("MARCA"));
  				coche.setModelo(rs.getString("MODELO"));
  				coche.setFabYear(rs.getInt("AÑO_FABRICACION"));
  				coche.setKilometros(rs.getInt("KILOMETROS"));

                  listaAuxiliar.add(coche);
              }

          } catch (SQLException e) {
              e.printStackTrace(); // Manejar la excepción de alguna manera adecuada en tu aplicación
          }
  		return listaAuxiliar;
      }
  	

  	/**
  	 * Muestra todos los coches y sus pasajeros asociados en la base de datos.  	 *
  	 * @return Una lista de objetos Coche con información sobre los coches y sus pasajeros asociados.
  	 *         Si no se puede abrir la conexión, devuelve null.
  	 */
  	@Override
  	public List<Coche> mostrarCochesConPasajeros() {
    	if(!abrirConexion()){
			filas=2;
			return null;
		}	
    	List<Coche> listaAuxiliar = new ArrayList<>();
        String sql = "SELECT c.id AS id_coche, c.marca, c.modelo, c.año_fabricacion, c.kilometros, p.id AS id_pasajero, p.nombre, p.edad, p.peso " +
                     "FROM coches c LEFT JOIN pasajeros p ON c.id_pasajero = p.id";
        Coche coche = null;
        try (PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	coche = new Coche();
              	coche.setId(rs.getInt("ID"));
  				coche.setMarca(rs.getString("MARCA"));
  				coche.setModelo(rs.getString("MODELO"));
  				coche.setFabYear(rs.getInt("AÑO_FABRICACION"));
  				coche.setKilometros(rs.getInt("KILOMETROS"));

                int idPasajero = rs.getInt("id_pasajero");
                String nombrePasajero = rs.getString("nombre");
                int edadPasajero = rs.getInt("edad");
                double pesoPasajero = rs.getDouble("peso");
                
                coche.getPasajero().setId_pasajero(idPasajero);
                coche.getPasajero().setNombre(nombrePasajero);
                coche.getPasajero().setEdad(edadPasajero);
                coche.getPasajero().setPeso(pesoPasajero);
                listaAuxiliar.add(coche);
          
            }

        } catch (SQLException e) {
            return null; // Manejar la excepción de alguna manera adecuada en tu aplicación
        }
		return listaAuxiliar;
    }
  	
}


