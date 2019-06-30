package edu.udistrital.fis.basicos.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorCine extends Gestor{
	
	public GestorCine() throws SQLException{
		super();
	}
	/**
	 * Método que consulta todos los cines disponibles
	 * @return Cines registrados
	 */
	ResultSet consultarCines(){
		ResultSet cines = null;
		try {
			String consulta = "select nombreCine from Cine order by idCine asc;";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			cines = sentencia.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Clase GestorCine: "+ e.getMessage());
		}
		return cines;
	}
	/**
	 * Método que obtiene el nombre de un cine dado su id
	 * @param idCine Identificador del cine
	 * @return Nombre del cine asociado al identificador
	 * @throws SQLException
	 */
	String getNombreCine(int idCine) throws SQLException {
		String consulta = "select nombreCine from cine where idCine = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCine);
		ResultSet resultado = sentencia.executeQuery();
		resultado.next();
		return resultado.getString("nombreCine");
	}
	/**
	 * Método que obtiene el id del cine al que está asociada una sala
	 * @param idSala Sala de la cual se quiere consultar el cine
	 * @return Identificador de la sala
	 * @throws SQLException
	 */
	int getIdCine(int idSala) throws SQLException {
		String consulta = "select idcine from sala where idSala = ?";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idSala);
		ResultSet resultado = sentencia.executeQuery();
		resultado.next();
		return resultado.getInt("idcine");
	}
	
	ResultSet login(String usuario, String pass) throws SQLException {
		String consulta = "select idtipousuario, idcineencargado from usuario where idusuario = ? and contrasena = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setString(1, usuario);
		sentencia.setString(2, pass);
		return sentencia.executeQuery();
	}
	
	/**
	 * Método que verifica que los datos ingresados correspondan a los datos registrados
	 * @param correo Correo del cliente
	 * @param contrasena contrasena del cliente
	 * @return True si los datos existen , false si no existen
	 * @throws SQLException Si existe algún problema para conectarse a la base de datos
	 */
	boolean verificarIngreso(String correo, String contrasena) throws SQLException {
		String consulta = "select nombreCliente from Cliente"
				+ " where correo = ? "
				+ "and contrasena= ?";
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
	 * Método que verifica que los datos ingresados correspondan a los datos registrados
	 * @param correo Correo del cliente
	 * @param contrasena contrasena del cliente
	 * @return El nombre del usuario que ingreso
	 * @throws SQLException Si existe algún problema para conectarse a la base de datos
	 */
	public String ingresar(String correo, String contrasena) throws SQLException {
		String consulta = "select nombreCliente||' '||apellidocliente from Cliente"
				+ " where correo = ? "
				+ "and contrasena= ?";
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

