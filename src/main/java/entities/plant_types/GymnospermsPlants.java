package entities.plant_types;

import entities.Plant;

public class GymnospermsPlants extends Plant {
    private static final double oxygenBase = 0.0;
    private static final double possibilityToBlock = 60.0;

    public GymnospermsPlants(String name, double mass) {
        super(name, mass, oxygenBase, possibilityToBlock);
    }
}