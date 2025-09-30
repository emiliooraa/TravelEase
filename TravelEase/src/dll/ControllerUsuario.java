package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bll.Usuario;

public class ControllerUsuario {
    private static Connection con = Conexion.getInstance().getConnection();
    public static Usuario login(String mail, String password) {
		
		Usuario usuario = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? AND password = ?");
			stmt.setString(1, mail);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String dni = rs.getString("dni");
				String rol = rs.getString("rol");

				usuario = new Usuario(id, nombre, dni, password, rol );
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
