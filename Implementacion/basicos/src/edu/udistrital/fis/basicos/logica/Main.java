package edu.udistrital.fis.basicos.logica;

import java.sql.SQLException;

import edu.udistrital.fis.basicos.persistencia.GestorDB;

public class Main {

	private static void main(String[] args) {
		try {
			GestorDB.getInstance();
			Funciones.mensajeConsola("Conexión exitosa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
