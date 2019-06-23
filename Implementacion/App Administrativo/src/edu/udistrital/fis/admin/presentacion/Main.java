package edu.udistrital.fis.admin.presentacion;


import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.udistrital.fis.basicos.logica.Funciones;
import java.util.ArrayList;
import java.util.HashMap;
import edu.udistrital.fis.admin.utilidades.*;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.core.*;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Estilos que tenga el sistema operativo
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Login x = new Login();
		x.setVisible(true);
	}
}