package ui;

import javax.swing.*;
import java.awt.*;
import bll.Destino;
import dll.ControllerDestino;

public class EditarDestinoView extends JFrame {
	private static final long serialVersionUID = 1L;
    private GestionarDestinosView padre;

    public EditarDestinoView(GestionarDestinosView padre, Destino destino) {

        this.padre = padre;

        setTitle("Editar Destino");
        setSize(400, 350);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setBounds(30, 40, 150, 20);
        getContentPane().add(lblCiudad);

        JTextField txtCiudad = new JTextField(destino.getNombre());
        txtCiudad.setBounds(30, 65, 300, 25);
        getContentPane().add(txtCiudad);

        JLabel errCiudad = new JLabel("");
        errCiudad.setBounds(30, 90, 300, 15);
        errCiudad.setForeground(Color.RED);
        getContentPane().add(errCiudad);

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(30, 120, 150, 20);
        getContentPane().add(lblPais);

        JTextField txtPais = new JTextField(destino.getPais());
        txtPais.setBounds(30, 145, 300, 25);
        getContentPane().add(txtPais);

        JLabel errPais = new JLabel("");
        errPais.setBounds(30, 170, 300, 15);
        errPais.setForeground(Color.RED);
        getContentPane().add(errPais);

        JButton btnGuardar = new JButton("Guardar cambios");
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBounds(120, 220, 150, 35);
        getContentPane().add(btnGuardar);

        btnGuardar.addActionListener(e -> {

            errCiudad.setText("");
            errPais.setText("");

            boolean valido = true;

            if (txtCiudad.getText().trim().isEmpty()) {
                errCiudad.setText("Ingrese una ciudad.");
                valido = false;
            }

            if (txtPais.getText().trim().isEmpty()) {
                errPais.setText("Ingrese un país.");
                valido = false;
            }

            if (!valido) return;

            boolean ok = ControllerDestino.editarDestino(
                    destino.getId(),
                    txtCiudad.getText(),
                    txtPais.getText()
            );

            if (ok) {
                JOptionPane.showMessageDialog(null, "Destino editado correctamente.");
                padre.cargarTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al editar.");
            }
        });
    }
}
