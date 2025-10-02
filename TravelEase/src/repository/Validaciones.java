package repository;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public interface Validaciones {

    static String ValidarString(String mensaje) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(mensaje);
            if (dato == null || dato.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error al ingresar dato, vuelva a intentarlo");
            }
        } while (dato == null || dato.trim().isEmpty());
        return dato;
    }

    // Validación de email con regex
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static boolean validarEmail(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }

    // Validación de password: mínimo 8 caracteres, una mayúscula, un número y un caracter especial
    static boolean validarPassword(String password) {
        if (password == null) return false;
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }
}
