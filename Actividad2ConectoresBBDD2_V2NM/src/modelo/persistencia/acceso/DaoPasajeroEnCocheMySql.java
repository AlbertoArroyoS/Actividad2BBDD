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
import modelo.persistencia.interfaces.DaoPasajeroEnCoche;

public class DaoPasajeroEnCocheMySql implements DaoPasajeroEnCoche{
	
	//variables
	private int filas = 0;
	private String sql;
	private Connection conexion;
	private ResultSet rs;
	
	
	/**
	 * Metodo para asocia un pasajero a un coche en la base de datos.
	 *
	 * @param idCoche    El ID del coche al cual se asociará el pasajero.
	 * @param idPasajero El ID del pasajero que se asociará al coche.
	 * @return Un valor entero que representa el resultado de la operación:
	 *         - 1: Éxito, el pasajero se asoció al coche correctamente.
	 *         - 2: No se pudo abrir la conexión a la base de datos.
	 *         - 3: Ocurrió una excepción SQLException durante la ejecución.
	 */
	@Override
	public int addPasajeroCoche(int idCoche, int idPasajero) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}
		sql = "INSERT INTO pasajeros_en_coches (id_coche, id_pasajero) VALUES (?, ?)";
		
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {	
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			filas=3;
			return filas;
		}
		
		return filas;
	}
	
	/**
	 * Metodo para eliminar un pasajero de un coche en la base de datos.
	 *
	 * @param idCoche    El ID del coche del cual se eliminará el pasajero.
	 * @param idPasajero El ID del pasajero que se eliminará del coche.
	 * @return Un valor entero que representa el resultado de la operación:
	 *         - 1: Éxito, el pasajero fue eliminado del coche.
	 *         - 0: No se encontró el coche o el pasajero asociado.
	 *         - 2: No se pudo abrir la conexión a la base de datos.
	 *         - 3: Ocurrió una excepción SQLException durante la ejecución.
	 */
	@Override
	public int eliminarPasajeroCoche(int idCoche, int idPasajero) {
		if(!abrirConexion()){
			filas=2;
			return filas;
		}		
        sql = "DELETE FROM pasajeros_en_coches WHERE id_coche = ? AND id_pasajero = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
            	filas=0;
            } else {
            	filas=1;
            }

        } catch (SQLException e) {
        	filas=3;
        }
		return filas;
	}
	/**
     * Obtiene una lista de coches con información sobre los pasajeros asociados a un coche específico.
     *
     * @param idCoche El ID del coche del cual se desea obtener la información de pasajeros.
     * @return Una lista de objetos Coche con información sobre los pasajeros asociados al coche.
     *         Si no se puede abrir la conexión, devuelve null.
     */
	@Override
	public List<Coche> pasajerosEnCoche(int idCoche) {
		if(!abrirConexion()){
			filas=2;
			return null;
		}	
		Coche coche = null;
		List<Coche> listaPasajeros = new ArrayList<>();
		sql = "SELECT p.id, p.nombre, p.edad, p.peso " +
	              "FROM coches c " +
	              "INNER JOIN pasajeros_en_coches pc ON c.id = pc.id_coche " +
	              "INNER JOIN pasajeros p ON pc.id_pasajero = p.id " +
	              "WHERE c.id = ?";
        
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idCoche);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	coche = new Coche();             	
                
                    Pasajero pasajero = new Pasajero();
      			    pasajero.setId_pasajero(rs.getInt(1));
      			    pasajero.setNombre(rs.getString(2));
      			    pasajero.setEdad(rs.getInt(3));
      			    pasajero.setPeso(rs.getDouble(4));

      			    // Asignar el Pasajero al Coche
      			    coche.setPasajero(pasajero);
                    
                    
                    listaPasajeros.add(coche);
                }
            }

        } catch (SQLException e) {
            return null;
        }

        return listaPasajeros;
    }
		

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
  		
        sql = "SELECT * FROM coches WHERE id NOT IN (SELECT id_coche FROM pasajeros_en_coches)";
        
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
    	
    	sql = "SELECT c.id AS id_coche, c.marca, c.modelo, c.año_fabricacion, c.kilometros, p.id AS id_pasajero, p.nombre, p.edad, p.peso " +
                "FROM coches c " +
                "LEFT JOIN pasajeros_en_coches pc ON c.id = pc.id_coche " +
                "LEFT JOIN pasajeros p ON pc.id_pasajero = p.id " +
                "WHERE pc.id_pasajero IS NOT NULL";
    	
        Coche coche = null;
        try (PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	coche = new Coche();
              	coche.setId(rs.getInt(1));
  				coche.setMarca(rs.getString(2));
  				coche.setModelo(rs.getString(3));
  				coche.setFabYear(rs.getInt(4));
  				coche.setKilometros(rs.getInt(5));
  				
  			// Inicializar un nuevo objeto Pasajero
  			    Pasajero pasajero = new Pasajero();
  			    pasajero.setId_pasajero(rs.getInt(6));
  			    pasajero.setNombre(rs.getString(7));
  			    pasajero.setEdad(rs.getInt(8));
  			    pasajero.setPeso(rs.getDouble(9));

  			    // Asignar el Pasajero al Coche
  			    coche.setPasajero(pasajero);

  			    listaAuxiliar.add(coche);

          
            }

        } catch (SQLException e) {
            return null; // Manejar la excepción de alguna manera adecuada en tu aplicación
        }
		return listaAuxiliar;
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
