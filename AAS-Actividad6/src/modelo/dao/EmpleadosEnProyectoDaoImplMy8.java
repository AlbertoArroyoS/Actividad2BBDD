package modelo.dao;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMy8;

import modelo.javabean.EmpleadosEnProyecto;

/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 *  1. altaEmpleadoEnProyecto(EmpleadosEnProyecto empleadosEnProyecto) : int 
 *  2. eliminarEmpleadoEnProyecto(int numeroOrden) : int 
 *  3. modificarEmpleadoEnProyecto(EmpleadosEnProyecto empleadosEnProyecto, int numeroOrden ) : int 
 *  4. buscarEmpleadoOrden(int numeroOrden) : EmpleadosEnProyecto 
 *  5. buscarTodos() : List<EmpleadosEnProyecto> 
 *  6. empleadosByProyecto(String codigoProyecto) : List<EmpleadosEnProyecto> 
 *  7. asignarEmpleadosAProyecto(List<EmpleadosEnProyecto> empleadosEnProyecto) : int 
 *  8. horasAsignadasAProyecto(String codigoProyecto) : int 
 *  9. costeActualDeProyecto(String codigoProyecto) : double 
 * 10. margenActualProyecto(String codigoProyecto) : double
 * 11. crearObjetoEmpleadosEnProyecto(EmpleadosEnProyecto eproy) : void
 *  
 * 
 * @see EmpleadosEnProyectoDao
 * @see AbstractDaoMy8
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */

public class EmpleadosEnProyectoDaoImplMy8 extends AbstractDaoMy8 implements EmpleadosEnProyectoDao{

