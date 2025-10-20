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

    // Constructor sin id 
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
    //Contructor para el login
    public Usuario(int id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
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
        if (nombre == null) return null;
        String email = Validaciones.validarEmail("Ingrese su email:");
        if (email == null) return null;
        String dni = Validaciones.validarDni("Ingrese su DNI:");
        if (dni == null) return null;
        String password = Validaciones.validarPassword("Ingrese su contraseña:");
        if (password == null) return null;
        
        boolean registrado = ControllerUsuario.registrarUsuario(nombre, dni, email, password);
        if (registrado) {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            return new Usuario(nombre, dni, email, password);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario.");
            return null;
        }
    }

    	 public static void eliminarUsuario() {
    		 String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a eliminar:");
    	        
    	        if (idStr == null || idStr.trim().isEmpty()) {
    	            return;
    	        }

    	        int id;
    	        try {
    	            id = Integer.parseInt(idStr.trim());
    	        } catch (NumberFormatException e) {
    	            JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número entero válido.", 
    	                                          "Error de Input", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }

    	        int confirmacion = JOptionPane.showConfirmDialog(
    	            null, 
    	            "¿Está seguro de que desea eliminar el usuario con ID: " + id + "(" +")"+ "? Esta acción es irreversible.", 
    	            "Confirmar Eliminación", 
    	            JOptionPane.YES_NO_OPTION, 
    	            JOptionPane.WARNING_MESSAGE
    	        );

    	        if (confirmacion == JOptionPane.YES_OPTION) {
    	            boolean eliminado = ControllerUsuario.eliminarUsuario(id);
    	            
    	            if (eliminado) {
    	                JOptionPane.showMessageDialog(null, "Usuario con ID " + id + " eliminado correctamente.", 
    	                                              "Éxito", JOptionPane.INFORMATION_MESSAGE);
    	            } else {
    	                JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario. El ID podría no existir.", 
    	                                              "Error", JOptionPane.ERROR_MESSAGE);
    	            }
    	        }
    	    
    	  return;
    	 }

    	 public static void editarUsuario() {
    	     String idStr = JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a editar:");
    	     if (idStr == null || idStr.trim().isEmpty()) return;

    	     int id;
    	     try {
    	         id = Integer.parseInt(idStr.trim());
    	     } catch (NumberFormatException e) {
    	         JOptionPane.showMessageDialog(null, "Error: El ID debe ser un número entero válido.", 
    	                                       "Error de Input", JOptionPane.ERROR_MESSAGE);
    	         return;
    	     }
    	     Usuario usuarioAEditar = ControllerUsuario.buscarUsuarioPorId(id); 

    	     if (usuarioAEditar == null) {
    	         JOptionPane.showMessageDialog(null, "Usuario no encontrado con ID: " + id, 
    	                                       "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
    	         return;
    	     }
    	     String nuevoNombre = Validaciones.validarString("Nuevo Nombre (Actual: " + usuarioAEditar.getNombre() + "):");
    	     if (nuevoNombre == null) return; 

    	     String nuevoDni = Validaciones.validarDni("Nuevo DNI (Actual: " + usuarioAEditar.getDni() + "):");
    	     if (nuevoDni == null) return; 

    	     String nuevoEmail = Validaciones.validarEmail("Nuevo Email (Actual: " + usuarioAEditar.getEmail() + "):");
    	     if (nuevoEmail == null) return; 
 
    	     String[] roles = {"cliente", "operario", "manager", "admin"};
    	     int rolOpcion = JOptionPane.showOptionDialog(
    	         null, 
    	         "Nuevo Rol (Actual: " + usuarioAEditar.getRol() + "):", 
    	         "Editar Rol", 
    	         JOptionPane.DEFAULT_OPTION, 
    	         JOptionPane.QUESTION_MESSAGE, 
    	         null, 
    	         roles, 
    	         usuarioAEditar.getRol()
    	     );
    	     String nuevoRol = (rolOpcion >= 0) ? roles[rolOpcion] : usuarioAEditar.getRol();

    	     String nuevoPassword = JOptionPane.showInputDialog(null, "Nueva Contraseña (Dejar vacío para NO cambiar):", 
    	                                                       "Editar Usuario", JOptionPane.QUESTION_MESSAGE);

    	     usuarioAEditar.setNombre(nuevoNombre.trim());
    	     usuarioAEditar.setDni(nuevoDni.trim());
    	     usuarioAEditar.setEmail(nuevoEmail.trim());
    	     usuarioAEditar.setRol(nuevoRol);

    	     if (nuevoPassword == null || nuevoPassword.trim().isEmpty()) {
    	         usuarioAEditar.setPassword(null); 
    	     } else {
    	         usuarioAEditar.setPassword(nuevoPassword.trim()); 
    	     }

    	     ControllerUsuario.editarAUsuario(usuarioAEditar);
    	     
    	     JOptionPane.showMessageDialog(null, "La solicitud de edición se ha enviado a la base de datos.", 
    	                                   "Edición Completada", JOptionPane.INFORMATION_MESSAGE);
    	 }
}
