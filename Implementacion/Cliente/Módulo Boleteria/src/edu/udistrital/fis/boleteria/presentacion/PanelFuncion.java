package edu.udistrital.fis.boleteria.presentacion;

import javax.swing.JPanel;

import edu.udistrital.fis.boleteria.logica.Funcion;
import edu.udistrital.fis.boleteria.persistencia.FachadaBoleteria;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelFuncion extends JPanel {
	private JLabel lblHora;
	private JLabel lblSala;
	private Funcion funcion;
	private JFrame target;

	public PanelFuncion(Funcion funcion,JFrame target) throws SQLException {
		this.target = target;
		this.funcion = funcion;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hora:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(31, 26, 56, 16);
		add(lblNewLabel);
		
		JLabel lblNumeroSala = new JLabel("Sala:");
		lblNumeroSala.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNumeroSala.setBounds(31, 82, 56, 16);
		add(lblNumeroSala);
		
		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprar();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton.setBounds(212, 45, 97, 32);
		add(btnNewButton);
		
		lblHora = new JLabel("");
		lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblHora.setBounds(99, 22, 101, 24);
		add(lblHora);
		
		lblSala = new JLabel("");
		lblSala.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSala.setBounds(99, 78, 74, 24);
		add(lblSala);
		
		cargarFuncion();
		
	}
	
	private void cargarFuncion() throws SQLException {
		lblHora.setText(funcion.getHora());
		lblSala.setText(String.valueOf(FachadaBoleteria.getInstance().getConsecutivoSala(funcion.getIdSala())));
	}
	
	private void comprar() {
		this.target.dispose();
		SeleccionarAsientos x = new SeleccionarAsientos(this.funcion);
		x.setVisible(true);
	}

}
