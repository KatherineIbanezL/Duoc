/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Sistema_teatro_moro_V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SistemaTeatroMoroV2 {
    
    static Asiento[] asientos = new Asiento[10];
    static Reserva[] reservas = new Reserva[100];
    static List<double[]> descuentos = new ArrayList<>();
    static int contadorReservas = 0;
    static int ingresosTotales = 0;
    static int contadorClientes = 1;

    public static void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) { //BREAKPOINT
            String zona = (i < 3) ? "VIP" : (i < 6) ? "PLATEA" : "GENERAL";
            asientos[i] = new Asiento(i, zona);
        }
    }
    
    public static void inicializarDescuentos() {
        descuentos.add(new double[]{0, 18, 0.10});  // Estudiantes // BREAKPOINT
        descuentos.add(new double[]{65, 150, 0.15}); // Tercera edad
    }
    
    public static void mostrarAsientos() {
        System.out.println("Asientos disponibles:"); //BREAKPOINT
        for (Asiento a : asientos) {
            if (a.disponible)
                System.out.println("Numero asiento: " + a.id + " | Zona: " + a.zona);
        }
    }
    
    public static double obtenerDescuento(int edad) {
        for (double[] d : descuentos) { //BREAKPOINT
            if (edad >= d[0] && edad <= d[1]) {
                return d[2];
            }
        }
        return 0.0;
    }
    
    public static int obtenerPrecio(String zona) {
        return switch (zona) { //BREAKPOINT
            case "VIP" -> 40000;
            case "PLATEA" -> 30000;
            case "GENERAL" -> 15000;
            default -> 0;
        };
    }
    
    public static void ventaEntrada(Scanner scanner) {
        mostrarAsientos(); //BREAKPOINT
        System.out.println("Ingrese numero de asiento a comprar:");
        int idAsiento = scanner.nextInt(); scanner.nextLine();

        if (idAsiento < 0 || idAsiento >= asientos.length || !asientos[idAsiento].disponible) {
            System.out.println("Asiento no disponible.");
            return;
        }

        System.out.println("Ingrese edad:");
        int edad = scanner.nextInt(); scanner.nextLine();

        double desc = obtenerDescuento(edad); //BREAKPOINT
        int precioBase = obtenerPrecio(asientos[idAsiento].zona);
        int precioFinal = (int)(precioBase * (1 - desc));

        contadorClientes++; //BREAKPOINT
        reservas[contadorReservas++] = new Reserva(contadorClientes, idAsiento, desc);
        asientos[idAsiento].disponible = false;
        ingresosTotales += precioFinal;

        System.out.println("Venta exitosa. Total a pagar: $" + precioFinal);
    }
    
    public static void resumenVentas() {
        System.out.println("Total de entradas vendidas: " + contadorReservas); //BREAKPOINT
        int[] porZona = new int[3];
        for (int i = 0; i < contadorReservas; i++) {
            String zona = asientos[reservas[i].idAsiento].zona;
            switch (zona) {
                case "VIP" -> porZona[0]++;
                case "PLATEA" -> porZona[1]++;
                case "GENERAL" -> porZona[2]++;
            }
        }
        System.out.println("Ventas por zona: VIP = " + porZona[0] + " | PLATEA = " + porZona[1] + " | GENERAL = " + porZona[2]);
    }
    
    public static void imprimirBoleta(){
        if (contadorReservas == 0) { //BREAKPOINT
            System.out.println("No hay ventas.");
            return;
        }
        Reserva r = reservas[contadorReservas - 1];
        Asiento a = asientos[r.idAsiento];
        int precio = obtenerPrecio(a.zona);
        int totalPago = (int)(precio * (1 - r.descuento));
        
        System.out.println("-------------------------------------");
        System.out.println("           " + "Teatro Moro" + "   ");
        System.out.println("-------------------------------------");
        System.out.println("Zona: " + a.zona);//BREAKPOINT
        System.out.println("Costo base: $" + precio);
        System.out.println("Descuento aplicado: " + (int)(r.descuento * 100) + "%");//BREAKPOINT
        System.out.println("Costo final: $" + totalPago);
        System.out.println("-------------------------------------");
        System.out.println("Gracias por su visita al Teatro Moro");
        System.out.println("-------------------------------------");
    }
    
    public static void main(String[] args) {      
        Scanner scanner = new Scanner(System.in);
        
        inicializarAsientos();
        inicializarDescuentos();
        int opcion;
        do {
            System.out.println("\nBienvenido a Teatro Moro");
            System.out.println("Seleccione una opcion:");
            System.out.println("Venta de entradas(1)");
            System.out.println("Resumen de ventas(2)");
            System.out.println("Generar boleta(3)");
            System.out.println("Calcular ingresos totales(4)");
            System.out.println("Salir(5)");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) { 
                case 1 -> ventaEntrada(scanner);
                case 2 -> resumenVentas();
                case 3 -> imprimirBoleta();
                case 4 -> System.out.println("Ingresos totales: $" + ingresosTotales);
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
        
    }
}
