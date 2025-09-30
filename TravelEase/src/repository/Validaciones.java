package repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public interface Validaciones {

	static String ValidarString(String mensaje) {

		String dato;
		do {

			dato = JOptionPane.showInputDialog(mensaje);
			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error al ingresar dato, vuela a intentarlo");
			}
		} while (dato.isEmpty());

		return dato;

	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.matches();
	}

	static String ValidarMail() {
		boolean flag;

		String dato;
		do {
			flag = true;
			do {

				dato = JOptionPane.showInputDialog("Ingrese mail");
				if (dato.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error al ingresar mail, vuela a intentarlo");
				}
			} while (dato.isEmpty());
			if (validate(dato)) {

				JOptionPane.showMessageDialog(null, "Mail valido");

			} else {
				JOptionPane.showMessageDialog(null, "Mail no valido");
				flag = false;

			}
		} while (flag == false);
		return dato;
	}

}

