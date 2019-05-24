package edu.udistrital.fis.admin.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JButton btnIngresar;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public Login() {
		createFrame();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("CINE+ Administrativo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsuario.setBounds(83, 34, 65, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblContrasea.setBounds(83, 76, 75, 14);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtUsuario.setBounds(177, 27, 129, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(177, 69, 129, 29);
		contentPane.add(txtContrasena);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionIngresar();
			}
		});
		btnIngresar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnIngresar.setBounds(80, 132, 98, 42);
		contentPane.add(btnIngresar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accionSalir();
			}
		});
		btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSalir.setBounds(208, 132, 98, 42);
		contentPane.add(btnSalir);
		
		setLocationRelativeTo(null);
	}
	
	private void accionSalir() {
		this.dispose();
	}
	
	private void accionIngresar() {
		String usuario = this.txtUsuario.getText(), contrasena = this.txtContrasena.getText(),tipoUsuario="";
		//****AQUI DEBERIA LLAMAR A LA CLASE FACHADA QUE SE ENCARGUE DEL LOGIN CON LA BASE DE DATOS
		/*
		 *    LOGIN DESDE LA BASE DE DATOS
		 * 
		 */
		tipoUsuario = "Administrador";  // ---> Quitar después de hacer bien el login
		this.setVisible(false);
		edu.udistrital.fis.admin.presentacion.Menu menu = new edu.udistrital.fis.admin.presentacion.Menu(usuario, contrasena, tipoUsuario);
		
		
	}

}