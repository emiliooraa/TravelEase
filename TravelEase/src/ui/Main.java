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
            Usuario encontrado = Usuario.login();
            JOptionPane.showMessageDialog(null, encontrado != null ? "Encontrado " + encontrado : "No encontrado");
				break;
            case 1: //Registrar
            Usuario nuevo = Usuario.registrarUsuario();
            break;
            
            }

        } while (opcion != 2);
    }
}


