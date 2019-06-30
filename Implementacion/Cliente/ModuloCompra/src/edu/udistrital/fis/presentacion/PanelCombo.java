package edu.udistrital.fis.presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;


import edu.udistrital.fis.compra.logica.Combo;

import edu.udistrital.imagen.Imagen;

public class PanelCombo extends JPanel{
	private JLabel lblImg;
	private JTextArea txtDescripcion;
	private JLabel lblPrecio;
	private Combo combo;
	private JLabel lblEstado;
	private JSpinner spinner;
	private JButton btnAgregar;
	private MuestraCombos mc;
	
	public PanelCombo(Combo combo,MuestraCombos mc) {
		this.mc = mc;
		this.combo = combo;
		setLayout(null);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescripcin.setBounds(32, 42, 107, 20);
		add(lblDescripcin);
		
		JLabel lblP1 = new JLabel("Precio: $");
		lblP1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblP1.setBounds(30, 151, 107, 20);
		add(lblP1);
		
		JLabel lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCantidad.setBounds(30, 181, 107, 20);
		add(lblCantidad);
		
		spinner = new JSpinner();
		spinner.setBounds(151, 181, 70, 30);
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		spinner.setModel(new SpinnerNumberModel(0,0,100,1));
		add(spinner);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAgregar.setBounds(232, 226, 100, 37);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
				}}
		);
		add(btnAgregar);
		
		lblImg = new JLabel("");
		lblImg.setBounds(384, 64, 157, 172);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(lblImg);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDescripcion.setEnabled(false);
		txtDescripcion.setBounds(151, 42, 173, 93);
		
		lblPrecio = new JLabel("");
		lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPrecio.setBounds(151, 151, 107, 20);
		add(lblPrecio);
		
		
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEstado.setBounds(384, 226, 107, 20);
		add(lblEstado);
		
		JScrollPane scrollPane = new JScrollPane(txtDescripcion);
		scrollPane.setBounds(151, 42, 181, 93);
		add(scrollPane);
		
		cargar();
	}
	
	private void cargar() {
		this.txtDescripcion.setText(this.combo.getDescripcion());
		this.lblPrecio.setText(String.valueOf(this.combo.getPrecio()));
		Imagen.cargarImagen(lblImg, this.combo.getImg().getFile());
	}
	
	
	private void agregar() {
		if(!(spinner.getValue().toString()=="")){
			this.combo.setCantidad(Integer.valueOf(spinner.getValue().toString()));
			mc.getCompras().getCombos().add(this.combo);
			mc.actualizar();
		}
		
	}
	void setModificada(Combo combo) {
		this.combo = combo;
		cargar();
	}
	
	

}
