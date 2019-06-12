package edu.udistrital.fis.funciones.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
				"sala.idSala = "+funcion.getIdSala()+" ;";
		sentencia = this.gestor.getConector().prepareStatement(consulta);
		ResultSet resultado = sentencia.executeQuery();
		//se verifica si se cruza con otra funcion
		if(!resultado.next()) { 
			//La función no se cruza con ninguna otra funcion
			consulta = "insert into "
					+ "funcion (idPelicula,fecha,hora,idSala,estado) "
					+ "values"
					+ "(?,'"+funcion.getFecha()+"','"+funcion.getHora()+"',?,1);";
			sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setInt(1,funcion.getPelicula().getId());
			sentencia.setInt(2,funcion.getIdSala());
			sentencia.execute();
			return true;
		}
		return false; //La funcion se cruza con otra función y no puede ser insertarda
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
}
