package DroneArreglado;

import java.util.List;

public class printDelivery {


    static void printDeliverySchedule(List<Drone> drones, List<String[]> locations) {
        for (Drone drone : drones) {
            drone.reset();
        }
        for (int i = 0; i < drones.size(); i++) {
            Drone drone = drones.get(i);
            System.out.println(drone.name);
            int tripNumber = 1;
            for (int j = 0; j < locations.size(); j++) {
                if (drone.canCarry(Integer.parseInt(locations.get(j)[1]))) {
                    System.out.println("Trip #" + tripNumber++);
                    System.out.println(locations.get(j)[0]);
                    drone.load(Integer.parseInt(locations.get(j)[1]));
                }
            }
        }
    }
}
