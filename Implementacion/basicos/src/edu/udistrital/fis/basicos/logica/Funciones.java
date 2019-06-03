package edu.udistrital.fis.basicos.logica;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * Clase que contiene funciones básicas
 * @author Andres Arias
 *
 */
public class Funciones {

	private Funciones() {
		
	}
	
	/**
	 * Método que valida si un campo de texto está vacío
	 * @param campo contenido del campo de texto
	 * @return True si el campo está vacío, false si no lo está
	 */
	public static boolean validarVacio(String campo) {
		if(campo.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	/**
	 * Método que elimina todo el contenido de una tabla
	 * @param modelo modelo de la tabla
	 */
	public static void limpiarTabla(DefaultTableModel modelo) {
		int filas = modelo.getRowCount();
		for(int i=0;i<filas;i++) {
			modelo.removeRow(0);
		}
	}
	/**
	 * Método que imprime un mensaje por consola
	 * @param msj mensaje que se quiere imprimir
	 */
	public static void mensajeConsola(String msj) {
		System.out.println(msj);
	}
	/**
	 * Método que imprime por pantalla un mensaje
	 * @param msj mensaje que se quiere imprimir
	 */
	public static void mensajePantalla(String msj) {
		JOptionPane.showMessageDialog(null,msj);		
	}
	/**
	 * Método que carga registros en un combo box
	 * @param cbx Combo box en el que serán ingresados los datos
	 * @param datos ResultSet que contiene los registros
	 */
	public static void cargarDatosCbx(JComboBox<String> cbx, ResultSet datos) throws SQLException{
		cbx.addItem("");
		while(datos.next()) {
			cbx.addItem(datos.getObject(1).toString());
		}
	}
	/**
	 * Método que imprime en pantalla un mensaje de confirmación
	 * @param msj Mensaje que se va a imprimir
	 * @return 0 si la opción es 'SÍ', 1 si la opción es 'NO'
	 */
	public static int mensajeConfirmacion(String msj) {
		return JOptionPane.showConfirmDialog(null,msj);
	}
	/**
	 * Método que imprime  en pantalla un mensaje con entrada 
	 * @param msj Mnesaje que se va a imprimir
	 * @return Entrada que fue digitada en pantalla
	 */
	public static String mensajeInPut(String msj) {
		return JOptionPane.showInputDialog(null,msj);
	}
	/**
	 * Metodo que valida que en una cadena existen unicamnete valores numericos
	 * @param cadena
	 * @return false si el valor no es numerico
	 */
	public static boolean validarNumerico(String cadena) {// si solo hay numeros
		try {
			Long.parseLong(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}