package logica;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.GestorInventario;
import persistencia.GestorProducto;
/**
 * Fachada del invntario, maneja el inventari y el manejo de productos
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
	 * Objeto que permite implementar el patrón prototipo
	 */
	private static FachadaInventario instance;
	
	//------------------------CONSTRUCTOR--------------
	private FachadaInventario() throws SQLException {
		this.gestorP = new GestorProducto();
		this.gestorI = new GestorInventario();
	}
	
	/**
	 * Método que sirve para implementar el patrón singletón 
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
	 * Método que consulta todos los productos existentes
	 * @return ResultSet con la lista de los productos
	 * @throws SQLException
	 */
	public ResultSet listarProductoss() throws SQLException {
		return gestorP.listarProductos();
	}
	
	/**
	 * Método que intermedia para poder obtener la unidad de medición del Producto
	 * @param nombre Nombre del producto
	 * @return Unidad de medicion del producto 
	 * @throws SQLException
	 */
	public String unidadMedicion(String nombre) throws SQLException {
		return gestorP.obtenerUM(nombre);
	}
	
	/**
	 * Método que Permite insertar un producto
	 * @param producto Producto nuevo que va a ser registrado
	 * @throws SQLException
	 */
	public void insertarProducto(Producto producto) throws SQLException {
		gestorP.insertarProducto(producto);
	}

	/**
	 * Método para actualizar datos en el inventario
	 * @param cantidad Nueva cantidad 
	 * @param idcine Identificador del cine
	 * @param idproducto Identificador del producto
	 * @throws SQLException Si falla conexión BD
	 */
	public void actualizar(int cantidad, int idcine,int idproducto) throws SQLException {
		gestorI.actualizarEmpleado(cantidad, idcine, idproducto);
	}
	
	/**
	 * Método para buscar el identificador del Producto
	 * @param nombreCine nombre del producto a buscar
	 * @return el identificador del producto
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public int buscarID(String nombre) throws NumberFormatException, SQLException {
		return gestorP.ObtenerIDProducto(nombre);
	}
	
	public GestorInventario getGestorI() {
		return gestorI;
	}

	public void setGestorI(GestorInventario gestorI) {
		this.gestorI = gestorI;
	}

	public GestorProducto getGestorP() {
		return gestorP;
	}

	public void setGestorP(GestorProducto gestorP) {
		this.gestorP = gestorP;
	}
}
