package ui;

import dll.ControllerHotel;
import dll.ControllerReserva;
import bll.Hotel;
import bll.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaBuscarHoteles extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblError;
    private JTable tablaHoteles;
    private DefaultTableModel modelo;

    public VentanaBuscarHoteles(Usuario usuario) {
        setTitle("Buscar hoteles - Cliente: " + usuario.getNombre());
        setSize(660, 495);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Ciudad", "Pais", "⭐"}, 0);
        tablaHoteles = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaHoteles);
        scroll.setBounds(10, 63, 624, 260);
        getContentPane().add(scroll);

        JLabel lblHoteles = new JLabel("Hoteles");
        lblHoteles.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblHoteles.setHorizontalAlignment(SwingConstants.CENTER);
        lblHoteles.setBounds(270, 25, 140, 25);
        getContentPane().add(lblHoteles);

        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setBounds(10, 330, 624, 20);
        getContentPane().add(lblError);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 422, 89, 23);
        getContentPane().add(btnVolver);
        btnVolver.addActionListener(e -> {
            new ClienteMenu(usuario).setVisible(true);
            dispose();
        });

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setBackground(Color.GREEN);
        btnComprar.setBounds(270, 370, 120, 40);
        getContentPane().add(btnComprar);
        
        btnComprar.addActionListener(e -> {
            lblError.setText("");
            int fila = tablaHoteles.getSelectedRow();
            if (fila == -1) {
                lblError.setText("Debes seleccionar un hotel.");
                return;
            }
            int idHotel = (int) modelo.getValueAt(fila, 0);
            boolean ok = ControllerReserva.reservarHotel(usuario.getId(), idHotel);
            if (!ok) {
                lblError.setText("No se pudo completar la compra del hotel.");
                return;
            }
            JLabel lblExito = new JLabel("");
            lblExito.setHorizontalAlignment(SwingConstants.CENTER);
            lblExito.setForeground(new Color(0, 255, 0));
            lblExito.setBounds(185, 345, 289, 14);
            getContentPane().add(lblExito);
            lblExito.setText("Reserva de hotel realizada con éxito.");
        });

        cargarHoteles();
    }

    private void cargarHoteles() {
        modelo.setRowCount(0);
        try {
            List<Hotel> hoteles = ControllerHotel.listarHotel();
            for (Hotel h : hoteles) {
                modelo.addRow(new Object[]{
                    h.getId(),
                    h.getNombre(),
                    h.getCiudad(),
                    h.getPais(),
                    h.getEstrellas()                    
                });
            }
        } catch (Exception e) {
            lblError.setText("Error cargando hoteles: " + e.getMessage());
        }
    }
}
