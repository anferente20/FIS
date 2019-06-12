package edu.udistrital.fis.funciones.persistencia;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.logica.Pelicula;


/**
 * Clase intermedia entre la logica y la persistencia para la inserción de registros en las tablas Pelicula y Funciones
 * @author Andres Arias
 *
 */
public class FachadaPelicula {
	private GestorPelicula gestor;
	private static FachadaPelicula instance;
	
	private FachadaPelicula() throws SQLException{
		this.gestor = new GestorPelicula();
	}
	/**
	 * Método singleton
	 * @return Instancia única de la clase
	 * @throws SQLException
	 */
	public static FachadaPelicula getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaPelicula();
		}
		return instance;
	}
	/**
	 * Método que inserta un registro de Película en la base de datos
	 * @param pelicula Pelicula que será insertada
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public void insertarPelicula (Pelicula pelicula) throws SQLException{
		this.gestor.insertarPelicula(pelicula);
	}
	/**
	 * Método que inserta un registro de Funcion en la base de datos
	 * @param funcion Funcion que será insertada
	 * @return true si la función fue insertada con éxito, false si la función se cruza con otra función ya existente
	 * @throws SQLException
	 */
	public boolean insertarFuncion(Funcion funcion) throws SQLException {
		return this.gestor.insertarFuncion(funcion);
	}
	/**
	 * Método que obtiene el ID de una sala dado su consecutivo y el id del cine al que pertence
	 * @param consecutivo
	 * @param idCine
	 * @return ID de la sala
	 * @throws SQLException
	 */
	public int getIdSala(int consecutivo,int idCine) throws SQLException {
		return this.gestor.getIdSala(consecutivo, idCine);
	}
}
