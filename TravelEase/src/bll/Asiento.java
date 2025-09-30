package bll;

public class Asiento {
    private String fila;
    private int numero;
    private boolean ocupado;
    public Asiento(String fila, int numero, boolean ocupado) {
        this.fila = fila;
        this.numero = numero;
        this.ocupado = ocupado;
    }
    public Asiento () {
        
    }
    public String getFila() {
        return fila;
    }
    public void setFila(String fila) {
        this.fila = fila;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public boolean isOcupado() {
        return ocupado;
    }
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    @Override
    public String toString() {
        return "Asiento [fila=" + fila + ", numero=" + numero + ", ocupado=" + ocupado + "]";
    }
    
}
