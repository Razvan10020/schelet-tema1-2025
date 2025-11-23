package entities.plant_types;

import entities.Plant;

public class FloweringPlants extends Plant {
    private static final double oxygenBase = 6.0;
    private static final double possibilityToBlock = 90.0;

    public FloweringPlants(String name, double mass) {
        super(name, mass, oxygenBase, possibilityToBlock);
    }
}