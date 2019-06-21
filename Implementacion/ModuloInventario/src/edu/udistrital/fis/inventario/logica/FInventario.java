package edu.udistrital.fis.inventario.logica;

import java.util.ArrayList;
import edu.udistrital.fis.api.logica.*;
import edu.udistrital.fis.core.IInventario;
import edu.udistrital.fis.inventario.presentacion.AgregarProducto;
import edu.udistrital.fis.inventario.presentacion.ConsultarInventario;
import edu.udistrital.fis.inventario.presentacion.ModificarInventario;


public class FInventario implements IInventario{

	@Override
	public ArrayList<AbstractFrame> getPresentacion() {
		ArrayList<AbstractFrame> array = new ArrayList<AbstractFrame>();
		array.add(new AgregarProducto());
		array.add(new ConsultarInventario());
		array.add(new ModificarInventario());
		return array;
	}

}
