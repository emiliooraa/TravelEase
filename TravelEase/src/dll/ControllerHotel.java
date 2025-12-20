package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Hotel;

public class ControllerHotel {

    private static Connection con = Conexion.getInstance().getConnection();

    // Crear hotel
    public static boolean crearHotel(String nombre, String ciudad, String pais, int estrellas) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO hotel (nombre, ciudad, pais, estrellas) VALUES (?, ?, ?, ?)"
            );

            stmt.setString(1, nombre);
            stmt.setString(2, ciudad);
            stmt.setString(3, pais);
            stmt.setInt(4, estrellas);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear hotel: " + e.getMessage());
            return false;
        }
    }

    // Editar hotel
    public static boolean editarHotel(int id, String nombre, String ciudad, String pais, int estrellas) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE hotel SET nombre=?, ciudad=?, pais=?, estrellas=? WHERE id=?"
            );

            stmt.setString(1, nombre);
            stmt.setString(2, ciudad);
            stmt.setString(3, pais);
            stmt.setInt(4, estrellas);
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar hotel: " + e.getMessage());
            return false;
        }
    }

    // Eliminar hotel
    public static boolean eliminarHotel(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM hotel WHERE id=?"
            );
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar hotel: " + e.getMessage());
            return false;
        }
    }

    // Buscar hotel por ID
    public static Hotel buscarPorId(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM hotel WHERE id=?"
            );
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Hotel(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("ciudad"),
                    rs.getString("pais"),
                    rs.getInt("estrellas")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Listar hoteles
    public static LinkedList<Hotel> listarHoteles() {

        LinkedList<Hotel> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM hotel"
            );
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Hotel(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("ciudad"),
                    rs.getString("pais"),
                    rs.getInt("estrellas")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
