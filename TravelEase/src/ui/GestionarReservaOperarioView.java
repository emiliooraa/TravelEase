package ui;

import dll.ControllerReserva;
import bll.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionarReservaOperarioView extends JFrame {

    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private ControllerReserva controllerReserva;

    public GestionarReservaOperarioView() {
        controllerReserva = new ControllerReserva();
        initComponents();
        cargarReservas();
    }

    private void initComponents() {
        setTitle("Gestionar Reservas - Operario");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Tabla
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Vuelo", "Estado", "Precio"}, 0
        );
        tablaReservas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);

        // Botones
        JPanel panelBotones = new JPanel();

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEditar = new JButton("Editar");
        JButton btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnActualizar);

        // Eventos
        btnConfirmar.addActionListener(e -> confirmarReserva());
        btnCancelar.addActionListener(e -> cancelarReserva());
        btnEditar.addActionListener(e -> editarReserva());
        btnActualizar.addActionListener(e -> cargarReservas());

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void cargarReservas() {
        modeloTabla.setRowCount(0);
        List<Reserva> reservas = controllerReserva.obtenerReservas();

        for (Reserva r : reservas) {
            modeloTabla.addRow(new Object[]{
                    r.getId(),
                    r.getCliente().getNombre(),
                    r.getVuelo().getCodigo(),
                    r.getEstado(),
                    r.getPrecioTotal()
            });
        }
    }

    private void confirmarReserva() {
        int fila = tablaReservas.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("Seleccione una reserva");
            return;
        }

        int idReserva = (int) modeloTabla.getValueAt(fila, 0);
        controllerReserva.confirmarReserva(idReserva);
        mostrarMensaje("Reserva confirmada");
        cargarReservas();
    }

    private void cancelarReserva() {
        int fila = tablaReservas.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("Seleccione una reserva");
            return;
        }

        int idReserva = (int) modeloTabla.getValueAt(fila, 0);
        controllerReserva.cancelarReserva(idReserva);
        mostrarMensaje("Reserva cancelada");
        cargarReservas();
    }

    private void editarReserva() {
        int fila = tablaReservas.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("Seleccione una reserva");
            return;
        }

        int idReserva = (int) modeloTabla.getValueAt(fila, 0);
        String nuevoEstado = JOptionPane.showInputDialog(
                this,
                "Ingrese nuevo estado (CONFIRMADA / CANCELADA / PENDIENTE):"
        );

        if (nuevoEstado != null && !nuevoEstado.isEmpty()) {
            controllerReserva.actualizarEstadoReserva(idReserva, nuevoEstado);
            mostrarMensaje("Reserva actualizada");
            cargarReservas();
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
