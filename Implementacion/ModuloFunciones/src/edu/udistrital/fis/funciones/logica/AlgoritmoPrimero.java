package edu.udistrital.fis.funciones.logica;

import java.util.ArrayList;

import edu.udistrital.fis.basicos.logica.Funciones;

/**
 * Clase que es la primera implementacion de la interfaz AlgoritmoFunciones para el algoritmo de creacion de funciones
 * @author Andres Arias
 *
 */
public class AlgoritmoPrimero extends AlgoritmoFunciones{

	@Override
	public void crearFunciones(Pelicula pelicula) {
		Funciones.mensajePantalla("Creando funciones");
	}

	@Override
	protected void insertarFunciones(ArrayList<Funcion> funciones) {

	}

}
