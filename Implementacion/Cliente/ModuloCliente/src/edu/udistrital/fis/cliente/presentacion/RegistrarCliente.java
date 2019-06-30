package edu.udistrital.fis.cliente.presentacion;


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
	private JPasswordField pswContrasena;
	private JPasswordField pswConfirmar;


	public RegistrarCliente() {
		createFrame();
		setIdentificador();
	}
	private void createFrame() {
		setTitle("Registro Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroUsuario = new JLabel("REGISTRO USUARIO");
		lblRegistroUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblRegistroUsuario.setBounds(118, 20, 142, 30);
		contentPane.add(lblRegistroUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(36, 63, 150, 30);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblApellido.setBounds(36, 103, 150, 30);
		contentPane.add(lblApellido);
		
		JLabel lblIdentificacion = new JLabel("Identificacion");
		lblIdentificacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdentificacion.setBounds(36, 143, 150, 30);
		contentPane.add(lblIdentificacion);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblContrasena.setBounds(36, 223, 150, 30);
		contentPane.add(lblContrasena);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCorreo.setBounds(36, 183, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblConfirmarContrasena = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblConfirmarContrasena.setBounds(36, 263, 150, 30);
		contentPane.add(lblConfirmarContrasena);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombre.setBounds(216, 63, 150, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtApellido.setBounds(216, 103, 150, 30);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtIdentificacion.setBounds(216, 143, 150, 30);
		contentPane.add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCorreo.setBounds(216, 183, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					registrar();
			}
		}
		);
		btnRegistrar.setBounds(139, 306, 100, 38);
		
		contentPane.add(btnRegistrar);
		
		pswContrasena = new JPasswordField();
		pswContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pswContrasena.setBounds(216, 223, 150, 30);
		contentPane.add(pswContrasena);
		
		pswConfirmar = new JPasswordField();
		pswConfirmar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pswConfirmar.setBounds(216, 263, 150, 30);
		contentPane.add(pswConfirmar);
		
		setLocationRelativeTo(null);
	}
	private boolean validarVacio() {
		if(Funciones.validarVacio(txtNombre.getText()) &&Funciones.validarVacio(txtApellido.getText()) &&
				Funciones.validarVacio(txtIdentificacion.getText()) && Funciones.validarVacio(txtCorreo.getText()) &&
				Funciones.validarVacio(pswContrasena.getText()) && Funciones.validarVacio(pswConfirmar.getText())) {
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
			cli.setContrasena(pswContrasena.getText());
			cli.setCorreo(txtCorreo.getText());
			cli.setIdentificacionCliente(Integer.valueOf(txtIdentificacion.getText()));
			cli.setTipoSuscripcion(1);
			try {
				FachadaCliente.getInstance().insertarCliente(cli);
				Funciones.mensajeConsola("¡TE HAS REGISTRADO CON ÉXITO!");
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
		this.identificador = "Registra un nuevo cliente";
	}
}
