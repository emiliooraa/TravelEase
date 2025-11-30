package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dll.ControllerPaquete;
import dll.ControllerHotel;
import dll.ControllerVuelo;
import bll.Paquete;
import bll.Usuario;
import bll.Hotel;
import bll.Vuelo;

import java.awt.*;
import java.util.LinkedList;

public class VentanaBuscarPaquete extends JFrame {

    private JList<Vuelo> listVuelos;
    private JList<Hotel> listHoteles;
    private Usuario usuario;
    private JLabel lblError;

    public VentanaBuscarPaquete(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Buscar Paquete");
        setSize(660, 495);
        setLocationRelativeTo(null);
        setLayout(null);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.5);
        splitPane.setBounds(10, 68, 624, 275);
        add(splitPane);

        //LISTA DE VUELOS
        JLabel lblVuelos = new JLabel("Vuelos");
        lblVuelos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblVuelos.setHorizontalAlignment(SwingConstants.CENTER);
        lblVuelos.setBounds(117, 43, 93, 22);
        add(lblVuelos);

        JPanel panelVuelos = new JPanel();
        splitPane.setLeftComponent(panelVuelos);

        listVuelos = new JList<>();
        panelVuelos.add(new JScrollPane(listVuelos));

        //LISTA DE HOTELES
        JLabel lblHoteles = new JLabel("Hoteles");
        lblHoteles.setHorizontalAlignment(SwingConstants.CENTER);
        lblHoteles.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblHoteles.setBounds(458, 45, 73, 18);
        add(lblHoteles);

        JPanel panelHoteles = new JPanel();
        splitPane.setRightComponent(panelHoteles);

        listHoteles = new JList<>();
        panelHoteles.add(new JScrollPane(listHoteles));

        //BOTÓN GUARDAR
        JButton btnGuardarPaquete = new JButton("Guardar paquete");
        btnGuardarPaquete.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnGuardarPaquete.setBackground(new Color(128, 255, 0));
        btnGuardarPaquete.setBounds(248, 405, 157, 40);
        add(btnGuardarPaquete);

        btnGuardarPaquete.addActionListener(e -> {
            lblError.setText("");

            try {
                Vuelo vueloSeleccionado = listVuelos.getSelectedValue();
                Hotel hotelSeleccionado = listHoteles.getSelectedValue();

                if (vueloSeleccionado == null || hotelSeleccionado == null) {
                    lblError.setText("Seleccioná un vuelo y un hotel.");
                    return;
                }

                Paquete paquete = new Paquete(vueloSeleccionado, hotelSeleccionado);
                ControllerPaquete.guardarPaquete(paquete);

                lblError.setForeground(new Color(0, 128, 0));
                lblError.setText("Paquete guardado correctamente.");
            } catch (Exception ex) {
                ex.printStackTrace();
                lblError.setForeground(Color.RED);
                lblError.setText("Error al guardar el paquete: " + ex.getMessage());
            }
        });

        // ---------- BOTÓN VOLVER ----------
        JButton btnVolver = new JButton("<-- Volver");
        btnVolver.setBounds(10, 422, 98, 23);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            new ClienteMenu(usuario).setVisible(true);
            dispose();
        });

        // ---------- LABEL ERROR ----------
        lblError = new JLabel("");
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setBounds(200, 360, 260, 25);
        add(lblError);

        // ---------- CARGAR DATOS ----------
        cargarVuelos();
        cargarHoteles();
    }

    private void cargarVuelos() {
        LinkedList<Vuelo> lista = ControllerVuelo.listarVuelos();
        DefaultListModel<Vuelo> model = new DefaultListModel<>();
        for (Vuelo v : lista) model.addElement(v);
        listVuelos.setModel(model);
    }

    private void cargarHoteles() {
        LinkedList<Hotel> lista = ControllerHotel.listarHoteles();
        DefaultListModel<Hotel> model = new DefaultListModel<>();
        for (Hotel h : lista) model.addElement(h);
        listHoteles.setModel(model);
    }
}
