package bll;

public class Operario extends Usuario {
    private String turno;

    public Operario(int id, String nombre, String dni, String email, String password, String rol, String turno) {
        super(id, nombre, dni, email, password, rol);
        this.turno = turno;
    }

    public Operario(String nombre, String dni, String email, String password, String rol, String turno) {
        super(nombre, dni, email, password, rol);
        this.turno = turno;
    }

    public Operario(int id, String nombre, String email, String turno) {
        super(id, nombre, email);
        this.turno = turno;
    }

    public Operario(String nombre, String dni, String email, String password, String turno) {
        super(nombre, dni, email, password);
        this.turno = turno;
    }

    public Operario(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Operario [turno=" + turno + "]";
    }

    // Menú del Operario
    public static void menuOperario() {
        System.out.println("=== MENÚ OPERARIO ===");
        System.out.println("1. Ver tareas asignadas");
        System.out.println("2. Registrar actividad diaria");
        System.out.println("3. Reportar incidencia");
        System.out.println("4. Cerrar sesión");
        // lógica de selección de opciones
    }
}
