package bll;

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
}
