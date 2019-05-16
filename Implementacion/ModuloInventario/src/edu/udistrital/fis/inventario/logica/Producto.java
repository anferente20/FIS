package edu.udistrital.fis.inventario.logica;
/**
 * Clase  que permite el manejo de datos relacionadas con los productos
 * @author anferente97
 *fecha:3.5.19
 */
public class Producto {
	
	//--------------------------ATRIBUTOS-------------------------
	/**
	 * Entero que represneta el identificador del producto
	 */
	private int idProducto;
	
	/**
	 * String que muestra el nombre del producto
	 */
	private String nombre;
	
	/**
	 * String que muestra la unidad de medición de dicho producto
	 */
	private String unidadMedicion;
	
	

	//-------------------------GETTERS & SETTERS--------------------------

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadMedicion() {
		return unidadMedicion;
	}

	public void setUnidadMedicion(String unidadMedicion) {
		this.unidadMedicion = unidadMedicion;
	}
	
}
