package edu.udistrital.fis.basicos.presentacion;



public class Main {

	public static void main(String[] args) throws InterruptedException {
		ProgressBar x = new ProgressBar(10,"Cargando","Creando funciones");
		for(int i=0;i<10;i++) {
			Thread.sleep(1000);
			x.cargarBarra("cine "+i);
		}
	}

}
