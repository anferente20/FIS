package edu.udistrital.fis.inventario.presentacion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.basicos.presentacion.AbstractFrame;
import edu.udistrital.fis.inventario.persistencia.FachadaInventario;

public class ModificarInventario extends AbstractFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JComboBox<String> cbxProducto;
	private JComboBox<String> cbxCinema;
	private JLabel lblUMedicion;
	private JLabel lblCantidad;
	private int accion; // 1 -> agregar existencia al cine; 2 -> actualizar existencia del cine
	private JButton btnAceptar;

	public ModificarInventario(){
		createFrame();
		try {
			Funciones.cargarDatosCbx(cbxCinema,FachadaCine.getInstance().consultarCines());
			cargarProductos();
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ModificarInventario: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			dispose();
		}
		setIdentificador();
	}
	
	private void createFrame() {
		setTitle("Actualizar Inventario");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCinema = new JLabel("Cinema");
		lblCinema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCinema.setBounds(51, 26, 85, 30);
		contentPane.add(lblCinema);
		
		cbxCinema = new JComboBox<String>();
		cbxCinema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarProductoEnCine();
			}
		});
		cbxCinema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxCinema.setBounds(156, 28, 271, 26);
		contentPane.add(cbxCinema);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblProducto.setBounds(51, 76, 85, 30);
		contentPane.add(lblProducto);
		
		cbxProducto = new JComboBox<String>();
		cbxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificarProductoEnCine();
			}
		});
		cbxProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxProducto.setBounds(156, 78, 271, 26);
		contentPane.add(cbxProducto);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCantidad.setBounds(51, 126, 85, 30);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCantidad.setBounds(156, 128, 148, 26);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		lblUMedicion = new JLabel("");
		lblUMedicion.setBounds(316, 126, 110, 30);
		contentPane.add(lblUMedicion);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarInventario();
				}}
		);
		btnAceptar.setBounds(51, 182, 174, 37);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(263, 182, 174, 37);
		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
	}
	
	
	private void cargarProductos() throws SQLException {
		ResultSet productos = FachadaInventario.getInstance().listarProductoss();
		if(!productos.next()) {
			Funciones.mensajePantalla("Error, no se ha ingresado ningún producto. Por favor ingrese uno en la pestaña 'Agregar producto'");
			dispose();
		}
		else {
			cbxProducto.addItem("");
			cbxProducto.addItem(productos.getObject(1).toString()+" - "+productos.getObject(2).toString());
			while(productos.next()) {
				cbxProducto.addItem(productos.getObject(1).toString()+" - "+productos.getObject(2).toString());
			}
		}
		
	}
	
	private void actualizarInventario(){
		try {
			if(!validarVacios()) {
				int idProducto = Integer.parseInt(cbxProducto.getSelectedItem().toString().split(" - ")[0]);
				int idCine = cbxCinema.getSelectedIndex();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				//se valida qué acción se realizará
				if(accion==1) { //se agrega la existencia en el cine
					FachadaInventario.getInstance().agregarExistencias(cantidad, idCine, idProducto);
					Funciones.mensajePantalla("¡PRODUCTO INSERTADO CON ÉXITO!");
					dispose();
				}
				else {//el producto ya está registrado en el cine, y se actualizan los datos
					FachadaInventario.getInstance().actualizarInventario(cantidad, idCine, idProducto);
					Funciones.mensajePantalla("¡PRODUCTO ACTUALIZADO CON ÉXITO!");
					dispose();
				}
			}
			else {
				Funciones.mensajePantalla("Error, digite la totalidad de los datos");
			}
		}
		catch(SQLException  | NumberFormatException e) {
			Funciones.mensajeConsola("Clase ModificarInventario: "+e.getMessage());
			Funciones.mensajePantalla("Error al ejecutar la operación");
			
		}
	}
	
	private void verificarProductoEnCine() {
		txtCantidad.setText("");
		lblUMedicion.setText("");
		try {
			if(!cbxProducto.getSelectedItem().toString().isEmpty() && !cbxCinema.getSelectedItem().toString().isEmpty()) {
				int idProducto = Integer.parseInt(cbxProducto.getSelectedItem().toString().split(" - ")[0]);
				int idCine = cbxCinema.getSelectedIndex();
				boolean bandera = FachadaInventario.getInstance().verificarProductoEnCine(cbxCinema.getSelectedIndex(), idProducto);
				if(bandera) { //el producto ya fue registrado en el cine
					Funciones.mensajePantalla("El producto ya ha sido registrado en el cine seleccionado, se actualizará la existencia");
					ResultSet producto = FachadaInventario.getInstance().consultarInventario(idCine, idProducto);
					producto.next();
					txtCantidad.setText(producto.getObject(3).toString());
					lblUMedicion.setText(producto.getObject(4).toString());
					accion = 2;
					btnAceptar.setText("Actualizar existencia");
				}
				else { //el producto no ha sido registrado en el cine
					Funciones.mensajePantalla("El producto no ha sido registrado en el cine seleccionado");
					lblUMedicion.setText(FachadaInventario.getInstance().unidadMedicion(cbxProducto.getSelectedItem().toString().split(" - ")[1]));
					accion = 1;
					btnAceptar.setText("Agregar existencias");
				}
			}
		} 
		catch (SQLException  | NullPointerException e) {
			Funciones.mensajeConsola("Clase ModificarInventario: "+e.getMessage());
		}
	}
	
	private boolean validarVacios() {
		if(Funciones.validarVacio(txtCantidad.getText()) || Funciones.validarVacio(cbxCinema.getSelectedItem().toString())
				|| Funciones.validarVacio(cbxCinema.getSelectedItem().toString())) {
			return true;
		}
		return false;
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Agregar o modificar existencias de un cine";
	}
}


