package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import logica.Empleado;
import logica.FachadaCine;
import logica.FachadaEmpleado;
import logica.validaciones;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.FachadaCine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtIdentificacion;
	private JComboBox<String> cbCine;

	public AgregarEmpleado(){
		createFrame();
		cargarCines();
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblId.setBounds(78, 43, 56, 16);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtID.setBounds(192, 38, 158, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombres:");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(78, 92, 70, 16);
		contentPane.add(lblNombre);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombres.setColumns(10);
		txtNombres.setBounds(192, 87, 158, 26);
		contentPane.add(txtNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblApellidos.setBounds(78, 141, 70, 16);
		contentPane.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(192, 136, 158, 26);
		contentPane.add(txtApellidos);
		
		JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
		lblIdentificacin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdentificacin.setBounds(78, 187, 102, 16);
		contentPane.add(lblIdentificacin);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtIdentificacion.setColumns(10);
		txtIdentificacion.setBounds(192, 182, 158, 26);
		contentPane.add(txtIdentificacion);
		
		JLabel lblCine = new JLabel("Cine:");
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCine.setBounds(78, 235, 102, 16);
		contentPane.add(lblCine);
		setTitle("Agregar un nuevo empleado");
		
		cbCine = new JComboBox<String>();
		cbCine.setBounds(196, 231, 154, 26);
		contentPane.add(cbCine);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarEmpleado();
				}}
		);
		btnAceptar.setBounds(99, 298, 97, 37);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hide();
			}
		});
		btnSalir.setBounds(222, 298, 97, 37);
		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
	}

	//se cargan los cines que est�n registrados en el combo box
	private void cargarCines(){
		try {
			ResultSet cines = FachadaCine.consultarCines();
			this.cbCine.addItem("");
			while(cines.next()) {
				this.cbCine.addItem((cines.getObject(1)).toString());
			}
		}
		catch(SQLException sqle) {
			System.out.print(sqle.getMessage());
		}
	}
	//inserta un nuevo empleado
	private void insertarEmpleado(){
		if(this.validarVacio()) {
			JOptionPane.showMessageDialog(null,"Error, digite la totalidad de los datos");
		}
		else {
			Empleado empleado = new Empleado();
			empleado.setIdEmpleado(Integer.parseInt(txtID.getText()));
			empleado.setNombresEmpleado(txtNombres.getText());
			empleado.setApellidosEmpleados(txtApellidos.getText());
			empleado.setIdentificacionEmpl(Integer.parseInt(txtIdentificacion.getText()));
			empleado.setIdCine(cbCine.getSelectedIndex());
			try {
				FachadaEmpleado.insertarEmpleado(empleado);
				JOptionPane.showMessageDialog(null,"�EMPLEADO INSERTADO CON �XITO");
				this.dispose();
				AgregarEmpleado frame = new AgregarEmpleado();
				frame.setVisible(true);
			}
			catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Error, el ID o la identificaci�n ya est�n registrados con otro usuario");
			}
		}
	}
	
	private boolean validarVacio() {
		if(validaciones.validarVacio(txtID.getText()) ||validaciones.validarVacio(txtNombres.getText()) || validaciones.validarVacio(txtApellidos.getText()) ||
				validaciones.validarVacio(txtIdentificacion.getText()) || validaciones.validarVacio(cbCine.getSelectedItem().toString())) {
			return true;
		}
		return false;
	}
}
