package dll;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Vuelo;

public class ControllerVuelo {

    private static Connection con = Conexion.getInstance().getConnection();

    // Crear vuelo
    public static boolean crearVuelo(String codigo, String origen, String destino,
                                     LocalDateTime salida, LocalDateTime llegada,
                                     String aerolinea) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO vuelos (codigo, origen, destino, aerolinea, fecha_salida, fecha_llegada) "
                + "VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, codigo);
            stmt.setString(2, origen);
            stmt.setString(3, destino);
            stmt.setString(4, aerolinea);
            stmt.setTimestamp(5, Timestamp.valueOf(salida));
            stmt.setTimestamp(6, Timestamp.valueOf(llegada));

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear vuelo: " + e.getMessage());
            return false;
        }
    }

    // Editar vuelo
    public static boolean editarVuelo(int id, String codigo, String origen, String destino,
                                      String aerolinea, LocalDateTime salida, LocalDateTime llegada) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE vuelos SET codigo=?, origen=?, destino=?, aerolinea=?, fecha_salida=?, fecha_llegada=? WHERE id=?"
            );

            stmt.setString(1, codigo);
            stmt.setString(2, origen);
            stmt.setString(3, destino);
            stmt.setString(4, aerolinea);
            stmt.setTimestamp(5, Timestamp.valueOf(salida));
            stmt.setTimestamp(6, Timestamp.valueOf(llegada));
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo editar vuelo: " + e.getMessage());
            return false;
        }
    }

    // Eliminar
    public static boolean eliminarVuelo(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM vuelos WHERE id_vuelo=?");
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar vuelo: " + e.getMessage());
            return false;
        }
    }

    // Buscar
    public static Vuelo buscarVueloPorId(int id) {
        Vuelo v = null;

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelos WHERE id_vuelo=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("codigo"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getString("aerolinea"),
                    rs.getTimestamp("fecha_salida").toLocalDateTime(),
                    rs.getTimestamp("fecha_llegada").toLocalDateTime()
                );
            }

        } catch (Exception e) {
            System.out.println("Error buscar vuelo: " + e);
        }

        return v;
    }

    // Listar
    public static LinkedList<Vuelo> listarVuelos() {
        LinkedList<Vuelo> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelos ORDER BY fecha_salida");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("codigo"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getString("aerolinea"),
                    rs.getTimestamp("fecha_salida").toLocalDateTime(),
                    rs.getTimestamp("fecha_llegada").toLocalDateTime()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
