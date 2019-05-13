
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class GestorDB {
	
	private Connection conector;
	private static String nombreBD = "cine_mas";
	private static String url = "jdbc:postgresql://localhost:5433/"+nombreBD;
	private static String usuario = "postgres";
	private static String contrasena = "leo990209";

	
	private static GestorDB gestor;
	//Patrón singleton
	public static GestorDB getInstance() throws SQLException {
		if(gestor==null) {
			gestor = new GestorDB();
		}
		return gestor;
	}
	//Constructor
	private GestorDB() throws SQLException {
		conectarBD();
	}
	//Devuelve el conector con el que se harán todas las operaciones sobre la base de datos
	public Connection getConector() {
		return conector;
	}
	//Se conecta  la base de datos instanciando al 'conector'
	public void conectarBD() {
		try {
			Class.forName("org.postgresql.Driver");
			conector = DriverManager.getConnection(url, usuario, contrasena);
		} catch (ClassNotFoundException cnf) {
			System.out.println("Clase no encontrada: " + cnf.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Descripci�n del error al conectarse a la base de datos: " + sqle.getMessage());
		}
	}
}