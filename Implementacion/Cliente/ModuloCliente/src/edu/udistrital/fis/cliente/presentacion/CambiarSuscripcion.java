package edu.udistrital.fis.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaSuscripcion;
import edu.udistrital.fis.cliente.persistencia.FachadaCliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CambiarSuscripcion extends AbstractFrame {

	private JPanel contentPane;
	private JTextArea txta1;
	private JComboBox comboBox;
	private JLabel lblSus;
	private ArrayList<String> desc;
	private String correo;

	
	public CambiarSuscripcion(String correo) {
		this.correo = correo;
		createFrame();
		setIdentificador();
		try {
			Funciones.cargarDatosCbx(comboBox,FachadaSuscripcion.getInstance().getTipos());
			ResultSet descripciones = FachadaSuscripcion.getInstance().getDescripciones();
			desc = new ArrayList<String>();
			while(descripciones.next()) {
				desc.add(descripciones.getString(1));
			}
		
			
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase AgregarEmpleado: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			dispose();
		}
	}
	
	private void createFrame() {
		setTitle("Cambio Suscripcion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel lblSuscripcion = new JLabel("Suscripcion Actual");
		lblSuscripcion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSuscripcion.setBounds(20, 40, 150, 30);
		panel.add(lblSuscripcion);
		
		lblSus = new JLabel("");
		lblSus.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSus.setBounds(180, 40, 150, 30);
		panel.add(lblSus);
		
		JLabel lblNueva = new JLabel("Nueva Suscripcion");
		lblNueva.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNueva.setBounds(20, 90, 150, 30);
		panel.add(lblNueva);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 13));
		comboBox.setBounds(180, 90, 200, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cambiarDescripcion();
			}
		}
		);
		panel.add(comboBox);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCambiar.setBounds(220, 140, 100, 30);
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						cambiar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
		);
		panel.add(btnCambiar);
		
		JPanel sur = new JPanel();
		sur.setLayout(new GridLayout(1,1,5,2));
		sur.setBorder(new TitledBorder("Descripciones"));
		contentPane.add(sur,BorderLayout.SOUTH);
		
		JPanel pane1 = new JPanel();
		pane1.setLayout(new BorderLayout());
		txta1 = new JTextArea();
		txta1.setBackground(new Color(240, 240, 240));
		pane1.add(txta1,BorderLayout.CENTER);
		sur.add(pane1);
		try {
			buscar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void buscar() throws SQLException {
		String susc= "";
		ResultSet res = FachadaSuscripcion.getInstance().getSuscripcion(this.correo);
		while (res.next()) {
			susc = res.getString(1);
		}
		lblSus.setText(susc);
	}
	
	private void cambiar() throws SQLException {
		FachadaCliente.getInstance().cambiarSuscripcion(comboBox.getSelectedIndex(),this.correo);
		this.dispose();
	}
	private void cambiarDescripcion() {
		if(comboBox.getSelectedIndex() != 0) {
			txta1.setText(desc.get(comboBox.getSelectedIndex()-1));
		}
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Cambia la suscripcion";	
	}
}