/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Exp2_S5_Katherine_Ibañez;

import java.util.Scanner;

/**
 *
 * @author Katherine
 */
public class VentasEntradas {
    //variables estaticas
    static int totalIngresos = 0;
    static int entradasVendidas = 0;

    public static void main(String[] args) {
        //variables de instancia
        Scanner scanner = new Scanner (System.in);
        String ubicacion = null;
        String opcion = null;
        String nombreTeatro = "Teatro Moro";   
        double precioFinal = 0;
        int precioEntrada = 0;
        double descuento = 0;
        
        //variables locales 
        String ultimaZonaVendida = null;
        double ultimaPromocionUsada = 0;
        double ultimoPrecioFinal = 0;
        
        int busquedaEntrada;
        int eliminarEntrada;
        
        int precioEntradaVip = 40000;
        int precioEntradaPlatea = 30000;
        int precioEntradaGeneral = 15000;
        
        double descuentoEstudiante = 0.10;
        double descuentoTerceraEdad = 0.15;
        
        while (true) {       
            System.out.println("\nBienvenido a " + nombreTeatro);
            //Volver a ingresar datos(1/2) en caso incorrecto
            boolean opcionesValidas = false;
            while (!opcionesValidas){
                //mostrar opciones
                System.out.println("\nSeleccione una de las siguientes opciones:");
                System.out.println("Comprar entrada(A) \nPromociones(B) \nBusqueda(C) \nEliminar entrada(D) \nSalir(E)");
                //solicitar opcion 
                opcion = scanner.nextLine();
                
                if (!"A".equals(opcion) && !"B".equals(opcion) && !"C".equals(opcion) && !"D".equals(opcion) && !"E".equals(opcion)){
                    System.out.println("Opcion invalida, intente nuevamente.");   
                } else {
                    opcionesValidas = true;
                }               
            }
            
            switch (opcion){
                case "A" -> {
                    do {                       
                        System.out.println("\nSeleccione una ubicacion(VIP/PLATEA/GENERAL):");
                        ubicacion = scanner.nextLine();

                        switch (ubicacion) {
                            case "VIP" -> precioEntrada = precioEntradaVip;
                            case "PLATEA" -> precioEntrada = precioEntradaPlatea;
                            case "GENERAL" -> precioEntrada = precioEntradaGeneral;
                            default -> System.out.println("Ubicacion no es correcta, intente nuevamente.");
                        }                       
                    } while (!"VIP".equals(ubicacion) && !"PLATEA".equals(ubicacion) && !"GENERAL".equals(ubicacion));

                    System.out.println("\nIngrese su edad:");

                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    
                    //validación de edades para aplicar descuento si corresponde
                    if (edad > 0 && edad <= 18){
                        descuento = descuentoEstudiante;
                    }
                    else if (edad >= 19 && edad < 65){
                        descuento = 0;
                    }
                    else if (edad > 0 && edad >= 65){
                        descuento = descuentoTerceraEdad;
                    }
                    else {
                        System.out.println("Edad no válida.");
                    }

                    //calculo de precio total
                    precioFinal = precioEntrada - (precioEntrada * descuento);
                    System.out.println("\nEl valor a pagar es $" + precioFinal);
                    
                    //total de ingresos actualizado
                    totalIngresos += precioFinal; 
                    
                    //incremento de contador de entrada
                    entradasVendidas ++;
                    
                    //almacenamiento de entrada
                    ultimaZonaVendida = ubicacion;
                    ultimaPromocionUsada = descuento;
                    ultimoPrecioFinal = precioFinal;
                }
                case "B" -> {
                    System.out.println("Promociones disponibles: descuento del 10 o 15%");
                }
                case "C" -> {
                    do {                       
                        System.out.println("Ingrese una opcion:");
                        System.out.println("Buscar ultima entrada(1) \nVer entradas vendidas(2)");
                        
                        busquedaEntrada = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (busquedaEntrada == 1){
                            System.out.println("Zona: " + ultimaZonaVendida);
                            System.out.println("Descuento realizado: " + (ultimaPromocionUsada * 100) + "%");
                            System.out.println("Tarifa total: " + "$" + ultimoPrecioFinal);
                        } else if (busquedaEntrada == 2){
                            System.out.println("Entradas vendidas actualmente: " + entradasVendidas);
                        }
                        else {
                            System.out.println("Ingrese un numero valido.");
                        }                       
                    } while (busquedaEntrada != 1 && busquedaEntrada != 2);
                }
                case "D" -> {
                    do {                       
                        System.out.println("\nIngrese entrada a eliminar: ");
                        System.out.println("Ultima entrada(1)");
                        
                        eliminarEntrada = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (eliminarEntrada == 1){
                            ultimaZonaVendida = null;
                            ultimaPromocionUsada = 0;
                            ultimoPrecioFinal = 0;
                            System.out.println("La entrada ha sido eliminada.");
                        } else {
                            System.out.println("Ingrese un numero valido.");
                        }                        
                    } while (eliminarEntrada != 1);
                }
                case "E" -> {
                    return;
                }
            }

        }
               
    }
    
}
