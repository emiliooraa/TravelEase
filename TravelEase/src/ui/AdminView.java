package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.Usuario;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	public AdminView(Usuario logueado) {
		setTitle("Panel de Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 398);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + logueado.getNombre());
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 17));
		lblNewLabel.setBounds(47, 24, 434, 52);
		contentPane.add(lblNewLabel);
		
		JButton btnGestionar = new JButton("Gestionar Usuarios ");
		btnGestionar.setBackground(new Color(240, 255, 255));
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionarUsuariosView(logueado).setVisible(true);
				dispose();
			}
		});
		btnGestionar.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionar.setBounds(47, 110, 202, 52);
		contentPane.add(btnGestionar);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBackground(new Color(240, 255, 255));
		btnNewButton_1.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnNewButton_1.setBounds(279, 110, 202, 52);
		contentPane.add(btnNewButton_1);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setBackground(new Color(255, 0, 0));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
            Inicio.main(new String[]{});
			}
		});
		btnCerrarSesion.setFont(new Font("Gadugi", Font.PLAIN, 15));
		btnCerrarSesion.setBounds(163, 268, 202, 37);
		contentPane.add(btnCerrarSesion);
	}
}
