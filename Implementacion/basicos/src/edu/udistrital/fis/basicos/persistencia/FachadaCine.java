package edu.udistrital.fis.basicos.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FachadaCine{
	
	private GestorCine gestor;
	private static FachadaCine instance;
	//constructor
	private FachadaCine() throws SQLException {
		this.gestor = new GestorCine();
	}
	//singleton
	public static FachadaCine getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaCine();
		}
		return instance;
	}
	public ResultSet consultarCines() throws SQLException {
		return gestor.consultarCines();
	}
	
	/**
	 * Metodo para buscar el identificador del cine
	 * @param nombreCine nombre del cine a buscar
	 * @return el identificador del cine
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public int buscarID(String nombreCine) throws NumberFormatException, SQLException {
		return gestor.ObtenerIDCine(nombreCine);
	}
}