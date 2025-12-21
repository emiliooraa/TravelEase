package ui;

import bll.Usuario;
import dll.ControllerReserva;

import javax.swing.*;
import java.awt.*;

public class AsignarAsientoView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtIdReserva;
    private JTextField txtAsiento;
    private JLabel lblInfo;
    private Usuario usuario;

    public AsignarAsientoView(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
    }

    private void initComponents() {
        setTitle("Asignar Asiento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 330);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Asignar Asiento");
        lblTitulo.setFont(new Font("Gadugi", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(120, 20, 250, 30);
        contentPane.add(lblTitulo);

        JLabel lblReserva = new JLabel("ID Reserva:");
        lblReserva.setBounds(80, 80, 100, 25);
        contentPane.add(lblReserva);

        txtIdReserva = new JTextField();
        txtIdReserva.setBounds(180, 80, 200, 25);
        contentPane.add(txtIdReserva);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(180, 115, 200, 30);
        contentPane.add(btnBuscar);

        lblInfo = new JLabel("");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBounds(40, 155, 400, 25);
        contentPane.add(lblInfo);

        JLabel lblAsiento = new JLabel("Asiento:");
        lblAsiento.setBounds(80, 195, 100, 25);
        contentPane.add(lblAsiento);

        txtAsiento = new JTextField();
        txtAsiento.setBounds(180, 195, 200, 25);
        contentPane.add(txtAsiento);

        JButton btnAsignar = new JButton("Asignar Asiento");
        btnAsignar.setBounds(180, 235, 200, 35);
        contentPane.add(btnAsignar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 10, 80, 25);
        contentPane.add(btnVolver);

        btnBuscar.addActionListener(e -> buscarReserva());
        btnAsignar.addActionListener(e -> asignarAsiento());
        btnVolver.addActionListener(e -> volver());
    }

    private void buscarReserva() {
        try {
            int id = Integer.parseInt(txtIdReserva.getText());
            Object[] reserva = ControllerReserva.buscarReservaVueloPorId(id);

            if (reserva != null) {
                lblInfo.setText("Cliente: " + reserva[1] + " | Vuelo: " + reserva[2]);
            } else {
                JOptionPane.showMessageDialog(this, "Reserva no encontrada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    private void asignarAsiento() {
        try {
            int id = Integer.parseInt(txtIdReserva.getText());
            String asiento = txtAsiento.getText();

            if (asiento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un asiento");
                return;
            }

            ControllerReserva.asignarAsiento(id, asiento);
            JOptionPane.showMessageDialog(this, "Asiento asignado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al asignar asiento");
        }
    }

    private void volver() {
        new OperarioMenu(usuario).setVisible(true);
        dispose();
    }
}
