package bll;

import javax.swing.JOptionPane;

public class Invitado extends Usuario {

    private boolean registroPendiente;
    private String comentario;

    public Invitado(String nombre, String dni, String email, String password, boolean registroPendiente) {
        super(nombre, dni, email, password, "Invitado");
        this.registroPendiente = registroPendiente;
    }

    public Invitado(String nombre, String email) {
        super(nombre, "0", email, "", "Invitado");
        this.registroPendiente = true;
    }

    public Invitado() {
        super();
        this.rol = "Invitado";
        this.registroPendiente = true;
    }

    public boolean isRegistroPendiente() { return registroPendiente; }
    public void setRegistroPendiente(boolean registroPendiente) { this.registroPendiente = registroPendiente; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public void dejarComentario() {
        this.comentario = JOptionPane.showInputDialog("Deje su comentario o sugerencia sobre TravelEase:");
        JOptionPane.showMessageDialog(null, "¡Gracias por su opinión!");
    }

    public void solicitarRegistro() {
        this.registroPendiente = true;
        JOptionPane.showMessageDialog(null, "Solicitud de registro enviada. Un administrador la revisará pronto.");
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nInvitado [Registro pendiente=" + (registroPendiente ? "Sí" : "No") +
                ", Comentario=" + (comentario != null ? comentario : "N/A") + "]";
    }
}