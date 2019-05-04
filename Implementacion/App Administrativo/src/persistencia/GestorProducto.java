package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logica.Producto;
/**
 * Clase  que permite el manejo de bases de dats relacionadas con el producto
 * @author anferente97
 *fecha:3.5.19
 */
public class GestorProducto extends Gestor{

	public GestorProducto() throws SQLException {
		super();
	}
	
	/**
	 * Método encargado de registrar un nuevo producto en la base de datos
	 * @param producto Objeto que representa el producto que se va a registrar
	 * @throws SQLException Si falla la conexión
	 */
	public void insertarProducto(Producto producto) throws SQLException {
		String consulta = "insert into Producto (idProducto,nombre,unidadmedicion) values"
				+ "(?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, producto.getIdProducto());
		sentencia.setString(2, producto.getNombre());
		sentencia.setString(3, producto.getUnidadMedicion());
		sentencia.execute();
	}
	
	/**
	 * Método que permite buscar los nombres de todos los productos de la base de datos
	 * @return ResultSet con los nombres de los productos
	 */
	public ResultSet listarProductos(){
		ResultSet productos  =  null;
		try {
			String consulta = "select nombreCine from Cine order by idCine asc;";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			productos = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorCine: "+ e.getMessage());
		}
		return productos;
	}
	/**
	 * Método que verifica la existencia de un producto
	 * @param nombre Nombre del producto
	 * @return true si el producto no existe, false de lo contrario
	 */
	public  boolean vereficarProducto(String nombre) {
		boolean rt = true;
		ResultSet producto  =  null;
		try {
			String consulta = "select nombre from Producto where nombre = '"+nombre+"' ;";
			PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
			producto = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorProducto: "+ e.getMessage());
		}
		if(producto != null) {
			rt = false;
		}
		
		return rt;
	}
}
