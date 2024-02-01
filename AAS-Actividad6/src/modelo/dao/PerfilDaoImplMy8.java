package modelo.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMy8;
import modelo.javabean.Perfil;
/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 * 1. altaPerfil(Perfil perfil) : int 
 * 2. eliminarPerfil(int idPerfil) : int 
 * 3. modificarPerfil(Perfil perfil) : int 
 * 4. buscarPerfil(int idPerfil) : Perfil 
 * 5. buscarTodos() : List<Perfil> 
 * 6. buscarPorNombre(String nombre) : List<Perfil> 
 * 7. crearPerfil(Perfil perf) : void 
 * 
 * @see PerfilDao
 * @see AbstractDaoMy8
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class PerfilDaoImplMy8 extends AbstractDaoMy8 implements PerfilDao{

	//1. altaPerfil 
	@Override
	public int altaPerfil(Perfil perfil) {
		sql = "insert into perfiles values(?,?,?)";
		filas = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, perfil.getIdPerfil());
			ps.setString(2, perfil.getNombre());
			ps.setDouble(3, perfil.getPrecioHora());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//2. eliminarPerfil
	@Override
	public int eliminarPerfil(int idPerfil) {
		sql = "delete from perfiles where id_perfil = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idPerfil);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	//3. modificarPerfil
	@Override
	public int modificarPerfil(Perfil perfil) {
		sql = "update perfiles set nombre = ?, precio_hora = ? "
				+ " where id_perfil = ?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, perfil.getNombre());
			ps.setDouble(2, perfil.getPrecioHora());
			ps.setInt(3, perfil.getIdPerfil());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//4. buscarPerfil
	@Override
	public Perfil buscarPerfil(int idPerfil) {
		sql = "select * from perfiles where id_perfil = ?";
		Perfil perf = null;
		
		try {
			ps = conn.prepareStatement(sql);
			//Como solo hay 1 ?
			ps.setInt(1, idPerfil);
			rs = ps.executeQuery();
			if (rs.next()) {
				perf = new Perfil();
				crearPerfil(perf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return perf;
	}

	//5. buscarTodos
	@Override
	public List<Perfil> buscarTodos() {
		sql = "select * from perfiles";
		List<Perfil> lista = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery();
			while (rs.next()) {
				Perfil perf = new Perfil();
				crearPerfil(perf);
				lista.add(perf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//6. buscarPorNombre
	@Override
	public List<Perfil> buscarPorNombre(String nombre) {
		sql = "select * from perfiles where nombre = ?";
		List<Perfil> lista = new ArrayList<>();
	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			while (rs.next()) {
				Perfil perf = new Perfil();
				crearPerfil(perf);
				lista.add(perf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	//7. Metodo para asignar el valor de las columnas al objeto
	private void crearPerfil(Perfil perf)throws SQLException{
		perf.setIdPerfil(rs.getInt("id_perfil"));
		perf.setNombre(rs.getString("nombre"));
		perf.setPrecioHora(rs.getDouble("precio_hora"));
	}
}
