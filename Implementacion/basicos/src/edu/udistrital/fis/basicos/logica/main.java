package edu.udistrital.fis.basicos.logica;

import java.sql.SQLException;

import edu.udistrital.fis.basicos.persistencia.GestorDB;

public class main {

	public static void main(String[] args) {
		try {
			GestorDB.getInstance();
			Funciones.mensajeConsola("Conexi�n exitosa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
