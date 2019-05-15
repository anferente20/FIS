package edu.udistrital.fis.empleado.presentencion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiarAdmin extends JInternalFrame {

	private static CambiarAdmin instance;
	private JTextField txtBuscaEmpleado;
	private JTextField txtBuscaAdmin;
	private JList listaAdmin;
	private JList listaEmpleados;
	
	//singleton
	public static CambiarAdmin getInstance() {
		if(instance==null) {
			instance = new CambiarAdmin();
		}
		return instance;
	}
	
	private  CambiarAdmin() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				setTamano(100, 100, 584, 146);
			}
		});
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				GestionAdmins.isOpen = false;
			}
		});
		setClosable(true);
		createFrame();
	}

	//setBounds(100, 100, 584, 424); -> grande
	//setBounds(100, 100, 584, 146); -> pequeño
	private void createFrame() {
		setTitle("Cambiar Administrador");
		setBounds(100, 100, 584, 146);
		getContentPane().setLayout(null);
		
		JButton Ascender = new JButton("Ascender empleado");
		Ascender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setPanelAscender();
			}
		});
		Ascender.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Ascender.setBounds(60, 51, 164, 35);
		getContentPane().add(Ascender);
		
		JButton btnOtroCine = new JButton("Asignar administrador de otro cine");
		btnOtroCine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPanelOtroAdmin();
			}
		});
		btnOtroCine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnOtroCine.setBounds(245, 51, 251, 35);
		getContentPane().add(btnOtroCine);
		
		JLabel lbldeQueForma = new JLabel("\u00BFDe que forma desea asignar un nuevo administrador?");
		lbldeQueForma.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbldeQueForma.setBounds(114, 13, 340, 25);
		getContentPane().add(lbldeQueForma);
		
		
		
		
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
	}
	
	private void setPanelAscender() {
		setTamano(100, 100, 584, 424);
		JPanel panelAscender = new JPanel();
		panelAscender.setBounds(29, 137, 509, 217);
		getContentPane().add(panelAscender);
		panelAscender.setLayout(null);
		
		JLabel lblSeleccioneElEmpleado = new JLabel("Seleccione el empleado a ascender:");
		lblSeleccioneElEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSeleccioneElEmpleado.setBounds(27, 26, 222, 20);
		panelAscender.add(lblSeleccioneElEmpleado);
		
		txtBuscaEmpleado = new JTextField();
		txtBuscaEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBuscaEmpleado.setColumns(10);
		txtBuscaEmpleado.setBounds(259, 22, 222, 29);
		panelAscender.add(txtBuscaEmpleado);
		
		listaEmpleados = new JList();
		
		JScrollPane scrollPaneAscender = new JScrollPane(listaEmpleados);
		scrollPaneAscender.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAscender.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneAscender.setBounds(259, 50, 222, 83);
		panelAscender.add(scrollPaneAscender);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.setBounds(200, 164, 89, 29);
		panelAscender.add(btnAceptar);
	}
	
	private void setPanelOtroAdmin(){
		setTamano(100, 100, 584, 424);
		JPanel panelOtroAdmin = new JPanel();
		panelOtroAdmin.setBounds(29, 137, 509, 217);
		getContentPane().add(panelOtroAdmin);
		panelOtroAdmin.setLayout(null);
		
		JLabel lblSeleccioneElEmpleado = new JLabel("Seleccione el nuevo administrador:");
		lblSeleccioneElEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSeleccioneElEmpleado.setBounds(27, 26, 222, 20);
		panelOtroAdmin.add(lblSeleccioneElEmpleado);
		
		txtBuscaAdmin = new JTextField();
		txtBuscaAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtBuscaAdmin.setColumns(10);
		txtBuscaAdmin.setBounds(259, 22, 222, 29);
		panelOtroAdmin.add(txtBuscaAdmin);
		
		listaAdmin = new JList();
		
		JScrollPane scrollPaneOtroAdmin = new JScrollPane(listaAdmin);
		scrollPaneOtroAdmin.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneOtroAdmin.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneOtroAdmin.setBounds(259, 50, 222, 83);
		panelOtroAdmin.add(scrollPaneOtroAdmin);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.setBounds(200, 164, 89, 29);
		panelOtroAdmin.add(btnAceptar);
	}
	//cambia el tamaño del internal frame
	private void setTamano(int x, int y,int width,int height) {
		this.setBounds(x, y, width, height);
	}
}
