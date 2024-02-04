package modelo.dao;

import java.sql.Date;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMy8;
import modelo.javabean.Proyecto;
/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 *  1. altaProyecto(Proyecto proyecto) : int 
 *  2. eliminarProyecto(String idProyecto) : int 
 *  3. modificarProyecto(Proyecto proyecto) : int  
 *  4. buscarProyecto(String idProyecto) : Proyecto 
 *  5. buscarTodos() : List<Proyecto> 
 *  6. proyectosByEstado(String estado) : List<Proyecto> 
 *  7. proyectosByCliente(String cif) : List<Proyecto> 
 *  8. proyectosByJefeProyectoAndByEstado(int jefeProyecto, String estado) : List<Proyecto> 
 *  9. importesVentaProyectosTerminados() : double 
 * 10. margenBrutoProyectosTerminados() : double 
 * 11. diasATerminoProyectoActivo(String codigoProyecto) : int 
 * 12. diasATerminoProyectoActivoMy8(String codigoProyecto) : int
 * 13. buscarProyectoPorContenidoId(String contenidoId) : List<Proyecto> 
 * 14. crearProyecto(Proyecto proy): void
 *   
 * @see ProyectoDao
 * @see AbstractDaoMy8
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class ProyectoDaoImplMy8 extends AbstractDaoMy8 implements ProyectoDao{

	//Creamos los objetos que vamos a usar continuamente a lo largo de la clase
	private ClienteDao cdao;
	private EmpleadoDao edao; 
	
	public ProyectoDaoImplMy8() {
		cdao = new ClienteDaoImplMy8();
		edao = new EmpleadoDaoImplMy8();
	}
		
	//1. altaProyecto
	@Override
	public int altaProyecto(Proyecto proyecto) {
		sql = "insert into proyectos values(?,?,?,?,?,?,?,?,?,?,?)";
		filas = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, proyecto.getIdProyecto()); 
			ps.setString(2, proyecto.getDescripcion()); 
			ps.setDate(3, proyecto.getFechaInicio());
			ps.setDate(4, proyecto.getFechaFinPrevisto());
			ps.setDate(5, proyecto.getFechaFinReal()); 
			ps.setDouble(6, proyecto.getVentaPrevisto());
			ps.setDouble(7, proyecto.getCostesPrevisto());
			ps.setDouble(8, proyecto.getCosteReal());
			ps.setString(9, proyecto.getEstado());
			ps.setInt(10,proyecto.getJefeProyecto().getIdEmpl());
			ps.setString(11,proyecto.getCliente().getCif());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//2. eliminarProyecto
	@Override
	public int eliminarProyecto(String idProyecto) {
		sql = "delete from proyectos where id_proyecto = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idProyecto);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	//3. modificarProyecto
	@Override
	public int modificarProyecto(Proyecto proyecto) {
		sql = "update proyectos set descripcion = ?, fecha_inicio = ?, fecha_fin_previsto = ?, fecha_fin_real = ?, venta_previsto = ?, costes_previsto = ?, coste_real = ?, estado = ?, jefe_proyecto = ?, cif = ? "
				+ " where id_proyecto = ?";
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, proyecto.getDescripcion()); 
			ps.setDate(2, proyecto.getFechaInicio());
			ps.setDate(3, proyecto.getFechaFinPrevisto());
			ps.setDate(4, proyecto.getFechaFinReal()); 
			ps.setDouble(5, proyecto.getVentaPrevisto());
			ps.setDouble(6, proyecto.getCostesPrevisto());
			ps.setDouble(7, proyecto.getCosteReal());
			ps.setString(8, proyecto.getEstado());
			ps.setInt(9,proyecto.getJefeProyecto().getIdEmpl());
			ps.setString(10,proyecto.getCliente().getCif());
			ps.setString(11, proyecto.getIdProyecto()); 
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//4. buscarProyecto
	@Override
	public Proyecto buscarProyecto(String idProyecto) {
		sql = "select * from proyectos where id_proyecto = ?";
		Proyecto proy = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idProyecto);
			rs = ps.executeQuery();
			if (rs.next()) {
				proy = new Proyecto();
				crearProyecto(proy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proy;
	}

	//5. buscarTodos
	@Override
	public List<Proyecto> buscarTodos() {
		sql = "select * from proyectos";
		List<Proyecto> lista = new ArrayList<>();	
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Proyecto proy = new Proyecto();
				crearProyecto(proy);
				lista.add(proy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//6. proyectosByEstado
	@Override
	public List<Proyecto> proyectosByEstado(String estado) {
		sql = "select * from proyectos where estado like ?";
		List<Proyecto> lista = new ArrayList<>();	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,estado);
			rs = ps.executeQuery();
			while (rs.next()) {
				Proyecto proy = new Proyecto();
				crearProyecto(proy);
				lista.add(proy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//7. proyectosByCliente
	@Override
	public List<Proyecto> proyectosByCliente(String cif) {
		sql = "select * from proyectos where cif like ?";
		List<Proyecto> lista = new ArrayList<>();		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,cif);
			rs = ps.executeQuery();
			while (rs.next()) {
				Proyecto proy = new Proyecto();
				crearProyecto(proy);
				lista.add(proy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//8. proyectosByJefeProyectoAndByEstado
	@Override
	public List<Proyecto> proyectosByJefeProyectoAndByEstado(int jefeProyecto, String estado) {
		sql = "select * from proyectos where jefe_proyecto = ? and estado like ?";
		List<Proyecto> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,jefeProyecto);
			ps.setString(2,estado);
			rs = ps.executeQuery();
			while (rs.next()) {
				Proyecto proy = new Proyecto();
				crearProyecto(proy);
				lista.add(proy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//9. importesVentaProyectosTerminados
	@Override
	public double importesVentaProyectosTerminados() {
		double importesVenta = 0;
		sql = "select SUM(venta_previsto)AS importes_venta from proyectos where estado like 'terminado'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				importesVenta = rs.getDouble("importes_venta");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return importesVenta;
	}
	
	//10. margenBrutoProyectosTerminados
	// Diferencia suma Importes venta y gastos reales
	@Override
	public double margenBrutoProyectosTerminados() {
		double margen = 0;
		sql = "select ((SUM(venta_previsto)) - (SUM(coste_real))) AS margen from proyectos where estado like 'terminado'";
		try {
			ps = conn.prepareStatement(sql);
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
	
	//11. diasATerminoProyectoActivo
	//(diferencia entre fecha_fin_previsto y la fecha de hoy
	@Override
	public int diasATerminoProyectoActivo(String codigoProyecto) {
		Date fechaActual = new Date(System.currentTimeMillis());
		Date fechaPrevista = null;
		sql = "select fecha_fin_previsto from proyectos where id_proyecto like ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,codigoProyecto);
			rs = ps.executeQuery();
			if(rs.next()){
				fechaPrevista = rs.getDate("fecha_fin_previsto");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//milisegundos que tiene un dia
		int milisegundosDia = 86400000;
		int dias = (int) ((fechaActual.getTime()-fechaPrevista.getTime()) / milisegundosDia);
		
		return dias;
	}
	
	//12. diasATerminoProyectoActivoMy8
	public int diasATerminoProyectoActivoMy8(String codigoProyecto) {
		sql = "SELECT DATEDIFF (fecha_fin_previsto, current_date()) As diferencia_dias\r\n"
				+ "From proyectos\r\n"
				+ "Where id_proyecto Like ?";
		int diferenciaDias =0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, codigoProyecto);
			rs = ps.executeQuery();
			if(rs.next()){
				diferenciaDias = rs.getInt("diferencia_dias");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return diferenciaDias;
	}
	
	//13.Buscar si el nombre del proyecto contiene una cadena de caracteres
	@Override
	public List<Proyecto> buscarProyectoPorContenidoId(String contenidoId) {
		/*
		 * 1.- que comienza por  like 'subcadena%'
		 * 2.- que termina en: like '%subcadena'
		 * 3.- que contiene     like '%subcadena%'
		 */
		sql = "select * from proyectos where id_proyecto like ?";
		List<Proyecto> lista = new ArrayList<>();
		 
		try {
			ps = conn.prepareStatement(sql);
			// ps.setString(1, contenidoId + "%");
			 //ps.setString(1, "%" + prefijoIdCliente);
			ps.setString(1, "%" + contenidoId + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Proyecto proy = new Proyecto();
				crearProyecto(proy);
				lista.add(proy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	//14. Metodo para asignar el valor de las columnas al objeto
	private void crearProyecto(Proyecto proy)throws SQLException{	
		proy.setIdProyecto(rs.getString("id_proyecto"));
		proy.setDescripcion(rs.getString("descripcion"));
		proy.setFechaInicio(rs.getDate("fecha_inicio"));
		proy.setFechaFinPrevisto(rs.getDate("fecha_fin_previsto"));
		proy.setFechaFinReal(rs.getDate("fecha_fin_real"));
		proy.setVentaPrevisto(rs.getDouble("venta_previsto"));
		proy.setCostesPrevisto(rs.getDouble("costes_previsto"));
		proy.setCosteReal(rs.getDouble("coste_real"));
		proy.setEstado(rs.getString("estado"));
		//id perfil
		proy.setJefeProyecto(edao.buscarEmpleado(rs.getInt("jefe_proyecto")));
		//id departamentos
		proy.setCliente(cdao.buscarCliente(rs.getString("cif")));
	}		
}
