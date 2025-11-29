package bll;

import dao.ReservaDAO;
import model.Reserva;
import java.util.List;

public class ControllerReserva {

    private ReservaDAO reservaDAO = new ReservaDAO();

    // Crear reserva (cliente)
    public void crearReserva(int clienteId, String destino, String fecha) {
        Reserva r = new Reserva();
        r.setClienteId(clienteId);
        r.setDestino(destino);
        r.setFecha(fecha);
        r.setEstado("Pendiente");
        reservaDAO.insert(r);
    }

    // Ver reservas por cliente
    public List<Reserva> obtenerReservasDeCliente(int clienteId) {
        return reservaDAO.findByCliente(clienteId);
    }

    // Ver todas las reservas (operario/manager)
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaDAO.findAll();
    }

    // Actualizar estado (operario)
    public boolean actualizarEstado(int idReserva, String nuevoEstado) {
        Reserva r = reservaDAO.findById(idReserva);
        if (r == null) return false;

        r.setEstado(nuevoEstado);
        reservaDAO.update(r);
        return true;
    }

    // Reporte (manager)
    public String generarReporte() {
        int totales = reservaDAO.count();
        int pendientes = reservaDAO.countByStatus("Pendiente");
        int aprobadas = reservaDAO.countByStatus("Aprobada");
        int rechazadas = reservaDAO.countByStatus("Rechazada");

        return  "===== REPORTE DE RESERVAS =====\n" +
                "Total: " + totales + "\n" +
                "Pendientes: " + pendientes + "\n" +
                "Aprobadas: " + aprobadas + "\n" +
                "Rechazadas: " + rechazadas + "\n";
    }

    public boolean eliminarReserva(int id) {
    Reserva r = reservaDAO.findById(id);
    if (r == null) return false;

    reservaDAO.delete(id);
    return true;
}

}
