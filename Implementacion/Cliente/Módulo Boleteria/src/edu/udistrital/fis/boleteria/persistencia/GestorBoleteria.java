package edu.udistrital.fis.boleteria.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.Gestor;
import edu.udistrital.fis.boleteria.logica.Asiento;
import edu.udistrital.fis.boleteria.logica.Funcion;

public class GestorBoleteria extends Gestor{

	protected GestorBoleteria() throws SQLException {
		super();
	}
	
	/**
	 * Método que consulta las peliculas que están en cartelera
	 * @return Peliculas en cartelera
	 * @throws SQLException
	 */
	ResultSet consultarPeliculasCartelera() throws SQLException {
		FuncionesTiempo ft = new FuncionesTiempo();
		String consulta = 
				"select distinct "
				+ "pelicula.idpelicula, "
				+ "pelicula.nombrePelicula, "
				+ "to_char(pelicula.fechaEstreno,'dd/MM/yyyy') fechaestreno, "
				+ "pelicula.sinopsis, "
				+ "pelicula.img, "
				+ "pelicula.nombredirector, "
				+ "pelicula.duracion "
				+ "from "
				+ "pelicula, funcion "
				+ "where "
				+ "pelicula.idpelicula = funcion.idpelicula and "
				+ "funcion.fecha = '"+ft.DateToString(new Date())+"';";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
	
	/**
	 * Método que consulta las peliculas que aun no se han estrenado
	 * @return Peliculas proximas a estrenarse
	 * @throws SQLException
	 */
	ResultSet consultarPeliculasEstrenos() throws SQLException {
		FuncionesTiempo ft = new FuncionesTiempo();
		String consulta = 
				"select distinct "
				+ "pelicula.idpelicula, "
				+ "pelicula.nombrePelicula, "
				+ "to_char(pelicula.fechaEstreno,'dd/MM/yyyy') fechaestreno, "
				+ "pelicula.sinopsis, "
				+ "pelicula.img, "
				+ "pelicula.nombredirector, "
				+ "pelicula.duracion "
				+ "from "
				+ "pelicula "
				+ "where "
				+ "pelicula.fechaestreno>'"+ft.DateToString(new Date())+"';";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		return sentencia.executeQuery();
	}
	/**
	 * Método que consulta funciones de una pelicula para una fecha y cine específicos
	 * @param idCine ID del cine
	 * @param fecha Fecha de la funcion
	 * @param idPelicula Pelicula de la que se quieren consultar las funciones
	 * @return Funciones con los parámetros de busqueda
	 * @throws SQLException
	 */
	ResultSet consultarFuncionesByCinePeliculaFecha(int idCine, String fecha, int idPelicula) throws SQLException {
		String consulta = 
				"select "
				+ "funcion.idfuncion, "
				+ "funcion.hora, "
				+ "funcion.fecha, "
				+ "funcion.idsala, "
				+ "funcion.idpelicula "
				+ "from "
				+ "funcion, sala "
				+ "where "
				+ "funcion.fecha = '"+fecha+"' and "
				+ "funcion.idsala = sala.idsala and "
				+ "sala.idCine = ? and "
				+ "funcion.idpelicula = ? "
				+ "order by hora asc;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCine);
		sentencia.setInt(2, idPelicula);
		return sentencia.executeQuery();
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
	 * Método que consulta si un asiento está ocupado en una funcion en específico
	 * @param idfuncion ID de la funcion de la cual se quieren consultar los asientos ocupados
	 * @return Vacio si no está ocupado, un registro si está ocupado
	 * @throws SQLException
	 */
	ResultSet consultarAsientosOcupados(int idfuncion, String fila, String columna) throws SQLException {
		String consulta = 
				"select "
				+ "fila,columna "
				+ "from "
				+ "espacio "
				+ "where "
				+ "idfuncion = ? and "
				+ "fila = ? and "
				+ "columna = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idfuncion);
		sentencia.setString(2, fila);
		sentencia.setString(3, columna);
		return sentencia.executeQuery();
	}
	
	private ResultSet consultarPrecioBoleteria() throws SQLException {
		String consulta = "select"
				+ " precio, "
				+ "fecha,"
				+ "idhistoricoboleta "
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
	
	void comprarBoletas (Funcion funcion,ArrayList<Asiento> asientos,int idCliente, int cantidadBoletas) throws SQLException {
		insertarEspacio(funcion,asientos);
		insertarCompra(idCliente,cantidadBoletas);
		insertarBoletas(cantidadBoletas,funcion,asientos,idCliente);
	}
	
	private int consultarIDCompraByCliente(int idCliente) throws SQLException {
		String consulta = "select max(idcompra) idcompra from compra where idcliente = ?;";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCliente);
		ResultSet resultado = sentencia.executeQuery();
		resultado.next();
		return resultado.getInt("idcompra");
	}
	

	private void insertarBoletas(int cantidadBoletas, Funcion funcion, ArrayList<Asiento> asientos, int idCliente) throws SQLException {
		int cantidadEspacios = asientos.size();
			
		//ID de la compra
		int idCompra = consultarIDCompraByCliente(idCliente);
		//ID historico del precio de la boleta
		ResultSet resultado = consultarPrecioBoleteria();
		resultado.next();
		int idhistoricoboleta = resultado.getInt("idhistoricoboleta");
		
		for(int i=0;i<cantidadEspacios;i++) {
			Asiento asiento = asientos.get(i);
			String consulta = "insert into boleta (fila,columna,idfuncion,idcompra,idhistoricoboleta,idsala) values " + 
					"(?,?,?,?,?,?);";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);	
			sentencia.setString(1, asiento.getFila());
			sentencia.setString(2, asiento.getColumna());
			sentencia.setInt(3, funcion.getId());
			sentencia.setInt(4, idCompra);
			sentencia.setInt(5, idhistoricoboleta);
			sentencia.setInt(6, funcion.getIdSala());
			sentencia.execute();
		}
		
	}

	private void insertarCompra(int idCliente, int cantidadBoletas) throws SQLException {
		FuncionesTiempo ft = new FuncionesTiempo();
		
		//Precio de la boleta
		ResultSet precioBoleta = consultarPrecioBoleteria();
		precioBoleta.next();
		float precio = precioBoleta.getFloat("precio"); 
		
		String consulta = "insert into compra (fecha,idcliente,total) values ('"+ft.DateToString(new Date())+"',?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, idCliente);
		sentencia.setFloat(2, precio*cantidadBoletas);
		sentencia.execute();
		
	}

	private void insertarEspacio(Funcion funcion, ArrayList<Asiento> asientos) throws SQLException {
		int cantidadEspacios = asientos.size();
		for(int i=0;i<cantidadEspacios;i++) {
			Asiento asiento = asientos.get(i);
			
			String consulta = "insert into espacio (idfuncion,fila,columna,idsala) values (?,?,?,?);";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setInt(1,funcion.getId());
			sentencia.setString(2, asiento.getFila());
			sentencia.setString(3, asiento.getColumna());
			sentencia.setInt(4, funcion.getIdSala());
			sentencia.execute();
		}
		
		
	}
	
}
