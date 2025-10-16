package bll;

import javax.swing.JOptionPane;

import dll.ControllerUsuario;
import repository.Validaciones;

public class Usuario {
    protected int id;
    protected String nombre;
    protected String dni;
    protected String email;
    protected String password; // Acá debería guardar el HASH, no texto plano
    protected String rol;

    // Constructor con id
    public Usuario(int id, String nombre, String dni, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.password = password; 
        this.rol = rol;
    }

    // Constructor sin id (antes de insertar en DB)
    public Usuario(String nombre, String dni, String email, String password, String rol) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.password = password; 
        this.rol = rol;
    }
    // Constructor sin password para hashear 
    public Usuario(int id, String nombre, String email) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    }
    //Constructor sin ingresar rol
    public Usuario (String nombre, String dni, String email, String password) {
        this.nombre = nombre;
        this.dni = dni;
        this.email= email;
        this.password = password;
        this.rol = "Usuario"; //Valor default 
    }

    // Constructor vacío
    public Usuario() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password; // Debería recibirse ya hasheado
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", dni=" + dni +
                ", email=" + email + ", rol=" + rol + "]";
    }

    public static Usuario login(){
        String email;
        String password;

        email = Validaciones.validarEmail("Ingrese su email:");
        password = Validaciones.validarPassword("Ingrese su contraseña:");
        
        return ControllerUsuario.login(email, password);
        
    }
      public static Usuario registrarUsuario() {
        String nombre = Validaciones.validarString("Ingrese su nombre:");
        String email = Validaciones.validarEmail("Ingrese su email:");
        String dni = Validaciones.validarDni("Ingrese su dni");
        String password = Validaciones.validarPassword("Ingrese su contraseña:");

        boolean registrado = ControllerUsuario.registrarUsuario(nombre, dni, email, password);

        if (registrado) {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            // Retornamos un objeto Usuario recién creado
            return new Usuario( nombre, dni,email, password);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario.");
            return null;
        }
    }
    public static void MenuLogin () {
        
    }
}
