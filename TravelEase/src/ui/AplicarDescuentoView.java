package ui;

import bll.Usuario;
import dll.ControllerVenta;

import javax.swing.*;
import java.awt.*;

public class AplicarDescuentoView extends JFrame {

    private JPanel contentPane;
    private JTextField txtIdVenta;
    private JTextField txtCodigo;
    private Usuario usuario;

    public AplicarDescuentoView(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
    }

    private void initComponents() {
        setTitle("Aplicar Descuento");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Aplicar Código de Descuento");
        lblTitulo.setFont(new Font("Gadugi", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(60, 20, 320, 30);
        contentPane.add(lblTitulo);

        JLabel lblVenta = new JLabel("ID Venta:");
        lblVenta.setBounds(80, 90, 100, 25);
        contentPane.add(lblVenta);

        txtIdVenta = new JTextField();
        txtIdVenta.setBounds(180, 90, 180, 25);
        contentPane.add(txtIdVenta);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(80, 130, 100, 25);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 130, 180, 25);
        contentPane.add(txtCodigo);

        JButton btnAplicar = new JButton("Aplicar");
        btnAplicar.setBounds(180, 170, 180, 35);
        contentPane.add(btnAplicar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 10, 80, 25);
        contentPane.add(btnVolver);

        btnAplicar.addActionListener(e -> aplicar());
        btnVolver.addActionListener(e -> volver());
    }

    private void aplicar() {
        try {
            int idVenta = Integer.parseInt(txtIdVenta.getText());
            String codigo = txtCodigo.getText();

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código");
                return;
            }

            boolean ok = ControllerVenta.aplicarCodigoDescuento(idVenta, codigo);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Descuento aplicado");
            } else {
                JOptionPane.showMessageDialog(this, "Código inválido");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void volver() {
        new OperarioMenu(usuario).setVisible(true);
        dispose();
    }
}
