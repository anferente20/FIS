package edu.udistrital.fis.inventario.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.inventario.logica.Combo;
import edu.udistrital.fis.inventario.persistencia.FachadaInventario;
import edu.udistrital.imagen.Imagen;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

class ModificarCombo extends JFrame {

	private JPanel contentPane;
	private PanelCombo target;
	private JTextField txtPrecio;
	private JLabel lblImagen;
	private JTextArea txtDescripcion;
	private Imagen img;
	private boolean cargada=true;
	private Combo combo;

	public ModificarCombo(Combo combo,PanelCombo target) {
		this.combo = combo;
		this.target = target;
		
		createFrame();
		
		txtDescripcion.setText(this.combo.getDescripcion());
		txtPrecio.setText(String.valueOf((int)this.combo.getPrecio()));
		this.img = this.combo.getImg();
		Imagen.cargarImagen(lblImagen, this.combo.getImg().getFile());
	}
	
	private void createFrame() {
		setTitle("Modificar Combo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 331);;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Descripcion:");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(27, 51, 91, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Precio: $");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label_1.setBounds(27, 144, 91, 16);
		contentPane.add(label_1);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(151, 142, 191, 28);
		contentPane.add(txtPrecio);
		
		lblImagen = new JLabel("");
		lblImagen.setBorder(BorderFactory.createLineBorder(Color.black,2));
		lblImagen.setBounds(383, 13, 168, 178);
		contentPane.add(lblImagen);
		
		JButton button = new JButton("Cargar Imagen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarImagen();
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button.setBounds(398, 204, 140, 34);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Aceptar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		button_1.setBounds(223, 237, 140, 34);
		contentPane.add(button_1);
		
	
		txtDescripcion = new JTextArea();
		txtDescripcion.setText((String) null);
		txtDescripcion.setBounds(0, 0, 179, 91);
		
		JScrollPane scrollPane = new JScrollPane(txtDescripcion);
		scrollPane.setBounds(151, 51, 191, 64);
		contentPane.add(scrollPane);
		
		setLocationRelativeTo(null);
	}
	
	void cargarImagen() {
		this.img = new Imagen();
		img.cargarImagen(lblImagen);
		this.cargada = true;
	}

	void modificar() {
		if(Funciones.validarVacio(txtDescripcion.getText()) || Funciones.validarVacio(txtPrecio.getText()) ||
				!Funciones.validarNumerico(txtPrecio.getText()) || !this.cargada) {
			Funciones.mensajePantalla("Error, verifique los datos");
		}
		else {
			this.combo.setDescripcion(txtDescripcion.getText());
			this.combo.setPrecio(Float.parseFloat(txtPrecio.getText()));
			this.combo.setImg(this.img);
			try {
				FachadaInventario.getInstance().modificarCombo(combo);
				Funciones.mensajePantalla("¡COMBO MODIFICADO!");
				this.dispose();
				this.target.setModificada(this.combo);
			} catch (FileNotFoundException | SQLException e) {
				Funciones.mensajeConsola("Clase ModificarCombo: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
			}
		}
	}
}
