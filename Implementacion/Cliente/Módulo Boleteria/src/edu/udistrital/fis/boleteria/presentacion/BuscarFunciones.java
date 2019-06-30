package edu.udistrital.fis.boleteria.presentacion;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.boleteria.logica.Pelicula;
import edu.udistrital.fis.boleteria.persistencia.FachadaBoleteria;
import edu.udistrital.imagen.Imagen;

import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class BuscarFunciones extends JFrame {

	private JPanel contentPane;
	private JLabel lblImg;
	private JLabel lblPelicula;
	private JLabel lblFechaEstreno;
	private JLabel lblDirector;
	private JTextArea txtSinopsis;
	private JLabel lblDuracion;
	private JComboBox<String> cbxCine;
	private JDateChooser dateChooser;
	private Pelicula pelicula;
	
	public BuscarFunciones(Pelicula pelicula, byte[] img) {
		setResizable(false);
		createFrame();
		this.pelicula = pelicula;
		lblPelicula.setText(pelicula.getNombre());
		lblDirector.setText("Director(es): "+pelicula.getDirector());
		lblDuracion.setText("Duración: "+pelicula.getDuracion());
		lblFechaEstreno.setText("Fecha de estreno: "+pelicula.getFechaEstreno());
		txtSinopsis.setText("SINOPSIS: "+pelicula.getSinopsis());
		txtSinopsis.setOpaque(false);
		txtSinopsis.setEnabled(false);
		Imagen.cargarImagen(lblImg, img);
		
		try {
			FuncionesTiempo ft = new FuncionesTiempo();
			Funciones.cargarDatosCbx(cbxCine, FachadaCine.getInstance().consultarCines());
			dateChooser.setMinSelectableDate(ft.StringToDate(pelicula.getFechaEstreno()));
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase Seleccionar funcion: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void createFrame() {
		setBounds(100, 100, 722, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Boleter\u00EDa");
		JLabel lblNewLabel = new JLabel("Seleccione un fecha:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(100, 415, 167, 25);
		contentPane.add(lblNewLabel);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(279, 415, 176, 30);
		contentPane.add(dateChooser);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultar();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBounds(489, 436, 115, 35);
		contentPane.add(btnNewButton);
		
		lblImg = new JLabel("");
		lblImg.setBounds(22, 27, 246, 341);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		contentPane.add(lblImg);
		
		lblPelicula = new JLabel("");
		lblPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPelicula.setBounds(319, 40, 373, 25);
		contentPane.add(lblPelicula);
		
		txtSinopsis = new JTextArea();
		txtSinopsis.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSinopsis.setBounds(319, 98, 373, 122);
		contentPane.add(txtSinopsis);
		
		lblDirector = new JLabel("");
		lblDirector.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDirector.setBounds(319, 258, 373, 25);
		contentPane.add(lblDirector);
		
		lblFechaEstreno = new JLabel("");
		lblFechaEstreno.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFechaEstreno.setBounds(319, 301, 373, 25);
		contentPane.add(lblFechaEstreno);
		
		lblDuracion = new JLabel("");
		lblDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDuracion.setBounds(319, 343, 373, 25);
		contentPane.add(lblDuracion);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel lblSeleccioneUnCine = new JLabel("Seleccione un cine:");
		lblSeleccioneUnCine.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSeleccioneUnCine.setBounds(100, 466, 167, 25);
		contentPane.add(lblSeleccioneUnCine);
		
		cbxCine = new JComboBox<String>();
		cbxCine.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbxCine.setBounds(279, 465, 176, 30);
		contentPane.add(cbxCine);
		
		
	}
	
	private void consultar() {
		if(cbxCine.getSelectedIndex()==0) {
			Funciones.mensajePantalla("Error, digite la totalidad de los datos");
		}
		else {
			FuncionesTiempo ft = new FuncionesTiempo();
			try {
				ResultSet funciones = FachadaBoleteria.getInstance().consultarFuncionesByCineFecha(cbxCine.getSelectedIndex(),
						ft.DateToString(dateChooser.getDate()),this.pelicula.getId());
				if(!funciones.next()) {
					Funciones.mensajePantalla("No hay funciones disponibles de esta película en la fecha ingresada");
				}
				else {
					dispose();
					MostrarFunciones x = new MostrarFunciones(funciones);
					x.setVisible(true);
					
				}
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase Seleccionar funcion: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			}
		}
	}

	
	
}
