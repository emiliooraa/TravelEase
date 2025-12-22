package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bll.Destino;
import bll.Usuario;
import dll.ControllerDestino;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class GestionarDestinosView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private Destino destinoSeleccionado;

    public GestionarDestinosView(Usuario usuario) {

        setTitle("Gestionar Destinos");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Gestión de Destinos - " + usuario.getNombre());
        lblTitulo.setForeground(new Color(25, 25, 112));
        lblTitulo.setFont(new Font("Gadugi", Font.BOLD, 18));
        lblTitulo.setBounds(20, 10, 400, 30);
        getContentPane().add(lblTitulo);

        JButton btnVolver = new JButton("");
        btnVolver.setIcon(new ImageIcon(GestionarDestinosView.class.getResource("/img/home.png")));
        btnVolver.setBounds(690, 10, 70, 35);
        btnVolver.setBackground(Color.WHITE);
        btnVolver.addActionListener(e -> {
            new AdminMenu(usuario).setVisible(true);
            dispose();
        });
        getContentPane().add(btnVolver);

        // Tabla
        model = new DefaultTableModel(new String[]{"ID", "Ciudad", "País"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 60, 740, 280);
        getContentPane().add(scroll);

        cargarTabla();

        // Botones CRUD
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 360, 120, 40);
        btnAgregar.setBackground(new Color(152, 251, 152));
        btnAgregar.setIcon(new ImageIcon(GestionarDestinosView.class.getResource("/img/agregar.png")));
        btnAgregar.addActionListener(e -> new AgregarDestinoView(this).setVisible(true));
        getContentPane().add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(150, 360, 120, 40);
        btnEditar.setBackground(new Color(30, 144, 255));
        btnEditar.setIcon(new ImageIcon(GestionarDestinosView.class.getResource("/img/boton-editar.png")));
        btnEditar.addActionListener(e -> {
            if (destinoSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un destino.");
                return;
            }
            new EditarDestinoView(this, destinoSeleccionado).setVisible(true);
        });
        getContentPane().add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(280, 360, 120, 40);
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setIcon(new ImageIcon(GestionarDestinosView.class.getResource("/img/eliminar.png")));
        btnEliminar.addActionListener(e -> eliminarDestino());
        getContentPane().add(btnEliminar);

        // Selección de tabla
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = table.getSelectedRow();
                if (fila != -1) {
                    int id = (int) model.getValueAt(fila, 0);
                    destinoSeleccionado = ControllerDestino.buscarDestinoPorId(id);
                }
            }
        });
    }

    public void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Destino> lista = ControllerDestino.listarDestinos();

        for (Destino d : lista) {
            model.addRow(new Object[]{
                    d.getId(),
                    d.getNombre(),
                    d.getPais()
            });
        }
    }

    private void eliminarDestino() {
        if (destinoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un destino.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Eliminar destino " + destinoSeleccionado.getNombre() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        boolean ok = ControllerDestino.eliminarDestino(destinoSeleccionado.getId());
        if (ok) cargarTabla();
    }
}
