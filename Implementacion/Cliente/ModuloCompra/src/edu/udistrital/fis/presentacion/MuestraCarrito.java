package edu.udistrital.fis.presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.basicos.persistencia.FachadaCine;
import edu.udistrital.fis.compra.logica.Carrito;
import edu.udistrital.fis.compra.logica.Combo;
import edu.udistrital.fis.compra.persistencia.FachadaCombo;
import edu.udistrital.fis.compra.persistencia.FachadaCompra;
import edu.udistrital.imagen.Imagen;

public class MuestraCarrito extends JFrame{

	private JPanel contentPane;
	private JLayeredPane panelDinamico;
	private JButton btnComprar;
	private Carrito compras;
	private JButton btnVolver;
	private MuestraCombos mc;
	private JLabel total;
	private int Tot=0;
	private int compra = 0;
	private int cliente = 0;
	
	public int getTot() {
		return Tot;
	}

	public void setTot(int tot) {
		Tot = tot;
	}

	public MuestraCarrito(Carrito carrito,MuestraCombos mc) {
		compras =carrito;
		this.mc=mc;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				panelDinamico.removeAll();
				cargarCombos();
			}
		});
		createFrame();
		cargarCombos();
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 693, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Calendar fec = new GregorianCalendar();
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblFecha.setBounds(40, 10, 100, 30);
		contentPane.add(lblFecha);
		
		JLabel fecha = new JLabel(fec.get(Calendar.DAY_OF_MONTH)+"/"+(fec.get(Calendar.MONTH)+1)+"/"+fec.get(Calendar.YEAR));
		fecha.setFont(new Font("Segoe UI", Font.BOLD, 13));
		fecha.setBounds(150, 10, 100, 30);
		contentPane.add(fecha);
		
		panelDinamico = new JLayeredPane();
		panelDinamico.setBounds(33, 148, 575, 226);
		panelDinamico.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(34, 32, 606, 350);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(panelDinamico);
		scrollPane.getViewport().setView(panelDinamico);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnComprar.setBounds(250, 400, 100, 30);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comprar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}}
		);
		contentPane.add(btnComprar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnVolver.setBounds(40, 400, 100, 30);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
				}}
		);
		contentPane.add(btnVolver);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTotal.setBounds(380, 400, 100, 30);
		contentPane.add(lblTotal);
		
		total = new JLabel(String.valueOf(Tot));
		total.setFont(new Font("Segoe UI", Font.BOLD, 13));
		total.setBounds(490, 400, 100, 30);
		contentPane.add(total);
		
		setTitle("Carrito de compras");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}

	
	public JLabel getTotal() {
		return total;
	}

	public void setTotal(JLabel total) {
		this.total = total;
	}

	
	public void remover(int dato) {
		this.compras.getCombos().remove(dato);
		volver();
		this.mc.actualizar();
		this.mc.mostrar();
	}
	private void cargarCombos() {
		try {
			int x = 0;
			for(int i = 0;i<compras.getCombos().size();i++) {
				Combo c = compras.getCombos().get(i);
				agregarCombo(c, x,i);
				x+=110;
				panelDinamico.setPreferredSize(new Dimension(480,x+110));
			}
			
			
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase GestionCombos: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
	}
	
	private void agregarCombo(Combo combo,int x,int idCompra) throws SQLException {
		int suscripcion = FachadaCine.getInstance().obtenerSuscripcion(mc.getCorreo());
		
		PanelCarrito pc = new PanelCarrito(combo,idCompra,this,suscripcion);
		pc.setBounds(0, x, 580, 100);
		pc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panelDinamico.add(pc);
	}


	public void comprar() throws SQLException {
		compra = ((int)((Math.random()*100000)+1));
		cliente = FachadaCine.getInstance().obtenerIdcliente(mc.getCorreo());
		int suscripcion = FachadaCine.getInstance().obtenerSuscripcion(mc.getCorreo());
		double des = 1;
		switch (suscripcion) {
		case 2:
			des = 0.95;
			break;
		case 3:
			des = 0.9;
			break;
		}
		FachadaCompra.getInstance().insertarCompra(Tot, cliente,compra);
		
		for(int i = 0;i<compras.getCombos().size();i++) {
			FachadaCompra.getInstance().registrarComboCompra(compra, compras.getCombos().get(i).getCantidad(),
					compras.getCombos().get(i).getIdCombo(),compras.getCombos().get(i).getCantidad()*compras.getCombos().get(i).getPrecio()*des);
		}
		this.dispose();
		mc.dispose();
	}
	public void volver() {
		this.dispose();
		mc.setVisible(true);
	}
	public Carrito getCompras() {
		return compras;
	}

	public void setCompras(Carrito compras) {
		this.compras = compras;
	}

}
