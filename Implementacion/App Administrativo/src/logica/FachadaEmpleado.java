package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import persistencia.GestorEmpleado;

public class FachadaEmpleado{
	
	private GestorEmpleado gestorE;
	private static FachadaEmpleado instance;
	
	//constructor
	private FachadaEmpleado() throws SQLException {
		this.gestorE = new GestorEmpleado();
	}
	
	//singleton
	public static FachadaEmpleado getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaEmpleado();
		}
		return instance;
	}

	public void insertarEmpleado(Empleado empleado) throws SQLException {
		gestorE.insertarEmpleado(empleado);
	}
	
	public ResultSet autoCompletarEmpleado(String nombreApellido) throws SQLException{
		 return gestorE.autoCompletarEmpleado(nombreApellido);
	}
	
	public ResultSet autoCompletarEmpleado(int id) throws SQLException{
		return gestorE.autoCompletarEmpleado(id);
	}
	
	public ResultSet consultarEmpleadoByID(int id) throws SQLException {
		return gestorE.consultarEmpleadoByID(id);
	}
	
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		gestorE.actualizarEmpleado(empleado);
	}
	
	public ResultSet consultarEmplByCine(int idCine) throws SQLException {
		return gestorE.consultarEmpleadoByCine(idCine);
	}
	
	public ResultSet sugerirEmpleados(String parametro) throws SQLException {
		return gestorE.sugerirEmpleados(parametro);
	}
	public ResultSet sugerirEmpleados(int parametro) throws SQLException {
		return gestorE.sugerirEmpleados(parametro);
	}
	public void darBajaEmpl(int idEmpleado) throws SQLException {
		gestorE.darBajaEmpleado(idEmpleado);
	}

}
