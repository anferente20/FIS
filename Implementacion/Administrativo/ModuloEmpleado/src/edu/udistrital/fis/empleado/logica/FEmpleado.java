package edu.udistrital.fis.empleado.logica;


import java.util.ArrayList;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.core.IEmpleados;
import edu.udistrital.fis.empleado.presentencion.*;

public class FEmpleado implements IEmpleados{

	@Override
	public ArrayList<AbstractFrame> getPresentacion(int tipoAdmin, int idCine) {
		ArrayList<AbstractFrame> array = new ArrayList<AbstractFrame>();
		if(tipoAdmin==2) { //Super usuario
			array.add(new AgregarEmpleado());
			array.add(new DarBajaEmpl());
			array.add(new ModificarEmpleado());
		}
		array.add(new ConsultarEmplByCine(tipoAdmin,idCine));
		return array;
	}

}
