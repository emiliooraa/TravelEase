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
                String hashedPassword = rs.getString("password");

                // Compara el password ingresado con el hash de la base de datos
                if (BCrypt.checkpw(password, hashedPassword)) {
                    usuario = new Usuario(id, nombre, email);
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
    public static boolean registrarUsuario(String nombre, String email, String password) {
        
        try {
            // Valida email y password antes de insertar
            if (!Validaciones.validarEmail(email) || !Validaciones.validarPassword(password)) {
                return false;
            }

            // Genera el hash seguro del password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			boolean check = BCrypt.checkpw(password, hashedPassword);

            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO usuario (nombre, email, password) VALUES (?, ?, ?)"
            );
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);

            int rows = stmt.executeUpdate();
            stmt.close();

            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error en registro: " + e.getMessage());
            return false;
        }
    }
}