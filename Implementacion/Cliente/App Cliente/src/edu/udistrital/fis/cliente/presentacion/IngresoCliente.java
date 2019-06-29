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

import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class IngresoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField pswContrasena;
	private MenuCliente mc;


	public IngresoCliente(MenuCliente mc) {
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
		lblIngreso.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblIngreso.setBounds(117, 20, 150, 30);
		contentPane.add(lblIngreso);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCorreo.setBounds(50, 60, 150, 30);
		contentPane.add(lblCorreo);

		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblContrasena.setBounds(50, 110, 150, 30);
		contentPane.add(lblContrasena);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(180, 60, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		pswContrasena = new JPasswordField();
		pswContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pswContrasena.setBounds(180, 110, 150, 30);
		contentPane.add(pswContrasena);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnIngresar.setBounds(142, 170, 100, 30);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						ingresar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
		);
		contentPane.add(btnIngresar);
	}
	private void ingresar() throws SQLException {
		if(FachadaCine.getInstance().verificarIngreso(txtCorreo.getText(), pswContrasena.getText())) {
			mc.getUsuario().setText( FachadaCine.getInstance().ingresar(txtCorreo.getText(), pswContrasena.getText()));
			mc.setCorreo( txtCorreo.getText());
			Componentes c = new Componentes(txtCorreo.getText());
			mc.setPresentacion(c.getPresentacion());
			mc.cargarFrames();
			this.dispose();
		}else {
			Funciones.mensajePantalla("Datos ingresados no existen.");
		}

	}



}