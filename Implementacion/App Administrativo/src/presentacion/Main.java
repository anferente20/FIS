package presentacion;

import java.awt.EventQueue;

import persistencia.GestorDB;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					GestorDB.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
