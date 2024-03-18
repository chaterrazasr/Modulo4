package Ventas;

import java.util.Scanner;

// Clase principal del programa
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] productos = {"gorra", "polera", "pantalón", "chompa", "tenis"};

        System.out.println("Productos disponibles:");
        for (int i = 0; i < productos.length; i++) {
            System.out.println((i + 1) + ". " + productos[i]);
        }

        System.out.print("Ingrese el número del producto que desea comprar: ");
        int opcionProducto = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el precio por unidad del producto: ");
        double precioUnitario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("¿Desea comprar por unidad o por docena? (u/d): ");
        String opcionCompra = scanner.nextLine();

        double precioTotal;
        if (opcionCompra.equals("u")) {
            precioTotal = precioUnitario;
        } else if (opcionCompra.equals("d")) {
            precioTotal = precioUnitario * 10;
        } else {
            System.out.println("Opción inválida. El programa ha finalizado.");
            scanner.close();
            return;
        }

        String productoSeleccionado = productos[opcionProducto - 1];
        System.out.println("Usted ha seleccionado: " + productoSeleccionado);

        System.out.println("El precio a pagar es: " + precioTotal);

        scanner.close();
    }
}
