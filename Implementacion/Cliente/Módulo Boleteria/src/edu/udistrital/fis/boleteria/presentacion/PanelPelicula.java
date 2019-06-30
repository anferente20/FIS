package edu.udistrital.fis.boleteria.presentacion;

import javax.swing.JPanel;

import edu.udistrital.fis.boleteria.logica.Pelicula;
import edu.udistrital.imagen.Imagen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class PanelPelicula extends JPanel {
	private JLabel lblImg;
	private JLabel lblNombrePelicula;
	private JLabel lblEstado;
	private Pelicula pelicula;
	private JFrame target;
	private byte[] img;

	
	public PanelPelicula(JFrame target,Pelicula pelicula, byte[] img, boolean estreno) {
		createPane();
		if(estreno) {
			lblEstado.setText("Estrena: "+ pelicula.getFechaEstreno());
			lblEstado.setForeground(Color.red);
		}
		else {
			lblEstado.setText("");
		}
		this.lblNombrePelicula.setText(pelicula.getNombre());
		Imagen.cargarImagen(lblImg, img);
		this.target = target;
		this.pelicula = pelicula;
		this.img = img;
	}
	
	
	private void createPane() {
		setLayout(null);
		
		lblImg = new JLabel("");
		lblImg.setBounds(35, 34, 197, 233);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(lblImg);
		
		lblNombrePelicula = new JLabel("New label");
		lblNombrePelicula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombrePelicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePelicula.setBounds(40, 280, 187, 34);
		add(lblNombrePelicula);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprar();
			}
		});
		btnComprar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnComprar.setBounds(89, 388, 89, 31);
		add(btnComprar);
		
		lblEstado = new JLabel("New label");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEstado.setBounds(40, 334, 187, 34);
		add(lblEstado);
	}
	
	private void comprar() {
		BuscarFunciones x = new BuscarFunciones(this.pelicula,img);
		x.setVisible(true);
		this.target.dispose();
	}
}
