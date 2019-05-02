package presentacion;



import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import logica.Empleado;
import logica.FachadaEmpleado;
import logica.Funciones;

import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscaEmpleado;
	private JTextField txtID;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtIdentficacion;
	private DefaultListModel<String> modeloLista;
	private JScrollPane scrollPane;
	private JList<String> listaResultados;
	private JTextField txtCine;

	public ModificarEmpleado() {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Modificar informaci\u00F3n de un empleado");
		createFrame();
		setDatos(false);
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modeloLista = new DefaultListModel<String>();
		listaResultados = new JList<String>();
		listaResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cargarDatos();
			}
		});
		listaResultados.setModel(modeloLista);
					
		scrollPane = new JScrollPane(listaResultados);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(444, 42, 319, 125);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);

		JPanel panelDatos = new JPanel();
		panelDatos.setBounds(64, 74, 680, 208);
		contentPane.add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel label = new JLabel("ID:");
		label.setBounds(31, 21, 18, 20);
		panelDatos.add(label);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(129, 18, 169, 26);
		panelDatos.add(txtID);
		txtID.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtID.setColumns(10);
		
		JLabel label_1 = new JLabel("Nombres:");
		label_1.setBounds(31, 67, 66, 20);
		panelDatos.add(label_1);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtNombres = new JTextField();
		txtNombres.setBounds(129, 64, 169, 26);
		panelDatos.add(txtNombres);
		txtNombres.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNombres.setColumns(10);
		
		JLabel label_2 = new JLabel("Apellidos:");
		label_2.setBounds(31, 116, 67, 20);
		panelDatos.add(label_2);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(129, 113, 169, 26);
		panelDatos.add(txtApellidos);
		txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtApellidos.setColumns(10);
		
		JLabel label_3 = new JLabel("Identificaci\u00F3n:");
		label_3.setBounds(357, 18, 94, 20);
		panelDatos.add(label_3);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtIdentficacion = new JTextField();
		txtIdentficacion.setBounds(455, 18, 169, 26);
		panelDatos.add(txtIdentficacion);
		txtIdentficacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtIdentficacion.setColumns(10);
		
		JLabel label_4 = new JLabel("Cine:");
		label_4.setBounds(357, 65, 33, 20);
		panelDatos.add(label_4);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actualizarEmpleado();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(270, 168, 85, 29);
		panelDatos.add(btnAceptar);
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(365, 168, 85, 29);
		panelDatos.add(btnSalir);
		btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtCine = new JTextField();
		txtCine.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtCine.setColumns(10);
		txtCine.setBounds(455, 64, 169, 26);
		txtCine.setEnabled(false);
		panelDatos.add(txtCine);
		
		JLabel lblDigiteElCdigo = new JLabel("Digite el c\u00F3digo, nombre, apellido o identificaci\u00F3n del empleado:");
		lblDigiteElCdigo.setBounds(12, 13, 401, 25);
		contentPane.add(lblDigiteElCdigo);
		lblDigiteElCdigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		txtBuscaEmpleado = new JTextField();
		txtBuscaEmpleado.setBounds(444, 11, 319, 29);
		contentPane.add(txtBuscaEmpleado);
		txtBuscaEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					autoCompletar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtBuscaEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBuscaEmpleado.setColumns(10);
		
		
		
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//Habilita o deshabilita todos los datos para el empleado
	private void setDatos(boolean estado) {
		this.txtNombres.setEnabled(estado);
		this.txtApellidos.setEnabled(estado);
		this.txtIdentficacion.setEnabled(estado);
	}
	
	//Autocompleta el textfield de empleado
	private void autoCompletar() throws SQLException {
		if(Funciones.validarVacio(txtBuscaEmpleado.getText())) {
			scrollPane.setVisible(false);
		}
		else {
			scrollPane.setVisible(true);
			modeloLista.removeAllElements();
			if(!Funciones.validarVacio(this.txtBuscaEmpleado.getText())) {
				ResultSet resultado = null;
				try {
					//System.out.println(this.txtBuscaEmpleado.getText());
					int valor = Integer.parseInt(this.txtBuscaEmpleado.getText());
					//System.out.print("Es un entero");
					resultado = FachadaEmpleado.getInstance().autoCompletarEmpleado(valor);
					
				}
				catch(Exception e) {
					//System.out.println("Es un string");
					resultado = FachadaEmpleado.getInstance().autoCompletarEmpleado(this.txtBuscaEmpleado.getText());
				}
				while(resultado.next()) {
					modeloLista.addElement(resultado.getObject(1).toString());
					//System.out.println(resultado.getObject(1).toString());
				}
				listaResultados.setModel(modeloLista);
			}
		}
		
	}
	
	private void cargarDatos(){
		int id;
		String seleccionado = listaResultados.getSelectedValue();
		id = Integer.parseInt(seleccionado.split(" -")[0]);
		scrollPane.setVisible(false);
		try {
			ResultSet resultado = FachadaEmpleado.getInstance().consultarEmpleadoByID(id);
			resultado.next();
			//se cargan los datos en los campos
			this.txtID.setText(String.valueOf(id));
			this.txtNombres.setText(resultado.getString("nombres"));
			this.txtApellidos.setText(resultado.getString("apellidos"));
			this.txtIdentficacion.setText(resultado.getString("identificacion"));
			this.txtCine.setText(resultado.getString("cine"));
			setDatos(true);
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	
	private void actualizarEmpleado() throws SQLException {
		if(validarVacio()) {
			JOptionPane.showMessageDialog(null,"Error, digite la totalidad de los datos");
		}
		else {
			Empleado empleado = new Empleado();
			empleado.setIdEmpleado(Integer.parseInt(txtID.getText()));
			empleado.setNombresEmpleado(txtNombres.getText());
			empleado.setApellidosEmpleados(txtApellidos.getText());
			empleado.setIdentificacionEmpl(Integer.parseInt(txtIdentficacion.getText()));
			try {
				FachadaEmpleado.getInstance().actualizarEmpleado(empleado);
				JOptionPane.showMessageDialog(null,"¡EMPLEADO ACTUALIZADO CON ÉXITO!");
				this.dispose();
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(null,"Error, la identificación ingresada ya está registrada con otro empleado");
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	private boolean validarVacio() {
		if(Funciones.validarVacio(txtNombres.getText()) || Funciones.validarVacio(txtApellidos.getText()) || 
			Funciones.validarVacio(txtIdentficacion.getText())) {
			return true;
		}
		return false;
	}
}
