package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class OperarioMenu {

    public static void mostrar(Usuario usuario) {
        String[] opciones = {"Gestionar Reservas", "Ver Itinerarios", "Salir"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Menú Operario - " + usuario.getNombre(),
                    "Panel Operario",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Funcionalidad: Gestionar Reservas");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Funcionalidad: Ver Itinerarios");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Saliendo del panel Operario...");
                    break;
                default:
                    break;
            }
        } while (opcion != 2);
    }
}
