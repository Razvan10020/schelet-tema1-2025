package entities.plant_types;

import entities.Plant;

public class GymnospermsPlants extends Plant {
    private static final double OXYGEN_BASE = 0.0;
    private static final double POSSIBILITY_TO_BLOCK = 60.0;

    public GymnospermsPlants(final String name, final double mass) {
        super(name, mass, OXYGEN_BASE, POSSIBILITY_TO_BLOCK);
    }
}
