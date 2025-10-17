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

    // Método de login usando BCrypt
    public static Usuario login(String mail, String password) {
        Usuario usuario = null;
        
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String rol = rs.getString("rol");
                String hashedPassword = rs.getString("password");

                // Compara el password ingresado con el hash de la base de datos
                if (BCrypt.checkpw(password, hashedPassword)) {
                    usuario = new Usuario(id, nombre, email, rol);
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return usuario;
    }

    // Método para registrar un usuario con un password seguro
    public static boolean registrarUsuario(String nombre, String dni, String email, String password) {
        
        try {
            
            // Genera el hash seguro del password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO usuario (nombre, dni, email, password) VALUES (?, ?, ?,?)"
            );
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
    //Metodo para editar Usuario, unicamente en Admin
    public static void editarAUsuario(Usuario usuario) {
        PreparedStatement stmt = null;
        String sql;
        String finalPassword = usuario.getPassword();

        try {
            if (finalPassword != null && !finalPassword.isEmpty()) {
                // Si hay contraseña nueva, la encripto
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
                // Si no cambia la contraseña
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
            stmt = con.prepareStatement(
                "SELECT id, nombre, dni, email, rol FROM usuario WHERE id = ?"
            );
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                encontrado = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getString("email"),
                    null, // contraseña no se devuelve
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

        try (
            PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            
            filasAfectadas = stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario en la base de datos: " + e.getMessage());
            return false; 
        }
        
        return filasAfectadas == 1;
    }
   
    }
