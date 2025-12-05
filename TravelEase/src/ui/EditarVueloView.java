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
        setSize(500, 750);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // CÓDIGO
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 20, 150, 25);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField(vuelo.getCodigo());
        txtCodigo.setBounds(180, 20, 220, 25);
        add(txtCodigo);

        JLabel lblErrorCodigo = new JLabel("");
        lblErrorCodigo.setForeground(Color.RED);
        lblErrorCodigo.setBounds(180, 45, 250, 20);
        add(lblErrorCodigo);

        // ORIGEN
        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 80, 150, 25);
        add(lblOrigen);

        JTextField txtOrigen = new JTextField(vuelo.getOrigen());
        txtOrigen.setBounds(180, 80, 220, 25);
        add(txtOrigen);

        JLabel lblErrorOrigen = new JLabel("");
        lblErrorOrigen.setForeground(Color.RED);
        lblErrorOrigen.setBounds(180, 105, 250, 20);
        add(lblErrorOrigen);

        // DESTINO
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 140, 150, 25);
        add(lblDestino);

        JTextField txtDestino = new JTextField(vuelo.getDestino());
        txtDestino.setBounds(180, 140, 220, 25);
        add(txtDestino);

        JLabel lblErrorDestino = new JLabel("");
        lblErrorDestino.setForeground(Color.RED);
        lblErrorDestino.setBounds(180, 165, 250, 20);
        add(lblErrorDestino);

        // AEROLÍNEA
        JLabel lblAero = new JLabel("Aerolínea:");
        lblAero.setBounds(30, 200, 150, 25);
        add(lblAero);

        JTextField txtAero = new JTextField(vuelo.getAerolinea());
        txtAero.setBounds(180, 200, 220, 25);
        add(txtAero);

        JLabel lblErrorAero = new JLabel("");
        lblErrorAero.setForeground(Color.RED);
        lblErrorAero.setBounds(180, 225, 250, 20);
        add(lblErrorAero);

        // FECHA SALIDA
        JLabel lblSalida = new JLabel("Fecha salida:");
        lblSalida.setBounds(30, 260, 150, 25);
        add(lblSalida);

        DateTimePicker salidaPicker = new DateTimePicker();
        salidaPicker.setDateTime(vuelo.getFechaSalida());
        salidaPicker.setBounds(40, 290, 350, 60);
        add(salidaPicker);

        JLabel lblErrorSalida = new JLabel("");
        lblErrorSalida.setForeground(Color.RED);
        lblErrorSalida.setBounds(40, 350, 350, 20);
        add(lblErrorSalida);

        // FECHA LLEGADA
        JLabel lblLlegada = new JLabel("Fecha llegada:");
        lblLlegada.setBounds(30, 380, 150, 25);
        add(lblLlegada);

        DateTimePicker llegadaPicker = new DateTimePicker();
        llegadaPicker.setDateTime(vuelo.getFechaLlegada());
        llegadaPicker.setBounds(40, 410, 350, 60);
        add(llegadaPicker);

        JLabel lblErrorLlegada = new JLabel("");
        lblErrorLlegada.setForeground(Color.RED);
        lblErrorLlegada.setBounds(40, 470, 350, 20);
        add(lblErrorLlegada);

        // CAPACIDAD
        JLabel lblCapacidad = new JLabel("Capacidad total:");
        lblCapacidad.setBounds(30, 500, 150, 25);
        add(lblCapacidad);

        JTextField txtCapacidad = new JTextField(String.valueOf(vuelo.getCapacidadTotal()));
        txtCapacidad.setBounds(180, 500, 220, 25);
        add(txtCapacidad);

        JLabel lblErrorCapacidad = new JLabel("");
        lblErrorCapacidad.setForeground(Color.RED);
        lblErrorCapacidad.setBounds(180, 525, 250, 20);
        add(lblErrorCapacidad);

        // DISPONIBLES
        JLabel lblDisp = new JLabel("Asientos disp.:");
        lblDisp.setBounds(30, 560, 150, 25);
        add(lblDisp);

        JTextField txtDisponibles = new JTextField(String.valueOf(vuelo.getAsientosDisponibles()));
        txtDisponibles.setBounds(180, 560, 220, 25);
        add(txtDisponibles);

        JLabel lblErrorDisponibles = new JLabel("");
        lblErrorDisponibles.setForeground(Color.RED);
        lblErrorDisponibles.setBounds(180, 585, 250, 20);
        add(lblErrorDisponibles);

        //BOTÓN GUARDAR

        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setBounds(150, 630, 180, 40);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            // LIMPIAR ERRORES
            lblErrorCodigo.setText("");
            lblErrorOrigen.setText("");
            lblErrorDestino.setText("");
            lblErrorAero.setText("");
            lblErrorSalida.setText("");
            lblErrorLlegada.setText("");
            lblErrorCapacidad.setText("");
            lblErrorDisponibles.setText("");

            boolean valido = true;

            // VALIDACIONES
            if (txtCodigo.getText().trim().length() < 3) {
                lblErrorCodigo.setText("El código debe tener mínimo 3 caracteres");
                valido = false;
            }

            if (txtOrigen.getText().trim().length() < 3) {
                lblErrorOrigen.setText("Ingrese un origen válido");
                valido = false;
            }

            if (txtDestino.getText().trim().length() < 3) {
                lblErrorDestino.setText("Ingrese un destino válido");
                valido = false;
            }

            if (txtAero.getText().trim().length() < 3) {
                lblErrorAero.setText("Ingrese una aerolínea válida");
                valido = false;
            }

            LocalDateTime salida = salidaPicker.getDateTime();
            LocalDateTime llegada = llegadaPicker.getDateTime();

            if (salida == null) {
                lblErrorSalida.setText("Seleccione una fecha de salida");
                valido = false;
            }

            if (llegada == null) {
                lblErrorLlegada.setText("Seleccione una fecha de llegada");
                valido = false;
            }

            if (salida != null && llegada != null && llegada.isBefore(salida)) {
                lblErrorLlegada.setText("La llegada no puede ser antes de la salida");
                valido = false;
            }

            int capacidad = 0;
            try {
                capacidad = Integer.parseInt(txtCapacidad.getText());
                if (capacidad <= 0) {
                    lblErrorCapacidad.setText("Capacidad debe ser > 0");
                    valido = false;
                }
            } catch (Exception ex) {
                lblErrorCapacidad.setText("Ingrese un número válido");
                valido = false;
            }

            int disponibles = 0;
            try {
                disponibles = Integer.parseInt(txtDisponibles.getText());
                if (disponibles < 0 || disponibles > capacidad) {
                    lblErrorDisponibles.setText("Disponibles debe ser entre 0 y capacidad");
                    valido = false;
                }
            } catch (Exception ex) {
                lblErrorDisponibles.setText("Ingrese un número válido");
                valido = false;
            }

            if (!valido) return;

            // GUARDAR CAMBIOS
            boolean ok = ControllerVuelo.editarVuelo(
                    vuelo.getId(),
                    txtCodigo.getText(),
                    txtOrigen.getText(),
                    txtDestino.getText(),
                    salida,
                    llegada,
                    txtAero.getText(),
                    capacidad,
                    disponibles
            );

            if (ok) {
                JOptionPane.showMessageDialog(null, "Vuelo actualizado correctamente.");
                padre.cargarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar vuelo.");
            }
        });
    }
}
