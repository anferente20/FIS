package edu.udistrital.fis.empleado.presentencion;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("edu.udistrital.fis.persistencia.GestorDB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
