package entities.plant_types;

import entities.Plant;

public class Algae extends Plant {
    private static final double oxygenBase = 0.5;
    private static final double possibilityToBlock = 20.0;

    public Algae(String name, double mass) {
        super(name, mass, oxygenBase, possibilityToBlock);
    }
}
