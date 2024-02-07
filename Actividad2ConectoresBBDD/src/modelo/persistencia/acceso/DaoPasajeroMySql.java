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
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			return null;			
		}
		return lista;
	}
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
		String sql = "UPDATE coches SET id_pasajero = ? WHERE id = ?";
		
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {	
			ps.setInt(1, idPasajero);
			ps.setInt(2, idCoche);
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
		String sql = "UPDATE coches SET id_pasajero = NULL WHERE id = ? AND id_pasajero = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
            	filas=1;
            } else {
            	filas=0;
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
        String sql = "SELECT p.id, p.nombre, p.edad, p.peso " +
                     "FROM coches c INNER JOIN pasajeros p ON c.id_pasajero = p.id " +
                     "WHERE c.id = ?";
        
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idCoche);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	coche = new Coche();
                  	coche.setId(rs.getInt("ID"));
                	int idPasajero = rs.getInt("id_pasajero");
                    String nombrePasajero = rs.getString("nombre");
                    int edadPasajero = rs.getInt("edad");
                    double pesoPasajero = rs.getDouble("peso");
                    
                    coche.getPasajero().setId_pasajero(idPasajero);
                    coche.getPasajero().setNombre(nombrePasajero);
                    coche.getPasajero().setEdad(edadPasajero);
                    coche.getPasajero().setPeso(pesoPasajero);
                    
                    
                    listaPasajeros.add(coche);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción de alguna manera adecuada en tu aplicación
        }

        return listaPasajeros;
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
