package logica;

public class Validaciones {

	//Función que valida si un campo está vacío
	public static boolean validarVacio(String campo) {
		if(campo.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}
