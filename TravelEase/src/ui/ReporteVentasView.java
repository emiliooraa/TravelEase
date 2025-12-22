package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dll.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReporteVentasView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public ReporteVentasView() {
        setTitle("Gerente - Reporte de Ventas (Hotel + Vuelo)");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titulo = new JLabel("Reporte de Ventas (sin filtro) - Calculado con precio_item");
        titulo.setBounds(20, 10, 600, 25);
        add(titulo);

        JButton btnCargar = new JButton("Cargar Reporte");
        btnCargar.setBounds(20, 45, 180, 30);
        add(btnCargar);

        // Columnas del reporte
        String[] columnas = {"Tipo", "ID Venta", "Fecha Venta", "Cliente", "Detalle", "Cantidad", "Precio Unit.", "Total"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla solo lectura
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 90, 840, 300);
        add(scroll);

        btnCargar.addActionListener(e -> cargarReporte());
    }

    private void cargarReporte() {
        modelo.setRowCount(0); // limpiar tabla

        Connection con = Conexion.getInstance().getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(this, "No hay conexión a la base de datos.");
            return;
        }

        String sql =
            "SELECT 'HOTEL' AS tipo, vh.id AS id_venta, vh.fecha_venta, u.nombre AS cliente, " +
            "       h.nombre AS detalle, vh.noches AS cantidad, " +
            "       COALESCE(p.precio,0) AS precio_unit, (vh.noches * COALESCE(p.precio,0)) AS total " +
            "FROM venta_hotel vh " +
            "JOIN usuario u ON u.id = vh.id_usuario " +
            "JOIN hotel h ON h.id = vh.id_hotel " +
            "LEFT JOIN precio_item p ON p.tipo='HOTEL' AND p.ref_id = h.id " +

            "UNION ALL " +

            "SELECT 'VUELO' AS tipo, vv.id AS id_venta, vv.fecha_venta, u.nombre AS cliente, " +
            "       CONCAT(v.origen,' -> ',v.destino,' (',v.codigo,')') AS detalle, vv.cantidad AS cantidad, " +
            "       COALESCE(p2.precio,0) AS precio_unit, (vv.cantidad * COALESCE(p2.precio,0)) AS total " +
            "FROM venta_vuelo vv " +
            "JOIN usuario u ON u.id = vv.id_usuario " +
            "JOIN vuelo v ON v.id_vuelo = vv.id_vuelo " +
            "LEFT JOIN precio_item p2 ON p2.tipo='VUELO' AND p2.ref_id = v.id_vuelo " +
            "ORDER BY fecha_venta DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("tipo"),
                    rs.getInt("id_venta"),
                    rs.getTimestamp("fecha_venta"),
                    rs.getString("cliente"),
                    rs.getString("detalle"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_unit"),
                    rs.getDouble("total")
                };
                modelo.addRow(fila);
            }

            JOptionPane.showMessageDialog(this, "Reporte cargado: " + modelo.getRowCount() + " registros.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar reporte: " + ex.getMessage());
        }
    }
}
