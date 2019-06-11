package edu.udistrital.fis.funciones.logica;

import java.util.ArrayList;

/**
 * Interfaz que define los algoritmos para la creación de funciones para una película
 * @author Andres Arias
 *
 */
public abstract class AlgoritmoFunciones {

	protected abstract void crearFunciones(Pelicula pelicula);
	protected abstract void insertarFunciones(ArrayList<Funcion> funciones,Pelicula pelicula);
}
