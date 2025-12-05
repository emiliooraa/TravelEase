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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AdminMenu(Usuario usuario) {
		setTitle("Panel de Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 515);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Panel de Admin - Hola " + usuario.getNombre() + "!");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 625, 52);
		contentPane.add(lblNewLabel);
		
		//Boton para cerrar sesion
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setBackground(new Color(220, 20, 60));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio.main(new String[]{});
			}
		});
		btnCerrarSesion.setFont(new Font("Gadugi", Font.PLAIN, 15));
		btnCerrarSesion.setBounds(221, 428, 202, 37);
		contentPane.add(btnCerrarSesion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(10, 76, 625, 341);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnGestionarHoteles = new JButton("Gestionar Hoteles");
		btnGestionarHoteles.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/hotel.png")));
		btnGestionarHoteles.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarHoteles.setBackground(new Color(240, 255, 255));
		btnGestionarHoteles.setBounds(44, 197, 202, 52);
		panel.add(btnGestionarHoteles);
		
		JButton btnGestionarDestinos = new JButton("Gestionar Destinos");
		btnGestionarDestinos.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/destino.png")));
		btnGestionarDestinos.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarDestinos.setBackground(new Color(240, 255, 255));
		btnGestionarDestinos.setBounds(382, 197, 202, 52);
		panel.add(btnGestionarDestinos);
		
		
		
		//Gestionar Reservas
		JButton btnGestionarReservas = new JButton("Gestionar Reservas");
		btnGestionarReservas.setBounds(44, 116, 202, 52);
		panel.add(btnGestionarReservas);
		btnGestionarReservas.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/gestionarReserva.png")));
		btnGestionarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarReservas.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarReservas.setBackground(new Color(240, 255, 255));
		
		//Gestionar Usuarios
		JButton btnGestionar = new JButton("Gestionar Usuarios");
		btnGestionar.setForeground(new Color(0, 0, 0));
		btnGestionar.setBounds(44, 34, 202, 52);
		panel.add(btnGestionar);
		btnGestionar.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/gestionarUsuario.png")));
		btnGestionar.setBackground(new Color(240, 255, 255));
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionarUsuariosView(usuario).setVisible(true);
				dispose();
			}
		});
		btnGestionar.setFont(new Font("Gadugi", Font.PLAIN, 14));
		
		//Gestionar Paquetes de viajes
		JButton btnGestionarVuelos = new JButton("Gestionar Vuelos");
		btnGestionarVuelos.setBounds(382, 34, 202, 52);
		panel.add(btnGestionarVuelos);
		btnGestionarVuelos.setToolTipText("Hi, estoy probando");
		btnGestionarVuelos.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/vuelo.png")));
		btnGestionarVuelos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new GestionarVuelosView(usuario).setVisible(true);
		        dispose();
		    }
		});
		btnGestionarVuelos.setBackground(new Color(240, 255, 255));
		btnGestionarVuelos.setFont(new Font("Gadugi", Font.PLAIN, 14));
		
		//Ver Reportes
		JButton btnGestionarPaquetes = new JButton("Gestionar Paquetes");
		btnGestionarPaquetes.setBounds(382, 116, 202, 52);
		panel.add(btnGestionarPaquetes);
		btnGestionarPaquetes.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/gestionarPaquete.png")));
		btnGestionarPaquetes.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarPaquetes.setBackground(new Color(240, 255, 255));
		
		JButton btnVerReportes = new JButton("Ver Reportes");
		btnVerReportes.setIcon(new ImageIcon(AdminMenu.class.getResource("/img/reporte.png")));
		btnVerReportes.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnVerReportes.setBackground(new Color(240, 255, 255));
		btnVerReportes.setBounds(208, 278, 202, 52);
		panel.add(btnVerReportes);
	}
}
