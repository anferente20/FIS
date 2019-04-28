package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorDB {
	
	private static Connection conector;
	private static String nombreBD = "cine_mas";
	private static String url = "jdbc:postgresql://localhost:5432/"+nombreBD;
	private static String usuario = "postgres";
	private static String contrasena = "leo990209";

	
	private static GestorDB gestor;
	//Patrón singleton
	public static GestorDB getInstance() {
		if(gestor==null) {
			gestor = new GestorDB();
		}
		return gestor;
	}
	//Constructor
	private GestorDB() {
		conectarBD();
	}
	//Devuelve el conector con el que se harán todas las operaciones sobre la base de datos
	public static Connection getConector() {
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
			System.out.println("Descripción del error al crear la base de datos: " + sqle.getMessage());
		}

}

}
