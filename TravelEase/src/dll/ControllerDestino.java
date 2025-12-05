package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import bll.Destino;

public class ControllerDestino {

    private static Connection con = Conexion.getInstance().getConnection();

    public static boolean crearDestino(String nombre, String pais) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO destino (nombre, pais) VALUES (?, ?)"
            );
            stmt.setString(1, nombre);
            stmt.setString(2, pais);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear destino: " + e.getMessage());
            return false;
        }
    }
    //Editar
    public static boolean editarDestino(int id, String nombre, String pais) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE destino SET nombre=?, pais=? WHERE id=?"
            );
            stmt.setString(1, nombre);
            stmt.setString(2, pais);
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar destino: " + e.getMessage());
            return false;
        }
    }
    //Eliminar
    public static boolean eliminarDestino(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM destino WHERE id=?"
            );
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Destino eliminado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No existe un destino con ese ID.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar destino: " + e.getMessage());
        }

        return false;
    }
    
    //Buscar
    public static Destino buscarDestinoPorId(int id) {
        Destino d = null;

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM destino WHERE id=?"
            );
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                d = new Destino(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("pais")
                );
            }

        } catch (Exception e) {
            System.err.println("No se pudo buscar el destino: " + e.getMessage());
        }

        return d;
    }
    //Listar destinos
    public static LinkedList<Destino> listarDestinos() {
        LinkedList<Destino> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM destino ORDER BY nombre"
            );
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Destino(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("pais")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
