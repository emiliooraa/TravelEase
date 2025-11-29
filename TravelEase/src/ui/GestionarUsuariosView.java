package ui;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bll.Usuario;
import dll.ControllerUsuario;

public class GestionarUsuariosView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtFiltro;
    private Usuario usuarioSeleccionado;

    public GestionarUsuariosView(Usuario usuario) {

        setTitle("Panel de Gestionar Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 582);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Panel de Gestión de Usuarios - " + usuario.getNombre());
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(20, 1, 400, 56);
        lblTitulo.setFont(new Font("Gadugi", Font.PLAIN, 15));
        contentPane.add(lblTitulo);

        // Cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBackground(new Color(220, 20, 60));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrarSesion.setBounds(655, 18, 131, 25);
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            Inicio.main(new String[]{});
        });
        contentPane.add(btnCerrarSesion);

     

        // Panel usuarios
        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setLayout(null);
        panelUsuarios.setBounds(10, 79, 776, 453);
        contentPane.add(panelUsuarios);


        // Tabla
        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Email", "Rol"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 766, 258);
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

        // Botones CRUD
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAgregar.setBackground(new Color(152, 251, 152));
        btnAgregar.setBounds(10, 330, 120, 40);
        panelUsuarios.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEditar.setBackground(new Color(30, 144, 255));
        btnEditar.setBounds(140, 330, 120, 40);
        panelUsuarios.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setBounds(270, 330, 120, 40);
        panelUsuarios.add(btnEliminar);

        JLabel lblSeleccionado = new JLabel("Seleccionado: Ninguno");
        lblSeleccionado.setBounds(10, 390, 600, 20);
        panelUsuarios.add(lblSeleccionado);

        // Cargar tabla inicial
        cargarTabla();

        // AGREGAR
        btnAgregar.addActionListener(e -> {
            AgregarUsuarioView ventana = new AgregarUsuarioView(this);
            ventana.setVisible(true);
            
        });

        // EDITAR
        btnEditar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                EditarUsuarioView ventana = new EditarUsuarioView(this, usuarioSeleccionado);
                ventana.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
            }
        });

        // ELIMINAR
        btnEliminar.addActionListener(e -> {

            if (usuarioSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario primero.");
                return;
            }

            int conf = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estás seguro de eliminar este usuario?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (conf != JOptionPane.YES_OPTION) return;

            boolean eliminado = ControllerUsuario.eliminarUsuario(usuarioSeleccionado.getId());

            if (eliminado) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario.");
            }
        });

        // SELECCIÓN DE FILA → CARGAR USUARIO COMPLETO DESDE BD
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int id = (int) model.getValueAt(row, 0);
                    usuarioSeleccionado = ControllerUsuario.buscarUsuarioPorId(id); // ← FIX PRINCIPAL

                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId() +
                            ", Nombre=" + usuarioSeleccionado.getNombre() +
                            ", Email=" + usuarioSeleccionado.getEmail() +
                            ", Rol=" + usuarioSeleccionado.getRol());
                    
                    JButton btnVolver = new JButton("Volver");
                    btnVolver.setBounds(20, 45, 89, 23);
                    contentPane.add(btnVolver);
                }
            }
        });
    }

    // Cargar todos
    public void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Usuario> usuarios = ControllerUsuario.listarUsuarios();
        for (Usuario u : usuarios) {
            model.addRow(new Object[]{u.getId(), u.getNombre(), u.getEmail(), u.getRol()});
        }
    }

    // Filtro simple por nombre
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
