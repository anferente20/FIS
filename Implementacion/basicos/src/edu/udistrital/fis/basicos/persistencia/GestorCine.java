package edu.udistrital.fis.basicos.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorCine extends Gestor{
	
	public GestorCine() throws SQLException {
		super();
	}
	
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

	/**
	 * Método que obtiene el id del cine
	 * @param nombreCine nombre del cine a buscar 
	 * @return Id del cine en la base de datos
	 * @throws NumberFormatException Si falla el formato del numero
	 * @throws SQLException si no conecta a la base de datos
	 */
	public int ObtenerIDCine(String nombreCine) throws NumberFormatException, SQLException {
		ResultSet cines = null;
		int cine =0;
		try {
			String consulta = "select idcine from Cine where nombreCine = '"+nombreCine+"';";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			cines = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorCine: "+ e.getMessage());
		}
		while(cines.next()) {
			cine = Integer.valueOf(cines.getObject(1).toString());
		}
		return cine;
	}
}

