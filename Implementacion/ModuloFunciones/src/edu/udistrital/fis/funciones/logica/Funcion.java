package edu.udistrital.fis.funciones.logica;

import java.util.Date;

/**
 * Clase Funcion
 * @author Andres Arias
 */
public class Funcion {
	
	// La hora debe estar en formato: hh hours mm minutes
	// La fecha debe estar en formato: dd/MM/yyyy
	private Pelicula pelicula;
	private Date fecha;
	private String hora;
	private int idSala;
	
	public Pelicula getPelicula() {
		return pelicula;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public int getIdSala() {
		return idSala;
	}
	/**
	 * La fecha debe estar en formato: dd/MM/yyyy
	 * La hora debe estar en formato: hh hours mm minutes
	 * @param pelicula
	 * @param fecha
	 * @param hora
	 * @param idSala
	 */
	Funcion(Pelicula pelicula, Date fecha, String hora, int idSala){
		this.pelicula = pelicula;
		this.fecha = fecha;
		this.hora = hora;
		this.idSala = idSala;
	}
}
