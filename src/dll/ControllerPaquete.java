package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bll.Paquete;
import bll.Vuelo;
import bll.Hotel;

public class ControllerPaquete {
	
	private static Connection con = Conexion.getInstance().getConnection();

    public void crearPaquete(String nombre, String descripcion) {
        System.out.println("Paquete creado: " + nombre);
    }

    public void verPaquetes() {
        System.out.println("Listado de paquetes...");
    }

    public void editarPaquete(int id, String nombre, String descripcion) {
        System.out.println("Paquete editado ID: " + id);
    }
    
    public List<Paquete> listarPaquetes() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT id_paquete, id_vuelo, id_hotel FROM paquetes";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idPaquete = rs.getInt("id_paquete");
                int idVuelo   = rs.getInt("id_vuelo");
                int idHotel   = rs.getInt("id_hotel");

                Vuelo vuelo = ControllerVuelo.buscarVueloPorId(idVuelo);
                Hotel hotel = ControllerHotel.buscarPorId(idHotel);

                if (vuelo == null || hotel == null) {
                    continue;
                }

                Paquete p = new Paquete();
                p.setId(idPaquete);
                p.setVuelo(vuelo);
                p.setHotel(hotel);

                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar paquetes: " + e.getMessage());
        }

        return lista;
    }
    
    //Guardar paquete
    public void guardarPaquete(Paquete paquete) throws SQLException {
        String sql = "INSERT INTO paquetes (id_vuelo, id_hotel) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            Vuelo vuelo = paquete.getVuelo();
            Hotel hotel = paquete.getHotel();

            ps.setInt(1, vuelo.getId());
            ps.setInt(2, hotel.getId());

            ps.executeUpdate();
        }
    }
}