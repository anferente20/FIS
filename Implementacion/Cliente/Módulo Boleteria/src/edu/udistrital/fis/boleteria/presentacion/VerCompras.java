package edu.udistrital.fis.boleteria.presentacion;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.boleteria.logica.Compra;
import edu.udistrital.fis.boleteria.persistencia.FachadaBoleteria;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VerCompras extends AbstractFrame {

	private JPanel contentPane;
	private JLayeredPane panelDinamico;


	public VerCompras(int idCliente) {
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				panelDinamico.removeAll();
				cargarCompras(idCliente);
			}
		});
		setTitle("Tus compras");
		setIdentificador();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(37, 29, 653, 450);
		contentPane.add(scrollPane);
		
		panelDinamico = new JLayeredPane();
		panelDinamico.setBounds(0, 0, 635, 500);
		panelDinamico.setLayout(null);
		
		scrollPane.setViewportView(panelDinamico);
		scrollPane.getViewport().setView(panelDinamico);
		
		setLocationRelativeTo(null);
		cargarCompras(idCliente);
	}


	private void cargarCompras(int idCliente) {
		try {
			ResultSet compras = FachadaBoleteria.getInstance().consultarComprasByCliente(idCliente);
			if(!compras.next()) {
				this.dispose();
				Funciones.mensajePantalla("No has realizado ninguna compra de boletas hasta el momento");
				this.dispose();
			}
			else {
				int y=0;
				Compra c = getCompra(compras);
				agregarCompra(c,y);
				while(compras.next()) {
					y += 419;
					Compra compra = getCompra(compras);
					agregarCompra(compra, y);
				}
				panelDinamico.setPreferredSize(new Dimension(635,y+419));
			}
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase VerCompras: "+e.getMessage());
			this.dispose();
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
		
	}


	private void agregarCompra(Compra compra,int y) {
		PanelCompra pc = new PanelCompra(0, y, compra);
		panelDinamico.add(pc);
	}


	@Override
	protected void setIdentificador() {
		this.identificador = "Tus compras";
	}
	
	private Compra getCompra(ResultSet compras) throws SQLException {
		Compra compra = new Compra();
		compra.setIdCompra(compras.getInt("idcompra"));
		compra.setIdCliente(compras.getInt("idcliente"));
		compra.setFechaCompra(compras.getString("fechacompra"));
		compra.setTotal(compras.getFloat("total"));
		compra.setPrecioBoleta(compras.getFloat("precioporboleta"));
		compra.setFechafuncion(compras.getString("fechafuncion"));
		compra.setHorafuncion(compras.getString("horafuncion"));
		compra.setNombrepelicula(compras.getString("nombrepelicula"));
		compra.setSala(compras.getInt("sala"));
		compra.setNombrecine(compras.getString("nombrecine"));
		compra.setImg(compras.getBytes("imagenpelicula"));
		compra.setCantidadBoletas(compras.getInt("cantidadboletas"));
		return compra;
	}

}
