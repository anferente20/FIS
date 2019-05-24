package edu.udistrital.fis.empleado.presentencion;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.empleado.persistencia.FachadaEmpleado;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DarBajaEmpl extends JFrame {

	private JPanel contentPane;
	private JTable tablaEmpleados;
	private JTextField txtParametro;
	private DefaultTableModel modeloTabla;

	public DarBajaEmpl() {
		setTitle("Dar de baja a un empleado");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		createFrame();
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEscribaNombreApellido = new JLabel("Escriba nombre, apellido, nombre del cine o identificaci\u00F3n del empleado: ");
		lblEscribaNombreApellido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEscribaNombreApellido.setBounds(10, 23, 474, 22);
		contentPane.add(lblEscribaNombreApellido);
		
		tablaEmpleados = new JTable();

		
		modeloTabla = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nombres", "Apellidos", "Identificaci\u00F3n","Cine"
				}
			);
		tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tablaEmpleados.setBounds(63, 196, 541, 150);
		tablaEmpleados.setModel(modeloTabla);
		
		txtParametro = new JTextField();
		txtParametro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				cargarEmpleados();
			}
		});
		txtParametro.setBounds(496, 22, 238, 26);
		contentPane.add(txtParametro);
		txtParametro.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				darBaja();
			}
		});
		btnAceptar.setBounds(340, 259, 99, 34);
		contentPane.add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
		scrollPane.setBounds(119, 85, 541, 150);
		contentPane.add(scrollPane);
		setLocationRelativeTo(null);
	}
	//carga los registro de los empleados en la tabla
	private void cargarEmpleados(){
		Funciones.limpiarTabla(this.modeloTabla);
		if(!Funciones.validarVacio(txtParametro.getText())) {
			ResultSet empleados = null;
			//se valida si el parametro es un entero o cadena
			try {
				int valor = Integer.parseInt(txtParametro.getText());
				//es entero
				empleados = FachadaEmpleado.getInstance().sugerirEmpleados(valor);
			}
			catch(SQLException e) {
				System.out.println("Clase DarBajaEmpl: "+e.getMessage());
			}
			catch(NumberFormatException e) {
				//es una cadena
				System.out.println("Clase DarBajaEmpl: "+e.getMessage());
				try {
					empleados = FachadaEmpleado.getInstance().sugerirEmpleados(txtParametro.getText());
				}
				catch(SQLException e2) {
					System.out.println("Clase DarBajaEmpl: "+e.getMessage());
				}
			}
			//llena la tabla
			try {
				while(empleados.next()) {
				modeloTabla.addRow(new Object[] {empleados.getString("id"),empleados.getString("nombres")
						,empleados.getString("apellidos"),empleados.getString("identificacion"),empleados.getString("nombreCine")});}
			} catch (SQLException e) {
				System.out.println("Clase DarBajaEmpl: "+e.getMessage());
			}
		}
		
	}
	
	private void darBaja(){
		int numFila = tablaEmpleados.getSelectedRow();
		if(numFila==-1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ningún registro de la tabla");
		}
		else {
			int idEmpleado = Integer.parseInt((String) modeloTabla.getValueAt(numFila, 0));
			try {
				int opcion = JOptionPane.showConfirmDialog(null,"¿Está seguro de dar de baja al empleado seleccionado?");
				if(opcion==0) {
					FachadaEmpleado.getInstance().darBajaEmpl(idEmpleado);
					JOptionPane.showMessageDialog(null,"¡EMPLEADO DADO DE BAJA CON ÉXITO");
					Funciones.limpiarTabla(modeloTabla);
				}
				
			} catch (SQLException e) {
				System.out.println("Clase DarBajaEmpl: "+e.getMessage());
				JOptionPane.showMessageDialog(null,"Error, ¡EMPLEADO NO DADO DE BAJA CON ÉXITO!");
				Funciones.limpiarTabla(modeloTabla);
			}
		}
	}
}