package edu.udistrital.fis.boleteria.presentacion;

import javax.swing.JPanel;

import edu.udistrital.fis.boleteria.logica.Compra;
import edu.udistrital.imagen.Imagen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class PanelCompra extends JPanel {
	private JLabel lblImg;
	private JLabel lblPrecioBoleta;
	private JLabel lblTotal;
	private JLabel lblFechaDeLa;
	private JLabel lblFechaFuncion;
	private JLabel lblHoraDeLa;
	private JLabel lblHoraFuncion;
	private JLabel lblNombrePelicula;
	private JLabel lblCine_1;
	private JLabel lblCine;
	private JLabel lbl2;
	private JLabel lblSala;
	private JLabel lblCompraste;
	private JLabel lblCantidadBoletas;
	private JLabel lblCompraHechaEl;
	private JLabel lblFechaCompra;
	
	private Compra compra;

	
	public PanelCompra(int x,int y, Compra compra) {
		this.compra = compra;
		setLayout(null);
		setBounds(x, y, 635, 419);
		
		lblImg = new JLabel("");
		lblImg.setBounds(26, 35, 179, 222);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		add(lblImg);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotal.setBounds(505, 372, 92, 21);
		add(lblTotal);
		
		JLabel lblTotal_1 = new JLabel("Total:");
		lblTotal_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotal_1.setBounds(439, 372, 56, 21);
		add(lblTotal_1);
		
		JLabel lblPrecioPorBoleta = new JLabel("Precio por boleta:");
		lblPrecioPorBoleta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPrecioPorBoleta.setBounds(262, 35, 126, 21);
		add(lblPrecioPorBoleta);
		
		lblPrecioBoleta = new JLabel("Fecha:");
		lblPrecioBoleta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPrecioBoleta.setBounds(430, 35, 162, 21);
		add(lblPrecioBoleta);
		
		lblFechaDeLa = new JLabel("Fecha de la funci\u00F3n:");
		lblFechaDeLa.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFechaDeLa.setBounds(262, 123, 138, 21);
		add(lblFechaDeLa);
		
		lblFechaFuncion = new JLabel("Fecha:");
		lblFechaFuncion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFechaFuncion.setBounds(430, 123, 162, 21);
		add(lblFechaFuncion);
		
		lblHoraDeLa = new JLabel("Hora de la funci\u00F3n:");
		lblHoraDeLa.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblHoraDeLa.setBounds(262, 167, 138, 21);
		add(lblHoraDeLa);
		
		lblHoraFuncion = new JLabel("Fecha:");
		lblHoraFuncion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblHoraFuncion.setBounds(430, 167, 167, 21);
		add(lblHoraFuncion);
		
		lblNombrePelicula = new JLabel("Precio por boleta:");
		lblNombrePelicula.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNombrePelicula.setBounds(26, 327, 213, 21);
		add(lblNombrePelicula);
		
		lblCine_1 = new JLabel("Cine:");
		lblCine_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCine_1.setBounds(262, 79, 138, 21);
		add(lblCine_1);
		
		lblCine = new JLabel("Fecha:");
		lblCine.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCine.setBounds(430, 79, 162, 21);
		add(lblCine);
		
		lbl2 = new JLabel("Sala:");
		lbl2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbl2.setBounds(262, 211, 138, 21);
		add(lbl2);
		
		lblSala = new JLabel("Fecha:");
		lblSala.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSala.setBounds(430, 211, 56, 21);
		add(lblSala);
		
		lblCompraste = new JLabel("Compraste:");
		lblCompraste.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCompraste.setBounds(262, 255, 138, 21);
		add(lblCompraste);
		
		lblCantidadBoletas = new JLabel("Fecha:");
		lblCantidadBoletas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCantidadBoletas.setBounds(430, 255, 167, 21);
		add(lblCantidadBoletas);
		
		lblCompraHechaEl = new JLabel("Compra hecha el:");
		lblCompraHechaEl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCompraHechaEl.setBounds(26, 372, 126, 21);
		add(lblCompraHechaEl);
		
		lblFechaCompra = new JLabel("Precio por boleta:");
		lblFechaCompra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFechaCompra.setBounds(164, 372, 157, 21);
		add(lblFechaCompra);
		
		JButton btnNewButton = new JButton("Ver m\u00E1s");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarBoletas();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBounds(500, 312, 97, 36);
		add(btnNewButton);
		
		cargarCompra();

	}


	private void cargarCompra() {
		Imagen.cargarImagen(lblImg, this.compra.getImg());
		lblNombrePelicula.setText("Película: "+this.compra.getNombrepelicula());
		lblFechaCompra.setText(this.compra.getFechaCompra());
		lblTotal.setText(String.valueOf(this.compra.getTotal()));
		lblPrecioBoleta.setText("$"+this.compra.getPrecioBoleta());
		lblCine.setText(this.compra.getNombrecine());
		lblFechaFuncion.setText(this.compra.getFechafuncion());
		lblHoraFuncion.setText(this.compra.getHorafuncion());
		lblSala.setText(String.valueOf(this.compra.getSala()));
		lblCantidadBoletas.setText(this.compra.getCantidadBoletas()+" boletas");
	}
	
	
	private void consultarBoletas() {
		ConsultarBoletas x = new ConsultarBoletas(this.compra.getIdCompra());
		x.setVisible(true);
	}
}
