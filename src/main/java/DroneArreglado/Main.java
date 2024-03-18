package DroneArreglado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Drone> drones = new ArrayList<>();
        List<String[]> locations = new ArrayList<>();
         printDelivery droneDelivery = new printDelivery();
        AssingPackes assingPackes = new AssingPackes();


        // inputs dron
        String[] droneInputs = scanner.nextLine().split(", ");
        for (int i = 0; i < droneInputs.length; i += 2) {
            drones.add(new Drone(droneInputs[i], Integer.parseInt(droneInputs[i + 1])));
        }

        // inputs locations
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            locations.add(line.split(", "));
        }

        // Assing packages
        assingPackes.assignPackages(drones, locations);

        // print delivery
        droneDelivery.printDeliverySchedule(drones,locations);
    }

}
