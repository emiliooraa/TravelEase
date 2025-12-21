package dll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bll.Hotel;

public class ControllerHotel {

    private static Connection con = Conexion.getInstance().getConnection();

    // CREAR
    public static boolean crearHotel(Hotel hotel) {
        String sql = "INSERT INTO hotel (nombre, ciudad, pais, estrellas) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hotel.getNombre());
            ps.setString(2, hotel.getCiudad());
            ps.setString(3, hotel.getPais());
            ps.setInt(4, hotel.getEstrellas());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EDITAR
    public static boolean editarHotel(Hotel hotel) {
        String sql = "UPDATE hotel SET nombre = ?, ciudad = ?, pais = ?, estrellas = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hotel.getNombre());
            ps.setString(2, hotel.getCiudad());
            ps.setString(3, hotel.getPais());
            ps.setInt(4, hotel.getEstrellas());
            ps.setInt(5, hotel.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public static boolean eliminarHotel(int id) {
        String sql = "DELETE FROM hotel WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR
    public static List<Hotel> listarHotel() {
        List<Hotel> hoteles = new ArrayList<>();
        String sql = "SELECT id, nombre, ciudad, pais, estrellas FROM hotel";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Hotel h = new Hotel(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("ciudad"),
                    rs.getString("pais"),
                    rs.getInt("estrellas")
                );
                hoteles.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoteles;
    }
    
    // BUSCAR HOTEL POR ID
    public static Hotel buscarPorId(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM hotel WHERE id=?");
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
}
