package edu.udistrital.fis.cliente.utilidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.udistrital.fis.cliente.utilidades.Cargador;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.core.IRegistroCliente;
import edu.udistrital.fis.core.ICompra;

public class Componentes {
	private HashMap<String,ArrayList<AbstractFrame>> presentacion;
	public Componentes(String correo){
		cargarComponentes(correo);
	}
	public void cargarComponentes(String correo) {
		//Conjuntos de frames agrupados por modulos
		HashMap<String,ArrayList<AbstractFrame>> presentacion = new HashMap<String, ArrayList<AbstractFrame>>(); 
				
		verificarJDBC();
				
		Cargador cc = new Cargador("componentes", ClassLoader.getSystemClassLoader());
		//Modulo registro cliente
		ArrayList<AbstractFrame> cliente = verificarModuloCliente(cc,correo);
		if(cliente!=null) presentacion.put("Mi cuenta",cliente);
		
		//Modulo confiteria
		ArrayList<AbstractFrame> confiteria = verificarModuloConfiteria(cc,correo);
		if(confiteria!=null) presentacion.put("Confiteria",confiteria);
		
		this.presentacion = presentacion;
	}
	//Verificacion de modulo cliente
	private static ArrayList<AbstractFrame> verificarModuloCliente(Cargador cc,String correo) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IRegistroCliente.class.getName());
		if(cls!=null) {
			try {
				IRegistroCliente cliente = (IRegistroCliente)cls.newInstance();
				Funciones.mensajeConsola("Modulo cliente cargado");
				return cliente.getPresentacion(correo);
			} catch (Exception e) {
				e.printStackTrace();
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
	
	//Verificacion de modulo confiteria
	private static ArrayList<AbstractFrame> verificarModuloConfiteria(Cargador cc,String correo) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(ICompra.class.getName());
		if(cls!=null) {
			try {
				ICompra compra = (ICompra)cls.newInstance();
				Funciones.mensajeConsola("Modulo Compra cargado");
				return compra.getPresentacion(correo);
			} catch (Exception e) {
				e.printStackTrace();
				Funciones.mensajeConsola("Error al cargar modulo Compra: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo Compra no encontrado");
			Funciones.mensajePantalla("Modulo Compra no encontrado");
		}
		return null;
	}
	//Se verifica si el controlador de PostgreSQL est� referenciado
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
			
		//Se verifica la conexi�n con la base de datos
		private static void verificarConexion() {
			try {
				GestorDB.getInstance();
				Funciones.mensajeConsola("Conexion exitosa a la base de datos");
			}
			catch(SQLException e) {
				Funciones.mensajeConsola("Descripci�n del error al conectarse con la base datos: "+e.getMessage());
				Funciones.mensajePantalla("Error, conexi�n fallida a la base de datos");
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
