package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import bll.Usuario;
import dll.ControllerUsuario;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpEmailLogin;
	private JPasswordField inpContraseniaLogin;
	private JPasswordField inpContraseniaRegistro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicio() {
		setTitle("TravelEase");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 491, 470);
		tabbedPane.setFont(new Font("Gadugi", Font.PLAIN, 20));
		tabbedPane.setToolTipText("Login");
		contentPane.add(tabbedPane);

		JPanel Login = new JPanel();
		tabbedPane.addTab("Login", null, Login, null);
		Login.setLayout(null);

		JLabel lblIniciarSesion = new JLabel("Iniciar Sesión");
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font("Gadugi", Font.PLAIN, 19));
		lblIniciarSesion.setBounds(132, 23, 221, 43);
		Login.add(lblIniciarSesion);

		JLabel lblEmailLogin = new JLabel("Email");
		lblEmailLogin.setFont(new Font("Gadugi", Font.BOLD, 14));
		lblEmailLogin.setBounds(132, 77, 130, 28);
		Login.add(lblEmailLogin);

		inpEmailLogin = new JTextField();
		inpEmailLogin.setColumns(10);
		inpEmailLogin.setBounds(132, 109, 221, 28);
		Login.add(inpEmailLogin);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Gadugi", Font.BOLD, 14));
		lblPassword.setBounds(132, 148, 130, 28);
		Login.add(lblPassword);

		inpContraseniaLogin = new JPasswordField();
		inpContraseniaLogin.setBounds(132, 173, 221, 28);
		Login.add(inpContraseniaLogin);

		JLabel lblNuevaCuenta = new JLabel("¿No estás registrado? ¡Podés registrarte acá abajo!");
		lblNuevaCuenta.setFont(new Font("Gadugi", Font.PLAIN, 13));
		lblNuevaCuenta.setBounds(83, 319, 319, 14);
		lblNuevaCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		Login.add(lblNuevaCuenta);

		JLabel lblError1 = new JLabel("");
		lblError1.setBounds(90, 265, 300, 28);
		lblError1.setHorizontalAlignment(SwingConstants.CENTER);
		lblError1.setForeground(Color.RED);
		Login.add(lblError1);

		JButton btnVentanaRegistrar = new JButton("Registrar");
		btnVentanaRegistrar.setForeground(new Color(255, 255, 255));
		btnVentanaRegistrar.setBackground(new Color(135, 206, 235));
		btnVentanaRegistrar.setFont(new Font("Gadugi", Font.BOLD, 17));
		btnVentanaRegistrar.setBounds(132, 344, 221, 37);
		Login.add(btnVentanaRegistrar);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(SystemColor.window);
		btnLogin.setFont(new Font("Gadugi", Font.BOLD, 17));
		btnLogin.setBounds(132, 212, 221, 37);
		Login.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError1.setText("");
				String email = inpEmailLogin.getText() != null ? inpEmailLogin.getText().trim() : "";
				String password = new String(inpContraseniaLogin.getPassword());
				Usuario usuario = ControllerUsuario.login(email, password);
				if (usuario == null) {
				    lblError1.setText("Usuario o contraseña inválidos.");
				} else {
					//View de cada rol
					
				    switch (usuario.getRol().toLowerCase()) {
				     	case "admin":
				     		new AdminMenu(usuario).setVisible(true);
				    	 break;
				     	case "usuario":
				     		new UsuarioInterfaz(usuario).setVisible(true);
				     	break;
				     	case "operario":
				     		new OperarioMenu(usuario).setVisible(true);
				     	break;
				     	case "manager":
				     		new ManagerMenu(usuario).setVisible(true);
				     	break;
				     	default:
				             JOptionPane.showMessageDialog(null, "Rol no reconocido: " + usuario.getRol());
				             return;
				    }
				    dispose(); 
				}
				}
			
		});

		btnVentanaRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});

		JPanel Registrar = new JPanel();
		tabbedPane.addTab("Registrar", null, Registrar, null);
		Registrar.setLayout(null);

		JFormattedTextField inpNombreRegistro = new JFormattedTextField();
		inpNombreRegistro.setBounds(141, 82, 203, 29);
		Registrar.add(inpNombreRegistro);

		JFormattedTextField inpEmailRegistro = new JFormattedTextField();
		inpEmailRegistro.setBounds(141, 145, 203, 29);
		Registrar.add(inpEmailRegistro);

		JFormattedTextField inpDniRegistro = new JFormattedTextField();
		inpDniRegistro.setBounds(141, 208, 203, 29);
		Registrar.add(inpDniRegistro);

		inpContraseniaRegistro = new JPasswordField();
		inpContraseniaRegistro.setBounds(141, 271, 203, 29);
		Registrar.add(inpContraseniaRegistro);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(141, 57, 63, 14);
		lblNombre.setFont(new Font("Gadugi", Font.BOLD, 14));
		Registrar.add(lblNombre);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(141, 120, 46, 14);
		lblEmail.setFont(new Font("Gadugi", Font.BOLD, 14));
		Registrar.add(lblEmail);

		JLabel lblDni = new JLabel("Documento");
		lblDni.setBounds(141, 183, 80, 14);
		lblDni.setFont(new Font("Gadugi", Font.BOLD, 14));
		Registrar.add(lblDni);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(141, 246, 80, 14);
		lblContrasena.setFont(new Font("Gadugi", Font.BOLD, 14));
		Registrar.add(lblContrasena);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(SystemColor.window);
		btnRegistrarse.setBounds(168, 313, 150, 30);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 17));
		Registrar.add(btnRegistrarse);

		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblError.setBounds(65, 346, 355, 20);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		Registrar.add(lblError);

		JLabel lblRegistrado = new JLabel("¿Ya estás registrado?");
		lblRegistrado.setBounds(72, 366, 341, 20);
		lblRegistrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrado.setForeground(new Color(0, 128, 0));
		lblRegistrado.setFont(new Font("Gadugi", Font.PLAIN, 14));
		Registrar.add(lblRegistrado);

		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setForeground(SystemColor.window);
		btnIniciarSesion.setBackground(new Color(135, 206, 235));
		btnIniciarSesion.setBounds(169, 389, 147, 29);
		btnIniciarSesion.setFont(new Font("Gadugi", Font.BOLD, 17));
		Registrar.add(btnIniciarSesion);

		JLabel lblRegistrar = new JLabel("Registrar");
		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setFont(new Font("Gadugi", Font.PLAIN, 19));
		lblRegistrar.setBounds(157, 0, 165, 29);
		Registrar.add(lblRegistrar);

		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				String nombre = inpNombreRegistro.getText() != null ? inpNombreRegistro.getText().trim() : "";
				String email = inpEmailRegistro.getText() != null ? inpEmailRegistro.getText().trim() : "";
				String dni = inpDniRegistro.getText() != null ? inpDniRegistro.getText().trim() : "";
				String password = new String(inpContraseniaRegistro.getPassword());
				if (nombre.isEmpty() || email.isEmpty() || dni.isEmpty() || password.isEmpty()) {
					lblError.setText("Complete todos los campos.");
					return;
				}
				boolean ok = ControllerUsuario.registrarUsuario(nombre, dni, email, password, "USUARIO");
				if (ok) {
					JOptionPane.showMessageDialog(Registrar, "Usuario registrado correctamente.");
					inpNombreRegistro.setText("");
					inpEmailRegistro.setText("");
					inpDniRegistro.setText("");
					inpContraseniaRegistro.setText("");
					tabbedPane.setSelectedIndex(0);
				} else {
					lblError.setText("No se pudo registrar. Verifique los datos.");
				}
			}
		});

		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
	}
}
