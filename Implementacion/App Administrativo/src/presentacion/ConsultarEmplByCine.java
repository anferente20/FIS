package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.FachadaCine;
import logica.FachadaEmpleado;
import logica.Funciones;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarEmplByCine extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbCine;
	private DefaultTableModel modeloTabla;
	private JTable tablaEmpleados;

	public ConsultarEmplByCine() throws SQLException {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Consultar empleados");
		createFrame();
		cargarCines();
		tablaEmpleados.setModel(modeloTabla);
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElCine = new JLabel("Seleccione el cine:");
		lblSeleccioneElCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSeleccioneElCine.setBounds(26, 33, 148, 14);
		contentPane.add(lblSeleccioneElCine);
		
		cbCine = new JComboBox<String>();
		cbCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbCine.setBounds(168, 27, 152, 26);
		contentPane.add(cbCine);
		
		tablaEmpleados = new JTable();
		tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tablaEmpleados.setBounds(218, 121, 0, 0);
		modeloTabla = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nombres", "Apellidos", "Identificaci\u00F3n", "Tipo"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};

		
		JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(21, 75, 450, 162);
		contentPane.add(scrollPane);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnBuscar.setBounds(364, 26, 89, 27);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarEmpleados();
			}
		});
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void cargarCines() throws SQLException {
		ResultSet cines = FachadaCine.getInstance().consultarCines();
		cbCine.addItem("");
		while(cines.next()) {
			cbCine.addItem(cines.getObject(1).toString());
		}
	}
	//carga los registros de empleados en la tabla
	private void cargarEmpleados(){
		try {
			Funciones.limpiarTabla(this.modeloTabla);
			if(!(cbCine.getSelectedIndex()==0)) {
				ResultSet empleados = FachadaEmpleado.getInstance().consultarEmplByCine(cbCine.getSelectedIndex());
				while(empleados.next()) {
					modeloTabla.addRow(new Object[] {empleados.getString("id"),empleados.getString("nombres")
							,empleados.getString("apellidos"),empleados.getString("identificacion"),empleados.getString("tipoEmpleado")});
				}
				tablaEmpleados.setModel(modeloTabla);
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Error, ¡NO FUE POSIBLE CARGAR LOS REGISTROS!");
			System.out.println("Clase ConsultarEmplByCine: "+e.getMessage());
		}
		
	}

}
