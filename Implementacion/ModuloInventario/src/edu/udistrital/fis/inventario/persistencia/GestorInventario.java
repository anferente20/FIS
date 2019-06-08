package edu.udistrital.fis.inventario.persistencia;

import edu.udistrital.fis.basicos.persistencia.Gestor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase  que permite el manejo de bases de dats relacionadas con el inventario
 * @author anferente97
 *fecha:3.5.19
 */
public class GestorInventario extends Gestor{

	GestorInventario() throws SQLException {
		super();
	}
	
	/**
	 * M�todo encargado de actualizar las existencias de inventario de un cine
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
	 * M�todo encargado de insertar nuevos productos en las existencias de un cine
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
	 * M�todo que consulta las existencias de inventario de un cine en espec�fico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @return ResultSet existencias del inventario
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
	 * M�todo que consulta las existencias de inventario  de un producto espec�fico en un cine en espec�fico
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
	 * M�todo que consulta las existencias de inventario  de un producto espec�fico en un cine en espec�fico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @param nombreProducto nombre del producto que se va a consultar
	 * @return ResultSet existencias del inventario
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
	 * M�todo que verifica la existencia de un producto en un cine en espec�fico
	 * @param idCine ID del cine del que se quiere consultar existencia
	 * @param idProducto ID del producto del que se quiere consultar existencia
	 * @return boolean true si la consulta no est� vac�a, false si est� vacia
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
	
}
