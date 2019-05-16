package edu.udistrital.fis.basicos.logica;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * Clase abstracta que se extenderá para implementar el patrón 'Fachada'
 * @author Andres Arias
 *
 */
public class Funciones {

	private Funciones() {
		
	}
	
	/**
	 * Método que valida si un campo de texto está vacío
	 * @param String contenido del campo de texto
	 * @return boolean true si el campo está vacío, false si no lo está
	 */
	public static boolean validarVacio(String campo) {
		if(campo.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	/**
	 * Método que elimina todo el contenido de una tabla
	 * @param DefaultTableModel modelo de la tabla
	 * @return void
	 */
	public static void limpiarTabla(DefaultTableModel modelo) {
		int filas = modelo.getRowCount();
		for(int i=0;i<filas;i++) {
			modelo.removeRow(0);
		}
	}
	/**
	 * Método que imprime un mensaje por consola
	 * @param String mensaje que se quiere imprimir
	 * @return void
	 */
	public static void mensajeConsola(String msj) {
		System.out.println(msj);
	}
	/**
	 * Método que imprime por pantalla un mensaje
	 * @param String mensaje que se quiere imprimir
	 * @return void
	 */
	public static void mensajePantalla(String msj) {
		JOptionPane.showMessageDialog(null,msj);		
	}
	/**
	 * Método que carga registros en un combo box
	 * @param JComboBox Combo box en el que serán ingresados los datos
	 * @param Resultet ResultSet que contiene los registros
	 * @return void
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
	 * @return int 0 si la opción es 'SÍ', 1 si la opción es 'NO'
	 */
	public static int mensajeConfirmacion(String msj) {
		return JOptionPane.showConfirmDialog(null,msj);
	}
	/**
	 * Método que imprime  en pantalla un mensaje con entrada 
	 * @param msj Mnesaje que se va a imprimir
	 * @return String Entrada que fue digitada en pantalla
	 */
	public static String mensajeInPut(String msj) {
		return JOptionPane.showInputDialog(null,msj);
	}
}