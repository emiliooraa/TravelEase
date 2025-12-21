package bll;

import javax.swing.JOptionPane;

public class Cliente extends Usuario {

    private String destinoFavorito;
    private String medioPago;
    private boolean tieneReserva;

    public Cliente(int id, String nombre, String dni, String email, String password, String rol,
                   String destinoFavorito, String medioPago, boolean tieneReserva) {
        super(id, nombre, dni, email, password, rol);
        this.destinoFavorito = destinoFavorito;
        this.medioPago = medioPago;
        this.tieneReserva = tieneReserva;
    }

    public Cliente(String nombre, String dni, String email, String password,
                   String destinoFavorito, String medioPago, boolean tieneReserva) {
        super(nombre, dni, email, password, "Cliente");
        this.destinoFavorito = destinoFavorito;
        this.medioPago = medioPago;
        this.tieneReserva = tieneReserva;
    }

    public Cliente() {
        super();
        this.rol = "Cliente";
    }

    public String getDestinoFavorito() { return destinoFavorito; }
    public void setDestinoFavorito(String destinoFavorito) { this.destinoFavorito = destinoFavorito; }

    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }

    public boolean isTieneReserva() { return tieneReserva; }
    public void setTieneReserva(boolean tieneReserva) { this.tieneReserva = tieneReserva; }

    public void reservarViaje() {
        if (!tieneReserva) {
            tieneReserva = true;
            JOptionPane.showMessageDialog(null, "Reserva realizada con éxito. ¡Buen viaje, " + nombre + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Ya tenés una reserva activa.");
        }
    }

    public void cancelarReserva() {
        if (tieneReserva) {
            tieneReserva = false;
            JOptionPane.showMessageDialog(null, "Tu reserva fue cancelada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No tenés reservas para cancelar.");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCliente [Destino favorito=" + destinoFavorito +
                ", Medio de pago=" + medioPago +
                ", Tiene reserva=" + (tieneReserva ? "Sí" : "No") + "]";
    }

    public static Cliente registrarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        String dni = JOptionPane.showInputDialog("Ingrese su DNI:");
        String email = JOptionPane.showInputDialog("Ingrese su email:");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña:");
        String destino = JOptionPane.showInputDialog("Ingrese su destino favorito:");
        String medioPago = JOptionPane.showInputDialog("Ingrese su medio de pago preferido:");

        return new Cliente(nombre, dni, email, password, destino, medioPago, false);
    }
}