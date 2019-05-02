package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		menuBar.setBounds(0, 0, 97, 21);
		contentPane.add(menuBar);
		
		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);
		
		menuAgregaEmpl = new JMenuItem("Agregar nuevo empleado");
		menuAgregaEmpl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarEmpleado frame = new AgregarEmpleado();
				frame.setVisible(true);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnEmpleados.add(menuConsultaEmpl);
		
		JMenuItem menuNuevoAdmin = new JMenuItem("Asignar un nuevo administrador a un cine");
		mnEmpleados.add(menuNuevoAdmin);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
