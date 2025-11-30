package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bll.Paquete;
import bll.Vuelo;
import bll.Hotel;

public class ControllerPaquete {
	
	private static Connection con = Conexion.getInstance().getConnection();

    public void crearPaquete(String nombre, String descripcion, double precio) {
        System.out.println("Paquete creado: " + nombre);
    }

    public void verPaquetes() {
        System.out.println("Listado de paquetes...");
    }

    public void editarPaquete(int id, String nombre, String descripcion, double precio) {
        System.out.println("Paquete editado ID: " + id);
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