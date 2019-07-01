package edu.udistrital.fis.funciones.logica;

import java.sql.SQLException;
import edu.udistrital.fis.basicos.logica.Cine;
import java.util.ArrayList;
import java.util.Date;
import java.time.Period;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.presentacion.ProgressBar;

/**
 * Clase que es la primera implementacion de la interfaz AlgoritmoFunciones para el algoritmo de creacion de funciones
 * @author Andres Arias
 */
public class AlgoritmoPrimero extends AlgoritmoFunciones{

	private FuncionesTiempo ft;
	private ProgressBar pg;
	
	public AlgoritmoPrimero() {
		ft = new FuncionesTiempo();
		pg = new ProgressBar(Cine.CINES, "Creando funciones", "Creando funciones para Cine 1"); //barra de progreso
	}
	@Override
	/**
	 * Método que crea funciones para una película dada
	 * @param pelicula Pelicula a la cual se le crearán las funciones
	 */
	public void crearFunciones(Pelicula pelicula) {
		Funciones.mensajeConsola("Creando funciones");
		try
		{
			Period tiempoCartelera; //tiempo que estara la pelicula en cartelera
			int funcionesDia = pelicula.getFuncionesPorDia(); //funciones por dia
			int cine,funciones,consecutivoSala;
			Date fecha;
			String hora; //formato HH:ss
			//cines
			for(cine=1;cine<=Cine.CINES;cine++) {
				fecha = this.ft.getFecha(pelicula.getFechaEstreno());
				tiempoCartelera = this.ft.intervalToPeriod(pelicula.getTiempoCartelera());
				//tiempo en cartelera de la pelicula
				while(!tiempoCartelera.isZero()) {
					consecutivoSala=0;
					hora = this.ft.intervalToTime(Pelicula.HORA_MAS_TEMPRANO);
					//funciones por dia
					for(funciones=1;funciones<=funcionesDia;funciones++) {
						boolean bandera = false;
						if(consecutivoSala==Cine.CINES-1) consecutivoSala=Cine.CINES;
						else consecutivoSala = (consecutivoSala+1)%Cine.CINES; 
						//se verifica si la funcion puede ingresarse en alguna sala en una hora en específico
						while(bandera==false) {
							bandera = verificarEnSalas(pelicula, fecha, hora, cine, consecutivoSala);
							if(bandera==false) hora = this.ft.modulo(this.ft.intervalToTime(Pelicula.HORA_MAS_TARDE), //Se intenta mirar una hora más tarde
									this.ft.intervalToTime(Pelicula.HORA_MAS_TEMPRANO), 
									this.ft.sumarHoras(hora, "1:00"));
						}
						//hora de inicio de la siguiente funcion del dia
						hora = this.ft.modulo(this.ft.intervalToTime(Pelicula.HORA_MAS_TARDE), 
								this.ft.intervalToTime(Pelicula.HORA_MAS_TEMPRANO), 
								this.ft.sumarHoras(hora, this.ft.sumarHoras(this.ft.intervalToTime(pelicula.getDuracion()), "00:30")));
					}
					//se le resta un día
					tiempoCartelera = tiempoCartelera.minusDays(1);
					//se suma un día a la fecha
					fecha = this.ft.sumarDiasFecha(fecha, 1);
				}
				Funciones.mensajeConsola("Funciones para cine "+cine+" creadas");
				pg.cargarBarra("Creando funciones para Cine "+String.valueOf(cine+1));
			}
			Funciones.mensajeConsola("Funciones creadas");
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase AlgoritmoPrimero: "+e.getMessage());
			Funciones.mensajePantalla("Error, la operación no pudo llevarse acabo");
		}
	}
	/**
	 * Método que verifica si una funcion puede ser agregada a una hora y en una sala en especifico.
	 * Si no puede ser insertada, se probará en la siguiente sala a la misma hora
	 * @param pelicula
	 * @param fecha Fecha con la que se quiere ingresar la funcion
	 * @param hora Hora con la que se quiere ingresar la funcion
	 * @param cine
	 * @param consecutivoSala Consecutivo de la sala(<=CINE.cines)
	 * @return true si la funcion pudo ser insertada, false si en ninguna sala pudo insertarse
	 * @throws SQLException
	 */
	private boolean verificarEnSalas(Pelicula pelicula,Date fecha,String hora,int cine,int consecutivoSala) throws SQLException {
		//La funcion no pudo ser ubicada en ninguna de las 10 salas del cine
		if(consecutivoSala==Cine.SALAS+1) {
			return false;
		}
		FachadaPelicula fachada = FachadaPelicula.getInstance();
		boolean bandera = fachada.insertarFuncion(new Funcion(pelicula, fecha, 
				this.ft.timeToInterval(hora), fachada.getIdSala(consecutivoSala, cine)));
		if(bandera==false) { //La función se cruza con otra, debe mirarse en la siguiente sala
			return verificarEnSalas(pelicula, fecha, hora, cine, consecutivoSala+1);
		}
		return true;
	}

	@Override
	protected void insertarFunciones(ArrayList<Funcion> funciones,Pelicula pelicula) {

	}
}
