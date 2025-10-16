import java.util.Scanner;

public class MaquetaBase_Travelease {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new MaquetaBase_Travelease().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            clearScreen();
            System.out.println("=== Travelease - Maqueta Base (Consola) ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Acceder como invitado");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    loginMenu();
                    break;
                case "2":
                    registerMenu();
                    break;
                case "3":
                    guestMenu();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    pause();
            }
        }
        System.out.println("Saliendo... Gracias por usar la maqueta.");
    }

    /* ---------------- Menús principales ---------------- */

    private void loginMenu() {
        clearScreen();
        System.out.println("--- Iniciar sesión ---");
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        // Acá validaremos credenciales y obtenemos el rol.
        // En la maqueta pedimos elegir el rol manualmente.
        System.out.println("(Maqueta) Seleccione el rol de la sesión:");
        System.out.println("1. Administrador");
        System.out.println("2. Gerente");
        System.out.println("3. Operario");
        System.out.println("4. Cliente");
        System.out.print("Rol: ");
        String r = sc.nextLine().trim();

        switch (r) {
            case "1":
                adminMenu();
                break;
            case "2":
                managerMenu();
                break;
            case "3":
                operatorMenu();
                break;
            case "4":
                clientMenu();
                break;
            default:
                System.out.println("Rol inválido.");
                pause();
        }
    }

    private void registerMenu() {
        clearScreen();
        System.out.println("--- Registrar usuario ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Contraseña: ");
        String pw = sc.nextLine();

        // Acá guardamos el usuario en una base de datos.
        System.out.println("(Maqueta) Usuario registrado (simulado).");
        pause();
    }

    private void guestMenu() {
        boolean back = false;
        while (!back) {
            clearScreen();
            System.out.println("--- Invitado ---");
            System.out.println("1. Buscar vuelos");
            System.out.println("2. Buscar hoteles");
            System.out.println("3. Ver recomendaciones por estación");
            System.out.println("4. Volver");
            System.out.print("Opción: ");
            String o = sc.nextLine().trim();
            switch (o) {
                case "1":
                    stub("Buscar vuelos (simulado)");
                    break;
                case "2":
                    stub("Buscar hoteles (simulado)");
                    break;
                case "3":
                    stub("Recomendaciones por estación (simulado)");
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    pause();
            }
        }
    }

    /* ---------------- Menús por rol (SIN FUNCIONES) ---------------- */

    private void adminMenu() {
        boolean back = false;
        while (!back) {
            clearScreen();
            System.out.println("--- Administrador ---");
            System.out.println("1. Gestionar destinos");
            System.out.println("2. Gestionar vuelos");
            System.out.println("3. Gestionar hoteles");
            System.out.println("4. Gestionar paquetes (vuelo+hotel)");
            System.out.println("5. Gestionar usuarios (operarios, gerentes, clientes)");
            System.out.println("6. Configurar programas de fidelidad y descuentos");
            System.out.println("7. Ver reportes (simulado)");
            System.out.println("8. Volver");
            System.out.print("Opción: ");
            String o = sc.nextLine().trim();

            switch (o) {
                case "1":
                    stub("Alta/Baja/Modificación de destinos");
                    break;
                case "2":
                    stub("Alta/Baja/Modificación de vuelos");
                    break;
                case "3":
                    stub("Alta/Baja/Modificación de hoteles");
                    break;
                case "4":
                    stub("Alta/Baja/Modificación de paquetes");
                    break;
                case "5":
                    stub("Gestión de usuarios");
                    break;
                case "6":
                    stub("Configurar fidelidad y códigos de descuento");
                    break;
                case "7":
                    stub("Reportes (ocupación, ventas, etc.)");
                    break;
                case "8":
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    pause();
            }
        }
    }

    private void managerMenu() {
        boolean back = false;
        while (!back) {
            clearScreen();
            System.out.println("--- Gerente ---");
            System.out.println("1. Crear/editar paquetes promocionales");
            System.out.println("2. Ver disponibilidad de vuelos/hoteles");
            System.out.println("3. Aplicar descuentos por temporada");
            System.out.println("4. Comparar precios (simulado)");
            System.out.println("5. Asignar/Controlar operarios");
            System.out.println("6. Volver");
            System.out.print("Opción: ");
            String o = sc.nextLine().trim();

            switch (o) {
                case "1":
                    stub("Crear/editar paquetes");
                    break;
                case "2":
                    stub("Ver disponibilidad");
                    break;
                case "3":
                    stub("Aplicar descuentos por temporada");
                    break;
                case "4":
                    stub("Comparar precios");
                    break;
                case "5":
                    stub("Asignar/Controlar operarios");
                    break;
                case "6":
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    pause();
            }
        }
    }

    private void operatorMenu() {
        boolean back = false;
        while (!back) {
            clearScreen();
            System.out.println("--- Operario (Venta) ---");
            System.out.println("1. Registrar venta de vuelo");
            System.out.println("2. Registrar venta de hotel");
            System.out.println("3. Reservar paquete para cliente");
            System.out.println("4. Asignar asiento (simulado)");
            System.out.println("5. Buscar reservas");
            System.out.println("6. Volver");
            System.out.print("Opción: ");
            String o = sc.nextLine().trim();

            switch (o) {
                case "1":
                    stub("Registrar venta de vuelo");
                    break;
                case "2":
                    stub("Registrar venta de hotel");
                    break;
                case "3":
                    stub("Reservar paquete para cliente");
                    break;
                case "4":
                    stub("Asignar asiento (lógica no implementada)");
                    break;
                case "5":
                    stub("Buscar/Ver reservas");
                    break;
                case "6":
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    pause();
            }
        }
    }

    private void clientMenu() {
        boolean back = false;
        while (!back) {
            clearScreen();
            System.out.println("--- Cliente ---");
            System.out.println("1. Buscar vuelos");
            System.out.println("2. Buscar hoteles");
            System.out.println("3. Reservar vuelo");
            System.out.println("4. Reservar paquete (vuelo+hotel)");
            System.out.println("5. Reservar para varias personas");
            System.out.println("6. Aplicar código de descuento");
            System.out.println("7. Ver/Modificar/Cancelar reservas");
            System.out.println("8. Compartir itinerario (simulado)");
            System.out.println("9. Volver");
            System.out.print("Opción: ");
            String o = sc.nextLine().trim();

            switch (o) {
                case "1":
                    stub("Buscar vuelos");
                    break;
                case "2":
                    stub("Buscar hoteles");
                    break;
                case "3":
                    stub("Reservar vuelo");
                    break;
                case "4":
                    stub("Reservar paquete");
                    break;
                case "5":
                    stub("Reservar para varias personas (ingresar datos por pasajero)");
                    break;
                case "6":
                    stub("Aplicar código de descuento");
                    break;
                case "7":
                    stub("Ver/Modificar/Cancelar reservas");
                    break;
                case "8":
                    stub("Compartir itinerario (simulado)");
                    break;
                case "9":
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    pause();
            }
        }
    }

    /* ---------------- Utilidades y stubs ---------------- */

    private void stub(String texto) {
        System.out.println("[MAQUETA] " + texto + " - (funcionalidad no implementada en esta maqueta)");
        pause();
    }

    private void pause() {
        System.out.println("Presione Enter para continuar...");
        sc.nextLine();
    }

    private void clearScreen() {
        // Método simple para separar visualmente las pantallas.
        System.out.println("\n\n");
    }

    /* ---------------- Clases modelo (esqueleto) ---------------- */

}

