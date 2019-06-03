package edu.udistrital.fis.inventario.persistencia;


import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.inventario.persistencia.GestorInventario;
import edu.udistrital.fis.inventario.persistencia.GestorProducto;
import edu.udistrital.fis.inventario.logica.Producto;

/**
 * Fachada del inventario, maneja el inventari y el manejo de productos
 * @author anferente97
 * fecha: 3.5.19
 *
 */
public class FachadaInventario {

	//----------------------ATRIBUTOS---------------------------
	/**
	 * Objeto tipo GestorInventario, permite el manejo de BD respecto al inventario
	 */
	private GestorInventario gestorI;
	
	/**
	 * Objeto tipo GestorInventario, permite el manejo de BD respecto a los productos
	 */
	private GestorProducto gestorP;
	
	/**
	 * Objeto que permite implementar el patron fachada
	 */
	private static FachadaInventario instance;
	
	//------------------------CONSTRUCTOR--------------
	private FachadaInventario() throws SQLException {
		this.gestorP = new GestorProducto();
		this.gestorI = new GestorInventario();
	}
	
	/**
	 * M�todo que sirve para implementar el patr�n singleton 
	 * @return la fachada creada
	 * @throws SQLException
	 */
	public static FachadaInventario getInstance() throws SQLException {
		if(instance==null) {
			instance = new FachadaInventario();
		}
		return instance;
	}
	/**
	 *Metodo que consulta todos los productos existentes
	 * @return ResultSet con la lista de los productos
	 * @throws SQLException
	 */
	public ResultSet listarProductoss() throws SQLException {
		return gestorP.listarProductos();
	}
	
	/**
	 *Metodo que intermedia para poder obtener la unidad de medición del Producto
	 * @param nombre Nombre del producto
	 * @return Unidad de medicion del producto 
	 * @throws SQLException
	 */
	public String unidadMedicion(String nombre) throws SQLException {
		return gestorP.obtenerUM(nombre);
	}
	
	/**
	 *Metodo que Permite insertar un producto
	 * @param producto Producto nuevo que va a ser registrado
	 * @throws SQLException
	 */
	public void insertarProducto(Producto producto) throws SQLException {
		gestorP.insertarProducto(producto);
	}

	/**
	 *Metodo para actualizar datos en el inventario
	 * @param cantidad Nueva cantidad 
	 * @param idcine Identificador del cine
	 * @param idproducto Identificador del producto
	 * @throws SQLException Si falla conexión BD
	 */
	public void actualizarInventario(int cantidad, int idCine,int idProducto) throws SQLException {
		gestorI.actualizarInventario(cantidad, idCine, idProducto);
	}
	
	
	/**
	 *Metodo verificar la existencia de un producto
	 * @param nombreProducto nombre del producto a buscar
	 * @return true si el producto existe, false si el producto no existe
	 */
	public boolean verificarProducto(String nombreProducto) {
		return gestorP.verificarProducto(nombreProducto);
	}
	/**
	 * M�todo encargado de insertar nuevos productos en las existencias de un cine
	 * @param cantidad Nueva cantidad a registrar
	 * @param idCine identificador del cine
	 * @param idProducto identificador del producto
	 * @throws SQLException si acontece algun error
	 */
	public void agregarExistencias(int cantidad, int idCine, int idProducto) throws SQLException {
		gestorI.agregarExistencias(cantidad, idCine, idProducto);
	}
	
	/**
	  * M�todo que permite consultar las existencias de inventario en un cine en espec�fico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @return  existencias del inventario
	 * @throws SQLException 
	 */
	
	public ResultSet consultarInventario(int idCine) throws SQLException {
		return gestorI.consultarInventario(idCine);
	}
	
	/**
	 * M�todo que permite consultar las existencias de un producto en un cine en espec�fico
	 * @param idCine ID del cine del cual se van a consultar las existencias
	 * @param nombreProducto nombre del producto que se va a consultar
	 * @return existencias del inventario
	 * @throws SQLException 
	 */
	
	public ResultSet consultarInventario(int idCine, String nombreProducto) throws SQLException {
		return gestorI.consultarInventario(idCine, nombreProducto);
	}
	
	/**
	 * M�todo que verifica si un producto ya est� registrado en un cine en espec�fico
	 * @param idCine ID del cine de que se quiere consultar la existencia
	 * @param idProducto ID del producto de que se quiere consultar la existencia
	 * @throws SQLException 
	 * @return true si el producto ya est� en el cine, false si no est�
	 */
	public boolean verificarProductoEnCine(int idCine, int idProducto) throws SQLException {
		return gestorI.verificarProductoEnCine(idCine, idProducto);
	}

}
