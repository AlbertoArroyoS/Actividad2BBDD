package testing;

import modelo.dao.PerfilDao;
import modelo.dao.PerfilDaoImplMy8;
import modelo.javabean.Perfil;
/**
 * Clase en la que realizamos los test del PerfilDao.
 * 
 * Probamos:
 * 
 * 1. Buscar todos los perfiles
 * 2. Buscar perfiles por nombre
 * 3. Buscar perfil por numero de perfil
 * 4. Alta perfil
 * 5. Modificar perfil
 * 6. Eliminar perfil
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.0
 * 
 */
public class TestPerfilDao {
	
	private static final String SEPARACION = "-------------------------------------------------------------------------------------------------------------------------";

	public static void main(String[] args) {
		
		PerfilDao pdao = new PerfilDaoImplMy8();		
		
		//1. Buscar todos los perfiles
		
		System.out.println("\n"+SEPARACION);
		System.out.println("1. LISTAR TODOS LOS PERFILES\n");
		for(Perfil ele: pdao.buscarTodos())
			System.out.println(ele);				
		System.out.println(SEPARACION);
		
		//2. Buscar perfiles por nombre
		
		System.out.println("2. MOSTRAR EL PERFIL QUE TIENE DE NOMBRE CONTROL DE GESTION\n");
		for(Perfil ele: pdao.buscarPorNombre("Control de Gestion"))
			System.out.println(ele);		
		System.out.println(SEPARACION);
		
		//3. Buscar perfil por numero de perfil

		System.out.println("3. BUSCAR PERFIL 3\n");
		Perfil cli = pdao.buscarPerfil(3);
			if (cli != null)
				System.out.println(cli);
			else
				System.out.println("PERFIL NO ENCONTRADO\n");				
		System.out.println(SEPARACION);	
		
		//4. Alta perfil
		
		System.out.println("4. AÑADIR PERFIL NUEVO CON NOMBRE INFORMATICA\n");
		Perfil pf = new Perfil(5, "Informatica", 55);
		if (pdao.altaPerfil(pf) == 1) {
			System.out.println("AÑADIDO CORRECTAMENTE\n");
			for(Perfil ele: pdao.buscarTodos())
			System.out.println(ele);	
		}
		else
			System.out.println("NO SE HA AÑADIDO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		//5. Modificar perfil
		
		System.out.println("5. MODIFICAR PERFIL 5 A NOMBRE I+D\n");
		pf.setNombre("I+D");
		if (pdao.modificarPerfil(pf) == 1) {
			System.out.println("MODIFICADO CORRECTAMENTE\n");
			for(Perfil ele: pdao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA MODIFICADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
		
		//6. Eliminar perfil
		
		System.out.println("6. ELIMINAR PERFIL 5\n");
		if (pdao.eliminarPerfil(5) == 1) {
			System.out.println("ELIMINADO CORRECTAMENTE\n");
		System.out.println("LISTAR TODOS");
			for(Perfil ele: pdao.buscarTodos())
				System.out.println(ele);	
			}
		else
			System.out.println("NO SE HA BORRADO CORRECTAMENTE\n");
		System.out.println(SEPARACION);
	}

}
