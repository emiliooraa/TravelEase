package ui;

import javax.swing.JFrame;
import bll.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ClienteMenu extends JFrame {

    private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Usuario usuario;

    public ClienteMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("TravelEase");
        setSize(660, 495);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
                
        JLabel lblNewLabel = new JLabel("Bienvenido " + usuario.getNombre());
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(25, 25, 112));
        lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 17));
        lblNewLabel.setBackground(Color.RED);
        lblNewLabel.setBounds(132, 24, 380, 52);
        getContentPane().add(lblNewLabel);
        
        JButton btnBuscarVuelo = new JButton("Buscar vuelo");
        btnBuscarVuelo.setBounds(57, 110, 202, 52);
        getContentPane().add(btnBuscarVuelo);
        btnBuscarVuelo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new VentanaBuscarVuelos(usuario).setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnBuscarHotel = new JButton("Buscar hoteles");
        btnBuscarHotel.setBounds(369, 110, 202, 52);
        getContentPane().add(btnBuscarHotel);
        btnBuscarHotel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new VentanaBuscarHoteles(usuario).setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnBuscarPaquete = new JButton("Buscar Paquetes");
        btnBuscarPaquete.setBounds(57, 197, 202, 52);
        getContentPane().add(btnBuscarPaquete);
        btnBuscarPaquete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new VentanaBuscarPaquete(usuario).setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnGestionarReservas = new JButton("Gestionar Reservas");
        btnGestionarReservas.setBounds(369, 197, 202, 52);
        getContentPane().add(btnGestionarReservas);
        btnGestionarReservas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new VentanaReservasCliente(usuario).setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(255, 0, 0));
        btnCerrarSesion.setForeground(new Color(255, 255, 255));
        btnCerrarSesion.setBounds(502, 11, 132, 23);
        btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio.main(new String[]{});
			}
		});
        getContentPane().add(btnCerrarSesion);
    }
}

