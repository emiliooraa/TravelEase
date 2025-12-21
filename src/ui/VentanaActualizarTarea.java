package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaActualizarTarea extends JFrame {

    public VentanaActualizarTarea(Usuario usuario) {
        setTitle("Actualizar estado de tarea");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lbl = new JLabel("Actualizar estado de tarea", SwingConstants.CENTER);

        String[] tareasMock = { 
            "Revisar documentación", 
            "Preparar informes", 
            "Control de stock" 
        };

        JComboBox<String> comboTareas = new JComboBox<>(tareasMock);

        String[] estados = { "Pendiente", "En proceso", "Completada" };
        JComboBox<String> comboEstado = new JComboBox<>(estados);

        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Tarea: " + comboTareas.getSelectedItem() +
                    "\nNuevo estado: " + comboEstado.getSelectedItem() +
                    "\n(Implementación pendiente)");
        });

        add(lbl);
        add(comboTareas);
        add(comboEstado);
        add(btnGuardar);
    }
}
