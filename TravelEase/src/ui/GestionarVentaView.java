package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import bll.Usuario;
import dll.ControllerVenta;

public class GestionarVentaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public GestionarVentaView(Usuario usuario) {

        setTitle("Registrar Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // NAV BAR
        JPanel nav = new JPanel();
        nav.setBackground(new Color(197, 221, 255));
        nav.setBounds(0, 0, 636, 60);
        nav.setLayout(null);
        contentPane.add(nav);

        JLabel lblTitulo = new JLabel("Gestión de Ventas");
        lblTitulo.setFont(new Font("Gadugi", Font.PLAIN, 16));
        lblTitulo.setBounds(240, 10, 200, 40);
        nav.add(lblTitulo);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(520, 10, 90, 35);
        btnVolver.addActionListener(e -> {
            new OperarioMenu(usuario).setVisible(true);
            dispose();
        });
        nav.add(btnVolver);

        // PANEL GENERAL
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(null);
        panelCentro.setBounds(0, 60, 636, 390);
        contentPane.add(panelCentro);

        // -------------------------------
        //      TABBED PANE
        // -------------------------------
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, 636, 390);
        tabs.setBorder(new TitledBorder(""));
        panelCentro.add(tabs);

        // ============================================================
        //                TAB 1 — REGISTRAR VENTA VUELO
        // ============================================================
        JPanel tabVuelo = new JPanel(null);

        JLabel lblIdVuelo = new JLabel("ID del vuelo:");
        lblIdVuelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblIdVuelo.setBounds(40, 30, 120, 25);
        tabVuelo.add(lblIdVuelo);

        JTextField txtIdVuelo = new JTextField();
        txtIdVuelo.setBounds(160, 30, 120, 25);
        tabVuelo.add(txtIdVuelo);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCantidad.setBounds(40, 70, 120, 25);
        tabVuelo.add(lblCantidad);

        JSpinner spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spnCantidad.setBounds(160, 70, 60, 25);
        tabVuelo.add(spnCantidad);

        JButton btnGuardarVuelo = new JButton("Registrar venta vuelo");
        btnGuardarVuelo.setBackground(new Color(0, 153, 255));
        btnGuardarVuelo.setForeground(Color.white);
        btnGuardarVuelo.setBounds(180, 140, 220, 40);
        tabVuelo.add(btnGuardarVuelo);

        btnGuardarVuelo.addActionListener(e -> {

            try {
                int idVuelo = Integer.parseInt(txtIdVuelo.getText());
                int cantidad = (int) spnCantidad.getValue();

                boolean ok = ControllerVenta.registrarVentaVuelo(usuario.getId(), idVuelo, cantidad);

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Venta de vuelo registrada correctamente.");
                    txtIdVuelo.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la venta.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "El ID del vuelo debe ser un número válido.");
            }
        });

        tabs.addTab("Registrar Vuelo", tabVuelo);

        // ============================================================
        //                TAB 2 — REGISTRAR VENTA HOTEL
        // ============================================================
        JPanel tabHotel = new JPanel(null);

        JLabel lblIdHotel = new JLabel("ID del hotel:");
        lblIdHotel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblIdHotel.setBounds(40, 30, 120, 25);
        tabHotel.add(lblIdHotel);

        JTextField txtIdHotel = new JTextField();
        txtIdHotel.setBounds(160, 30, 120, 25);
        tabHotel.add(txtIdHotel);

        JLabel lblEntrada = new JLabel("Fecha entrada:");
        lblEntrada.setBounds(40, 80, 120, 25);
        tabHotel.add(lblEntrada);

        com.toedter.calendar.JDateChooser dateEntrada = new com.toedter.calendar.JDateChooser();
        dateEntrada.setBounds(160, 80, 150, 25);
        dateEntrada.setMinSelectableDate(new Date());
        tabHotel.add(dateEntrada);

        JLabel lblSalida = new JLabel("Fecha salida:");
        lblSalida.setBounds(40, 130, 120, 25);
        tabHotel.add(lblSalida);

        com.toedter.calendar.JDateChooser dateSalida = new com.toedter.calendar.JDateChooser();
        dateSalida.setBounds(160, 130, 150, 25);
        dateSalida.setEnabled(true);
        tabHotel.add(dateSalida);

        JButton btnGuardarHotel = new JButton("Registrar venta hotel");
        btnGuardarHotel.setBackground(new Color(0, 153, 102));
        btnGuardarHotel.setForeground(Color.white);
        btnGuardarHotel.setBounds(180, 200, 220, 40);
        tabHotel.add(btnGuardarHotel);

        btnGuardarHotel.addActionListener((ActionEvent e) -> {

            try {
                int idHotel = Integer.parseInt(txtIdHotel.getText());

                Date d1 = dateEntrada.getDate();
                Date d2 = dateSalida.getDate();

                if (d1 == null || d2 == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione ambas fechas.");
                    return;
                }

                LocalDate entrada = new java.sql.Date(d1.getTime()).toLocalDate();
                LocalDate salida = new java.sql.Date(d2.getTime()).toLocalDate();

                if (salida.isBefore(entrada)) {
                    JOptionPane.showMessageDialog(null, "La salida no puede ser antes de la entrada.");
                    return;
                }

                int noches = (int) java.time.temporal.ChronoUnit.DAYS.between(entrada, salida);

                boolean ok = ControllerVenta.registrarVentaHotel(
                        usuario.getId(),
                        idHotel,
                        entrada,
                        salida,
                        noches
                );

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Venta de hotel registrada correctamente.");
                    txtIdHotel.setText("");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        tabs.addTab("Registrar Hotel", tabHotel);
    }
}
