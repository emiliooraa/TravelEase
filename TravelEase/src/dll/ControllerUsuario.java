package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import bll.Usuario;
import repository.Validaciones;
import org.mindrot.jbcrypt.BCrypt;

public class ControllerUsuario {
    private static Connection con = Conexion.getInstance().getConnection();

    public static Usuario login(String mail, String password) {
        Usuario usuario = null;
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?")) {
            stmt.setString(1, mail);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashed = rs.getString("password");
                    if (BCrypt.checkpw(password, hashed)) {
                        usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("email"),
                            null,
                            rs.getString("rol")
                        );
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return usuario;
    }

    public static void registrar() {
        String nombre = Validaciones.validarString("Ingrese nombre:");
        if (nombre == null) return;
        String dni = Validaciones.validarDni("Ingrese DNI (8 dígitos):");
        if (dni == null) return;
        String email = Validaciones.validarEmail("Ingrese email:");
        if (email == null) return;
        String password = Validaciones.validarPassword("Ingrese contraseña:");
        if (password == null) return;

        boolean ok = registrarUsuario(nombre, dni, email, password);
        JOptionPane.showMessageDialog(null, ok ? "Usuario registrado correctamente." : "No se pudo registrar el usuario.");
    }

    public static boolean registrarUsuario(String nombre, String dni, String email, String password) {
        try {
            if (existeEmail(email)) {
                System.err.println("Ya existe una cuenta registrada con ese email.");
                return false;
            }
            if (existeDni(dni)) {
                System.err.println("Ya existe una cuenta registrada con ese dni.");
                return false;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario (nombre, dni, email, password) VALUES (?, ?, ?, ?)");
            stmt.setString(1, nombre);
            stmt.setString(2, dni);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);

            int rows = stmt.executeUpdate();
            stmt.close();
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error en registro: " + e.getMessage());
            return false;
        }
    }

    public static void editarAUsuario(Usuario usuario) {
        PreparedStatement stmt = null;
        String sql;
        String finalPassword = usuario.getPassword();

        try {
            if (finalPassword != null && !finalPassword.isEmpty()) {
                finalPassword = BCrypt.hashpw(finalPassword, BCrypt.gensalt());
                sql = "UPDATE usuario SET nombre=?, dni=?, email=?, password=?, rol=? WHERE id=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getDni());
                stmt.setString(3, usuario.getEmail());
                stmt.setString(4, finalPassword);
                stmt.setString(5, usuario.getRol());
                stmt.setInt(6, usuario.getId());
            } else {
                sql = "UPDATE usuario SET nombre=?, dni=?, email=?, rol=? WHERE id=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getDni());
                stmt.setString(3, usuario.getEmail());
                stmt.setString(4, usuario.getRol());
                stmt.setInt(5, usuario.getId());
            }

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println("✅ Usuario editado correctamente.");
            } else {
                System.out.println("⚠️ No se pudo editar el usuario (ID no encontrado).");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al editar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }

    public static Usuario buscarUsuarioPorId(int id) {
        Usuario encontrado = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT id, nombre, dni, email, rol FROM usuario WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                encontrado = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getString("email"),
                    null,
                    rs.getString("rol")
                );
            }
        } catch (Exception e) {
            System.err.println("❌ Error al buscar usuario por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
        return encontrado;
    }

    public static boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        int filasAfectadas = 0;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            filasAfectadas = stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario en la base de datos: " + e.getMessage());
            return false;
        }
        return filasAfectadas == 1;
    }

    public static boolean existeEmail(String email) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM usuario WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existeDni(String dni) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM usuario WHERE dni = ?");
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
