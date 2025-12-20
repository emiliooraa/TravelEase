package dao.impl;

import dao.UsuarioDAO;
import bll.Usuario;
import db.ConexionDB;

import java.sql.*;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario login(String email, String contrasenia) {

        String sql = "SELECT * FROM usuario WHERE email = ? AND contrasenia = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, contrasenia);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getString("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
