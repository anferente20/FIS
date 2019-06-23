package edu.udistrital.fis.admin.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.admin.utilidades.Cargador;
import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.basicos.persistencia.GestorDB;
import edu.udistrital.fis.core.IEmpleados;
import edu.udistrital.fis.core.IFunciones;
import edu.udistrital.fis.core.IInventario;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JButton btnIngresar;
	private JButton btnSalir;

	private HashMap<String,ArrayList<AbstractFrame>> presentacion;
	
	public Login() {
		createFrame();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("CINE+ Administrativo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsuario.setBounds(83, 34, 65, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblContrasea.setBounds(83, 76, 75, 14);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtUsuario.setBounds(177, 27, 129, 29);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasena = new JTextField();
		txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(177, 69, 129, 29);
		contentPane.add(txtContrasena);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionIngresar();
			}
		});
		btnIngresar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnIngresar.setBounds(80, 132, 98, 42);
		contentPane.add(btnIngresar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accionSalir();
			}
		});
		btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSalir.setBounds(208, 132, 98, 42);
		contentPane.add(btnSalir);
		
		setLocationRelativeTo(null);
	}
	
	private void accionSalir() {
		this.dispose();
	}
	
	private void accionIngresar() {
		String usuario = this.txtUsuario.getText(), pass = this.txtContrasena.getText();
		try {
			ResultSet usu = FachadaCine.getInstance().login(usuario, pass);
			if(!usu.next()) Funciones.mensajePantalla("Error, credenciales no válidas");
			else {
				int tipoAdmin = usu.getInt("idtipousuario");
				int idCine = usu.getInt("idcineencargado");
				cargarComponentes(tipoAdmin,idCine);
				this.dispose();
				MenuAdmin menu = new MenuAdmin(this.presentacion);
				menu.setVisible(true);
			}
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase Login: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operacion");
		}
	}
	
	private void cargarComponentes(int tipoAdmin,int idCine) {
		//Conjuntos de frames agrupados por modulos
		HashMap<String,ArrayList<AbstractFrame>> presentacion = new HashMap<String, ArrayList<AbstractFrame>>(); 
				
		verificarJDBC();
				
		Cargador cc = new Cargador("componentes", ClassLoader.getSystemClassLoader());
		//Modulo empleado
		ArrayList<AbstractFrame> empleado = verificarModuloEmpleado(cc,tipoAdmin,idCine);
		if(empleado!=null) presentacion.put("Empleados",empleado);
		//Modulo inventario
		ArrayList<AbstractFrame> inventario = verificarModuloInventario(cc,tipoAdmin,idCine);
		if(inventario != null) presentacion.put("Inventario",inventario);
		//Módulo funciones
		ArrayList<AbstractFrame> funciones = verificarModuloFunciones(cc,tipoAdmin,idCine);
		if(funciones!=null) presentacion.put("Funciones",funciones);
		this.presentacion = presentacion;
	}
	
	//Verificacion de modulo empleado
		private static ArrayList<AbstractFrame> verificarModuloEmpleado(Cargador cc,int tipoAdmin,int idCine) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IEmpleados.class.getName());
		if(cls!=null) {
			try {
				IEmpleados empleado = (IEmpleados)cls.newInstance();
				Funciones.mensajeConsola("Modulo empleados cargado");
				return empleado.getPresentacion(tipoAdmin,idCine);
			} catch (Exception e) {
				Funciones.mensajeConsola("Error al cargar modulo Empleado: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo empleados no encontrado");
			Funciones.mensajePantalla("Modulo empleados no encontrado");
		}
		return null;
	}
		
	//Verificacion de modulo funciones
		private static ArrayList<AbstractFrame> verificarModuloFunciones(Cargador cc,int tipoAdmin,int idCine) {
			Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IFunciones.class.getName());
			if(cls!=null) {
				try {
					IFunciones funciones = (IFunciones)cls.newInstance();
					Funciones.mensajeConsola("Modulo funciones cargado");
					return funciones.getPresentacion(tipoAdmin,idCine);
				} catch (Exception e) {
					Funciones.mensajeConsola("Error al cargar modulo funciones: "+e.getMessage());
					return null;
				}
			}
			else {
				Funciones.mensajeConsola("Modulo funciones no encontrado");
				Funciones.mensajePantalla("Modulo funciones no encontrado");
			}
			return null;
		}
		
	//Verificacion de modulo inventario
	private static ArrayList<AbstractFrame> verificarModuloInventario(Cargador cc,int tipoAdmin,int idCine) {
		Class cls = cc.cargarUnaClaseDesdeSuDirectorio(IInventario.class.getName());
		if(cls!=null) {
			try {
				IInventario inventario = (IInventario)cls.newInstance();
				Funciones.mensajeConsola("Modulo inventario cargado");
				return inventario.getPresentacion(tipoAdmin,idCine);
			} catch (Exception e) {
				Funciones.mensajeConsola("Error al cargar modulo inventario: "+e.getMessage());
				return null;
			}
		}
		else {
			Funciones.mensajeConsola("Modulo inventario no encontrado");
			Funciones.mensajePantalla("Modulo inventario no encontrado");
		}
		return null;
	}
		
	//Se verifica si el controlador de PostgreSQL está referenciado
	private static void verificarJDBC() {
		try {
			Class.forName("org.postgresql.Driver");
			Funciones.mensajeConsola("JDBC encontrado");
			verificarConexion();
		}
		catch(ClassNotFoundException e) {
			Funciones.mensajePantalla("JBDC faltante");
			System.exit(0);
		}
	}
		
	//Se verifica la conexión con la base de datos
	private static void verificarConexion() {
		try {
			GestorDB.getInstance();
			Funciones.mensajeConsola("Conexion exitosa a la base de datos");
		}
		catch(SQLException e) {
			Funciones.mensajeConsola("Descripción del error al conectarse con la base datos: "+e.getMessage());
			Funciones.mensajePantalla("Error, conexión fallida a la base de datos");
			System.exit(0);
		}
	}
}