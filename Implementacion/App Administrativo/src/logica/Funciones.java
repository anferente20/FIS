package logica;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Funciones {

	private Funciones() {
		
	}
	
	//Función que valida si un campo está vacío
	public static boolean validarVacio(String campo) {
		if(campo.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	//Elimina todo el contenido de una tabla
	public static void limpiarTabla(DefaultTableModel modelo) {
		int filas = modelo.getRowCount();
		for(int i=0;i<filas;i++) {
			modelo.removeRow(0);
		}
	}
	//Imprime un mensaje en consola
	public static void mensajeConsola(String msj) {
		System.out.println(msj);
	}
	//Imprime un mensaje en pantalla
	public static void mensajePantalla(String msj) {
		JOptionPane.showMessageDialog(null,msj);		
	}
	//M�todo que carga los cines en un combo box
	public static void cargarCines(JComboBox<String> cbx, ResultSet datos) throws SQLException{
		cbx.addItem("");
		while(datos.next()) {
			cbx.addItem(datos.getObject(1).toString());
		}
	}
}