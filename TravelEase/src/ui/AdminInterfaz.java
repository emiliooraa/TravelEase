package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bll.Usuario;
import dll.ControllerUsuario;

import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class AdminInterfaz extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Usuario usuarioSeleccionado;
    private JTextField txtFiltro;

    public AdminInterfaz(Usuario logueado) {
        setTitle("Panel de Administración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 582);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Bienvenida
        JLabel lblBienvenidoAdmin = new JLabel("Bienvenido " + logueado.getNombre() + ", " + logueado.getRol().toLowerCase());
        lblBienvenidoAdmin.setForeground(Color.BLACK);
        lblBienvenidoAdmin.setBounds(10, 1, 400, 56);
        lblBienvenidoAdmin.setFont(new Font("Gadugi", Font.PLAIN, 15));
        contentPane.add(lblBienvenidoAdmin);

        // Botón cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBackground(new Color(128, 0, 0));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrarSesion.setBounds(655, 11, 131, 39);
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            Inicio.main(new String[]{});
        });
        contentPane.add(btnCerrarSesion);

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 68, 776, 464);
        contentPane.add(tabbedPane);

        // Panel usuarios
        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setLayout(null);
        tabbedPane.addTab("Gestionar Usuarios", null, panelUsuarios, null);

        // Tabla
        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Email", "Rol"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 751, 250);
        panelUsuarios.add(scrollPane);

        // Campo filtro
        txtFiltro = new JTextField();
        txtFiltro.setBounds(10, 280, 150, 30);
        panelUsuarios.add(txtFiltro);

        JButton btnFiltrar = new JButton("Filtrar nombre");
        btnFiltrar.setBounds(170, 280, 130, 30);
        btnFiltrar.addActionListener(e -> cargarTablaFiltrada(txtFiltro.getText()));
        panelUsuarios.add(btnFiltrar);

        JButton btnReiniciarFiltro = new JButton("Reiniciar");
        btnReiniciarFiltro.setBounds(310, 280, 100, 30);
        btnReiniciarFiltro.addActionListener(e -> {
            txtFiltro.setText("");
            cargarTabla();
        });
        panelUsuarios.add(btnReiniciarFiltro);

        //CRUD
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 330, 120, 40);
        panelUsuarios.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(140, 330, 120, 40);
        panelUsuarios.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(270, 330, 120, 40);
        panelUsuarios.add(btnEliminar);

        JLabel lblSeleccionado = new JLabel("Seleccionado: Ninguno");
        lblSeleccionado.setBounds(10, 390, 600, 20);
        panelUsuarios.add(lblSeleccionado);

        // Panel reportes
        JPanel panelReportes = new JPanel();
        tabbedPane.addTab("Ver Reportes", null, panelReportes, null);

        // Cargar tabla 
        cargarTabla();

        // Evento al seleccionar fila
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    usuarioSeleccionado = new Usuario(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        null, 
                        (String) model.getValueAt(row, 2),
                        null,
                        (String) model.getValueAt(row, 3)
                    );
                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId() +
                            ", Nombre=" + usuarioSeleccionado.getNombre() +
                            ", Email=" + usuarioSeleccionado.getEmail() +
                            ", Rol=" + usuarioSeleccionado.getRol());
                }
            }
        });

        // Agregar usuario
        btnAgregar.addActionListener(e -> {
            JTextField nombreField = new JTextField();
            JTextField dniField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField rolField = new JTextField();

            Object[] campos = {
                    "Nombre:", nombreField,
                    "DNI:", dniField,
                    "Email:", emailField,
                    "Contraseña:", passwordField,
                    "Rol:", rolField
            };

            int option = JOptionPane.showConfirmDialog(null, campos, "Nuevo usuario", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                boolean ok = ControllerUsuario.registrarUsuario(
                        nombreField.getText(),
                        dniField.getText(),
                        emailField.getText(),
                        new String(passwordField.getPassword())
                );
                if (ok) JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
                cargarTabla();
            }
        });

        // Editar usuario
        btnEditar.addActionListener(e -> {
            if (usuarioSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario para editar.");
                return;
            }

            JTextField nombreField = new JTextField(usuarioSeleccionado.getNombre());
            JTextField emailField = new JTextField(usuarioSeleccionado.getEmail());
            JTextField rolField = new JTextField(usuarioSeleccionado.getRol());

            Object[] campos = {
                    "Nombre:", nombreField,
                    "Email:", emailField,
                    "Rol:", rolField
            };

            int option = JOptionPane.showConfirmDialog(null, campos, "Editar Usuario", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                usuarioSeleccionado.setNombre(nombreField.getText());
                usuarioSeleccionado.setEmail(emailField.getText());
                usuarioSeleccionado.setRol(rolField.getText());
                ControllerUsuario.editarAUsuario(usuarioSeleccionado);
                cargarTabla();
            }
        });

        // Eliminar usuario
        btnEliminar.addActionListener(e -> {
            if (usuarioSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario para eliminar.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Eliminar al usuario " + usuarioSeleccionado.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ControllerUsuario.eliminarUsuario(usuarioSeleccionado.getId());
                JOptionPane.showMessageDialog(null, "Usuario eliminado.");
                cargarTabla();
            }
        });
    }

    //Cargar todos los usuarios
    private void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Usuario> usuarios = ControllerUsuario.listarUsuarios();
        for (Usuario u : usuarios) {
            model.addRow(new Object[]{u.getId(), u.getNombre(), u.getEmail(), u.getRol()});
        }
    }

    //Filtro simple por nombre
    private void cargarTablaFiltrada(String filtro) {
        model.setRowCount(0);
        LinkedList<Usuario> filtrados = ControllerUsuario.listarUsuarios().stream()
                .filter(u -> u.getNombre() != null && u.getNombre().toLowerCase().startsWith(filtro.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        for (Usuario u : filtrados) {
            model.addRow(new Object[]{u.getId(), u.getNombre(), u.getEmail(), u.getRol()});
        }
    }
}
