package logica;

public class Validaciones {

	//Funci�n que valida si un campo est� vac�o
	public static boolean validarVacio(String campo) {
		if(campo.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}
