package bll;

public class Reserva {
    private int id;
    private Usuario cliente;
    private Vuelo vuelo;
    private Hotel hotel;
    private Paquete paquete;
    private boolean cancelada;
    public Reserva(int id, Usuario cliente, Vuelo vuelo, Hotel hotel, Paquete paquete, boolean cancelada) {
        this.id = id;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.hotel = hotel;
        this.paquete = paquete;
        this.cancelada = cancelada;
    }
    public Reserva(Usuario cliente, Vuelo vuelo, Hotel hotel, Paquete paquete, boolean cancelada) {
        this.id = id;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.hotel = hotel;
        this.paquete = paquete;
        this.cancelada = cancelada;
    }
    public Reserva() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getCliente() {
        return cliente;
    }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
    public Vuelo getVuelo() {
        return vuelo;
    }
    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Paquete getPaquete() {
        return paquete;
    }
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    public boolean isCancelada() {
        return cancelada;
    }
    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
    @Override
    public String toString() {
        return "Reserva [id=" + id + ", cliente=" + cliente + ", vuelo=" + vuelo + ", hotel=" + hotel + ", paquete="
                + paquete + ", cancelada=" + cancelada + "]";
    }
    
}
