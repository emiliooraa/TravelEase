package bll;

import java.sql.Timestamp;

public class Reserva {

    private int idReserva;
    private Usuario cliente;

    private String tipo; // Tipo de reserva: VUELO / HOTEL / PAQUETE

    private Integer idVuelo;
    private Integer idHotel;
    private Integer idPaquete;

    private Timestamp fechaReserva;
    private String estado; // PENDIENTE / CANCELADA

    public Reserva() {
    }

    public Reserva(int idReserva, Usuario cliente, String tipo,
                   Integer idVuelo, Integer idHotel, Integer idPaquete,
                   Timestamp fechaReserva, String estado) {

        this.idReserva = idReserva;
        this.cliente = cliente;
        this.tipo = tipo;
        this.idVuelo = idVuelo;
        this.idHotel = idHotel;
        this.idPaquete = idPaquete;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
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

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //LÓGICA

    public boolean isCancelada() {
        return "CANCELADA".equalsIgnoreCase(estado);
    }

    public boolean isActiva() {
        return !isCancelada();
    }

    public void cancelar() {
        this.estado = "CANCELADA";
    }

    @Override
    public String toString() {
        return "Reserva [id=" + idReserva +
               ", cliente=" + cliente +
               ", tipo=" + tipo +
               ", estado=" + estado +
               ", fecha=" + fechaReserva + "]";
    }
}
