package ui;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import dll.ControllerVuelo;

public class AgregarVueloView extends JFrame {

    private static final long serialVersionUID = 1L;
    private GestionarVuelosView padre;

    public AgregarVueloView(GestionarVuelosView padre) {

        this.padre = padre;

        setTitle("Agregar Vuelo");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 40, 120, 25);
        add(lblOrigen);

        JTextField txtOrigen = new JTextField();
        txtOrigen.setBounds(160, 40, 180, 25);
        add(txtOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 80, 120, 25);
        add(lblDestino);

        JTextField txtDestino = new JTextField();
        txtDestino.setBounds(160, 80, 180, 25);
        add(txtDestino);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 120, 120, 25);
        add(lblFecha);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(160, 120, 180, 25);
        add(dateChooser);

        // NO permitir fechas pasadas
        dateChooser.setMinSelectableDate(new Date());

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(30, 160, 120, 25);
        add(lblHora);

        // Spinner hora (0–23)
        JSpinner hourSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
        hourSpinner.setBounds(160, 160, 60, 25);
        hourSpinner.setEditor(new JSpinner.NumberEditor(hourSpinner, "00"));
        add(hourSpinner);

        // Spinner minutos (0–59)
        JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        minuteSpinner.setBounds(240, 160, 60, 25);
        minuteSpinner.setEditor(new JSpinner.NumberEditor(minuteSpinner, "00"));
        add(minuteSpinner);

        JLabel lblCap = new JLabel("Capacidad:");
        lblCap.setBounds(30, 200, 120, 25);
        add(lblCap);

        JTextField txtCapacidad = new JTextField();
        txtCapacidad.setBounds(160, 200, 180, 25);
        add(txtCapacidad);

        JLabel lblDisp = new JLabel("Asientos disponibles:");
        lblDisp.setBounds(30, 240, 150, 25);
        add(lblDisp);

        JTextField txtDisponibles = new JTextField();
        txtDisponibles.setBounds(180, 240, 160, 25);
        add(txtDisponibles);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120, 320, 140, 35);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            try {
                // Validar fecha
                Date fechaRaw = dateChooser.getDate();
                if (fechaRaw == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione una fecha.");
                    return;
                }

                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaRaw);

                LocalDate fecha = LocalDate.of(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH)
                );

                LocalTime hora = LocalTime.of(
                    (int) hourSpinner.getValue(),
                    (int) minuteSpinner.getValue()
                );

                boolean ok = ControllerVuelo.crearVuelo(
                        txtOrigen.getText(),
                        txtDestino.getText(),
                        fecha,
                        hora,
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
