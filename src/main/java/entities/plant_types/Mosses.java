package entities.plant_types;

import entities.Plant;

public class Mosses extends Plant {
    private static final double oxygenBase = 0.8;
    private static final double possibilityToBlock = 40.0;

    public Mosses(String name, double mass) {
        super(name, mass, oxygenBase, possibilityToBlock);
    }
}