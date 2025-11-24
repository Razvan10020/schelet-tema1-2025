package entities.plant_types;

import entities.Plant;

public class Mosses extends Plant {
    private static final double OXYGEN_BASE = 0.8;
    private static final double POSSIBILITY_TO_BLOCK = 40.0;

    public Mosses(final String name, final double mass) {
        super(name, mass, OXYGEN_BASE, POSSIBILITY_TO_BLOCK);
    }
}
