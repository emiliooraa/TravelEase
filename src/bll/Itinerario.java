package bll;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Itinerario {
    private LinkedList<Reserva> reservas;

    public Itinerario() {
        this.reservas = new LinkedList<>();
    }

    public LinkedList<Reserva> getReservas() {
        return reservas;

    }
    // Metodos 
    // Agregar reserva
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    // Eliminar reserva
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    // Mostrar reservas
    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay reservas en el itinerario.");
        } else {
            for (Reserva r : reservas) {
                JOptionPane.showMessageDialog(null, r);
                ;
            }
        }
    }

}
