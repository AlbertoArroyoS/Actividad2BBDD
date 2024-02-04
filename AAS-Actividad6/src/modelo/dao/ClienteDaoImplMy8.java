package modelo.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.AbstractDaoMy8;
import modelo.javabean.Cliente;
/**
 * Clase en la que estan los metodos que implementan el interface:
 * 
 * 1. altaCliente(Cliente cliente) : int
 * 2. buscarCliente(String cif) : Cliente
 * 3. buscarTodos() : List<Cliente>
 * 4. eliminarCliente(String cif) : int
 * 5. modificarCliente(Cliente cliente) : int
 * 6. buscarPorDomicilio(String domicilio) : List<Cliente>
 * 7. crearObjetoCliente(Cliente cl) : void 
 * 
 * @see ClienteDao
 * @see AbstractDaoMy8
 *  
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 *
 */
public class ClienteDaoImplMy8 extends AbstractDaoMy8 implements ClienteDao{
	
	//1. altaCliente
	@Override
	public int altaCliente(Cliente cliente) {
		sql = "insert into clientes values(?,?,?,?,?,?)";
		filas = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getCif());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getApellidos());
			ps.setString(4, cliente.getDomicilio());
			ps.setDouble(5, cliente.getFacturacionAnual());;
			ps.setInt(6, cliente.getNumeroEmpleados());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//2. buscarCliente
	@Override
	public Cliente buscarCliente(String cif) {
		sql = "select * from clientes where cif = ?";
		Cliente cl = null;
		
		try {
			ps = conn.prepareStatement(sql);
			//Como solo hay 1 ?
			ps.setString(1, cif);
			rs = ps.executeQuery();
			if (rs.next()) {
				cl = new Cliente();
				crearObjetoCliente(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cl;
	}

	//3. buscarTodos
	@Override
	public List<Cliente> buscarTodos() {
		sql = "select * from clientes";
		List<Cliente> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cl = new Cliente();;
				crearObjetoCliente(cl);
				lista.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	//4. eliminarCliente
	@Override
	public int eliminarCliente(String cif) {
		sql = "delete from clientes where cif = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cif);
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas;
	}

	//5. modificarCliente
	@Override
	public int modificarCliente(Cliente cliente) {
		sql = "update clientes set nombre = ?, apellidos = ?, domicilio = ?, facturacion_anual = ?, numero_empleados =? "
				+ " where cif = ?";
		try {
			ps = conn.prepareStatement(sql);	
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());
			ps.setString(3, cliente.getDomicilio());
			ps.setDouble(4, cliente.getFacturacionAnual());;
			ps.setInt(5, cliente.getNumeroEmpleados());
			ps.setString(6, cliente.getCif());
			filas = ps.executeUpdate();
			filas=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	//6. buscarPorDomicilio
	@Override
	public List<Cliente> buscarPorDomicilio(String domicilio) {
		sql = "select * from clientes where domicilio = ?";
		List<Cliente> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, domicilio);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cl = new Cliente();
				crearObjetoCliente(cl);			
				lista.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	//7. Metodo para asignar el valor de las columnas al objeto
	private void crearObjetoCliente(Cliente cl) throws SQLException {
		cl.setCif(rs.getString("cif"));
		cl.setNombre(rs.getString("nombre"));
		cl.setApellidos(rs.getString("apellidos"));
		cl.setDomicilio(rs.getString("domicilio"));
		cl.setFacturacionAnual(rs.getDouble("facturacion_anual"));
		cl.setNumeroEmpleados(rs.getInt("numero_empleados"));		
	}
}
