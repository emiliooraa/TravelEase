package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class ClienteMenu {

    public static void mostrar(Usuario usuario) {
        String[] opciones = {"Ver Paquetes", "Mis Reservas", "Salir"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Menú Cliente - " + usuario.getNombre(),
                    "Panel Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Funcionalidad: Ver Paquetes Disponibles");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Funcionalidad: Ver Mis Reservas");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Saliendo del panel Cliente...");
                    break;
                default:
                    break;
            }
        } while (opcion != 2);
    }
}
