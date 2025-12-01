package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Timestamp;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Vuelo;

public class ControllerVuelo {

    private static Connection con = Conexion.getInstance().getConnection();

    // Crear vuelo
    public static boolean crearVuelo(String codigo, String origen, String destino,
                                     java.time.LocalDateTime fechaSalida,
                                     java.time.LocalDateTime fechaLlegada,
                                     String aerolinea) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO vuelos (codigo, origen, destino, fecha_salida, fecha_llegada, aerolinea) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, codigo);
            stmt.setString(2, origen);
            stmt.setString(3, destino);
            stmt.setTimestamp(4, Timestamp.valueOf(fechaSalida));
            stmt.setTimestamp(5, Timestamp.valueOf(fechaLlegada));
            stmt.setString(6, aerolinea);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear vuelo: " + e.getMessage());
            return false;
        }
    }

    // Editar vuelo
    public static boolean editarVuelo(int id, String codigo, String origen, String destino,
                                      java.time.LocalDateTime fechaSalida,
                                      java.time.LocalDateTime fechaLlegada,
                                      String aerolinea) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE vuelos SET codigo=?, origen=?, destino=?, fecha_salida=?, fecha_llegada=?, aerolinea=? WHERE id_vuelo=?"
            );

            stmt.setString(1, codigo);
            stmt.setString(2, origen);
            stmt.setString(3, destino);
            stmt.setTimestamp(4, Timestamp.valueOf(fechaSalida));
            stmt.setTimestamp(5, Timestamp.valueOf(fechaLlegada));
            stmt.setString(6, aerolinea);
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar vuelo: " + e.getMessage());
            return false;
        }
    }

    // Eliminar vuelo
    public static boolean eliminarVuelo(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM vuelos WHERE id_vuelo=?"
            );
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar vuelo: " + e.getMessage());
            return false;
        }
    }

    // Buscar vuelo
    public static Vuelo buscarVueloPorId(int id) {
        Vuelo v = null;
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM vuelos WHERE id_vuelo=?"
            );
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("codigo"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getTimestamp("fecha_salida").toLocalDateTime(),
                    rs.getTimestamp("fecha_llegada").toLocalDateTime(),
                    rs.getString("aerolinea")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    // Listar vuelos
    public static LinkedList<Vuelo> listarVuelos() {
        LinkedList<Vuelo> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("codigo"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getTimestamp("fecha_salida").toLocalDateTime(),
                    rs.getTimestamp("fecha_llegada").toLocalDateTime(),
                    rs.getString("aerolinea")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
