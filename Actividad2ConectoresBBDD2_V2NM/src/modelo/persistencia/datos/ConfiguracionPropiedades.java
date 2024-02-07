package modelo.persistencia.datos;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracionPropiedades {
	
	public static Properties properties;
	
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
     * Método que devuelve un valor de una propiedad a partir de la clave
     *
     * @param key la clave de la propiedad
     * @return valor de la propiedad. Null en caso de que no exista.
     */
    public String getProperty(String key) {
    	return properties.getProperty(key);
    		
    }



	
	

}
