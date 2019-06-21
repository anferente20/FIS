package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JPanel;

abstract interface PanelFuncion{

	abstract void setModificada(String hora);
	abstract void setModificada(String hora,int sala,int idCine);
}
