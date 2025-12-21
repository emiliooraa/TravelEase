package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dll.ControllerPaquete;
import dll.ControllerReserva;
import bll.Paquete;
import bll.Usuario;
import bll.Hotel;
import bll.Vuelo;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

public class VentanaBuscarPaquete extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaPaquetes;
    private DefaultTableModel modeloPaquetes;
    private JLabel lblError;

    public VentanaBuscarPaquete(Usuario usuario) {
        setTitle("Buscar Paquete");
        setSize(660, 495);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Panel central con tabla de paquetes
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBounds(10, 68, 624, 275);
        getContentPane().add(panelCentro);

        // Modelo y tabla de Paquetes
        modeloPaquetes = new DefaultTableModel(
                new String[] {
                    "ID",           // ID paquete (oculto)
                    "Origen",       // vuelo
                    "Destino",      // vuelo
                    "Fecha",        // vuelo
                    "Hotel",        // hotel
                    "Destino hotel" // hotel
                }, 0);
        tablaPaquetes = new JTable(modeloPaquetes);

        // Ocultar columna ID
        tablaPaquetes.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPaquetes.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPaquetes.getColumnModel().getColumn(0).setWidth(0);

        panelCentro.add(new JScrollPane(tablaPaquetes), BorderLayout.CENTER);

        // Etiqueta Paquetes
        JLabel lblPaquetes = new JLabel("Paquetes (Vuelo + Hotel)");
        lblPaquetes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPaquetes.setHorizontalAlignment(SwingConstants.CENTER);
        lblPaquetes.setBounds(180, 35, 280, 22);
        getContentPane().add(lblPaquetes);

        // Botón Guardar / Reservar Paquete
        JButton btnGuardarPaquete = new JButton("Reservar paquete");
        btnGuardarPaquete.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnGuardarPaquete.setBackground(new Color(128, 255, 0));
        btnGuardarPaquete.setBounds(248, 405, 157, 40);
        getContentPane().add(btnGuardarPaquete);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 422, 98, 23);
        getContentPane().add(btnVolver);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClienteMenu(usuario).setVisible(true);
                dispose();
            }
        });

        // Label Error / Mensajes
        lblError = new JLabel("");
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(117, 366, 414, 14);
        getContentPane().add(lblError);

        // Acción Reservar Paquete
        btnGuardarPaquete.addActionListener(e -> {
            lblError.setText("");

            int fila = tablaPaquetes.getSelectedRow();

            if (fila == -1) {
                lblError.setForeground(Color.RED);
                lblError.setText("Seleccioná un paquete.");
                return;
            }

            int idPaquete = (int) modeloPaquetes.getValueAt(fila, 0);

            try {
                boolean ok = ControllerReserva.reservarPaquete(usuario.getId(), idPaquete);

                if (!ok) {
                    lblError.setForeground(Color.RED);
                    lblError.setText("No se pudo reservar el paquete.");
                    return;
                }

                lblError.setForeground(new Color(0, 255, 0));
                lblError.setText("Reserva de paquete realizada con éxito.");
            } catch (Exception ex) {
                ex.printStackTrace();
                lblError.setForeground(Color.RED);
                lblError.setText("Error al reservar el paquete: " + ex.getMessage());
            }
        });

        // Cargar datos en tabla
        cargarDatos();
    }

    private void cargarDatos() {
        modeloPaquetes.setRowCount(0);

        ControllerPaquete cp = new ControllerPaquete();

        for (Paquete p : cp.listarPaquetes()) {
            Vuelo v = p.getVuelo();
            Hotel h = p.getHotel();

            if (v != null && h != null) {
                modeloPaquetes.addRow(new Object[] {
                        p.getId(),
                        v.getOrigen(),
                        v.getDestino(),
                        v.getFechaSalida(),
                        h.getNombre(),
                        h.getCiudad() + ", " + h.getPais()
                });
            }
        }
    }
}
