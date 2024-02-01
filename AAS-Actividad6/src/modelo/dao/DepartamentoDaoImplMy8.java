package modelo.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMy8;
import modelo.javabean.Departamento;
/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 * 1. altaDepartamento(Departamento departamento) : int
 * 2. eliminarDepartamento(int idDepar) : int 
 * 3. modificarDepartamento(Departamento departamento) : int 
 * 4. buscarDepartamento(int idDepar) : Departamento 
 * 5. buscarTodos() : List<Departamento> 
 * 6. buscarPorNombre(String nombre) : List<Departamento> 
 * 7. crearObjetoDepartamento(Departamento dp) : void 
 * 
 * @see DepartamentoDao
 * @see AbstractDaoMy8
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class DepartamentoDaoImplMy8 extends AbstractDaoMy8 implements DepartamentoDao{

	//1. altaDepartamento
	@Override
	public int altaDepartamento(Departamento departamento) {
		sql = "insert into departamentos values(?,?,?)";
		filas = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, departamento.getIdDepar());
			ps.setString(2, departamento.getNombre());
			ps.setString(3, departamento.getDireccion());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//2. eliminarDepartamento
	@Override
	public int eliminarDepartamento(int idDepar) {
		sql = "delete from departamentos where id_depar = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idDepar);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	//3. modificarDepartamento
	@Override
	public int modificarDepartamento(Departamento departamento) {
		sql = "update departamentos set nombre = ?, direccion = ? "
				+ " where id_depar = ?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, departamento.getNombre());
			ps.setString(2, departamento.getDireccion());
			ps.setInt(3, departamento.getIdDepar());
			
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//4. buscarDepartamento
	@Override
	public Departamento buscarDepartamento(int idDepar) {
		sql = "select * from departamentos where id_depar = ?";
		Departamento dp = null;
		
		try {
			ps = conn.prepareStatement(sql);
			//Como solo hay 1 ?
			ps.setInt(1, idDepar);
			rs = ps.executeQuery();
			if (rs.next()) {
				dp = new Departamento();
				crearObjetoDepartamento(dp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dp;
	}

	//5. buscarTodos
	@Override
	public List<Departamento> buscarTodos() {
		sql = "select * from departamentos";
		List<Departamento> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery();
			while (rs.next()) {
				Departamento dp = new Departamento();
				crearObjetoDepartamento(dp);
				lista.add(dp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//6. buscarPorNombre
	@Override
	public List<Departamento> buscarPorNombre(String nombre) {
		sql = "select * from departamentos where nombre = ?";
		List<Departamento> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			while (rs.next()) {
				Departamento dp = new Departamento();
				crearObjetoDepartamento(dp);
				lista.add(dp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	//7. Metodo para asignar el valor de las columnas al objeto
	private void crearObjetoDepartamento(Departamento dp) throws SQLException{
		dp.setIdDepar(rs.getInt("id_depar"));
		dp.setNombre(rs.getString("nombre"));
		dp.setDireccion(rs.getString("direccion"));
	}
}
