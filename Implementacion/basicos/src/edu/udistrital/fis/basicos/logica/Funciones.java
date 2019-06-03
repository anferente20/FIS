package edu.udistrital.fis.basicos.logica;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * Clase que contiene funciones b�sicas
 * @author Andres Arias
 *
 */
public class Funciones {

	private Funciones() {
		
	}
	
	/**
	 * M�todo que valida si un campo de texto est� vac�o
	 * @param campo contenido del campo de texto
	 * @return True si el campo est� vac�o, false si no lo est�
	 */
	public static boolean validarVacio(String campo) {
		if(campo.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	/**
	 * M�todo que elimina todo el contenido de una tabla
	 * @param modelo modelo de la tabla
	 */
	public static void limpiarTabla(DefaultTableModel modelo) {
		int filas = modelo.getRowCount();
		for(int i=0;i<filas;i++) {
			modelo.removeRow(0);
		}
	}
	/**
	 * M�todo que imprime un mensaje por consola
	 * @param msj mensaje que se quiere imprimir
	 */
	public static void mensajeConsola(String msj) {
		System.out.println(msj);
	}
	/**
	 * M�todo que imprime por pantalla un mensaje
	 * @param msj mensaje que se quiere imprimir
	 */
	public static void mensajePantalla(String msj) {
		JOptionPane.showMessageDialog(null,msj);		
	}
	/**
	 * M�todo que carga registros en un combo box
	 * @param cbx Combo box en el que ser�n ingresados los datos
	 * @param datos ResultSet que contiene los registros
	 */
	public static void cargarDatosCbx(JComboBox<String> cbx, ResultSet datos) throws SQLException{
		cbx.addItem("");
		while(datos.next()) {
			cbx.addItem(datos.getObject(1).toString());
		}
	}
	/**
	 * M�todo que imprime en pantalla un mensaje de confirmaci�n
	 * @param msj Mensaje que se va a imprimir
	 * @return 0 si la opci�n es 'S�', 1 si la opci�n es 'NO'
	 */
	public static int mensajeConfirmacion(String msj) {
		return JOptionPane.showConfirmDialog(null,msj);
	}
	/**
	 * M�todo que imprime  en pantalla un mensaje con entrada 
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