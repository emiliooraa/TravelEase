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

public class OperarioMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public OperarioMenu(Usuario usuario) {
		setTitle("Panel de Operario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
		
		// Registrar Venta
		JButton btnVenta = new JButton("Registrar Venta");
		btnVenta.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/iconVenta.png")));
		btnVenta.setBackground(new Color(240, 255, 255));
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionarVentaView(usuario).setVisible(true);
				dispose();
			}
		});
		btnVenta.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnVenta.setBounds(57, 110, 202, 52);
		contentPane.add(btnVenta);
		
		//Aplicar Descuento
		JButton btnAplicarDescuento = new JButton("Aplicar Descuento");
		btnAplicarDescuento.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/vuelo.png")));
		btnAplicarDescuento.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new AplicarDescuentoView(usuario).setVisible(true);
		        dispose();
		    }
		});
		btnAplicarDescuento.setBackground(new Color(240, 255, 255));
		btnAplicarDescuento.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnAplicarDescuento.setBounds(369, 110, 202, 52);
		contentPane.add(btnAplicarDescuento);
		
		
		
		//Asignar asiento
		JButton btnGestionarReservas = new JButton("Asignar asiento");
		btnGestionarReservas.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/asiento.png")));
		btnGestionarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionarReservas.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarReservas.setBackground(new Color(240, 255, 255));
		btnGestionarReservas.setBounds(57, 197, 202, 52);
		contentPane.add(btnGestionarReservas);
		
		//Gestionar Reserva
		JButton btnGestionarReserva = new JButton("Gestionar Reserva");
		btnGestionarReserva.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        new GestionarReservaOperarioView().setVisible(true);
        dispose();
    }
});

		btnGestionarReserva.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/gestionarReserva.png")));
		btnGestionarReserva.setFont(new Font("Gadugi", Font.PLAIN, 14));
		btnGestionarReserva.setBackground(new Color(240, 255, 255));
		btnGestionarReserva.setBounds(369, 197, 202, 52);
		contentPane.add(btnGestionarReserva);
		
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
		btnCerrarSesion.setBounds(221, 314, 202, 37);
		contentPane.add(btnCerrarSesion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(10, 76, 625, 216);
		contentPane.add(panel);
	}
}
