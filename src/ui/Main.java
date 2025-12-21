package ui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Bienvenido a TravelEase");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 428);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(197, 221, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(Main.class.getResource("/img/logoInicio.png")));
		lblImagen.setBounds(64, 50, 250, 250);
		contentPane.add(lblImagen);
		
//		JLabel lblBienvenida = new JLabel("Bienvenido a TravelEase");
//		lblBienvenida.setForeground(new Color(0, 0, 102));
//		lblBienvenida.setFont(new Font("Gadugi", Font.BOLD, 19));
//		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
//		lblBienvenida.setBounds(-11, 11, 400, 39);
//		contentPane.add(lblBienvenida);
		
		JButton btnNewButton = new JButton("Iniciar Sesión");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(12, 39, 78));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio login = new Inicio();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Gadugi", Font.PLAIN, 15));
		btnNewButton.setBounds(81, 311, 216, 39);
		contentPane.add(btnNewButton);
		
	}
	@SuppressWarnings("unused")
	private void setImageLabel(JLabel label, String path) {
	    ImageIcon original = new ImageIcon(Main.class.getResource(path));
	    Image scaled = original.getImage().getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
	    label.setIcon(new ImageIcon(scaled));
	}
}
