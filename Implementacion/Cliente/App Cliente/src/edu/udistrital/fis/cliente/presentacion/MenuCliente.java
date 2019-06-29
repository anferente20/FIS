package edu.udistrital.fis.cliente.presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import javax.swing.JButton;

public class MenuCliente extends JFrame {

	private JPanel contentPane;
	private HashMap<String,ArrayList<AbstractFrame>> presentacion;
	private JMenuBar menuBar;
	private int i;
	JButton btnIngresar;
	private JButton btnRegistrar;
	private JLabel usuario;
	private String correo;


	public MenuCliente() {
		this.presentacion = presentacion;
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("MEN\u00DA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuario = new JLabel();
		usuario.setBounds(0, 227, 100, 20);
		usuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
		contentPane.add(usuario);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		contentPane.add(menuBar);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnIngresar.setBounds(335, 227, 89, 23);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ingresar();
			}
		}
		);
		contentPane.add(btnIngresar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRegistrar.setBounds(335, 204, 89, 23);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					registrar();
			}
		}
		);
		contentPane.add(btnRegistrar);
		
		
		
		setLocationRelativeTo(null);
		
	}
	
	private void cerrarSesion() {
		btnIngresar.setVisible(true);
		btnRegistrar.setVisible(true);
		usuario.setText("");
		menuBar.remove(1);
		menuBar.remove(0);
	}
	private void ingresar() {
		IngresoCliente cli = new IngresoCliente(this);			
		cli.setVisible(true);
		btnIngresar.setVisible(false);
		btnRegistrar.setVisible(false);
	}
	
	private void registrar() {
		//Iterar sobre los elementos del HashMap
		Iterator<Map.Entry<String, ArrayList<AbstractFrame>>> it = this.presentacion.entrySet().iterator();
		while (it.hasNext()) {
		    Entry<String, ArrayList<AbstractFrame>> e = it.next();
		    ArrayList<AbstractFrame> array = (ArrayList<AbstractFrame>)e.getValue();
		    JFrame frame = array.get(0);
		    frame.setVisible(true);
		}
	}
	public void cargarFrames() {
		//Iterar sobre los elementos del HashMap
		Iterator<Map.Entry<String, ArrayList<AbstractFrame>>> it = this.presentacion.entrySet().iterator();
		while (it.hasNext()) {
		    Entry<String, ArrayList<AbstractFrame>> e = it.next();
		    JMenu menu = new JMenu(e.getKey().toString());
		    ArrayList<AbstractFrame> array = (ArrayList<AbstractFrame>)e.getValue();
		    for(i=0;i<array.size();i++) {
		    	JMenuItem menuI = new JMenuItem(array.get(i).getIdentificador());
		    	JFrame frame = array.get(i);
		    	menuI.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
					}
				});
		    	menu.add(menuI);
		    }
		    menuBar.add(menu);
		}
		//System.out.println(e.getKey() + " " + e.getValue());
	    JMenu menuSalir = new JMenu("Cerrar Sesion");
		menuSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cerrarSesion();
			}
		});
		menuBar.add(menuSalir);
	}
	public JLabel getUsuario() {
		return usuario;
	}
	public void setUsuario(JLabel usuario) {
		this.usuario = usuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public HashMap<String, ArrayList<AbstractFrame>> getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(HashMap<String, ArrayList<AbstractFrame>> presentacion) {
		this.presentacion = presentacion;
	}
	
	
	
}
