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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		getContentPane().setLayout(null);

		JLabel lbl = new JLabel("Hola, " + usuario.getNombre() + " (" + usuario.getRol() + ")");
		lbl.setBounds(0, 0, 504, 188);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lbl);

		JPanel bottom = new JPanel();
		bottom.setBounds(0, 174, 504, 47);
		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.setForeground(Color.RED);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.addActionListener(e -> {
			dispose();
			Inicio.main(new String[]{});
		});
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bottom.add(btnMenu);
		bottom.add(btnLogout);
		getContentPane().add(bottom);
	}
}

