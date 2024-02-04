package modelo.entidad;

public class Usuario {
	//Atributos privados
	private String nombre;
	private String password;
	private int edad;
	
	//constructor por defecto
	public Usuario() {
		super();
	}
	
	//getter y setters
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	//toString
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + ", edad=" + edad + "]";
	}
	
	


	
	
	
	
}
