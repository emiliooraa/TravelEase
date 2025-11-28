package ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

import dll.ControllerVuelo;

public class AgregarVueloView extends JFrame {
	private static final long serialVersionUID = 1L;
    private GestionarVuelosView padre;

    public AgregarVueloView(GestionarVuelosView padre) {

        this.padre = padre;

        setTitle("Agregar Vuelo");
        setSize(380, 420);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(20, 30, 120, 25);
        add(lblOrigen);
        JTextField txtOrigen = new JTextField();
        txtOrigen.setBounds(140, 30, 200, 25);
        add(txtOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 70, 120, 25);
        add(lblDestino);
        JTextField txtDestino = new JTextField();
        txtDestino.setBounds(140, 70, 200, 25);
        add(txtDestino);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(20, 110, 150, 25);
        add(lblFecha);
        JTextField txtFecha = new JTextField();
        txtFecha.setBounds(180, 110, 160, 25);
        add(txtFecha);

        JLabel lblHora = new JLabel("Hora (HH:MM):");
        lblHora.setBounds(20, 150, 150, 25);
        add(lblHora);
        JTextField txtHora = new JTextField();
        txtHora.setBounds(180, 150, 160, 25);
        add(txtHora);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(20, 190, 150, 25);
        add(lblCapacidad);
        JTextField txtCapacidad = new JTextField();
        txtCapacidad.setBounds(180, 190, 160, 25);
        add(txtCapacidad);

        JLabel lblDisp = new JLabel("Disponibles:");
        lblDisp.setBounds(20, 230, 150, 25);
        add(lblDisp);
        JTextField txtDisp = new JTextField();
        txtDisp.setBounds(180, 230, 160, 25);
        add(txtDisp);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120, 300, 120, 35);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            try {
                String origen = txtOrigen.getText().trim();
                String destino = txtDestino.getText().trim();
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());
                int cap = Integer.parseInt(txtCapacidad.getText().trim());
                int disp = Integer.parseInt(txtDisp.getText().trim());

                boolean ok = ControllerVuelo.crearVuelo(origen, destino, fecha, hora, cap, disp);

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Vuelo agregado exitosamente.");
                    padre.cargarTabla();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el vuelo.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos: " + ex.getMessage());
            }
        });
    }
}
