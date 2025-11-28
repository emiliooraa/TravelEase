package ui;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

import bll.Vuelo;
import dll.ControllerVuelo;

public class EditarVueloView extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestionarVuelosView padre;

    public EditarVueloView(GestionarVuelosView padre, Vuelo vuelo) {

        this.padre = padre;

        setTitle("Editar Vuelo");
        setSize(380, 420);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(20, 30, 120, 25);
        add(lblOrigen);
        JTextField txtOrigen = new JTextField(vuelo.getOrigen());
        txtOrigen.setBounds(140, 30, 200, 25);
        add(txtOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 70, 120, 25);
        add(lblDestino);
        JTextField txtDestino = new JTextField(vuelo.getDestino());
        txtDestino.setBounds(140, 70, 200, 25);
        add(txtDestino);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(20, 110, 150, 25);
        add(lblFecha);
        JTextField txtFecha = new JTextField(vuelo.getFecha().toString());
        txtFecha.setBounds(180, 110, 160, 25);
        add(txtFecha);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(20, 150, 150, 25);
        add(lblHora);
        JTextField txtHora = new JTextField(vuelo.getHorario().toString());
        txtHora.setBounds(180, 150, 160, 25);
        add(txtHora);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(20, 190, 150, 25);
        add(lblCapacidad);
        JTextField txtCapacidad = new JTextField(String.valueOf(vuelo.getCapacidad()));
        txtCapacidad.setBounds(180, 190, 160, 25);
        add(txtCapacidad);

        JLabel lblDisp = new JLabel("Disponibles:");
        lblDisp.setBounds(20, 230, 150, 25);
        add(lblDisp);
        JTextField txtDisp = new JTextField(String.valueOf(vuelo.getAsientosDisponibles()));
        txtDisp.setBounds(180, 230, 160, 25);
        add(txtDisp);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(100, 300, 160, 35);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            try {
                boolean ok = ControllerVuelo.editarVuelo(
                    vuelo.getId(),
                    txtOrigen.getText(),
                    txtDestino.getText(),
                    LocalDate.parse(txtFecha.getText()),
                    LocalTime.parse(txtHora.getText()),
                    Integer.parseInt(txtCapacidad.getText()),
                    Integer.parseInt(txtDisp.getText())
                );

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Vuelo actualizado.");

                    padre.cargarTabla();
                    padre.setVisible(true);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el vuelo.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        });
    }
}
