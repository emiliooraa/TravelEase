package bll;
public class Paquete {
    private int id;
    private Vuelo vuelo;
    private double precio;
    public Paquete(int id, Vuelo vuelo, double precio) {
        this.id = id;
        this.vuelo = vuelo;
        this.precio = precio;
    }
    public Paquete( Vuelo vuelo, double precio) {
        this.id = id;
        this.vuelo = vuelo;
        this.precio = precio;
    }
    public Paquete (){
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
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Paquete [id=" + id + ", vuelo=" + vuelo + ", precio=" + precio + "]";
    }
    
}
