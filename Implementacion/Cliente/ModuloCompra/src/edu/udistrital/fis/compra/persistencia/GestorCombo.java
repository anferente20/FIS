package edu.udistrital.fis.compra.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.basicos.persistencia.Gestor;

public class GestorCombo extends Gestor {

	protected GestorCombo() throws SQLException {
		super();
	}


	/**
	 * Método para consultar los combos existentes en la base de datos
	 * @return REsultSet con los combos
	 * @throws SQLException Si no puede conectar a la base de datos
	 */
	ResultSet consultarCombos() throws SQLException {
		String consulta = "select * from combo where estado = 1;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}

	/**
	 * Método para cargar un combo  a partir de su id
	 * @param idCombo id del Combo a buscar
	 * @return ResultSet con los datos del combo buscado
	 * @throws SQLException Si hay problema para conectar a la base de datos
	 */
	ResultSet buscarComboByID(int idCombo) throws SQLException {
		String consulta = "select * from combo where idcombo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCombo);
		return sentencia.executeQuery();
	}
}
