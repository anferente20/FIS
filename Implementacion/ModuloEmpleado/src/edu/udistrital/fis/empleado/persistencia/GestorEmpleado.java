package edu.udistrital.fis.empleado.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.empleado.logica.Empleado;
import edu.udistrital.fis.basicos.persistencia.Gestor;
/**
 * Clase que se comunicará directamente con la base de datos para el manejo de información relacionada a empleados
 * @author Andrés Arias
 */
public class GestorEmpleado extends Gestor{
	
	/**
	 * @throws SQLException
	 */
	protected GestorEmpleado() throws SQLException {
		super();
	}
	/**
	 * Método que inserta un nuevo empleado a la base de datos
	 * @param empleado Objeto Empleado
	 * @throws SQLException
	 */
	void insertarEmpleado(Empleado empleado) throws SQLException {
		String consulta = "insert into Empleado (idEmpleado,nombreEmpleado,apellidoEmpleado,identificacionEmpleado,idCine,estado,idTipoEmpleado) values"
				+ "(?,?,?,?,?,1,2);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, empleado.getIdEmpleado());
		sentencia.setString(2, empleado.getNombresEmpleado());
		sentencia.setString(3, empleado.getApellidosEmpleados());
		sentencia.setInt(4, empleado.getIdentificacionEmpl());
		sentencia.setInt(5, empleado.getIdCine());
		sentencia.execute();
	}
	/**
	 * Método que sugiere registros de empleados dado su nombre o su apellido
	 * @param parametro Parámetro (nombre o apellido) del empleado 
	 * @throws SQLException
	 * @return Resultado de la consulta
	 */
	ResultSet autoCompletarEmpleado(String parametro) throws SQLException {
		ResultSet empleados = null;
		String consulta = "select idEmpleado || ' - '|| nombreEmpleado || ' ' || apellidoEmpleado from Empleado where lower(nombreEmpleado) like ? || '%' or lower(apellidoEmpleado) like "
				+ "? || '%' and estado = 1;";
		
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, parametro.toLowerCase());
		sentencia.setString(2, parametro.toLowerCase());
		empleados = sentencia.executeQuery();
		return empleados;
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación, mostrando su ID y sus nombres
	 * @param parametro Parámetro (ID o Identificación) del empleado 
	 * @return Resultado de la consulta
	 */
	ResultSet autoCompletarEmpleado(int id) throws SQLException {
		ResultSet empleados = null;
		String consulta = "select idEmpleado || ' - '|| nombreEmpleado || ' ' || apellidoEmpleado from Empleado where CAST(idEmpleado as text) like ? || '%' or "
				+ "CAST(identificacionEmpleado as text) like ? || '%'  and estado = 1;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1,String.valueOf(id));
		sentencia.setString(2,String.valueOf(id));
		empleados = sentencia.executeQuery();

		return empleados;
	}
	/**
	 * Método que consulta un empleado dado su ID
	 * @param id ID del empleado 
	 * @return Resultado de la consulta
	 */
	ResultSet consultarEmpleadoByID(int id) throws SQLException {
		String consulta = "select empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, empleado.identificacionEmpleado as identificacion,"
				+ "Cine.nombreCine as cine from Empleado, Cine where  Empleado.idEmpleado = ? and Empleado.idCine = Cine.idCine  and estado = 1";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, id);
		return sentencia.executeQuery();	
	}
	/**
	 * Método que actualiza la información de un empleado
	 * @param empleado Objeto que contiene la nueva información del empleado
	 */
	void actualizarEmpleado(Empleado empleado) throws SQLException {
		String consulta = "update Empleado set nombreEmpleado = ?, apellidoEmpleado = ?, identificacionEmpleado = ? , idCine = ? where idEmpleado = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, empleado.getNombresEmpleado());
		sentencia.setString(2, empleado.getApellidosEmpleados());
		sentencia.setInt(3, empleado.getIdentificacionEmpl());
		sentencia.setInt(4, empleado.getIdCine());
		sentencia.setInt(5, empleado.getIdEmpleado());
		sentencia.execute();	
	}
	/**
	 * Método que consulta todos los empleados registrados en un cine
	 * @param idCine ID del cine 
	 * @return Resultado de la consulta
	 */
	ResultSet consultarEmpleadoByCine(int idCine) throws SQLException {
		String consulta = "select empleado.idEmpleado as id , empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, empleado.identificacionEmpleado as identificacion,\r\n" + 
				"				tipoEmpleado.descripcion as tipoEmpleado from empleado, tipoEmpleado" + 
				"				where empleado.idTipoEmpleado = tipoEmpleado.idTipoEmpleado and empleado.idCine = ? order by empleado.idTipoEmpleado asc;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCine);
		return sentencia.executeQuery();	
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación
	 * @param parametro Parámetro (ID o Identificación) del empleado 
	 * @return Resultado de la consulta
	 */
	ResultSet sugerirEmpleados(String parametro) throws SQLException {
		String consulta = "select empleado.idEmpleado as id, empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, identificacionEmpleado\r\n" + 
				"				as identificacion, cine.nombreCine as nombreCine from empleado, cine where empleado.estado = 1 and\r\n" + 
				"				empleado.idTipoEmpleado = 2 and empleado.idCine = cine.idCine and (lower(empleado.nombreEmpleado) like ? || '%'  or\r\n" + 
				"				lower(empleado.apellidoEmpleado) like ? || '%' or  lower(cine.nombreCine) like 'cine+ ' || ? || '%');";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, parametro);
		sentencia.setString(2, parametro);
		sentencia.setString(3, parametro);
		return sentencia.executeQuery();
	}
	/**
	 * Método que sugiere registros de empleados dado su ID o su identificación, toda su información dado su ID
	 * @param id ID del empleado
	 * @return Resultado de la consulta
	 */
	ResultSet sugerirEmpleados(int idEmpleado) throws SQLException {
		String consulta = "select empleado.idEmpleado as id, empleado.nombreEmpleado as nombres, empleado.apellidoEmpleado as apellidos, identificacionEmpleado\r\n" + 
				"				as identificacion, cine.nombreCine as nombreCine from empleado, cine where empleado.estado = 1 and \r\n" + 
				"				empleado.idTipoEmpleado = 2 and empleado.idCine = cine.idCine and CAST(empleado.idEmpleado as text) like ? || '%'  or\r\n" + 
				"				CAST(empleado.identificacionEmpleado as text) like ? || '%';";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, String.valueOf(idEmpleado));
		sentencia.setString(2, String.valueOf(idEmpleado));
		return sentencia.executeQuery();
	}
	/**
	 * Método que da de baja a un empleado en la base de datos
	 * @param idEmpleado Parámetro (ID o Identificación) del empleado 
	 */
	void darBajaEmpleado(int idEmpleado) throws SQLException {
		String consulta = "update empleado set estado = 0 where idEmpleado = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idEmpleado);
		sentencia.execute();
	}
	/**
	 * Método consulta todos los empleados que son administradores
	 * @return Resultado de la consulta
	 */
	ResultSet consultarAdmins() throws SQLException {
		String consulta = "select empleado.idEmpleado || ' - ' || empleado.nombreEmpleado || ' ' || empleado.apellidoEmpleado as adminInfo, cine.nombreCine as cine \r\n" + 
				"from (usuario inner join empleado on empleado.idEmpleado = usuario.idEmpleado) inner join cine on cine.idCine = usuario.idCineEncargado order by cine.idCine;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
}
