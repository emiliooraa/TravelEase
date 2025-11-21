package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaBuscarHoteles extends JFrame {

    public VentanaBuscarHoteles(Usuario usuario) {
        setTitle("Buscar hoteles - Cliente: " + usuario.getNombre());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Hoteles disponibles (maqueta)", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        String[] hotelesMock = {
            "Hotel Centro - Madrid - 4★ - Habitación doble",
            "Hostel Joven - Barcelona - 2★ - Habitación compartida",
            "Resort Playa - Cancún - 5★ - All inclusive"
        };

        JList<String> listaHoteles = new JList<>(hotelesMock);
        JScrollPane scroll = new JScrollPane(listaHoteles);

        JButton btnSeleccionar = new JButton("Seleccionar hotel");
        btnSeleccionar.addActionListener(e -> {
            String seleccionado = listaHoteles.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un hotel.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Reservar el hotel:\n" + seleccionado,
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnSeleccionar, BorderLayout.SOUTH);
    }
}
