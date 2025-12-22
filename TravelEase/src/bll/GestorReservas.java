package bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GestorReservas {

    private static List<Reserva> reservas = new ArrayList<>();

    public static class Reserva {
        private int id;
        private Usuario cliente;
        private String tipo;
        private String estado;
        private Timestamp fechaReserva;
        private boolean cancelada;
        private int idHotel;
        private int idPaquete;
        private int idVuelo; 
        private Vuelo vuelo;
        private Hotel hotel;

        public Reserva() {}

        public int getIdReserva() {
            return id;
        }

        public void setIdReserva(int id) {
            this.id = id;
        }

        public Usuario getCliente() {
            return cliente;
        }

        public void setCliente(Usuario cliente) {
            this.cliente = cliente;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public Timestamp getFechaReserva() {
            return fechaReserva;
        }

        public void setFechaReserva(Timestamp fechaReserva2) {
            this.fechaReserva = fechaReserva2;
        }

        public boolean isCancelada() {
            return cancelada;
        }

        public void setCancelada(boolean cancelada) {
            this.cancelada = cancelada;
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
        
        public int getIdVuelo() {
            return idVuelo;
        }

        public void setIdVuelo(int idVuelo) {
            this.idVuelo = idVuelo;
        }

        public int getIdHotel() {
            return idHotel;
        }

        public void setIdHotel(int idHotel) {
            this.idHotel = idHotel;
        }

        public int getIdPaquete() {
            return idPaquete;
        }

        public void setIdPaquete(int idPaquete) {
            this.idPaquete = idPaquete;
        }
    }
  
    public static void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

 
    public static List<Reserva> obtenerReservasDe(Usuario usuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getCliente() == usuario) { 
                resultado.add(r);
            }
        }
        return resultado;
    }

 
    public static Reserva buscarPorIdYCliente(int id, Usuario usuario) {
        for (Reserva r : reservas) {
            if (r.getIdReserva() == id && r.getCliente() == usuario) {   
                return r;
            }
        }
        return null;
    }

    public static boolean cancelarReserva(int id, Usuario usuario) {
        Reserva r = buscarPorIdYCliente(id, usuario);

        if (r != null && !r.isCancelada()) {  
            r.setCancelada(true);            
            return true;
        }

        return false;
    }
}
