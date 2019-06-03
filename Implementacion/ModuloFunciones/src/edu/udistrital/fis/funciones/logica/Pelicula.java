package edu.udistrital.fis.funciones.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;
/**
 * Clase Pelicula
 * @author Andres Arias
 */
public class Pelicula {
	
	public static final int HORAS_MAXIMAS_DURACION = 4;
	public static final int MESES_MAXIMOS_DURACION = 3;
	public static final int FUNCIONES_MAXIMAS_POR_DIA = 9;
	public static final String HORA_MAS_TEMPRANO = "10 hours 40 minutes";
	public static final String HORA_MAS_TARDE = "12 hours 40 minutes";
	
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
		
		//creacion de las funciones por el algoritmo dado
		this.algoritmo.crearFunciones(this);
		//Insersión en persistencia
		try {
			FachadaPelicula.getInstance().insertarPelicula(this);
			Funciones.mensajePantalla("¡Pelicula insertada con éxito!");
		} catch (SQLException | FileNotFoundException e) {
			Funciones.mensajePantalla("No fue posible llevar a cabo la operación");
			Funciones.mensajeConsola("Clase Pelicula: "+e.getMessage());
		}
		
	}

}
