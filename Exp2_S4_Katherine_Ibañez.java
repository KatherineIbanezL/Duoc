/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplicationtmoro;

import java.util.Scanner;

/**
 *
 * @author Katherine
 */
public class Exp2_S4_Katherine_Ibañez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner (System.in);
        
        System.out.println("\nBienvenido a Teatro Moro");
        
        //Volver a ingresar datos(1/2) en caso incorrecto
        boolean compraValida = false;
        while (!compraValida) {
            
            System.out.println("\nSeleccione una de las opciones(1/2): ");
            //mostrar opciones
            String[] opciones = {"Comprar entrada(1)", "Salir(2)"};    
            for (int i = 0; i < opciones.length; i++) {
                System.out.println(opciones[i]);
            }
            //solicitar opcion 1 o 2 
            int comprarEntrada = scanner.nextInt();
            scanner.nextLine();
            
            //validar comprar o salir
            if (comprarEntrada == 1 && comprarEntrada <= opciones.length){
                compraValida = true;
            }
            else if (comprarEntrada == 2 && comprarEntrada <= opciones.length){
                return;
            }
            else{
                System.out.println("El valor ingresado no es correcto, por favor, intente nuevamente.");
            }
        }
        
        //permite realizar mas de una compra
        boolean seguirComprando = true;
        while (seguirComprando) {
            
            String ubicacion = null;
            //reingreso en caso de valor incorrecto
            while(!"VIP".equals(ubicacion) && !"PLATEA".equals(ubicacion) && !"PALCO".equals(ubicacion)) {
                
                System.out.println("Ingrese la ubicacion del asiento que desea comprar(VIP/PLATEA/PALCO): ");
                System.out.println(" " + "-----------");
                System.out.println("|" + " ESCENARIO " + "|");
                System.out.println(" " + "-----------");
                System.out.println("   " + "  VIP ");
                System.out.println("  " + "---------");
                System.out.println("   " + " PLATEA ");
                System.out.println("  " + "---------");
                System.out.println("   " + " PALCO ");
                System.out.println("  " + "---------"); 
                //solicitar ubicacion
                ubicacion = scanner.nextLine();
                //validar ubicacion incorrecta
                if (!"VIP".equals(ubicacion) && !"PLATEA".equals(ubicacion) && !"PALCO".equals(ubicacion)){
                    System.out.println("Valor incorrecto");
                }
            }    
            
            //solicitar edad
            System.out.println("Ingrese su edad para descuentos si corresponde: ");
            int edad = scanner.nextInt();
            
            //precios de entradas
            int precioEntradaVip = 30000;
            int precioEntradaPlatea = 20000;
            int precioEntradaPalco = 15000;
            int precioEntrada = 0;
            
            //descuentos
            double descuentoEstudiante = 0.10;
            double descuentoTerceraEdad = 0.15;
            double descuento = 0;
            
            //asignar precio segun ubicacion
            switch (ubicacion) {
                case "VIP":
                    precioEntrada = precioEntradaVip;
                    break;
                case "PLATEA":
                    precioEntrada = precioEntradaPlatea;
                    break;
                case "PALCO":
                    precioEntrada = precioEntradaPalco;
                    break;
                default:
                    break;
            }
            
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
                System.out.println("Edad no válida");
            }
            
            //calculo de precio total
            double precioFinal = precioEntrada - (precioEntrada * descuento);
            
            //Resumen de la compra
            System.out.println("El valor a pagar es $" + precioFinal);
            System.out.println("\nResumen de la compra: ");
            System.out.println("Ubicacion: " + ubicacion);
            System.out.println("Precio de entrada base: $" + precioEntrada);
            System.out.println("Descuento estudiante o tercera edad: " + (descuento * 100) + "%");
            System.out.println("Precio final a pagar: $" + precioFinal);
            
            //validacion de comprar-salir
            while (true) {
                
                //solicitar respuesta de continuar compra o salir
                System.out.println("Continuar comprando(1) / Salir(2)");
                int respuestaSeguir = scanner.nextInt();
                scanner.nextLine();
                
                //cortar ciclo para continuar comprando y validar ingreso inválido
                if (respuestaSeguir == 2) {
                    seguirComprando = false;
                    break;
                }
                else if (respuestaSeguir == 1){
                    break;
                }
                else{
                    System.out.println("Ingrese un numero valido."); 
                }
                
            }
            
        }

    }

}
    
    
