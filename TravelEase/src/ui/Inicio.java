package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpEmailLogin;
	private JPasswordField inpContraseniaLogin;
	private JPasswordField inpContraseniaRegistro;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 491, 470);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setToolTipText("Login");
		contentPane.add(tabbedPane);
		
		JPanel Login = new JPanel();
		tabbedPane.addTab("Login", null, Login, null);
		Login.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Iniciar Sesion");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_2.setBounds(116, 23, 221, 43);
		Login.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(116, 84, 130, 28);
		Login.add(lblNewLabel);
		
		inpEmailLogin = new JTextField();
		inpEmailLogin.setColumns(10);
		inpEmailLogin.setBounds(116, 109, 221, 28);
		Login.add(inpEmailLogin);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(116, 148, 130, 28);
		Login.add(lblPassword);
		
		inpContraseniaLogin = new JPasswordField();
		inpContraseniaLogin.setBounds(116, 173, 221, 28);
		Login.add(inpContraseniaLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogin.setBounds(116, 212, 221, 37);
		Login.add(btnLogin);
		
		JLabel lblNewUsuario = new JLabel( "¿No estás registrado? ¡Podés registrarte acá abajo!");
		lblNewUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewUsuario.setBounds(116, 283, 254, 14);
		Login.add(lblNewUsuario);
		
		// -------------------------
		//	Ventana REGISTRAR
		// -------------------------
		JButton btnVentanaRegistrar = new JButton("Registrar");
		btnVentanaRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnVentanaRegistrar.setBounds(116, 308, 221, 37);
		Login.add(btnVentanaRegistrar);
		
		JPanel Registrar = new JPanel();
		tabbedPane.addTab("Registrar", null, Registrar, null);
		Registrar.setLayout(null);
		
		JFormattedTextField inpNombreRegistro = new JFormattedTextField();
		inpNombreRegistro.setBounds(157, 82, 165, 29);
		Registrar.add(inpNombreRegistro);
		
		JFormattedTextField inpEmailRegistro = new JFormattedTextField();
		inpEmailRegistro.setBounds(157, 134, 165, 29);
		Registrar.add(inpEmailRegistro);
		
		JFormattedTextField inpDniRegistro = new JFormattedTextField();
		inpDniRegistro.setBounds(157, 194, 165, 29);
		Registrar.add(inpDniRegistro);
		
		inpContraseniaRegistro = new JPasswordField();
		inpContraseniaRegistro.setBounds(157, 255, 165, 29);
		Registrar.add(inpContraseniaRegistro);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(157, 57, 63, 14);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		Registrar.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(157, 117, 46, 14);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		Registrar.add(lblEmail);
		
		JLabel lblDni = new JLabel("Documento");
		lblDni.setBounds(157, 177, 80, 14);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		Registrar.add(lblDni);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(157, 238, 80, 14);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 14));
		Registrar.add(lblContrasena);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(165, 313, 150, 30);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 17));
		Registrar.add(btnRegistrarse);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(74, 292, 341, 20);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		Registrar.add(lblError);
		
		JLabel lblRegistrado = new JLabel("Ya estas registrado?");
		lblRegistrado.setBounds(74, 366, 341, 20);
		lblRegistrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrado.setForeground(new Color(0, 128, 0));
		lblRegistrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Registrar.add(lblRegistrado);
		
		JButton btnIniciarSesion = new JButton("Inicia Sesion");
		btnIniciarSesion.setBounds(169, 389, 147, 29);
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 17));
		Registrar.add(btnIniciarSesion);
		
		JLabel lblRegistrar = new JLabel("Registrar");
		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegistrar.setBounds(157, 0, 165, 29);
		Registrar.add(lblRegistrar);

	}
}
