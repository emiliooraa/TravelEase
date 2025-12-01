package ui;

import javax.swing.*;
import components.DateTimePicker;
import java.awt.*;
import java.time.LocalDateTime;
import bll.Vuelo;
import dll.ControllerVuelo;

public class EditarVueloView extends JFrame {

    private GestionarVuelosView padre;

    public EditarVueloView(GestionarVuelosView padre, Vuelo vuelo) {

        this.padre = padre;

        setTitle("Editar Vuelo");
        setSize(480, 650);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // -------- CÓDIGO --------
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 30, 150, 25);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField(vuelo.getCodigo());
        txtCodigo.setBounds(180, 30, 220, 25);
        add(txtCodigo);

        // -------- ORIGEN --------
        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 80, 150, 25);
        add(lblOrigen);

        JTextField txtOrigen = new JTextField(vuelo.getOrigen());
        txtOrigen.setBounds(180, 80, 220, 25);
        add(txtOrigen);

        // -------- DESTINO --------
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 130, 150, 25);
        add(lblDestino);

        JTextField txtDestino = new JTextField(vuelo.getDestino());
        txtDestino.setBounds(180, 130, 220, 25);
        add(txtDestino);

        // -------- AEROLINEA --------
        JLabel lblAero = new JLabel("Aerolínea:");
        lblAero.setBounds(30, 180, 150, 25);
        add(lblAero);

        JTextField txtAero = new JTextField(vuelo.getAerolinea());
        txtAero.setBounds(180, 180, 220, 25);
        add(txtAero);

        // -------- FECHA SALIDA --------
        JLabel lblSalida = new JLabel("Fecha salida:");
        lblSalida.setBounds(30, 230, 150, 25);
        add(lblSalida);

        DateTimePicker salidaPicker = new DateTimePicker();
        salidaPicker.setDateTime(vuelo.getFechaSalida());
        salidaPicker.setBounds(40, 260, 350, 60);
        add(salidaPicker);

        // -------- FECHA LLEGADA --------
        JLabel lblLlegada = new JLabel("Fecha llegada:");
        lblLlegada.setBounds(30, 330, 150, 25);
        add(lblLlegada);

        DateTimePicker llegadaPicker = new DateTimePicker();
        llegadaPicker.setDateTime(vuelo.getFechaLlegada());
        llegadaPicker.setBounds(40, 360, 350, 60);
        add(llegadaPicker);

        // -------- CAPACIDAD TOTAL --------
        JLabel lblCapacidad = new JLabel("Capacidad total:");
        lblCapacidad.setBounds(30, 430, 150, 25);
        add(lblCapacidad);

        JTextField txtCapacidad = new JTextField(String.valueOf(vuelo.getCapacidadTotal()));
        txtCapacidad.setBounds(180, 430, 220, 25);
        add(txtCapacidad);

        // -------- ASIENTOS DISPONIBLES --------
        JLabel lblDisp = new JLabel("Asientos disp.:");
        lblDisp.setBounds(30, 480, 150, 25);
        add(lblDisp);

        JTextField txtDisponibles = new JTextField(String.valueOf(vuelo.getAsientosDisponibles()));
        txtDisponibles.setBounds(180, 480, 220, 25);
        add(txtDisponibles);

        // -------- BOTÓN GUARDAR --------
        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setBounds(150, 540, 180, 40);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            try {
                LocalDateTime salida = salidaPicker.getDateTime();
                LocalDateTime llegada = llegadaPicker.getDateTime();

                if (salida == null || llegada == null) {
                    JOptionPane.showMessageDialog(null, "Complete ambas fechas.");
                    return;
                }

                if (llegada.isBefore(salida)) {
                    JOptionPane.showMessageDialog(null, "La llegada no puede ser antes de la salida.");
                    return;
                }

                boolean ok = ControllerVuelo.editarVuelo(
                        vuelo.getId(),
                        txtCodigo.getText(),
                        txtOrigen.getText(),
                        txtDestino.getText(),
                        salida,
                        llegada,
                        txtAero.getText(),
                        Integer.parseInt(txtCapacidad.getText()),
                        Integer.parseInt(txtDisponibles.getText())
                );

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Vuelo actualizado correctamente.");
                    padre.cargarTabla();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar vuelo.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        });
    }
}
