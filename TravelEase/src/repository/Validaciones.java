package repository;

import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validaciones {

    //VALIDAR STRING
    public static String validarString(String mensaje) {
        String valor;
        do {
            valor = JOptionPane.showInputDialog(null, mensaje);
            if (valor == null) return null; 
            valor = valor.trim();
            if (valor.isEmpty()) {
                JOptionPane.showMessageDialog(null, " Este campo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (valor.isEmpty());
        return valor;
    }

    //VALIDAR DNI
    public static String validarDni(String mensaje) {
        String dni;
        do {
            dni = JOptionPane.showInputDialog(null, mensaje);
            if (dni == null) return null; //
            dni = dni.trim();

            if (!dni.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(null, " El DNI debe tener exactamente 8 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                dni = "";
            }
        } while (dni.isEmpty());
        return dni;
    }

    //VALIDAR EMAIL
    public static String validarEmail(String mensaje) {
        String email;
        do {
            email = JOptionPane.showInputDialog(null, mensaje);
            if (email == null) return null;
            email = email.trim();

            if (!esEmailValido(email)) {
                JOptionPane.showMessageDialog(null, "⚠️ El formato del email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                email = "";
            }
        } while (email.isEmpty());
        return email;
    }

    // VALIDAR CONTRASEÑA 
    public static String validarPassword(String mensaje) {
        String pass;
        do {
            pass = JOptionPane.showInputDialog(null, mensaje);
            if (pass == null) return null;

            if (!esPasswordValida(pass)) {
                JOptionPane.showMessageDialog(
                    null,
                    "La contraseña debe cumplir los siguientes requisitos:\n" +
                    "• Tener al menos 6 caracteres.\n" +
                    "• Incluir una letra mayúscula.\n" +
                    "• Incluir un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                pass = "";
            }
        } while (pass.isEmpty());
        return pass;
    }


    // MÉTODOS AUXILIARES 
    public static boolean esEmailValido(String email) {
        if (email == null) return false;
        String regex = "^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean esPasswordValida(String password) {
        if (password == null) return false;
        // Mínimo 6 caracteres, al menos una mayúscula y un número
        String regex = "^(?=.*[A-Z])(?=.*\\d).{6,}$";
        return password.matches(regex);
    }
}
