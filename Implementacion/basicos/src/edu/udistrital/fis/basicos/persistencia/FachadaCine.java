package edu.udistrital.fis.basicos.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FachadaCine{
	
	private GestorCine gestor;
	private static FachadaCine instance;
	
	private FachadaCine() throws SQLException{
		this.gestor = new GestorCine();
	}
	/**
	 * M�todo singleton
	 * @return Instancia �nica de la clase
	 * @throws SQLException
	 */
	public static FachadaCine getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaCine();
		}
		return instance;
	}
	/**
	 * M�todo que consulta todos los cines disponibles
	 * @return Cines registrados
	 * @throws SQLException
	 */
	public ResultSet consultarCines() throws SQLException {
		return gestor.consultarCines();
	}
}