package bll;

public class Admin extends Usuario{
    private String sector;

    public Admin(int id, String nombre, String dni, String email, String password, String rol, String sector) {
        super(id, nombre, dni, email, password, rol);
        this.sector = sector;
    }

    public Admin(String nombre, String dni, String email, String password, String rol, String sector) {
        super(nombre, dni, email, password, rol);
        this.sector = sector;
    }

    public Admin(int id, String nombre, String email, String sector) {
        super(id, nombre, email);
        this.sector = sector;
    }

    public Admin(String nombre, String dni, String email, String password, String sector) {
        super(nombre, dni, email, password);
        this.sector = sector;
    }

    public Admin(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Admin [sector=" + sector + "]";
    }
    
    public static void MenuLogin () {

    }

}
