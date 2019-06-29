package edu.udistrital.fis.compra.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FachadaCombo {

	//----------------------ATRIBUTOS---------------------------
	/**
	 * Objeto tipo GestorInventario, permite el manejo de BD respecto al inventario
	 */
	private GestorCombo gestorC;
	
	/**
	 * Objeto que permite implementar el patron fachada
	 */
	private static FachadaCombo instance;
	
	//------------------------CONSTRUCTOR--------------
	/**
	 * @throws SQLException
	 */
	private FachadaCombo() throws SQLException {
		gestorC = new GestorCombo();
	}
	
	/**
	 * Método que sirve para implementar el patrón singleton 
	 * @return la fachada creada
	 * @throws SQLException
	 */
	public static FachadaCombo getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaCombo();
		}
		return instance;
	}
	
	public ResultSet consultarCombos() throws SQLException {
		return gestorC.consultarCombos();
	}

	/**
	 * Método para cargar un combo  a partir de su id
	 * @param idCombo id del Combo a buscar
	 * @return ResultSet con los datos del combo buscado
	 * @throws SQLException Si hay problema para conectar a la base de datos
	 */
	public ResultSet consultarComboById(int idCombo) throws SQLException {
		return gestorC.buscarComboByID(idCombo);
	}
}
