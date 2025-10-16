package ui;

import javax.swing.JOptionPane;

import bll.Usuario;
import dll.Conexion;
import dll.ControllerUsuario;

public class Main {
    public static void main(String[] args) {

        // Inicializar conexión
        Conexion.getInstance();

        String[] opciones = { "Login", "Registrar", "Salir" };
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenid@ a TravelEase ✈️\nSeleccione una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0: // LOGIN
                    Usuario encontrado = Usuario.Login();

                    if (encontrado != null) {
                        JOptionPane.showMessageDialog(null,
                                "✅ Bienvenido/a " + encontrado.getNombre() + " (" + encontrado.getRol() + ")");

                        // Redirigir al menú correspondiente según el rol
                        switch (encontrado.getRol().toLowerCase()) {
                            case "admin":
                                AdminMenu.mostrar(encontrado);
                                break;
                            case "manager":
                                ManagerMenu.mostrar(encontrado);
                                break;
                            case "operario":
                                OperarioMenu.mostrar(encontrado);
                                break;
                            case "cliente":
                                ClienteMenu.mostrar(encontrado);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,
                                        "⚠️ Rol no reconocido. Contacte con el administrador.");
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Credenciales incorrectas o usuario no encontrado.");
                    }
                    break;

                case 1: // REGISTRO
                    Usuario nuevo = Usuario.registrarUsuario();
                    if (nuevo != null) {
                        JOptionPane.showMessageDialog(null, "✅ Usuario registrado correctamente.");
                    }
                    break;

                case 2: // SALIR
                    JOptionPane.showMessageDialog(null, "👋 Gracias por usar TravelEase.");
                    break;

                default:
                    break;
            }

        } while (opcion != 2 && opcion != JOptionPane.CLOSED_OPTION);
    }
}
