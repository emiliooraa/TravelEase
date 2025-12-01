package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bll.Vuelo;
import dll.ControllerVuelo;

public class GestionarVuelosView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private Vuelo vueloSeleccionado;

    public GestionarVuelosView() {

        setTitle("Gestionar Vuelos");
        setSize(850, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{
            "ID", "Origen", "Destino", "Fecha", "Horario",
            "Capacidad", "Disponibles"
        }, 0);

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 73, 800, 300);
        getContentPane().add(scroll);

        cargarTabla();

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 384, 120, 40);
        getContentPane().add(btnAgregar);

        btnAgregar.addActionListener(e -> {
            new AgregarVueloView(this).setVisible(true);
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(150, 384, 120, 40);
        getContentPane().add(btnEditar);

        btnEditar.addActionListener(e -> {
            if (vueloSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccioná un vuelo");
                return;
            }

            EditarVueloView ventana = new EditarVueloView(this, vueloSeleccionado);
            ventana.setVisible(true);
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(280, 384, 120, 40);
        getContentPane().add(btnEliminar);
        
        JLabel lblTituloGestion = new JLabel("Gestion Reserva de Vuelos -");
        lblTituloGestion.setBounds(20, 11, 272, 45);
        getContentPane().add(lblTituloGestion);

        btnEliminar.addActionListener(e -> {
            if (vueloSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un vuelo.");
                return;
            }

            int conf = JOptionPane.showConfirmDialog(
                null,
                "¿Seguro que desea eliminar este vuelo?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (conf == JOptionPane.YES_OPTION) {
                boolean ok = ControllerVuelo.eliminarVuelo(vueloSeleccionado.getId());
                if (ok) {
                    cargarTabla();
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int id = (int) model.getValueAt(row, 0);
                    vueloSeleccionado = ControllerVuelo.buscarVueloPorId(id);
                }
            }
        });
    }

    public void cargarTabla() {
        model.setRowCount(0);

        for (Vuelo v : ControllerVuelo.listarVuelos()) {
            model.addRow(new Object[]{
                v.getId(),
                v.getOrigen(),
                v.getDestino(),
                v.getFecha(),
                v.getHorario(),
                v.getCapacidad(),
                v.getAsientosDisponibles()
            });
        }
    }
}
