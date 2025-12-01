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
        if (nombre == null) 
        	return;

        String dni = Validaciones.validarDni("Ingrese DNI:");
        if (dni == null) 
        	return;

        String email = Validaciones.validarEmail("Ingrese email:");
        if (email == null) 
        	return;

        String password = Validaciones.validarPassword("Ingrese contraseña:");
        if (password == null) 
        	return;

        boolean ok = registrarUsuario(nombre, dni, email, password, "USUARIO");

        JOptionPane.showMessageDialog(null,
                ok ? "Usuario registrado correctamente." : "Error al registrar el usuario.");
    }
    

    public static boolean registrarUsuario(String nombre, String dni, String email, String password, String rol) {

        // Validaciones básicas
        if (nombre.isEmpty() || dni.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
            return false;
        }

        if (!dni.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 dígitos.");
            return false;
        }

        if (!Validaciones.esEmailValido(email)) {
            JOptionPane.showMessageDialog(null, "Formato de email inválido.");
            return false;
        }

        try {
            if (existeEmail(email)) {
                JOptionPane.showMessageDialog(null, "Ya existe una cuenta con ese email.");
                return false;
            }

            if (existeDni(dni)) {
                JOptionPane.showMessageDialog(null, "Ya existe una cuenta con ese DNI.");
                return false;
            }

            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO usuario (nombre, dni, email, password, rol) VALUES (?, ?, ?, ?, ?)");

            stmt.setString(1, nombre);
            stmt.setString(2, dni);
            stmt.setString(3, email);
            stmt.setString(4, hashed);
            stmt.setString(5, rol);

            int rows = stmt.executeUpdate();
            stmt.close();

            return rows > 0;

        } catch (Exception e) {
        	System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // EDITAR
    public static boolean editarAUsuario(int id, String nombre, String dni, String email, String password, String rol) {
        try {
            PreparedStatement stmt;

            if (password != null && !password.trim().isEmpty()) {
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

                stmt = con.prepareStatement(
                    "UPDATE usuario SET nombre=?, dni=?, email=?, password=?, rol=? WHERE id=?"
                );
                stmt.setString(1, nombre);
                stmt.setString(2, dni);
                stmt.setString(3, email);
                stmt.setString(4, hashed);
                stmt.setString(5, rol);
                stmt.setInt(6, id);
            } else {
                stmt = con.prepareStatement(
                    "UPDATE usuario SET nombre=?, dni=?, email=?, rol=? WHERE id=?"
                );
                stmt.setString(1, nombre);
                stmt.setString(2, dni);
                stmt.setString(3, email);
                stmt.setString(4, rol);
                stmt.setInt(5, id);
            }

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            System.err.println("Error al editar usuario: " + e.getMessage());
            return false;
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
            			    "",             
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
                System.out.println("✅ Usuario eliminado correctamente.");
                return true;
            } else {
            	System.out.println("⚠️ No se encontró un usuario con ese ID.");
            }
        } catch (Exception e) {
        	System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
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
