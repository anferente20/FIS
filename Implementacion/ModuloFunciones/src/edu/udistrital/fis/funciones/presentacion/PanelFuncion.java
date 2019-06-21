package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JPanel;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

class PanelFuncion extends JPanel {

	private Funcion funcion;
	private JButton btnCancelar;
	private JLabel lblEstado;
	private JLabel lblSala;
	private JLabel lblHora;
	
	public PanelFuncion(Funcion funcion) throws SQLException {
		
		this.funcion = funcion;
		setLayout(null);
		
		JLabel lbl1 = new JLabel("Hora:");
		lbl1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl1.setBounds(32, 24, 56, 20);
		add(lbl1);
		
		JLabel lbl2 = new JLabel("Sala:");
		lbl2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl2.setBounds(32, 67, 56, 20);
		add(lbl2);
		
		lblHora = new JLabel();
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHora.setBounds(83, 24, 65, 20);
		add(lblHora);
		
		lblSala = new JLabel();
		lblSala.setHorizontalAlignment(SwingConstants.LEFT);
		lblSala.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSala.setBounds(83, 67, 83, 20);
		add(lblSala);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(172, 24, 97, 32);
		add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setBounds(172, 71, 97, 32);
		add(btnCancelar);
		
		lblEstado = new JLabel();
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEstado.setBounds(83, 120, 124, 16);
		add(lblEstado);
		
		cargar();

	}
	
	private void cargar() throws SQLException {
		String[] cadena = this.funcion.getHora().split(":");
		lblHora.setText(cadena[0]+":"+cadena[1]);
		
		lblSala.setText(String.valueOf(FachadaPelicula.getInstance().getConsecutivoSala(funcion.getIdSala())));
	}
	
	private void modificar() {
		ModificarFuncion  x = new ModificarFuncion(this.funcion,this);
		x.setVisible(true);
	}
	
	void setModificada(String hora,int sala,int idCine) {
		this.lblHora.setText(hora);
		this.lblSala.setText(String.valueOf(sala));
		
		this.funcion.setHora(hora);
		try {
			this.funcion.setIdSala(FachadaPelicula.getInstance().getIdSala(sala, idCine));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cancelar() {
		int opcion = Funciones.mensajeConfirmacion("¿Está seguro de querer cancelar esta función?");
		if(opcion==0) {
			try {
				FachadaPelicula.getInstance().cancelarFuncion(this.funcion.getIdFuncion());
				this.btnCancelar.setEnabled(false);
				this.lblEstado.setText("CANCELADA");
				this.lblEstado.setForeground(Color.red);
				Funciones.mensajePantalla("¡FUNCIÓN ELIMINADA!");
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase PanelFuncion: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible ejecutar la operación");
			}
		}
	}
}
