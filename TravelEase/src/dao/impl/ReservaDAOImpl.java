package dao.impl;

import dao.ReservaDAO;
import bll.Reserva;
import db.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    @Override
    public List<Reserva> listarTodas() {

        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setIdReserva(rs.getString("idReserva"));
                r.setEstado(rs.getString("estado"));
                r.setTotal(rs.getFloat("total"));
                r.setMedioPago(rs.getString("medioPago"));
                reservas.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public void confirmarReserva(String idReserva) {

        String sql = "UPDATE reserva SET estado = 'CONFIRMADA' WHERE idReserva = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idReserva);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelarReserva(String idReserva) {

        String sql = "UPDATE reserva SET estado = 'CANCELADA' WHERE idReserva = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idReserva);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
