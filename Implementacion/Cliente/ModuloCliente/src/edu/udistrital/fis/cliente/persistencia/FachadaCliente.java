package edu.udistrital.fis.cliente.persistencia;

import java.sql.SQLException;

import edu.udistrital.fis.cliente.logica.Cliente;


/**
 * Clase de fachada para el cliente
 * @author anferente
 */
public class FachadaCliente {

	/**
	 * Atributo que representa el gestor cliente
	 */
	private GestorCliente gestor;
	
	/**
	 * Atributo que permitir� instanciar la fachada
	 */
	private static FachadaCliente instance;
	
	public FachadaCliente() throws SQLException {
		gestor = new GestorCliente();
	}
	
	/**
	 * M�todo para singlet�n
	 * @return Instancia �nica de la clase
	 * @throws SQLException
	 */
	public static FachadaCliente getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaCliente();
		}
		return instance;
	}
	
	/**
	 * 	M�todo que permite ingresar un cliente a la base de datos
	 * @param cliente Cliente a ingresar
	 * @throws SQLException En caso de que haya un error al conectar a la base de datos
	 */
	public void insertarCliente(Cliente cliente) throws SQLException {
		gestor.insertarCliente(cliente);
	}
	
	/**
	 * M�todo que permite cambiar la contrasena de un cliente a partir de su correo
	 * @param contrasena Nueva contrasena para su cuenta
	 * @param correo Correo registrado del cliente
	 * @throws SQLException Si existe alg�n error al conectarse a la base de datos
	 */
	public void cambiarContrasena(String contrasena, String correo) throws SQLException {
		gestor.cambiarContrasena(contrasena, correo);
	}
	
	/**
	 * M�todo que permite modificar el correo del cliente
	 * @param correoNuevo Nuevo correo que se registrar� en la base de datos
	 * @param correoAntiguo Correo que estaba hasta ese momento en la base de datos
	 * @throws SQLException Si existe alg�n problema al conectarse en la base de datos.
	 */
	public void cambiarCorreo(String correoNuevo, String CorreoAntiguo) throws SQLException {
		gestor.cambiarCorreo(correoNuevo, CorreoAntiguo);
	}
	
	/**
	 * M�todo que permite cambiar el tipo de suscripci�n de un cliente
	 * @param tipo Nuevo tipo de suscripcion del cliente
	 * @param correo Crorreo del cliente
	 * @throws SQLException Si existe un error al conectarse a la base de datos
	 */
	public void cambiarSuscripcion(int tipo, String correo) throws SQLException {
		gestor.cambiarSuscripcion(tipo, correo);
	}
	
	
	
	/**
	 * M�todo que verifica la existencia de un correo 
	 * @param correo correo a verificar
	 * @return true si el correo existe, false de lo contrario 
	 * @throws SQLException Si existe alg�n problema para conectar a la base de datos
	 */
	public boolean verificarConrreo(String correo) throws SQLException {
		return gestor.verificarCorreo(correo);
	}
}
