package edu.udistrital.fis.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarCorreo frame = new CambiarCorreo();
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
	public CambiarCorreo() {
		setIdentificador();
		createFrame();
	}

	private void createFrame() {

		setTitle("Cambio Correo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 402, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCorreo = new JLabel("Correo Actual");
		lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCorreo.setBounds(30, 30, 150, 30);
		contentPane.add(lblCorreo);
		
		JLabel lblNuevoCorreo = new JLabel("Nuevo Correo");
		lblNuevoCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNuevoCorreo.setBounds(30, 70, 150, 30);
		contentPane.add(lblNuevoCorreo);
		
		JLabel lblConfirmar = new JLabel("Confirmar Correo");
		lblConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblConfirmar.setBounds(30, 110, 150, 30);
		contentPane.add(lblConfirmar);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCambiar.setBounds(130, 150, 100, 30);
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cambiar();
				} catch (SQLException e1) {
					Funciones.mensajePantalla("Datos erroneos");
				}	
			}
		}
		);
		contentPane.add(btnCambiar);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtCorreo.setBounds(200, 30, 150, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtNuevo = new JTextField();
		txtNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtNuevo.setBounds(200, 70, 150, 30);
		contentPane.add(txtNuevo);
		txtNuevo.setColumns(10);
		
		txtConfirmar = new JTextField();
		txtConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtConfirmar.setBounds(200, 110, 150, 30);
		contentPane.add(txtConfirmar);
		txtConfirmar.setColumns(10);

	}
	private void cambiar() throws SQLException {
		if(FachadaCliente.getInstance().verificarConrreo(txtCorreo.getText())) {
			if(txtNuevo.getText().equals(txtConfirmar.getText())) {
				FachadaCliente.getInstance().cambiarCorreo(txtNuevo.getText(), txtCorreo.getText());
				this.dispose();
			}else {
				Funciones.mensajePantalla("Correos no coinciden.");
			}
		}else {
			Funciones.mensajePantalla("Correo actual no registrado en la base de datos.");
		}
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Cambia el correo";
		
	}
}
