package DroneArreglado;

public class Drone {
    String name;
    int maxWeight;
    int actual_weight;

    Drone(String name, int maxWeight) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.actual_weight = 0;
    }

    boolean canCarry(int weight) {

        return actual_weight + weight <= maxWeight;
    }
    void load(int weight) {

        actual_weight += weight;
    }

    void reset()
    {
        actual_weight = 0;
    }
}

