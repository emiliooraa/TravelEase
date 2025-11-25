package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Vuelo;

public class ControllerVuelo {

    private static Connection con = Conexion.getInstance().getConnection();

    // Crear vuelo
    
    public static boolean crearVuelo(String origen, String destino, java.time.LocalDate fecha, java.time.LocalTime horario, int capacidad, int asientosDisponibles) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO vuelo (origen, destino, fecha, horario, capacidad, asientos_disponibles) VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setDate(3, Date.valueOf(fecha));
            stmt.setTime(4, Time.valueOf(horario));
            stmt.setInt(5, capacidad);
            stmt.setInt(6, asientosDisponibles);

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear vuelo: " + e.getMessage());
            return false;
        }
    }


    // Editar

    public static boolean editarVuelo(int id, String origen, String destino, 
            java.time.LocalDate fecha, java.time.LocalTime horario,
            int capacidad, int asientosDisponibles) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE vuelo SET origen=?, destino=?, fecha=?, horario=?, capacidad=?, asientos_disponibles=? WHERE id=?"
            );

            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setDate(3, Date.valueOf(fecha));
            stmt.setTime(4, Time.valueOf(horario));
            stmt.setInt(5, capacidad);
            stmt.setInt(6, asientosDisponibles);
            stmt.setInt(7, id);

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo editar el vuelo: " + e.getMessage());
            return false;
        }
    }
    
    // Eliminar vuelo

    public static boolean eliminarVuelo(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM vuelo WHERE id=?"
            );
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Vuelo eliminado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No existe un vuelo con ese ID.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar vuelo: " + e.getMessage());
        }

        return false;
    }

    // Buscar por id
    public static Vuelo buscarVueloPorId(int id) {
        Vuelo v = null;

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM vuelo WHERE id=?"
            );
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                v = new Vuelo(
                    rs.getInt("id"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getTime("horario").toLocalTime(),
                    rs.getInt("capacidad"),
                    rs.getInt("asientos_disponibles")
                );
            }

        } catch (Exception e) {
            System.err.println("No se pudo buscar el vuelo: " + e.getMessage());
        }

        return v;
    }

    // Listar vuelos
    
    public static LinkedList<Vuelo> listarVuelos() {
        LinkedList<Vuelo> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelo");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Vuelo(
                    rs.getInt("id"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getTime("horario").toLocalTime(),
                    rs.getInt("capacidad"),
                    rs.getInt("asientos_disponibles")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}
