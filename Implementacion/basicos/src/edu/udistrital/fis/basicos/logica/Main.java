package edu.udistrital.fis.basicos.logica;


import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		FuncionesTiempo x = new FuncionesTiempo();
		System.out.print(x.modulo("00:40", "10:40", "14:30"));
	}

}
