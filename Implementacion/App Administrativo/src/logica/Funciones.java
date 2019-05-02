package logica;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Funciones {

	//Funci�n que valida si un campo est� vac�o
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
}
