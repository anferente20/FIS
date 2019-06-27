package edu.udistrital.fis.boleteria.presentacion;

import javax.swing.JPanel;

import edu.udistrital.fis.funciones.logica.Pelicula;
import edu.udistrital.imagen.Imagen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class PanelPelicula extends JPanel {
	private JLabel lblImg;
	private JLabel lblNombrePelicula;

	
	public PanelPelicula(Pelicula pelicula, byte[] img) {
		createPane();
		this.lblNombrePelicula.setText(pelicula.getNombre());
		Imagen.cargarImagen(lblImg, img);
	}
	
	private void createPane() {
		setLayout(null);
		
		lblImg = new JLabel("");
		lblImg.setBounds(29, 23, 197, 233);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(lblImg);
		
		lblNombrePelicula = new JLabel("New label");
		lblNombrePelicula.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNombrePelicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePelicula.setBounds(34, 267, 187, 34);
		add(lblNombrePelicula);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprar();
			}
		});
		btnComprar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnComprar.setBounds(83, 312, 89, 31);
		add(btnComprar);
	}
	
	private void comprar() {
		
	}
}
