package edu.udistrital.fis.admin.presentacion;


import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.udistrital.fis.basicos.logica.Funciones;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.ArrayList;
import java.util.HashMap;
import edu.udistrital.fis.admin.utilidades.*;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.core.*;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//Estilos que tenga el sistema operativo
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		
		//Conjuntos de frames agrupados por modulos
		HashMap<String,ArrayList<AbstractFrame>> presentacion = new HashMap<String, ArrayList<AbstractFrame>>(); 
		
		verificarJDBC();
		
		Cargador cc = new Cargador("componentes", ClassLoader.getSystemClassLoader());
		//Modulo empleado
		ArrayList<AbstractFrame> empleado = verificarModuloEmpleado(cc);
		if(empleado!=null) presentacion.put("Empleados",empleado);
		//Modulo inventario
		ArrayList<AbstractFrame> inventario = verificarModuloInventario(cc);
		if(inventario != null) presentacion.put("Inventario",inventario);
		//Módulo funciones
		ArrayList<AbstractFrame> funciones = verificarModuloFunciones(cc);
		if(funciones!=null) presentacion.put("Funciones",funciones);
		
		Login x = new Login(presentacion);
		x.setVisible(true);
	}
	//Verificacion de modulo empleado
	private static ArrayList<AbstractFrame> verificarModuloEmpleado(Cargador cc) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IEmpleados.class.getName());
		if(cls!=null) {
			try {
				IEmpleados empleado = (IEmpleados)cls.newInstance();
				Funciones.mensajeConsola("Modulo empleados cargado");
				return empleado.getPresentacion();
			} catch (Exception e) {
				Funciones.mensajeConsola("Error al cargar modulo Empleado: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo empleados no encontrado");
			Funciones.mensajePantalla("Modulo empleados no encontrado");
		}
		return null;
	}
	
	//Verificacion de modulo funciones
		private static ArrayList<AbstractFrame> verificarModuloFunciones(Cargador cc) {
			Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IFunciones.class.getName());
			if(cls!=null) {
				try {
					IFunciones funciones = (IFunciones)cls.newInstance();
					Funciones.mensajeConsola("Modulo funciones cargado");
					return funciones.getPresentacion();
				} catch (Exception e) {
					Funciones.mensajeConsola("Error al cargar modulo funciones: "+e.getMessage());
					return null;
				}
			}
			else {
				Funciones.mensajeConsola("Modulo funciones no encontrado");
				Funciones.mensajePantalla("Modulo funciones no encontrado");
			}
			return null;
		}
	
	//Verificacion de modulo inventario
	private static ArrayList<AbstractFrame> verificarModuloInventario(Cargador cc) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IInventario.class.getName());
		if(cls!=null) {
			try {
				IInventario inventario = (IInventario)cls.newInstance();
				Funciones.mensajeConsola("Modulo inventario cargado");
				return inventario.getPresentacion();
			} catch (Exception e) {
				Funciones.mensajeConsola("Error al cargar modulo inventario: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo inventario no encontrado");
			Funciones.mensajePantalla("Modulo inventario no encontrado");
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
}