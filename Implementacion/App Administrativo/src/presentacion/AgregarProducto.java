package presentacion;

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

import logica.Empleado;
import logica.FachadaEmpleado;
import logica.FachadaInventario;
import logica.Funciones;
import logica.Producto;
import persistencia.GestorProducto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtUMedicion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarProducto frame = new AgregarProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarProducto() {
		createFrame();
	}
	
	public void createFrame() {
		setTitle("Agregar Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdProducto = new JLabel("ID Producto");
		lblIdProducto.setBounds(50, 50, 110, 30);
		contentPane.add(lblIdProducto);
		
		txtID = new JTextField();
		txtID.setBounds(190, 50, 110, 30);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 100, 110, 30);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(190, 100, 110, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblUMedicion = new JLabel("U. Medición");
		lblUMedicion.setBounds(50,150,110,30);
		contentPane.add(lblUMedicion);
		
		txtUMedicion = new JTextField();
		txtUMedicion.setBounds(190,150,110,30);
		contentPane.add(txtUMedicion);
		txtUMedicion.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					agregarProducto();
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}}
		);
		btnAceptar.setBounds(95, 220, 97, 37);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(245, 220, 97, 37);
		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	/**
	 * Método que agrega un nuevo producto a la Base de datos
	 * @throws SQLException 
	 * @throws HeadlessException 
	 */
	public void agregarProducto() throws HeadlessException, SQLException {
		if(this.validarVacio()) {
			JOptionPane.showMessageDialog(null,"Error, digite la totalidad de los datos");
		}
		else {
			Producto producto = new Producto();
			producto.setIdProducto(Integer.parseInt(txtID.getText()));
			producto.setNombre(txtNombre.getText());
			producto.setUnidadMedicion(txtUMedicion.getText());
			if(FachadaInventario.getInstance().getGestorP().vereficarProducto(txtNombre.getText())) {
				try {
					FachadaInventario.getInstance().insertarProducto(producto);
					JOptionPane.showMessageDialog(null,"¡PRODUCTO INSERTADO CON ÉXITO");
					this.dispose();
				}
				catch(SQLException sqle) {
					JOptionPane.showMessageDialog(null, "Error, el ID o la identificación ya están registrados con otro usuario");
					System.out.println("Clase AgregarProducto: "+sqle.getMessage());
				}
			}else {
				JOptionPane.showMessageDialog(null, "El Producto ya existe");
			}
			
		}
	}
	/**
	 * Método que verifica que todos los campos esten llenos
	 * @return true hay algún campo vacío
	 * 		   false si todos los campos están llenos
	 */
	private boolean validarVacio() {
		if(Funciones.validarVacio(txtID.getText()) ||Funciones.validarVacio(txtNombre.getText()) || Funciones.validarVacio(txtUMedicion.getText()) ) {
			return true;
		}
		return false;
	}
}
