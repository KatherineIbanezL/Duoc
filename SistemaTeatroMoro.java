/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VentasTeatroMoro;

/**
 *
 * @author Katherine
 */

import java.util.Scanner;

public class SistemaTeatroMoro {
    // Variables estáticas
    static int totalIngresos = 0;
    static int totalEntradasVendidas = 0;
    static int totalReservas = 0;

    static String ubicacionVenta = null;
    static int cantidadEntradasVendidas = 0;
    static int precioUltimaVenta = 0;
    static boolean hayReserva = false;
    static String ubicacionReserva = null;
    static int cantidadReserva = 0;
    static String nombreTeatro = "Teatro Moro";

    public static void imprimirBoleta(String ubic, int cant, double desc, double precio){
        System.out.println("\n--- BOLETA ---");
        System.out.println("Teatro: " + nombreTeatro);//BREAKPOINT
        System.out.println("Zona: " + ubic);//BREAKPOINT
        System.out.println("Cantidad: " + cant);//BREAKPOINT
        System.out.println("Descuento aplicado: " + (desc * 100) + "%");//BREAKPOINT
        System.out.println("Total pagado: $" + precioUltimaVenta);//BREAKPOINT
        System.out.println("----------------");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String opcion = "";

        // Variables locales
        double descuentoEstudiante = 0.10;
        double descuentoTerceraEdad = 0.15;
        String tipoEntrada = "";
        double descuentoAplicado = 0;
        boolean hayVenta = false;

        // Asientos disponibles
        int disponiblesVIP = 5;
        int disponiblesPlatea = 5;
        int disponiblesGeneral = 5;

        while (true) {
            System.out.println("\nBienvenido a " + nombreTeatro);
            System.out.println("Seleccione una opcion:");
            System.out.println("Reservar entradas(1)");
            System.out.println("Comprar entradas(2)");
            System.out.println("Modificar venta(3)");
            System.out.println("Imprimir boleta(4)");
            System.out.println("Salir(5)");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.println("Seleccione ubicacion (VIP/PLATEA/GENERAL):");
                    tipoEntrada = scanner.nextLine();
                     
                    System.out.println("Cantidad de entradas a reservar:");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();

                    boolean disponible = false;

                    if (tipoEntrada.equals("VIP") && cantidad <= disponiblesVIP) { //BREAKPOINT
                        disponible = true;
                    } else if (tipoEntrada.equals("PLATEA") && cantidad <= disponiblesPlatea) {
                        disponible = true;
                    } else if (tipoEntrada.equals("GENERAL") && cantidad <= disponiblesGeneral) {
                        disponible = true;
                    }

                    if (disponible) {//BREAKPOINT
                        hayReserva = true;
                        ubicacionReserva = tipoEntrada;
                        cantidadReserva = cantidad;
                        totalReservas++;
                        System.out.println("Reserva realizada con exito.");
                    } else {
                        System.out.println("No hay suficientes asientos disponibles.");
                    }
                }

                case "2" -> {
                    if (hayReserva) {
                        System.out.println("Se ha encontrado una reserva. ¿Desea comprarla? (SI/NO)");
                        String confirmar = scanner.nextLine();

                        if (confirmar.equalsIgnoreCase("SI")) {
                            tipoEntrada = ubicacionReserva;
                            cantidadEntradasVendidas = cantidadReserva;
                            cantidadReserva = 0;
                        } else {
                            System.out.println("Reserva descartada. Ingrese nueva compra.");
                            hayReserva = false;
                        }
                    }

                    if (!hayReserva) {
                        System.out.println("Seleccione ubicacion (VIP/PLATEA/GENERAL):");
                        tipoEntrada = scanner.nextLine();

                        System.out.println("Cantidad de entradas a comprar:");
                        cantidadEntradasVendidas = scanner.nextInt();
                        scanner.nextLine();
                    }

                    int precioEntrada = 0;
                    switch (tipoEntrada) {//BREAKPOINT
                        case "VIP" -> precioEntrada = consultaPrecio("VIP");
                        case "PLATEA" -> precioEntrada = consultaPrecio("PLATEA");
                        case "GENERAL" -> precioEntrada = consultaPrecio("GENERAL");
                    }

                    System.out.println("Ingrese su edad:");
                    int edad = scanner.nextInt();
                    scanner.nextLine();

                    if (edad > 0 && edad <= 18) {
                        descuentoAplicado = descuentoEstudiante;//BREAKPOINT
                    } else if (edad >= 65) {
                        descuentoAplicado = descuentoTerceraEdad;//BREAKPOINT
                    } else {
                        descuentoAplicado = 0;//BREAKPOINT
                    }

                    int precioTotal = (int) ((precioEntrada * cantidadEntradasVendidas) * (1 - descuentoAplicado));//BREAKPOINT
                    precioUltimaVenta = precioTotal;
                    totalIngresos += precioTotal;
                    totalEntradasVendidas += cantidadEntradasVendidas;

                    // Actualizar disponibilidad
                    if (tipoEntrada.equals("VIP")) {
                        disponiblesVIP -= cantidadEntradasVendidas;//BREAKPOINT
                    } else if (tipoEntrada.equals("PLATEA")) {
                        disponiblesPlatea -= cantidadEntradasVendidas;//BREAKPOINT
                    } else if (tipoEntrada.equals("GENERAL")) {
                        disponiblesGeneral -= cantidadEntradasVendidas;//BREAKPOINT
                    }

                    ubicacionVenta = tipoEntrada;
                    hayVenta = true;

                    System.out.println("Compra realizada. Total a pagar: $" + precioTotal);
                    hayReserva = false;
                }

                case "3" -> {
                    if (hayVenta) {
                        System.out.println("Modificar ultima venta:");
                        System.out.println("Nueva cantidad de entradas:");
                        int nuevaCantidad = scanner.nextInt();
                        scanner.nextLine();

                        int diferencia = nuevaCantidad - cantidadEntradasVendidas;

                        if (diferencia > 0) {
                            boolean hayEspacio = false;

                            if (ubicacionVenta.equals("VIP") && diferencia <= disponiblesVIP) {
                                disponiblesVIP -= diferencia;
                                hayEspacio = true;
                            } else if (ubicacionVenta.equals("PLATEA") && diferencia <= disponiblesPlatea) {
                                disponiblesPlatea -= diferencia;
                                hayEspacio = true;
                            } else if (ubicacionVenta.equals("GENERAL") && diferencia <= disponiblesGeneral) {
                                disponiblesGeneral -= diferencia;
                                hayEspacio = true;
                            }

                            if (hayEspacio) {
                                cantidadEntradasVendidas = nuevaCantidad;
                                precioUltimaVenta = (int) ((nuevaCantidad * consultaPrecio(ubicacionVenta)) * (1 - descuentoAplicado));
                                System.out.println("La venta ha sido modificada con exito. Nuevo total: $" + precioUltimaVenta);
                            } else {
                                System.out.println("No hay entradas disponibles para modificar la venta.");
                            }

                        } else if (diferencia < 0) {
                            // devolución de entradas
                            if (ubicacionVenta.equals("VIP")) disponiblesVIP += Math.abs(diferencia);
                            else if (ubicacionVenta.equals("PLATEA")) disponiblesPlatea += Math.abs(diferencia);
                            else if (ubicacionVenta.equals("GENERAL")) disponiblesGeneral += Math.abs(diferencia);

                            cantidadEntradasVendidas = nuevaCantidad;
                            precioUltimaVenta = (int) ((nuevaCantidad * consultaPrecio(ubicacionVenta)) * (1 - descuentoAplicado));
                            System.out.println("Nuevo precio total de entradas es: $" + precioUltimaVenta);
                        } else {
                            System.out.println("La cantidad no ha cambiado.");
                        }
                    } else {
                        System.out.println("No hay ventas realizadas para modificar.");
                    }
                }

                case "4" -> {
                    if (hayVenta) {
                        imprimirBoleta(ubicacionVenta, cantidadEntradasVendidas, descuentoAplicado, precioUltimaVenta);
                    } else {
                        System.out.println("No hay ventas para imprimir.");
                    }
                }

                case "5" -> {
                    System.out.println("Gracias por usar el sistema.");
                    return;
                }

                default -> System.out.println("Opción invalida, intente nuevamente.");
            }
        }
    }

    public static int consultaPrecio(String ubicacion) {
        
        return switch (ubicacion) {
            case "VIP" -> 40000;
            case "PLATEA" -> 30000;
            case "GENERAL" -> 15000;
            default -> 0;
        };
        
    }
}

