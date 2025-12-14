package dll;

import java.sql.*;
import java.util.LinkedList;

public class ControllerReserva {

    private static Connection con = Conexion.getInstance().getConnection();

    //RESERVAS VUELO
    public static LinkedList<Object[]> listarReservasVuelo() {

        LinkedList<Object[]> lista = new LinkedList<>();

        String sql =
            "SELECT vv.id, u.nombre, " +
            "CONCAT(v.origen, ' → ', v.destino), " +
            "vv.fecha_venta, vv.cantidad " +
            "FROM venta_vuelo vv " +
            "JOIN usuario u ON u.id = vv.id_usuario " +
            "JOIN vuelo v ON v.id_vuelo = vv.id_vuelo";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getTimestamp(4),
                    rs.getInt(5)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static void eliminarReservaVuelo(int id) {
        try (PreparedStatement ps =
             con.prepareStatement("DELETE FROM venta_vuelo WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //RESERVAS HOTEL
    public static LinkedList<Object[]> listarReservasHotel() {

        LinkedList<Object[]> lista = new LinkedList<>();

        String sql =
            "SELECT vh.id, u.nombre, " +
            "CONCAT(h.ciudad, ' - ', h.pais), " +
            "vh.fecha_entrada, vh.fecha_salida, vh.noches " +
            "FROM venta_hotel vh " +
            "JOIN usuario u ON u.id = vh.id_usuario " +
            "JOIN hotel h ON h.id = vh.id_hotel";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getDate(5),
                    rs.getInt(6)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static void eliminarReservaHotel(int id) {
        try (PreparedStatement ps =
             con.prepareStatement("DELETE FROM venta_hotel WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
