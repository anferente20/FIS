package edu.udistrital.fis.core;

import java.util.ArrayList;

import javax.swing.JFrame;

import edu.udistrital.fis.api.logica.AbstractFrame;

public interface IRegistroCliente {
	public ArrayList<AbstractFrame> getPresentacion(String correo);
	public JFrame getVentanaRegistro();
}
