package edu.udistrital.fis.funciones.logica;

import java.util.Date;

/**
 * Clase Funcion
 * @author Andres Arias
 */
public class Funcion {
	
	// La hora debe estar en formato: hh hours mm minutes
	// La fecha debe estar en formato: dd/MM/yyyy
	private int idFuncion;
	private Pelicula pelicula;
	private Date fecha;
	private String hora;
	private int idSala;
	
	/**
	 * La fecha debe estar en formato: dd/MM/yyyy
	 * La hora debe estar en formato: hh hours mm minutes
	 * @param pelicula
	 * @param fecha
	 * @param hora
	 * @param idSala
	 */
	public Funcion(Pelicula pelicula, Date fecha, String hora, int idSala){
		this.pelicula = pelicula;
		this.fecha = fecha;
		this.hora = hora;
		this.idSala = idSala;
	}

	
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public Pelicula getPelicula() {
		return pelicula;
	}
	
	/**
	 * Fecha en formato: dd/MM/yyyy
	 * @return Fecha de la funcion
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * Hora en formato: hh hours mm minutes
	 * @return Hora de la funcion
	 */
	public String getHora() {
		return hora;
	}

	public int getIdSala() {
		return idSala;
	}

	public int getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}
}
