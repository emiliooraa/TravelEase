package ui;

import java.awt.EventQueue;
import bll.Vuelo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.Usuario;
import dll.ControllerVuelo;

import java.awt.SystemColor;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class GestionarVentaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public GestionarVentaView(Usuario usuario) {
		setTitle("Gestionar venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelNav = new JPanel();
		panelNav.setBackground(new Color(197, 221, 255));
		panelNav.setBounds(0, 0, 636, 63);
		contentPane.add(panelNav);
		panelNav.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu de Gestionar Venta");
		lblNewLabel.setFont(new Font("Gadugi", Font.PLAIN, 15));
		lblNewLabel.setBounds(214, 11, 207, 41);
		panelNav.add(lblNewLabel);
		
		JButton btnMenu = new JButton("Volver");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OperarioMenu(usuario).setVisible(true);
				dispose();
			}
		});
		btnMenu.setFont(new Font("Gadugi", Font.PLAIN, 13));
		btnMenu.setBounds(537, 11, 89, 41);
		panelNav.add(btnMenu);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 63, 636, 389);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.setBounds(0, 0, 636, 389);
		panelMenu.add(tabbedPane);
		
		JPanel panelRegVuelo = new JPanel();
        panelRegVuelo.setLayout(null);
        tabbedPane.addTab("Registrar Vuelo", null, panelRegVuelo, null);

        JLabel lblDni = new JLabel("DNI del Cliente:");
        lblDni.setFont(new Font("Gadugi", Font.PLAIN, 11));
        lblDni.setBounds(30, 20, 150, 25);
        panelRegVuelo.add(lblDni);

        JTextField txtDni = new JTextField();
        txtDni.setFont(new Font("Gadugi", Font.PLAIN, 11));
        txtDni.setBounds(150, 20, 150, 25);
        panelRegVuelo.add(txtDni);

        JLabel lblVuelo = new JLabel("Seleccionar Vuelo:");
        lblVuelo.setFont(new Font("Gadugi", Font.PLAIN, 11));
        lblVuelo.setBounds(30, 60, 150, 25);
        panelRegVuelo.add(lblVuelo);

        JComboBox<String> comboVuelos = new JComboBox<>();
        comboVuelos.setFont(new Font("Gadugi", Font.PLAIN, 11));
        comboVuelos.setBounds(150, 60, 300, 25);
        panelRegVuelo.add(comboVuelos);

        // Cargar vuelos desde BD
        LinkedList<Vuelo> listaVuelos = ControllerVuelo.listarVuelos();
        for (Vuelo v : listaVuelos) {
            comboVuelos.addItem(v.getId() + " - " + v.getOrigen() + " → " + v.getDestino());
        }

        JTextArea infoVuelo = new JTextArea();
        infoVuelo.setFont(new Font("Gadugi", Font.PLAIN, 13));
        infoVuelo.setEditable(false);
        infoVuelo.setBorder(new TitledBorder("Información del vuelo"));
        infoVuelo.setBounds(30, 110, 551, 148);
        panelRegVuelo.add(infoVuelo);

        comboVuelos.addActionListener(e -> {
            int idx = comboVuelos.getSelectedIndex();
            if (idx >= 0) {
                Vuelo v = listaVuelos.get(idx);
                infoVuelo.setText(
                    "ID: " + v.getId() +
                    "\nOrigen: " + v.getOrigen() +
                    "\nDestino: " + v.getDestino() +
                    "\nFecha: " + v.getFecha() +
                    "\nHorario: " + v.getHorario() +
                    "\nCapacidad: " + v.getCapacidad() +
                    "\nDisponibles: " + v.getAsientosDisponibles()
                );
            }
        });

        JButton btnRegistrarVuelo = new JButton("Registrar Venta");
        btnRegistrarVuelo.setBounds(200, 280, 180, 40);
        panelRegVuelo.add(btnRegistrarVuelo);

        btnRegistrarVuelo.addActionListener(e -> {
            if (txtDni.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese DNI del cliente");
                return;
            }
            if (comboVuelos.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
                return;
            }

            JOptionPane.showMessageDialog(null, "Venta registrada (lógica pendiente).");
        });

        //-----------------------------------
        //  TAB 2 → Registrar Venta de Hotel
        //-----------------------------------
        JPanel panelRegHotel = new JPanel();
        panelRegHotel.setLayout(null);
        tabbedPane.addTab("Registrar Hotel", null, panelRegHotel, null);

        JLabel lblHotel = new JLabel("Registrar hotel (plantilla)");
        lblHotel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblHotel.setBounds(200, 160, 250, 30);
        panelRegHotel.add(lblHotel);

    }
}
