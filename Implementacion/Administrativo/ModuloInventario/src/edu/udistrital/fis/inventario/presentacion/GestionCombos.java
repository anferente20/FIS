package edu.udistrital.fis.inventario.presentacion;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.api.logica.AbstractFrame;
import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.inventario.logica.Combo;
import edu.udistrital.fis.inventario.persistencia.FachadaInventario;
import edu.udistrital.imagen.Imagen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GestionCombos extends AbstractFrame {

	private JPanel contentPane;
	private JLayeredPane panelDinamico;
	private JButton btnInsertarUnNuevo;
	private JButton btnInsertarUnNuevo_1;

	public GestionCombos() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				panelDinamico.removeAll();
				cargarCombos();
			}
		});
		createFrame();
		this.setIdentificador();
		cargarCombos();
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
		
		btnInsertarUnNuevo_1 = new JButton("Insertar un nuevo Combo");
		btnInsertarUnNuevo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				InsertarCombo x = new InsertarCombo();
				x.setVisible(true);
			}
		});
		btnInsertarUnNuevo_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnInsertarUnNuevo_1.setBounds(228, 395, 219, 39);
		contentPane.add(btnInsertarUnNuevo_1);
		
		btnInsertarUnNuevo = new JButton("Insertar un nuevo combo");
		btnInsertarUnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InsertarCombo x = new InsertarCombo();
				x.setVisible(true);
			}
		});
		setLocationRelativeTo(null);
		
		setTitle("Combos");
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}

	@Override
	protected void setIdentificador() {
		this.identificador = "Confitería";		
	}
	
	private void cargarCombos() {
		try {
			ResultSet combos = FachadaInventario.getInstance().consultarCombos();
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
		
		PanelCombo pc = new PanelCombo(combo);
		pc.setBounds(0, x, 575, 280);
		pc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panelDinamico.add(pc);
	}
}
