package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class ManagerMenu {

    public static void mostrar(Usuario usuario) {
        String[] opciones = {"Gestionar Usuarios", "Ver Reportes", "Salir"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Menú Manager - " + usuario.getNombre(),
                    "Panel Manager",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Funcionalidad: Gestionar Usuarios");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Funcionalidad: Ver Reportes de Actividad");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Saliendo del panel Manager...");
                    break;
                default:
                    break;
            }
        } while (opcion != 2);
    }
}
