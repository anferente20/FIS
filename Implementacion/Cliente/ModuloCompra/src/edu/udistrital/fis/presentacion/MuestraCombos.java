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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.compra.logica.Carrito;
import edu.udistrital.fis.compra.logica.Combo;
import edu.udistrital.fis.compra.persistencia.FachadaCombo;

import edu.udistrital.imagen.Imagen;

public class MuestraCombos extends AbstractFrame {


	private JPanel contentPane;
	private JLayeredPane panelDinamico;
	private JButton btnComprar;
	private JLabel carrito;
	private Carrito compras;
	private String correo;
	
	public MuestraCombos(String correo) {
		this.correo = correo;
		compras = new Carrito();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				panelDinamico.removeAll();
				cargarCombos();
			}
		});
		createFrame();
		setIdentificador();
		cargarCombos();
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	private void createFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 693, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		btnComprar = new JButton("Ver");
		btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnComprar.setBounds(340, 400, 100, 30);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar();
				}}
		);
		contentPane.add(btnComprar);
		
		JLabel lblCarrito = new JLabel ("Objetos carrito: ");
		lblCarrito.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCarrito.setBounds(10, 400, 100, 30);
		contentPane.add(lblCarrito);
		
		carrito = new JLabel("0");
		carrito.setFont(new Font("Segoe UI", Font.BOLD, 13));
		carrito.setBounds(120, 400, 100, 30);
		contentPane.add(carrito);
		
		setTitle("Combos");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}

	
	private void cargarCombos() {
		try {
			ResultSet combos = FachadaCombo.getInstance().consultarCombos();
			if(!combos.next()) Funciones.mensajePantalla("No hay ningun combo registrado en el momento");
			else
			{
				int x = 0;
				agregarCombo(combos, x);
				while(combos.next()) {
					x += 280;
					agregarCombo(combos, x);
				}
				panelDinamico.setPreferredSize(new Dimension(575,x+280));
			}
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase GestionCombos: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
	}
	
	private void agregarCombo(ResultSet combos,int x) throws SQLException {
		Combo combo = new Combo();
		combo.setIdCombo(combos.getInt("idcombo"));
		combo.setDescripcion(combos.getString("descripcion"));
		combo.setPrecio(combos.getFloat("precio"));
		combo.setImg(new Imagen(combos.getBytes("img")));
		
		PanelCombo pc = new PanelCombo(combo,this);
		pc.setBounds(0, x, 575, 280);
		pc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panelDinamico.add(pc);
	}

	public void actualizar() {
		int car = 0;
		for(int i = 0;i<compras.getCombos().size();i++) {
			car+= compras.getCombos().get(i).getCantidad();
		}
		carrito.setText(String.valueOf(car));
	}
	public Carrito getCompras() {
		return compras;
	}

	public void setCompras(Carrito compras) {
		this.compras = compras;
	}
	
	public void mostrar() {
		MuestraCarrito mc = new MuestraCarrito(compras,this);
		this.setVisible(false);
		mc.setVisible(true);
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Comprar comida";
		
	}

}
