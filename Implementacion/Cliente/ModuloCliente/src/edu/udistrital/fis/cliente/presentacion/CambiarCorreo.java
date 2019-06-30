package edu.udistrital.fis.cliente.presentacion;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.cliente.persistencia.FachadaCliente;

public class CambiarCorreo extends AbstractFrame{

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JTextField txtNuevo;
	private JTextField txtConfirmar;

	public CambiarCorreo() {
		setIdentificador();
		createFrame();
	}

	private void createFrame() {
		setResizable(false);
		setTitle("Cambio Correo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCorreo = new JLabel("Correo Actual");
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCorreo.setBounds(38, 13, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblNuevoCorreo = new JLabel("Nuevo Correo");
		lblNuevoCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNuevoCorreo.setBounds(38, 53, 150, 30);
		contentPane.add(lblNuevoCorreo);
		
		JLabel lblConfirmar = new JLabel("Confirmar Correo");
		lblConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblConfirmar.setBounds(38, 93, 150, 30);
		contentPane.add(lblConfirmar);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCambiar.setBounds(142, 136, 100, 33);
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiar();	
			}
		}
		);
		contentPane.add(btnCambiar);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCorreo.setBounds(208, 13, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtNuevo = new JTextField();
		txtNuevo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNuevo.setBounds(208, 53, 150, 30);
		contentPane.add(txtNuevo);
		txtNuevo.setColumns(10);
		
		txtConfirmar = new JTextField();
		txtConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtConfirmar.setBounds(208, 93, 150, 30);
		contentPane.add(txtConfirmar);
		txtConfirmar.setColumns(10);
		setLocationRelativeTo(null);
	}
	private void cambiar(){
		try {
			if(FachadaCliente.getInstance().verificarConrreo(txtCorreo.getText())) {
				if(txtNuevo.getText().equals(txtConfirmar.getText())) {
					FachadaCliente.getInstance().cambiarCorreo(txtNuevo.getText(), txtCorreo.getText());
					Funciones.mensajePantalla("¡CORREO MODIFICADO CON ÉXITO!");
					this.dispose();
				}else {
					Funciones.mensajePantalla("Correos no coinciden.");
				}
			}else {
				Funciones.mensajePantalla("Correo actual no registrado en la base de datos.");
			}
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase CambiarCorreo: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
		
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Cambia el correo";
		
	}
}
