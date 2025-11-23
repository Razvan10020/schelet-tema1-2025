package entities.plant_types;

import entities.Plant;

public class Ferns extends Plant {
    private static final double oxygenBase = 0.0;
    private static final double possibilityToBlock = 30.0;

    public Ferns(String name, double mass) {
        super(name, mass, oxygenBase, possibilityToBlock);
    }
}
