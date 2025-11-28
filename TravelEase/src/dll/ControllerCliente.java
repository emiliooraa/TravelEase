package bll;

import model.Reserva;
import java.util.List;

public class ControllerCliente {

    private ControllerReserva controllerReserva = new ControllerReserva();

    // Crear una reserva
    public void hacerReserva(int clienteId, String destino, String fecha) {
        controllerReserva.crearReserva(clienteId, destino, fecha);
    }

    // Ver reservas propias
    public List<Reserva> verMisReservas(int clienteId) {
        return controllerReserva.obtenerReservasDeCliente(clienteId);
    }
}
