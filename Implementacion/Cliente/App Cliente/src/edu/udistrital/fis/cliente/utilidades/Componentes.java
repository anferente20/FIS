package edu.udistrital.fis.cliente.utilidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.udistrital.fis.cliente.utilidades.Cargador;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.core.IRegistroCliente;

public class Componentes {
	private HashMap<String,ArrayList<AbstractFrame>> presentacion;
	public Componentes(){
		cargarComponentes();
	}
	public void cargarComponentes() {
		//Conjuntos de frames agrupados por modulos
		HashMap<String,ArrayList<AbstractFrame>> presentacion = new HashMap<String, ArrayList<AbstractFrame>>(); 
				
		verificarJDBC();
				
		Cargador cc = new Cargador("componentes", ClassLoader.getSystemClassLoader());
		//Modulo registro cliente
		ArrayList<AbstractFrame> cliente = verificarModuloCliente(cc);
		if(cliente!=null) presentacion.put("Mi cuenta",cliente);
		
		this.presentacion = presentacion;
	}
	//Verificacion de modulo cliente
	private static ArrayList<AbstractFrame> verificarModuloCliente(Cargador cc) {
	Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IRegistroCliente.class.getName());
	if(cls!=null) {
		try {
			IRegistroCliente cliente = (IRegistroCliente)cls.newInstance();
			Funciones.mensajeConsola("Modulo cliente cargado");
			return cliente.getPresentacion();
		} catch (Exception e) {
			Funciones.mensajeConsola("Error al cargar modulo Cliente: "+e.getMessage());
			return null;
		}
	}
	else {
		Funciones.mensajeConsola("Modulo Cliente no encontrado");
		Funciones.mensajePantalla("Modulo Cliente no encontrado");
	}
	return null;
		}
	//Se verifica si el controlador de PostgreSQL está referenciado
		private static void verificarJDBC() {
			try {
				Class.forName("org.postgresql.Driver");
				Funciones.mensajeConsola("JDBC encontrado");
				verificarConexion();
			}
			catch(ClassNotFoundException e) {
				Funciones.mensajePantalla("JBDC faltante");
				System.exit(0);
			}
		}
			
		//Se verifica la conexión con la base de datos
		private static void verificarConexion() {
			try {
				GestorDB.getInstance();
				Funciones.mensajeConsola("Conexion exitosa a la base de datos");
			}
			catch(SQLException e) {
				Funciones.mensajeConsola("Descripción del error al conectarse con la base datos: "+e.getMessage());
				Funciones.mensajePantalla("Error, conexión fallida a la base de datos");
				System.exit(0);
			}
		}
		public HashMap<String, ArrayList<AbstractFrame>> getPresentacion() {
			return presentacion;
		}
		public void setPresentacion(HashMap<String, ArrayList<AbstractFrame>> presentacion) {
			this.presentacion = presentacion;
		}
		
}
