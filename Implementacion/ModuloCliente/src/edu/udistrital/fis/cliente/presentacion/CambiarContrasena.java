package edu.udistrital.fis.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class CambiarContrasena extends AbstractFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField pswContrasena;
	private JPasswordField pswConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarContrasena frame = new CambiarContrasena();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CambiarContrasena() {
		createFrame();
		setIdentificador();
	}
	
	private void createFrame() {
		setTitle("Cambio Contrasena");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCorreo.setBounds(30, 30, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblNuevaContrasena = new JLabel("Nueva Contrase\u00F1a");
		lblNuevaContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNuevaContrasena.setBounds(30, 70, 150, 30);
		contentPane.add(lblNuevaContrasena);
		
		JLabel lblConfirmar = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblConfirmar.setBounds(30, 110, 150, 30);
		contentPane.add(lblConfirmar);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtCorreo.setBounds(200, 30, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		pswContrasena = new JPasswordField();
		pswContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pswContrasena.setBounds(200, 70, 150, 30);
		contentPane.add(pswContrasena);
		
		pswConfirmar = new JPasswordField();
		pswConfirmar.setBounds(200, 110, 150, 30);
		contentPane.add(pswConfirmar);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCambiar.setBounds(130, 150, 100, 30);
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cambiar();
			}
		}
		);
		contentPane.add(btnCambiar);
	}
	
	private void cambiar() {
		if(pswContrasena.getText().equals(pswConfirmar.getText())) {
			try {
				FachadaCliente.getInstance().cambiarContrasena(pswContrasena.getText(), txtCorreo.getText());
				this.dispose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
