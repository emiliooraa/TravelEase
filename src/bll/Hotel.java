package bll;

public class Hotel {
    private int id;
    private String nombre;
    private String ciudad;
    private String pais;
    private int estrellas;

    public Hotel(int id, String nombre, String ciudad, String pais, int estrellas) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.estrellas = estrellas;
    }

    public Hotel(String nombre, String ciudad, String pais, int estrellas) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.estrellas = estrellas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    @Override
    public String toString() {
        return nombre + " - " + ciudad + " (" + estrellas + "★)";
    }
}
