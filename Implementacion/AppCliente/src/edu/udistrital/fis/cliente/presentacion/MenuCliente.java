package edu.udistrital.fis.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuCliente frame = new MenuCliente();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MenuCliente(HashMap<String,ArrayList<AbstractFrame>> presentacion) {
		this.presentacion = presentacion;
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("MEN\u00DA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
	private void ingresar() {
		//Iterar sobre los elementos del HashMap
		Iterator<Map.Entry<String, ArrayList<AbstractFrame>>> it = this.presentacion.entrySet().iterator();
		while (it.hasNext()) {
		    Entry<String, ArrayList<AbstractFrame>> e = it.next();
		    ArrayList<AbstractFrame> array = (ArrayList<AbstractFrame>)e.getValue();
		    JFrame frame = array.get(0);
		    frame.setVisible(true);
		    cargarFrames();
		    btnIngresar.setVisible(false);
		    btnRegistrar.setVisible(false);
		}
			
	}
	
	private void registrar() {
		//Iterar sobre los elementos del HashMap
		Iterator<Map.Entry<String, ArrayList<AbstractFrame>>> it = this.presentacion.entrySet().iterator();
		while (it.hasNext()) {
		    Entry<String, ArrayList<AbstractFrame>> e = it.next();
		    ArrayList<AbstractFrame> array = (ArrayList<AbstractFrame>)e.getValue();
		    JFrame frame = array.get(1);
		    frame.setVisible(true);
		    cargarFrames();
		}
	}
	private void cargarFrames() {
		//Iterar sobre los elementos del HashMap
		Iterator<Map.Entry<String, ArrayList<AbstractFrame>>> it = this.presentacion.entrySet().iterator();
				while (it.hasNext()) {
				    Entry<String, ArrayList<AbstractFrame>> e = it.next();
				    JMenu menu = new JMenu(e.getKey().toString());
				    ArrayList<AbstractFrame> array = (ArrayList<AbstractFrame>)e.getValue();
				    for(i=2;i<array.size();i++) {
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
				    //System.out.println(e.getKey() + " " + e.getValue());
				}
			}
}
