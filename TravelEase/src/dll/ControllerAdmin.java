package bll;

import model.Reserva;
import java.util.List;

public class ControllerAdmin {

    private ControllerReserva controllerReserva = new ControllerReserva();

    // =============================
    //       GESTIONAR RESERVAS
    // =============================
    public List<Reserva> gestionarReservas() {
        // Devuelve TODAS las reservas
        return controllerReserva.obtenerTodasLasReservas();
    }

    // Cambiar estado (pendiente, aprobada, rechazada)
    public boolean actualizarEstadoReserva(int idReserva, String nuevoEstado) {
        return controllerReserva.actualizarEstado(idReserva, nuevoEstado);
    }

    // Eliminar reserva
    public boolean eliminarReserva(int idReserva) {
        return controllerReserva.eliminarReserva(idReserva);
    }

    // =============================
    //       VER REPORTE
    // =============================
    public String verReporte() {
        return controllerReserva.generarReporte();
    }
}
