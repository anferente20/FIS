
package edu.udistrital.fis.basicos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.udistrital.fis.basicos.logica.Funciones;

/**
 * Clase que establece la conexi�n con la base de datos
 * @author Andres Arias
 *
 */
public class GestorDB {
	
	private Connection conector;
	private static String nombreBD = "cine_mas";
	private static String url = "";
	private static String usuario = "";
	private static String contrasena = "";
	private static String puerto="";

	
	private static GestorDB gestor;
	/**
	 * Patr�n singleton
	 * @return GestorDB instancia de la clase
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static GestorDB getInstance() throws SQLException{
		if(gestor==null) {
			gestor = new GestorDB();
		}
		return gestor;
	}

	//Constructor
	private GestorDB() throws SQLException{
		setCredenciales();
		conectarBD();
	}
	/**
	 * M�todo que recibe las credenciales para conectarse a la base de datos
	 * @return void
	 */
	private static void setCredenciales() {
		String direccion = Funciones.mensajeInPut("Digite la direcci�n IP del servidor");
		usuario = Funciones.mensajeInPut("Digite el usuario PostgreSQL");
		contrasena = Funciones.mensajeInPut("Digite la contrase�a");
		puerto = Funciones.mensajeInPut("Digite el puerto");
		url = "jdbc:postgresql://"+direccion+":"+puerto+"/"+nombreBD;
		
	}
	//Devuelve el conector con el que se harán todas las operaciones sobre la base de datos
	public Connection getConector() {
		return conector;
	}
	/**
	 * M�todo que conecta con la base de datos
	 * @return void
	 */
	private void conectarBD(){
		try {
			Class.forName("org.postgresql.Driver");
			conector = DriverManager.getConnection(url, usuario, contrasena);
		} catch (ClassNotFoundException e) {
			System.out.println("Clase no encontrada: "+e.getMessage());
		}
		catch(SQLException e) {
			System.out.println("Error al conectarse a la base de datos: "+e.getMessage());
		}
		
	}
}