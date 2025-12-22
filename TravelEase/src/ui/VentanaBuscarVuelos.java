package ui;

import dll.ControllerVuelo;
import dll.ControllerReserva;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bll.Usuario;
import bll.Vuelo;

import java.awt.Font;
import java.awt.Color;

public class VentanaBuscarVuelos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblError;
    private JTable tablaVuelos;
    private DefaultTableModel modelo;

    public VentanaBuscarVuelos(Usuario usuario) {
        setTitle("Buscar Vuelos");
        setSize(660, 495);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Modelo de tabla
        modelo = new DefaultTableModel(
            new String[]{"ID", "Origen", "Destino", "Fecha"}, 0
        );

        // Tabla
        tablaVuelos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaVuelos);
        scroll.setBounds(10, 63, 624, 260);
        getContentPane().add(scroll);

        // Título
        JLabel lblVuelos = new JLabel("Vuelos disponibles");
        lblVuelos.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblVuelos.setHorizontalAlignment(SwingConstants.CENTER);
        lblVuelos.setBounds(216, 25, 194, 35);
        getContentPane().add(lblVuelos);

        // Label error
        lblError = new JLabel("");
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(50, 322, 560, 14);
        getContentPane().add(lblError);

        // Botón Volver
        JButton btnVolverCBV = new JButton("Volver");
        btnVolverCBV.setBounds(10, 422, 89, 23);
        getContentPane().add(btnVolverCBV);
        btnVolverCBV.addActionListener(e -> {
            new ClienteMenu(usuario).setVisible(true);
            dispose();
        });

        // Botón Comprar vuelo
        JButton btnComprarVuelo = new JButton("Comprar vuelo");
        btnComprarVuelo.setBackground(new Color(0, 255, 0));
        btnComprarVuelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnComprarVuelo.setBounds(248, 360, 128, 35);
        getContentPane().add(btnComprarVuelo);
        btnComprarVuelo.addActionListener(e -> {
            lblError.setText("");

            int fila = tablaVuelos.getSelectedRow();
            if (fila == -1) {
                lblError.setText("Debes seleccionar un vuelo.");
                return;
            }

            int idVuelo = (int) modelo.getValueAt(fila, 0);
            boolean ok = ControllerReserva.reservarVuelo(usuario.getId(), idVuelo);

            if (!ok) {
                lblError.setText("No se pudo completar la compra del vuelo.");
                return;
            }
            
            // Mensaje de éxito
            JLabel lblExito = new JLabel("");
            lblExito.setHorizontalAlignment(SwingConstants.CENTER);
            lblExito.setForeground(new Color(0, 255, 0));
            lblExito.setBounds(108, 335, 422, 14);
            getContentPane().add(lblExito);
            lblExito.setText("Reserva de hotel realizada con éxito.");
        });


        cargarVuelos();
    }

    private void cargarVuelos() {
        modelo.setRowCount(0);
        for (Vuelo v : ControllerVuelo.listarVuelos()) {
            modelo.addRow(new Object[]{
                v.getId(),
                v.getOrigen(),
                v.getDestino(),
                v.getFechaSalida()
            });
        }
    }
}
