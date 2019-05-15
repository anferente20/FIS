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
	 * Método para singletón
	 * @return FachadaEmpleado instancia de la clase
	 */
	public static FachadaEmpleado getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaEmpleado();
		}
		return instance;
	}
	/**
	 * Método que inserta un nuevo empleado a la base de datos
	 * @param empleado Objeto Empleado
	 * @throws SQLException
	 * @return void
	 */
	public void insertarEmpleado(Empleado empleado) throws SQLException {
		gestor.insertarEmpleado(empleado);
	}
	/**
	 * Método que sugiere registros de empleados dado su nombre o su apellido
	 * @param String parametro Parámetro (nombre o apellido) del empleado 
	 * @throws SQLException
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(String nombreApellido) throws SQLException{
		 return gestor.autoCompletarEmpleado(nombreApellido);
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación, mostrando su ID y sus nombres
	 * @param int parametro Parámetro (ID o Identificación) del empleado 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(int id) throws SQLException{
		return gestor.autoCompletarEmpleado(id);
	}
	/**
	 * Método que consulta un empleado dado su ID
	 * @param int id ID del empleado 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet consultarEmpleadoByID(int id) throws SQLException {
		return gestor.consultarEmpleadoByID(id);
	}
	/**
	 * Método que actualiza la información de un empleado
	 * @param Empleado empleado Objeto que contiene la nueva información del empleado
	 * @return void
	 */
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		gestor.actualizarEmpleado(empleado);
	}
	/**
	 * Método que consulta todos los empleados registrados en un cine
	 * @param int idCine ID del cine 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet consultarEmplByCine(int idCine) throws SQLException {
		return gestor.consultarEmpleadoByCine(idCine);
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación
	 * @param int parametro Parámetro (ID o Identificación) del empleado 
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(String parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación, toda su información dado su ID
	 * @param int id ID del empleado
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(int parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * Método que da de baja a un empleado en la base de datos
	 * @param int idEmpleado Parámetro (ID o Identificación) del empleado 
	 * @return void
	 */
	public void darBajaEmpl(int idEmpleado) throws SQLException {
		gestor.darBajaEmpleado(idEmpleado);
	}
	/**
	 * Método consulta todos los empleados que son administradores
	 * @return ResultSet Resultado de la consulta
	 */
	public ResultSet consultarAdmins() throws SQLException {
		return gestor.consultarAdmins();
	}

}