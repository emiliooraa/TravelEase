package dll;

import model.Reserva;
import java.util.List;

public class ControllerManager {

    private ControllerReserva controllerReserva = new ControllerReserva();

    // Ver todas las reservas
    public List<Reserva> verTodasLasReservas() {
        return controllerReserva.obtenerTodasLasReservas();
    }

    // Obtener reporte general
    public String verReporteReservas() {
        return controllerReserva.generarReporte();
    }
}
