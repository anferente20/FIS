package edu.udistrital.fis.inventario.persistencia;
import edu.udistrital.fis.inventario.logica.Producto;
import edu.udistrital.fis.basicos.persistencia.Gestor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase  que permite el manejo de bases de dats relacionadas con el producto
 * @author anferente97
 *fecha:3.5.19
 */
public class GestorProducto extends Gestor{
	/**
	 * @throws SQLException
	 */
	public GestorProducto() throws SQLException {
		super();
	}
	
	/**
	 * Método encargado de registrar un nuevo producto en la base de datos
	 * @param producto Objeto que representa el producto que se va a registrar
	 * @throws SQLException Si falla la conexiÃ³n
	 */
	void insertarProducto(Producto producto) throws SQLException {
		String consulta = "insert into Producto (idProducto,nombre,unidadmedicion) values"
				+ "(?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, producto.getIdProducto());
		sentencia.setString(2, producto.getNombre());
		sentencia.setString(3, producto.getUnidadMedicion());
		sentencia.execute();
		//FachadaInventario.getInstance().agregarExistencias(cantidad, idCine, idProducto);
	}
	
	/**
	 * Metodo que permite buscar los nombres de todos los productos de la base de datos
	 * @return con los nombres de los productos
	 */
	ResultSet listarProductos(){
		ResultSet productos  =  null;
		try {
			String consulta = "select idProducto,nombre from Producto order by idProducto asc;";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			productos = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorProducto: "+ e.getMessage());
		}
		return productos;
	}
	/**
	 * Método que verifica la existencia de un producto
	 * @param nombre Nombre del producto
	 * @return true si el producto no existe, false de lo contrario
	 */
	boolean verificarProducto(String nombre) {
		boolean rt = true;
		ResultSet producto  =  null;
		try {
			String consulta = "select nombre from Producto where nombre = ?;";
			PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
			sentencia.setString(1,nombre);
			producto = sentencia.executeQuery();
			if(producto.next()) {
				rt = false;
			}
		}
		catch(SQLException e) {
			System.out.println("Clase GestorProducto: "+ e.getMessage());
		}	
		return rt;
	}
	
	/**
	 * Método que se encarga de obtener la unidad de medición
	 * @param nombre Nombre del producto a buscar
	 * @return Unidad de medición
	 * @throws SQLException
	 */
	String obtenerUM(String nombre) throws SQLException {
		String um = "";
		ResultSet producto  =  null;
		try {
			String consulta = "select unidadmedicion from Producto where nombre = '"+nombre+"' ;";
			PreparedStatement sentencia = gestor.getConector().prepareStatement(consulta);
			producto = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorProducto: "+ e.getMessage());
		}
		while(producto.next()) {
			um = producto.getObject(1).toString();
		}	
		return um;
	}
}
