package ui;

import javax.swing.*;

import bll.Usuario;

import java.awt.*;
import java.awt.event.*;

public class OperarioMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public OperarioMenu(Usuario usuario) {
        setTitle("Travelease - Menú Operario");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Menú Operario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnVentaVuelo = new JButton("Registrar venta de vuelo");
        JButton btnVentaHotel = new JButton("Registrar venta de hotel");
        JButton btnReservarPaquete = new JButton("Reservar paquete para cliente");
        JButton btnAsignarAsiento = new JButton("Asignar asiento");
        JButton btnBuscarReservas = new JButton("Buscar reservas");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnVentaVuelo);
        panel.add(btnVentaHotel);
        panel.add(btnReservarPaquete);
        panel.add(btnAsignarAsiento);
        panel.add(btnBuscarReservas);
        panel.add(btnSalir);

        add(panel, BorderLayout.CENTER);

        // EVENTOS
        btnVentaVuelo.addActionListener(e -> mostrarMensaje("Registrar venta de vuelo (no implementado)") );
        btnVentaHotel.addActionListener(e -> mostrarMensaje("Registrar venta de hotel (no implementado)") );
        btnReservarPaquete.addActionListener(e -> mostrarMensaje("Reservar paquete para cliente (no implementado)") );
        btnAsignarAsiento.addActionListener(e -> mostrarMensaje("Asignar asiento (no implementado)") );
        btnBuscarReservas.addActionListener(e -> mostrarMensaje("Buscar reservas (no implementado)") );

        btnSalir.addActionListener(e -> dispose());
    }

    private void mostrarMensaje(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new OperarioMenu().setVisible(true));
//    }
}
