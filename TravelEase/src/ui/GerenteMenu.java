package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class GerenteMenu extends JFrame {

    private Usuario usuario;

    public GerenteMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Gerente");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Gerente: " + usuario.getNombre(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnVerTareas = new JButton("Ver todas las tareas");
        JButton btnAsignarTarea = new JButton("Asignar tarea a operario");
        JButton btnVerOperarios = new JButton("Ver operarios");

        // 🔹 NUEVAS FUNCIONES REALES DE GERENTE
        JButton btnReporteVentas = new JButton("Reporte de ventas");
        JButton btnEstadisticas = new JButton("Estadísticas");
        JButton btnCompararPrecios = new JButton("Comparar precios");

        JButton btnCerrar = new JButton("Cerrar sesión");

        add(lblTitulo);
        add(btnVerTareas);
        add(btnAsignarTarea);
        add(btnVerOperarios);
        add(btnReporteVentas);
        add(btnEstadisticas);
        add(btnCompararPrecios);
        add(btnCerrar);

      
        btnVerTareas.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Lista completa de tareas\n(en desarrollo)")
        );

        btnAsignarTarea.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Asignación de tareas a operarios\n(en desarrollo)")
        );

        btnVerOperarios.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Lista de operarios\n(en desarrollo)")
        );

        // =========================
        // NUEVAS FUNCIONES CONECTADAS
        // =========================
        btnReporteVentas.addActionListener(e ->
            new ReporteVentasView().setVisible(true)
        );

        btnEstadisticas.addActionListener(e ->
            new EstadisticasView().setVisible(true)
        );

        btnCompararPrecios.addActionListener(e ->
            new CompararPreciosView().setVisible(true)
        );

        // =========================
        // CERRAR SESIÓN (se mantiene)
        // =========================
        btnCerrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cerrando sesión...");
            dispose();
            new Inicio().setVisible(true);
        });
    }
}
