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
	 * Método singleton
	 * @return Instancia única de la clase
	 * @throws SQLException
	 */
	public static FachadaCine getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaCine();
		}
		return instance;
	}
	/**
	 * Método que consulta todos los cines disponibles
	 * @return Cines registrados
	 * @throws SQLException
	 */
	public ResultSet consultarCines() throws SQLException {
		return gestor.consultarCines();
	}
}