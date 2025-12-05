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
        setSize(500, 750);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // CÓDIGO
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 20, 150, 25);
        getContentPane().add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 20, 220, 25);
        getContentPane().add(txtCodigo);

        JLabel lblErrorCodigo = new JLabel("");
        lblErrorCodigo.setForeground(Color.RED);
        lblErrorCodigo.setBounds(180, 45, 250, 20);
        getContentPane().add(lblErrorCodigo);

        // ORIGEN
        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(30, 80, 150, 25);
        getContentPane().add(lblOrigen);

        JTextField txtOrigen = new JTextField();
        txtOrigen.setBounds(180, 80, 220, 25);
        getContentPane().add(txtOrigen);

        JLabel lblErrorOrigen = new JLabel("");
        lblErrorOrigen.setForeground(Color.RED);
        lblErrorOrigen.setBounds(180, 105, 250, 20);
        getContentPane().add(lblErrorOrigen);

        // DESTINO
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(30, 140, 150, 25);
        getContentPane().add(lblDestino);

        JTextField txtDestino = new JTextField();
        txtDestino.setBounds(180, 140, 220, 25);
        getContentPane().add(txtDestino);

        JLabel lblErrorDestino = new JLabel("");
        lblErrorDestino.setForeground(Color.RED);
        lblErrorDestino.setBounds(180, 165, 250, 20);
        getContentPane().add(lblErrorDestino);

        // AEROLÍNEA
        JLabel lblAero = new JLabel("Aerolínea:");
        lblAero.setBounds(30, 200, 150, 25);
        getContentPane().add(lblAero);

        JTextField txtAero = new JTextField();
        txtAero.setBounds(180, 200, 220, 25);
        getContentPane().add(txtAero);

        JLabel lblErrorAero = new JLabel("");
        lblErrorAero.setForeground(Color.RED);
        lblErrorAero.setBounds(180, 225, 250, 20);
        getContentPane().add(lblErrorAero);

        // FECHA SALIDA
        JLabel lblSalida = new JLabel("Fecha salida:");
        lblSalida.setBounds(30, 260, 150, 25);
        getContentPane().add(lblSalida);

        DateTimePicker salidaPicker = new DateTimePicker();
        salidaPicker.setBounds(40, 290, 350, 60);
        getContentPane().add(salidaPicker);

        JLabel lblErrorSalida = new JLabel("");
        lblErrorSalida.setForeground(Color.RED);
        lblErrorSalida.setBounds(40, 349, 350, 20);
        getContentPane().add(lblErrorSalida);

        // FECHA LLEGADA
        JLabel lblLlegada = new JLabel("Fecha llegada:");
        lblLlegada.setBounds(30, 380, 150, 25);
        getContentPane().add(lblLlegada);

        DateTimePicker llegadaPicker = new DateTimePicker();
        llegadaPicker.setBounds(40, 410, 350, 60);
        getContentPane().add(llegadaPicker);

        JLabel lblErrorLlegada = new JLabel("");
        lblErrorLlegada.setForeground(Color.RED);
        lblErrorLlegada.setBounds(40, 470, 350, 20);
        getContentPane().add(lblErrorLlegada);

        // CAPACIDAD
        JLabel lblCapacidad = new JLabel("Capacidad total:");
        lblCapacidad.setBounds(30, 500, 150, 25);
        getContentPane().add(lblCapacidad);

        JTextField txtCapacidad = new JTextField();
        txtCapacidad.setBounds(180, 500, 220, 25);
        getContentPane().add(txtCapacidad);

        JLabel lblErrorCapacidad = new JLabel("");
        lblErrorCapacidad.setForeground(Color.RED);
        lblErrorCapacidad.setBounds(180, 525, 250, 20);
        getContentPane().add(lblErrorCapacidad);

        // DISPONIBLES
        JLabel lblDisp = new JLabel("Asientos disp.:");
        lblDisp.setBounds(30, 560, 150, 25);
        getContentPane().add(lblDisp);

        JTextField txtDisponibles = new JTextField();
        txtDisponibles.setBounds(180, 560, 220, 25);
        getContentPane().add(txtDisponibles);

        JLabel lblErrorDisponibles = new JLabel("");
        lblErrorDisponibles.setForeground(Color.RED);
        lblErrorDisponibles.setBounds(180, 585, 250, 20);
        getContentPane().add(lblErrorDisponibles);

        //BOTÓN GUARDAR
  

        JButton btnGuardar = new JButton("Guardar vuelo");
        btnGuardar.setBounds(150, 630, 170, 40);
        getContentPane().add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            // Limpiar errores
            lblErrorCodigo.setText("");
            lblErrorOrigen.setText("");
            lblErrorDestino.setText("");
            lblErrorAero.setText("");
            lblErrorSalida.setText("");
            lblErrorLlegada.setText("");
            lblErrorCapacidad.setText("");
            lblErrorDisponibles.setText("");

            boolean valido = true;

            // Validar código
            if (txtCodigo.getText().trim().length() < 3) {
                lblErrorCodigo.setText("El código debe tener mínimo 3 caracteres");
                valido = false;
            }

            // Validar origen
            if (txtOrigen.getText().trim().length() < 3) {
                lblErrorOrigen.setText("Ingrese un origen válido");
                valido = false;
            }

            // Validar destino
            if (txtDestino.getText().trim().length() < 3) {
                lblErrorDestino.setText("Ingrese un destino válido");
                valido = false;
            }

            // Validar aerolínea
            if (txtAero.getText().trim().length() < 3) {
                lblErrorAero.setText("Ingrese una aerolínea válida");
                valido = false;
            }

            // Validar fechas
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

            // Validar capacidad
            int capacidad = 0;
            try {
                capacidad = Integer.parseInt(txtCapacidad.getText());
                if (capacidad <= 0) {
                    lblErrorCapacidad.setText("La capacidad debe ser mayor a 0");
                    valido = false;
                }
            } catch (Exception ex) {
                lblErrorCapacidad.setText("Ingrese un número válido");
                valido = false;
            }

            // Validar disponibles
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

            // GUARDAR
            boolean ok = ControllerVuelo.crearVuelo(
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
                JOptionPane.showMessageDialog(null, "Vuelo creado correctamente.");
                padre.cargarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear vuelo.");
            }
        });
    }
}
