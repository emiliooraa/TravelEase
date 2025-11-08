package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.Usuario;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class AdminInterfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AdminInterfaz(Usuario logueado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidoAdmin = new JLabel("Hola " + logueado.getNombre() +", "+ logueado.getRol().toLowerCase());
		lblBienvenidoAdmin.setForeground(Color.BLACK);
		lblBienvenidoAdmin.setBounds(10, 1, 195, 56);
		lblBienvenidoAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidoAdmin.setFont(new Font("Gadugi", Font.PLAIN, 15));
		contentPane.add(lblBienvenidoAdmin);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 776, 470);
		contentPane.add(panel);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.setBackground(new Color(128, 0, 0));
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio.main(new String[]{});
				
			}
		});
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrarSesion.setBounds(655, 11, 131, 39);
		contentPane.add(btnCerrarSesion);

	}
}
