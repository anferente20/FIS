package edu.udistrital.fis.funciones.presentacion;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.funciones.logica.Pelicula;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class ConsultarFuncionesByPelicula extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbxCine;
	private JComboBox<String> cbxPelicula;
	private JLabel lblFecha;
	private JDateChooser dateChooser;
	private JScrollPane scrollPane;

	
	public ConsultarFuncionesByPelicula() {
		createFrame();
		try {
			cargarCines();
			cargarPeliculas();
			dateChooser.setMinSelectableDate(new Date());
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ConsultarFuncionesByPelicula: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible ejecutar la operacion");
		}
	}
	
	private void createFrame() {
		setResizable(false);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Consultar funciones por pel\u00EDcula");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCine = new JLabel("Cine:");
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCine.setBounds(27, 32, 46, 14);
		contentPane.add(lblCine);
		
		JLabel lblPelcula = new JLabel("Pel\u00EDcula:");
		lblPelcula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPelcula.setBounds(285, 32, 71, 14);
		contentPane.add(lblPelcula);
		
		cbxCine = new JComboBox<String>();
		cbxCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxCine.setBounds(83, 28, 176, 23);
		contentPane.add(cbxCine);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(27, 80, 46, 14);
		contentPane.add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(83, 77, 176, 23);
		contentPane.add(dateChooser);
		setLocationRelativeTo(null);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validar();
			}
		});
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnConsultar.setBounds(366, 72, 99, 30);
		contentPane.add(btnConsultar);
		
		cbxPelicula = new JComboBox<String>();
		contentPane.add(cbxPelicula);
		cbxPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxPelicula.setBounds(291, 94, 217, 28);
		
		scrollPane = new JScrollPane(cbxPelicula);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(366, 28, 230, 28);
		contentPane.add(scrollPane);
	}

	private void cargarCines() throws SQLException{
		Funciones.cargarDatosCbx(cbxCine,FachadaCine.getInstance().consultarCines());
	}
	
	private void cargarPeliculas() throws SQLException {
		Funciones.cargarDatosCbx(cbxPelicula, FachadaPelicula.getInstance().consultarPeliculasVigentes());
	}
	
	private void validar() {
		if(Funciones.validarVacio((String)cbxCine.getSelectedItem()) || Funciones.validarVacio((String)cbxPelicula.getSelectedItem())
				|| validarFecha()){
			Funciones.mensajePantalla("Error, digite la totalidad de los datos");
		}
		else {
			int idCine = cbxCine.getSelectedIndex();
			String[] cadena = (String[])((String) cbxPelicula.getSelectedItem()).split(" - ");
			int idPelicula = Integer.parseInt(cadena[0]);
			FuncionesTiempo ft = new FuncionesTiempo();
			String fecha = ft.DateToString(dateChooser.getDate());
			try {
				ResultSet funciones = FachadaPelicula.getInstance().consultarFuncionesByPeliculaByCine(fecha, idPelicula, idCine);
				if(!funciones.next()) {
					//No hay funciones con esos criterios
					Funciones.mensajePantalla("No existen funciones con los criterios de búsqueda especificados");
				}
				else {
					//Se muestran los resultados
					Funciones.mensajeConsola("Mostrando funciones");
					Pelicula pelicula = new Pelicula("", "", "", ft.timeToInterval(funciones.getString("duracion")), 
							null, null, "", 0, "");
					MostrarFuncionesPelicula x = new MostrarFuncionesPelicula(cadena[1],funciones.getBytes("imagenPelicula"),
							funciones,fecha,(String)cbxCine.getSelectedItem(),pelicula);
					x.setVisible(true);
				}
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase ConsultarFuncionesByPelicula: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible ejecutar la operacion");
			}
		}
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
}
