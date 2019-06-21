package edu.udistrital.fis.funciones.logica;

import java.util.ArrayList;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.core.IFunciones;
import edu.udistrital.fis.funciones.presentacion.AgregarPelicula;
import edu.udistrital.fis.funciones.presentacion.ConsultarFuncionesByPelicula;
import edu.udistrital.fis.funciones.presentacion.ConsultarFuncionesBySala;

public class FFunciones implements IFunciones{

	@Override
	public ArrayList<AbstractFrame> getPresentacion() {
		ArrayList<AbstractFrame> array = new ArrayList<AbstractFrame>();
		array.add(new AgregarPelicula());
		array.add(new ConsultarFuncionesByPelicula());
		array.add(new ConsultarFuncionesBySala());
		return array;
	}
	
}
