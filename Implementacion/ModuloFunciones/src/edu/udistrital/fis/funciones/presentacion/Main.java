package edu.udistrital.fis.funciones.presentacion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.udistrital.fis.basicos.logica.Cine;
import edu.udistrital.fis.basicos.presentacion.ProgressBar;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Estilos que tenga el sistema operativo
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		AgregarPelicula x = new AgregarPelicula();
		x.setVisible(true);
		
		
	}
}
