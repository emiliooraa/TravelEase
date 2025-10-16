package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class ClienteMenu {
    public static void mostrar(Usuario usuario) {
        String[] opciones = { "Ver reservas", "Hacer nueva reserva", "Modificar perfil", "Salir" };
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "👩‍💼 Cliente: " + usuario.getNombre() + "\nSeleccione una acción:",
                    "Menú de Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "🧾 Mostrando reservas del cliente (en desarrollo)");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "✈️ Nueva reserva (en desarrollo)");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "⚙️ Modificación de perfil (en desarrollo)");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "👋 Cerrando sesión de Cliente...");
                    break;
            }
        } while (opcion != 3 && opcion != JOptionPane.CLOSED_OPTION);
    }
}
