package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bll.Usuario;

public class HomeUsuario extends JFrame {

	private static final long serialVersionUID = 1L;

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

	public HomeUsuario(Usuario usuario) {
		setTitle("Home - TravelEase");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 260);
		setLayout(new BorderLayout());

		JLabel lbl = new JLabel("Hola, " + usuario.getNombre() + " (" + usuario.getRol() + ")");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lbl, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.addActionListener(e -> {
			dispose();
			Inicio.main(new String[]{});
		});
		bottom.add(btnLogout);
		add(bottom, BorderLayout.SOUTH);
	}
}

