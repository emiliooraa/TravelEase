package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaReservasCliente extends JFrame {

    public VentanaReservasCliente(Usuario usuario) {
        setTitle("Mis reservas - " + usuario.getNombre());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Reservas del cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        String[] reservasMock = {
            "Reserva 001 - Vuelo AR123 - Confirmada",
            "Reserva 002 - Paquete vuelo+hotel - Pendiente de pago",
            "Reserva 003 - Hotel Centro Madrid - Cancelada"
        };

        JList<String> listaReservas = new JList<>(reservasMock);
        JScrollPane scroll = new JScrollPane(listaReservas);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnVerDetalle = new JButton("Ver detalle");
        JButton btnModificar = new JButton("Modificar ");
        JButton btnCancelar = new JButton("Cancelar ");

        btnVerDetalle.addActionListener(e -> {
            String seleccion = listaReservas.getSelectedValue();
            if (seleccion == null) {
                JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una reserva.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Detalle de la reserva:\n" + seleccion,
                    "Detalle", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            String seleccion = listaReservas.getSelectedValue();
            if (seleccion == null) {
                JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una reserva.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Modificar reserva:\n" + seleccion,
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            String seleccion = listaReservas.getSelectedValue();
            if (seleccion == null) {
                JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una reserva.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Cancelar reserva:\n" + seleccion,
                    "Cancelar", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnModificar);
        panelBotones.add(btnCancelar);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
