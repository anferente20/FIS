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

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class IngresoCliente extends AbstractFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField pswContrasena;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresoCliente frame = new IngresoCliente();
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
	public IngresoCliente() {
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
		lblIngreso.setBounds(130, 20, 150, 30);
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
		btnIngresar.setBounds(130, 170, 100, 30);
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
		if(FachadaCliente.getInstance().verificarIngreso(txtCorreo.getText(), pswContrasena.getText())) {
			setIdentificador();
			this.dispose();
		}else {
			Funciones.mensajePantalla("Datos ingresados no existen.");
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
