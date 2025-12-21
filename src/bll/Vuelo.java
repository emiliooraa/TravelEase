package bll;

import java.time.LocalDateTime;

public class Vuelo {

	private int id;
	private String codigo;
	private String origen;
	private String destino;
	private LocalDateTime fechaSalida;
	private LocalDateTime fechaLlegada;
	private String aerolinea;
	private int capacidadTotal;
	private int asientosDisponibles;

	public Vuelo(int id, String codigo, String origen, String destino, LocalDateTime fechaSalida,
			LocalDateTime fechaLlegada, String aerolinea, int capacidadTotal, int asientosDisponibles) {

		this.id = id;
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.aerolinea = aerolinea;
		this.capacidadTotal = capacidadTotal;
		this.asientosDisponibles = asientosDisponibles;
	}

	public Vuelo(String codigo, String origen, String destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada,
			String aerolinea,int capacidadTotal, int asientosDisponibles) {
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.aerolinea = aerolinea;
		this.capacidadTotal = capacidadTotal;
		this.asientosDisponibles = asientosDisponibles;
	}

	public Vuelo() {
	}

	public int getCapacidadTotal() {
		return capacidadTotal;
	}

	public void setCapacidadTotal(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}

	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}

	public int getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public LocalDateTime getFechaLlegada() {
		return fechaLlegada;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	@Override
	public String toString() {
		return "Vuelo [id=" + id + ", codigo=" + codigo + ", origen=" + origen + ", destino=" + destino
				+ ", fechaSalida=" + fechaSalida + ", fechaLlegada=" + fechaLlegada + ", aerolinea=" + aerolinea
				+ ", capacidadTotal=" + capacidadTotal + ", asientosDisponibles=" + asientosDisponibles + "]";
	}
}
