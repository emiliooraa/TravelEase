package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import bll.GestorReservas.Reserva;
import bll.Usuario;
import dll.ControllerReserva;
import java.util.List;
import bll.Reserva;

public class VentanaReservasCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable tablaReservas;
    private DefaultTableModel modelo;
    private JLabel lblError;

    public VentanaReservasCliente(Usuario usuario) {
        setTitle("Mis reservas - " + usuario.getNombre());
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Modelo y tabla
        modelo = new DefaultTableModel(
            new String[] { "ID", "Tipo", "Vuelo/Hotel/Paquete", "Fecha", "Estado" }, 0
        );
        tablaReservas = new JTable(modelo);

        // Ocultar ID
        tablaReservas.getColumnModel().getColumn(0).setMinWidth(0);
        tablaReservas.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaReservas.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        scrollPane.setBounds(10, 10, 564, 300);
        getContentPane().add(scrollPane);

        // Label error
        lblError = new JLabel("", SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(10, 330, 564, 25);
        getContentPane().add(lblError);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar reserva");
        btnCancelar.setBackground(new Color(255, 100, 100));
        btnCancelar.setBounds(200, 370, 155, 30);
        getContentPane().add(btnCancelar);

        // Botón Volver
        JButton btnVolverCR = new JButton("Volver");
        btnVolverCR.setBounds(10, 420, 100, 30);
        getContentPane().add(btnVolverCR);
        btnVolverCR.addActionListener(e -> {
            new ClienteMenu(usuario).setVisible(true);
            dispose();
        });

        // Acción cancelar
        btnCancelar.addActionListener(e -> {
            lblError.setText("");
            lblError.setForeground(Color.RED);

            int fila = tablaReservas.getSelectedRow();
            if (fila == -1) {
                lblError.setText("Seleccioná una reserva.");
                return;
            }

            int idReserva = (Integer) modelo.getValueAt(fila, 0);

            boolean ok = ControllerReserva.cancelarReserva(idReserva);
            if (!ok) {
                lblError.setText("No se pudo cancelar la reserva.");
                return;
            }

            // Actualizar tabla
            modelo.setValueAt("CANCELADA", fila, 4);
            lblError.setForeground(new Color(0, 150, 0));
            lblError.setText("Reserva cancelada correctamente.");
        });

        // Cargar datos
        cargarReservas(usuario);
    }

    private void cargarReservas(Usuario usuario) {
        modelo.setRowCount(0);
        List<Reserva> reservas = ControllerReserva.obtenerReservasDeCliente(usuario.getId());
        	
        for (Reserva r : reservas) {
            String tipo = r.getTipo();
            String detalle = "";
            
            if ("VUELO".equals(tipo)) {
				detalle = "Vuelo ID: " + r.getIdVuelo();
            } else if ("HOTEL".equals(tipo)) {
                detalle = "Hotel ID: " + r.getIdHotel();
            } else if ("PAQUETE".equals(tipo)) {
                detalle = "Paquete ID: " + r.getIdPaquete();
            }
            
            String fechaStr = "";
            Timestamp fecha = r.getFechaReserva();
            if (fecha != null) {
                fechaStr = fecha.toString().substring(0, 16);
            }
            
            String estado = r.isCancelada() ? "CANCELADA" : r.getEstado();

            modelo.addRow(new Object[] {
                r.getIdReserva(), 
                tipo,
                detalle,
                fechaStr,
                estado
            });
        }
    }
}
