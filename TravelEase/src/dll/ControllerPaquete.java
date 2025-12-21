package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Paquete;
import bll.Vuelo;
import bll.Hotel;

public class ControllerPaquete {

    private static Connection con = Conexion.getInstance().getConnection();

    // Crear paquete
    public static boolean crearPaquete(int idVuelo, int idHotel) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO paquete (id_vuelo, id_hotel) VALUES (?, ?)"
            );

            stmt.setInt(1, idVuelo);
            stmt.setInt(2, idHotel);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error al crear paquete: " + e.getMessage());
            return false;
        }
    }

    // Eliminar paquete
    public static boolean eliminarPaquete(int idPaquete) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM paquete WHERE id_paquete = ?"
            );

            stmt.setInt(1, idPaquete);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
        	System.err.println("Error al eliminar paquete: " + e.getMessage());
            return false;
        }
    }

    // Buscar paquete por ID
    public static Paquete buscarPorId(int idPaquete) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM paquete WHERE id_paquete = ?"
            );

            stmt.setInt(1, idPaquete);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Vuelo vuelo = ControllerVuelo.buscarVueloPorId(rs.getInt("id_vuelo"));
                Hotel hotel = ControllerHotel.buscarPorId(rs.getInt("id_hotel"));

                return new Paquete(
                    rs.getInt("id_paquete"),
                    vuelo,
                    hotel
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Listar paquetes
    public static LinkedList<Paquete> listarPaquetes() {

        LinkedList<Paquete> lista = new LinkedList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM paquete"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Vuelo vuelo = ControllerVuelo.buscarVueloPorId(rs.getInt("id_vuelo"));
                Hotel hotel = ControllerHotel.buscarPorId(rs.getInt("id_hotel"));

                lista.add(new Paquete(
                    rs.getInt("id_paquete"),
                    vuelo,
                    hotel
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
