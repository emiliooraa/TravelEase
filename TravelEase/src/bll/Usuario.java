package bll;

import javax.swing.JOptionPane;

import dll.ControllerUsuario;
import repository.Validaciones;

public class Usuario {
    protected int id;
    protected String nombre;
    protected String dni;
    protected String email;
    protected String password;
    protected String rol;
    public Usuario(int id, String nombre, String dni, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public Usuario(String nombre, String dni, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public Usuario () {

    }
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
    public void setEmail (String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", dni=" + dni + ", email=" + email + ", password=" + password + ", rol="
                + rol + "]";
    }
    public static Usuario Login() {
       String email;
		email = Validaciones.ValidarMail();
		String password;
		password = JOptionPane.showInputDialog("Ingrese password");
		if(email.isEmpty() || password.isEmpty()) { 
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
			return null;
		}else {
			return ControllerUsuario.login(email, password);
		}
		
    }
    
}
