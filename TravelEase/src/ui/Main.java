package ui;

import javax.swing.JOptionPane;

import bll.Usuario;
import dll.Conexion;
import dll.ControllerUsuario;

public class Main {
    public static void main(String[] args) {

        // Inicializamos la conexión (singleton)
        Conexion.getInstance();

        String[] opciones = { "Login", "Registrar", "Salir" };
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Elija una opción",
                    "Bienvenid@ a TravelEase ✈️",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0: // LOGIN
                    Usuario encontrado = Usuario.login();
                    if (encontrado != null) {
                        JOptionPane.showMessageDialog(null,
                                "Bienvenido/a " + encontrado.getNombre() + " (" + encontrado.getRol() + ")");
                        mostrarMenuPorRol(encontrado);
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales inválidas o usuario no encontrado");
                    }
                    break;

                case 1: // REGISTRAR
                    Usuario.registrarUsuario();
                    break;

                case 2: // SALIR
                    JOptionPane.showMessageDialog(null, "Gracias por usar TravelEase. ¡Hasta pronto!");
                    break;

                default:
                    break;
            }

        } while (opcion != 2);
    }

    /**
     * Muestra el menú correspondiente al rol del usuario logueado
     */
    private static void mostrarMenuPorRol(Usuario usuario) {
        switch (usuario.getRol().toLowerCase()) {
            case "manager":
                ManagerMenu.mostrar(usuario);
                break;
            case "operario":
                OperarioMenu.mostrar(usuario);
                break;
            case "cliente":
                ClienteMenu.mostrar(usuario);
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Rol desconocido: " + usuario.getRol() + ". Contacte con soporte.");
        }
    }
}
