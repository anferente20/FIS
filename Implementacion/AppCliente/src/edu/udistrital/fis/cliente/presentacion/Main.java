package edu.udistrital.fis.cliente.presentacion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.udistrital.fis.cliente.utilidades.Componentes;


public class Main {

	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Estilos que tenga el sistema operativo
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Componentes comp = new Componentes();
		MenuCliente men = new MenuCliente(comp.getPresentacion());
		men.setVisible(true);
//		Login x = new Login();
//		x.setVisible(true);
	}
	
	
}
