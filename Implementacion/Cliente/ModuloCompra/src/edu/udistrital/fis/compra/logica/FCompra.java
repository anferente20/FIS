package edu.udistrital.fis.compra.logica;

import java.util.ArrayList;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.core.ICompra;
import edu.udistrital.fis.presentacion.MuestraCombos;

public class FCompra implements ICompra{

	@Override
	public ArrayList<AbstractFrame> getPresentacion(String correo) {
		ArrayList<AbstractFrame>array = new ArrayList<AbstractFrame>();
		array.add(new MuestraCombos(correo));
		return array;
	}

}
