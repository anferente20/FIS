package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MostrarFuncionesSala extends JFrame {

	private JPanel contentPane;
	private JLabel lblSala;
	private JScrollPane scrollPane;
	private JLabel lblCine;
	private JLabel lblFecha;


	public MostrarFuncionesSala(int sala,String fecha, String cine) {
		createFrame();
		lblSala.setText("Sala: "+sala);
		lblCine.setText("Cine: "+cine);
		lblFecha.setText("Fecha: "+fecha);
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 790, 356);
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
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(223, 25, 507, 277);
		contentPane.add(scrollPane);
		
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
	}

}
