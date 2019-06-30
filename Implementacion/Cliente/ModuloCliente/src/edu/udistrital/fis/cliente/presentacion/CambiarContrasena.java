package edu.udistrital.fis.cliente.presentacion;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.cliente.persistencia.FachadaCliente;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CambiarContrasena extends AbstractFrame{

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField pswContrasena;
	private JPasswordField pswConfirmar;


	public CambiarContrasena() {
		createFrame();
		setIdentificador();
	}
	
	private void createFrame() {
		setResizable(false);
		setTitle("Cambio Contrasena");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCorreo.setBounds(32, 13, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblNuevaContrasena = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNuevaContrasena.setBounds(32, 53, 150, 30);
		contentPane.add(lblNuevaContrasena);
		
		JLabel lblConfirmar = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblConfirmar.setBounds(32, 93, 150, 30);
		contentPane.add(lblConfirmar);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCorreo.setBounds(202, 13, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		pswContrasena = new JPasswordField();
		pswContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pswContrasena.setBounds(202, 53, 150, 30);
		contentPane.add(pswContrasena);
		
		pswConfirmar = new JPasswordField();
		pswConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pswConfirmar.setBounds(202, 93, 150, 30);
		contentPane.add(pswConfirmar);
		
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
		setLocationRelativeTo(null);
	}
	
	private void cambiar() {
		if(pswContrasena.getText().equals(pswConfirmar.getText())) {
			try {
				FachadaCliente.getInstance().cambiarContrasena(pswContrasena.getText(), txtCorreo.getText());
				Funciones.mensajePantalla("¡CONTRASEÑA CAMBIADA CON ÉXITO!");
				this.dispose();
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase CambiarContraseña: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
			}
		}else {
			Funciones.mensajePantalla("Contraseñas no coinciden.");
		}
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Cambia la contrasena";		
	}

}
