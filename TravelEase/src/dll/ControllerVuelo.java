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
                "INSERT INTO vuelo (codigo, origen, destino, fecha_salida, fecha_llegada, aerolinea) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, codigo);
            stmt.setString(2, origen);
            stmt.setString(3, destino);
            stmt.setTimestamp(4, Timestamp.valueOf(salida));
            stmt.setTimestamp(5, Timestamp.valueOf(llegada));
            stmt.setString(6, aerolinea);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
        	System.err.println("Error al crear vuelo: " + e.getMessage());
            return false;
        }
    }


    // Editar vuelo
    public static boolean editarVuelo(int id, String codigo, String origen, String destino,
                                      LocalDateTime salida, LocalDateTime llegada,
                                      String aerolinea) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE vuelo SET codigo=?, origen=?, destino=?, fecha_salida=?, fecha_llegada=?, aerolinea=? WHERE id_vuelo=?"
            );

            stmt.setString(1, codigo);
            stmt.setString(2, origen);
            stmt.setString(3, destino);
            stmt.setTimestamp(4, Timestamp.valueOf(salida));
            stmt.setTimestamp(5, Timestamp.valueOf(llegada));
            stmt.setString(6, aerolinea);
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
        	System.err.println("No se pudo editar: " + e.getMessage());
            return false;
        }
    }


    // Eliminar
    public static boolean eliminarVuelo(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM vuelo WHERE id_vuelo=?");
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error al eliminar vuelo: " + e.getMessage());
            return false;
        }
    }


    // Buscar uno
    public static Vuelo buscarVueloPorId(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelo WHERE id_vuelo=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("codigo"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getTimestamp("fecha_salida").toLocalDateTime(),
                    rs.getTimestamp("fecha_llegada").toLocalDateTime(),
                    rs.getString("aerolinea")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }


    // Listar
    public static LinkedList<Vuelo> listarVuelos() {
        LinkedList<Vuelo> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelo");
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

        } catch (Exception e) { e.printStackTrace(); }

        return lista;
    }
}
