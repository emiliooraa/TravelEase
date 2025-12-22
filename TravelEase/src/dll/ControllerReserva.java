package dll;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import bll.Reserva;
import bll.Usuario;
import bll.GestorReservas.Reserva;

public class ControllerReserva {

    private static Connection con = Conexion.getInstance().getConnection();

    // Reserva solo vuelo
    public static boolean reservarVuelo(int idUsuario, int idVuelo) {
        if (idUsuario <= 0 || idVuelo <= 0) {
            System.err.println("Usuario o vuelo inválido.");
            return false;
        }
        String sql = "INSERT INTO reserva (id_usuario, tipo, id_vuelo, fecha_reserva, estado) " +
                     "VALUES (?, 'VUELO', ?, NOW(), 'PENDIENTE')";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVuelo);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error al reservar vuelo: " + e.getMessage());
            return false;
        }
    }

    // Reserva solo hotel
    public static boolean reservarHotel(int idUsuario, int idHotel) {
        if (idUsuario <= 0 || idHotel <= 0) {
            System.err.println("Usuario o hotel inválido.");
            return false;
        }
        String sql = "INSERT INTO reserva (id_usuario, tipo, id_hotel, fecha_reserva, estado) " +
                     "VALUES (?, 'HOTEL', ?, NOW(), 'PENDIENTE')";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idHotel);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error al reservar hotel: " + e.getMessage());
            return false;
        }
    }

    // Reserva paquete (vuelo + hotel)
    public static boolean reservarPaquete(int idUsuario, int idPaquete) {
        if (idUsuario <= 0 || idPaquete <= 0) {
            System.err.println("Usuario o paquete inválido.");
            return false;
        }
        String sql = "INSERT INTO reserva (id_usuario, tipo, id_paquete, fecha_reserva, estado) " +
                     "VALUES (?, 'PAQUETE', ?, NOW(), 'PENDIENTE')";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idPaquete);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error al reservar paquete: " + e.getMessage());
            return false;
        }
    }

    // Listar reservas por idUsuario
    public static List<Reserva> obtenerReservasDeCliente(int idUsuario) {
        List<Reserva> lista = new LinkedList<>();

        String sql = "SELECT * FROM reserva WHERE id_usuario = ? ORDER BY fecha_reserva DESC";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idReserva   = rs.getInt("id_reserva");
                String tipo     = rs.getString("tipo");
                int idUser      = rs.getInt("id_usuario");
                String estado   = rs.getString("estado");
                Timestamp fechaReserva = rs.getTimestamp("fecha_reserva");

                Usuario cliente = ControllerUsuario.buscarUsuarioPorId(idUser);
                if (cliente == null) {
                    continue;
                }

                Reserva r = new Reserva();
                r.setIdReserva(idReserva);
                r.setCliente(cliente);
                r.setTipo(tipo);
                r.setEstado(estado);
                r.setFechaReserva(fechaReserva);
                r.setCancelada("CANCELADA".equalsIgnoreCase(estado));

                // Estos deben ir a sus propios campos, NO a idReserva
                r.setIdVuelo(rs.getInt("id_vuelo"));
                r.setIdHotel(rs.getInt("id_hotel"));
                r.setIdPaquete(rs.getInt("id_paquete"));

                lista.add(r);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener reservas: " + e.getMessage());
        }

        return lista;
    }

    public static List<Reserva> obtenerReservasDeCliente(Usuario usuario) {
        if (usuario == null) {
            return new LinkedList<>();
        }
        return obtenerReservasDeCliente(usuario.getId());
    }

    // Cancelar reserva
    public static boolean cancelarReserva(int idReserva) {
        String sql = "UPDATE reserva SET estado = 'CANCELADA' " +
                     "WHERE id_reserva = ? AND estado != 'CANCELADA'";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (Exception e) {
            System.err.println("Error al cancelar reserva: " + e.getMessage());
            return false;
        }
    }
}
