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

    public Vuelo(int id, String codigo, String origen, String destino,
                 LocalDateTime fechaSalida, LocalDateTime fechaLlegada, String aerolinea) {
        this.id = id;
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.aerolinea = aerolinea;
    }

    public Vuelo(String codigo, String origen, String destino,
                 LocalDateTime fechaSalida, LocalDateTime fechaLlegada, String aerolinea) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.aerolinea = aerolinea;
    }

    public Vuelo() {}

    public int getId() { return id; }
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
}
