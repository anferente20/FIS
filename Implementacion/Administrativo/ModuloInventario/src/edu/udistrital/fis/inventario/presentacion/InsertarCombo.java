package edu.udistrital.fis.inventario.presentacion;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.inventario.logica.Combo;
import edu.udistrital.fis.inventario.persistencia.FachadaInventario;
import edu.udistrital.imagen.Imagen;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class InsertarCombo extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrecio;
	private Imagen img;
	private JLabel lblImagen;
	private JTextArea txtDescripcion;
	private boolean cargada = false;

	public InsertarCombo() {
		createFrame();
	}
	
	private void createFrame() {
		setResizable(false);
		setTitle("Insertar Combo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescripcion.setBounds(25, 51, 91, 16);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDescripcion.setBounds(149, 51, 191, 64);
		
		JLabel lblPrecio = new JLabel("Precio: $");
		lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPrecio.setBounds(25, 144, 91, 16);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(149, 142, 179, 28);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(381, 13, 168, 178);
		lblImagen.setBorder(BorderFactory.createLineBorder(Color.black,2));
		contentPane.add(lblImagen);
		
		JButton btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarImagen();
			}
		});
		btnCargarImagen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCargarImagen.setBounds(396, 204, 140, 34);
		contentPane.add(btnCargarImagen);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.setBounds(223, 237, 140, 34);
		contentPane.add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane(txtDescripcion);
		scrollPane.setBounds(149, 51, 191, 64);
		contentPane.add(scrollPane);
		setLocationRelativeTo(null);
	}
	
	private void cargarImagen() {
		this.img = new Imagen();
		img.cargarImagen(lblImagen);
		this.cargada = true;
	}
	
	private void insertar() {
		if(Funciones.validarVacio(txtDescripcion.getText()) || Funciones.validarVacio(txtPrecio.getText()) ||
				!Funciones.validarNumerico(txtPrecio.getText()) || !this.cargada) {
			Funciones.mensajePantalla("Error, verifique los datos");
		}
		else {
			Combo combo = new Combo();
			combo.setDescripcion(txtDescripcion.getText());
			combo.setPrecio(Float.parseFloat(txtPrecio.getText()));
			combo.setImg(this.img);
			try {
				FachadaInventario.getInstance().insertarCombo(combo);
				Funciones.mensajePantalla("¡COMBO INSERTADO!");
				this.dispose();
				GestionCombos x = new GestionCombos();
				x.setVisible(true);
			} catch (FileNotFoundException | SQLException e) {
				Funciones.mensajeConsola("Clase InsertarCombo: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
			}
		}
	}
}
