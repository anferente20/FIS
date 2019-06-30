package edu.udistrital.fis.cliente.presentacion;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.cliente.utilidades.Componentes;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IngresoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField pswContrasena;
	private MenuCliente mc;


	public IngresoCliente(MenuCliente mc) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				mc.setVisible(true);
				mc.btnIngresar.setVisible(true);
				mc.btnRegistrar.setVisible(true);
			}
		});
		this.mc = mc;
		createFrame();
	}
	private void createFrame() {
		setTitle("Bienvenido a Cine+");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngreso = new JLabel("Ingreso");
		lblIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreso.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblIngreso.setBounds(141, 20, 100, 30);
		contentPane.add(lblIngreso);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCorreo.setBounds(50, 60, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblContrasena.setBounds(50, 109, 150, 30);
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
		btnIngresar.setBounds(141, 163, 100, 30);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ingresar();
			}
		}
		);
		contentPane.add(btnIngresar);
		setLocationRelativeTo(null);
	}
	private void ingresar() {
		try {
			if(FachadaCine.getInstance().verificarIngreso(txtCorreo.getText(), pswContrasena.getText())) {
				mc.getUsuario().setText("Usuario: "+FachadaCine.getInstance().ingresar(txtCorreo.getText(), pswContrasena.getText()));
				mc.setCorreo( txtCorreo.getText());
				Componentes c = new Componentes(txtCorreo.getText());
				mc.setPresentacion(c.getPresentacion());
				mc.cargarFrames();
				mc.setVisible(true);
				mc.setNoVisible();
				this.dispose();
			}else {
				Funciones.mensajePantalla("Datos ingresados no existen.");
			}
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase IngresoCliente: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
		
		
	}
	

	
}
