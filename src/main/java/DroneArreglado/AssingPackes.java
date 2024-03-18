package DroneArreglado;

import java.util.Comparator;
import java.util.List;

public class AssingPackes {
    static void assignPackages(List<Drone> drones , List<String[]> locations) {
        for (String[] location : locations) {
            int weight = Integer.parseInt(location[1]);
            drones.sort(Comparator.comparingInt(a -> a.actual_weight));
            for (Drone drone : drones) {
                if (drone.canCarry(weight)) {
                    drone.load(weight);
                    break;
                }
            }
        }
    }
}
