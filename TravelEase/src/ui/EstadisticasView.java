package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dll.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstadisticasView extends JFrame {

    private JTable tablaTipo;
    private JTable tablaHoteles;
    private JTable tablaDestinos;

    private DefaultTableModel modeloTipo;
    private DefaultTableModel modeloHoteles;
    private DefaultTableModel modeloDestinos;

    public EstadisticasView() {
        setTitle("Gerente - Estadísticas");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Estadísticas Generales");
        titulo.setBounds(20, 10, 300, 25);
        add(titulo);

        JButton btnCargar = new JButton("Cargar Estadísticas");
        btnCargar.setBounds(20, 40, 200, 30);
        add(btnCargar);

        // ===== Tabla 1: Ventas por tipo =====
        JLabel lblTipo = new JLabel("Ventas por Tipo");
        lblTipo.setBounds(20, 80, 200, 20);
        add(lblTipo);

        modeloTipo = new DefaultTableModel(
            new String[]{"Tipo", "Cantidad", "Total ($)"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaTipo = new JTable(modeloTipo);
        JScrollPane spTipo = new JScrollPane(tablaTipo);
        spTipo.setBounds(20, 105, 400, 100);
        add(spTipo);

        // ===== Tabla 2: Hoteles más vendidos =====
        JLabel lblHoteles = new JLabel("Hoteles más vendidos");
        lblHoteles.setBounds(20, 215, 300, 20);
        add(lblHoteles);

        modeloHoteles = new DefaultTableModel(
            new String[]{"Hotel", "Ventas"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaHoteles = new JTable(modeloHoteles);
        JScrollPane spHoteles = new JScrollPane(tablaHoteles);
        spHoteles.setBounds(20, 240, 400, 220);
        add(spHoteles);

        // ===== Tabla 3: Destinos más vendidos =====
        JLabel lblDestinos = new JLabel("Destinos de vuelo más vendidos");
        lblDestinos.setBounds(450, 80, 300, 20);
        add(lblDestinos);

        modeloDestinos = new DefaultTableModel(
            new String[]{"Destino", "Ventas"}, 0
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaDestinos = new JTable(modeloDestinos);
        JScrollPane spDestinos = new JScrollPane(tablaDestinos);
        spDestinos.setBounds(450, 105, 400, 355);
        add(spDestinos);

        btnCargar.addActionListener(e -> cargarEstadisticas());
    }

    private void cargarEstadisticas() {

        modeloTipo.setRowCount(0);
        modeloHoteles.setRowCount(0);
        modeloDestinos.setRowCount(0);

        Connection con = Conexion.getInstance().getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "No hay conexión a la base de datos.");
            return;
        }

        try {
            // ===== 1) Ventas por tipo =====
            String sqlTipo =
                "SELECT 'HOTEL' AS tipo, COUNT(*) AS cantidad, " +
                "       COALESCE(SUM(vh.noches * p.precio),0) AS total " +
                "FROM venta_hotel vh " +
                "JOIN precio_item p ON p.tipo='HOTEL' AND p.ref_id = vh.id_hotel " +
                "UNION ALL " +
                "SELECT 'VUELO' AS tipo, COUNT(*) AS cantidad, " +
                "       COALESCE(SUM(vv.cantidad * p2.precio),0) AS total " +
                "FROM venta_vuelo vv " +
                "JOIN precio_item p2 ON p2.tipo='VUELO' AND p2.ref_id = vv.id_vuelo";

            PreparedStatement psTipo = con.prepareStatement(sqlTipo);
            ResultSet rsTipo = psTipo.executeQuery();

            while (rsTipo.next()) {
                modeloTipo.addRow(new Object[]{
                    rsTipo.getString("tipo"),
                    rsTipo.getInt("cantidad"),
                    rsTipo.getDouble("total")
                });
            }

            // ===== 2) Hoteles más vendidos =====
            String sqlHoteles =
                "SELECT h.nombre, COUNT(*) AS ventas " +
                "FROM venta_hotel vh " +
                "JOIN hotel h ON h.id = vh.id_hotel " +
                "GROUP BY h.nombre " +
                "ORDER BY ventas DESC";

            PreparedStatement psHoteles = con.prepareStatement(sqlHoteles);
            ResultSet rsHoteles = psHoteles.executeQuery();

            while (rsHoteles.next()) {
                modeloHoteles.addRow(new Object[]{
                    rsHoteles.getString("nombre"),
                    rsHoteles.getInt("ventas")
                });
            }

            // ===== 3) Destinos de vuelo más vendidos =====
            String sqlDestinos =
                "SELECT v.destino, COUNT(*) AS ventas " +
                "FROM venta_vuelo vv " +
                "JOIN vuelo v ON v.id_vuelo = vv.id_vuelo " +
                "GROUP BY v.destino " +
                "ORDER BY ventas DESC";

            PreparedStatement psDestinos = con.prepareStatement(sqlDestinos);
            ResultSet rsDestinos = psDestinos.executeQuery();

            while (rsDestinos.next()) {
                modeloDestinos.addRow(new Object[]{
                    rsDestinos.getString("destino"),
                    rsDestinos.getInt("ventas")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estadísticas: " + e.getMessage());
        }
    }
}
