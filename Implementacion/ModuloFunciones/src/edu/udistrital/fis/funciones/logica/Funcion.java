package edu.udistrital.fis.funciones.logica;

import java.util.Date;

public class Funcion {
	private Pelicula pelicula;
	private Date fecha;
	private String hora;
	private int idSala;
	
	Funcion(Pelicula pelicula, Date fecha, String hora, int idSala){
		this.pelicula = pelicula;
		this.fecha = fecha;
		this.hora = hora;
		this.idSala = idSala;
	}
}
