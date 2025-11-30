package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Hotel;

public class ControllerHotel {

    private static Connection con = Conexion.getInstance().getConnection();

   //Crear hotel
    public static boolean crearHotel(String nombre, String destino, int habDisponibles) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO hotel (nombre, destino, habitaciones_disponibles) VALUES (?, ?, ?)"
            );

            stmt.setString(1, nombre);
            stmt.setString(2, destino);
            stmt.setInt(3, habDisponibles);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear hotel: " + e.getMessage());
            return false;
        }
    }

    // Editar Hotel
    public static boolean editarHotel(int id, String nombre, String destino, int habDisponibles) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE hotel SET nombre=?, destino=?, habitaciones_disponibles=? WHERE id=?"
            );

            stmt.setString(1, nombre);
            stmt.setString(2, destino);
            stmt.setInt(3, habDisponibles);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar hotel: " + e.getMessage());
            return false;
        }
    }

    // Eliminar Hotel
    public static boolean eliminarHotel(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM hotel WHERE id=?");
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar hotel: " + e.getMessage());
            return false;
        }
    }

    // Buscar Hotel por Id
    public static Hotel buscarPorId(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM hotel WHERE id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Hotel(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("destino"),
                    rs.getInt("habitaciones_disponibles")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Listar Hotel's
    public static LinkedList<Hotel> listarHoteles() {
        LinkedList<Hotel> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM hotel");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Hotel(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("destino"),
                    rs.getInt("habitaciones_disponibles")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
