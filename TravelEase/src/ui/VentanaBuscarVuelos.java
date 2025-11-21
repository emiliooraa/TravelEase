package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaBuscarVuelos extends JFrame {

    public VentanaBuscarVuelos(Usuario usuario) {
        setTitle("Buscar vuelos - Cliente: " + usuario.getNombre());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Vuelos disponibles (maqueta)", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        String[] vuelosMock = {
            "AR123 - Buenos Aires → Madrid - 20/12/2025 - 14:30",
            "LA456 - Buenos Aires → Santiago - 05/01/2026 - 09:15",
            "IB789 - Buenos Aires → Barcelona - 10/02/2026 - 22:00"
        };

        JList<String> listaVuelos = new JList<>(vuelosMock);
        JScrollPane scroll = new JScrollPane(listaVuelos);

        JButton btnSeleccionar = new JButton("Seleccionar vuelo (maqueta)");
        btnSeleccionar.addActionListener(e -> {
            String seleccionado = listaVuelos.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un vuelo.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "[MAQUETA] Reservar el vuelo:\n" + seleccionado,
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnSeleccionar, BorderLayout.SOUTH);
    }
}
