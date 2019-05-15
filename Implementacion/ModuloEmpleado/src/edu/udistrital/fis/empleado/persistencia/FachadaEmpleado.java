package edu.udistrital.fis.empleado.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.empleado.logica.Empleado;

public class FachadaEmpleado{
	
	private GestorEmpleado gestor;
	private static FachadaEmpleado instance;
	
	//constructor
	private FachadaEmpleado() throws SQLException {
		this.gestor = new GestorEmpleado();
	}
	
	/**
	 * M�todo para singlet�n
	 * @return FachadaEmpleado instancia de la clase
	 */
	public static FachadaEmpleado getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaEmpleado();
		}
		return instance;
	}
	/**
	 * M�todo que inserta un nuevo empleado a la base de datos
	 * @param empleado Objeto Empleado
	 * @throws SQLException
	 * @return void
	 */
	public void insertarEmpleado(Empleado empleado) throws SQLException {
		gestor.insertarEmpleado(empleado);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su nombre o su apellido
	 * @param String parametro Par�metro (nombre o apellido) del empleado 
	 * @throws SQLException
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(String nombreApellido) throws SQLException{
		 return gestor.autoCompletarEmpleado(nombreApellido);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su ID o su identificaci�n, mostrando su ID y sus nombres
	 * @param int parametro Par�metro (ID o Identificaci�n) del empleado 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(int id) throws SQLException{
		return gestor.autoCompletarEmpleado(id);
	}
	/**
	 * M�todo que consulta un empleado dado su ID
	 * @param int id ID del empleado 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet consultarEmpleadoByID(int id) throws SQLException {
		return gestor.consultarEmpleadoByID(id);
	}
	/**
	 * M�todo que actualiza la informaci�n de un empleado
	 * @param Empleado empleado Objeto que contiene la nueva informaci�n del empleado
	 * @return void
	 */
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		gestor.actualizarEmpleado(empleado);
	}
	/**
	 * M�todo que consulta todos los empleados registrados en un cine
	 * @param int idCine ID del cine 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet consultarEmplByCine(int idCine) throws SQLException {
		return gestor.consultarEmpleadoByCine(idCine);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su ID o su identificaci�n
	 * @param int parametro Par�metro (ID o Identificaci�n) del empleado 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(String parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * M�todo que sugiere registros de empleados dado su ID o su identificaci�n, toda su informaci�n dado su ID
	 * @param int id ID del empleado
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(int parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * M�todo que da de baja a un empleado en la base de datos
	 * @param int idEmpleado Par�metro (ID o Identificaci�n) del empleado 
	 * @return void
	 */
	public void darBajaEmpl(int idEmpleado) throws SQLException {
		gestor.darBajaEmpleado(idEmpleado);
	}
	/**
	 * M�todo consulta todos los empleados que son administradores
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet consultarAdmins() throws SQLException {
		return gestor.consultarAdmins();
	}

}