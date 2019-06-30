package edu.udistrital.fis.basicos.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Clase que muestra el tipo de suscripciones que se tienen en la base de datos
 * @author anferente
 *
 */
public class GestorSuscripcion extends Gestor{

	protected GestorSuscripcion() throws SQLException {
		super();
	}

	/**
	 * Método que retorna los tipos de suscripciones que existe
	 * @return ResultSet con los tipos de suscripciones existentes
	 * @throws SQLException Si existe algún error para conectarse a la base de datos
	 */
	public ResultSet getSuscripciones() throws SQLException {
		String consulta = "select nombre from tiposuscripcion";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		ResultSet resultado = sentencia.executeQuery();
		return resultado;
	}
	
	/**
	 * Método que retorna las descripciones que existe
	 * @return ResultSet con los tipos de suscripciones existentes
	 * @throws SQLException Si existe algún error para conectarse a la base de datos
	 */
	public ResultSet getDescripciones() throws SQLException {
		String consulta = "select descripcion from tiposuscripcion";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		ResultSet resultado = sentencia.executeQuery();
		return resultado;
	}
	
	/**
	 * Método que busca el tipo de suscripcion de un cliente por su correo
	 * @param correo Correo del cliente 
	 * @return ResultSet con el tipo de correo
	 * @throws SQLException Si hay error para conectar a la base de datos
	 */
	ResultSet getSuscripcion(String correo) throws SQLException {
		String consulta = "select ts.nombre from cliente cli, tiposuscripcion ts where cli.correo = ?"
				+ " and cli.tiposuscripcion = ts.idsuscripcion";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1,correo);
		ResultSet resultado = sentencia.executeQuery();
		return resultado;
	}
}
