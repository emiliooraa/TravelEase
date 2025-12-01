package bll;

import java.time.LocalDateTime;

public class Vuelo {

    private int id;
    private String codigo;
    private String origen;
    private String destino;
    private String aerolinea;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;

    public Vuelo(int id, String codigo, String origen, String destino,
                 String aerolinea, LocalDateTime salida, LocalDateTime llegada) {
        this.id = id;
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.aerolinea = aerolinea;
        this.fechaSalida = salida;
        this.fechaLlegada = llegada;
    }

    public Vuelo() {}

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
    public String getAerolinea() { 
    	return aerolinea; 
    	}
    public LocalDateTime getFechaSalida() { 
    	return fechaSalida; 
    	}
    public LocalDateTime getFechaLlegada() { 
    	return fechaLlegada; 
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
    public void setAerolinea(String aerolinea) { 
    	this.aerolinea = aerolinea; 
    	}
    public void setFechaSalida(LocalDateTime fechaSalida) { 
    	this.fechaSalida = fechaSalida; 
    	}
    public void setFechaLlegada(LocalDateTime fechaLlegada) { 
    	this.fechaLlegada = fechaLlegada; 
    	}
}
