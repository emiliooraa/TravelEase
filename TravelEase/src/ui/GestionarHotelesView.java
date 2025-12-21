package ui;

import bll.Hotel;
import bll.Usuario;
import dll.ControllerHotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class GestionarHotelesView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtCiudad;
    private JTextField txtPais;
    private JTextField txtEstrellas;
    private JTable tblHoteles;
    private JLabel lblErrorNombre;
    private JLabel lblErrorCiudad;
    private JLabel lblErrorPais;
    private JLabel lblErrorEstrella;


    private int hotelSeleccionadoId = -1;

    public GestionarHotelesView(Usuario usuario) {

        setTitle("TravelEase - Gestión de Hoteles");
        setSize(760, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        //TÍTULO
        JLabel lblGestionarHotel = new JLabel("Gestionar Hoteles - " + usuario.getNombre());
        lblGestionarHotel.setForeground(new Color(25, 25, 112));
        lblGestionarHotel.setFont(new Font("Gadugi", Font.BOLD, 18));
        lblGestionarHotel.setBounds(20, 15, 420, 35);
        panel.add(lblGestionarHotel);

        //CAMPOS
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 80, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 80, 200, 25);
        panel.add(txtNombre);

        JLabel lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setBounds(30, 120, 100, 25);
        panel.add(lblCiudad);

        txtCiudad = new JTextField();
        txtCiudad.setBounds(130, 120, 200, 25);
        panel.add(txtCiudad);

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(30, 160, 100, 25);
        panel.add(lblPais);

        txtPais = new JTextField();
        txtPais.setBounds(130, 160, 200, 25);
        panel.add(txtPais);

        JLabel lblEstrellas = new JLabel("Estrellas:");
        lblEstrellas.setBounds(30, 200, 100, 25);
        panel.add(lblEstrellas);

        txtEstrellas = new JTextField();
        txtEstrellas.setBounds(130, 200, 200, 25);
        panel.add(txtEstrellas);

        //BOTONES
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(152, 251, 152));
        btnGuardar.setFont(new Font("Gadugi", Font.PLAIN, 11));
        btnGuardar.setIcon(new ImageIcon(GestionarHotelesView.class.getResource("/img/agregar.png")));
        btnGuardar.setBounds(600, 80, 120, 30);
        panel.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Gadugi", Font.PLAIN, 11));
        btnEditar.setIcon(new ImageIcon(GestionarHotelesView.class.getResource("/img/boton-editar.png")));
        btnEditar.setBackground(new Color(30, 144, 255));
        btnEditar.setBounds(600, 120, 120, 30);
        panel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Gadugi", Font.PLAIN, 11));
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setIcon(new ImageIcon(GestionarHotelesView.class.getResource("/img/eliminar.png")));
        btnEliminar.setBounds(600, 160, 120, 30);
        panel.add(btnEliminar);

        //TABLA
        tblHoteles = new JTable();
        tblHoteles.setModel(new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Ciudad", "País", "Estrellas"}, 0
        ));

        JScrollPane scroll = new JScrollPane(tblHoteles);
        scroll.setBounds(30, 260, 690, 210);
        panel.add(scroll);

        setContentPane(panel);
        
        JButton btnVolver = new JButton("");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminMenu(usuario).setVisible(true);
        		dispose();
        	}
        });
        btnVolver.setIcon(new ImageIcon(GestionarHotelesView.class.getResource("/img/home.png")));
        btnVolver.setFont(new Font("Gadugi", Font.PLAIN, 11));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setBounds(609, 25, 111, 30);
        panel.add(btnVolver);
        
        lblErrorNombre = new JLabel("");
        lblErrorNombre.setForeground(Color.RED);
        lblErrorNombre.setBounds(336, 80, 168, 25);
        panel.add(lblErrorNombre);
        
        lblErrorCiudad = new JLabel("");
        lblErrorCiudad.setForeground(Color.RED);
        lblErrorCiudad.setBounds(336, 120, 168, 25);
        panel.add(lblErrorCiudad);
        
        lblErrorPais = new JLabel("");
        lblErrorPais.setForeground(Color.RED);
        lblErrorPais.setBounds(336, 160, 168, 25);
        panel.add(lblErrorPais);
        
        lblErrorEstrella = new JLabel("");
        lblErrorEstrella.setForeground(Color.RED);
        lblErrorEstrella.setBounds(336, 200, 168, 25);
        panel.add(lblErrorEstrella);

        //ACCIONES
        btnGuardar.addActionListener(e -> guardarHotel());
        btnEditar.addActionListener(e -> editarHotel());
        btnEliminar.addActionListener(e -> eliminarHotel());

        tblHoteles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarFormularioDesdeTabla();
            }
        });

        cargarTabla();
    }

    //MÉTODOS

    private void guardarHotel() {

        if (!validarFormulario()) return;

        String nombre = txtNombre.getText();
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();
        int estrellas = Integer.parseInt(txtEstrellas.getText());

        if (ControllerHotel.crearHotel(nombre, ciudad, pais, estrellas)) {
            JOptionPane.showMessageDialog(this, "Hotel creado correctamente");
            limpiarCampos();
            cargarTabla();
        }
    }


    private void editarHotel() {

        if (hotelSeleccionadoId == -1) return;
        if (!validarFormulario()) return;

        String nombre = txtNombre.getText();
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();
        int estrellas = Integer.parseInt(txtEstrellas.getText());

        if (ControllerHotel.editarHotel(hotelSeleccionadoId, nombre, ciudad, pais, estrellas)) {
            JOptionPane.showMessageDialog(this, "Hotel editado correctamente");
            limpiarCampos();
            cargarTabla();
            hotelSeleccionadoId = -1;
        }
    }


    private void eliminarHotel() {
        if (hotelSeleccionadoId == -1) return;

        if (ControllerHotel.eliminarHotel(hotelSeleccionadoId)) {
            JOptionPane.showMessageDialog(this, "Hotel eliminado correctamente");
            limpiarCampos();
            cargarTabla();
            hotelSeleccionadoId = -1;
        }
    }

    private void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) tblHoteles.getModel();
        model.setRowCount(0);

        for (Hotel h : ControllerHotel.listarHoteles()) {
            model.addRow(new Object[]{
                    h.getId(),
                    h.getNombre(),
                    h.getCiudad(),
                    h.getPais(),
                    h.getEstrellas()
            });
        }
    }

    private void cargarFormularioDesdeTabla() {
        int fila = tblHoteles.getSelectedRow();
        if (fila >= 0) {
            hotelSeleccionadoId = (int) tblHoteles.getValueAt(fila, 0);
            txtNombre.setText(tblHoteles.getValueAt(fila, 1).toString());
            txtCiudad.setText(tblHoteles.getValueAt(fila, 2).toString());
            txtPais.setText(tblHoteles.getValueAt(fila, 3).toString());
            txtEstrellas.setText(tblHoteles.getValueAt(fila, 4).toString());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCiudad.setText("");
        txtPais.setText("");
        txtEstrellas.setText("");
    }
    private boolean validarFormulario() {

        boolean valido = true;

        // Limpiar errores
        lblErrorNombre.setText("");
        lblErrorCiudad.setText("");
        lblErrorPais.setText("");
        lblErrorEstrella.setText("");

        if (txtNombre.getText().trim().isEmpty()) {
            lblErrorNombre.setText("Ingrese nombre");
            valido = false;
        }

        if (txtCiudad.getText().trim().isEmpty()) {
            lblErrorCiudad.setText("Ingrese ciudad");
            valido = false;
        }

        if (txtPais.getText().trim().isEmpty()) {
            lblErrorPais.setText("Ingrese país");
            valido = false;
        }

        if (txtEstrellas.getText().trim().isEmpty()) {
            lblErrorEstrella.setText("Ingrese estrellas");
            valido = false;
        } else {
            try {
                int estrellas = Integer.parseInt(txtEstrellas.getText());
                if (estrellas < 1 || estrellas > 5) {
                    lblErrorEstrella.setText("Debe ser 1 a 5");
                    valido = false;
                }
            } catch (NumberFormatException e) {
                lblErrorEstrella.setText("Solo números");
                valido = false;
            }
        }

        return valido;
    }

}
