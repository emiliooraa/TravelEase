package bll;

public class ProgramaFidelidad {
    private int id;
    private String nombreAlojamString;
    public ProgramaFidelidad(int id, String nombreAlojamString) {
        this.id = id;
        this.nombreAlojamString = nombreAlojamString;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreAlojamString() {
        return nombreAlojamString;
    }
    public void setNombreAlojamString(String nombreAlojamString) {
        this.nombreAlojamString = nombreAlojamString;
    }
    @Override
    public String toString() {
        return "ProgramaFidelidad [id=" + id + ", nombreAlojamString=" + nombreAlojamString + "]";
    }

    
}
