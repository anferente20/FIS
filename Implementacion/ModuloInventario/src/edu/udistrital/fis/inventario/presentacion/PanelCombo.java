package edu.udistrital.fis.inventario.presentacion;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.inventario.logica.Combo;
import edu.udistrital.fis.inventario.persistencia.FachadaInventario;
import edu.udistrital.imagen.Imagen;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

class PanelCombo extends JPanel {
	private JLabel lblImg;
	private JTextArea txtDescripcion;
	private JLabel lblPrecio;
	private Combo combo;
	private JLabel lblEstado;
	private JButton btnEliminar;
	private JButton btnModificar;
	
	public PanelCombo(Combo combo) {
		this.combo = combo;
		setLayout(null);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescripcin.setBounds(32, 42, 107, 20);
		add(lblDescripcin);
		
		JLabel lblP1 = new JLabel("Precio: $");
		lblP1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblP1.setBounds(32, 151, 107, 20);
		add(lblP1);
		
		lblImg = new JLabel("");
		lblImg.setBounds(384, 53, 157, 172);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(lblImg);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setBounds(151, 42, 173, 93);
		
		lblPrecio = new JLabel("");
		lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPrecio.setBounds(151, 151, 107, 20);
		add(lblPrecio);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnModificar.setBounds(34, 214, 97, 38);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEliminar.setBounds(143, 214, 97, 38);
		add(btnEliminar);
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEstado.setBounds(384, 226, 107, 20);
		add(lblEstado);
		
		JScrollPane scrollPane = new JScrollPane(txtDescripcion);
		scrollPane.setBounds(151, 42, 181, 93);
		add(scrollPane);
		
		cargar();
	}
	
	private void cargar() {
		this.txtDescripcion.setText(this.combo.getDescripcion());
		this.lblPrecio.setText(String.valueOf(this.combo.getPrecio()));
		Imagen.cargarImagen(lblImg, this.combo.getImg().getFile());
	}
	
	private void modificar() {
		ModificarCombo x = new ModificarCombo(this.combo,this);
		x.setVisible(true);
	}
	
	void setModificada(Combo combo) {
		this.combo = combo;
		cargar();
	}
	
	private void eliminar() {
		if(Funciones.mensajeConfirmacion("¿Está seguro de eliminar este combo?")==0) {
			try {
				FachadaInventario.getInstance().darBajaCombo(this.combo.getIdCombo());
				lblEstado.setText("ELIMINADO");
				lblEstado.setForeground(Color.red);
				btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
				Funciones.mensajePantalla("¡COMBO ELIMINADO!");
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase PanelCombo: "+e.getMessage());
				Funciones.mensajePantalla("Error, la operación no pudo llevarse a cabo");
			}
		}
	}
}
