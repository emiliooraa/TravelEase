package ui;

import javax.swing.JOptionPane;

import bll.Usuario;
import dll.Conexion;

public class Main {
    public static void main(String[] args) {
       
        Conexion.getInstance();
		String mail, password;

        String[] opciones = { 
				"Login", "Salir"};

        int opcion;
		do {
			opcion = JOptionPane.showOptionDialog(null, "Elija opción", null, 0, 0, null, opciones, opciones);
			switch (opcion) {
			case 0:
            Usuario encontrado = Usuario.Login();

				JOptionPane.showMessageDialog(null, encontrado != null ? "Encontrado " + encontrado : "No encontrado");
				break;
            }

        } while (opcion != 1);
    }
}


