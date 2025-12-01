package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Vuelo;

public class ControllerVuelo {

	private static Connection con = Conexion.getInstance().getConnection();

	// CREAR VUELO

	public static boolean crearVuelo(String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, String aerolinea, int capacidadTotal, int asientosDisponibles) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO vuelo (codigo, origen, destino, fecha_salida, fecha_llegada, aerolinea, capacidad_total, asientos_disponibles) "
							+ "VALUES (?,?,?,?,?,?,?,?)");

			stmt.setString(1, codigo);
			stmt.setString(2, origen);
			stmt.setString(3, destino);
			stmt.setTimestamp(4, Timestamp.valueOf(fechaSalida));
			stmt.setTimestamp(5, Timestamp.valueOf(fechaLlegada));
			stmt.setString(6, aerolinea);
			stmt.setInt(7, capacidadTotal);
			stmt.setInt(8, asientosDisponibles);

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			System.err.println("Error al crear vuelo: " + e.getMessage());
			return false;
		}
	}

	// EDITAR VUELO
	public static boolean editarVuelo(
	        int id,
	        String codigo,
	        String origen,
	        String destino,
	        LocalDateTime fechaSalida,
	        LocalDateTime fechaLlegada,
	        String aerolinea,
	        int capacidadTotal,
	        int asientosDisponibles
	) {
	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "UPDATE vuelo SET codigo=?, origen=?, destino=?, fecha_salida=?, fecha_llegada=?, aerolinea=?, capacidad_total=?, asientos_disponibles=? WHERE id_vuelo=?"
	        );

	        stmt.setString(1, codigo);
	        stmt.setString(2, origen);
	        stmt.setString(3, destino);
	        stmt.setTimestamp(4, java.sql.Timestamp.valueOf(fechaSalida));
	        stmt.setTimestamp(5, java.sql.Timestamp.valueOf(fechaLlegada));
	        stmt.setString(6, aerolinea);
	        stmt.setInt(7, capacidadTotal);
	        stmt.setInt(8, asientosDisponibles);
	        stmt.setInt(9, id);

	        return stmt.executeUpdate() > 0;

	    } catch (Exception e) {
	        System.err.println("Error al editar vuelo: " + e.getMessage());
	        return false;
	    }
	}


	// ELIMINAR VUELO
	public static boolean eliminarVuelo(int id) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM vuelo WHERE id=?");
			stmt.setInt(1, id);

			int filas = stmt.executeUpdate();

			if (filas > 0) {
				System.out.println("Vuelo eliminado correctamente.");
				return true;
			} else {
				System.out.println("No existe un vuelo con ese ID.");
			}

		} catch (Exception e) {
			System.err.println("Error al eliminar vuelo: " + e.getMessage());
		}

		return false;
	}

	// BUSCAR VUELO POR ID
	public static Vuelo buscarVueloPorId(int id) {

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelo WHERE id_vuelo=?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Vuelo(rs.getInt("id_vuelo"), rs.getString("codigo"), rs.getString("origen"),
						rs.getString("destino"), rs.getTimestamp("fecha_salida").toLocalDateTime(),
						rs.getTimestamp("fecha_llegada").toLocalDateTime(), rs.getString("aerolinea"),
						rs.getInt("capacidad_total"), rs.getInt("asientos_disponibles"));
			}

		} catch (Exception e) {
			System.err.println("No se pudo buscar el vuelo: " + e.getMessage());
		}

		return null;
	}

	// LISTAR VUELOS
	public static LinkedList<Vuelo> listarVuelos() {
		LinkedList<Vuelo> lista = new LinkedList<>();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM vuelo");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				lista.add(new Vuelo(rs.getInt("id_vuelo"), rs.getString("codigo"), rs.getString("origen"),
						rs.getString("destino"), rs.getTimestamp("fecha_salida").toLocalDateTime(),
						rs.getTimestamp("fecha_llegada").toLocalDateTime(), rs.getString("aerolinea"),
						rs.getInt("capacidad_total"), rs.getInt("asientos_disponibles")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
}
