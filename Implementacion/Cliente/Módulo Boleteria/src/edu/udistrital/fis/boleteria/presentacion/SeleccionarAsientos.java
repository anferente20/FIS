package edu.udistrital.fis.boleteria.presentacion;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.boleteria.logica.Asiento;
import edu.udistrital.fis.boleteria.logica.Funcion;
import edu.udistrital.fis.boleteria.persistencia.FachadaBoleteria;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionarAsientos extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtCantidadAsientos;
	private JLayeredPane panelAsientos;
	private JLabel lblColorLibre;
	private JLabel lblColorOcupado;
	private int cantidadAsientosSeleccionados;
	private Funcion funcion;
	private ArrayList<Asiento> totalAsientos; //La totalidad de los 70 asientos
	private ArrayList<Asiento> asientosSeleccionados; //Asientos que son seleccionados
	
	public SeleccionarAsientos(Funcion funcion) {
		setResizable(false);
		this.asientosSeleccionados = new ArrayList<Asiento>();
		this.totalAsientos = new ArrayList<Asiento>();
		this.funcion = funcion;
		cantidadAsientosSeleccionados = 0;
		createFrame();
		try {
			cargarAsientos();
			lblColorLibre.setIcon(Asiento.colorLibre);
			lblColorOcupado.setIcon(Asiento.colorOcupada);
			txtCantidadAsientos.setText("0");
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase SeleccionarAsientos: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
	}
	
	private void createFrame() {
		setTitle("Asientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 1065);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Cantidad de asientos seleccionados:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(100, 39, 249, 23);
		contentPane.add(lblNewLabel);
		
		txtCantidadAsientos = new JTextField();
		txtCantidadAsientos.setEnabled(false);
		txtCantidadAsientos.setBounds(361, 38, 35, 29);
		contentPane.add(txtCantidadAsientos);
		txtCantidadAsientos.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aceptar();
			}
		});
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnAceptar.setBounds(442, 37, 97, 27);
		contentPane.add(btnAceptar);
		
		panelAsientos = new JLayeredPane();
		panelAsientos.setBounds(23, 84, 1033, 910);
		panelAsientos.setBorder(BorderFactory.createLineBorder(Color.black,1));
		contentPane.add(panelAsientos);
		panelAsientos.setLayout(null);
		
		lblColorLibre = new JLabel("");
		lblColorLibre.setBounds(619, 21, 50, 50);
		contentPane.add(lblColorLibre);
		
		JLabel lblAsientosLibres = new JLabel("Asientos libres");
		lblAsientosLibres.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAsientosLibres.setBounds(681, 37, 108, 25);
		contentPane.add(lblAsientosLibres);
		
		JLabel lblAsientosOcupados = new JLabel("Asientos seleccionados");
		lblAsientosOcupados.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAsientosOcupados.setBounds(884, 37, 165, 25);
		contentPane.add(lblAsientosOcupados);
		
		lblColorOcupado = new JLabel("");
		lblColorOcupado.setBounds(822, 21, 50, 50);
		contentPane.add(lblColorOcupado);
		setLocationRelativeTo(null);
	}
	
	private void cargarAsientos() throws SQLException {
		int x = 20;
		int y = 20;
		for(int i=0;i<7;i++) {
			String fila = Asiento.numeroToLetra(i+1);
			x = 20;
			for(int j=0;j<10;j++) {
				String columna = String.valueOf(j+1);
				ResultSet resultado = FachadaBoleteria.getInstance().consultarAsientosOcupados(this.funcion.getId(), fila, columna);
				Asiento asiento = null;
				if(resultado.next()) {
					//Es asiento está ocupado
					asiento = new Asiento(i+1, j+1, false, x, y, this);
					panelAsientos.add(asiento);
				}
				else {
					//El asiento está libre
					asiento = new Asiento(i+1, j+1, true, x, y, this);
					panelAsientos.add(asiento);
				}
				totalAsientos.add(asiento);
				x +=100;
			}
			y += 124;
		}
	}
	
	public void restaAsiento() {
		this.cantidadAsientosSeleccionados--;
		Funciones.mensajeConsola("Sillas seleccionadas: "+this.cantidadAsientosSeleccionados);
		txtCantidadAsientos.setText(String.valueOf(this.cantidadAsientosSeleccionados));
	}
	
	public void sumaAsiento() {
		this.cantidadAsientosSeleccionados++;
		Funciones.mensajeConsola("Sillas seleccionadas: "+this.cantidadAsientosSeleccionados);
		txtCantidadAsientos.setText(String.valueOf(this.cantidadAsientosSeleccionados));
	}
	
	private void aceptar() {
		getAsientosSeleccionados();
		int asientos = this.asientosSeleccionados.size();
		Funciones.mensajeConsola(String.valueOf(this.asientosSeleccionados.size()));
		int opcion = Funciones.mensajeConfirmacion("Estás a punto de comprar "+asientos+" boletas para esta función, ¿Deseas continuar?");
		if(opcion==0) {
			//Sí
			try {
				FachadaBoleteria.getInstance().comprarBoletas(this.funcion, this.asientosSeleccionados, SeleccionPelicula.idCliente, asientos);
				this.dispose();
				Funciones.mensajePantalla("¡BOLETAS COMPRADAS, DIFRUTA DE TU PELICULA!");
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase SeleccionarAsientos: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
			}
		}
		else {
			//No
			for(int i=0;i<asientos;i++) {
				this.asientosSeleccionados.remove(0);
			}
		}
	}
	
	private void getAsientosSeleccionados() {
		for(int i=0;i<this.totalAsientos.size();i++) {
			Asiento asiento = this.totalAsientos.get(i);
			if(asiento.getSeleccionado()) {
				//El asiento está seleccionado
				this.asientosSeleccionados.add(asiento);
			}
		}
	}
}
