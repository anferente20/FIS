package edu.udistrital.fis.boleteria.presentacion;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SeleccionPelicula extends JFrame {

	private JPanel contentPane;
	private JLayeredPane panelDinamico;
	

	public SeleccionPelicula() throws SQLException {
		cargarPeliculas();
		createFrame();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 13));
		setTitle("Boleter\u00EDa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 11, 647, 364);
		contentPane.add(scrollPane);
		
		panelDinamico = new JLayeredPane();
		panelDinamico.setLayout(null);
		panelDinamico.setBounds(0,0, 300, 364);
		scrollPane.setViewportView(panelDinamico);
		scrollPane.getViewport().setView(panelDinamico);
		
		
	}
	
	private void cargarPeliculas() throws SQLException {
		ResultSet peliculas = FachadaPelicula.getInstance().consultarPeliculasVigentes();
		
	}
}
