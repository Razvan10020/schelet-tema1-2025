package entities.plant_types;

import entities.Plant;

public class FloweringPlants extends Plant {
    private static final double OXYGEN_BASE = 6.0;
    private static final double POSSIBILITY_TO_BLOCK = 90.0;

    public FloweringPlants(final String name, final double mass) {
        super(name, mass, OXYGEN_BASE, POSSIBILITY_TO_BLOCK);
    }
}
