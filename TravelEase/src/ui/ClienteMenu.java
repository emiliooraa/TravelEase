package ui;

import javax.swing.JOptionPane;

import bll.Usuario;
import bll.Reserva;
import bll.GestorReservas;

import java.util.List;

public class ClienteMenu {

    public static void mostrar(Usuario usuario) {
        String[] opciones = {
                "Buscar destinos",
                "Hacer nueva reserva",
                "Cancelar reserva",
                "Modificar reserva",
                "Ver historial de reservas",
                "Salir"
        };

        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Cliente: " + usuario.getNombre() + "\nSeleccione una opción:",
                    "Menú de Cliente - TravelEase",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    buscarDestinos(usuario);
                    break;
                case 1:
                    hacerNuevaReserva(usuario);
                    break;
                case 2:
                    cancelarReserva(usuario);
                    break;
                case 3:
                    modificarReserva(usuario);
                    break;
                case 4:
                    verHistorial(usuario);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Cerrando sesión de cliente...");
                    break;
                default:
                    // Cierre de ventana o cancelar
                    opcion = 5;
                    break;
            }
        } while (opcion != 5);
    }

    // 🔍 Buscar destinos (por ahora algo simple)
    private static void buscarDestinos(Usuario usuario) {
        String mensaje = "Destinos disponibles:\n"
                + "- Madrid (Vuelo + Hotel)\n"
                + "- Roma (Vuelo)\n"
                + "- París (Vuelo + Hotel + Paquete completo)\n"
                + "- Río de Janeiro (Paquete completo)\n\n"
                + "Funcionalidad ampliable más adelante (filtros, precios, etc.).";
        JOptionPane.showMessageDialog(null, mensaje, "Buscar destinos", JOptionPane.INFORMATION_MESSAGE);
    }

    // ✈ Hacer nueva reserva
    private static void hacerNuevaReserva(Usuario usuario) {
        String[] tipos = {"Vuelo", "Hotel", "Paquete", "Cancelar"};
        int tipoSeleccionado = JOptionPane.showOptionDialog(
                null,
                "Seleccione el tipo de reserva:",
                "Nueva reserva",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        if (tipoSeleccionado == -1 || tipoSeleccionado == 3) {
            return; // Canceló
        }

        String tipo = tipos[tipoSeleccionado];

        String destino = JOptionPane.showInputDialog(null, "Ingrese el destino:");
        if (destino == null || destino.isBlank()) {
            JOptionPane.showMessageDialog(null, "Destino inválido. Operación cancelada.");
            return;
        }

        String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha (ej: 10/12/2025):");
        if (fecha == null || fecha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Fecha inválida. Operación cancelada.");
            return;
        }

        Reserva reserva = new Reserva(usuario, tipo, destino, fecha);
        GestorReservas.agregarReserva(reserva);

        JOptionPane.showMessageDialog(null,
                "Reserva creada con éxito.\n" + reserva.toString(),
                "Reserva creada",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // ❌ Cancelar reserva
    private static void cancelarReserva(Usuario usuario) {
        List<Reserva> reservasUsuario = GestorReservas.obtenerReservasDe(usuario);

        if (reservasUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenés reservas para cancelar.");
            return;
        }

        StringBuilder sb = new StringBuilder("Tus reservas:\n");
        for (Reserva r : reservasUsuario) {
            sb.append(r.getId()).append(" - ").append(r.getTipo())
              .append(" - ").append(r.getDestino())
              .append(" (").append(r.getEstado()).append(")\n");
        }

        String input = JOptionPane.showInputDialog(null,
                sb.toString() + "\nIngrese el ID de la reserva a cancelar:");
        if (input == null) {
            return;
        }

        try {
            int id = Integer.parseInt(input);
            boolean ok = GestorReservas.cancelarReserva(id, usuario);
            if (ok) {
                JOptionPane.showMessageDialog(null, "Reserva cancelada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una reserva activa con ese ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    // ✏ Modificar reserva (cambiar destino y fecha)
    private static void modificarReserva(Usuario usuario) {
        List<Reserva> reservasUsuario = GestorReservas.obtenerReservasDe(usuario);

        if (reservasUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenés reservas para modificar.");
            return;
        }

        StringBuilder sb = new StringBuilder("Tus reservas:\n");
        for (Reserva r : reservasUsuario) {
            sb.append(r.getId()).append(" - ").append(r.getTipo())
              .append(" - ").append(r.getDestino())
              .append(" - ").append(r.getFecha())
              .append(" (").append(r.getEstado()).append(")\n");
        }

        String input = JOptionPane.showInputDialog(null,
                sb.toString() + "\nIngrese el ID de la reserva a modificar:");
        if (input == null) {
            return;
        }

        try {
            int id = Integer.parseInt(input);
            Reserva r = GestorReservas.buscarPorIdYUsuario(id, usuario);
            if (r == null) {
                JOptionPane.showMessageDialog(null, "No se encontró una reserva con ese ID.");
                return;
            }

            if (!"Activa".equalsIgnoreCase(r.getEstado())) {
                JOptionPane.showMessageDialog(null, "Solo se pueden modificar reservas ACTIVAS.");
                return;
            }

            String nuevoDestino = JOptionPane.showInputDialog(null,
                    "Destino actual: " + r.getDestino() + "\nNuevo destino:");
            if (nuevoDestino == null || nuevoDestino.isBlank()) {
                JOptionPane.showMessageDialog(null, "Destino inválido. No se modificó.");
                return;
            }

            String nuevaFecha = JOptionPane.showInputDialog(null,
                    "Fecha actual: " + r.getFecha() + "\nNueva fecha:");
            if (nuevaFecha == null || nuevaFecha.isBlank()) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. No se modificó.");
                return;
            }

            r.setDestino(nuevoDestino);
            r.setFecha(nuevaFecha);

            JOptionPane.showMessageDialog(null,
                    "Reserva modificada:\n" + r.toString(),
                    "Reserva modificada",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    // 📜 Ver historial de reservas
    private static void verHistorial(Usuario usuario) {
        List<Reserva> reservasUsuario = GestorReservas.obtenerReservasDe(usuario);

        if (reservasUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenés reservas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("Historial de reservas:\n\n");
        for (Reserva r : reservasUsuario) {
            sb.append(r.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null,
                sb.toString(),
                "Historial de reservas",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

