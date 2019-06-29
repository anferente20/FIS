package edu.udistrital.fis.compra.logica;

import edu.udistrital.imagen.Imagen;

public class Combo {

	private int idCombo;
	private String descripcion;
	private float precio;
	private Imagen img;
	private int cantidad;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdCombo() {
		return idCombo;
	}
	public void setIdCombo(int idCombo) {
		this.idCombo = idCombo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Imagen getImg() {
		return img;
	}
	public void setImg(Imagen img) {
		this.img = img;
	}
}
