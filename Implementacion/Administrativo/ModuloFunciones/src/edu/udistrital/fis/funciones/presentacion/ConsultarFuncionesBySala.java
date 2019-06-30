package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarFuncionesBySala extends AbstractFrame {

	private JPanel contentPane;
	private JComboBox<String> cbxCine;
	private JComboBox<String> cbxSala;
	private JDateChooser dateChooser;


	public ConsultarFuncionesBySala(int tipoAdmin, int idCine) {
		setResizable(false);
		setIdentificador();
		createFrame();
		try {
			cargarCines();
			if(tipoAdmin==1) { //Administrador normal
				cbxCine.setSelectedIndex(idCine);
				cbxCine.setEnabled(false);
			}
			cargarSalas();
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ConsultarFuncionesBySala: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, la operación no pudo llevarse a cabo");
		}
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setResizable(false);
		setTitle("Consultar funciones por sala");
		
		JLabel label = new JLabel("Cine:");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(25, 25, 46, 14);
		contentPane.add(label);
		
		cbxCine = new JComboBox<String>();
		cbxCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxCine.setBounds(81, 21, 176, 23);
		contentPane.add(cbxCine);
		
		JLabel label_1 = new JLabel("Fecha");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_1.setBounds(25, 73, 46, 14);
		contentPane.add(label_1);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(81, 70, 176, 23);
		contentPane.add(dateChooser);
		
		cbxSala = new JComboBox<String>();
		cbxSala.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxSala.setBounds(365, 22, 46, 26);
		contentPane.add(cbxSala);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validar();
			}
		});
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnConsultar.setBounds(322, 65, 99, 30);
		contentPane.add(btnConsultar);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSala.setBounds(322, 25, 33, 14);
		contentPane.add(lblSala);
		setLocationRelativeTo(null);
	}
	
	private void cargarCines() throws SQLException {
		Funciones.cargarDatosCbx(cbxCine, FachadaCine.getInstance().consultarCines());
	}
	
	private void cargarSalas() {
		cbxSala.addItem("");
		for(int i=1;i<11;i++) cbxSala.addItem(String.valueOf(i));
	}
	
	private boolean validarFecha() {
		try {
			FuncionesTiempo ft = new FuncionesTiempo();
			ft.DateToString(dateChooser.getDate());
		}
		catch(NullPointerException npe) {
			return true;
		}
		return false;
	}
	
	private void validar() {
		if(Funciones.validarVacio((String)cbxCine.getSelectedItem()) || Funciones.validarVacio((String)cbxSala.getSelectedItem())
				|| validarFecha()){
			Funciones.mensajePantalla("Error, digite la totalidad de los datos");
		}
		else {
			int idCine = cbxCine.getSelectedIndex();
			int sala = Integer.parseInt((String)cbxSala.getSelectedItem());
			FuncionesTiempo ft = new FuncionesTiempo();
			String fecha = ft.DateToString(dateChooser.getDate());
			try {
				ResultSet funciones = FachadaPelicula.getInstance().consultarFuncionesBySalaByFecha(fecha, sala, idCine);
				if(!funciones.next()) {
					//No hay funciones con esos criterios de busqueda
					Funciones.mensajePantalla("No existen funciones con los criterios de búsqueda especificados");
				}
				else {
					Funciones.mensajeConsola("Mostrando funciones");
					MostrarFuncionesSala x = new MostrarFuncionesSala(sala, fecha, 
							idCine, funciones);
					x.setVisible(true);
				}
			}
			catch(SQLException e ) {
				Funciones.mensajeConsola("Clase ConsultarFuncionesBySala: "+e.getMessage());
				Funciones.mensajePantalla("Error, la operacion no pudo llevarse a cabo");
			}
		}
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Consultar funciones por sala";
	}
}
