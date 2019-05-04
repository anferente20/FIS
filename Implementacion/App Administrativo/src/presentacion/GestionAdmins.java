package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.FachadaEmpleado;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;

public class GestionAdmins extends JFrame {

	private JPanel contentPane;
	private JTable tablaAdmins;
	private DefaultTableModel modeloTabla;
	private JDesktopPane desktopPane;
	protected static boolean isOpen;

	public GestionAdmins() {
		setTitle("Administradores");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		createFrame();
		cargarAdmins();
		//renderizado de tabla para poder colocar los botones
		this.tablaAdmins.setDefaultRenderer(Object.class, new RenderTabla());
		isOpen = false;
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modeloTabla = new DefaultTableModel(new Object[][] {},new String[]{"Cine","Administrador",""}) 
		{public boolean isCellEditable(int row, int column) {
			return false;
			};
		};
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 669, 335);
		contentPane.add(desktopPane);
		desktopPane.setLayout(null);

		
		tablaAdmins = new JTable();
		tablaAdmins.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = tablaAdmins.getSelectedRow();
				int columna = tablaAdmins.getColumnModel().getColumnIndexAtX(arg0.getX());
				Object value = tablaAdmins.getValueAt(fila, columna);
				if(value instanceof JButton) {
					cambiarAdmin(fila+1);
				}
			}
		});
		
		tablaAdmins.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tablaAdmins.setBounds(0, 0, 468, 234);
		tablaAdmins.setRowHeight(30);
		tablaAdmins.setModel(modeloTabla);
		
		JScrollPane scrollPane = new JScrollPane(tablaAdmins);
		scrollPane.setBounds(043, 49 ,582, 236);
		desktopPane.add(scrollPane);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
	}

	
	private void cargarAdmins() {
		try {
			ResultSet admins = FachadaEmpleado.getInstance().consultarAdmins();
			while(admins.next()) {
				this.modeloTabla.addRow(new Object[] {admins.getString("cine"),admins.getString("adminInfo"), new JButton("Cambiar administrador")});
			}
		} catch (SQLException e) {
			System.out.println("Clase GestionAdmins: "+e.getMessage());
		}
	}
	
	private void cambiarAdmin(int fila) {
		System.out.println("Clikeado"+fila);
		if(!isOpen) {
			desktopPane.add(CambiarAdmin.getInstance());
			CambiarAdmin.getInstance().show();
			isOpen = true;
		}
	}
}
//clase que redenriza un boton en una tabla
class RenderTabla extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        if(value instanceof JButton){
            JButton btn = (JButton)value;
            return btn;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

    
}