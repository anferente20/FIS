package edu.udistrital.fis.admin.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import edu.udistrital.fis.basicos.presentacion.AbstractFrame;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdmin extends JFrame {

	private JPanel contentPane;
	private HashMap<String,ArrayList<AbstractFrame>> presentacion;
	private int i;
	
	public MenuAdmin(String usuario, String contrasena, String tipoUsuario,HashMap<String,ArrayList<AbstractFrame>> presentacion) {
		this.presentacion = presentacion;
		createFrame();
	}
	
	private void createFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("MEN\u00DA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		contentPane.add(menuBar);
		
		//Iterar sobre los elementos del HashMap
		Iterator<Map.Entry<String, ArrayList<AbstractFrame>>> it = this.presentacion.entrySet().iterator();
		while (it.hasNext()) {
		    Entry<String, ArrayList<AbstractFrame>> e = it.next();
		    JMenu menu = new JMenu(e.getKey().toString());
		    ArrayList<AbstractFrame> array = (ArrayList<AbstractFrame>)e.getValue();
		    for(i=0;i<array.size();i++) {
		    	JMenuItem menuI = new JMenuItem(array.get(i).getIdentificador());
		    	JFrame frame = array.get(i);
		    	menuI.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
					}
				});
		    	menu.add(menuI);
		    }
		    menuBar.add(menu);
		    //System.out.println(e.getKey() + " " + e.getValue());
		}
		setLocationRelativeTo(null);
	}
}