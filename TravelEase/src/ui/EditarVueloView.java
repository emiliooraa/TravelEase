package ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import bll.Vuelo;
import dll.ControllerVuelo;
import components.DateTimePicker;

public class EditarVueloView extends JFrame {

    private GestionarVuelosView padre;
    private Vuelo vuelo;

    public EditarVueloView(GestionarVuelosView padre, Vuelo vueloSeleccionado) {
        this.padre = padre;
        this.vuelo = vueloSeleccionado;

        setTitle("Editar Vuelo");
        setSize(450, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // ------- CÓDIGO -------
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 30, 150, 25);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField(vuelo.getCodigo());
        txtCodigo.setBounds(180, 30, 200, 25);
        add(txtCodigo);

        // ------- ORIGEN -------
        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 70, 150, 25);
        add(lblOrigen);

        JTextField txtOrigen = new JTextField(vuelo.getOrigen());
        txtOrigen.setBounds(180, 70, 200, 25);
        add(txtOrigen);

        // ------- DESTINO -------
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 110, 150, 25);
        add(lblDestino);

        JTextField txtDestino = new JTextField(vuelo.getDestino());
        txtDestino.setBounds(180, 110, 200, 25);
        add(txtDestino);

        // ------- Aerolínea -------
        JLabel lblAero = new JLabel("Aerolínea:");
        lblAero.setBounds(30, 150, 150, 25);
        add(lblAero);

        JTextField txtAero = new JTextField(vuelo.getAerolinea());
        txtAero.setBounds(180, 150, 200, 25);
        add(txtAero);

        // ------- Fecha Salida -------
        JLabel lblSalida = new JLabel("Fecha de salida:");
        lblSalida.setBounds(30, 200, 200, 25);
        add(lblSalida);

        DateTimePicker salidaPicker = new DateTimePicker();
        salidaPicker.setBounds(40, 230, 300, 60);
        salidaPicker.setDateTime(vuelo.getFechaSalida()); 
        add(salidaPicker);

        // ------- Fecha Llegada -------
        JLabel lblLlegada = new JLabel("Fecha de llegada:");
        lblLlegada.setBounds(30, 305, 200, 25);
        add(lblLlegada);

        DateTimePicker llegadaPicker = new DateTimePicker();
        llegadaPicker.setBounds(40, 335, 300, 60);
        llegadaPicker.setDateTime(vuelo.getFechaLlegada()); 
        add(llegadaPicker);

        // ------- BOTÓN GUARDAR -------
        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setBounds(120, 450, 180, 35);
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
                    JOptionPane.showMessageDialog(null, "La fecha de llegada no puede ser anterior a la salida.");
                    return;
                }

                boolean ok = ControllerVuelo.editarVuelo(
                        vuelo.getId(),
                        txtCodigo.getText().trim(),
                        txtOrigen.getText().trim(),
                        txtDestino.getText().trim(),
                        salida,
                        llegada,
                        txtAero.getText().trim()
                );

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Vuelo modificado correctamente.");
                    padre.cargarTabla();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el vuelo.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}
