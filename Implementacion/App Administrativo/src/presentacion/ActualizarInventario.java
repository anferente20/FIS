package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.FachadaCine;
import logica.FachadaInventario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ActualizarInventario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JComboBox cbxProducto;
	private JComboBox cbxCinema;
	private JLabel lblUMedicion;
	private JLabel lblCantidad;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActualizarInventario frame = new ActualizarInventario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ActualizarInventario() throws SQLException {
		createFrame();
		cargarCines();
		cargarProductos();
	}
	
	private void createFrame() {
		setTitle("Actualizar Invetario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCinema = new JLabel("Cinema");
		lblCinema.setBounds(50, 30, 110, 30);
		contentPane.add(lblCinema);
		
		cbxCinema = new JComboBox();
		cbxCinema.setBounds(190, 30, 110, 30);
		contentPane.add(cbxCinema);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(50, 80, 110, 30);
		contentPane.add(lblProducto);
		
		cbxProducto = new JComboBox();
		cbxProducto.setBounds(190, 80, 110, 30);
		contentPane.add(cbxProducto);
		cbxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(50, 130, 110, 30);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(190, 130, 110, 30);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		lblUMedicion = new JLabel("");
		lblUMedicion.setBounds(330, 130, 110, 30);
		contentPane.add(lblUMedicion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actualizar();
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(null,"¡DATOS ERRONEOS!");
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
	}
	
	private void cargarCines() throws SQLException {
		ResultSet cines = FachadaCine.getInstance().consultarCines();
		cbxCinema.addItem("");
		while(cines.next()) {
			cbxCinema.addItem(cines.getObject(1).toString());
		}
	}
	
	private void cargarProductos() throws SQLException {
		ResultSet productos = FachadaInventario.getInstance().listarProductoss();
		cbxProducto.addItem("");
		while(productos.next()) {
			cbxProducto.addItem(productos.getObject(1).toString());
		}
	}
	
	private void buscar() {
		if(cbxProducto.getSelectedItem().toString().equals("")) {
			lblUMedicion.setText("");
		}else {
			try {
				lblUMedicion.setText(FachadaInventario.getInstance().unidadMedicion(cbxProducto.getSelectedItem().toString()));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"¡DATOS ERRONEOS!");
			}			
		}
	}
	
	private void actualizar() throws NumberFormatException, SQLException {
		int idCine = FachadaCine.getInstance().buscarID(cbxCinema.getSelectedItem().toString());
		int idProducto = FachadaInventario.getInstance().buscarID(cbxProducto.getSelectedItem().toString());
		FachadaInventario.getInstance().actualizar(Integer.valueOf(txtCantidad.getText()),idCine,idProducto);
	}
}
