package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import bll.Usuario;
import dll.ControllerUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarUsuariosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtFiltro;
	private Usuario usuarioSeleccionado;
	
	
	
	public GestionarUsuariosView(Usuario logueado) {
		setTitle("Panel de Gestionar Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 812, 582);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Bienvenida
        JLabel lblTitulo = new JLabel("Panel de Gestionar Usuarios ");
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(10, 1, 400, 56);
        lblTitulo.setFont(new Font("Gadugi", Font.PLAIN, 15));
        contentPane.add(lblTitulo);

        // Botón cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBackground(new Color(255, 0, 0));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrarSesion.setBounds(655, 18, 131, 25);
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
        // Boton Agregar
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

        
        // Cargar tabla 
        cargarTabla();
        
        
        // Agregar
        
        btnAgregar.addActionListener(null);
        
        //Editar
        
        btnEditar.addActionListener(null);
        
        // Eliminar
        btnEliminar.addActionListener(null);
        
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

