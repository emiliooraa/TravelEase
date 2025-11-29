package bll;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas {

    private static List<Reserva> reservas = new ArrayList<>();

    public static void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public static List<Reserva> obtenerReservasDe(Usuario usuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
      
            if (r.getUsuario() == usuario) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public static Reserva buscarPorIdYUsuario(int id, Usuario usuario) {
        for (Reserva r : reservas) {
            if (r.getId() == id && r.getUsuario() == usuario) {
                return r;
            }
        }
        return null;
    }

    public static boolean cancelarReserva(int id, Usuario usuario) {
        Reserva r = buscarPorIdYUsuario(id, usuario);
        if (r != null && "Activa".equalsIgnoreCase(r.getEstado())) {
            r.setEstado("Cancelada");
            return true;
        }
        return false;
    }
}
