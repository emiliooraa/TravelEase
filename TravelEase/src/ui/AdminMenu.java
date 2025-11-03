package ui;

import javax.swing.JOptionPane;
import bll.Usuario;

public class AdminMenu {

    public static void mostrar(Usuario usuario) {
        int opcion;
        do {
            String[] opciones = {
                "Gestionar Usuarios",
                "Ver Reportes del Sistema",
                "Configuración General",
                "Cerrar Sesión"
                
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
        String[] opciones = {"Agregar Usuario", "Eliminar Usuario","Editar Usuario", "Ver lista de usuarios","Volver"};
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
                agregarUsuario();
                break;
            case 1:
                eliminarUsuario();
                break;
            case 2:
            	editarUsuario();
                break;
            case 3:
            	verListaUsuarios();
            	break;
            case 4:
            	break;
        }
      
    }

    private static void editarUsuario() {
		Usuario.editarUsuario();
		
	}

	private static void eliminarUsuario() {
    	Usuario.eliminarUsuario();
	}

	private static void agregarUsuario() {
		Usuario.registrarUsuario();
	}

	private static void verReportes() {
        JOptionPane.showMessageDialog(null, "📊 Mostrando reportes generales del sistema...");
    }

    private static void configuracionGeneral() {
        JOptionPane.showMessageDialog(null, "⚙️ Accediendo a configuración del sistema...");
    }
    private static void verListaUsuarios() {
		Usuario.verListaUsuarios();
		
	}
}
