package edu.udistrital.fis.basicos.persistencia;


import java.sql.ResultSet;
import java.sql.SQLException;

public class FachadaCine{
	
	private GestorCine gestor;
	private static FachadaCine instance;
	
	private FachadaCine() throws SQLException{
		this.gestor = new GestorCine();
	}
	/**
	 * M�todo singleton
	 * @return Instancia �nica de la clase
	 * @throws SQLException Exception SQL
	 */
	public static FachadaCine getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaCine();
		}
		return instance;
	}
	/**
	 * M�todo que consulta todos los cines disponibles
	 * @return Cines registrados
	 * @throws SQLException Exception SQL
	 */
	public ResultSet consultarCines() throws SQLException {
		return gestor.consultarCines();
	}
	
	/**
	 * M�todo que obtiene el nombre de un cine dado su id
	 * @param idCine Identificador del cine
	 * @return Nombre del cine asociado al identificador
	 * @throws SQLException
	 */
	public String getNombreCine(int idCine) throws SQLException {
		return this.gestor.getNombreCine(idCine);
	}
	/**
	 * M�todo que obtiene el id del cine al que est� asociada una sala
	 * @param idSala Sala de la cual se quiere consultar el cine
	 * @return Identificador de la sala
	 * @throws SQLException
	 */
	public int getIdCine(int idSala) throws SQLException {
		return this.gestor.getIdCine(idSala);
	}
	
	public ResultSet login(String usuario, String pass) throws SQLException{
		return this.gestor.login(usuario, pass);
	}
	
	/**
	 * M�todo que verifica que los datos ingresados correspondan a los datos registrados
	 * @param correo Correo del cliente
	 * @param contrasena contrasena del cliente
	 * @return True si los datos existen , false si no existen
	 * @throws SQLException Si existe alg�n problema para conectarse a la base de datos
	 */
	public boolean verificarIngreso(String correo, String contrasena) throws SQLException {
		return this.gestor.verificarIngreso(correo, contrasena);
	}
	
	/**
	 * M�todo que verifica que los datos ingresados correspondan a los datos registrados
	 * @param correo Correo del cliente
	 * @param contrasena contrasena del cliente
	 * @return El nombre del usuario que ingreso
	 * @throws SQLException Si existe alg�n problema para conectarse a la base de datos
	 */
	public String ingresar(String correo, String contrasena) throws SQLException {
		return gestor.ingresar(correo, contrasena);
	}
	
}