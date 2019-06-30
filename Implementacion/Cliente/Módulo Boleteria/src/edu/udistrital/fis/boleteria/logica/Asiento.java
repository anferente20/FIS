package edu.udistrital.fis.boleteria.logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.udistrital.fis.boleteria.presentacion.SeleccionarAsientos;

public class Asiento extends JButton{
	
	private int fila;
	private int columna;
	private ImageIcon img;
	private boolean libre;
	private SeleccionarAsientos target;
	private boolean seleccionado;
	
	public static final ImageIcon sillaLibre = new ImageIcon("resource\\libre.png"); 
	public static final ImageIcon sillaOcupada = new ImageIcon("resource\\ocupada.png");
	public static final ImageIcon colorLibre = new ImageIcon("resource\\color-libre.png");
	public static final ImageIcon colorOcupada = new ImageIcon("resource\\color-ocupada.png");
	
	public Asiento(int fila, int columna, boolean libre, int x, int y, SeleccionarAsientos target) {
		this.seleccionado = false;
		this.target = target;
		this.columna = columna;
		this.fila = fila;
		this.libre = libre;
		if(libre) {
			setLibre();
		}
		else {
			setOcupado();
			setEnabled(false);
		}
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resuelve();
			}
		});
		setBounds(x, y, 80, 104);
		this.setIcon(this.img);
		setOpaque(false);
	}
	
	private void resuelve() {
		if(this.libre) {
			setOcupado();
			this.seleccionado = true;
			target.sumaAsiento();
		}
		else {
			setLibre();
			this.seleccionado = false;
			target.restaAsiento();
		}
	}
	
	private void setOcupado() {
		this.img = Asiento.sillaOcupada;
		this.setIcon(this.img);
		this.libre = false;
	}
	
	private void setLibre() {
		this.img = Asiento.sillaLibre;
		this.setIcon(this.img);
		this.libre = true;
		
	}
	
	public static int letraToNumero(Character fila) {
		return fila-96;
	}
	
	public static String numeroToLetra(int fila) {
		return Character.toString((char)(fila+96));
	}
	
	public boolean getSeleccionado() {
		return this.seleccionado;
	}
	
	public String getFila() {
		return numeroToLetra(this.fila);
	}
	
	public String getColumna() {
		return String.valueOf(this.columna);
	}
}
