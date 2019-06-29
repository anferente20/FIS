package edu.udistrital.fis.compra.logica;

import java.util.ArrayList;

public class Carrito {
	private ArrayList<Combo> combos;
	
	public Carrito() {
		combos = new ArrayList<Combo>();
	}

	public ArrayList<Combo> getCombos() {
		return combos;
	}

	public void setCombos(ArrayList<Combo> idCombos) {
		this.combos = idCombos;
	}
	
	
	
}
