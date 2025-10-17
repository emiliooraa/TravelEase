package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class OperarioMenu {
    public static void mostrar(Usuario usuario) {
        String[] opciones = { "Ver tareas asignadas", "Actualizar estado de tarea", "Ver perfil", "Salir" };
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "🔧 Operario: " + usuario.getNombre() + "\nSeleccione una acción:",
                    "Menú de Operario",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "📋 Listado de tareas pendientes (en desarrollo)");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "✅ Actualizar estado de tarea (en desarrollo)");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "👤 Perfil de usuario (en desarrollo)");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "👋 Cerrando sesión de Operario...");
                    break;
            }
        } while (opcion != 3 && opcion != JOptionPane.CLOSED_OPTION);
    }
}
