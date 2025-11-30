package dll;

import java.sql.*;
import javax.swing.JOptionPane;

public class ControllerVentaHotel {

    private static Connection con = Conexion.getInstance().getConnection();

    public static boolean registrarVenta(int idUsuario, int idHotel, 
        Date fechaEntrada, Date fechaSalida, int noches) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO venta_hotel (id_usuario, id_hotel, fecha_entrada, fecha_salida, noches) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idHotel);
            stmt.setDate(3, fechaEntrada);
            stmt.setDate(4, fechaSalida);
            stmt.setInt(5, noches);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar venta de hotel: " + e.getMessage());
            return false;
        }
    }
}
