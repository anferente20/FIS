package edu.udistrital.fis.core;

import java.util.ArrayList;
import edu.udistrital.fis.api.logica.AbstractFrame;

public interface IFunciones {
	public ArrayList<AbstractFrame> getPresentacion(int tipoAdmin, int idCine);
}
