package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class ManagerMenu extends JFrame {

    private Usuario usuario;

    public ManagerMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Manager");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Manager: " + usuario.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnVerTareas = new JButton("Ver todas las tareas");
        JButton btnAsignarTarea = new JButton("Asignar tarea a operario");
        JButton btnVerOperarios = new JButton("Ver operarios");
        JButton btnReportes = new JButton("Ver reportes");
        JButton btnCerrar = new JButton("Cerrar sesión");

        add(lblTitulo);
        add(btnVerTareas);
        add(btnAsignarTarea);
        add(btnVerOperarios);
        add(btnReportes);
        add(btnCerrar);

    
        btnVerTareas.addActionListener(e ->
            JOptionPane.showMessageDialog(this, " Lista completa de tareas (en desarrollo)")
        );

        btnAsignarTarea.addActionListener(e ->
            JOptionPane.showMessageDialog(this, " Asignación de tareas a operarios (en desarrollo)")
        );

        btnVerOperarios.addActionListener(e ->
            JOptionPane.showMessageDialog(this, " Lista de operarios (en desarrollo)")
        );

        btnReportes.addActionListener(e ->
            JOptionPane.showMessageDialog(this, " Reportes generales (en desarrollo)")
        );

        btnCerrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, " Cerrando sesión...");
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }
}
