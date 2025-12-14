package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bll.Usuario;

import java.awt.*;

import dll.ControllerReserva;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarReservaView extends JFrame {

    private JTable tableVuelos;
    private JTable tableHoteles;

    private DefaultTableModel modelVuelos;
    private DefaultTableModel modelHoteles;

    public GestionarReservaView(Usuario usuario) {

        setTitle("Gestionar Reservas");
        setSize(900, 537);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblGestionarReserva = new JLabel("Gestionar Reserva - " + usuario.getNombre());
        lblGestionarReserva.setForeground(new Color(25, 25, 112));
        lblGestionarReserva.setFont(new Font("Gadugi", Font.BOLD, 18));
        lblGestionarReserva.setBounds(20, 11, 276, 35);
        getContentPane().add(lblGestionarReserva);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Gadugi", Font.PLAIN, 11));
        tabs.setBounds(20, 57, 860, 380);
        getContentPane().add(tabs);

        //TAB VUELOS
        modelVuelos = new DefaultTableModel(
            new String[]{"ID", "Cliente", "Vuelo", "Fecha venta", "Cantidad"}, 0
        );
        tableVuelos = new JTable(modelVuelos);
        tabs.addTab("Reservas Vuelos", new JScrollPane(tableVuelos));

        //TAB HOTELES
        modelHoteles = new DefaultTableModel(
            new String[]{"ID", "Cliente", "Hotel", "Entrada", "Salida", "Noches"}, 0
        );
        tableHoteles = new JTable(modelHoteles);
        tabs.addTab("Reservas Hoteles", new JScrollPane(tableHoteles));

        // BOTONES
        JButton btnEliminar = new JButton("Eliminar reserva");
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEliminar.setIcon(new ImageIcon(GestionarReservaView.class.getResource("/img/eliminar.png")));
        btnEliminar.setBounds(358, 448, 168, 35);
        getContentPane().add(btnEliminar);

        JButton btnVolver = new JButton("");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminMenu(usuario).setVisible(true);
        		dispose();
        	}
        });
        btnVolver.setIcon(new ImageIcon(GestionarReservaView.class.getResource("/img/home.png")));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setBounds(793, 11, 77, 35);
        getContentPane().add(btnVolver);
        

        

        btnEliminar.addActionListener(e -> eliminarReserva(tabs.getSelectedIndex()));

        cargarReservas();
    }

    private void cargarReservas() {
        cargarReservasVuelos();
        cargarReservasHoteles();
    }

    private void cargarReservasVuelos() {
        modelVuelos.setRowCount(0);
        for (Object[] fila : ControllerReserva.listarReservasVuelo()) {
            modelVuelos.addRow(fila);
        }
    }

    private void cargarReservasHoteles() {
        modelHoteles.setRowCount(0);
        for (Object[] fila : ControllerReserva.listarReservasHotel()) {
            modelHoteles.addRow(fila);
        }
    }

    private void eliminarReserva(int tab) {

        if (tab == 0) { // VUELOS
            int row = tableVuelos.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una reserva de vuelo");
                return;
            }

            int id = (int) modelVuelos.getValueAt(row, 0);
            ControllerReserva.eliminarReservaVuelo(id);
            cargarReservasVuelos();

        } else { // HOTELES
            int row = tableHoteles.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una reserva de hotel");
                return;
            }

            int id = (int) modelHoteles.getValueAt(row, 0);
            ControllerReserva.eliminarReservaHotel(id);
            cargarReservasHoteles();
        }
    }
}
