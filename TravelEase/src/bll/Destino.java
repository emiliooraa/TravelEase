package bll;

public class Destino {

    private int id;
    private String nombre;
    
    public Destino(int id, String nombre, String pais) {
    	this.id = id;
    	this.nombre = nombre;
    	this.pais = pais;
    }
    private String pais;
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
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	@Override
	public String toString() {
		return "Destino [id=" + id + ", nombre=" + nombre + ", pais=" + pais + "]";
	}
	
    
}
