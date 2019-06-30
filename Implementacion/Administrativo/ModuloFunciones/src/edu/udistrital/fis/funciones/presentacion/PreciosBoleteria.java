package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreciosBoleteria extends AbstractFrame {

	private JPanel contentPane;
	private JLabel lblPrecio;
	private JTextField txtPrecio;


	public PreciosBoleteria() {
		
		setIdentificador();
		createFrame();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				cargarPrecio();
			}
		});
	}
	
	private void createFrame() {
		setResizable(false);
		setTitle("Precios boleter\u00EDa");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Precio actual:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(25, 13, 104, 26);
		contentPane.add(lblNewLabel);
		
		lblPrecio = new JLabel("");
		lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPrecio.setBounds(172, 13, 236, 26);
		contentPane.add(lblPrecio);
		
		JLabel lblNuevoPrecio = new JLabel("Nuevo precio:");
		lblNuevoPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNuevoPrecio.setBounds(25, 72, 104, 26);
		contentPane.add(lblNuevoPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPrecio.setBounds(172, 74, 236, 26);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambiarPrecio();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBounds(163, 124, 97, 31);
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Precios boletería";
		
	}
	
	
	private void cargarPrecio() {
		ResultSet consulta;
		try {
			consulta = FachadaPelicula.getInstance().consultarPrecioBoleteria();
			consulta.next();
			String precio = String.valueOf(consulta.getFloat("precio"));
			String fecha = consulta.getString("fecha");
			lblPrecio.setText("$"+precio+" Estipulado el: "+fecha);
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase PreciosBoleteria: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
		
	}
	
	private void cambiarPrecio() {
		if(Funciones.validarVacio(txtPrecio.getText()) || !Funciones.validarNumerico(txtPrecio.getText())) {
			Funciones.mensajePantalla("Error, datos no válidos");
		}
		else {
			Float precio = Float.parseFloat(txtPrecio.getText());
			if(precio<=0) Funciones.mensajePantalla("Error, datos no válidos");
			else {
				try {
					FachadaPelicula.getInstance().insertarPrecioBoleta(precio);
					Funciones.mensajePantalla("¡PRECIO CAMBIADO CON ÉXITO!");
					this.dispose();
				} catch (SQLException e) {
					Funciones.mensajeConsola("Clase PreciosBoleteria: "+e.getMessage());
					Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
				}
			}
		}
	}
}
