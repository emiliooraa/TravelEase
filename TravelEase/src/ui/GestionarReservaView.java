package ui;

import java.awt.EventQueue;
import bll.Usuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GestionarReservaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	
	public GestionarReservaView(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		//Sigue en proceso
	}

}
