package edu.udistrital.fis.basicos.presentacion;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProgressBar extends JFrame{
	
	private JPanel contentPane;
	JProgressBar progressB;
	private int x;
	private int progreso;
	JLabel lblText;
	
	public ProgressBar(int cantidadOperaciones,String titulo,String txtInicial) {
		this.setTitle(titulo);
		this.x = 0;
		this.progreso = 100/cantidadOperaciones;
		lblText = new JLabel(txtInicial);
		createFrame();
		setVisible(true);
	}
	
	public void cargarBarra(String texto) {
		this.x = x+progreso;
		this.progressB.setValue(x);
		this.lblText.setText(texto);
		if(this.progressB.getValue()==100) {
			dispose();
		}
	}
	
	private void createFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 417, 133);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		progressB = new JProgressBar();
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		progressB.setBounds(26,53, 349, 30);
		contentPane.add(progressB);
		
		
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblText.setBounds(49, 11, 303, 20);
		contentPane.add(lblText);
	}

}

