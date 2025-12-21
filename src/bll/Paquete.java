package bll;

public class Paquete {
    private int id;
    private Vuelo vuelo;
    private Hotel hotel;
    private double precio;

    public Paquete(int id, Vuelo vuelo, Hotel hotel) {
        this.id = id;
        this.vuelo = vuelo;
        this.hotel = hotel;
    }

    public Paquete(Vuelo vuelo, Hotel hotel) {
        this.vuelo = vuelo;
        this.hotel = hotel;
    }

    public Paquete() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Paquete [id=" + id + ", vuelo=" + vuelo + ", hotel=" + hotel + ", precio=" + precio + "]";
    }
}