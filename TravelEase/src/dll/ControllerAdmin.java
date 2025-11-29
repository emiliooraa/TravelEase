package dll;

public class ControllerAdmin {

    // GESTIONAR HOTELES ---------------------

    public void anadirHotel(String nombre, String ciudad, double precio) {
        // Lógica futura para añadir hotel
        System.out.println("Hotel añadido: " + nombre);
    }

    public void verHoteles() {
        // Lógica futura para listar hoteles
        System.out.println("Mostrando lista de hoteles...");
    }

    public void eliminarHotel(int idHotel) {
        // Lógica futura para eliminar hotel
        System.out.println("Hotel eliminado con ID: " + idHotel);
    }


    // GESTIONAR PAQUETES --------------------

    public void crearPaquete(String nombre, String descripcion, double precio) {
        System.out.println("Paquete creado: " + nombre);
    }

    public void editarPaquete(int id, String nombre, String descripcion, double precio) {
        System.out.println("Paquete editado: " + id);
    }

    public void verPaquetes() {
        System.out.println("Mostrando lista de paquetes...");
    }


    // RESERVAS ------------------------------

    public void gestionarReservas() {
        System.out.println("Gestión de reservas...");
    }

    public void verReporte() {
        System.out.println("Generando reporte...");
    }
}
