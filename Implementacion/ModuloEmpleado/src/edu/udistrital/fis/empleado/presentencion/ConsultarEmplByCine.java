package edu.udistrital.fis.empleado.presentencion;



import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.api.logica.*;
import edu.udistrital.fis.empleado.persistencia.FachadaEmpleado;

public class ConsultarEmplByCine extends AbstractFrame {

	private JPanel contentPane;
	private JComboBox<String> cbCine;
	private DefaultTableModel modeloTabla;
	private JTable tablaEmpleados;

	public ConsultarEmplByCine(int tipoAdmin, int idCine){
		createFrame();
		try {
			Funciones.cargarDatosCbx(cbCine,FachadaCine.getInstance().consultarCines());
			if(tipoAdmin==1) //Administrador normal
			{
				cbCine.setSelectedIndex(idCine);
				cbCine.setEnabled(false);
			}
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ConsultarEmplByCine: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			dispose();
		}
		tablaEmpleados.setModel(modeloTabla);
		setIdentificador();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Consultar empleados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			@Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return false;
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
			}
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Clase ConsultarEmplByCine: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
		
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Consultar empleados";
	}

}
