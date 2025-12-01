package ui;

import javax.swing.*;

import components.DateTimePicker;

import java.awt.*;
import java.time.LocalDateTime;

import dll.ControllerVuelo;

public class AgregarVueloView extends JFrame {

    private GestionarVuelosView padre;

    public AgregarVueloView(GestionarVuelosView padre) {

        this.padre = padre;

        setTitle("Agregar Vuelo");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // ----- Código -----
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 30, 150, 25);
        getContentPane().add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 30, 200, 25);
        getContentPane().add(txtCodigo);

        // ----- Origen -----
        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 70, 150, 25);
        getContentPane().add(lblOrigen);

        JTextField txtOrigen = new JTextField();
        txtOrigen.setBounds(180, 70, 200, 25);
        getContentPane().add(txtOrigen);

        // ----- Destino -----
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 110, 150, 25);
        getContentPane().add(lblDestino);

        JTextField txtDestino = new JTextField();
        txtDestino.setBounds(180, 110, 200, 25);
        getContentPane().add(txtDestino);

        // ----- Aerolínea -----
        JLabel lblAero = new JLabel("Aerolínea:");
        lblAero.setBounds(30, 150, 150, 25);
        getContentPane().add(lblAero);

        JTextField txtAero = new JTextField();
        txtAero.setBounds(180, 150, 200, 25);
        getContentPane().add(txtAero);

        // ----- Fecha salida -----
        JLabel lblSalida = new JLabel("Fecha de salida:");
        lblSalida.setBounds(30, 190, 200, 25);
        getContentPane().add(lblSalida);

        DateTimePicker salidaPicker = new DateTimePicker();
        salidaPicker.setBounds(40, 220, 350, 60);
        getContentPane().add(salidaPicker);

        // ----- Fecha llegada -----
        JLabel lblLlegada = new JLabel("Fecha de llegada:");
        lblLlegada.setBounds(30, 290, 200, 25);
        getContentPane().add(lblLlegada);

        DateTimePicker llegadaPicker = new DateTimePicker();
        llegadaPicker.setBounds(40, 320, 350, 60);
        getContentPane().add(llegadaPicker);

        // ----- BOTÓN GUARDAR -----
        JButton btnGuardar = new JButton("Guardar vuelo");
        btnGuardar.setBounds(140, 430, 160, 35);
        getContentPane().add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            try {

                LocalDateTime salida = salidaPicker.getDateTime();
                LocalDateTime llegada = llegadaPicker.getDateTime();

                if (salida == null || llegada == null) {
                    JOptionPane.showMessageDialog(null, "Complete ambas fechas.");
                    return;
                }

                if (llegada.isBefore(salida)) {
                    JOptionPane.showMessageDialog(null, "La llegada no puede ser antes que la salida.");
                    return;
                }

                boolean ok = ControllerVuelo.crearVuelo(
                        txtCodigo.getText(),
                        txtOrigen.getText(),
                        txtDestino.getText(),
                        salida,
                        llegada,
                        txtAero.getText()
                );

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Vuelo creado correctamente.");
                    padre.cargarTabla();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear vuelo.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}
