package entities.plant_types;

import entities.Plant;

public class Ferns extends Plant {
    private static final double OXYGEN_BASE = 0.0;
    private static final double POSSIBILITY_TO_BLOCK = 30.0;

    public Ferns(final String name, final double mass) {
        super(name, mass, OXYGEN_BASE, POSSIBILITY_TO_BLOCK);
    }
}
