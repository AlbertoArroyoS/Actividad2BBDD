package modelo.persistencia.datos;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Clase que proporciona métodos para inicializar y acceder a propiedades desde un archivo de configuración.
 *
 * <p>Esta clase utiliza un archivo de propiedades llamado "config.properties" que debe estar dentro del classpath
 * para cargar y acceder a las propiedades necesarias en una aplicación.
 * 
 * @see Properties
 * @author Alberto Arroyo Santofimia
 * @since Fecha de creación de la clase
 */
public class ConfiguracionPropiedades {
	
	public static Properties properties;
	
	/**
     * Inicializa las propiedades cargándolas desde el archivo "config.properties".
     *
     * @return - <b>true</b> si las propiedades se inicializan con éxito.
     *         - <b>false</b> en caso de error al cargar el archivo o IOException.
     */
	public boolean inicializarPropiedades() {
		//Esta manera es cuando queremos acceder a un fichero properties que esta
		//dentro de nuestro "classpath"
		try (InputStream ficheroPropiedades = ConfiguracionPropiedades.class.getClassLoader()
				.getResourceAsStream("config.properties");){
			// Cargamos el properties
			// tiene que estar dentro de una ruta de classpath
			properties = new Properties();
			//Cargamos en el objeto properties, todos los valores del fichero
			properties.load(ficheroPropiedades);
			
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	
	/**
     * Método que devuelve un valor de una propiedad a partir de la clave.
     *
     * @param key La clave de la propiedad.
     * @return Valor de la propiedad. Null en caso de que no exista.
     */
    public String getProperty(String key) {
    	return properties.getProperty(key);
    		
    }


}
