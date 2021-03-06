package edu.udistrital.fis.cliente.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.basicos.persistencia.Gestor;
import edu.udistrital.fis.cliente.logica.Cliente;


/**
 * Clase que permitir� conectarse a la base de Datos
 * @author anferente
 */
public class GestorCliente extends Gestor{

	protected GestorCliente() throws SQLException {
		super();
	}

	/**
	 * M�todo que permite ingresar un cliente a la base de datos
	 * @param cliente Cliente a ingresar
	 * @throws SQLException En caso de que haya un error al conectar a la base de datos
	 */
	public void insertarCliente(Cliente cliente) throws SQLException {
		String consulta = "insert into Cliente (nombrecliente,apellidocliente,identificacioncliente,"
				+ "idsuscripcion,contrasnea,correo) "
				+ "values (?,?,?,?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, cliente.getNombreCliente());
		sentencia.setString(2, cliente.getApellidoCliente());
		sentencia.setInt(3, cliente.getIdentificacionCliente());
		sentencia.setInt(4,cliente.getTipoSuscripcion());
		sentencia.setString(5, cliente.getContrasena());
		sentencia.setString(6,cliente.getCorreo());
		sentencia.execute();
	}
	
	/**
	 * M�todo que permite cambiar la contrasena de un cliente a partir de su correo
	 * @param contrasena Nueva contrasena para su cuenta
	 * @param correo Correo registrado del cliente
	 * @throws SQLException Si existe alg�n error al conectarse a la base de datos
	 */
	public void cambiarContrasena(String contrasena, String correo) throws SQLException {
		String consulta ="update cliente set contrasnea = ? where correo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, contrasena);
		sentencia.setString(2, correo);
		sentencia.execute();
	}
	
	/**
	 * M�todo que permite modificar el correo del cliente
	 * @param correoNuevo Nuevo correo que se registrar� en la base de datos
	 * @param correoAntiguo Correo que estaba hasta ese momento en la base de datos
	 * @throws SQLException Si existe alg�n problema al conectarse en la base de datos.
	 */
	public void cambiarCorreo(String correoNuevo, String correoAntiguo) throws SQLException {
		String consulta ="update cliente set correo = ? where correo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, correoNuevo);
		sentencia.setString(2, correoAntiguo);
		sentencia.execute();
	}
	
	/**
	 * M�todo que permite cambiar el tipo de suscripci�n de un cliente
	 * @param tipo Nuevo tipo de suscripcion del cliente
	 * @param correo Crorreo del cliente
	 * @throws SQLException Si existe un error al conectarse a la base de datos
	 */
	public void cambiarSuscripcion(int tipo, String correo) throws SQLException {
		String consulta ="update cliente set idsuscripcion = ? where correo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, tipo);
		sentencia.setString(2, correo);
		sentencia.execute();
	}
	

	/**
	 * M�todo que verifica la existencia de un correo 
	 * @param correo correo a verificar
	 * @return true si el correo existe, false de lo contrario 
	 * @throws SQLException Si existe alg�n problema para conectar a la base de datos
	 */
	public boolean verificarCorreo(String correo) throws SQLException {
		String consulta = "select correo from Cliente"
				+ " where correo = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, correo);
		ResultSet resultado = sentencia.executeQuery();
		if(resultado.next()==true) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * M�todo que verifica que los datos ingresados correspondan a los datos registrados
	 * @param correo Correo del cliente
	 * @param contrasena contrasena del cliente
	 * @return True si los datos existen , false si no existen
	 * @throws SQLException Si existe alg�n problema para conectarse a la base de datos
	 */
	public boolean verificarIngreso(String correo, String contrasena) throws SQLException {
		String consulta = "select nombreCliente from Cliente"
				+ " where correo = ? "
				+ "and contrasnea= ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, correo);
		sentencia.setString(2, contrasena);
		ResultSet resultado = sentencia.executeQuery();
		if(resultado.next()==true) {
			return true;
		}else {
			return false;
		}	
	}
	
	/**
	 * M�todo que verifica que los datos ingresados correspondan a los datos registrados
	 * @param correo Correo del cliente
	 * @param contrasena contrasena del cliente
	 * @return El nombre del usuario que ingreso
	 * @throws SQLException Si existe alg�n problema para conectarse a la base de datos
	 */
	public String ingresar(String correo, String contrasena) throws SQLException {
		String consulta = "select nombreCliente||' '||apellidocliente from Cliente"
				+ " where correo = ? "
				+ "and contrasnea= ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, correo);
		sentencia.setString(2, contrasena);
		ResultSet resultado = sentencia.executeQuery();
		String res="";
		if(resultado.next()) {
			res = resultado.getString(1);
		}
		return res;
	}
}
