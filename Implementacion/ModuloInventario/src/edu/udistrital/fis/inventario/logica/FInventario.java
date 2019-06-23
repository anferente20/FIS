package edu.udistrital.fis.inventario.logica;

import java.util.ArrayList;
import edu.udistrital.fis.api.logica.*;
import edu.udistrital.fis.core.IInventario;
import edu.udistrital.fis.inventario.presentacion.AgregarProducto;
import edu.udistrital.fis.inventario.presentacion.ConsultarInventario;
import edu.udistrital.fis.inventario.presentacion.GestionCombos;
import edu.udistrital.fis.inventario.presentacion.ModificarInventario;


public class FInventario implements IInventario{

	@Override
	public ArrayList<AbstractFrame> getPresentacion(int tipoAdmin, int idCine) {
		ArrayList<AbstractFrame> array = new ArrayList<AbstractFrame>();
		if(tipoAdmin==2) //Super administrador
		{
			array.add(new AgregarProducto());
			array.add(new GestionCombos());
		}
		array.add(new ConsultarInventario(tipoAdmin,idCine));
		array.add(new ModificarInventario(tipoAdmin,idCine));
		
		return array;
	}

}
