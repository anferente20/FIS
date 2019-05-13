package presentacion;

import java.awt.EventQueue;
import java.sql.SQLException;

import persistencia.GestorDB;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestorDB.getInstance();
			Login frame = new Login();
			frame.setVisible(true);
		} catch (SQLException e) {
			System.out.println("Conexión a la base de datos:"+e.getMessage());
		}
	}
}