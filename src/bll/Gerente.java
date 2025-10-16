package bll;

public class Gerente extends Usuario {
    private String areaResponsable;

    public Gerente(int id, String nombre, String dni, String email, String password, String rol, String areaResponsable) {
        super(id, nombre, dni, email, password, rol);
        this.areaResponsable = areaResponsable;
    }

    public Gerente(String nombre, String dni, String email, String password, String rol, String areaResponsable) {
        super(nombre, dni, email, password, rol);
        this.areaResponsable = areaResponsable;
    }

    public Gerente(int id, String nombre, String email, String areaResponsable) {
        super(id, nombre, email);
        this.areaResponsable = areaResponsable;
    }

    public Gerente(String nombre, String dni, String email, String password, String areaResponsable) {
        super(nombre, dni, email, password);
        this.areaResponsable = areaResponsable;
    }

    public Gerente(String areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    public String getAreaResponsable() {
        return areaResponsable;
    }

    public void setAreaResponsable(String areaResponsable) {
        this.areaResponsable = areaResponsable;
    }

    @Override
    public String toString() {
        return "Gerente [areaResponsable=" + areaResponsable + "]";
    }

    // Menú del Gerente
    public static void menuGerente() {
        System.out.println("=== MENÚ GERENTE ===");
        System.out.println("1. Ver reportes de ventas");
        System.out.println("2. Asignar tareas a operarios");
        System.out.println("3. Gestionar presupuestos");
        System.out.println("4. Cerrar sesión");
        // acá iría la lógica para seleccionar opciones (Scanner o JOptionPane)
    }
}
