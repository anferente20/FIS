package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.logica.Pelicula;
import edu.udistrital.imagen.Imagen;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;

class MostrarFuncionesPelicula extends JFrame {

	private JPanel contentPane;
	private JLabel lblImgPeli;
	private JLabel lblNombrePeli;
	private JLabel lblCine;
	private JLabel lblFecha;
	private JLayeredPane panelDinamico;
	private String fecha;
	private Pelicula pelicula;
	
	public MostrarFuncionesPelicula(String nombrePelicula, byte[] img, ResultSet funciones, String fecha, String cine,Pelicula pelicula) {
		this.pelicula = pelicula;
		this.fecha = fecha;
		createFrame();
		Imagen.cargarImagen(lblImgPeli, img);
		lblNombrePeli.setText("Pelicula: "+nombrePelicula);
		lblFecha.setText("Fecha: "+fecha);
		lblCine.setText("Cine: "+cine);
		
		
		cargarFunciones(funciones);
		
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Funciones");
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		lblNombrePeli = new JLabel();
		lblNombrePeli.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombrePeli.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombrePeli.setBounds(36, 13, 366, 21);
		contentPane.add(lblNombrePeli);
		
		lblImgPeli = new JLabel("");
		lblImgPeli.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		lblImgPeli.setBounds(36, 154, 234, 268);
		contentPane.add(lblImgPeli);
		
		
		
		lblCine = new JLabel();
		lblCine.setHorizontalAlignment(SwingConstants.LEFT);
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCine.setBounds(36, 57, 203, 14);
		contentPane.add(lblCine);
		
		lblFecha = new JLabel();
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(36, 97, 146, 14);
		contentPane.add(lblFecha);
		
		
		panelDinamico = new JLayeredPane();
		panelDinamico.setBounds(0, 0, 298, 386);
		panelDinamico.setLayout(null);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(363, 25, 322, 386);
		scrollPane.setViewportView(panelDinamico);
		scrollPane.getViewport().setView(panelDinamico);
		
		
		contentPane.add(scrollPane);
		
		setLocationRelativeTo(null);
	}
	
	private void cargarFunciones(ResultSet funciones) {
		try {
			int x = 0;
			agregarFuncion(funciones,x);
			while(funciones.next()) {
				x += 150;
				agregarFuncion(funciones,x);
			}
			panelDinamico.setPreferredSize(new Dimension(298, x+150));
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase MostrarFuncionesPelicula: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void agregarFuncion(ResultSet funciones,int x) throws ParseException, SQLException {
		Funcion funcion;
		FuncionesTiempo ft = new FuncionesTiempo();
		funcion = new Funcion(pelicula, ft.StringToDate(this.fecha), funciones.getString("hora"), funciones.getInt("idsala"));
		funcion.setIdFuncion(funciones.getInt("idfuncion"));
		PanelFuncionByPelicula pf = new PanelFuncionByPelicula(funcion);
		pf.setBounds(0, x, this.panelDinamico.getWidth(), 150);
		pf.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panelDinamico.add(pf);
	}
}
