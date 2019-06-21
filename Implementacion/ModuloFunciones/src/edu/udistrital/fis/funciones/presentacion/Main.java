package edu.udistrital.fis.funciones.presentacion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Estilos que tenga el sistema operativo
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		AgregarPelicula x1 = new AgregarPelicula();
		x1.setVisible(true);
		ConsultarFuncionesBySala x = new ConsultarFuncionesBySala();
		x.setVisible(true);
	}
}
