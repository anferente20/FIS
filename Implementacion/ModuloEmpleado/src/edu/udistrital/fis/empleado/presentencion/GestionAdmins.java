package edu.udistrital.fis.empleado.presentencion;


import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.empleado.persistencia.FachadaEmpleado;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class GestionAdmins extends JFrame {

	private JPanel contentPane;
	private JTable tablaAdmins;
	private DefaultTableModel modeloTabla;
	private JButton btnRetroceder;
	private JPanel panelPrincipal;

	private GestionAdmins() {
		createFrame();
		cargarAdmins();
		//renderizado de tabla para poder colocar los botones
		this.tablaAdmins.setDefaultRenderer(Object.class, new RenderTabla());
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Administradores");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		panelPrincipal = panelAdmins();
		contentPane.add(panelPrincipal);
		
		btnRetroceder = new JButton("<<");
		btnRetroceder.setBounds(22, 359, 66, 38);
		btnRetroceder.setEnabled(false);
		contentPane.add(btnRetroceder);
		
	}

	
	private void cargarAdmins() {
		try {
			ResultSet admins = FachadaEmpleado.getInstance().consultarAdmins();
			while(admins.next()) {
				this.modeloTabla.addRow(new Object[] {admins.getString("cine"),admins.getString("adminInfo"), new JButton("Cambiar administrador")});
			}
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase GestionAdmins: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			this.dispose();
		}
	}
	
	private void cambiarAdmin(int fila) {
		try{
			System.out.println("Clickeado "+fila);
			int opcion = Integer.parseInt(Funciones.mensajeInPut("¿Cómo desea hacer el cambio?:\n1. Ascender a un empleado del mismo "
				+ "cine como administrador\n2. Asignar a un administrador de otro cine\n3.Salir"));
			switch(opcion) {
				case 1:
				//Ascender a un empleado del mismo cine
				break;
				case 2:
				//Asignar un administrador de otro cine
				break;
				case 3:
					//Se cierra la ventana de dialogo
				break;
				default: 
					cambiarAdmin(fila);
			}
		}
		catch (NullPointerException | NumberFormatException e) {
			cambiarAdmin(fila);
		}
	}
	private JPanel panelAdmins() {
		JPanel panel = new JPanel();
		panel.setBounds(18, 24, 676, 322);

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
		panel.setLayout(null);
		modeloTabla = new DefaultTableModel(new Object[][] {},new String[]{"Cine","Administrador",""}) ;
		
		tablaAdmins.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tablaAdmins.setBounds(0, 0, 468, 234);
		tablaAdmins.setRowHeight(30);
		tablaAdmins.setModel(modeloTabla);
		
		JScrollPane scrollPane = new JScrollPane(tablaAdmins);
		scrollPane.setBounds(0, 0, 676, 322);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		return panel;
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