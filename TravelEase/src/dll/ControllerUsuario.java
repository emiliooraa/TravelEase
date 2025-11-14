package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import bll.Usuario;
import repository.Validaciones;

public class ControllerUsuario {
    private static Connection con = Conexion.getInstance().getConnection();

    //LOGIN
    public static Usuario login(String mail, String password) {
        if (mail == null || mail.trim().isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Debe ingresar email y contraseña.");
            return null;
        }

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
                    } else {
                    	System.err.println("Contraseña incorrecta.");
                    }
                } else {
                	System.err.println("No existe una cuenta con ese email.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return usuario;
    }

    //REGISTRAR
    public static void registrar() {
        String nombre = Validaciones.validarString("Ingrese nombre:");
        if (nombre == null) return;

        String dni = Validaciones.validarDni("Ingrese DNI (8 dígitos):");
        if (dni == null) return;

        String email = Validaciones.validarEmail("Ingrese email:");
        if (email == null) return;

        String password = Validaciones.validarPassword("Ingrese contraseña:");
        if (password == null) return;

        boolean ok = registrarUsuario(nombre.trim(), dni.trim(), email.trim(), password);
        JOptionPane.showMessageDialog(null, ok ? "✅ Usuario registrado correctamente." : "❌ No se pudo registrar el usuario.");
    }

    public static boolean registrarUsuario(String nombre, String dni, String email, String password) {
        // Validaciones básicas
        if (nombre.isEmpty() || dni.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!dni.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Validaciones.esEmailValido(email)) {
            JOptionPane.showMessageDialog(null, "El formato del email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            if (existeEmail(email)) {
                JOptionPane.showMessageDialog(null, "Ya existe una cuenta registrada con ese email.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (existeDni(dni)) {
                JOptionPane.showMessageDialog(null, "Ya existe una cuenta registrada con ese DNI.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario (nombre, dni, email, password, rol) VALUES (?, ?, ?, ?, 'usuario')");
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

    // EDITAR
    public static void editarAUsuario(Usuario usuario) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "El usuario no puede ser nulo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuario.getNombre().trim().isEmpty() || usuario.getEmail().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre y el email no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.esEmailValido(usuario.getEmail())) {
            JOptionPane.showMessageDialog(null, "El formato del email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar que no exista otro usuario con el mismo email o dni
        try {
            PreparedStatement check = con.prepareStatement("SELECT id FROM usuario WHERE (email = ? OR dni = ?) AND id != ?");
            check.setString(1, usuario.getEmail());
            check.setString(2, usuario.getDni());
            check.setInt(3, usuario.getId());
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Ya existe otro usuario con el mismo email o DNI.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            check.close();

            PreparedStatement stmt;
            String sql;
            String finalPassword = usuario.getPassword();

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
            JOptionPane.showMessageDialog(null, filas > 0 ? "✅ Usuario editado correctamente." : "⚠️ No se pudo editar el usuario.");
        } catch (Exception e) {
            System.err.println("❌ Error al editar usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ELIMINAR / BUSCAR / LISTAR
    public static Usuario buscarUsuarioPorId(int id) {
        Usuario encontrado = null;
        try (PreparedStatement stmt = con.prepareStatement("SELECT id, nombre, dni, email, rol FROM usuario WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
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
        }
        return encontrado;
    }

    public static boolean eliminarUsuario(int id) {
        try (PreparedStatement stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?")) {
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "✅ Usuario eliminado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró un usuario con ese ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //AUXILIARES
    public static boolean existeEmail(String email) {
        try (PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM usuario WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existeDni(String dni) {
        try (PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM usuario WHERE dni = ?")) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static LinkedList<Usuario> listarUsuarios() {
        LinkedList<Usuario> lista = new LinkedList<>();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getString("email"),
                    null,
                    rs.getString("rol")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
