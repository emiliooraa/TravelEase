package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class ClienteMenu extends JFrame {

    private Usuario usuario;

    public ClienteMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Cliente - TravelEase");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Cliente: " + usuario.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnBuscarVuelos = new JButton("Buscar vuelos");
        JButton btnBuscarHoteles = new JButton("Buscar hoteles");
        JButton btnReservarVuelo = new JButton("Reservar vuelo");
        JButton btnReservarPaquete = new JButton("Reservar paquete (vuelo + hotel)");
        JButton btnVariasPersonas = new JButton("Reservar para varias personas");
        JButton btnDescuento = new JButton("Aplicar código de descuento");
        JButton btnVerReservas = new JButton("Ver / modificar / cancelar reservas");
        JButton btnCompartir = new JButton("Compartir itinerario");
        JButton btnCerrar = new JButton("Cerrar sesión");

        add(lblTitulo);
        add(btnBuscarVuelos);
        add(btnBuscarHoteles);
        add(btnReservarVuelo);
        add(btnReservarPaquete);
        add(btnVariasPersonas);
        add(btnDescuento);
        add(btnVerReservas);
        add(btnCompartir);
        add(btnCerrar);

        
        btnBuscarVuelos.addActionListener(e ->
            new VentanaBuscarVuelos(usuario).setVisible(true)
        );

        btnBuscarHoteles.addActionListener(e ->
            new VentanaBuscarHoteles(usuario).setVisible(true)
        );

        btnReservarVuelo.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Reservar vuelo")
        );

        btnReservarPaquete.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Reservarpaquete vuelo+hotel")
        );

        btnVariasPersonas.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Reservar para varias personas")
        );

        btnDescuento.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Aplicar código de descuento")
        );

        btnVerReservas.addActionListener(e ->
            new VentanaReservasCliente(usuario).setVisible(true)
        );

        btnCompartir.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Compartir itinerario")
        );

        btnCerrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cerrando sesión...");
            dispose();
            new Inicio().setVisible(true);
        });
    }
}

