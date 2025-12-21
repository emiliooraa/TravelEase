package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class ControllerVenta {

    private static Connection con = Conexion.getInstance().getConnection();

    //REGISTRAR VENTA DE VUELO
    public static boolean registrarVentaVuelo(int idUsuario, int idVuelo, int cantidad) {

        try {
            PreparedStatement check = con.prepareStatement("SELECT asientos_disponibles FROM vuelo WHERE id=?");
            check.setInt(1, idVuelo);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "El vuelo no existe.");
                return false;
            }

            int disponibles = rs.getInt("asientos_disponibles");

            if (cantidad > disponibles) {
                JOptionPane.showMessageDialog(null, "No hay suficientes asientos disponibles.");
                return false;
            }

            //Registrar la venta
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO venta_vuelo (id_usuario, id_vuelo, cantidad) VALUES (?, ?, ?)"
            );
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVuelo);
            stmt.setInt(3, cantidad);

            int filas = stmt.executeUpdate();

            //Actualizar asientos
            PreparedStatement update = con.prepareStatement(
                "UPDATE vuelo SET asientos_disponibles = asientos_disponibles - ? WHERE id=?"
            );
            update.setInt(1, cantidad);
            update.setInt(2, idVuelo);
            update.executeUpdate();

            return filas > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar venta de vuelo: " + e.getMessage());
            return false;
        }
    }

    //REGISTRAR VENTA DE HOTEL
    public static boolean registrarVentaHotel(int idUsuario, int idHotel,
                                              LocalDate fechaEntrada, LocalDate fechaSalida,
                                              int noches) {

        try {
            
            PreparedStatement check = con.prepareStatement(
                "SELECT habitaciones_disponibles FROM hotel WHERE id=?"
            );
            check.setInt(1, idHotel);
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "El hotel no existe.");
                return false;
            }

            int disponibles = rs.getInt("habitaciones_disponibles");

            if (disponibles <= 0) {
                JOptionPane.showMessageDialog(null, "No hay habitaciones disponibles.");
                return false;
            }

            // 2) Registrar venta de hotel
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO venta_hotel (id_usuario, id_hotel, fecha_entrada, fecha_salida, noches) " +
                "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idHotel);
            stmt.setDate(3, Date.valueOf(fechaEntrada));
            stmt.setDate(4, Date.valueOf(fechaSalida));
            stmt.setInt(5, noches);

            int filas = stmt.executeUpdate();

            // 3) Actualizar disponibilidad
            PreparedStatement update = con.prepareStatement(
                "UPDATE hotel SET habitaciones_disponibles = habitaciones_disponibles - 1 WHERE id=?"
            );
            update.setInt(1, idHotel);
            update.executeUpdate();

            return filas > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar venta de hotel: " + e.getMessage());
            return false;
        }
    }


    public static boolean aplicarCodigoDescuento(int idVenta, String codigo) {

    String sql =
        "UPDATE venta_vuelo v " +
        "JOIN descuento d ON d.codigo = ? " +
        "SET v.total = v.total - (v.total * d.porcentaje / 100) " +
        "WHERE v.id = ? AND d.activo = 1";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, codigo);
        ps.setInt(2, idVenta);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

}
