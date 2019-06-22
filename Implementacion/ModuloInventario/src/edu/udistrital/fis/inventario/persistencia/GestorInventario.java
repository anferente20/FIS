package edu.udistrital.fis.inventario.persistencia;

import edu.udistrital.fis.basicos.persistencia.Gestor;
import edu.udistrital.fis.inventario.logica.Combo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase  que permite el manejo de bases de dats relacionadas con el inventario
 * @author anferente97
 *fecha:3.5.19
 */
public class GestorInventario extends Gestor{
	/**
	 * @throws SQLException
	 */
	GestorInventario() throws SQLException {
		super();
	}
	
	/**
	 * Método encargado de actualizar las existencias de inventario de un cine
	 * @param cantidad Nueva cantidad a registrar
	 * @param idCine identificador del cine
	 * @param idProducto identificador del producto
	 * @throws SQLException Si no logra conectarse
	 */
	void actualizarInventario(int cantidad, int idCine, int idProducto) throws SQLException {
		String consulta = "update Inventario set cantidad = ? where idcine = ? and idproducto = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, cantidad);
		sentencia.setInt(2, idCine);
		sentencia.setInt(3, idProducto);
		sentencia.execute();	
	}
	
	/**
	 * Método encargado de insertar nuevos productos en las existencias de un cine
	 * @param cantidad Nueva cantidad a registrar
	 * @param idCine identificador del cine
	 * @param idProducto identificador del producto
	 * @throws SQLException si acontece algun error
	 */
	void agregarExistencias(int cantidad, int idCine, int idProducto) throws SQLException {
		String consulta = "insert into inventario (idCine,idProducto,cantidad) values (?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCine);
		sentencia.setInt(2, idProducto);
		sentencia.setInt(3, cantidad);
		sentencia.execute();	
	}
	

	
	/**
	 * Método que consulta las existencias de inventario de un cine en específico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @return existencias del inventario
	 * @throws SQLException 
	 */
	
	ResultSet consultarInventario(int idCine) throws SQLException {
		String consulta = "select producto.idProducto, producto.nombre, inventario.cantidad, producto.unidadMedicion from \r\n" + 
				"producto, inventario, cine where producto.idProducto = inventario.idProducto and inventario.idCine = cine.idCine\r\n" + 
				"and cine.idCine = ?;";
		PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1,idCine);
		return sentencia.executeQuery();
	}
	
	/**
	 * Método que consulta las existencias de inventario  de un producto específico en un cine en específico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @param idProducto id del producto que se va a consultar
	 * @return ResultSet existencias del inventario
	 * @throws SQLException 
	 */
	
	ResultSet consultarInventario(int idCine, int idProducto) throws SQLException {
		String consulta = "select producto.idProducto, producto.nombre, inventario.cantidad, producto.unidadMedicion from \r\n" + 
				"producto, inventario, cine where producto.idProducto = inventario.idProducto and inventario.idCine = cine.idCine\r\n" + 
				"and cine.idCine = ? and producto.idProducto = ?;";
		PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1,idCine);
		sentencia.setInt(2,idProducto);
		return sentencia.executeQuery();
	}
	
	/**
	 * Método que consulta las existencias de inventario  de un producto específico en un cine en específico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @param nombreProducto nombre del producto que se va a consultar
	 * @return existencias del inventario
	 * @throws SQLException 
	 */
	
	ResultSet consultarInventario(int idCine, String nombreProducto) throws SQLException {
		String consulta = "select producto.idProducto, producto.nombre, inventario.cantidad, producto.unidadMedicion from \r\n" + 
				"producto, inventario, cine where producto.idProducto = inventario.idProducto and inventario.idCine = cine.idCine\r\n" + 
				"and cine.idCine = ? and lower(producto.nombre) like ? || '%';";
		PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1,idCine);
		sentencia.setString(2,nombreProducto);
		return sentencia.executeQuery();
	}
	/**
	 * Método que verifica la existencia de un producto en un cine en específico
	 * @param idCine ID del cine del que se quiere consultar existencia
	 * @param idProducto ID del producto del que se quiere consultar existencia
	 * @return true si la consulta no está vacía, false si está vacia
	 * @throws SQLException
	 */
	boolean verificarProductoEnCine(int idCine,int idProducto) throws SQLException {
		String consulta = "select * from inventario where inventario.idProducto = ? and inventario.idCine = ?";
		PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1,idProducto);
		sentencia.setInt(2,idCine);
		ResultSet productos = sentencia.executeQuery();
		if(productos.next()) {
			return true;
		}
		return false;		
	}
	
	void insertarCombo(Combo combo) throws SQLException, FileNotFoundException {
		String consulta = 
				"insert into "
				+ "combo (descripcion,precio,estado,img) "
				+ "values "
				+ "(?,?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, combo.getDescripcion());
		sentencia.setFloat(2, combo.getPrecio());
		sentencia.setInt(3, 1);
		sentencia.setBinaryStream(4,new FileInputStream(combo.getImg().getFile()),combo.getImg().getFile().length());
		sentencia.execute();
	}
	
	ResultSet consultarCombos() throws SQLException {
		String consulta = "select * from combo where estado = 1;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
	
	void darBajaCombo(int idCombo) throws SQLException {
		String consulta = "update combo set estado = 0 where idcombo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCombo);
		sentencia.execute();
	}
	
	void modificarCombo(Combo combo) throws SQLException, FileNotFoundException {
		String consulta = "update combo set descripcion = ?, precio = ?, img = ? where idcombo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, combo.getDescripcion());
		sentencia.setFloat(2, combo.getPrecio());
		sentencia.setBinaryStream(3,new FileInputStream(combo.getImg().getFile()),combo.getImg().getFile().length());
		sentencia.setInt(4, combo.getIdCombo());
		sentencia.execute();
	}
	
}
