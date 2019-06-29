package edu.udistrital.fis.funciones.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.Gestor;
import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.logica.Pelicula;

/**
 * Clase encargada directamente de realizar operaciones sobre la base de datos en las tablas Pelicula y Funcion
 * @author Andres Arias
 */

public class GestorPelicula extends Gestor{

	FuncionesTiempo ft;
	
	protected GestorPelicula() throws SQLException {
		super();
		ft = new FuncionesTiempo();
	}
	
	/**
	 * Método que inserta un registro de Película en la base de datos
	 * @param pelicula Pelicula que será insertada
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	void insertarPelicula(Pelicula pelicula) throws SQLException{
		try {
			String consulta = 
					"insert into "
					+ "pelicula (nombrePelicula,sinopsis,fechaEstreno,duracion,img,nombreDirector) "
					+ "values"
					+ "(?,?,'"+pelicula.getFechaEstreno()+"','"+pelicula.getDuracion()+"',?,?);";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setString(1, pelicula.getNombre());
			sentencia.setString(2, pelicula.getSinopsis());
			sentencia.setBinaryStream(3,new FileInputStream(pelicula.getImg()),pelicula.getImg().length());
			sentencia.setString(4, pelicula.getDirector());
			sentencia.execute();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Método que inserta un registro de Funcion en la base de datos
	 * @param funcion Funcion que será insertada
	 * @return true si la función fue insertada con éxito, false si la función se cruza con otra función ya existente
	 * @throws SQLException
	 */
	boolean insertarFuncion(Funcion funcion) throws SQLException {
		ResultSet resultado = this.verificarFuncion(funcion);
		//se verifica si se cruza con otra funcion
		if(!resultado.next()) { 
			//La función no se cruza con ninguna otra funcion
			String consulta = "insert into "
					+ "funcion (idPelicula,fecha,hora,idSala,estado) "
					+ "values"
					+ "(?,'"+funcion.getFecha()+"','"+funcion.getHora()+"',?,1);";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setInt(1,funcion.getPelicula().getId());
			sentencia.setInt(2,funcion.getIdSala());
			sentencia.execute();
			return true;
		}
		return false; //La funcion se cruza con otra función y no puede ser insertarda
	}
	/**
	 * Método que verifica si una funcion a insertar se cruza con otras
	 * @param funcion Funcion que se quiere insertar
	 * @return Devuelve todas las funciones con las que se cruza la funcion a insertar
	 * @throws SQLException
	 */
	private ResultSet verificarFuncion(Funcion funcion) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Pelicula pel = funcion.getPelicula();
		PreparedStatement sentencia;
		//Se verifica que la funcion a insertar no se cruce con otra funcion
		String consulta = 
				"select\n" + 
				"funcion.idFuncion,\n" + 
				"pelicula.nombrePelicula,\n" + 
				"pelicula.duracion,\n" + 
				"funcion.hora horaInicio,\n" + 
				"funcion.fecha,\n" + 
				"(pelicula.duracion + funcion.hora) horaTerminacion,\n" + 
				"sala.consecutivo sala,\n" + 
				"cine.nombreCine cine\n" + 
				"from\n" + 
				"Funcion,Pelicula,sala,cine\n" + 
				"where\n" + 
				"funcion.idSala = sala.idSala and\n" + 
				"sala.idCine = cine.idCine and\n" + 
				"funcion.idPelicula = pelicula.idPelicula and\n" + 
				"funcion.fecha = '"+sdf.format(funcion.getFecha())+"' and\n" + 
				"(\n" + 
				"	--Verifica si la hora de inicio de la funcion a ingresar esta dentro de otra funcion\n" + 
				"	(\n" + 
				"		'"+funcion.getHora()+"'>=funcion.hora and\n" + 
				"		'"+funcion.getHora()+"'<=(pelicula.duracion + funcion.hora)\n" + 
				"	)\n" + 
				"	or\n" + 
				"	--Verifica si la hora de inicio de una funcion esta dentro de la funcion a ingresar\n" + 
				"	(\n" + 
				"		funcion.hora>='"+funcion.getHora()+"' and\n" + 
				"		funcion.hora<=( interval'"+funcion.getHora()+"'+  interval '"+pel.getDuracion()+"')\n" + 
				"	)\n" + 
				"	or\n" + 
				"	--Verifica si una funcion a ingresar esta dentro de otra funcion\n" + 
				"	(\n" + 
				"		'"+funcion.getHora()+"'>=funcion.hora and\n" + 
				"		'"+funcion.getHora()+"'<=(funcion.hora+pelicula.duracion) and\n" + 
				"		(interval'"+funcion.getHora()+"'+interval'"+pel.getDuracion()+"')>=funcion.hora and\n" + 
				"		(interval'"+funcion.getHora()+"'+interval'"+pel.getDuracion()+"')<=(funcion.hora+pelicula.duracion)\n" + 
				"	)\n" + 
				"	or\n" + 
				"	--Verifica si una funcion a ingresar contiene a otra funcion\n" + 
				"	(\n" + 
				"		funcion.hora>='"+funcion.getHora()+"' and\n" + 
				"		funcion.hora<=(interval'"+funcion.getHora()+"'+interval'"+pel.getDuracion()+"') and\n" + 
				"		(funcion.hora+pelicula.duracion)>='"+funcion.getHora()+"' and\n" + 
				"		(funcion.hora+pelicula.duracion)<=(interval'"+funcion.getHora()+"'+interval'"+pel.getDuracion()+"')\n" + 
				"	)\n" + 
				")\n" + 
				"and\n" + 
				"sala.idSala = "+funcion.getIdSala()+" "+
				"and funcion.estado = 1;";
		sentencia = this.gestor.getConector().prepareStatement(consulta);
		ResultSet resultado = sentencia.executeQuery();
		return resultado;
	}
	
	/**
	 * Método que obtiene el ID de una sala dado su consecutivo y el id del cine al que pertence
	 * @param consecutivo
	 * @param idCine
	 * @return ID de la sala
	 * @throws SQLException
	 */
	int getIdSala(int consecutivo,int idCine) throws SQLException {
		String consulta = 
				"select\n"
				+ "sala.idSala\n"
				+ "from\n"
				+ "sala,cine\n"
				+ "where\n"
				+ "sala.idCine = cine.idCine and\n"
				+ "sala.consecutivo = ? and\n"
				+ "cine.idCine = ?;\n";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, consecutivo);
		sentencia.setInt(2,idCine);
		ResultSet resultado = sentencia.executeQuery();
		if(resultado.next()) {
			return resultado.getInt(1);
		}
		return 0;
	}
	/**
	 * Método que retorna el consecutivo de una sala dentro de una cine dado si ID
	 * @param idSala Identificador de la sala
	 * @return Consecutivo de la sala dentro de su cine
	 * @throws SQLException 
	 */
	int getConsecutivoSala(int idSala) throws SQLException {
		String consulta = "select consecutivo from sala where idSala = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idSala);
		ResultSet resultado = sentencia.executeQuery();
		resultado.next();
		return resultado.getInt("consecutivo");
	}
	/**
	 * Método que cancela una funcion 
	 * @param idFuncion Identificador de la funcion que se va a cancelar
	 * @throws SQLException
	 */
	void cancelarFuncion (int idFuncion) throws SQLException {
		String consulta = "update funcion set estado = 0 where idFuncion = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1,idFuncion);
		sentencia.execute();
	}
	/**
	 * Método que verifica si una funcion puede ser modificada, es decir, verifica si la nueva informacion
	 * hace que la funcion se cruce con otra
	 * @param funcion Funcion con la nueva informacion y que se quiere modificar
	 * @return true si la modificacion pudo hacerse, false si no pudo hacerse
	 * @throws SQLException
	 */
	boolean verificarModificacion(Funcion funcion) throws SQLException {
		ResultSet resultado = verificarFuncion(funcion);
		if(!resultado.next()) {
			//La modificacion se puede hacer
			modificarFuncion(funcion);
		}
		else {
			int idFuncion = resultado.getInt("idfuncion");
			if(idFuncion==funcion.getIdFuncion()) {
				//Se cruza consigo misma, por tanto la modificacion se puede hacer
				modificarFuncion(funcion);
			}
			else {
				//la modificacion no se puede hacer
				return false;
			}
		}
		return true;
	}
	/**
	 * Funcion que ejecuta una actualizacion sobre la tabla funcion
	 * @param funcion Funcion con la nueva informacion
	 * @throws SQLException
	 */
	private void modificarFuncion(Funcion funcion) throws SQLException {
		FuncionesTiempo ft = new FuncionesTiempo();
		String consulta = "update funcion set idSala = ?, fecha = '"+ft.DateToString(funcion.getFecha())+"', "
				+ "hora = '"+funcion.getHora()+"' where idfuncion = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, funcion.getIdSala());
		sentencia.setInt(2, funcion.getIdFuncion());
		sentencia.execute();
	}
	/**
	 * Funcion que consulta funciones por pelicula en una fecha y cine en específico
	 * @param fecha Fecha en formato dd/MM/yyyy
	 * @param idPelicula Identificacion de la pelicula
	 * @param idCine Identificacion del cine
	 * @return Funciones que cumplan con los criterios de busqueda
	 * @throws SQLException
	 */
	ResultSet consultarFuncionesByPeliculaByCine(String fecha,int idPelicula,int idCine) throws SQLException {
		String consulta = 
				"select "
				+ "pelicula.nombrePelicula, "
				+ "funcion.hora, "
				+ "funcion.fecha, "
				+ "sala.idsala, "
				+ "pelicula.duracion, "
				+ "funcion.idfuncion, "
				+ "cine.nombreCine, "
				+ "pelicula.img imagenPelicula "
				+ "from "
				+ "pelicula,funcion,sala,cine "
				+ "where "
				+ "pelicula.idpelicula = funcion.idpelicula and "
				+ "funcion.idsala = sala.idSala and "
				+ "sala.idcine = cine.idcine and "
				+ "funcion.fecha = '"+fecha+"' and "
				+ "pelicula.idPelicula = ? and "
				+ "cine.idcine = ? and "
				+ "funcion.estado = 1 "
				+ "order by funcion.hora asc;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idPelicula);
		sentencia.setInt(2, idCine);
		return sentencia.executeQuery();
	}
	/**
	 * Método que consulta funciones en una fecha y sala de un cine en específico
	 * @param fecha Fecha en formato dd/MM/yyyy
	 * @param consecutivoSala Consecutivo de la sala dentro de su cine
	 * @param idCine Identificador del cine 
	 * @return Funciones que cumplen con los criterios de búsqueda
	 * @throws SQLException 
	 */
	ResultSet consultarFuncionesBySalaByFecha(String fecha,int consecutivoSala,int idCine) throws SQLException {
		String consulta = 
				"select "
				+ "funcion.hora, "
				+ "pelicula.nombrePelicula, "
				+ "pelicula.duracion, "
				+ "funcion.fecha, "
				+ "funcion.idfuncion, "
				+ "sala.consecutivo, "
				+ "sala.idsala, "
				+ "cine.nombreCine, "
				+ "pelicula.img imagenPelicula "
				+ "from "
				+ "funcion, pelicula, sala, cine "
				+ "where "
				+ "funcion.idpelicula = pelicula.idpelicula and "
				+ "funcion.idsala = sala.idsala and "
				+ "sala.idcine = cine.idcine and "
				+ "funcion.fecha = '"+fecha+"' and "
				+ "sala.consecutivo = ? and "
				+ "cine.idcine = ? "
				+ "and funcion.estado = 1 "
				+ "order by funcion.hora asc;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, consecutivoSala);
		sentencia.setInt(2, idCine);
		return sentencia.executeQuery();
	}
	
	/**
	 * Método que consulta todas las películas que tengan funciones vigentes
	 * @return Nombre de las películas vigentes
	 * @throws SQLException
	 */
	ResultSet consultarPeliculasVigentes() throws SQLException {
		String consulta = 
				"select distinct "
				+ "pelicula.idpelicula || ' - ' || pelicula.nombrePelicula || ' - Estreno: ' || pelicula.fechaestreno  "
				+ "from "
				+ "pelicula, funcion "
				+ "where "
				+ "pelicula.idpelicula = funcion.idpelicula and "
				+ "funcion.fecha >= now()";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
	
	ResultSet consultarPrecioBoleteria() throws SQLException {
		String consulta = "select"
				+ " precio, "
				+ "fecha "
				+ "from "
				+ "historicopreciosboleta "
				+ "where "
				+ "fecha ="
				+ "("
				+ "select "
				+ "max(fecha) "
				+ "from "
				+ "historicopreciosboleta"
				+ ");";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
	
	void insertarPrecioBoleta(float precio) throws SQLException {
		FuncionesTiempo ft = new FuncionesTiempo();
		String consulta = 
				"insert into "
				+ "historicopreciosboleta "
				+ "(precio,fecha) "
				+ "values "
				+ "(?,'"+ft.DateToString(new Date())+"');";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setFloat(1, precio);
		sentencia.execute();
	}
}
