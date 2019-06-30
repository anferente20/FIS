package edu.udistrital.fis.boleteria.logica;

public class Compra {
	private int idCliente;
	private String fechaCompra;
	private float total;
	private float precioBoleta;
	private String fechafuncion;
	private String horafuncion;
	private String nombrepelicula;
	private int sala;
	private String nombrecine;
	private byte[] img;
	private int cantidadBoletas;
	private int idCompra;
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getPrecioBoleta() {
		return precioBoleta;
	}
	public void setPrecioBoleta(float precioBoleta) {
		this.precioBoleta = precioBoleta;
	}
	public String getFechafuncion() {
		return fechafuncion;
	}
	public void setFechafuncion(String fechafuncion) {
		this.fechafuncion = fechafuncion;
	}
	public String getHorafuncion() {
		return horafuncion;
	}
	public void setHorafuncion(String horafuncion) {
		this.horafuncion = horafuncion;
	}
	public String getNombrepelicula() {
		return nombrepelicula;
	}
	public void setNombrepelicula(String nombrepelicula) {
		this.nombrepelicula = nombrepelicula;
	}
	public int getSala() {
		return sala;
	}
	public void setSala(int sala) {
		this.sala = sala;
	}
	public String getNombrecine() {
		return nombrecine;
	}
	public void setNombrecine(String nombrecine) {
		this.nombrecine = nombrecine;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getCantidadBoletas() {
		return cantidadBoletas;
	}
	public void setCantidadBoletas(int cantidadBoletas) {
		this.cantidadBoletas = cantidadBoletas;
	}
}
