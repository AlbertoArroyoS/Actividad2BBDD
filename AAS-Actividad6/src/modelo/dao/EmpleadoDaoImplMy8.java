package modelo.dao;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMy8;
import modelo.javabean.Empleado;
/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 *  1. altaEmpleado(Empleado empleado) : int 
 *  2. eliminarEmpleado(int idEmpl) : int 
 *  3. modificarEmpleado(Empleado empleado) : int 
 *  4. buscarEmpleado(int idEmpl) : Empleado 
 *  5. buscarTodos() : List<Empleado> 
 *  6. empleadosByDepartamento(int idDepar) : List<Empleado> 
 *  7. empleadosBySexo(char sexo) : List<Empleado> 
 *  8. empleadosByApellido(String subcadena) : List<Empleado> 
 *  9. salarioTotal() : double 
 * 10. salarioTotal(int idDepar) : double 
 * 11. crearObjetoEmpleado(Empleado emp) : void 
 * 
 * @see EmpleadoDao
 * @see AbstractDaoMy8
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class EmpleadoDaoImplMy8  extends AbstractDaoMy8 implements EmpleadoDao{

	//Creamos los objetos que vamos a usar continuamente a lo largo de la clase
	private PerfilDao pdao;
	private DepartamentoDao ddao;
	
	public EmpleadoDaoImplMy8() {

		pdao = new PerfilDaoImplMy8();
		ddao = new DepartamentoDaoImplMy8();
	}
	
	//1. altaEmpleado
	@Override
	public int altaEmpleado(Empleado empleado) {
		sql = "insert into empleados values(?,?,?,?,?,?,?,?,?,?,?)";
		filas = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empleado.getIdEmpl()); 
			ps.setString(2, empleado.getNombre()); 
			ps.setString(3, empleado.getApellidos());
			// setString(X, String.valueOf(myChar)) , getString("your_column").charAt(0);
			ps.setString(4, String.valueOf(empleado.getGenero()));
			ps.setString(5, empleado.getEmail()); 
			ps.setString(6, empleado.getPassword());
			ps.setDouble(7, empleado.getSalario());
			ps.setDate(8, empleado.getFechaIngreso());
			ps.setDate(9, empleado.getFechaNacimiento());
			ps.setInt(10,empleado.getPerfil().getIdPerfil());
			ps.setInt(11,empleado.getDepartamento().getIdDepar());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//2. eliminarEmpleado
	@Override
	public int eliminarEmpleado(int idEmpl) {
		
		sql = "delete from empleados where id_empl = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idEmpl);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	//3. modificarEmpleado
	@Override
	public int modificarEmpleado(Empleado empleado) {
		sql = "update empleados set nombre = ?, apellidos = ?, genero = ?, email = ?, password = ?, salario = ?, fecha_ingreso = ?, fecha_nacimiento = ?, id_perfil = ?, id_depar = ? "
				+ " where id_empl = ?";
		try {
			ps = conn.prepareStatement(sql);
		
			ps.setString(1, empleado.getNombre()); 
			ps.setString(2, empleado.getApellidos());
			// setString(X, String.valueOf(myChar)) , getString("your_column").charAt(0);
			ps.setString(3, String.valueOf(empleado.getGenero()));
			ps.setString(4, empleado.getEmail()); 
			ps.setString(5, empleado.getPassword());
			ps.setDouble(6, empleado.getSalario());
			ps.setDate(7, empleado.getFechaIngreso());
			ps.setDate(8, empleado.getFechaNacimiento());
			ps.setInt(9,empleado.getPerfil().getIdPerfil());
			ps.setInt(10,empleado.getDepartamento().getIdDepar());
			ps.setInt(11, empleado.getIdEmpl()); 
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//4. modificarEmpleado
	@Override
	public Empleado buscarEmpleado(int idEmpl) {
		sql = "select * from empleados where id_empl = ?";
		Empleado emp = null;		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idEmpl);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp = new Empleado();
				crearObjetoEmpleado(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	//5. buscarTodos
	@Override
	public List<Empleado> buscarTodos() {
		
		sql = "select * from empleados";
		List<Empleado> lista = new ArrayList<>();		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Empleado emp = new Empleado();
				emp = new Empleado();
				crearObjetoEmpleado(emp);
				lista.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//6. empleadosByDepartamento
	@Override
	public List<Empleado> empleadosByDepartamento(int idDepar) {
		sql = "select * from empleados where id_depar = ?";
		List<Empleado> lista = new ArrayList<>();	
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idDepar);
			rs = ps.executeQuery();
			ddao.buscarDepartamento(idDepar);
			while (rs.next()) {
				Empleado emp = new Empleado();
				emp = new Empleado();
				crearObjetoEmpleado(emp);
			//	emp.setDepartamento(depart);
				lista.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//7. empleadosBySexo
	@Override
	public List<Empleado> empleadosBySexo(char sexo) {
		
		sql = "select * from empleados where genero = ?";
		List<Empleado> lista = new ArrayList<>();		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(sexo));
			rs = ps.executeQuery();
			while (rs.next()) {
				Empleado emp = new Empleado();
				emp = new Empleado();
				crearObjetoEmpleado(emp);
				lista.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//8. empleadosByApellido
	@Override
	public List<Empleado> empleadosByApellido(String subcadena) {
		sql = "select * from empleados where apellidos like ?";
		List<Empleado> lista = new ArrayList<>();		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,subcadena);
			rs = ps.executeQuery();
			while (rs.next()) {
				Empleado emp = new Empleado();
				emp = new Empleado();
				crearObjetoEmpleado(emp);
				lista.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	//9. salarioTotal
	@Override
	public double salarioTotal() {

		double salarioTotal = 0;
		sql = "select SUM(salario)AS salario_total from empleados";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				salarioTotal = rs.getDouble("salario_total");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		return salarioTotal;		
	}

	//10. salarioTotal
	@Override
	public double salarioTotal(int idDepar) {		
		double salarioTotal = 0;
		sql = "select SUM(salario)AS salario_total from empleados where id_depar = ?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idDepar);
			rs = ps.executeQuery();
			if(rs.next()){
				salarioTotal = rs.getDouble("salario_total");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}	
		return salarioTotal;		
	}
	
	//11. Metodo para asignar el valor de las columnas al objeto
	private void crearObjetoEmpleado(Empleado emp) throws SQLException{
		emp.setIdEmpl(rs.getInt("id_empl"));
		emp.setNombre(rs.getString("nombre"));
		emp.setApellidos(rs.getString("apellidos"));
		// setString(1, String.valueOf(myChar)) , getString("your_column").charAt(0);
		emp.setGenero(rs.getString("genero").charAt(0));
		emp.setEmail(rs.getString("email"));
		emp.setPassword(rs.getString("password"));
		emp.setSalario(rs.getDouble("salario"));
		emp.setFechaIngreso(rs.getDate("fecha_ingreso"));
		emp.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		//id perfil
		emp.setPerfil(pdao.buscarPerfil(rs.getInt("id_perfil")));
		//id departamentos
		emp.setDepartamento(ddao.buscarDepartamento(rs.getInt("id_depar")));
	}
}
