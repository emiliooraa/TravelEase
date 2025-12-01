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
import javax.swing.ImageIcon;

public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AdminMenu(Usuario usuario) {
		setTitle("Panel de Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido " + usuario.getNombre());
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 17));
		lblNewLabel.setBounds(105, 24, 434, 52);
		contentPane.add(lblNewLabel);
		
		//Gestionar Usuarios
		JButton btnGestionar = new JButton("Gestionar Usuarios");
		btnGestionar.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/gestionarUsuario.png")));
		btnGestionar.setBackground(new Color(240, 255, 255));
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionarUsuariosView(usuario).setVisible(true);
				dispose();
			}
		});
		btnGestionar.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionar.setBounds(57, 110, 202, 52);
		contentPane.add(btnGestionar);
		
		//Gestionar Paquetes de viajes
		JButton btnGestionarVuelos = new JButton("Gestionar Vuelos");
		btnGestionarVuelos.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/vuelo.png")));
		btnGestionarVuelos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new GestionarVuelosView(usuario).setVisible(true);
		        dispose();
		    }
		});
		btnGestionarVuelos.setBackground(new Color(240, 255, 255));
		btnGestionarVuelos.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarVuelos.setBounds(369, 110, 202, 52);
		contentPane.add(btnGestionarVuelos);
		
		
		
		//Gestionar Reservas
		JButton btnGestionarReservas = new JButton("Gestionar Reservas");
		btnGestionarReservas.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/gestionarReserva.png")));
		btnGestionarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarReservas.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarReservas.setBackground(new Color(240, 255, 255));
		btnGestionarReservas.setBounds(57, 197, 202, 52);
		contentPane.add(btnGestionarReservas);
		
		//Ver Reportes
		JButton btnVerReportes = new JButton("Ver Reportes");
		btnVerReportes.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/reporte.png")));
		btnVerReportes.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnVerReportes.setBackground(new Color(240, 255, 255));
		btnVerReportes.setBounds(369, 197, 202, 52);
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
		btnCerrarSesion.setBounds(221, 314, 202, 37);
		contentPane.add(btnCerrarSesion);
	}
}
