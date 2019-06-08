package edu.udistrital.fis.empleado.logica;


import java.util.ArrayList;
import edu.udistrital.fis.basicos.presentacion.AbstractFrame;
import edu.udistrital.fis.core.IEmpleados;
import edu.udistrital.fis.empleado.presentencion.*;

public class FEmpleado implements IEmpleados{

	@Override
	public ArrayList<AbstractFrame> getPresentacion() {
		ArrayList<AbstractFrame> array = new ArrayList<AbstractFrame>();
		array.add(new AgregarEmpleado());
		array.add(new ConsultarEmplByCine());
		array.add(new DarBajaEmpl());
		array.add(new ModificarEmpleado());
		return array;
	}

}
