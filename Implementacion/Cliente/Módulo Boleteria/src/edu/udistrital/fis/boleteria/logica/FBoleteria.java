package edu.udistrital.fis.boleteria.logica;

import java.util.ArrayList;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.boleteria.presentacion.SeleccionPelicula;
import edu.udistrital.fis.boleteria.presentacion.VerCompras;
import edu.udistrital.fis.core.IBoleteria;

public class FBoleteria implements IBoleteria{

	@Override
	public ArrayList<AbstractFrame> getPresetacion(int idCliente) {
		ArrayList<AbstractFrame> presentacion = new ArrayList<AbstractFrame>();
		presentacion.add(new SeleccionPelicula(idCliente));
		presentacion.add(new VerCompras(idCliente));
		return presentacion;
	}

}
