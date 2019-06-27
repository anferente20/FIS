package edu.udistrital.fis.basicos.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Fachada para las suscripciones
 * @author anferente
 *
 */
public class FachadaSuscripcion {
	private GestorSuscripcion gestor;
	private static FachadaSuscripcion instance;
	
	private FachadaSuscripcion() throws SQLException{
		this.gestor = new GestorSuscripcion();
	}
	/**
	 * M�todo singleton
	 * @return Instancia �nica de la clase
	 * @throws SQLException Exception SQL
	 */
	public static FachadaSuscripcion getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaSuscripcion();
		}
		return instance;
	}
	/**
	 * M�todo que retorna los tipos de suscripciones que existe
	 * @return ResultSet con los tipos de suscripciones existentes
	 * @throws SQLException Si existe alg�n error para conectarse a la base de datos
	 */
	public ResultSet getTipos() throws SQLException {
		return gestor.getSuscripciones();
	}
	
	/**
	 * M�todo que retorna las descripciones que existe
	 * @return ResultSet con los tipos de suscripciones existentes
	 * @throws SQLException Si existe alg�n error para conectarse a la base de datos
	 */
	public ResultSet getDescripciones() throws SQLException{
		return gestor.getDescripciones();
	}
	/**
	 * M�todo que busca el tipo de suscripcion de un cliente por su correo
	 * @param correo Correo del cliente 
	 * @return Resultset con el tipo de suscripcion del cliente
	 * @throws SQLException Si hay error para conectar a la base de datos
	 */
	public ResultSet getSuscripcion(String correo) throws SQLException {
		return gestor.getSuscripcion(correo);
	}
}
