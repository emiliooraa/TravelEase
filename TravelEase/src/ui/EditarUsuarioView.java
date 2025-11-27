package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bll.Usuario;
import dll.ControllerUsuario;
import repository.Validaciones;

public class EditarUsuarioView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Usuario usuario;
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EditarUsuarioView(Usuario usuario) {
	  
		setTitle("Editar Usuario");
        setSize(350, 421);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Editar Usuario: " + usuario.getNombre());
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(30, 11, 273, 30);
        getContentPane().add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        getContentPane().add(lblNombre);
        JTextField inpNombre = new JTextField();
        inpNombre.setBounds(120, 60, 180, 25);
        getContentPane().add(inpNombre);
        inpNombre.setText(usuario.getNombre());
        

        JLabel lblErrorNombre = new JLabel("");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setBounds(120, 85, 200, 14);
        getContentPane().add(lblErrorNombre);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(20, 107, 100, 25);
        getContentPane().add(lblDni);
        JTextField inpDni = new JTextField();
        inpDni.setBounds(120, 107, 180, 25);
        getContentPane().add(inpDni);
        inpDni.setText(usuario.getDni());

        JLabel lblErrorDni = new JLabel("");
        lblErrorDni.setForeground(Color.RED);
        lblErrorDni.setBounds(120, 132, 200, 14);
        getContentPane().add(lblErrorDni);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 154, 100, 25);
        getContentPane().add(lblEmail);
        JTextField inpEmail = new JTextField();
        inpEmail.setBounds(120, 154, 180, 25);
        getContentPane().add(inpEmail);
        inpEmail.setText(usuario.getEmail());


        JLabel lblErrorEmail = new JLabel("");
        lblErrorEmail.setForeground(Color.RED);
        lblErrorEmail.setBounds(120, 179, 200, 14);
        getContentPane().add(lblErrorEmail);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(20, 215, 100, 25);
        getContentPane().add(lblPass);
        JPasswordField inpPass = new JPasswordField();
        inpPass.setBounds(120, 215, 180, 25);
        getContentPane().add(inpPass);
        inpPass.setText(usuario.getPassword());

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
            if (inpNombre.getText().trim().isEmpty()) {
                lblErrorNombre.setText("El nombre no puede estar vacío.");
                valido = false;
            }

            // DNI inválido
            if (!Validaciones.esDniValido(inpDni.getText())) {
                lblErrorDni.setText("DNI inválido (8 dígitos).");
                valido = false;
            }

            // Email inválido
            if (!Validaciones.esEmailValido(inpEmail.getText())) {
                lblErrorEmail.setText("Email inválido.");
                valido = false;
            }

            // Contraseña inválida
         
            String pass = new String(inpPass.getPassword());

            if (pass.trim().isEmpty()) {
                pass = null;  
            } else {
                if (!Validaciones.esPasswordValida(pass)) {
                    lblErrorPassword.setText("Contraseña inválida.");
                    valido = false;
                }
            }


            boolean ok = ControllerUsuario.editarAUsuario(
            	    usuario.getId(),
            	    inpNombre.getText(),
            	    inpDni.getText(),
            	    inpEmail.getText(),
            	    pass,
            	    comboRol.getSelectedItem().toString()
            	);


            if (ok) {
                JOptionPane.showMessageDialog(null, "Usuario editado correctamente.");
                
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar el usuario.");
            }
        });
    }

}
