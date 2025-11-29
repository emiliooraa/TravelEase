package dll;

public class ControllerPaquete {

    public void crearPaquete(String nombre, String descripcion, double precio) {
        System.out.println("Paquete creado: " + nombre);
    }

    public void verPaquetes() {
        System.out.println("Listado de paquetes...");
    }

    public void editarPaquete(int id, String nombre, String descripcion, double precio) {
        System.out.println("Paquete editado ID: " + id);
    }
}
