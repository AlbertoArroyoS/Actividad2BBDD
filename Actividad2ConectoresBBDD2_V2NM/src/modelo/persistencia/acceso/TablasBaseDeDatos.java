package modelo.persistencia.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TablasBaseDeDatos {
	
	private Connection conexion;
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
                      + "kilometros INT NOT NULL"  // Eliminar la coma al final de esta línea
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
    /*
    private static void crearRelacionForeignKey(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la relación de clave externa si no existe
            statement.executeUpdate("ALTER TABLE coches "
                    + "ADD CONSTRAINT fk_pasajero "
                    + "FOREIGN KEY (id_pasajero) "
                    + "REFERENCES Pasajeros(id)");
        }
    }*/
    
    private static void crearTablaPasajerosEnCoches(Connection conexion) throws SQLException {
        try (Statement statement = conexion.createStatement()) {
            // Crear la tabla de relación N:N entre coches y pasajeros
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Pasajeros_En_Coches ("
                    + "id_coche INT,"
                    + "id_pasajero INT,"
                    + "PRIMARY KEY (id_coche, id_pasajero),"
                    + "FOREIGN KEY (id_coche) REFERENCES coches(id),"
                    + "FOREIGN KEY (id_pasajero) REFERENCES Pasajeros(id)"
                    + ")");
        }
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
                  crearTablaPasajerosEnCoches(conexionBD);
                  //llamo al metodo para crear la relacion entre las tablas
                  
                  //crearRelacionForeignKey(conexionBD);
                  
              }
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			//e.printStackTrace();
  			return 2;
  		}
  		return 1;
  	}
  	
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
}
