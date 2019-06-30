package edu.udistrital.fis.boleteria.presentacion;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.boleteria.logica.Funcion;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;

public class MostrarFunciones extends JFrame {

	private JPanel contentPane;
	private JLayeredPane panelDinamico;

	public MostrarFunciones(ResultSet funciones) {
		createFrame();
		try {
			cargarFunciones(funciones);
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase MostrarFunciones: "+e.getMessage());
			dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
	}
	
	private void createFrame() {
		setResizable(false);
		setTitle("Funciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(34, 36, 360, 271);
		contentPane.add(scrollPane);
		
		panelDinamico = new JLayeredPane();
		panelDinamico.setBounds(0, 0, 1, 1);
		
		scrollPane.setViewportView(panelDinamico);
		scrollPane.getViewport().setView(panelDinamico);
		panelDinamico.setLayout(null);
		setLocationRelativeTo(null);
	}
	
	private void cargarFunciones(ResultSet funciones) throws SQLException {
		Funcion funcion = new Funcion();
		funcion.setId(funciones.getInt("idfuncion"));
		funcion.setHora(funciones.getString("hora"));
		funcion.setFecha(funciones.getString("fecha"));
		funcion.setIdSala(funciones.getInt("idsala"));
		funcion.setIdPelicula(funciones.getInt("idpelicula"));
		
		int x = 0;
		agregarPanel(x, funcion);
		while(funciones.next()) {
			x += 124;
			Funcion f = new Funcion();
			f.setId(funciones.getInt("idfuncion"));
			f.setHora(funciones.getString("hora"));
			f.setFecha(funciones.getString("fecha"));
			f.setIdSala(funciones.getInt("idsala"));
			f.setIdPelicula(funciones.getInt("idpelicula"));
			agregarPanel(x, f);
		}
		panelDinamico.setPreferredSize(new Dimension(337,x+124));
	}
	
	private void agregarPanel(int x, Funcion funcion) throws SQLException {
		PanelFuncion panelFuncion = new PanelFuncion(funcion,this);
		panelFuncion.setBounds(0, x, 337, 124);
		panelDinamico.add(panelFuncion);
	}
	
	
}
