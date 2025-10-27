package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.Usuario;
import javax.swing.JLabel;

public class HomeUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void run(Usuario usuario) {
	    EventQueue.invokeLater(() -> {
	        try {
	            HomeUsuario frame = new HomeUsuario(usuario);
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}


	/**
	 * Create the frame.
	 * @param usuario 
	 */
	public HomeUsuario(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hola, " + usuario.getNombre() + "!");
		lblNewLabel.setBounds(71, 40, 291, 68);
		contentPane.add(lblNewLabel);

	}

}
