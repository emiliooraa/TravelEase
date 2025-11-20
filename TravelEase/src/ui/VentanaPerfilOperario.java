package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaPerfilOperario extends JFrame {

    public VentanaPerfilOperario(Usuario usuario) {
        setTitle("Perfil del Operario");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblNombre = new JLabel("Nombre: " + usuario.getNombre(), SwingConstants.CENTER);
        JLabel lblEmail = new JLabel("Email: " + usuario.getEmail(), SwingConstants.CENTER);
        JLabel lblTipo = new JLabel("Rol: Operario", SwingConstants.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        add(lblNombre);
        add(lblEmail);
        add(lblTipo);
        add(btnCerrar);
    }
}
