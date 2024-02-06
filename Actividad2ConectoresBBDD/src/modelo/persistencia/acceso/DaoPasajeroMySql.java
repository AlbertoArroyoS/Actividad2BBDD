package modelo.persistencia.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		private Coche coche;

	@Override
	public int altaPasajero(Pasajero pasajero) {
		if(!abrirConexion()){
			filas = 2;
			return filas;
		}

		sql = "insert into coches (MARCA,MODELO,AÑO_FABRICACION,KILOMETROS) "
				+ " values(?,?,?,?)";
		filas = 0;
		try {
			//preparamos la query con valores parametrizables(?)
			ps = conexion.prepareStatement(sql);
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

	@Override
	public int eliminarPasajero(int idPasajero) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pasajero buscarPasajero(int idPasajero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pasajero> buscarTodosPasajeros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPasajeroCoche(Pasajero pasajero) {
		// TODO Auto-generated method stub
		return 0;
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
