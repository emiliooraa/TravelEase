package ui;

import javax.swing.*;

import bll.Usuario;

import java.awt.*;
import java.awt.event.*;

public class OperarioMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public OperarioMenu(Usuario usuario) {
    	getContentPane().setBackground(new Color(240, 240, 240));
        setTitle("Travelease - Menú Operario");
        setSize(681, 451);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 26, 665, 386);
        JButton btnVenta = new JButton("Registrar venta");
        btnVenta.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/iconVenta.png")));
        btnVenta.setFont(new Font("Gadugi", Font.PLAIN, 13));
        btnVenta.setBounds(94, 69, 183, 65);
        JButton btnAplicarDescuento = new JButton("Aplicar Descuento");
        btnAplicarDescuento.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/cupon-de-descuento.png")));
        btnAplicarDescuento.setFont(new Font("Gadugi", Font.PLAIN, 13));
        btnAplicarDescuento.setBounds(405, 183, 183, 65);
        JButton btnAsignarAsiento = new JButton("Asignar asiento");
        btnAsignarAsiento.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/asiento.png")));
        btnAsignarAsiento.setFont(new Font("Gadugi", Font.PLAIN, 13));
        btnAsignarAsiento.setBounds(94, 183, 183, 65);
        JButton btnGestionarReserva = new JButton("Gestionar reservas");
        btnGestionarReserva.setIcon(new ImageIcon(OperarioMenu.class.getResource("/img/calendario.png")));
        btnGestionarReserva.setFont(new Font("Gadugi", Font.PLAIN, 13));
        btnGestionarReserva.setBounds(405, 67, 183, 68);
        
        //Cerrar sesion
        JButton btnSalir = new JButton("Cerrar sesión");
        btnSalir.setFont(new Font("Gadugi", Font.PLAIN, 13));
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		Inicio.main(new String[]{});
        	}
        });
        btnSalir.setBounds(248, 277, 175, 45);
        panel.setLayout(null);
        panel.add(btnVenta);
        panel.add(btnAplicarDescuento);
        panel.add(btnAsignarAsiento);
        panel.add(btnGestionarReserva);
        panel.add(btnSalir);

        getContentPane().add(panel);
        
                JLabel titulo = new JLabel("Menú Operario " + usuario.getNombre());
                titulo.setBounds(182, 0, 483, 26);
                panel.add(titulo);
                titulo.setFont(new Font("Arial", Font.BOLD, 22));
        btnVenta.addActionListener(e -> mostrarMensaje("Registrar venta de hotel (no implementado)") );
        btnAplicarDescuento.addActionListener(e -> mostrarMensaje("Reservar paquete para cliente (no implementado)") );
        btnAsignarAsiento.addActionListener(e -> mostrarMensaje("Asignar asiento (no implementado)") );
        btnGestionarReserva.addActionListener(e -> mostrarMensaje("Buscar reservas (no implementado)") );
        
        
    }

    private void mostrarMensaje(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new OperarioMenu().setVisible(true));
//    }
}
