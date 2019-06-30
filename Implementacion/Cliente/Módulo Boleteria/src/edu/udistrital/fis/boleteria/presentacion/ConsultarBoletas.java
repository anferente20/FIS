package edu.udistrital.fis.boleteria.presentacion;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.udistrital.fis.basicos.logica.Funciones;
import edu.udistrital.fis.boleteria.logica.Asiento;
import edu.udistrital.fis.boleteria.persistencia.FachadaBoleteria;

public class ConsultarBoletas extends JFrame {

	private JPanel contentPane;
	private JLayeredPane panelAsientos;


	public ConsultarBoletas(int idCompra) {
		createFrame();
		try {
			cargarAsientos(idCompra);
		} catch (SQLException e) {
			Funciones.mensajeConsola("Clase ConsultarBoletas: "+e.getMessage());
			Funciones.mensajePantalla("Error, no fue posible llevar a cabo la operación");
		}
	}
	
	private void createFrame() {
		setResizable(false);
		setTitle("Tus compras");
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 1026);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		panelAsientos = new JLayeredPane();
		panelAsientos.setBounds(29, 40, 1033, 910);
		panelAsientos.setBorder(BorderFactory.createLineBorder(Color.black,1));
		panelAsientos.setLayout(null);
		contentPane.add(panelAsientos);
		setLocationRelativeTo(null);
		
	}
	
	private void cargarAsientos(int idCompra) throws SQLException {
		ArrayList<Asient> asientos = getAsientosComprados(idCompra);
		int x = 20;
		int y = 20;
		for(int i=0;i<7;i++) {
			x = 20;
			for(int j=0;j<10;j++) {
				JLabel asiento = new JLabel();
				asiento.setBounds(x, y, 80, 104);
				if(verificar(asientos, new Asient(i+1, j+1))) asiento.setIcon(Asiento.sillaOcupada);
				else asiento.setIcon(Asiento.sillaLibre);
				
				panelAsientos.add(asiento);
				x +=100;
			}
			y += 124;
		}
	}

	private ArrayList<Asient> getAsientosComprados(int idCompra) throws SQLException {
		ResultSet asientos = FachadaBoleteria.getInstance().consultarBoletasByCompra(idCompra);
		ArrayList<Asient> asients = new ArrayList<Asient>();
		while(asientos.next()) {
			Asient asient = new Asient(Asiento.letraToNumero(asientos.getString("fila").charAt(0)),asientos.getInt("columna"));
			asients.add(asient);
		}
		return asients;
	}
	
	private boolean verificar(ArrayList<Asient> asientos, Asient asiento) {
		boolean bandera = false;
		for(int i=0;i<asientos.size();i++) {
			Asient a = asientos.get(i);
			if(a.getX()==asiento.getX() && a.getY()==asiento.getY()) bandera = true;
		}
		return bandera;
	}
	

}

class Asient {
	
	private int x;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	private int y;
	
	Asient(int x, int y){
		this.x = x;
		this.y = y;
	}
}