package edu.udistrital.fis.inventario.presentacion;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.api.logica.*;
import edu.udistrital.fis.inventario.persistencia.FachadaInventario;

public class ConsultarInventario extends AbstractFrame{

	private JPanel contentPane;
	private JComboBox<String> cbxCines;
	private JTextField txtNombreProducto;
	private JTable tablaProductos;
	private DefaultTableModel modeloTabla;

	public ConsultarInventario(int tipoAdmin,int idCine){
		setResizable(false);
		createFrame();
		try {
			Funciones.cargarDatosCbx(cbxCines, FachadaCine.getInstance().consultarCines());
			if(tipoAdmin==1) //Administrador normal
			{
				cbxCines.setSelectedIndex(idCine);
				cbxCines.setEnabled(false);
			}
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ConsultarInventario: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			dispose();
		}
		setIdentificador();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setTitle("Consultar inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbxCines = new JComboBox<String>();
		cbxCines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarDatos();
			}
		});
		cbxCines.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxCines.setBounds(66, 22, 165,26);
		contentPane.add(cbxCines);
		
		JLabel lblCine = new JLabel("Cine");
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCine.setBounds(22, 28, 34, 14);
		contentPane.add(lblCine);
		
		JLabel lblNombreDelProducto = new JLabel("Nombre del Producto");
		lblNombreDelProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombreDelProducto.setBounds(256, 28, 141, 14);
		contentPane.add(lblNombreDelProducto);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombreProducto.setBounds(401, 22, 141, 26);
		contentPane.add(txtNombreProducto);
		txtNombreProducto.setColumns(10);
		
		txtNombreProducto.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				cargarDatos();
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		modeloTabla = new DefaultTableModel(new Object[][] {}, new Object[] {"ID","Nombre","Cantidad","Unidad de medición"})
		{
			@Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return false;
			   }
		};
		
		tablaProductos = new JTable();
		tablaProductos.setModel(modeloTabla);
		
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(22, 75, 536, 196);
		contentPane.add(scrollPane);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void cargarDatos() {
		try {
			if(!(cbxCines.getSelectedIndex()==0)) {
				if(Funciones.validarVacio(txtNombreProducto.getText())) {
					//se consultan todos los productos del cine
					llenarTabla(FachadaInventario.getInstance().consultarInventario(cbxCines.getSelectedIndex()));
				}
				else {
					//se consultan productos en específico del cine
					llenarTabla(FachadaInventario.getInstance().consultarInventario(cbxCines.getSelectedIndex(),txtNombreProducto.getText()));
				}	
			}
			else {
				Funciones.limpiarTabla(modeloTabla);
			}
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase Consultar inventario: "+e.getMessage());
			Funciones.mensajePantalla("Error, la operación no pudo llevarse a cabo");
		}
	}
	
	private void llenarTabla(ResultSet datos) throws SQLException {
		Funciones.limpiarTabla(modeloTabla);
		if(datos.next()) {
			modeloTabla.addRow(new Object[] {datos.getObject(1),datos.getObject(2),datos.getObject(3),datos.getObject(4)});
			while(datos.next()) {
				modeloTabla.addRow(new Object[] {datos.getObject(1),datos.getObject(2),datos.getObject(3),datos.getObject(4)});
			}
		}
		
	}

	@Override
	protected void setIdentificador() {	
		this.identificador = "Consultar existencias";
	}
}
