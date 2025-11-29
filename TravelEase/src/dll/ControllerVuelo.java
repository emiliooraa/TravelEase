package dll;

public class ControllerVuelo {

    // =============================
    //       CREAR VUELO
    // =============================
    public void crearVuelo(String codigo, String origen, String destino, 
                           String fecha, String hora, double precio) 
    {
        System.out.println("Vuelo creado: " + codigo);
    }

    // =============================
    //       EDITAR VUELO
    // =============================
    public void editarVuelo(int idVuelo, String codigo, String origen, 
                            String destino, String fecha, String hora, 
                            double precio) 
    {
        System.out.println("Vuelo editado ID: " + idVuelo);
    }

    // =============================
    //       CANCELAR VUELO
    // =============================
    public void cancelarVuelo(int idVuelo) {
        System.out.println("Vuelo cancelado ID: " + idVuelo);
    }

    // =============================
    //        VER VUELOS
    // =============================
    public void verVuelos() {
        System.out.println("Mostrando lista de vuelos...");
    }
}
