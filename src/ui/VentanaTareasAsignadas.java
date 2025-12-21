package ui;

import javax.swing.*;
import java.awt.*;
import bll.Usuario;

public class VentanaTareasAsignadas extends JFrame {

    public VentanaTareasAsignadas(Usuario usuario) {
        setTitle("Tareas Asignadas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Tareas asignadas a " + usuario.getNombre(), SwingConstants.CENTER);

        String[] tareasMock = {
            "Revisar documentación",
            "Preparar informes",
            "Control de stock",
            "Actualizar planilla"
        };

        JList<String> listaTareas = new JList<>(tareasMock);
        JScrollPane scroll = new JScrollPane(listaTareas);

        add(lbl, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
