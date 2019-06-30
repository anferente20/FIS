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


public class CambiarSuscripcion extends AbstractFrame {

	private JPanel contentPane;
	private JTextArea txta1;
	private JComboBox<String> comboBox;
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
			Funciones.mensajeConsola("Clase CambiarSuscripcion: "+e.getMessage());
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
		lblSuscripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSuscripcion.setBounds(69, 40, 150, 30);
		panel.add(lblSuscripcion);
		
		lblSus = new JLabel("");
		lblSus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSus.setBounds(229, 40, 200, 30);
		panel.add(lblSus);
		
		JLabel lblNueva = new JLabel("Nueva Suscripcion");
		lblNueva.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNueva.setBounds(69, 90, 150, 30);
		panel.add(lblNueva);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBox.setBounds(229, 90, 200, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cambiarDescripcion();
			}
		}
		);
		panel.add(comboBox);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCambiar.setBounds(186, 140, 100, 33);
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					cambiar();
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
		txta1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txta1.setBackground(new Color(240, 240, 240));
		pane1.add(txta1,BorderLayout.CENTER);
		sur.add(pane1);
		buscar();
		setLocationRelativeTo(null);
	}
	private void buscar(){
		String susc= "";
		try {
			ResultSet res = FachadaSuscripcion.getInstance().getSuscripcion(this.correo);
			while (res.next()) {
				susc = res.getString(1);
			}
			lblSus.setText(susc);
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase CambiarSuscripción: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
		
	}
	
	private void cambiar(){
		try {
			FachadaCliente.getInstance().cambiarSuscripcion(comboBox.getSelectedIndex(),this.correo);
			this.dispose();
			Funciones.mensajePantalla("¡SUSCRIPCIÓN CAMBIADA CON ÉXITO!");
		}
		catch(SQLException e) {
			
		}
		
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