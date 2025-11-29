package dll;

public class ControllerHotel {

    public void anadirHotel(String nombre, String ciudad, double precio) {
        System.out.println("Hotel añadido: " + nombre);
    }

    public void verHoteles() {
        System.out.println("Listado de hoteles...");
    }

    public void eliminarHotel(int idHotel) {
        System.out.println("Hotel eliminado ID: " + idHotel);
    }
}
