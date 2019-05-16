package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;

public class CustomFrame extends JFrame{
	protected CustomFrame() {
		createFrame();
	}
	private void createFrame() {
		this.setContentPane(new JPanel());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		setResizable(false);
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}
}
	
