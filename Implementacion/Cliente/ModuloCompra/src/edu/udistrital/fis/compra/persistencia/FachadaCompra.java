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


	public void insertarCompra(float total,int idCliente) throws SQLException {
		gestorC.registrarCompra(total, idCliente);
	}
	
	public void registrarComboCompra(int idCompra,int cantidad, int idCombo,double d) throws SQLException {
		gestorC.registrarCompraCombo(idCompra, cantidad, idCombo, d);
	}

	public int getIdCompraByCliente(int idCliente) throws SQLException {
		return this.gestorC.getIdCompraByCliente(idCliente);
	}
}
