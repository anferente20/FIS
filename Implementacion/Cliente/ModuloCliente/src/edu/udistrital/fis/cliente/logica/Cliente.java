package edu.udistrital.fis.cliente.logica;
/**
 * Clase Cliente
 * @author anferente
 *
 */
public class Cliente {

	//------------------ATRIBUTOS------------------------------
	/**
	 * atributo que representa el id del cliente
	 */
	private int idCliente;
	/**
	 * atributo que representa el nombre del cliente
	 */
	private String nombreCliente;
	/**
	 * Atributo que representa el apellido del cliente
	 */
	private String apellidoCliente;
	/**
	 * atributo que representa la identificacion del cliente
	 */
	private int identificacionCliente;
	/**
	 * Atributo que representa el tipo de suscripcion que el cliente tiene
	 */
	private int tipoSuscripcion;
	/**
	 * Atributo que representa la contraseña del cliente
	 */
	private String contrasena;
	/**
	 * Atributo que representa el correo del cliente
	 */
	private String correo;
	
	//_-----------------GETTERS & SETTERS-----------------------
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public int getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(int identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public int getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(int tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
