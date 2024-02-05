package modelo.negocio;

import java.sql.SQLException;

import modelo.entidad.Coche;
import modelo.entidad.Usuario;
import modelo.persistencia.acceso.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {
	
	private DaoCoche daoCoche = new DaoCocheMySql();
	
	//validar datos introducidos del coche

	/**
	 * Método que valida si los datos introducidos del coche son correctos
	 * 
	 * @param usuario El objeto Coche a ser validado.
	 * @return Entero que indica el resultado de la validación:
	 *         - 0 si la validacion no ha dado ningun problema
	 *         - 1 si la marca está vacia
	 *         - 2 si el modelo está vacio
	 *         - 3 si la marca y el modelo estan vacios
	 */
	public int validarCoche(Coche coche) {
		int resultadoValidacion = 0;
		
		if(coche.getMarca().isEmpty()) {
			resultadoValidacion = 1;
			
		}
		if(coche.getModelo().isEmpty()) {
			resultadoValidacion = 2;
			
		}if(coche.getModelo().isEmpty() && coche.getMarca().isEmpty()) {
			resultadoValidacion = 3;
			
		}
		return resultadoValidacion;
	}
	
	/**
	 * Método que agrega un coche a la persistencia
	 * @param coche a agregar al motor de persistencia
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> si se ha añadido correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	public int altaCoche(Coche coche) {
		int altaCoche = daoCoche.altaCoche(coche);
		return altaCoche;
	}
	
	/**
	 * Método para eliminar un coche por su id
	 * 
	 * @return Entero que indica el resultado de la operación:
	 *         - <b>0</b> no se ha dado de alta ningun coche
	 *         - <b>1</b> se ha eliminado correctamente
	 *         - <b>2</b> si hay un error al establecer la conexion
	 *         - <b>3</b> error de Excepcion
	 */
	@Override
	public int eliminarCoche(int id) {
		int borrarCoche = daoCoche.eliminarCoche(id);
		return borrarCoche;
	}

}
