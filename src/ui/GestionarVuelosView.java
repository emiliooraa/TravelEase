package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bll.Usuario;
import bll.Vuelo;
import dll.ControllerVuelo;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarVuelosView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;
    private Vuelo vueloSeleccionado = null;

    public GestionarVuelosView(Usuario usuario) {

        setTitle("Gestionar Vuelos");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel titulo = new JLabel("Gestión de Vuelos - " + usuario.getNombre());
        titulo.setBounds(20, 20, 400, 30);
        titulo.setFont(new Font("Gadugi", Font.BOLD, 20));
        getContentPane().add(titulo);

        // BOTÓN VOLVER
        JButton btnMenu = new JButton("");
        btnMenu.setIcon(new ImageIcon(GestionarVuelosView.class.getResource("/img/home.png")));
        btnMenu.setBackground(new Color(240, 255, 255));
        btnMenu.setBounds(830, 20, 90, 35);
        btnMenu.addActionListener(e -> {
            new AdminMenu(usuario).setVisible(true);
            dispose();
        });
        getContentPane().add(btnMenu);

        // TABLA
        model = new DefaultTableModel(
                new String[]{
                        "ID", "Código", "Origen", "Destino",
                        "Salida", "Llegada", "Aerolínea",
                        "Capacidad", "Asientos disp."
                }, 0
        );

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 80, 900, 300);
        getContentPane().add(scroll);

        cargarTabla();

        // BOTÓN AGREGAR
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 400, 120, 40);
        btnAgregar.setIcon(new ImageIcon(GestionarVuelosView.class.getResource("/img/agregar.png")));
        btnAgregar.setBackground(new Color(152, 251, 152));
        btnAgregar.setFont(new Font("Gadugi", Font.BOLD, 12));
        btnAgregar.addActionListener(e -> new AgregarVueloView(this).setVisible(true));
        getContentPane().add(btnAgregar);

        // BOTÓN EDITAR
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(150, 400, 120, 40);
        btnEditar.setIcon(new ImageIcon(GestionarVuelosView.class.getResource("/img/boton-editar.png")));
        btnEditar.setBackground(new Color(30, 144, 255));
        btnEditar.setFont(new Font("Gadugi", Font.BOLD, 12));

        btnEditar.addActionListener(e -> {
            if (vueloSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccioná un vuelo.");
                return;
            }
            new EditarVueloView(this, vueloSeleccionado).setVisible(true);
        });

        getContentPane().add(btnEditar);

        // BOTÓN ELIMINAR
 
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(280, 400, 120, 40);
        btnEliminar.setIcon(new ImageIcon(GestionarVuelosView.class.getResource("/img/eliminar.png")));
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setFont(new Font("Gadugi", Font.BOLD, 12));

        btnEliminar.addActionListener(e -> eliminarVuelo());
        getContentPane().add(btnEliminar);
 
        // Selección de fila
    
        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    vueloSeleccionado = null;
                    return;
                }

                int id = (int) model.getValueAt(row, 0);
                vueloSeleccionado = ControllerVuelo.buscarVueloPorId(id);
            }
        });
    }

    // CARGAR TABLA
    public void cargarTabla() {

        model.setRowCount(0);

        for (Vuelo v : ControllerVuelo.listarVuelos()) {
            model.addRow(new Object[]{
                    v.getId(),
                    v.getCodigo(),
                    v.getOrigen(),
                    v.getDestino(),
                    v.getFechaSalida(),
                    v.getFechaLlegada(),
                    v.getAerolinea(),
                    v.getCapacidadTotal(),      
                    v.getAsientosDisponibles()  
            });
        }

        vueloSeleccionado = null;
        table.clearSelection();
    }

    // ELIMINAR
    private void eliminarVuelo() {

        if (vueloSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo.");
            return;
        }

        int conf = JOptionPane.showConfirmDialog(
                null,
                "¿Eliminar vuelo: " + vueloSeleccionado.getCodigo()
                        + " | Aerolínea: " + vueloSeleccionado.getAerolinea() + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (conf == JOptionPane.YES_OPTION) {

            if (ControllerVuelo.eliminarVuelo(vueloSeleccionado.getId())) {
                cargarTabla();
                JOptionPane.showMessageDialog(null, "Vuelo eliminado.");
            }
        }
    }
}
