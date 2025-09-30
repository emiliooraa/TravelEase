package bll;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelo {
    private int id;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private LocalTime horario;
    private int capacidad;
    private int asientosDisponibles;

    public Vuelo(int id, String origen, String destino, LocalDate fecha, LocalTime horario, int capacidad,
            int asientosDisponibles) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.horario = horario;
        this.capacidad = capacidad;
        this.asientosDisponibles = asientosDisponibles;
        }
    public Vuelo(String origen, String destino, LocalDate fecha, LocalTime horario, int capacidad,
            int asientosDisponibles) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.horario = horario;
        this.capacidad = capacidad;
        this.asientosDisponibles = asientosDisponibles;
    }
    public Vuelo() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHorario() {
        return horario;
    }
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }
    @Override
    public String toString() {
        return "Vuelo [id=" + id + ", origen=" + origen + ", destino=" + destino + ", fecha=" + fecha + ", horario="
                + horario + ", capacidad=" + capacidad + ", asientosDisponibles=" + asientosDisponibles + "]";
    }
    
}

