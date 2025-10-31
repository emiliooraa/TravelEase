package dll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/travelease?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conect;
    private static Conexion instance;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión establecida con la base de datos 'travelease'.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: No se encontró el driver de MySQL.");
        } catch (SQLException e) {
            System.err.println("❌ No se pudo conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return conect;
    }

    public static void main(String[] args) {
        Conexion conexion = Conexion.getInstance();
        if (conexion.getConnection() != null) {
            System.out.println("✅ Prueba de conexión exitosa.");
        } else {
            System.err.println("❌ Falló la conexión.");
        }
    }
}
