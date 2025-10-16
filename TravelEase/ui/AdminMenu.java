package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class AdminMenu {

    public static void mostrar(Usuario usuario) {
        int opcion;
        do {
            String[] opciones = {
                "1️⃣ Gestionar Usuarios",
                "2️⃣ Ver Reportes del Sistema",
                "3️⃣ Configuración General",
                "4️⃣ Cerrar Sesión"
            };

            opcion = JOptionPane.showOptionDialog(
                null,
                "👤 Bienvenido/a, " + usuario.getNombre() + 
                "\n\nSeleccione una opción del panel de administración:",
                "Menú del Administrador - TravelEase ⚙️",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            switch (opcion) {
                case 0:
                    gestionarUsuarios();
                    break;
                case 1:
                    verReportes();
                    break;
                case 2:
                    configuracionGeneral();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente. 👋");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }

        } while (opcion != 3 && opcion != JOptionPane.CLOSED_OPTION);
    }

    private static void gestionarUsuarios() {
        String[] opciones = {"Agregar Usuario", "Eliminar Usuario", "Volver"};
        int opcion = JOptionPane.showOptionDialog(
            null,
            "Seleccione una acción de gestión de usuarios:",
            "Gestión de Usuarios",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        switch (opcion) {
            case 0:
                JOptionPane.showMessageDialog(null, "Función: Agregar Usuario 🧑‍💻");
                // Acá hay que conectar con ControllerUsuario.agregarUsuario
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Función: Eliminar Usuario 🗑️");
                break;
            case 2:
                break;
        }
    }

    private static void verReportes() {
        JOptionPane.showMessageDialog(null, "📊 Mostrando reportes generales del sistema...");
    }

    private static void configuracionGeneral() {
        JOptionPane.showMessageDialog(null, "⚙️ Accediendo a configuración del sistema...");
    }
}
