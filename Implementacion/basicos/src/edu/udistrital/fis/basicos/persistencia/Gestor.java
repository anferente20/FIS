package edu.udistrital.fis.basicos.persistencia;

import java.sql.SQLException;

/**
 * Clase abstracta que se extenderá en clases que tengan manejo sobre la base de datos
 * @author Andres Arias
 *
 */
public abstract class Gestor {
	
	protected GestorDB gestor;
	
	protected Gestor() throws SQLException {
		gestor = GestorDB.getInstance();
	}
}