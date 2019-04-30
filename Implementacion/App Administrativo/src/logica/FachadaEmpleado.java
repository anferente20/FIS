package logica;

import java.sql.SQLException;

import persistencia.GestorEmpleado;

public class FachadaEmpleado{
	
	public static void insertarEmpleado(Empleado empleado) throws SQLException {
		GestorEmpleado gestorE = new GestorEmpleado();
		gestorE.insertarEmpleado(empleado);
	}

}
