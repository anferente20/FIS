package edu.udistrital.fis.cliente.logica;

import java.util.ArrayList;

import edu.udistrital.fis.api.logica.*;
import edu.udistrital.fis.cliente.presentacion.CambiarContrasena;
import edu.udistrital.fis.cliente.presentacion.CambiarCorreo;
import edu.udistrital.fis.cliente.presentacion.CambiarSuscripcion;
import edu.udistrital.fis.cliente.presentacion.IngresoCliente;
import edu.udistrital.fis.cliente.presentacion.RegistrarCliente;
import edu.udistrital.fis.core.IRegistroCliente;

/**
 * Clase que cargará los frames de empleado
 * @author anferente
 *
 */
public class FCliente implements IRegistroCliente {

	@Override
	public ArrayList<AbstractFrame> getPresentacion() {
		ArrayList<AbstractFrame>array = new ArrayList<AbstractFrame>();
		array.add(new IngresoCliente());
		array.add(new RegistrarCliente());
		array.add(new CambiarContrasena());
		array.add(new CambiarCorreo());
		array.add(new CambiarSuscripcion());
		return array;
	}


}
