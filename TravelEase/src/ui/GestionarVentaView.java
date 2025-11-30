package ui;

import java.awt.EventQueue;
import bll.Vuelo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.Hotel;
import bll.Usuario;
import dll.ControllerHotel;
import dll.ControllerVuelo;

import java.awt.SystemColor;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

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

       
        //Registrar Venta de Hotel
   
        JPanel panelRegHotel = new JPanel();
        panelRegHotel.setLayout(null);
        tabbedPane.addTab("Registrar Hotel", null, panelRegHotel, null);

        // DNI Cliente
        JLabel lblClienteH = new JLabel("DNI Cliente:");
        lblClienteH.setFont(new Font("Gadugi", Font.PLAIN, 11));
        lblClienteH.setBounds(20, 20, 150, 25);
        panelRegHotel.add(lblClienteH);

        JTextField txtDniHotel = new JTextField();
        txtDniHotel.setFont(new Font("Gadugi", Font.PLAIN, 11));
        txtDniHotel.setBounds(140, 20, 150, 25);
        panelRegHotel.add(txtDniHotel);

        // HOTEL
        JLabel lblHotel = new JLabel("Hotel:");
        lblHotel.setFont(new Font("Gadugi", Font.PLAIN, 11));
        lblHotel.setBounds(20, 60, 150, 25);
        panelRegHotel.add(lblHotel);

        JComboBox<String> comboHoteles = new JComboBox<>();
        comboHoteles.setFont(new Font("Gadugi", Font.PLAIN, 11));
        comboHoteles.setBounds(140, 60, 300, 25);
        panelRegHotel.add(comboHoteles);

        // Mostrar info del hotel
        JTextArea txtInfoHotel = new JTextArea();
        txtInfoHotel.setFont(new Font("Gadugi", Font.PLAIN, 13));
        txtInfoHotel.setEditable(false);
        txtInfoHotel.setBorder(BorderFactory.createTitledBorder("Información del hotel"));
        txtInfoHotel.setBounds(20, 100, 580, 110);
        panelRegHotel.add(txtInfoHotel);

        // Cargar hoteles desde la BD
        LinkedList<Hotel> listaHoteles = ControllerHotel.listarHoteles();
        for (Hotel h : listaHoteles) {
            comboHoteles.addItem(h.getId() + " - " + h.getNombre());
        }

        // Mostrar datos al elegir un hotel
        comboHoteles.addActionListener(e -> {
            int idx = comboHoteles.getSelectedIndex();
            if (idx >= 0) {
                Hotel h = listaHoteles.get(idx);
                txtInfoHotel.setText(
                        "ID: " + h.getId() +
                        "\nNombre: " + h.getNombre() +
                        "\nDestino: " + h.getDestino() +
                        "\nHabitaciones disponibles: " + h.getHabitacionesDisponibles()
                );
            }
        });

        // Fecha de entrada
        JLabel lblEntrada = new JLabel("Entrada:");
        lblEntrada.setFont(new Font("Gadugi", Font.PLAIN, 11));
        lblEntrada.setBounds(20, 230, 150, 25);
        panelRegHotel.add(lblEntrada);

        JDateChooser fechaEntrada = new JDateChooser();
        fechaEntrada.setBounds(140, 230, 150, 25);
        panelRegHotel.add(fechaEntrada);

        // Fecha de salida
        JLabel lblSalida = new JLabel("Salida:");
        lblSalida.setFont(new Font("Gadugi", Font.PLAIN, 11));
        lblSalida.setBounds(20, 270, 150, 25);
        panelRegHotel.add(lblSalida);

        JDateChooser fechaSalida = new JDateChooser();
        fechaSalida.setBounds(140, 270, 150, 25);
        panelRegHotel.add(fechaSalida);

        // Calcular noches
        JLabel lblNoches = new JLabel("Noches: 0");
        lblNoches.setFont(new Font("Gadugi", Font.BOLD, 15));
        lblNoches.setBounds(350, 250, 150, 30);
        panelRegHotel.add(lblNoches);

        // Cálculo automático de noches
        fechaSalida.addPropertyChangeListener(evt -> {
            try {
                Date in = fechaEntrada.getDate();
                Date out = fechaSalida.getDate();

                if (in != null && out != null) {
                    long diff = out.getTime() - in.getTime();
                    int noches = (int)(diff / (1000 * 60 * 60 * 24));
                    lblNoches.setText("Noches: " + (noches > 0 ? noches : 0));
                }
            } catch (Exception ex) {}
        });

        // Botón registrar
        JButton btnRegistrarHotel = new JButton("Registrar Venta");
        btnRegistrarHotel.setBounds(223, 306, 180, 40);
        panelRegHotel.add(btnRegistrarHotel);

        btnRegistrarHotel.addActionListener(e -> {
            if (txtDniHotel.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese DNI del cliente");
                return;
            }
            if (comboHoteles.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un hotel");
                return;
            }
            if (fechaEntrada.getDate() == null || fechaSalida.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione fechas válidas");
                return;
            }

            JOptionPane.showMessageDialog(null, "Venta de hotel registrada (falta guardar en BD)");
        });
}
}
