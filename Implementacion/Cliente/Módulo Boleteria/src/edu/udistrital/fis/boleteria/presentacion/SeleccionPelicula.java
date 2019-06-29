package edu.udistrital.fis.boleteria.presentacion;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.boleteria.logica.Pelicula;
import edu.udistrital.fis.boleteria.persistencia.FachadaBoleteria;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SeleccionPelicula extends AbstractFrame {

	private JPanel contentPane;
	private JLayeredPane panelDinamicoCartelera;
	private JScrollPane scrollPaneCartelera;
	private JLayeredPane panelDinamicoEstrenos;
	private static final int heightPanel = 435;
	static int idCliente;

	public SeleccionPelicula(int idCliente){
		SeleccionPelicula.idCliente = idCliente;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				panelDinamicoCartelera.removeAll();
				panelDinamicoEstrenos.removeAll();
				try {
					cargarPeliculasCartelera();
					cargarPeliculasEstrenos();
				} catch (SQLException e) {
					Funciones.mensajeConsola("Clase SeleccionPelicula: "+e.getMessage());
					dispose();
					Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
				}	
			}
		});
		createFrame();
		setIdentificador();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 13));
		setTitle("Pel\u00EDculas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1397, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPaneCartelera = new JScrollPane();
		scrollPaneCartelera.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneCartelera.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneCartelera.setBounds(19, 52, 647, heightPanel+20);
		contentPane.add(scrollPaneCartelera);
		
		panelDinamicoCartelera = new JLayeredPane();
		panelDinamicoCartelera.setLayout(null);
		panelDinamicoCartelera.setBounds(0,0, 300, 370);
		scrollPaneCartelera.setViewportView(panelDinamicoCartelera);
		scrollPaneCartelera.getViewport().setView(panelDinamicoCartelera);
		
		JScrollPane scrollPaneEstrenos = new JScrollPane();
		scrollPaneEstrenos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneEstrenos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneEstrenos.setBounds(707, 52, 647, heightPanel+20);
		contentPane.add(scrollPaneEstrenos);
		
		panelDinamicoEstrenos = new JLayeredPane();
		panelDinamicoEstrenos.setLayout(null);
		panelDinamicoEstrenos.setBounds(0,0, 300, 370);
		scrollPaneEstrenos.setColumnHeaderView(panelDinamicoEstrenos);
		scrollPaneEstrenos.getViewport().setView(panelDinamicoEstrenos);
		
		
		JLabel lblEnCartelera = new JLabel("En cartelera:");
		lblEnCartelera.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEnCartelera.setBounds(19, 13, 96, 26);
		contentPane.add(lblEnCartelera);
		
		JLabel lblPrximosEstrenos = new JLabel("Pr\u00F3ximos estrenos:");
		lblPrximosEstrenos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPrximosEstrenos.setBounds(707, 13, 127, 26);
		contentPane.add(lblPrximosEstrenos);
		
		setLocationRelativeTo(null);
	}
	
	private void cargarPeliculasCartelera() throws SQLException {
		ResultSet peliculas = FachadaBoleteria.getInstance().consultarPeliculasCartelera();
		int x = 0;
		while(peliculas.next()) {
			Pelicula pelicula = new Pelicula();
			pelicula.setNombre(peliculas.getString("nombrepelicula"));
			pelicula.setId(peliculas.getInt("idpelicula"));
			pelicula.setDuracion(peliculas.getString("duracion"));
			pelicula.setDirector(peliculas.getString("nombredirector"));
			pelicula.setSinopsis(peliculas.getString("sinopsis"));
			byte[] img = peliculas.getBytes("img");
			pelicula.setFechaEstreno(peliculas.getString("fechaEstreno"));
			
			PanelPelicula panel = new PanelPelicula(this,pelicula,img,false);
			panel.setBounds(x,0,257,heightPanel);
			panelDinamicoCartelera.add(panel);
			x += 257;
		}
		
		panelDinamicoCartelera.setPreferredSize(new Dimension(x,heightPanel));
	}
	
	private void cargarPeliculasEstrenos() throws SQLException {
		ResultSet peliculas = FachadaBoleteria.getInstance().consultarPeliculasEstrenos();
		int x = 0;
		while(peliculas.next()) {
			Pelicula pelicula = new Pelicula();
			pelicula.setNombre(peliculas.getString("nombrepelicula"));
			pelicula.setId(peliculas.getInt("idpelicula"));
			pelicula.setDuracion(peliculas.getString("duracion"));
			pelicula.setDirector(peliculas.getString("nombredirector"));
			pelicula.setSinopsis(peliculas.getString("sinopsis"));
			byte[] img = peliculas.getBytes("img");
			pelicula.setFechaEstreno(peliculas.getString("fechaEstreno"));
			
			PanelPelicula panel = new PanelPelicula(this,pelicula,img,true);
			panel.setBounds(x,0,257,heightPanel);
			panelDinamicoEstrenos.add(panel);
			x += 257;
		}
		
		panelDinamicoEstrenos.setPreferredSize(new Dimension(x,heightPanel));
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Comprar boletas";
		
	}
}
