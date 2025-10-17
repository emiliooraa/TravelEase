package bll;

public class Hotel {
    private int id;
    private String nombre;
    private String destino;
    private int habitacionesDisponibles;
    public Hotel(int id, String nombre, String destino, int habitacionesDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.destino = destino;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }
    public Hotel(String nombre, String destino, int habitacionesDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.destino = destino;
        this.habitacionesDisponibles = habitacionesDisponibles;
    }
    public Hotel () {
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
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public int getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }
    public void setHabitacionesDisponibles(int habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }
    @Override
    public String toString() {
        return "Hotel [id=" + id + ", nombre=" + nombre + ", destino=" + destino + ", habitacionesDisponibles="
                + habitacionesDisponibles + "]";
    }
    
}
