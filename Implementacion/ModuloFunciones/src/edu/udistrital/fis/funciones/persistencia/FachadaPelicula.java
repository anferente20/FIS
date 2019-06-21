package edu.udistrital.fis.funciones.persistencia;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.logica.Pelicula;


/**
 * Clase intermedia entre la logica y la persistencia para la inserci�n de registros en las tablas Pelicula y Funciones
 * @author Andres Arias
 *
 */
public class FachadaPelicula {
	private GestorPelicula gestor;
	private static FachadaPelicula instance;
	
	private FachadaPelicula() throws SQLException{
		this.gestor = new GestorPelicula();
	}
	/**
	 * M�todo singleton
	 * @return Instancia �nica de la clase
	 * @throws SQLException
	 */
	public static FachadaPelicula getInstance() throws SQLException{
		if(instance==null) {
			instance = new FachadaPelicula();
		}
		return instance;
	}
	/**
	 * M�todo que inserta un registro de Pel�cula en la base de datos
	 * @param pelicula Pelicula que ser� insertada
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public void insertarPelicula (Pelicula pelicula) throws SQLException{
		this.gestor.insertarPelicula(pelicula);
	}
	/**
	 * M�todo que inserta un registro de Funcion en la base de datos
	 * @param funcion Funcion que ser� insertada
	 * @return true si la funci�n fue insertada con �xito, false si la funci�n se cruza con otra funci�n ya existente
	 * @throws SQLException
	 */
	public boolean insertarFuncion(Funcion funcion) throws SQLException {
		return this.gestor.insertarFuncion(funcion);
	}
	/**
	 * M�todo que obtiene el ID de una sala dado su consecutivo y el id del cine al que pertence
	 * @param consecutivo
	 * @param idCine
	 * @return ID de la sala
	 * @throws SQLException
	 */
	public int getIdSala(int consecutivo,int idCine) throws SQLException {
		return this.gestor.getIdSala(consecutivo, idCine);
	}
	
	/**
	 * M�todo que cancela una funcion 
	 * @param idFuncion Identificador de la funcion que se va a cancelar
	 * @throws SQLException
	 */
	public void cancelarFuncion (int idFuncion) throws SQLException {
		this.gestor.cancelarFuncion(idFuncion);
	}
	
	/**
	 * M�todo que actualiza la informacion de una funcion si es posible
	 * hace que la funcion se cruce con otra
	 * @param funcion Funcion con la nueva informacion y que se quiere modificar
	 * @return true si la modificacion pudo hacerse, false si no pudo hacerse
	 * @throws SQLException
	 */
	public boolean modificarFuncion(Funcion funcion) throws SQLException {
		return this.gestor.verificarModificacion(funcion);
	}
	
	/**
	 * Funcion que consulta funciones por pelicula en una fecha y cine en espec�fico
	 * @param fecha Fecha en formato dd/MM/yyyy
	 * @param idPelicula Identificacion de la pelicula
	 * @param idCine Identificacion del cine
	 * @return Funciones que cumplan con los criterios de busqueda
	 * @throws SQLException
	 */
	public ResultSet consultarFuncionesByPeliculaByCine(String fecha,int idPelicula,int idCine) throws SQLException {
		return this.gestor.consultarFuncionesByPeliculaByCine(fecha, idPelicula, idCine);
	}
	
	/**
	 * M�todo que consulta funciones en una fecha y sala de un cine en espec�fico
	 * @param fecha Fecha en formato dd/MM/yyyy
	 * @param consecutivoSala Consecutivo de la sala dentro de su cine
	 * @param idCine Identificador del cine 
	 * @return Funciones que cumplen con los criterios de b�squeda
	 * @throws SQLException 
	 */
	public ResultSet consultarFuncionesBySalaByFecha(String fecha,int consecutivoSala,int idCine) throws SQLException {
		return this.gestor.consultarFuncionesBySalaByFecha(fecha, consecutivoSala, idCine);
	}
	
	/**
	 * M�todo que consulta todas las pel�culas que tengan funciones vigentes
	 * @return Nombre de las pel�culas vigentes
	 * @throws SQLException
	 */
	public ResultSet consultarPeliculasVigentes() throws SQLException {
		return this.gestor.consultarPeliculasVigentes();
	}
	/**
	 * M�todo que retorna el consecutivo de una sala dentro de una cine dado si ID
	 * @param idSala Identificador de la sala
	 * @return Consecutivo de la sala dentro de su cine
	 * @throws SQLException 
	 */
	public int getConsecutivoSala(int idSala) throws SQLException {
		return this.gestor.getConsecutivoSala(idSala);
	}
}
