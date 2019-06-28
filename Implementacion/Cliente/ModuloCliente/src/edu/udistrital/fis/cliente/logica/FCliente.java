package edu.udistrital.fis.cliente.logica;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.udistrital.fis.api.logica.*;
import edu.udistrital.fis.cliente.presentacion.CambiarContrasena;
import edu.udistrital.fis.cliente.presentacion.CambiarCorreo;
import edu.udistrital.fis.cliente.presentacion.CambiarSuscripcion;
import edu.udistrital.fis.cliente.presentacion.RegistrarCliente;
import edu.udistrital.fis.core.IRegistroCliente;

/**
 * Clase que cargará los frames de empleado
 * @author anferente
 *
 */
public class FCliente implements IRegistroCliente {

	@Override
	public ArrayList<AbstractFrame> getPresentacion(String correo) {
		ArrayList<AbstractFrame>array = new ArrayList<AbstractFrame>();
		array.add(new RegistrarCliente());
		array.add(new CambiarContrasena());
		array.add(new CambiarCorreo());
		System.out.println(correo);
		array.add(new CambiarSuscripcion(correo));
		return array;
	}


}
