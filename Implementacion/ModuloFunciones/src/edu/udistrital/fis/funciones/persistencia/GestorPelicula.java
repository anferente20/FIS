package edu.udistrital.fis.funciones.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import edu.udistrital.fis.basicos.persistencia.Gestor;
import edu.udistrital.fis.funciones.logica.Pelicula;

/**
 * Clase encargada directamente de realizar operaciones sobre la base de datos en las tablas Pelicula y Funcion
 * @author Andres Arias
 *
 */

public class GestorPelicula extends Gestor{

	protected GestorPelicula() throws SQLException {
		super();
	}
	
	void insertarPelicula(Pelicula pelicula) throws SQLException, FileNotFoundException {
		String consulta = "insert into pelicula (nombrePelicula,sinopsis,fechaEstreno,duracion,img,nombreDirector) values"
				+ "(?,?,"+pelicula.getFechaEstreno()+","+pelicula.getDuracion()+",?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, pelicula.getNombre());
		sentencia.setString(2, pelicula.getSinopsis());
		sentencia.setBinaryStream(3,new FileInputStream(pelicula.getImg()),pelicula.getImg().length());
		sentencia.setString(4, pelicula.getDirector());
		sentencia.execute();
	}

	
}
