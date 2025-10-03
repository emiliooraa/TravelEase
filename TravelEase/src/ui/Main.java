package ui;

import javax.swing.JOptionPane;

import bll.Usuario;
import dll.Conexion;
import dll.ControllerUsuario;

public class Main {
    public static void main(String[] args) {
       
        Conexion.getInstance();
		String email;
        String password;

        String[] opciones = { 
				"Login", "Registrar", "Salir"};

        int opcion;
		do {
			opcion = JOptionPane.showOptionDialog(null, "Elija una opción", "Bienvenid@, somos TravelEase✈️", 0, 1, null, opciones, opciones);
			switch (opcion) {
			case 0: //Login
            email = JOptionPane.showInputDialog("Ingrese su email:");
            password = JOptionPane.showInputDialog("Ingrese su contraseña:");
            Usuario encontrado = ControllerUsuario.login(email, password);

				JOptionPane.showMessageDialog(null, encontrado != null ? "Encontrado " + encontrado : "No encontrado");
				break;
            case 1: //Registrar
            
            break;
            }

        } while (opcion != 2);
    }
}


