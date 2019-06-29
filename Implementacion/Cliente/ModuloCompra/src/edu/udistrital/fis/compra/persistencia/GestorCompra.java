package edu.udistrital.fis.compra.persistencia;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.udistrital.fis.basicos.persistencia.Gestor;

public class GestorCompra extends Gestor {

	protected GestorCompra() throws SQLException {
		super();
	}

	/**
	 * Método que registra una compra en el la base de datos
	 * @param total Total de la compra
	 * @param idCliente id del cliente que realizó la compra
	 * @throws SQLException Si hay problemas para conectar a la base de datos
	 */
	public void registrarCompra(int total,int idCliente,int id) throws SQLException {
		Calendar fecha = new GregorianCalendar();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
       String fec = dia+"/"+mes+"/"+año;
       String hor = hora+":"+minuto+":"+segundo;
       
       
       
       String consulta = "insert into Compra (idcompra,idcliente,total,fecha,hora) values"
				+ "(?,?,?,?,?);";
		PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
		sentencia.setInt(1, id);
		sentencia.setInt(2, idCliente);
		sentencia.setInt(3, total);
		sentencia.setString(4, fec);
		sentencia.setString(5, hor);
		sentencia.execute();
	}
	
	/**
	 * Método para registrar un combocompra
	 * @param idCompra ide de la compra relacionada
	 * @param cantidad cantidad de combos comprados
	 * @param idCombo id del combo comprado
	 * @param d subtotal obtenido
	 * @throws SQLException Si hay problema para conectarse a la base de datos
	 */
	public void registrarCompraCombo(int idCompra,int cantidad, int idCombo,double d) throws SQLException {
		 String consulta = "insert into combocompra (idcompra,idcombo,subtotal,cantidad) values"
					+ "(?,?,?,?);";
			PreparedStatement sentencia = this.gestor.getConector().prepareStatement(consulta);
			sentencia.setInt(1, idCompra);
			sentencia.setInt(2, idCombo);
			sentencia.setDouble(3, d);
			sentencia.setInt(4, cantidad);
			sentencia.execute();
	}
}
