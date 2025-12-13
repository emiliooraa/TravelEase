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

    // Asignar asiento 

    public void asignarAsiento(int idReserva, String asiento) {
    String sql = "UPDATE reservas SET asiento = ? WHERE id = ?";

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, asiento);
        ps.setInt(2, idReserva);
        ps.executeUpdate();

        System.out.println("Asiento asignado correctamente.");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    // Aplicar descuento 

    public void aplicarDescuento(int idReserva, double porcentaje) {
    String sql = """
        UPDATE reservas r
        JOIN paquetes p ON r.paquete_id = p.id
        SET r.descuento = ?,
            r.precio_final = p.precio - (p.precio * ? / 100)
        WHERE r.id = ?
    """;

    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDouble(1, porcentaje);
        ps.setDouble(2, porcentaje);
        ps.setInt(3, idReserva);
        ps.executeUpdate();

        System.out.println("Descuento aplicado.");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
