package edu.udistrital.fis.funciones.persistencia;

import java.io.FileNotFoundException;
import java.sql.SQLException;

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
	
	public void insertarPelicula (Pelicula pelicula) throws SQLException, FileNotFoundException {
		this.gestor.insertarPelicula(pelicula);
	}
}
