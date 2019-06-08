package edu.udistrital.fis.admin.presentacion;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import edu.udistrital.fis.admin.utilidades.*;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.basicos.presentacion.AbstractFrame;
import edu.udistrital.fis.core.*;

public class Main {

	public static void main(String[] args) {
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
			}
		}
		else {
			Funciones.mensajeConsola("Modulo empleados no encontrado");
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
			}
		}
		else {
			Funciones.mensajeConsola("Modulo inventario no encontrado");
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
}