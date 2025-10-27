package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField inpPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistrar frame = new VentanaRegistrar();
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
	public VentanaRegistrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField inpNombre = new JFormattedTextField();
		inpNombre.setBounds(93, 89, 165, 29);
		contentPane.add(inpNombre);
		
		JFormattedTextField inpEmail = new JFormattedTextField();
		inpEmail.setBounds(93, 149, 165, 29);
		contentPane.add(inpEmail);
		
		JFormattedTextField Dni = new JFormattedTextField();
		Dni.setBounds(93, 209, 165, 29);
		contentPane.add(Dni);
		
		inpPassword = new JPasswordField();
		inpPassword.setBounds(93, 270, 165, 29);
		contentPane.add(inpPassword);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegistro.setBounds(0, 11, 361, 34);
		contentPane.add(lblRegistro);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(93, 72, 63, 14);
		contentPane.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(93, 132, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblDni = new JLabel("Documento");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDni.setBounds(93, 192, 80, 14);
		contentPane.add(lblDni);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasena.setBounds(93, 253, 80, 14);
		contentPane.add(lblContrasena);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRegistrarse.setBounds(101, 350, 150, 30);
		contentPane.add(btnRegistrarse);
		
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setBounds(10, 310, 341, 20);
		contentPane.add(lblError);
		
		JLabel lblRegistrado = new JLabel("Ya estas registrado?");
		lblRegistrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistrado.setForeground(new Color(0, 128, 0));
		lblRegistrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrado.setBounds(10, 403, 341, 20);
		contentPane.add(lblRegistrado);
		
		JButton btnNewButton = new JButton("Inicia Sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(105, 426, 147, 29);
		contentPane.add(btnNewButton);

	}
}
