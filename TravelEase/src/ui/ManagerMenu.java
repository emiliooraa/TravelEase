package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class ManagerMenu {
    public static void mostrar(Usuario usuario) {
        String[] opciones = { "Ver estadísticas", "Asignar tareas", "Consultar reportes", "Salir" };
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "🧑‍💼 Manager: " + usuario.getNombre() + "\nSeleccione una acción:",
                    "Menú de Manager",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "📈 Mostrando estadísticas generales (en desarrollo)");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "🗂 Asignar tareas al equipo (en desarrollo)");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "📋 Consultar reportes (en desarrollo)");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "👋 Cerrando sesión de Manager...");
                    break;
            }
        } while (opcion != 3 && opcion != JOptionPane.CLOSED_OPTION);
    }
}