// Acá podríamos agregar clases modelo básicas (esqueleto) en el mismo archivo
// para referencia. En una implementación real se separaría cada clase en su propio archivo.

class Usuario {
    public enum Rol {ADMIN, GERENTE, OPERARIO, CLIENTE}
    String nombre;
    String dni;
    String email;
    String contraseña;
    Rol rol;

    // constructor, getters y setters (omitiros para la maqueta)
}

class Vuelo {
    String id;
    String origen;
    String destino;
    String fecha; // en la maqueta usamos String; en el real usaremos LocalDate/LocalDateTime
    String horario;
    int capacidad;
    int asientosDisponibles;

    // métodos para alta/baja/modificación
}

class Hotel {
    String id;
    String nombre;
    String destino;
    int habitacionesDisponibles;
}

class Paquete {
    String id;
    Vuelo vuelo;
    Hotel hotel;
    double precio;
}

class Reserva {
    String id;
    Usuario cliente;
    Vuelo vuelo;
    Hotel hotel; // opcional, si es paquete
    Paquete paquete; // opcional
    boolean cancelada;
}

class ProgramaFidelidad {
    String id;
    String nombreAlojamientoOAerolinea;
    // métodos para vincular códigos de descuento
}

class Itinerario {
    Reserva[] reservas;
}

class Asiento {
    String fila;
    int numero;
    boolean ocupado;
}
