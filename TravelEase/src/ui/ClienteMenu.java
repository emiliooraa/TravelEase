package ui;

import java.util.List;

import javax.swing.JOptionPane;

import bll.GestorReservas;
import bll.Hotel;
import bll.Paquete;
import bll.Reserva;
import bll.Usuario;
import bll.Vuelo;

public class ClienteMenu {

    public static void mostrar(Usuario usuario) {

        String[] opciones = {
                "Buscar destinos / vuelos / hoteles / paquetes",  
                "Realizar reserva",                               
                "Cancelar reserva",                            
                "Modificar reserva",                            
                "Ver historial de reservas",                   
                "Salir"
        };

        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(
                    null,
                    "Cliente: " + usuario.getNombre() + "\nSeleccione una opcion:",
                    "Menu de Cliente - TravelEase",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0:
                    buscarDestinos();
                    break;
                case 1:
                    realizarReserva(usuario);
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
                    JOptionPane.showMessageDialog(null, "Cerrando sesion de cliente...");
                    break;
                default:
                    opcion = 5; 
                    break;
            }
        } while (opcion != 5);
    }


    private static void buscarDestinos() {
        String mensaje = "Destinos disponibles (ejemplo):\n"
                + "- Madrid (vuelo + hotel)\n"
                + "- Roma (solo vuelo)\n"
                + "- Paris (vuelo + hotel + paquete)\n"
                + "- Rio de Janeiro (paquete completo)\n\n"
                + "Mas adelante podes reemplazar esto por tus ventanas\n"
                + "reales de busqueda de vuelos, hoteles y paquetes.";
        JOptionPane.showMessageDialog(null, mensaje, "Buscar destinos", JOptionPane.INFORMATION_MESSAGE);
    }

   
    private static void realizarReserva(Usuario usuario) {

        String[] tipos = {"Vuelo", "Hotel", "Paquete", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(
                null,
                "Que tipo de reserva queres hacer?",
                "Nueva reserva",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        if (opcion == -1 || opcion == 3) {
            return; 
        }

        Vuelo vuelo = null;
        Hotel hotel = null;
        Paquete paquete = null;

       
        switch (opcion) {
            case 0:
                JOptionPane.showMessageDialog(null,
                        "Acá iria la logica para seleccionar un VUELO.\n" +
                        "Por ahora la reserva se crea sin vuelo asociado.");
                break;
            case 1:
                JOptionPane.showMessageDialog(null,
                        "Acá iria la logica para seleccionar un HOTEL.\n" +
                        "Por ahora la reserva se crea sin hotel asociado.");
                break;
            case 2:
                JOptionPane.showMessageDialog(null,
                        "Acá iria la logica para seleccionar un PAQUETE.\n" +
                        "Por ahora la reserva se crea sin paquete asociado.");
                break;
        }

        Reserva reserva = new Reserva(usuario, vuelo, hotel, paquete, false);
        GestorReservas.agregarReserva(reserva);

        JOptionPane.showMessageDialog(
                null,
                "Reserva creada con exito.\nID: " + reserva.getId(),
                "Reserva creada",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

   
    private static void cancelarReserva(Usuario usuario) {

        List<Reserva> reservasCliente = GestorReservas.obtenerReservasDe(usuario);

        if (reservasCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenes reservas para cancelar.");
            return;
        }

        StringBuilder sb = new StringBuilder("Tus reservas:\n");
        for (Reserva r : reservasCliente) {
            sb.append("ID: ").append(r.getId())
              .append(" | Cancelada: ").append(r.isCancelada())
              .append("\n");
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
                JOptionPane.showMessageDialog(null, "No se encontro una reserva activa con ese ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID invalido.");
        }
    }

    private static void modificarReserva(Usuario usuario) {

        List<Reserva> reservasCliente = GestorReservas.obtenerReservasDe(usuario);

        if (reservasCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenes reservas para modificar.");
            return;
        }

        StringBuilder sb = new StringBuilder("Tus reservas:\n");
        for (Reserva r : reservasCliente) {
            sb.append("ID: ").append(r.getId())
              .append(" | Cancelada: ").append(r.isCancelada())
              .append("\n");
        }

        String input = JOptionPane.showInputDialog(null,
                sb.toString() + "\nIngrese el ID de la reserva a modificar:");

        if (input == null) {
            return;
        }

        try {
            int id = Integer.parseInt(input);
            Reserva r = GestorReservas.buscarPorIdYCliente(id, usuario);

            if (r == null) {
                JOptionPane.showMessageDialog(null, "No se encontro una reserva con ese ID.");
                return;
            }

            String[] opciones = {"Marcar como cancelada", "Marcar como activa", "Salir"};
            int op = JOptionPane.showOptionDialog(
                    null,
                    "Estado actual: " + (r.isCancelada() ? "Cancelada" : "Activa"),
                    "Modificar reserva",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (op == 0) {
                r.setCancelada(true);
            } else if (op == 1) {
                r.setCancelada(false);
            }

            JOptionPane.showMessageDialog(null,
                    "Reserva modificada.\nEstado ahora: " +
                    (r.isCancelada() ? "Cancelada" : "Activa"));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID invalido.");
        }
    }

  
    private static void verHistorial(Usuario usuario) {

        List<Reserva> reservasCliente = GestorReservas.obtenerReservasDe(usuario);

        if (reservasCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenes reservas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("Historial de reservas:\n\n");
        for (Reserva r : reservasCliente) {
            sb.append("ID: ").append(r.getId())
              .append(" | Cliente: ").append(r.getCliente().getNombre())
              .append(" | Cancelada: ").append(r.isCancelada())
              .append("\n");
        }

        JOptionPane.showMessageDialog(
                null,
                sb.toString(),
                "Historial de reservas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
