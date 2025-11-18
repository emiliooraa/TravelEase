package ui;

import java.awt.Font;
import javax.swing.*;
import dll.ControllerUsuario;
import repository.Validaciones;
import java.awt.Color;

public class AgregarUsuarioView extends JFrame {

    private static final long serialVersionUID = 1L;

    public AgregarUsuarioView(GestionarUsuariosView nuevo) {

        setTitle("Agregar Usuario");
        setSize(350, 421);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Nuevo Usuario");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(100, 10, 200, 30);
        getContentPane().add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        getContentPane().add(lblNombre);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(120, 60, 180, 25);
        getContentPane().add(txtNombre);

        JLabel lblErrorNombre = new JLabel("");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setBounds(120, 85, 200, 14);
        getContentPane().add(lblErrorNombre);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(20, 107, 100, 25);
        getContentPane().add(lblDni);
        JTextField txtDni = new JTextField();
        txtDni.setBounds(120, 107, 180, 25);
        getContentPane().add(txtDni);

        JLabel lblErrorDni = new JLabel("");
        lblErrorDni.setForeground(Color.RED);
        lblErrorDni.setBounds(120, 132, 200, 14);
        getContentPane().add(lblErrorDni);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 154, 100, 25);
        getContentPane().add(lblEmail);
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(120, 154, 180, 25);
        getContentPane().add(txtEmail);

        JLabel lblErrorEmail = new JLabel("");
        lblErrorEmail.setForeground(Color.RED);
        lblErrorEmail.setBounds(120, 179, 200, 14);
        getContentPane().add(lblErrorEmail);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(20, 215, 100, 25);
        getContentPane().add(lblPass);
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 215, 180, 25);
        getContentPane().add(txtPass);

        JLabel lblErrorPassword = new JLabel("");
        lblErrorPassword.setForeground(Color.RED);
        lblErrorPassword.setBounds(120, 240, 200, 14);
        getContentPane().add(lblErrorPassword);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(20, 262, 100, 25);
        getContentPane().add(lblRol);

        JComboBox<String> comboRol = new JComboBox<>();
        comboRol.addItem("ADMIN");
        comboRol.addItem("CLIENTE");
        comboRol.addItem("MANAGER");
        comboRol.addItem("OPERARIO");
        comboRol.setBounds(120, 262, 180, 25);
        getContentPane().add(comboRol);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(107, 310, 120, 30);
        getContentPane().add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            // Limpiar mensajes de error
            lblErrorNombre.setText("");
            lblErrorDni.setText("");
            lblErrorEmail.setText("");
            lblErrorPassword.setText("");

            boolean valido = true;

            lblErrorDni.setText("");
            lblErrorEmail.setText("");
            lblErrorPassword.setText("");
            lblErrorNombre.setText("");

            // Nombre vacío
            if (txtNombre.getText().trim().isEmpty()) {
                lblErrorNombre.setText("El nombre no puede estar vacío.");
                valido = false;
            }

            // DNI inválido
            if (!Validaciones.esDniValido(txtDni.getText())) {
                lblErrorDni.setText("DNI inválido (8 dígitos).");
                valido = false;
            }

            // Email inválido
            if (!Validaciones.esEmailValido(txtEmail.getText())) {
                lblErrorEmail.setText("Email inválido.");
                valido = false;
            }

            // Contraseña inválida
            String pass = new String(txtPass.getPassword());
            if (!Validaciones.esPasswordValida(pass)) {
                lblErrorPassword.setText("Contraseña inválida.");
                valido = false;
            }

            if (!valido) return;
            if (!valido) return;

            boolean ok = ControllerUsuario.registrarUsuario(
                txtNombre.getText(),
                txtDni.getText(),
                txtEmail.getText(),
                pass,
                comboRol.getSelectedItem().toString()
            );

            if (ok) {
                JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
                nuevo.cargarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
            }
        });
    }
}
