package dll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerGerente {

    private static Connection con = Conexion.getInstance().getConnection();

    // =========================
    // REPORTE DE VENTAS
    // =========================
    public static ArrayList<String> reporteVentas(LocalDate desde, LocalDate hasta) {
        ArrayList<String> lista = new ArrayList<>();

        String sql =
            "SELECT 'HOTEL' AS tipo, h.nombre AS item, vh.noches AS cantidad, " +
            "       (vh.noches * p.precio) AS total " +
            "FROM venta_hotel vh " +
            "JOIN hotel h ON h.id = vh.id_hotel " +
            "JOIN precio_item p ON p.tipo='HOTEL' AND p.ref_id=h.id " +
            "WHERE DATE(vh.fecha_venta) BETWEEN ? AND ? " +

            "UNION ALL " +

            "SELECT 'VUELO' AS tipo, CONCAT(v.origen,' -> ',v.destino) AS item, vv.cantidad AS cantidad, " +
            "       (vv.cantidad * p2.precio) AS total " +
            "FROM venta_vuelo vv " +
            "JOIN vuelo v ON v.id_vuelo = vv.id_vuelo " +
            "JOIN precio_item p2 ON p2.tipo='VUELO' AND p2.ref_id=v.id_vuelo " +
            "WHERE DATE(vv.fecha_venta) BETWEEN ? AND ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(desde));
            ps.setDate(2, Date.valueOf(hasta));
            ps.setDate(3, Date.valueOf(desde));
            ps.setDate(4, Date.valueOf(hasta));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(
                    rs.getString("tipo") + " | " +
                    rs.getString("item") + " | Cantidad: " +
                    rs.getInt("cantidad") + " | Total: $" +
                    rs.getDouble("total")
                );
            }
        } catch (Exception e) {
            System.err.println("Error reporteVentas: " + e.getMessage());
        }

        return lista;
    }

    // =========================
    // COMPARAR PRECIOS
    // =========================
    public static double obtenerPrecio(String tipo, int refId) {
        String sql = "SELECT precio FROM precio_item WHERE tipo=? AND ref_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipo);
            ps.setInt(2, refId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("precio");
        } catch (Exception e) {
            System.err.println("Error obtenerPrecio: " + e.getMessage());
        }
        return 0;
    }
}
