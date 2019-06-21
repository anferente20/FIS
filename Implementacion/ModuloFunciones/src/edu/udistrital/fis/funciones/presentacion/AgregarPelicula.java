package edu.udistrital.fis.funciones.presentacion;


import java.awt.Color;
import edu.udistrital.placerHolder.TextPrompt;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.util.Date;
import java.awt.event.ActionEvent;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.funciones.logica.AlgoritmoPrimero;
import edu.udistrital.fis.funciones.logica.Pelicula;
import edu.udistrital.imagen.Imagen;

public class AgregarPelicula extends AbstractFrame{

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtMinutos;
	private JTextField txtHoras;
	private JLabel lblImagen;
	private JDateChooser dpFechaEstreno;
	private JTextArea txtSinopsis;
	private JTextField txtDirector;
	private Imagen img;
	private JTextField txtMeses;
	private JTextField txtDias;
	private JTextField txtFuncionesDia;

	public AgregarPelicula() {
		//Imagen que se va a cargar
		img = new Imagen();
		createFrame();
	}
	
	private void createFrame() {
		
		setIdentificador();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 782, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Agrega nueva pel\u00EDcula");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(39, 43, 68, 22);
		contentPane.add(lblNombre);
		
		JLabel lblFechaDeEstreno = new JLabel("Fecha de estreno:");
		lblFechaDeEstreno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFechaDeEstreno.setBounds(39, 91, 125, 22);
		contentPane.add(lblFechaDeEstreno);
		
		JLabel lblDuracin = new JLabel("Duraci\u00F3n:");
		lblDuracin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDuracin.setBounds(39, 141, 68, 22);
		contentPane.add(lblDuracin);
		
		JLabel lblSinopsis = new JLabel("Sinopsis:");
		lblSinopsis.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSinopsis.setBounds(39, 367, 68, 22);
		contentPane.add(lblSinopsis);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombre.setBounds(201, 43, 201, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtMinutos = new JTextField();
		txtMinutos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMinutos.setBounds(321, 141, 81, 26);
		contentPane.add(txtMinutos);
		txtMinutos.setColumns(10);
	
		txtHoras = new JTextField();
		txtHoras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtHoras.setBounds(201, 141, 81, 26);
		contentPane.add(txtHoras);
		txtHoras.setColumns(10);
		
		dpFechaEstreno = new JDateChooser();
		dpFechaEstreno.setBounds(201, 93, 201, 26);
		contentPane.add(dpFechaEstreno);
		txtSinopsis = new JTextArea();
		txtSinopsis.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane(txtSinopsis);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(39, 417, 353, 152);
		contentPane.add(scrollPane);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(443, 100, 291, 342);
		contentPane.add(lblImagen);
		
		JButton btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarImagen();
			}
		});
		btnCargarImagen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCargarImagen.setBounds(537, 476, 125, 30);
		contentPane.add(btnCargarImagen);
		setLocationRelativeTo(null);
		
		lblImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK,2 ));
		

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertarPelicula();
			}
		});
		btnAceptar.setBounds(333, 595, 97, 30);
		contentPane.add(btnAceptar);
		
		JLabel lblDirectores = new JLabel("Director(es):");
		lblDirectores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDirectores.setBounds(39, 198, 88, 22);
		contentPane.add(lblDirectores);
		
		txtDirector = new JTextField();
		txtDirector.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDirector.setBounds(201, 199, 201, 26);
		contentPane.add(txtDirector);
		txtDirector.setColumns(10);
		
		JLabel lblTiempoEnCartelera = new JLabel("Tiempo en cartelera:");
		lblTiempoEnCartelera.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTiempoEnCartelera.setBounds(39, 251, 137, 22);
		contentPane.add(lblTiempoEnCartelera);
		
		txtMeses = new JTextField();
		txtMeses.setBounds(201, 250, 81, 26);
		contentPane.add(txtMeses);
		txtMeses.setColumns(10);
		
		txtDias = new JTextField();
		txtDias.setColumns(10);
		txtDias.setBounds(321, 250, 81, 26);
		contentPane.add(txtDias);
		
		JLabel lblFuncionesPorDa = new JLabel("Funciones por d\u00EDa:");
		lblFuncionesPorDa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFuncionesPorDa.setBounds(39, 305, 137, 22);
		contentPane.add(lblFuncionesPorDa);
		
		txtFuncionesDia = new JTextField();
		txtFuncionesDia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtFuncionesDia.setColumns(10);
		txtFuncionesDia.setBounds(201, 304, 201, 26);
		contentPane.add(txtFuncionesDia);
		
		//Place holder
		TextPrompt thHoras = new TextPrompt("Horas", txtHoras);
		TextPrompt thMinutos = new TextPrompt("Minutos", txtMinutos);
		TextPrompt thMeses = new TextPrompt("Meses", txtMeses);
		TextPrompt thDias = new TextPrompt("Dias", txtDias);
		
		thMeses.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		thDias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("Horas");
		lblNewLabel.setBounds(223, 170, 33, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(340, 170, 44, 16);
		contentPane.add(lblMinutos);
		
		JLabel lblMeses = new JLabel("Meses");
		lblMeses.setBounds(226, 275, 44, 16);
		contentPane.add(lblMeses);
		
		JLabel lblDias = new JLabel("Dias");
		lblDias.setBounds(351, 275, 33, 16);
		contentPane.add(lblDias);
		//Minima fecha seleccionable = HOY
		dpFechaEstreno.setMinSelectableDate(new Date());
		
	}
	
	private void cargarImagen() {
		//se carga la imagen que el usuario desee cargar
		img.cargarImagen(lblImagen);
	}
	
	private void insertarPelicula() {
		if(validar()==true ) {
			Funciones.mensajePantalla("Error, verifique los datos");
		}
		else {
			this.dispose();
			String duracion = txtHoras.getText()+" hours "+txtMinutos.getText()+" minutes";
			String tiempoCartelera = txtMeses.getText()+" months "+txtDias.getText()+" days";
			FuncionesTiempo ft = new FuncionesTiempo();
			String fecha = ft.DateToString(dpFechaEstreno.getDate());
			int funcionesDia = Integer.parseInt(txtFuncionesDia.getText());
			Pelicula pelicula = new Pelicula(txtNombre.getText(), txtSinopsis.getText(), fecha,
			duracion, img.getFile(), new AlgoritmoPrimero(), 
			tiempoCartelera, funcionesDia, txtDirector.getText());
		}
	}
	
	private boolean validar() {
		if(Funciones.validarVacio(txtNombre.getText()) || Funciones.validarVacio(dpFechaEstreno.getDateFormatString()) ||
				Funciones.validarVacio(txtHoras.getText()) || Funciones.validarVacio(txtMinutos.getText()) || 
				Funciones.validarVacio(txtSinopsis.getText()) || Funciones.validarVacio(txtDirector.getText())
				|| this.img.getCargada()==false || validarFecha() || validarNumericos() || validarFechasTiempo()){
			return true;
		}
		return false;
	}
	
	//Valida que la fecha de estreno sea mayor que la fecha actual
	private boolean validarFecha() {
		Date actualFecha = new Date();
		if(actualFecha.compareTo(dpFechaEstreno.getDate())>0) {
			return true;
		}
		try {
			FuncionesTiempo ft = new FuncionesTiempo();
			ft.DateToString(dpFechaEstreno.getDate());
		}
		catch(NullPointerException npe) {
			return true;
		}
		return false;
	}
	//Valida que solo existan caracteres de tipo numerico
	private boolean validarNumericos() {
		if(Funciones.validarNumerico(txtDias.getText())==false || Funciones.validarNumerico(txtMeses.getText())==false ||
		Funciones.validarNumerico(txtHoras.getText())==false || Funciones.validarNumerico(txtMinutos.getText())==false || 
		Funciones.validarNumerico(txtFuncionesDia.getText())==false) {
			return true;
		}
		return false;
	}
	//Valida que los valores de los campos de tiempo estén correctos
	private boolean validarFechasTiempo() {
		try {
 			boolean validarHoras = Integer.parseInt(txtHoras.getText())>Pelicula.HORAS_MAXIMAS_DURACION;
			boolean validarMinutos = !(Integer.parseInt(txtMinutos.getText())>=0 && Integer.parseInt(txtMinutos.getText())<=59);
			boolean validarMeses = Integer.parseInt(txtMeses.getText())>Pelicula.MESES_MAXIMOS_DURACION;
			boolean validarFunciones = Integer.parseInt(txtFuncionesDia.getText())>Pelicula.FUNCIONES_MAXIMAS_POR_DIA;
			boolean validarDias = !(Integer.parseInt(txtDias.getText())>=0 && Integer.parseInt(txtDias.getText())<=30);
			if(validarHoras || validarMinutos || validarMeses || validarDias || validarFunciones) {
				return true;
			}
		}
		catch(NumberFormatException nfe) {
			
		}
		return false;
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Agregar una nueva película";
	}

}
