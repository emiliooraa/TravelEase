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

public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AdminMenu(Usuario usuario) {
		setTitle("Panel de Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(197, 221, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + usuario.getNombre());
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 17));
		lblNewLabel.setBounds(47, 24, 434, 52);
		contentPane.add(lblNewLabel);
		
		//Gestionar Usuarios
		JButton btnGestionar = new JButton("Gestionar Usuarios");
		btnGestionar.setBackground(new Color(240, 255, 255));
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionarUsuariosView(usuario).setVisible(true);
				dispose();
			}
		});
		btnGestionar.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionar.setBounds(47, 110, 202, 52);
		contentPane.add(btnGestionar);
		
		//Gestionar Paquetes de viajes
		JButton btnGestionarPaquetes = new JButton("Gestionar Paquetes");
		btnGestionarPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarPaquetes.setBackground(new Color(240, 255, 255));
		btnGestionarPaquetes.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarPaquetes.setBounds(279, 110, 202, 52);
		contentPane.add(btnGestionarPaquetes);
		
		
		
		//Gestionar Reservas
		JButton btnGestionarReservas = new JButton("Gestionar Reservas");
		btnGestionarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarReservas.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarReservas.setBackground(new Color(240, 255, 255));
		btnGestionarReservas.setBounds(47, 183, 202, 52);
		contentPane.add(btnGestionarReservas);
		
		//Ver Reportes
		JButton btnVerReportes = new JButton("Ver Reportes");
		btnVerReportes.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnVerReportes.setBackground(new Color(240, 255, 255));
		btnVerReportes.setBounds(279, 183, 202, 52);
		contentPane.add(btnVerReportes);
		
		//Boton para cerrar sesion
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
		btnCerrarSesion.setBounds(163, 280, 202, 37);
		contentPane.add(btnCerrarSesion);
	}
}
