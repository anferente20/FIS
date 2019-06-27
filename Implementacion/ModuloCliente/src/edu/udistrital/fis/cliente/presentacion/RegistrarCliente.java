package edu.udistrital.fis.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.cliente.logica.Cliente;
import edu.udistrital.fis.cliente.persistencia.FachadaCliente;
import edu.udistrital.fis.api.logica.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class RegistrarCliente extends AbstractFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtIdentificacion;
	private JTextField txtCorreo;
	private JTextField txtId;
	private JPasswordField pswContrasena;
	private JPasswordField pswConfirmar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarCliente frame = new RegistrarCliente();
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
	public RegistrarCliente() {
		createFrame();
		setIdentificador();
	}
	private void createFrame() {
		setTitle("Registro Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroUsuario = new JLabel("REGISTRO USUARIO");
		lblRegistroUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblRegistroUsuario.setBounds(140, 20, 200, 30);
		contentPane.add(lblRegistroUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNombre.setBounds(30, 110, 150, 30);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblApellido.setBounds(30, 150, 150, 30);
		contentPane.add(lblApellido);
		
		JLabel lblIdentificacion = new JLabel("Identificacion");
		lblIdentificacion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblIdentificacion.setBounds(30, 190, 150, 30);
		contentPane.add(lblIdentificacion);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblContrasena.setBounds(30, 270, 150, 30);
		contentPane.add(lblContrasena);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCorreo.setBounds(30, 230, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblConfirmarContrasena = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblConfirmarContrasena.setBounds(30, 310, 150, 30);
		contentPane.add(lblConfirmarContrasena);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtNombre.setBounds(210, 110, 150, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtApellido.setBounds(210, 150, 150, 30);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtIdentificacion.setBounds(210, 190, 150, 30);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtCorreo.setBounds(210, 230, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					registrar();
			}
		}
		);
		btnRegistrar.setBounds(130, 360, 100, 30);
		
		contentPane.add(btnRegistrar);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblId.setBounds(30, 70, 150, 30);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtId.setBounds(210, 70, 150, 30);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		pswContrasena = new JPasswordField();
		pswContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pswContrasena.setBounds(210, 270, 150, 30);
		contentPane.add(pswContrasena);
		
		pswConfirmar = new JPasswordField();
		pswConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pswConfirmar.setBounds(210, 310, 150, 30);
		contentPane.add(pswConfirmar);
	}
	private boolean validarVacio() {
		if(Funciones.validarVacio(txtNombre.getText()) &&Funciones.validarVacio(txtApellido.getText()) &&
				Funciones.validarVacio(txtIdentificacion.getText()) && Funciones.validarVacio(txtCorreo.getText()) &&
				Funciones.validarVacio(pswContrasena.getText()) && Funciones.validarVacio(pswConfirmar.getText()) && Funciones.validarVacio(txtId.getText())) {
			return true;
		}
		return false;
	}
	private boolean validarContrasena() {
		if(pswContrasena.getText().equals(pswConfirmar.getText())) {
			return true;
		}else {
			return false;
		}
	}
	private void registrar()  {
		
		if(this.validarVacio()) {
			JOptionPane.showMessageDialog(null,"Error, digite la totalidad de los datos");
		}else if (validarContrasena()){
			Cliente cli = new Cliente();
			cli.setNombreCliente(txtNombre.getText());
			cli.setApellidoCliente(txtApellido.getText());
			cli.setIdCliente(Integer.valueOf(txtId.getText()));
			cli.setContrasena(pswContrasena.getText());
			cli.setCorreo(txtCorreo.getText());
			cli.setIdentificacionCliente(Integer.valueOf(txtIdentificacion.getText()));
			cli.setTipoSuscripcion(1);
			try {
				FachadaCliente.getInstance().insertarCliente(cli);
				this.dispose();
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase AgregarEmpleado: "+e.getMessage());
				Funciones.mensajePantalla("Error, el ID o la identificacion ya estan registrados con otro usuario");
			}
		}else {
			JOptionPane.showMessageDialog(null,"Error, Contraseñas no coinciden");
		}
		
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Registra un  nuevo cliente";
	}
}
