package edu.udistrital.fis.funciones.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.sql.SQLException;

import com.toedter.calendar.JDateChooser;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.logica.FuncionesTiempo;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.funciones.logica.Funcion;
import edu.udistrital.fis.funciones.persistencia.FachadaPelicula;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class ModificarFuncion extends JFrame {

	private JPanel contentPane;
	private JTextField txtHora;
	private JTextField txtCine;
	private JTextField txtMinutos;

	private Funcion funcion;
	private JDateChooser dateChooser;
	private JComboBox<String> cbxSala;
	private int idCine;
	
	private PanelFuncion target;
	
	public ModificarFuncion(Funcion funcion,PanelFuncion target) {
		this.target = target;
		this.funcion = funcion;
		createFrame();
		cargarSalas();
		try {
			this.cbxSala.setSelectedIndex(FachadaPelicula.getInstance().getConsecutivoSala(this.funcion.getIdSala()));
			this.dateChooser.setDate(funcion.getFecha());
			idCine = FachadaCine.getInstance().getIdCine(funcion.getIdSala());
			this.txtCine.setText(FachadaCine.getInstance().getNombreCine(idCine));
			String[] cadena = funcion.getHora().split(":");
			this.txtHora.setText(cadena[0]);
			this.txtMinutos.setText(cadena[1]);
			
			
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ModificarFuncion: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
		
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(100, 22, 176, 25);
		contentPane.add(dateChooser);
		
		txtHora = new JTextField();
		txtHora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtHora.setBounds(100, 66, 47, 25);
		contentPane.add(txtHora);
		txtHora.setColumns(10);
		
		cbxSala = new JComboBox();
		cbxSala.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbxSala.setBounds(100, 110, 47, 25);
		contentPane.add(cbxSala);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFecha.setBounds(33, 22, 46, 25);
		contentPane.add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHora.setBounds(33, 66, 46, 25);
		contentPane.add(lblHora);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSala.setBounds(33, 106, 46, 25);
		contentPane.add(lblSala);
		
		JLabel lblCine = new JLabel("Cine");
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCine.setBounds(33, 152, 46, 25);
		contentPane.add(lblCine);
		
		txtCine = new JTextField();
		txtCine.setEnabled(false);
		txtCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCine.setColumns(10);
		txtCine.setBounds(100, 154, 176, 25);
		contentPane.add(txtCine);
		
		txtMinutos = new JTextField();
		txtMinutos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMinutos.setColumns(10);
		txtMinutos.setBounds(178, 66, 47, 25);
		contentPane.add(txtMinutos);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.setBounds(118, 202, 89, 30);
		contentPane.add(btnAceptar);
		
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Modificar Funci\u00F3n");
		setResizable(false);
		
		setLocationRelativeTo(null);
	}
	
	private void cargarSalas() {
		cbxSala.addItem("");
		for(int i=1;i<=10;i++) cbxSala.addItem(String.valueOf(i));
	}
	
	private void modificar() {
		if(validar()) {
			Funciones.mensajePantalla("Error, verifique los datos");
		}
		else {
			try {
				FuncionesTiempo ft = new FuncionesTiempo();
				this.funcion.setFecha(dateChooser.getDate());
				this.funcion.setIdSala(FachadaPelicula.getInstance().getIdSala(cbxSala.getSelectedIndex(), idCine));
				this.funcion.setHora(ft.timeToInterval(txtHora.getText()+":"+txtMinutos.getText()));
				boolean bandera = FachadaPelicula.getInstance().modificarFuncion(this.funcion);
				if(bandera) {
					Funciones.mensajePantalla("¡FUNCIÓN MODIFICADA CON ÉXITO");
					this.dispose();
					this.target.setModificada(txtHora.getText()+":"+txtMinutos.getText(), cbxSala.getSelectedIndex(), idCine);
					this.target.setModificada(txtHora.getText()+":"+txtMinutos.getText());
				}
				else {
					Funciones.mensajePantalla("Error, las modificaciones hechas en la función hacen que se crucen con otra funcion."
							+ "La modificación no puede hacerse");
				}
			} catch (SQLException e) {
				Funciones.mensajeConsola("Clase ModificarFuncion: "+e.getMessage());
				Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
			}
		}
	}
	
	private boolean validar() {
		if(!Funciones.validarNumerico(txtHora.getText()) || !Funciones.validarNumerico(txtMinutos.getText()) ||
				cbxSala.getSelectedIndex()==0 ||  validarFecha() || validarNumericos() || validarFechasTiempo()) {
			return true;
		}
		return false;
	}
	
	private boolean validarFecha() {
		try {
			FuncionesTiempo ft = new FuncionesTiempo();
			ft.DateToString(dateChooser.getDate());
		}
		catch(NullPointerException npe) {
			return true;
		}
		return false;
	}
	
	//Valida que solo existan caracteres de tipo numerico
	private boolean validarNumericos() {
		if(Funciones.validarNumerico(txtHora.getText())==false || Funciones.validarNumerico(txtMinutos.getText())==false ) {
			return true;
		}
		return false;
	}
		//Valida que los valores de los campos de tiempo estén correctos
	private boolean validarFechasTiempo() {
		try {
	 		boolean validarHoras = !(Integer.parseInt(txtHora.getText())>=0 && Integer.parseInt(txtHora.getText())<=23);
			boolean validarMinutos = !(Integer.parseInt(txtMinutos.getText())>=0 && Integer.parseInt(txtMinutos.getText())<=59);
			if(validarHoras || validarMinutos) {
				return true;
			}
		}
		catch(NumberFormatException nfe) {
			
		}
		return false;
	}
}
