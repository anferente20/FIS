package edu.udistrital.fis.inventario.presentacion;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.inventario.logica.FachadaInventario;
import edu.udistrital.fis.inventario.logica.Producto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class AgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtUMedicion;

	public AgregarProducto() {
		createFrame();
	}
	
	public void createFrame() {
		setTitle("Agregar Producto");
		setBounds(100, 100, 380, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdProducto = new JLabel("ID Producto");
		lblIdProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblIdProducto.setBounds(35, 22, 110, 30);
		contentPane.add(lblIdProducto);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtID.setBounds(193, 26, 139, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(35, 72, 110, 30);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombre.setBounds(193, 76, 139, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblUMedicion = new JLabel("Unidad de medici\u00F3n");
		lblUMedicion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUMedicion.setBounds(35,122,138,30);
		contentPane.add(lblUMedicion);
		
		txtUMedicion = new JTextField();
		txtUMedicion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtUMedicion.setBounds(193,126,139,26);
		contentPane.add(txtUMedicion);
		txtUMedicion.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
				}}
		);
		btnAceptar.setBounds(65, 173, 97, 37);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(204, 173, 97, 37);
		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	/**
	 * Método que agrega un nuevo producto a la Base de datos
	 * @throws SQLException 
	 */
	public void agregarProducto() {
		if(this.validarVacio()) {
			JOptionPane.showMessageDialog(null,"Error, digite la totalidad de los datos");
		}
		else {
			Producto producto = new Producto();
			producto.setIdProducto(Integer.parseInt(txtID.getText()));
			producto.setNombre(txtNombre.getText());
			producto.setUnidadMedicion(txtUMedicion.getText());
			try {
				if(FachadaInventario.getInstance().verificarProducto(txtNombre.getText())) {
					FachadaInventario.getInstance().insertarProducto(producto);
					JOptionPane.showMessageDialog(null,"¡PRODUCTO INSERTADO CON ÉXITO!");
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error, ya existe otro producto registrado con ese nombre");
				}
			}
			catch(SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Error, ya existe otro producto registrado con el ID digitado");
				System.out.println("Clase AgregarProducto: "+sqle.getMessage());
			}
		}
			
		}

	/**
	 * Método que verifica que todos los campos esten llenos
	 * @return true hay algún campo vacio
	 * 		   false si todos los campos estÃ¡n llenos
	 */
	private boolean validarVacio() {
		if(Funciones.validarVacio(txtID.getText()) ||Funciones.validarVacio(txtNombre.getText()) || Funciones.validarVacio(txtUMedicion.getText()) ) {
			return true;
		}
		return false;
	}
}
