package logica;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.GestorCine;

public class FachadaCine {
	
	private static GestorCine gestorC;
	private static FachadaCine instance;
	//constructor
	private FachadaCine() throws SQLException {
		gestorC = new GestorCine();
	}
	//singleton
	public static FachadaCine getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaCine();
		}
		return instance;
	}
	public ResultSet consultarCines() throws SQLException {
		return gestorC.consultarCines();
	}
}
