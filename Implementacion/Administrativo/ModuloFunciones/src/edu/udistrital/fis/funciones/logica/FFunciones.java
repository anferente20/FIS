package edu.udistrital.fis.funciones.logica;

import java.util.ArrayList;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.core.IFunciones;
import edu.udistrital.fis.funciones.presentacion.AgregarPelicula;
import edu.udistrital.fis.funciones.presentacion.ConsultarFuncionesByPelicula;
import edu.udistrital.fis.funciones.presentacion.ConsultarFuncionesBySala;
import edu.udistrital.fis.funciones.presentacion.PreciosBoleteria;

public class FFunciones implements IFunciones{

	@Override
	public ArrayList<AbstractFrame> getPresentacion(int tipoAdmin, int idCine) {
		ArrayList<AbstractFrame> array = new ArrayList<AbstractFrame>();
		if(tipoAdmin==2) { //Super administrador
			array.add(new AgregarPelicula());
			array.add(new PreciosBoleteria());
		}
		array.add(new ConsultarFuncionesByPelicula(tipoAdmin,idCine));
		array.add(new ConsultarFuncionesBySala(tipoAdmin,idCine));
		return array;
	}
	
}
