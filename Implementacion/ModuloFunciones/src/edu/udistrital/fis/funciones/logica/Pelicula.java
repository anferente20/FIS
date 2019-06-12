package edu.udistrital.fis.funciones.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;
/**
 * Clase Pelicula
 * @author Andres Arias
 */
public class Pelicula implements Runnable{
	//El tiempo en horas debe estar en formato: hh hours mm minutes
	//El tiempo en meses debe estar en formato: MM months dd days
	/**
	 * Horas de máxima duración de un pelicula
	 */
	public static final int HORAS_MAXIMAS_DURACION = 3;
	/**
	 * Meses de máxima duración de un pelicula en cartelera
	 */
	public static final int MESES_MAXIMOS_DURACION = 3;
	/**
	 * Cantidad máxima de funciones por dia de una pelicula
	 */
	public static final int FUNCIONES_MAXIMAS_POR_DIA = 9;
	/**
	 * Hora más temprano a la que puede habilitarse una funcion
	 */
	public static final String HORA_MAS_TEMPRANO = "10 hours 40 minutes";
	/**
	 * Hora más tarde a la que puede habilitarse una funcion
	 */
	public static final String HORA_MAS_TARDE = "0 hours 40 minutes";
	
	private int id;
	private String nombre;
	private String sinopsis;
	private String fechaEstreno;
	private String duracion;
	private File img;
	private String tiempoCartelera;
	private int funcionesPorDia;
	private String director;
	private AlgoritmoFunciones algoritmo;
	/**
	 * El tiempo en horas debe estar en formato: hh hours mm minutes
	 * El tiempo en meses debe estar en formato: MM months dd days
	 * La fecha debe estar en formato: dd/MM/yyyy
	 * @param nombre
	 * @param sinopsis
	 * @param fechaEstreno
	 * @param duracion
	 * @param img
	 * @param algoritmo
	 * @param tiempoCartelera
	 * @param funcionesPorDia
	 * @param director
	 */
	public Pelicula(String nombre, String sinopsis,String fechaEstreno,String duracion,File img, AlgoritmoFunciones algoritmo,
			String tiempoCartelera,int funcionesPorDia, String director) {
		this.nombre = nombre;
		this.sinopsis = sinopsis;
		this.fechaEstreno = fechaEstreno;
		this.duracion = duracion;
		this.img= img;
		this.algoritmo = algoritmo;
		this.tiempoCartelera = tiempoCartelera;
		this.funcionesPorDia = funcionesPorDia;
		this.director = director;
		//se agrega la pelicula
		agregarPelicula();
	}
	
	public int getId() {
		return this.id;
	}
	public String getDirector() {
		return director;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	
	public String getFechaEstreno() {
		return fechaEstreno;
	}
	/**
	 * @return Tiempo en 'hh hours mm minutes'
	 */
	public String getDuracion() {
		return duracion;
	}

	public File getImg() {
		return img;
	}

	public String getTiempoCartelera() {
		return tiempoCartelera;
	}

	public int getFuncionesPorDia() {
		return funcionesPorDia;
	}
	
	private void agregarPelicula() {	
		//Insersión en persistencia
		try {
			FachadaPelicula.getInstance().insertarPelicula(this);
			this.setId();
			//creacion de las funciones por el algoritmo dado
			Thread hilo = new Thread(this);
			hilo.start();
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase Pelicula: "+e.getMessage());
			Funciones.mensajePantalla("No fue posible llevar a cabo la operación");
		}
	}
	
	private void setId() {
		String consulta = "select max(idPelicula) from pelicula;";
		try {
			PreparedStatement sentencia = GestorDB.getInstance().getConector().prepareStatement(consulta);
			ResultSet rs = sentencia.executeQuery();
			rs.next();
			this.id = rs.getInt(1);
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase pelicula: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis(); //Tiempo inicial
		this.algoritmo.crearFunciones(this);
		long endTime = System.currentTimeMillis()-startTime; //Tiempo empleado
		Funciones.mensajeConsola("Tiempo empleado: "+String.valueOf((long)endTime/1000)+" s");
		Funciones.mensajePantalla("¡Pelicula insertada con éxito!");
	}

}
