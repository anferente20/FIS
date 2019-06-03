package edu.udistrital.fis.basicos.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorCine extends Gestor{
	
	public GestorCine() throws SQLException{
		super();
	}
	/**
	 * Método que consulta todos los cines disponibles
	 * @return Cines registrados
	 */
	public ResultSet consultarCines(){
		ResultSet cines = null;
		try {
			String consulta = "select nombreCine from Cine order by idCine asc;";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			cines = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorCine: "+ e.getMessage());
		}
		return cines;
	}
}

