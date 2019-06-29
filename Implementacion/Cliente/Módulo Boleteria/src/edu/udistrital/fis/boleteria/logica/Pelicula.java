package edu.udistrital.fis.boleteria.logica;

import edu.udistrital.imagen.Imagen;

public class Pelicula {
	private int id;
	private String nombre;
	private String sinopsis;
	private String fechaEstreno;
	private String duracion;
	private Imagen img;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public Imagen getImg() {
		return img;
	}
	public void setImg(Imagen img) {
		this.img = img;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	private String director;
}
