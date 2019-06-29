package edu.udistrital.fis.presentacion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.udistrital.fis.compra.logica.Combo;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelCarrito extends JPanel {

	
	private MuestraCarrito mc;
	private JLabel lblRegistro;
	private int suscripcion;
	private Combo combo;
	public PanelCarrito(Combo combo,int idCompra,MuestraCarrito mc,int suscripcion) {		
		setLayout(null);
		this.suscripcion=suscripcion;
		this.mc = mc;
		this.combo= combo;
		
		JLabel lblIdCompra = new JLabel("Id Compra");
		lblIdCompra.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblIdCompra.setBounds(10, 10, 60, 20);
		add(lblIdCompra);
		
		lblRegistro = new JLabel(String.valueOf(idCompra+1));
		lblRegistro.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setBounds(10, 40, 60, 50);
		add(lblRegistro);
		
		JLabel lblIdCombo = new JLabel("Id Combo");
		lblIdCombo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblIdCombo.setBounds(80, 10, 60, 20);
		add(lblIdCombo);
		
		JLabel lblCombo = new JLabel(String.valueOf(combo.getIdCombo()));
		lblCombo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCombo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCombo.setBounds(80, 40, 60, 50);
		add(lblCombo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblDescripcion.setBounds(150, 10, 150, 20);
		add(lblDescripcion);
		
		JLabel lblDesc = new JLabel(combo.getDescripcion());
		lblDesc.setFont(new Font("Segoe UI", Font.BOLD, 8));
		lblDesc.setBounds(150, 40, 150, 50);
		add(lblDesc);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPrecio.setBounds(310, 10, 60, 20);
		add(lblPrecio);
		
		JLabel lblPr = new JLabel(String.valueOf(combo.getPrecio()));
		lblPr.setHorizontalAlignment(SwingConstants.CENTER);
		lblPr.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPr.setBounds(310, 40, 60, 50);
		add(lblPr);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRemover.setBounds(480, 40, 89, 23);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
				}}
		);
		add(btnRemover);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblCantidad.setBounds(380, 10, 60, 20);
		add(lblCantidad);
		
		JLabel lblCan = new JLabel(String.valueOf(combo.getCantidad()));
		lblCan.setHorizontalAlignment(SwingConstants.CENTER);
		lblCan.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCan.setBounds(380, 40, 60, 50);
		add(lblCan);
		
		this.mc.getTotal().setText(String.valueOf(this.mc.getTot()+calcularPrecio()));
	}
	
	private void remover() {
		this.mc.remover(Integer.valueOf(lblRegistro.getText()));
//		this.mc.actualizar();
	}
	public float calcularPrecio() {
		float subtotal = 0;
		switch (this.suscripcion){
			case 1:
				subtotal = (combo.getCantidad()*combo.getPrecio());
				break;
			case 2:
				subtotal = (float) ((combo.getCantidad()*combo.getPrecio())*0.95);
				break;
			case 3:
				subtotal = (float) ((combo.getCantidad()*combo.getPrecio())*0.9);
				break;
		}
		return subtotal;
	}
}
