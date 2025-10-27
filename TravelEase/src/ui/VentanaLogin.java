package ui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bll.Usuario;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpEmail;
	private JPasswordField inpPassword;
	private JButton btnLogin;
	private JButton btnVentanaRegistrar;
	private JLabel lblNewUsuario;
	private JLabel lblNewLabel_2;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(67, 83, 130, 28);
		contentPane.add(lblNewLabel);
		
		inpEmail = new JTextField();
		inpEmail.setBounds(67, 115, 221, 28);
		contentPane.add(inpEmail);
		inpEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(67, 143, 130, 28);
		contentPane.add(lblPassword);
		
		inpPassword = new JPasswordField();
		inpPassword.setBounds(67, 175, 221, 28);
		contentPane.add(inpPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario logueado = Usuario.login(inpEmail.getText(), inpPassword.getText());
				if (logueado == null) {
					lblError.setText("No se encontró el usuario");}
					else { 
						 	HomeUsuario.run(logueado);
							
							dispose();
						}
					}	
			}
		);
		btnLogin.setBounds(67, 218, 221, 37);
		contentPane.add(btnLogin);
		
		btnVentanaRegistrar = new JButton("Registrar");
		btnVentanaRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistrar registro = new VentanaRegistrar(); //Sigue en proceso
				registro.setVisible(true);
				dispose();
			}
		});
		btnVentanaRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnVentanaRegistrar.setBounds(67, 314, 221, 37);
		contentPane.add(btnVentanaRegistrar);
		
		lblNewUsuario = new JLabel("No estas registrado? Podes registrar aca abajo! ");
		lblNewUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewUsuario.setBounds(67, 289, 232, 14);
		contentPane.add(lblNewUsuario);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(67, 262, 221, 14);
		contentPane.add(lblError);
		lblNewLabel_2 = new JLabel("Iniciar Sesion");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_2.setBounds(67, 29, 221, 43);
		contentPane.add(lblNewLabel_2);
		
		

	}
}