	//Creamos los objetos que vamos a usar continuamente a lo largo de la clase
	private ProyectoDao pdao;
	private EmpleadoDao edao;
	
	
	public EmpleadosEnProyectoDaoImplMy8() {
		super();
		pdao = new ProyectoDaoImplMy8();
		edao = new EmpleadoDaoImplMy8();
	}

	
	//1. altaEmpleadoEnProyecto
	@Override
	public int altaEmpleadoEnProyecto(EmpleadosEnProyecto empleadosEnProyecto) {
		sql = "insert into proyecto_con_empleados (id_proyecto, id_empl, horas_asignadas,fecha_incorporacion) values(?,?,?,?)";
		filas = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, empleadosEnProyecto.getProyecto().getIdProyecto()); 
			ps.setInt(2, empleadosEnProyecto.getEmpleado().getIdEmpl()); 
			ps.setInt(3, empleadosEnProyecto.getHorasAsignadas());
			ps.setDate(4, empleadosEnProyecto.getFechaIncorporacion());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//2. eliminarEmpleadoEnProyecto
	@Override
	public int eliminarEmpleadoEnProyecto(int numeroOrden) {
		sql = "delete from proyecto_con_empleados where numero_orden = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, numeroOrden);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	//3. modificarEmpleadoEnProyecto
	@Override
	public int modificarEmpleadoEnProyecto(EmpleadosEnProyecto empleadosEnProyecto, int numeroOrden ) {
		sql = "update proyecto_con_empleados set id_proyecto = ?, id_empl = ?, horas_asignadas = ?, fecha_incorporacion = ? where numero_orden = ?";
				//+ " where numero_orden = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, empleadosEnProyecto.getProyecto().getIdProyecto()); 
			ps.setInt(2, empleadosEnProyecto.getEmpleado().getIdEmpl()); 
			ps.setInt(3, empleadosEnProyecto.getHorasAsignadas());
			ps.setDate(4, empleadosEnProyecto.getFechaIncorporacion());
			ps.setInt(5, numeroOrden);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//4. buscarEmpleadoOrden
	@Override
	public EmpleadosEnProyecto buscarEmpleadoOrden(int numeroOrden) {
		sql = "select * from proyecto_con_empleados where numero_orden = ?";
		EmpleadosEnProyecto eproy = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, numeroOrden);
			rs = ps.executeQuery();
			if (rs.next()) {
				eproy = new EmpleadosEnProyecto();
				crearObjetoEmpleadosEnProyecto(eproy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eproy;
	}

	//5. buscarTodos
	@Override
	public List<EmpleadosEnProyecto> buscarTodos() {
		sql = "select * from proyecto_con_empleados";
		List<EmpleadosEnProyecto> lista = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmpleadosEnProyecto eproy = new EmpleadosEnProyecto();
				crearObjetoEmpleadosEnProyecto(eproy);
				lista.add(eproy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//6. empleadosByProyecto
	@Override
	public List<EmpleadosEnProyecto> empleadosByProyecto(String codigoProyecto) {
		sql = "select *\r\n"
				+ "from empleados, proyecto_con_empleados\r\n"
				+ "Where proyecto_con_empleados.id_empl = empleados.id_empl\r\n"
				+ "and id_proyecto like ?";
		
	   //querie simple sin join, sql = "select id_empl from proyecto_con_empleados where id_proyecto like ?";
		
		List<EmpleadosEnProyecto> lista = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,codigoProyecto);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmpleadosEnProyecto eproy = new EmpleadosEnProyecto();
				crearObjetoEmpleadosEnProyecto(eproy);			
				lista.add(eproy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//7. asignarEmpleadosAProyecto
	// devolver cuantos hemos insertado en la list que hemos pasado por parametro
	@Override
	public int asignarEmpleadosAProyecto(List<EmpleadosEnProyecto> empleadosEnProyecto) {
		int contador = 0;
		for (EmpleadosEnProyecto ele: empleadosEnProyecto) {
			sql = "insert into proyecto_con_empleados (id_proyecto, id_empl, horas_asignadas,fecha_incorporacion) values(?,?,?,?)";
			filas = 0;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1,ele.getProyecto().getIdProyecto()); 
				ps.setInt(2, ele.getEmpleado().getIdEmpl()); 
				ps.setInt(3, ele.getHorasAsignadas());
				ps.setDate(4, ele.getFechaIncorporacion());
				filas = ps.executeUpdate();
				contador++;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contador;
			
	}

	//8. horasAsignadasAProyecto
	@Override
	public int horasAsignadasAProyecto(String codigoProyecto) {
		int horasAsignadasAProyecto = 0;
		sql = "select SUM(horas_asignadas)AS horas from proyecto_con_empleados where id_proyecto like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,codigoProyecto);
			rs = ps.executeQuery();
			if(rs.next()){
				horasAsignadasAProyecto = rs.getInt("horas");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return horasAsignadasAProyecto;
	}
	
	//9. costeActualDeProyecto
	//horas*coste-hora de cada empleadoasignado al proyecto
	@Override
	public double costeActualDeProyecto(String codigoProyecto) {
		double coste = 0;
		sql = "select  COUNT(proyecto_con_empleados.id_empl)AS num_id,SUM(proyecto_con_empleados.horas_asignadas) AS horas_totales , perfiles.precio_hora, (perfiles.precio_hora* SUM(proyecto_con_empleados.horas_asignadas)) AS coste_actual\r\n"
				+ "from proyecto_con_empleados, empleados, perfiles\r\n"
				+ "where proyecto_con_empleados.id_empl = empleados.id_empl And empleados.id_perfil = perfiles.id_perfil\r\n"
				+ "And proyecto_con_empleados.id_proyecto = ?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,codigoProyecto);
			rs = ps.executeQuery();
			if(rs.next()){
				coste = rs.getDouble("coste_actual");
				int idTotales = rs.getInt("num_id");
				int horasTotales = rs.getInt("horas_totales");
				double precioHora = rs.getDouble("precio_hora");
				System.out.println("Los empleados en el proyecto son : " + idTotales);
				System.out.println("Las horas totales del proyecto son : " + horasTotales);
				System.out.println("El precio/hora de este proyecto es : " + precioHora);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return coste;
	}
	
	//10. margenActualProyecto
	//Importe_venta del proyecto – costeActualdel Proyecto
	@Override
	public double margenActualProyecto(String codigoProyecto) {
		double margen = 0;
		sql = "SELECT (proyectos.venta_previsto - proyectos.coste_real) As margen from proyectos where proyectos.id_proyecto = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,codigoProyecto);
			rs = ps.executeQuery();
			if(rs.next()){
				margen = rs.getDouble("margen");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return margen;
	}
	
	//11. Metodo para asignar el valor de las columnas al objeto
	private void crearObjetoEmpleadosEnProyecto(EmpleadosEnProyecto eproy)throws SQLException {
		eproy.setNumeroOrden(rs.getInt("numero_orden"));
		eproy.setProyecto(pdao.buscarProyecto(rs.getString("id_proyecto")));	
		eproy.setEmpleado(edao.buscarEmpleado(rs.getInt("id_empl")));
		eproy.setHorasAsignadas(rs.getInt("horas_asignadas"));
		eproy.setFechaIncorporacion(rs.getDate("fecha_incorporacion"));
	}
}
