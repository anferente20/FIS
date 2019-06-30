package edu.udistrital.fis.cliente.utilidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import edu.udistrital.fis.cliente.utilidades.Cargador;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.core.IRegistroCliente;
import edu.udistrital.fis.core.IBoleteria;
import edu.udistrital.fis.core.ICompra;

public class Componentes {
	
	private HashMap<String,ArrayList<AbstractFrame>> presentacion;
	private static Cargador cc = new Cargador("componentes", ClassLoader.getSystemClassLoader());;

	
	public Componentes(String correo, int idCliente){
		cargarComponentes(correo,idCliente);
		
	}
	public void cargarComponentes(String correo, int idCliente) {
		//Conjuntos de frames agrupados por modulos
		HashMap<String,ArrayList<AbstractFrame>> presentacion = new HashMap<String, ArrayList<AbstractFrame>>(); 
				
		verificarJDBC();
				
		//Modulo registro cliente
		ArrayList<AbstractFrame> cliente = verificarModuloCliente(cc,correo);
		if(cliente!=null) presentacion.put("Mi cuenta",cliente);
		
		//Modulo confiteria
		ArrayList<AbstractFrame> confiteria = verificarModuloConfiteria(cc,correo);
		if(confiteria!=null) presentacion.put("Confiteria",confiteria);
		
		//Módulo bolteria
		ArrayList<AbstractFrame> boleteria = verificarModuloFunciones(cc, idCliente);
		if(boleteria!=null) presentacion.put("Boletería", boleteria);
		
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
		}
		return null;
	}
	
	//Verificacion de modulo confiteria
	private static ArrayList<AbstractFrame> verificarModuloConfiteria(Cargador cc,String correo) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(ICompra.class.getName());
		if(cls!=null) {
			try {
				ICompra compra = (ICompra)cls.newInstance();
				Funciones.mensajeConsola("Modulo Confiteria cargado");
				return compra.getPresentacion(correo);
			} catch (Exception e) {
				e.printStackTrace();
				Funciones.mensajeConsola("Error al cargar modulo Confiteria: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo Confiteria no encontrado");
		}
		return null;
	}
	
	//Verificacion de modulo Funciones
	private static ArrayList<AbstractFrame> verificarModuloFunciones(Cargador cc,int idCliente) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IBoleteria.class.getName());
		if(cls!=null) {
			try {
				IBoleteria boleteria = (IBoleteria)cls.newInstance();
				Funciones.mensajeConsola("Modulo boleteria cargado");
				return boleteria.getPresetacion(idCliente);
			} catch (Exception e) {
				e.printStackTrace();
				Funciones.mensajeConsola("Error al cargar modulo Boleteria: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo Boleteria no encontrado");
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
		
	public static JFrame getVentanaRegistro() {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IRegistroCliente.class.getName());
		if(cls!=null) {
			try {
				IRegistroCliente cliente = (IRegistroCliente)cls.newInstance();
				return cliente.getVentanaRegistro();
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
		
}
