package Drone;
//Charly Alexandre Terrazas Rosado

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obtener la cantidad de drones con los cuales trabajaremos
        System.out.print("Introduce la cantidad de drones: ");
        int cantidadDrones = scanner.nextInt();
        scanner.nextLine();

        // Crear lista de drones
        List<Drone> drones = new ArrayList<>();

        // Introducir datos de cada dron
        for (int i = 1; i <= cantidadDrones; i++) {
            System.out.println("Datos del Drone " + i + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            int capacidadCarga;
            do {
                System.out.print("Capacidad de carga (mayor a 0 y <= 20): ");
                capacidadCarga = scanner.nextInt();
                scanner.nextLine();
            } while (capacidadCarga <= 0 || capacidadCarga > 20);

            drones.add(new Drone(nombre, capacidadCarga));
        }

        // Mostrar drones
        Drone.mostrarDrones(drones);

        // Crear entregas aleatorias y asignar ubicaciones
        Entregas entregas = new Entregas(drones);
        entregas.generarEntregasAleatorias();
        entregas.asignarUbicaciones();

        // Mostrar cantidad de cajas por dron
        entregas.mostrarCantidadCajas();

        // Mostrar eficiencia de los drones
        entregas.mostrarEficiencia();

        // Cerrar el scanner
        scanner.close();
    }
}

class Drone {
    private String nombre;
    private int capacidadCarga;
    private List<Integer> cajas;

    public Drone(String nombre, int capacidadCarga) {
        this.nombre = nombre;
        this.capacidadCarga = capacidadCarga;
        this.cajas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public List<Integer> getCajas() {
        return cajas;
    }

    public void agregarCaja(int pesoCaja) {
        cajas.add(pesoCaja);
    }

    public static void mostrarDrones(List<Drone> drones) {
        System.out.println("Lista de Drones:");
        for (Drone drone : drones) {
            System.out.println("Drone: " + drone.getNombre() + ", Carga: " + drone.getCapacidadCarga());
        }
    }
}

class Entregas {
    private List<Drone> drones;

    public Entregas(List<Drone> drones) {
        this.drones = drones;
    }

    public void generarEntregasAleatorias() {
        // Implementar lógica para generar entregas aleatorias
        Random random = new Random();
        for (Drone drone : drones) {
            int capacidadCarga = drone.getCapacidadCarga();
            int pesoTotalCajas = 0;

            while (pesoTotalCajas < capacidadCarga) {
                int pesoCaja = obtenerPesoCajaAleatorio();
                if (pesoTotalCajas + pesoCaja <= capacidadCarga) {
                    drone.agregarCaja(pesoCaja);
                    pesoTotalCajas += pesoCaja;
                }
            }
        }
    }

    private int obtenerPesoCajaAleatorio() {
        int[] pesosPosibles = {1, 2, 3, 5, 8, 10};
        return pesosPosibles[new Random().nextInt(pesosPosibles.length)];
    }

    public void mostrarCantidadCajas() {
        // Mostrar cantidad de cajas por dron
        for (Drone drone : drones) {
            int cantidadCajas = drone.getCajas().size();
            System.out.println(drone.getNombre() + " lleva " + cantidadCajas + " caja(s): " + drone.getCajas());
        }
    }

    public void asignarUbicaciones() {
        // Implementar lógica para asignar ubicaciones
        for (Drone drone : drones) {
            if (drone.getCapacidadCarga() > 15) {
                // Asignar el viaje mas largo
                System.out.println(drone.getNombre() + " Viaje mas largo");
            } else if (drone.getCapacidadCarga() > 10) {
                // Asignar el viaje medianamente largo
                System.out.println(drone.getNombre() + " Viaje mediano ");
            } else {
                // Asignar el viaje corto
                System.out.println(drone.getNombre() + " Viaje corto ");
            }
        }
    }

    public void mostrarEficiencia() {
        // Implementar lógica para mostrar eficiencia
        int maxCapacidadCarga = -1;
        String nombreDronEficiente = "";

        for (Drone drone : drones) {
            if (drone.getCapacidadCarga() > maxCapacidadCarga) {
                maxCapacidadCarga = drone.getCapacidadCarga();
                nombreDronEficiente = drone.getNombre();
            }
        }

        System.out.println("El dron más eficiente es: " + nombreDronEficiente);
    }

}
