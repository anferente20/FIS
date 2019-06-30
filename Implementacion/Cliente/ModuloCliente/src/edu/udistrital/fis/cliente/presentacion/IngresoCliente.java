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

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class IngresoCliente extends AbstractFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField pswContrasena;


	public IngresoCliente() {
		createFrame();
	}
	private void createFrame() {
		
		setTitle("Bienvenido a Cine+");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 261);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreso = new JLabel("Ingreso");
		lblIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreso.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblIngreso.setBounds(154, 13, 74, 30);
		contentPane.add(lblIngreso);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCorreo.setBounds(50, 60, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblContrasena.setBounds(50, 110, 150, 30);
		contentPane.add(lblContrasena);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtCorreo.setBounds(180, 60, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		pswContrasena = new JPasswordField();
		pswContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pswContrasena.setBounds(180, 110, 150, 30);
		contentPane.add(pswContrasena);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnIngresar.setBounds(142, 170, 100, 31);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ingresar();
			}
		}
		);
		contentPane.add(btnIngresar);
	}
	private void ingresar(){
		try {
			if(FachadaCliente.getInstance().verificarIngreso(txtCorreo.getText(), pswContrasena.getText())) {
				setIdentificador();
				this.dispose();
			}else {
				Funciones.mensajePantalla("Datos ingresados no existen.");
			}
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase IngresoCliente: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
		
		
	}

	@Override
	protected void setIdentificador() {
		try {
			this.identificador = FachadaCliente.getInstance().ingresar(txtCorreo.getText(), pswContrasena.getText())+" "+txtCorreo.getText();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
