package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.logica.Pelicula;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;
import edu.udistrital.imagen.Imagen;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PanelFuncionBySala extends JPanel implements PanelFuncion{
	private JLabel lblEstado;
	private Funcion funcion;
	private JLabel lblHora;
	private JLabel lblPelicula;
	private JButton btnCancelar;
	private JLabel lblImg;
	private JButton btnModificar;


	public PanelFuncionBySala(Funcion funcion, byte[] imgPelicula) {
		this.funcion = funcion;
		setLayout(null);
		
		JLabel label = new JLabel("Hora:");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(25, 30, 56, 20);
		add(label);
		
		JLabel label1 = new JLabel("Pelicula:");
		label1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label1.setBounds(25, 73, 56, 20);
		add(label1);
		
		lblHora = new JLabel();
		lblHora.setText("<dynamic>:<dynamic>");
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHora.setBounds(94, 30, 78, 20);
		add(lblHora);
		
		lblPelicula = new JLabel();
		lblPelicula.setText("0");
		lblPelicula.setHorizontalAlignment(SwingConstants.LEFT);
		lblPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPelicula.setBounds(93, 73, 102, 20);
		add(lblPelicula);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(224, 47, 97, 32);
		add(btnModificar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setBounds(224, 109, 97, 32);
		add(btnCancelar);
		
		lblEstado = new JLabel();
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEstado.setBounds(25, 125, 124, 16);
		add(lblEstado);
		
		lblImg = new JLabel("");
		lblImg.setBounds(358, 13, 135, 151);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(lblImg);

		cargar(imgPelicula);
	}
	
	private void cargar(byte[] imgPelicula) {
		Pelicula pelicula = this.funcion.getPelicula();
		String[] cadena = this.funcion.getHora().split(":");
		this.lblHora.setText(cadena[0]+":"+cadena[1]);
		this.lblPelicula.setText(pelicula.getNombre());
		
		//Imagen
		Imagen.cargarImagen(lblImg, imgPelicula);
	}
	
	private void cancelar() {
		int opcion = Funciones.mensajeConfirmacion("¿Está seguro de querer cancelar esta función?");
		if(opcion==0) {
			try {
				FachadaPelicula.getInstance().cancelarFuncion(this.funcion.getIdFuncion());
				this.btnCancelar.setEnabled(false);
				this.lblEstado.setText("CANCELADA");
				this.btnCancelar.setEnabled(false);
				this.btnModificar.setEnabled(false);
				this.lblEstado.setForeground(Color.red);
				Funciones.mensajePantalla("¡FUNCIÓN ELIMINADA!");
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase PanelFuncion: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible ejecutar la operación");
			}
		}
	}
	
	public void setModificada(String hora) {
		this.lblHora.setText(hora);
		this.funcion.setHora(hora);
	}
	
	private void modificar() {
		ModificarFuncion x = new ModificarFuncion(this.funcion, this);
		x.setVisible(true);
	}

	@Override
	public void setModificada(String hora, int sala, int idCine) {
		// TODO Auto-generated method stub
		
	}
}
