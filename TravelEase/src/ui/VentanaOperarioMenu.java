package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaOperarioMenu extends JFrame {

    private Usuario usuario;

    public VentanaOperarioMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Operario");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Operario: " + usuario.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnVerTareas = new JButton("Ver tareas asignadas");
        JButton btnActualizarEstado = new JButton("Actualizar estado de tarea");
        JButton btnPerfil = new JButton("Ver perfil");
        JButton btnCerrar = new JButton("Cerrar sesión");

        add(lblTitulo);
        add(btnVerTareas);
        add(btnActualizarEstado);
        add(btnPerfil);
        add(btnCerrar);

        // EVENTOS
        btnVerTareas.addActionListener(e -> new VentanaTareasAsignadas(usuario).setVisible(true));
        btnActualizarEstado.addActionListener(e -> new VentanaActualizarTarea(usuario).setVisible(true));
        btnPerfil.addActionListener(e -> new VentanaPerfilOperario(usuario).setVisible(true));
        btnCerrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cerrando sesión...");
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }
}
