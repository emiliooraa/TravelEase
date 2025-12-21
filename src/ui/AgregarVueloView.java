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
        setSize(480, 650);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // -------- CÓDIGO ----------
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 30, 150, 25);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 30, 220, 25);
        add(txtCodigo);

        // -------- ORIGEN ----------
        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 80, 150, 25);
        add(lblOrigen);

        JTextField txtOrigen = new JTextField();
        txtOrigen.setBounds(180, 80, 220, 25);
        add(txtOrigen);

        // -------- DESTINO ----------
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 130, 150, 25);
        add(lblDestino);

        JTextField txtDestino = new JTextField();
        txtDestino.setBounds(180, 130, 220, 25);
        add(txtDestino);

        // -------- AEROLÍNEA ----------
        JLabel lblAero = new JLabel("Aerolínea:");
        lblAero.setBounds(30, 180, 150, 25);
        add(lblAero);

        JTextField txtAero = new JTextField();
        txtAero.setBounds(180, 180, 220, 25);
        add(txtAero);

        // -------- FECHA/HORA SALIDA ----------
        JLabel lblSalida = new JLabel("Fecha salida:");
        lblSalida.setBounds(30, 230, 150, 25);
        add(lblSalida);

        DateTimePicker salidaPicker = new DateTimePicker();
        salidaPicker.setBounds(40, 260, 350, 60);
        add(salidaPicker);

        // -------- FECHA/HORA LLEGADA ----------
        JLabel lblLlegada = new JLabel("Fecha llegada:");
        lblLlegada.setBounds(30, 330, 150, 25);
        add(lblLlegada);

        DateTimePicker llegadaPicker = new DateTimePicker();
        llegadaPicker.setBounds(40, 360, 350, 60);
        add(llegadaPicker);

        // -------- CAPACIDAD TOTAL ----------
        JLabel lblCapacidad = new JLabel("Capacidad total:");
        lblCapacidad.setBounds(30, 430, 150, 25);
        add(lblCapacidad);

        JTextField txtCapacidad = new JTextField();
        txtCapacidad.setBounds(180, 430, 220, 25);
        add(txtCapacidad);

        // -------- ASIENTOS DISPONIBLES ----------
        JLabel lblDisp = new JLabel("Asientos disp.:");
        lblDisp.setBounds(30, 480, 150, 25);
        add(lblDisp);

        JTextField txtDisponibles = new JTextField();
        txtDisponibles.setBounds(180, 480, 220, 25);
        add(txtDisponibles);

        // -------- BOTÓN GUARDAR ----------
        JButton btnGuardar = new JButton("Guardar vuelo");
        btnGuardar.setBounds(150, 540, 170, 40);
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
                    JOptionPane.showMessageDialog(null, "La fecha de llegada no puede ser antes de la salida.");
                    return;
                }

                boolean ok = ControllerVuelo.crearVuelo(
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
