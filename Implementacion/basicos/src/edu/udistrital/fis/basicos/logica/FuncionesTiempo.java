package edu.udistrital.fis.basicos.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class FuncionesTiempo {
	
	private SimpleDateFormat sdfFecha; //formato para fecha
	private SimpleDateFormat sdfTiempo; //formato para tiempo
	
	public FuncionesTiempo() {
		sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
		sdfTiempo = new SimpleDateFormat("HH:mm");
	}
	/**
	 * Metodo que suma una cantidad de dias a una fecha y devuelve la nueva fecha
	 * @param fecha fecha a la que se le suamaran dias
	 * @param numeroDias numero de dias que se le sumaran a la fecha
	 * @return nueva fecha con los nuevos dias sumadows
	 */
	public Date sumarDiasFecha(Date fecha, int numeroDias) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.add(Calendar.DATE, numeroDias);
		return c.getTime();
	}
	/**
	 * Método que obtiene un objeto de tipo Date dada una fecha en formato dd/MM/yyy
	 * @param fecha Fecha en formato dd/MM/yyyy
	 * @return Fecha obtenida
	 */
	public Date getFecha(String fecha) {
		try {
			return this.sdfFecha.parse(fecha);
		} catch (ParseException e) {
			Funciones.mensajeConsola("Clase AlgoritmoPrimero: "+e.getMessage());
			Funciones.mensajePantalla("La operación no pudo llevarse acabo");
		}
		return null;
	}
	/**
	 * Método que convierte un valor de tiempo en horas y minutos en formato'interval' (PostgreSQL) a formato HH:mm
	 * @param tiempo Tiempo en horas y minutos expresado en formato 'interval' (PostgreSQL)
	 * @return Tiempo en formato HH:mm
	 */
	public String intervalToTime(String tiempo) {
		String[] cadena = tiempo.split(" ");
		String horas = cadena[0];
		String minutos = cadena[2];
		return horas+":"+minutos;
	}
	/**
	 * Método que convierte un valor de tiempo en horas y minutos en formato hh:mm a formato'interval' (PostgreSQL)
	 * @param tiempo Tiempo en horas y minutos expresado en formato hh:mm
	 * @return Tiempo en formato 'interval' (PostgreSQL)
	 */
	public String timeToInterval(String tiempo) {
		String[] cadena = tiempo.split(":");
		String horas = cadena[0];
		String minutos = cadena[1];
		return horas+" hours "+minutos+" minutes";
	}
	/**
	 * Método que obtiene los meses de un valor de tiempo en meses y dias en formato 'interval' (PostgreSQL)
	 * @param period Tiempo en meses y días expresado en formato 'interval' (PostgreSQL)
	 * @return Cantidad de meses
	 */
	public int getMonths(String period) {
		String cadena[] = period.split(" ");
		return Integer.parseInt(cadena[0]);
	}
	/**
	 * Método que obtiene los dias de un valor de tiempo en meses y dias en formato 'interval' (PostgreSQL)
	 * @param period Tiempo en meses y días expresado en formato 'interval' (PostgreSQL)
	 * @return Cantidad de dias
	 */
	public int getDays(String period) {
		String cadena[] = period.split(" ");
		return Integer.parseInt(cadena[2]);
	}
	/**
	 * Metodo que suma dos cantidades en horas y minutos en formato hh:mm
	 * @param cantidad1 en formato hh:mm
	 * @param cantidad2 en formato hh:mm
	 * @return Resultado de sumar las 2 cantidades en formato hh:mm
	 */
	public String sumarHoras(String cantidad1,String cantidad2) {
		try {
			String cadena[] = cantidad2.split(":");
			Date tiempo = this.sdfTiempo.parse(cantidad1); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(tiempo);
			cal.add(Calendar.HOUR,Integer.parseInt(cadena[0]));
			cal.add(Calendar.MINUTE, Integer.parseInt(cadena[1]));
			return sdfTiempo.format(cal.getTime());
		}
		catch(Exception e) {
			Funciones.mensajeConsola("Clase AlgoritmoPrimero: "+e.getMessage());
		}
		return null;
	}
	/**
	 * Método que convierte un objeto tipo fecha a una cadena en formato dd/MM/yyyy
	 * @param fecha Objeto de tipo fecha
	 * @return Cadena con la fecha en formato dd/MM/yyyy
	 */
	public String DateToString(Date fecha) {
		return this.sdfFecha.format(fecha);
	}
	/**
	 * Método que convierte una fecha en formato dd/MM/yyyy a un objeto tipo fecha
	 * @param fecha Fecha en formato dd/MM/yyyy
	 * @return Objeto de tipo fecha
	 * @throws ParseException Error al convertir de String a Date
	 */
	public Date StringToDate(String fecha) throws ParseException {
		return this.sdfFecha.parse(fecha);
	}
	/**
	 * Método que convierte un periodo de tiempo expresado en formato 'interval' (PostgreSQL) en un objeto de tipo Period expresado todo en días
	 * @param periodo Periodo de tiempo expresado en formato 'interval' (PostgreSQL)
	 * @return Objeto de tipo Period expresado todo en dias
	 */
	public Period intervalToPeriod(String periodo) {
		String cadena[] = periodo.split(" ");
		int meses = Integer.parseInt(cadena[0]);
		int dias = Integer.parseInt(cadena[2]);
		dias += meses*30;
		Period period = Period.of(0,0,dias);
		return period;
	}
	/**
	 * Método que hace una 'división modular' de una hora
	 * @param limiteSup Limite superior en HH:ss
	 * @param limiteInf Limite inferior en HH:ss
	 * @param valor Valor a hacer 'division modular' en HH:ss
	 * @return 'Division modular' en HH:ss
	 */
	public String modulo(String limiteSup,String limiteInf,String valor) {
		try {
			Date sup = this.sdfTiempo.parse(limiteSup);
			Date inf = this.sdfTiempo.parse(limiteInf);
			Date vFecha = this.sdfTiempo.parse(valor);
			if(vFecha.compareTo(sup)>0 && vFecha.compareTo(inf)<0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(vFecha);
				calendar.add(Calendar.MINUTE,-sup.getMinutes());
				calendar.add(Calendar.HOUR,inf.getHours());
				calendar.add(Calendar.MINUTE,inf.getMinutes());
				return this.sdfTiempo.format(calendar.getTime());
			}
			return valor;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
