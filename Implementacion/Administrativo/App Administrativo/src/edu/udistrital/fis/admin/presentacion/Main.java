package edu.udistrital.fis.admin.presentacion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Estilos que tenga el sistema operativo
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Login x = new Login();
		x.setVisible(true);
	}
}