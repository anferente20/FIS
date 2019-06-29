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
	 * Método que consulta las peliculas que están en cartelera
	 * @return Peliculas en cartelera
	 * @throws SQLException
	 */
	public ResultSet consultarPeliculasCartelera() throws SQLException {
		return this.gestor.consultarPeliculasCartelera();
	}
	/**
	 * Método que consulta las peliculas que aun no se han estrenado
	 * @return Peliculas proximas a estrenarse
	 * @throws SQLException
	 */
	public ResultSet consultarPeliculasEstrenos() throws SQLException {
		return this.gestor.consultarPeliculasEstrenos();
	}
	/**
	 * Método que consulta funciones de una pelicula para una fecha y cine específicos
	 * @param idCine ID del cine
	 * @param fecha Fecha de la funcion
	 * @param idPelicula Pelicula de la que se quieren consultar las funciones
	 * @return Funciones con los parámetros de busqueda
	 */
	public ResultSet consultarFuncionesByCineFecha(int idCine, String fecha, int idPelicula) throws SQLException {
		return this.gestor.consultarFuncionesByCinePeliculaFecha(idCine, fecha,idPelicula);
	}
	
	/**
	 * Método que retorna el consecutivo de una sala dentro de una cine dado si ID
	 * @param idSala Identificador de la sala
	 * @return Consecutivo de la sala dentro de su cine
	 * @throws SQLException 
	 */
	public int getConsecutivoSala(int idSala) throws SQLException {
		return this.gestor.getConsecutivoSala(idSala);
	}
	
	/**
	 * Método que consulta si un asiento está ocupado en una funcion en específico
	 * @param idfuncion ID de la funcion de la cual se quieren consultar los asientos ocupados
	 * @return Vacio si no está ocupado, un registro si está ocupado
	 * @throws SQLException
	 */
	public ResultSet consultarAsientosOcupados(int idfuncion, String fila, String columna) throws SQLException {
		return this.gestor.consultarAsientosOcupados(idfuncion, fila, columna);
	}
	
	public void comprarBoletas (Funcion funcion,ArrayList<Asiento> asientos,int idCliente, int cantidadBoletas) throws SQLException {
		this.gestor.comprarBoletas(funcion, asientos, idCliente, cantidadBoletas);
	}
}
