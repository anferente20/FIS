package edu.udistrital.fis.boleteria.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.udistrital.fis.boleteria.logica.Asiento;
import edu.udistrital.fis.boleteria.logica.Funcion;

public class FachadaBoleteria {
	
	GestorBoleteria gestor;
	
	private FachadaBoleteria() throws SQLException {
		this.gestor = new GestorBoleteria();
	}
	
	private static FachadaBoleteria instance;
	
	public static FachadaBoleteria getInstance() throws SQLException {
		if(instance==null) instance = new FachadaBoleteria();
		return instance;
	}
	
	/**
	 * M�todo que consulta las peliculas que est�n en cartelera
	 * @return Peliculas en cartelera
	 * @throws SQLException
	 */
	public ResultSet consultarPeliculasCartelera() throws SQLException {
		return this.gestor.consultarPeliculasCartelera();
	}
	/**
	 * M�todo que consulta las peliculas que aun no se han estrenado
	 * @return Peliculas proximas a estrenarse
	 * @throws SQLException
	 */
	public ResultSet consultarPeliculasEstrenos() throws SQLException {
		return this.gestor.consultarPeliculasEstrenos();
	}
	/**
	 * M�todo que consulta funciones de una pelicula para una fecha y cine espec�ficos
	 * @param idCine ID del cine
	 * @param fecha Fecha de la funcion
	 * @param idPelicula Pelicula de la que se quieren consultar las funciones
	 * @return Funciones con los par�metros de busqueda
	 */
	public ResultSet consultarFuncionesByCineFecha(int idCine, String fecha, int idPelicula) throws SQLException {
		return this.gestor.consultarFuncionesByCinePeliculaFecha(idCine, fecha,idPelicula);
	}
	
	/**
	 * M�todo que retorna el consecutivo de una sala dentro de una cine dado si ID
	 * @param idSala Identificador de la sala
	 * @return Consecutivo de la sala dentro de su cine
	 * @throws SQLException 
	 */
	public int getConsecutivoSala(int idSala) throws SQLException {
		return this.gestor.getConsecutivoSala(idSala);
	}
	
	/**
	 * M�todo que consulta si un asiento est� ocupado en una funcion en espec�fico
	 * @param idfuncion ID de la funcion de la cual se quieren consultar los asientos ocupados
	 * @return Vacio si no est� ocupado, un registro si est� ocupado
	 * @throws SQLException
	 */
	public ResultSet consultarAsientosOcupados(int idfuncion, String fila, String columna) throws SQLException {
		return this.gestor.consultarAsientosOcupados(idfuncion, fila, columna);
	}
	/**
	 * M�todo que se encarga de registrar la compra de boletas para una funcion
	 * @param funcion Funcion de la cual se comprar�n las boletas
	 * @param asientos Lista din�mica de los asientos que se comprar�n para la funci�n
	 * @param idCliente ID del cliente que har� la compra
	 * @param cantidadBoletas Cantidad de boletas que se vender�n
	 * @throws SQLException
	 */
	public void comprarBoletas (Funcion funcion,ArrayList<Asiento> asientos,int idCliente, int cantidadBoletas) throws SQLException {
		this.gestor.comprarBoletas(funcion, asientos, idCliente, cantidadBoletas);
	}
	/**
	 * M�todo que consulta las compras de boletas realizadas por un cliente
	 * @param idCliente ID del cliente del cual se desean consultar las compras
	 * @return Compras de boletas hechas por el cliente
	 * @throws SQLException
	 */
	public ResultSet consultarComprasByCliente(int idCliente) throws SQLException {
		return this.gestor.consultarComprasByCliente(idCliente);
	}
	/**
	 * M�todo que consulta las boletas compradas en una compra
	 * @param idCompra ID de la compra de la que se quieren consultar las boletas
	 * @return Boletas compradas
	 * @throws SQLException
	 */
	public ResultSet consultarBoletasByCompra(int idCompra) throws SQLException {
		return this.gestor.consultarBoletasByCompra(idCompra);
	}
}
