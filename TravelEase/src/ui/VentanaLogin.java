package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import bll.Usuario;
import dll.ControllerUsuario;

public class VentanaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public VentanaLogin() {

        setTitle("Login - Sistema de Viajes");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 255));
        add(panel);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setBounds(120, 40, 200, 40);
        panel.add(lblTitulo);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(50, 130, 100, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, 160, 300, 35);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(txtEmail);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setBounds(50, 215, 150, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 245, 300, 35);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 330, 200, 40);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(new Color(100, 149, 237));
        btnLogin.setForeground(Color.WHITE);

        panel.add(btnLogin);

        btnLogin.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "Debe completar todos los campos.", 
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Usuario usuario = ControllerUsuario.login(email, password);

        if (usuario == null) {
            JOptionPane.showMessageDialog(
                this, 
                "Email o contraseña incorrectos.",
                "Error de autenticación", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
            this, 
            "Bienvenido/a: " + usuario.getNombre(),
            "Acceso correcto",
            JOptionPane.INFORMATION_MESSAGE
        );

        abrirMenuPorRol(usuario);
    }

    private void abrirMenuPorRol(Usuario usuario) {
        String rol = usuario.getRol().toLowerCase();

        dispose();

        switch (rol) {
            case "operario":
                new VentanaOperarioMenu(usuario).setVisible(true);
                break;

            case "cliente":
                new ClienteMenu(usuario).setVisible(true);
                break;

            case "admin":
                new AdminMenu(usuario).setVisible(true);
                break;

            case "manager":
                new ManagerMenu(usuario).setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(null, 
                    "Rol no reconocido: " + usuario.getRol());
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}
