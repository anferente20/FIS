package edu.udistrital.fis.empleado.persistencia;

import java.sql.SQLException;

import edu.udistrital.fis.basicos.persistencia.GestorDB;

public abstract class Gestor {
	
	protected GestorDB gestor;
	
	protected Gestor() throws SQLException {
		gestor = GestorDB.getInstance();
	}
}