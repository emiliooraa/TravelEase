package repository;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public interface Validaciones {

	static String validarString(String mensaje) {
	    String dato;
	    do {
	        dato = JOptionPane.showInputDialog(mensaje);
	        if (dato == null) {
	            return null;
	        }
	        if (dato.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Error al ingresar dato, vuelva a intentarlo");
	        }
	    } while (dato.trim().isEmpty());
	    return dato.trim();
	}

    // --- Validación de Email ---
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    static String validarEmail(String mensaje) {
        String email;
        do {
            email = JOptionPane.showInputDialog(mensaje);
            if (email == null) return null; // Si cancela
            if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
                JOptionPane.showMessageDialog(null, "Email inválido. Intente nuevamente.");
                email = null;
            }
        } while (email == null);
        return email;
    }

    // Validación de password: mínimo 8 caracteres, una mayúscula, un número y un caracter especial
    static String validarPassword(String mensaje) {
        String password;
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        do {
            password = JOptionPane.showInputDialog(mensaje);
            if (password == null) 
            return null; // Si cancela
            if (!password.matches(regex)) {
                JOptionPane.showMessageDialog(null,
                    "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un caracter especial.");
                password = null;
            }
        } while (password == null);

        return password;
    }
    static String validarDni(String mensaje) {
        String dni;
        do {
            dni = JOptionPane.showInputDialog(mensaje);
            
           if (dni == null) 
           return null; // Si cancela
            if (!dni.matches("\\d{8}")) { // Valida que tenga exactamente 8 dígitos
                JOptionPane.showMessageDialog(null,
                    "El DNI debe tener exactamente 8 dígitos.");
                dni = null; 
            }
        } while (dni == null);

        return dni;
    }
}
