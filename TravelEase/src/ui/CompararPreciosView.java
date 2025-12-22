package ui;

import javax.swing.*;
import dll.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompararPreciosView extends JFrame {

    private JComboBox<String> comboTipo;
    private JComboBox<Item> comboA;
    private JComboBox<Item> comboB;
    private JLabel lblResultado;

    public CompararPreciosView() {
        setTitle("Gerente - Comparar Precios");
        setSize(600, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Comparar Precios (usa tabla precio_item)");
        titulo.setBounds(20, 10, 400, 25);
        add(titulo);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 50, 60, 25);
        add(lblTipo);

        comboTipo = new JComboBox<>(new String[]{"HOTEL", "VUELO"});
        comboTipo.setBounds(80, 50, 150, 25);
        add(comboTipo);

        JLabel lblA = new JLabel("Opción A:");
        lblA.setBounds(20, 90, 80, 25);
        add(lblA);

        comboA = new JComboBox<>();
        comboA.setBounds(100, 90, 450, 25);
        add(comboA);

        JLabel lblB = new JLabel("Opción B:");
        lblB.setBounds(20, 130, 80, 25);
        add(lblB);

        comboB = new JComboBox<>();
        comboB.setBounds(100, 130, 450, 25);
        add(comboB);

        JButton btnCargar = new JButton("Cargar opciones");
        btnCargar.setBounds(250, 50, 160, 25);
        add(btnCargar);

        JButton btnComparar = new JButton("Comparar");
        btnComparar.setBounds(20, 170, 150, 30);
        add(btnComparar);

        lblResultado = new JLabel("Resultado: (aún sin comparar)");
        lblResultado.setBounds(20, 220, 530, 50);
        add(lblResultado);

        // Acciones
        btnCargar.addActionListener(e -> cargarItems());
        btnComparar.addActionListener(e -> comparar());

        // Cargar por primera vez (HOTEL)
        cargarItems();
    }

    private void cargarItems() {
        comboA.removeAllItems();
        comboB.removeAllItems();

        String tipo = (String) comboTipo.getSelectedItem();
        Connection con = Conexion.getInstance().getConnection();

        if (con == null) {
            JOptionPane.showMessageDialog(this, "No hay conexión a la base de datos.");
            return;
        }

        try {
            if ("HOTEL".equals(tipo)) {
                // Lista hoteles que tienen precio en precio_item
                String sql =
                    "SELECT h.id, h.nombre, p.precio " +
                    "FROM hotel h " +
                    "JOIN precio_item p ON p.tipo='HOTEL' AND p.ref_id=h.id " +
                    "ORDER BY h.nombre";

                try (PreparedStatement ps = con.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Item item = new Item(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
                        comboA.addItem(item);
                        comboB.addItem(item);
                    }
                }

            } else {
                // Lista vuelos que tienen precio en precio_item
                String sql =
                    "SELECT v.id_vuelo AS id, CONCAT(v.origen,' -> ',v.destino,' (',v.codigo,')') AS nombre, p.precio " +
                    "FROM vuelo v " +
                    "JOIN precio_item p ON p.tipo='VUELO' AND p.ref_id=v.id_vuelo " +
                    "ORDER BY v.id_vuelo";

                try (PreparedStatement ps = con.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Item item = new Item(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
                        comboA.addItem(item);
                        comboB.addItem(item);
                    }
                }
            }

            lblResultado.setText("Resultado: opciones cargadas. Elegí A y B y tocá Comparar.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar opciones: " + e.getMessage());
        }
    }

    private void comparar() {
        Item a = (Item) comboA.getSelectedItem();
        Item b = (Item) comboB.getSelectedItem();

        if (a == null || b == null) {
            JOptionPane.showMessageDialog(this, "Cargá opciones y seleccioná A y B.");
            return;
        }

        double precioA = a.precio;
        double precioB = b.precio;

        if (precioA == precioB) {
            lblResultado.setText("Resultado: Empate. Ambos cuestan $" + precioA);
            return;
        }

        Item barato = (precioA < precioB) ? a : b;
        Item caro = (precioA < precioB) ? b : a;
        double diferencia = Math.abs(precioA - precioB);

        lblResultado.setText(
            "<html>Resultado: Más barato → <b>" + barato.nombre + "</b> ($" + barato.precio + ")" +
            "<br>Más caro → " + caro.nombre + " ($" + caro.precio + ")" +
            "<br>Diferencia: $" + diferencia + "</html>"
        );
    }

    // Clase interna simple para combos
    private static class Item {
        int id;
        String nombre;
        double precio;

        Item(int id, String nombre, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return nombre + "  -  $" + precio;
        }
    }
}
