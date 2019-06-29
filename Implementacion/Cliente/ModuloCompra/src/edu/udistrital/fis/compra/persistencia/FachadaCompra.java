package edu.udistrital.fis.compra.persistencia;

import java.sql.SQLException;

public class FachadaCompra {

	private GestorCompra gestorC;
	
	private static FachadaCompra instance;
	
	public FachadaCompra() throws SQLException {
		gestorC = new GestorCompra();
	}

	public static FachadaCompra getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaCompra();
		}
		return instance;
	}


	public void insertarCompra(int total,int idCliente,int id) throws SQLException {
		gestorC.registrarCompra(total, idCliente, id);
	}
	
	public void registrarComboCompra(int idCompra,int cantidad, int idCombo,double d) throws SQLException {
		gestorC.registrarCompraCombo(idCompra, cantidad, idCombo, d);
	}
}
