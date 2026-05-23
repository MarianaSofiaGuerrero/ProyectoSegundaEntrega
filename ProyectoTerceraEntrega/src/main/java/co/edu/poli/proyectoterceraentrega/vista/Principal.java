package co.edu.poli.proyectoterceraentrega.vista;

import co.edu.poli.proyectoterceraentrega.modelo.Insumo;
import co.edu.poli.proyectoterceraentrega.servicios.ImplementacionOperacion;

import java.util.Scanner;

public class Principal {

    static Scanner sc = new Scanner(System.in);
    static ImplementacionOperacion gestion = new ImplementacionOperacion();

    public static void main(String[] args) {

        // Cargar datos guardados al iniciar
        gestion.deserializar();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> crearInsumo();
                case 2 -> leerUnInsumo();
                case 3 -> listarInsumos();
                case 4 -> modificarInsumo();
                case 5 -> eliminarInsumo();
                case 6 -> System.out.println(gestion.serializar());
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    //  Menú

    static void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   GESTIÓN DE INSUMOS AGRÍCOLAS   ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1. Crear insumo                 ║");
        System.out.println("║  2. Buscar insumo por ID         ║");
        System.out.println("║  3. Listar todos los insumos     ║");
        System.out.println("║  4. Modificar insumo             ║");
        System.out.println("║  5. Eliminar insumo              ║");
        System.out.println("║  6. Guardar en archivo (.bin)    ║");
        System.out.println("║  0. Salir                        ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    //  Operaciones CRUD

    static void crearInsumo() {
        System.out.println("\n--- CREAR INSUMO ---");
        String id            = leerTexto("ID: ");
        String nombre        = leerTexto("Nombre: ");
        String tipo          = leerTexto("Tipo (ej: fertilizante, herramienta, semilla): ");
        String unidad        = leerTexto("Unidad de medida (ej: kg, litro, unidad): ");
        double cantidad      = leerDouble("Cantidad disponible: ");
        double precio        = leerDouble("Precio unitario: ");
        String proveedor     = leerTexto("Proveedor: ");
        String fecha         = leerTexto("Fecha de compra (YYYY-MM-DD): ");

        Insumo nuevo = new Insumo(id, nombre, tipo, unidad, cantidad, precio, proveedor, fecha);
        System.out.println(gestion.crear(nuevo));
        System.out.println(gestion.serializar());
    }

    static void leerUnInsumo() {
        System.out.println("\n--- BUSCAR INSUMO ---");
        String id = leerTexto("Ingrese el ID del insumo: ");
        Insumo encontrado = gestion.leerUno(id);
        if (encontrado != null) {
            System.out.println("Insumo encontrado:\n  " + encontrado);
        } else {
            System.out.println("No se encontró ningún insumo con ID: " + id);
        }
    }

    static void listarInsumos() {
        System.out.println("\n--- LISTA DE INSUMOS ---");
        Insumo[] todos = gestion.leerTodos();
        if (todos.length == 0) {
            System.out.println("No hay insumos registrados.");
        } else {
            for (int i = 0; i < todos.length; i++) {
                System.out.println((i + 1) + ". " + todos[i]);
            }
        }
    }

    static void modificarInsumo() {
        System.out.println("\n--- MODIFICAR INSUMO ---");
        String id = leerTexto("Ingrese el ID del insumo a modificar: ");

        Insumo existente = gestion.leerUno(id);
        if (existente == null) {
            System.out.println("No se encontró ningún insumo con ID: " + id);
            return;
        }

        System.out.println("Insumo actual:\n  " + existente);
        System.out.println("Ingrese los nuevos datos (Enter para conservar el valor actual):");

        String nombre   = leerTextoOpcional("Nombre [" + existente.getNombre() + "]: ", existente.getNombre());
        String tipo     = leerTextoOpcional("Tipo [" + existente.getTipo() + "]: ", existente.getTipo());
        String unidad   = leerTextoOpcional("Unidad [" + existente.getUnidadMedida() + "]: ", existente.getUnidadMedida());
        double cantidad = leerDoubleOpcional("Cantidad [" + existente.getCantidadDisponible() + "]: ", existente.getCantidadDisponible());
        double precio   = leerDoubleOpcional("Precio [" + existente.getPrecioUnitario() + "]: ", existente.getPrecioUnitario());
        String proveedor = leerTextoOpcional("Proveedor [" + existente.getProveedor() + "]: ", existente.getProveedor());
        String fecha    = leerTextoOpcional("Fecha [" + existente.getFechaCompra() + "]: ", existente.getFechaCompra());

        Insumo modificado = new Insumo(id, nombre, tipo, unidad, cantidad, precio, proveedor, fecha);
        System.out.println(gestion.modificar(id, modificado));
    }

    static void eliminarInsumo() {
        System.out.println("\n--- ELIMINAR INSUMO ---");
        String id = leerTexto("Ingrese el ID del insumo a eliminar: ");
        Insumo eliminado = gestion.eliminar(id);
        if (eliminado != null) {
            System.out.println("Insumo eliminado:\n  " + eliminado);
        } else {
            System.out.println("No se encontró ningún insumo con ID: " + id);
        }
    }

    //  Utilidades de entrada

    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    static String leerTextoOpcional(String mensaje, String valorActual) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        return entrada.isEmpty() ? valorActual : entrada;
    }

    static int leerInt(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido.");
            }
        }
    }

    static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }

    static double leerDoubleOpcional(String mensaje, double valorActual) {
        System.out.print(mensaje);
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) return valorActual;
        try {
            return Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, se conserva el actual.");
            return valorActual;
        }
    }
}
