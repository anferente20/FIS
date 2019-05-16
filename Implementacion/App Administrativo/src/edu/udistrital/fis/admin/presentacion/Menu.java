package edu.udistrital.fis.admin.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.empleado.presentencion.*;
import edu.udistrital.fis.inventario.presentacion.*;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JMenuItem menuAgregaEmpl;
	
	

	public Menu(String usuario, String contrasena, String tipoUsuario) {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("MEN\u00DA");
		createFrame();
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		contentPane.add(menuBar);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);

		
		
		menuAgregaEmpl = new JMenuItem("Agregar nuevo empleado");
		menuAgregaEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarEmpleado frame;
				try {
					frame = new AgregarEmpleado();
					frame.setVisible(true);
					
				} catch (SQLException e) {
					Funciones.mensajeConsola("Agregar empleado: "+e.getMessage());
					Funciones.mensajePantalla("Error, no se pudo llevar a cabo la operación");
				}
			}
		});
		mnEmpleados.add(menuAgregaEmpl);
		
		JMenuItem menuModificaEmpl = new JMenuItem("Modificar infomaci\u00F3n de un empleado");
		menuModificaEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarEmpleado frame = new ModificarEmpleado();
				frame.setVisible(true);
			}
		});
		mnEmpleados.add(menuModificaEmpl);
		
		JMenuItem menuDarBaja = new JMenuItem("Dar de Baja a un empleado");
		menuDarBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DarBajaEmpl frame = new DarBajaEmpl();
				frame.setVisible(true);
			}
		});
		mnEmpleados.add(menuDarBaja);
		
		JMenuItem menuConsultaEmpl = new JMenuItem("Consultar empleados");
		menuConsultaEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarEmplByCine frame;
				try {
					frame = new ConsultarEmplByCine();
					frame.setVisible(true);
				} catch (SQLException e) {
					Funciones.mensajeConsola("Consultar empleados: "+e.getMessage());
					Funciones.mensajePantalla("Error, no se pudo llevar a cabo la operación");
				}
			}
		});
		mnEmpleados.add(menuConsultaEmpl);
		
		JMenuItem menuNuevoAdmin = new JMenuItem("Administradores");
		menuNuevoAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionAdmins frame = new GestionAdmins();
				frame.setVisible(true);
			}
		});
		mnEmpleados.add(menuNuevoAdmin);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//MenÃº Inventario
		JMenu mnInventario = new JMenu("Inventario");
		menuBar.add(mnInventario);
		
		//Item de agregar producto 
		
		JMenuItem agregarProducto = new JMenuItem("Agregar producto");
		agregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarProducto frame = new AgregarProducto();
				frame.setVisible(true);
			}
		});
		mnInventario.add(agregarProducto);
		
		//ITEM de Actualizar inventario
		JMenuItem actualizarInventario = new JMenuItem("Agregar o modificar existencias de un cine");
		actualizarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarInventario frame;
				try {
					frame = new ModificarInventario();
					frame.setVisible(true);
				} catch (SQLException e1) {
					Funciones.mensajeConsola("Modificar inventario: "+e1.getMessage());
					Funciones.mensajePantalla("Error, no se pudo llevar a cabo la operación");
				}

			}
		});
		mnInventario.add(actualizarInventario);
		
		JMenuItem mntmConsultarExistencias = new JMenuItem("Consultar existencias");
		mntmConsultarExistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarInventario frame;
				try {
					frame = new ConsultarInventario();
					frame.setVisible(true);
				} catch (SQLException e) {
					Funciones.mensajeConsola("Consultar inventario: "+e.getMessage());
					Funciones.mensajePantalla("Error, no se pudo llevar a cabo la operación");
				}
				
			}
		});
		mnInventario.add(mntmConsultarExistencias);
	}
}