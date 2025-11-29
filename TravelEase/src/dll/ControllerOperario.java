package dll;

import model.Reserva;
import java.util.List;

public class ControllerOperario {

    private ControllerReserva controllerReserva = new ControllerReserva();

    // Ver todas las reservas
    public List<Reserva> verTodasLasReservas() {
        return controllerReserva.obtenerTodasLasReservas();
    }

    // Actualizar estado de una reserva
    public boolean cambiarEstadoReserva(int idReserva, String nuevoEstado) {
        return controllerReserva.actualizarEstado(idReserva, nuevoEstado);
    }
}
