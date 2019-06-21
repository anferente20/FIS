package edu.udistrital.fis.empleado.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.empleado.logica.Empleado;
/**
 * Clase que har� de fachada para la gestion de informaci�n relacionada con empleados
 *  @author Andr�s Arias
 */
public class FachadaEmpleado{
	
	private GestorEmpleado gestor;
	private static FachadaEmpleado instance;
	
	/**
	 * @throws SQLException
	 */
	private FachadaEmpleado() throws SQLException{
		this.gestor = new GestorEmpleado();
	}
	
	/**
	 * M�todo para singlet�n
	 * @return Instancia �nica de la clase
	 * @throws SQLException
	 */
	public static FachadaEmpleado getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaEmpleado();
		}
		return instance;
	}
	/**
	 * M�todo que inserta un nuevo empleado a la base de datos
	 * @param empleado Objeto Empleado
	 * @throws SQLException
	 */
	public void insertarEmpleado(Empleado empleado) throws SQLException {
		gestor.insertarEmpleado(empleado);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su nombre o su apellido
	 * @param parametro Par�metro (nombre o apellido) del empleado 
	 * @throws SQLException
	 * @return Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(String parametro) throws SQLException{
		 return gestor.autoCompletarEmpleado(parametro);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su ID o su identificaci�n, mostrando su ID y sus nombres
	 * @param id Par�metro (ID o Identificaci�n) del empleado 
	 * @return Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(int id) throws SQLException{
		return gestor.autoCompletarEmpleado(id);
	}
	/**
	 * M�todo que consulta un empleado dado su ID
	 * @param id ID del empleado 
	 * @return Resultado de la consulta
	 */
	public ResultSet consultarEmpleadoByID(int id) throws SQLException {
		return gestor.consultarEmpleadoByID(id);
	}
	/**
	 * M�todo que actualiza la informaci�n de un empleado
	 * @param empleado Objeto que contiene la nueva informaci�n del empleado
	 */
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		gestor.actualizarEmpleado(empleado);
	}
	/**
	 * M�todo que consulta todos los empleados registrados en un cine
	 * @param idCine ID del cine 
	 * @return Resultado de la consulta
	 */
	public ResultSet consultarEmplByCine(int idCine) throws SQLException {
		return gestor.consultarEmpleadoByCine(idCine);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su ID o su identificaci�n
	 * @param parametro Par�metro (ID o Identificaci�n) del empleado 
	 * @return Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(String parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su ID o su identificaci�n
	 * @param parametro ID del empleado
	 * @return Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(int parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * M�todo que da de baja a un empleado en la base de datos
	 * @param idEmpleado Par�metro (ID o Identificaci�n) del empleado 
	 */
	public void darBajaEmpl(int idEmpleado) throws SQLException {
		gestor.darBajaEmpleado(idEmpleado);
	}
	/**
	 * M�todo consulta todos los empleados que son administradores
	 * @return Resultado de la consulta
	 */
	public ResultSet consultarAdmins() throws SQLException {
		return gestor.consultarAdmins();
	}

}