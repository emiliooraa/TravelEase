package ui;

import bll.Paquete;
import bll.Vuelo;
import bll.Hotel;
import bll.Usuario;

import dll.ControllerPaquete;
import dll.ControllerVuelo;
import dll.ControllerHotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarPaqueteView extends JFrame {

    private JComboBox<Vuelo> cmbVuelos;
    private JComboBox<Hotel> cmbHoteles;
    private JTable tblPaquetes;

    private int paqueteSeleccionadoId = -1;

    public GestionarPaqueteView(Usuario usuario) {

        setTitle("TravelEase - Gestión de Paquetes");
        setSize(760, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        //TÍTULO
        JLabel lblTitulo = new JLabel("Gestionar Paquetes - " + usuario.getNombre());
        lblTitulo.setForeground(new Color(25, 25, 112));
        lblTitulo.setFont(new Font("Gadugi", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 420, 35);
        panel.add(lblTitulo);

        //VUELOS
        JLabel lblVuelo = new JLabel("Vuelo:");
        lblVuelo.setBounds(30, 80, 100, 25);
        panel.add(lblVuelo);

        cmbVuelos = new JComboBox<>();
        cmbVuelos.setBackground(Color.WHITE);
        cmbVuelos.setBounds(130, 80, 250, 25);
        panel.add(cmbVuelos);

        //HOTELES
        JLabel lblHotel = new JLabel("Hotel:");
        lblHotel.setBounds(30, 120, 100, 25);
        panel.add(lblHotel);

        cmbHoteles = new JComboBox<>();
        cmbHoteles.setBackground(Color.WHITE);
        cmbHoteles.setBounds(130, 120, 250, 25);
        panel.add(cmbHoteles);

        //BOTONES
        JButton btnCrear = new JButton("Crear Paquete");
        btnCrear.setIcon(new ImageIcon(GestionarPaqueteView.class.getResource("/img/agregar.png")));
        btnCrear.setBackground(Color.WHITE);
        btnCrear.setBounds(420, 80, 150, 30);
        panel.add(btnCrear);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setIcon(new ImageIcon(GestionarPaqueteView.class.getResource("/img/eliminar.png")));
        btnEliminar.setBackground(Color.WHITE);
        btnEliminar.setBounds(420, 120, 150, 30);
        panel.add(btnEliminar);

        JButton btnVolver = new JButton("");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminMenu(usuario).setVisible(true);
        		dispose();
        	}
        });
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setIcon(new ImageIcon(GestionarPaqueteView.class.getResource("/img/home.png")));
        btnVolver.setBounds(665, 20, 65, 35);
        panel.add(btnVolver);
        
        // 🔹 TABLA
        tblPaquetes = new JTable();
        tblPaquetes.setModel(new DefaultTableModel(
                new Object[]{"ID", "Vuelo", "Hotel"}, 0
        ));

        JScrollPane scroll = new JScrollPane(tblPaquetes);
        scroll.setBounds(30, 180, 690, 260);
        panel.add(scroll);

        setContentPane(panel);
        
      

        //ACCIONES
        btnCrear.addActionListener(e -> crearPaquete());
        btnEliminar.addActionListener(e -> eliminarPaquete());

        tblPaquetes.getSelectionModel().addListSelectionListener(e -> {
            int fila = tblPaquetes.getSelectedRow();
            if (fila >= 0) {
                paqueteSeleccionadoId = (int) tblPaquetes.getValueAt(fila, 0);
            }
        });

        cargarCombos();
        cargarTabla();
    }

    //MÉTODOS

    private void cargarCombos() {

        cmbVuelos.removeAllItems();
        for (Vuelo v : ControllerVuelo.listarVuelos()) {
            cmbVuelos.addItem(v);
        }

        cmbHoteles.removeAllItems();
        for (Hotel h : ControllerHotel.listarHoteles()) {
            cmbHoteles.addItem(h);
        }
    }

    private void crearPaquete() {

        Vuelo vuelo = (Vuelo) cmbVuelos.getSelectedItem();
        Hotel hotel = (Hotel) cmbHoteles.getSelectedItem();

        if (vuelo == null || hotel == null) {
            JOptionPane.showMessageDialog(this, "Seleccione vuelo y hotel");
            return;
        }

        if (ControllerPaquete.crearPaquete(vuelo.getId(), hotel.getId())) {
            JOptionPane.showMessageDialog(this, "Paquete creado correctamente");
            cargarTabla();
        }
    }

    private void eliminarPaquete() {

        if (paqueteSeleccionadoId == -1) return;

        int resp = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el paquete?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (resp == JOptionPane.YES_OPTION) {
            if (ControllerPaquete.eliminarPaquete(paqueteSeleccionadoId)) {
                JOptionPane.showMessageDialog(this, "Paquete eliminado");
                cargarTabla();
                paqueteSeleccionadoId = -1;
            }
        }
    }

    private void cargarTabla() {

        DefaultTableModel model = (DefaultTableModel) tblPaquetes.getModel();
        model.setRowCount(0);

        for (Paquete p : ControllerPaquete.listarPaquetes()) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getVuelo(),
                    p.getHotel()
            });
        }
    }
}
