package dll;

public class ControllerDestino {

    // =============================
    //       AGREGAR DESTINO
    // =============================
    public void agregarDestino(String nombre, String pais, String descripcion) {
        System.out.println("Destino agregado: " + nombre + " (" + pais + ")");
    }

    // =============================
    //        EDITAR DESTINO
    // =============================
    public void editarDestino(int idDestino, String nombre, String pais, String descripcion) {
        System.out.println("Destino editado ID: " + idDestino);
    }

    // =============================
    //        VER DESTINOS
    // =============================
    public void verDestinos() {
        System.out.println("Mostrando lista de destinos...");
    }

    // =============================
    //      ELIMINAR DESTINO
    // =============================
    public void eliminarDestino(int idDestino) {
        System.out.println("Destino eliminado ID: " + idDestino);
    }
}
