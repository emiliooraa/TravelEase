package ui;

import javax.swing.*;
import java.awt.*;
import dll.ControllerDestino;

public class AgregarDestinoView extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private GestionarDestinosView padre;

    public AgregarDestinoView(GestionarDestinosView padre) {

        this.padre = padre;

        setTitle("Agregar Destino");
        setSize(400, 350);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 40, 150, 20);
        add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(30, 65, 300, 25);
        add(txtNombre);

        JLabel errNombre = new JLabel("");
        errNombre.setBounds(30, 90, 300, 15);
        errNombre.setForeground(Color.RED);
        add(errNombre);

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(30, 120, 150, 20);
        add(lblPais);

        JTextField txtPais = new JTextField();
        txtPais.setBounds(30, 145, 300, 25);
        add(txtPais);

        JLabel errPais = new JLabel("");
        errPais.setBounds(30, 170, 300, 15);
        errPais.setForeground(Color.RED);
        add(errPais);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120, 220, 150, 35);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            errNombre.setText("");
            errPais.setText("");

            boolean valido = true;

            if (txtNombre.getText().trim().isEmpty()) {
                errNombre.setText("Ingrese un nombre.");
                valido = false;
            }

            if (txtPais.getText().trim().isEmpty()) {
                errPais.setText("Ingrese un país.");
                valido = false;
            }

            if (!valido) return;

            boolean ok = ControllerDestino.crearDestino(txtNombre.getText(), txtPais.getText());

            if (ok) {
                JOptionPane.showMessageDialog(null, "Destino agregado correctamente.");
                padre.cargarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar.");
            }
        });
    }
}
