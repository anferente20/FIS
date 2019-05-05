package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logica.Empleado;
import logica.Producto;
/**
 * Clase  que permite el manejo de bases de dats relacionadas con el inventario
 * @author anferente97
 *fecha:3.5.19
 */
public class GestorInventario extends Gestor{

	public GestorInventario() throws SQLException {
		super();
	}
	
	/**
	 * Método encargado de actualizar los datos del empleado en  la base de datos
	 * @param cantidad Nueva cantidad a registrar
	 * @param idCine identificador del cine
	 * @param idProducto identificador del producto
	 * @throws SQLException Si no logra conectarse
	 */
	public void actualizarEmpleado(int cantidad, int idCine, int idProducto) throws SQLException {
		String consulta = "update Inventario set cantidad = ? where idcine = ? and idproducto = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, cantidad);
		sentencia.setInt(2, idCine);
		sentencia.setInt(3, idProducto);
		sentencia.execute();	
	}
	
	
}
