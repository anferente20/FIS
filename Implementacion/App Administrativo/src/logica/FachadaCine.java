package logica;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.GestorCine;

public class FachadaCine {

	public static ResultSet consultarCines() throws SQLException {
		GestorCine gestor = new GestorCine();
		return gestor.consultarCines();
	}
}
