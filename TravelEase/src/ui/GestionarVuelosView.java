package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bll.Usuario;
import bll.Vuelo;
import dll.ControllerVuelo;

public class GestionarVuelosView extends JFrame {
	private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private Vuelo vueloSeleccionado;

    public GestionarVuelosView(Usuario usuario) {
    	
        setTitle("Gestionar Vuelos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{
                "ID", "Código", "Origen", "Destino",
                "Fecha Salida", "Fecha Llegada", "Aerolínea"
        }, 0);
        getContentPane().setLayout(null);

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 80, 840, 300);
        getContentPane().add(scroll);

        cargarTabla();

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 400, 120, 40);
        btnAgregar.addActionListener(e -> new AgregarVueloView(this).setVisible(true));
        getContentPane().add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(150, 400, 120, 40);
        btnEditar.addActionListener(e -> {
            if (vueloSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccioná un vuelo");
                return;
            }
            new EditarVueloView(this, vueloSeleccionado).setVisible(true);
        });
        getContentPane().add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(280, 400, 120, 40);
        btnEliminar.addActionListener(e -> eliminarVuelo());
        getContentPane().add(btnEliminar);

        JLabel titulo = new JLabel("Gestión de Vuelos - " + usuario.getNombre());
        titulo.setBounds(20, 20, 300, 30);
        titulo.setFont(new java.awt.Font("Gadugi", java.awt.Font.BOLD, 20));
        getContentPane().add(titulo);
        
        JButton btnMenu = new JButton("");
        btnMenu.setIcon(new ImageIcon(GestionarVuelosView.class.getResource("/img/home.png")));
        btnMenu.setBounds(771, 25, 89, 30);
        getContentPane().add(btnMenu);

        // seleccionar fila
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

    private void eliminarVuelo() {
        if (vueloSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo.");
            return;
        }

        int conf = JOptionPane.showConfirmDialog(
                null,
                "¿Eliminar vuelo seleccionado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (conf == JOptionPane.YES_OPTION) {
            if (ControllerVuelo.eliminarVuelo(vueloSeleccionado.getId())) {
                cargarTabla();
            }
        }
    }

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
                    v.getAerolinea()
            });
        }
    }
}
