package logica;

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
	 * Método que Permite insertar un producto
	 * @param producto Producto nuevo que va a ser registrado
	 * @throws SQLException
	 */
	public void insertarProducto(Producto producto) throws SQLException {
		gestorP.insertarProducto(producto);
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
