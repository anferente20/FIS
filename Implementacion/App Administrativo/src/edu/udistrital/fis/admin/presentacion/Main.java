package edu.udistrital.fis.admin.presentacion;


import java.sql.SQLException;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Class.forName("edu.udistrital.fis.basicos.persistencia.GestorDB");
			GestorDB.getInstance();
			Login frame = new Login();
			frame.setVisible(true);
		} 
		catch (SQLException e) {
			Funciones.mensajeConsola(("Descripción del error: "+e.getMessage()));
			Funciones.mensajePantalla("Error, No fue posible conectarse a la base de datos");
		}
		catch(ClassNotFoundException e) {
			Funciones.mensajeConsola(("Clase no encontrada: "+e.getMessage()));
			Funciones.mensajePantalla("Error, No fue posible conectarse a la base de datos");
		}
	}
}