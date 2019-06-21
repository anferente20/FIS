package edu.udistrital.fis.funciones.presentacion;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.logica.Pelicula;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;

public class MostrarFuncionesSala extends JFrame {

	private JPanel contentPane;
	private JLabel lblSala;
	private JScrollPane scrollPane;
	private JLabel lblCine;
	private JLabel lblFecha;
	private JLayeredPane panelDinamico;
	private String fecha;


	public MostrarFuncionesSala(int sala,String fecha, int idCine, ResultSet funciones) throws SQLException {
		this.fecha = fecha;
		createFrame();
		lblSala.setText("Sala: "+sala);
		lblCine.setText("Cine: "+FachadaCine.getInstance().getNombreCine(idCine));
		lblFecha.setText("Fecha: "+fecha);
		cargarFunciones(funciones);
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSala = new JLabel();
		lblSala.setText("Pelicula: <dynamic>");
		lblSala.setHorizontalAlignment(SwingConstants.LEFT);
		lblSala.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSala.setBounds(25, 32, 84, 14);
		contentPane.add(lblSala);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(217, 25, 556, 386);
		contentPane.add(scrollPane);
		
		panelDinamico = new JLayeredPane();
		panelDinamico.setBounds(33, 148, 533, 226);
		panelDinamico.setLayout(null);
		
		scrollPane.setViewportView(panelDinamico);
		scrollPane.getViewport().setView(panelDinamico);
		
		
		lblCine = new JLabel();
		lblCine.setText("Cine: <dynamic>");
		lblCine.setHorizontalAlignment(SwingConstants.LEFT);
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCine.setBounds(25, 70, 188, 14);
		contentPane.add(lblCine);
		
		lblFecha = new JLabel();
		lblFecha.setText("Fecha: <dynamic>");
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(25, 107, 146, 14);
		contentPane.add(lblFecha);
		
		setLocationRelativeTo(null);
	}
	
	private void cargarFunciones(ResultSet funciones) {
		try {
			int x = 0;
			agregarFuncion(funciones,x);
			while(funciones.next()) {
				x += 188;
				agregarFuncion(funciones,x);
			}
			panelDinamico.setPreferredSize(new Dimension(533, x));
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
		Pelicula pelicula = new Pelicula(funciones.getString("nombrePelicula"), "", 
				"", funciones.getString("duracion"), null, null, "", 0, "");
		byte[] imgPelicula = funciones.getBytes("imagenPelicula");
		funcion = new Funcion(pelicula, ft.StringToDate(this.fecha), funciones.getString("hora"), funciones.getInt("idsala"));
		funcion.setIdFuncion(funciones.getInt("idfuncion"));
		PanelFuncionBySala pf = new PanelFuncionBySala(funcion, imgPelicula);
		pf.setBounds(0, x, this.panelDinamico.getWidth(), 188);
		pf.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panelDinamico.add(pf);
	}
}
