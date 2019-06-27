package edu.udistrital.fis.empleado.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.empleado.logica.Empleado;
/**
 * Clase que hará de fachada para la gestion de información relacionada con empleados
 *  @author Andrés Arias
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
	 * Método para singletón
	 * @return Instancia única de la clase
	 * @throws SQLException
	 */
	public static FachadaEmpleado getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaEmpleado();
		}
		return instance;
	}
	/**
	 * Método que inserta un nuevo empleado a la base de datos
	 * @param empleado Objeto Empleado
	 * @throws SQLException
	 */
	public void insertarEmpleado(Empleado empleado) throws SQLException {
		gestor.insertarEmpleado(empleado);
	}
	/**
	 * Método que sugiere registros de empleados dado su nombre o su apellido
	 * @param parametro Parámetro (nombre o apellido) del empleado 
	 * @throws SQLException
	 * @return Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(String parametro) throws SQLException{
		 return gestor.autoCompletarEmpleado(parametro);
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación, mostrando su ID y sus nombres
	 * @param id Parámetro (ID o Identificación) del empleado 
	 * @return Resultado de la consulta
	 */
	public ResultSet autoCompletarEmpleado(int id) throws SQLException{
		return gestor.autoCompletarEmpleado(id);
	}
	/**
	 * Método que consulta un empleado dado su ID
	 * @param id ID del empleado 
	 * @return Resultado de la consulta
	 */
	public ResultSet consultarEmpleadoByID(int id) throws SQLException {
		return gestor.consultarEmpleadoByID(id);
	}
	/**
	 * Método que actualiza la información de un empleado
	 * @param empleado Objeto que contiene la nueva información del empleado
	 */
	public void actualizarEmpleado(Empleado empleado) throws SQLException {
		gestor.actualizarEmpleado(empleado);
	}
	/**
	 * Método que consulta todos los empleados registrados en un cine
	 * @param idCine ID del cine 
	 * @return Resultado de la consulta
	 */
	public ResultSet consultarEmplByCine(int idCine) throws SQLException {
		return gestor.consultarEmpleadoByCine(idCine);
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación
	 * @param parametro Parámetro (ID o Identificación) del empleado 
	 * @return Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(String parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación
	 * @param parametro ID del empleado
	 * @return Resultado de la consulta
	 */
	public ResultSet sugerirEmpleados(int parametro) throws SQLException {
		return gestor.sugerirEmpleados(parametro);
	}
	/**
	 * Método que da de baja a un empleado en la base de datos
	 * @param idEmpleado Parámetro (ID o Identificación) del empleado 
	 */
	public void darBajaEmpl(int idEmpleado) throws SQLException {
		gestor.darBajaEmpleado(idEmpleado);
	}
	/**
	 * Método consulta todos los empleados que son administradores
	 * @return Resultado de la consulta
	 */
	public ResultSet consultarAdmins() throws SQLException {
		return gestor.consultarAdmins();
	}

}